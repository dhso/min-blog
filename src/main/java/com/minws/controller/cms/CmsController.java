package com.minws.controller.cms;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.ServletKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.minws.entity.sys.Message;
import com.minws.frame.kit.StringKit;
import com.minws.frame.sdk.ueditor.UeditorKit;
import com.minws.model.cms.Article;
import com.minws.model.cms.Tag;
import com.minws.model.sys.Config;
import com.rsslibj.elements.Channel;

public class CmsController extends Controller {

	public void error404() {
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		setAttr("message", new Message("404", "danger", "555~~ 页面不存在！"));
		render("front/message.htm", 404);
	}

	public void error500() {
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		setAttr("message", new Message("500", "danger", "555~~ 页面错误，请报告管理员！"));
		render("front/message.htm", 500);
	}

	public void index() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.getArticles(pageNumber, pageSize));
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/articleList.htm");
	}

	public void tag() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		Integer tagId = getParaToInt("tagId", -1);
		if (tagId != -1) {
			Page<Record> articlePage = Article.dao.getArticlesByTagId(pageNumber, pageSize, tagId);
			if (articlePage.getList().size() == 0) {
				setAttr("message", new Message("200", "warning", "没有找到标签ID为 " + tagId + " 的文章！"));
				render("front/message.htm");
				return;
			}
			setAttr("articlePage", articlePage);
		}
		setAttr("breadcrumbTag", Tag.dao.getTagByTagId(tagId));
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/articleList.htm");
	}

	public void search() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		String searchKey = getPara("searchKey", "");
		if (StringKit.isNotBlank(searchKey)) {
			Page<Record> articlePage = Article.dao.getArticlesBySearch(pageNumber, pageSize, "%".concat(searchKey).concat("%"));
			if (articlePage.getList().size() == 0) {
				setAttr("message", new Message("200", "warning", "没有搜索到与  " + searchKey + " 相关的文章！"));
				render("front/message.htm");
				return;
			}
			setAttr("articlePage", articlePage);
		}
		setAttr("breadcrumbSearch", searchKey);
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/articleList.htm");
	}

	public void articlePage() {
		Integer articleId = getParaToInt("articleId", -1);
		Record article = null;
		if (articleId != -1) {
			Article.dao.updateArticleView(articleId);
			article = Article.dao.getArticleByArticleId(articleId);
		}
		if (null == article) {
			renderError(404, "error404");
			return;
		}
		setAttr("article", article);
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/articlePage.htm");
	}

	public void tagsWall() {
		setAttr("tagList", Tag.dao.getTags());
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
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
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("back/addArticle.htm");
	}

	@ActionKey("back/article/submit")
	@RequiresPermissions(value = { "cms:article:add", "cms:article:edit" }, logical = Logical.OR)
	@Before(Tx.class)
	public void submitArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		String articleTitle = getPara("articleTitle");
		String editorValue = getPara("editorValue").replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		String articleTag = getPara("articleTag");
		if (articleId == -1) {
			Record article = Article.dao.addArticle(articleTitle, editorValue, articleTag);
			articleId = article.getInt("articleId");
			redirect("/back/article/edit?articleId=" + String.valueOf(articleId) + "&type=add");
		} else {
			Article.dao.updateArticle(articleId, articleTitle, editorValue, articleTag);
			redirect("/back/article/edit?articleId=" + String.valueOf(articleId) + "&type=update");
		}

	}

	@ActionKey("back/article/edit")
	@RequiresPermissions("cms:article:edit")
	public void editArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		String type = getPara("type", "");
		if (articleId != -1) {
			setAttr("article", Article.dao.getArticleByArticleId(articleId));
		}
		if ("add".equalsIgnoreCase(type)) {
			setAttr("message", new Message("200", "success", "添加文章成功！"));
		} else if ("update".equalsIgnoreCase(type)) {
			setAttr("message", new Message("200", "success", "修改文章成功！"));
		}
		setAttr("tagList", Tag.dao.getTags());
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("back/editArticle.htm");
	}

	@ActionKey("back/article/delete")
	@RequiresPermissions("cms:article:delete")
	@Before(Tx.class)
	public void deleteArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		if (articleId != -1) {
			Article.dao.deleteArticle(articleId);
			setAttr("message", new Message("200", "success", "删除文章成功！"));
		} else {
			setAttr("message", new Message("200", "error", "错误的文章ID！"));
		}
		render("front/message.htm");
	}

	@ActionKey("back/settingsPage")
	@RequiresPermissions("cms:setting:edit")
	public void settingsPage() {
		setAttr("cmsName", Config.dao.getValueByKey("cms_name"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("back/settingsPage.htm");
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
