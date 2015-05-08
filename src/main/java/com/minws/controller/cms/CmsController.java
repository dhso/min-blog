package com.minws.controller.cms;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.minws.frame.kit.StringKit;
import com.minws.frame.sdk.ueditor.UeditorKit;
import com.minws.model.cms.Article;
import com.minws.model.cms.Tag;
import com.minws.model.sys.Config;
import com.rsslibj.elements.Channel;

public class CmsController extends Controller {

	public void index() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		Integer tagId = getParaToInt("tagId", -1);

		if (tagId == -1) {
			setAttr("articlePage", Article.dao.getArticles(pageNumber, pageSize));
		} else {
			setAttr("articlePage", Article.dao.getArticles(pageNumber, pageSize, tagId));
			setAttr("breadcrumbTag", Tag.dao.getTagByTagId(tagId));
		}
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		render("front/articleList.htm");
	}

	public void articlePage() {
		Integer articleId = getParaToInt("articleId", -1);
		if (articleId != -1) {
			Article.dao.updateArticleView(articleId);
			setAttr("article", Article.dao.getArticleByArticleId(articleId));
		}
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		render("front/articlePage.htm");
	}

	public void tagsWall() {
		setAttr("tagList", Tag.dao.getTags());
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		render("front/tagsWall.htm");
	}

	@ActionKey("back/ueditor")
	public void ueditor() throws JSONException, FileUploadException {
		String result = new UeditorKit(getRequest()).exec();
		renderText(result);
	}

	@ActionKey("back/article/add")
	@RequiresPermissions("cms:article:add")
	public void addArticle() {
		setAttr("tagList", Tag.dao.getTags());
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		render("back/addArticle.htm");
	}

	@ActionKey("back/article/submit")
	@RequiresPermissions("cms:article:add")
	@Before(Tx.class)
	public void submitArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		String articleTitle = getPara("articleTitle");
		String editorValue = getPara("editorValue").replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		String articleTag = getPara("articleTag");
		if (articleId == -1) {
			Record article = Article.dao.addArticle(articleTitle, editorValue, articleTag);
			setAttr("article", article);
			articleId = article.getInt("articleId");
		} else {
			Article.dao.updateArticle(articleId, articleTitle, editorValue, articleTag);
			setAttr("article", Article.dao.getArticleByArticleId(articleId));
		}
		setAttr("tagList", Tag.dao.getTags());
		redirect("/back/article/edit?articleId=" + String.valueOf(articleId));
	}

	@ActionKey("back/article/edit")
	@RequiresPermissions("cms:article:edit")
	public void editArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		if (articleId != -1) {
			setAttr("article", Article.dao.getArticleByArticleId(articleId));
		}
		setAttr("tagList", Tag.dao.getTags());
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		render("back/editArticle.htm");
	}

	public void rss() throws ParseException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Record> articlePage = Article.dao.getArticles(1, 100).getList();
		String mainUrl = "http://www.minws.com";
		Channel channel = new Channel();
		channel.setDescription("This is my sample channel.");
		channel.setLink("/rss");
		channel.setTitle("blog rss");
		channel.setImage(mainUrl, "The Channel Image", mainUrl + "/foo.jpg");
		Iterator<Record> it = articlePage.iterator();
		while (it.hasNext()) {
			Record article = it.next();
			channel.addItem("articlePage?articleId=" + String.valueOf(article.get("articleId")), StringKit.replaceHtml(article.getStr("articleContent")), article.getStr("articleTitle")).setDcContributor(article.getStr("articleAuthor"));
		}
		renderText(channel.getFeed("rss"), "text/xml");
	}
}
