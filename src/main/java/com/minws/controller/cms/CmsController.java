package com.minws.controller.cms;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.minws.frame.sdk.ueditor.UeditorKit;
import com.minws.model.cms.Article;
import com.minws.model.cms.Tag;

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
		render("front/articlePage.htm");
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
			setAttr("article", Article.dao.addArticle(articleTitle, editorValue, articleTag));
		} else {
			Article.dao.updateArticle(articleId, articleTitle, editorValue, articleTag);
			setAttr("article", Article.dao.getArticleByArticleId(articleId));
		}
		setAttr("tagList", Tag.dao.getTags());
		render("back/editArticle.htm");
	}

	@ActionKey("back/article/edit")
	@RequiresPermissions("cms:article:edit")
	public void editArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		if (articleId != -1) {
			setAttr("article", Article.dao.getArticleByArticleId(articleId));
		}
		setAttr("tagList", Tag.dao.getTags());
		render("back/editArticle.htm");
	}
}
