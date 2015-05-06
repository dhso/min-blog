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
		Integer articleId = getParaToInt("articleId", 1);
		setAttr("article", Article.dao.getArticleByArticleId(articleId));
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
		String editorValue = getPara("editorValue");
		String articleTag = getPara("articleTag");
		if (articleId == -1) {
			Article.dao.addArticle(articleTitle, editorValue, articleTag);
		} else {
			Article.dao.updateArticle(articleId, articleTitle, editorValue, articleTag);
		}
		setAttr("tagList", Tag.dao.getTags());
		render("back/addArticle.htm");
	}
	/**//**
	 * 分页查询客户
	 */
	/*
	 * public void articleJson() { int pageNumber = getParaToInt("page", 1); int
	 * pageSize = getParaToInt("rows", 10); //Page<Article> articlePage =
	 * Article.dao.selectAllArticles(pageNumber, pageSize); //DataGrid DataGrid
	 * = new DataGrid(String.valueOf(articlePage.getTotalRow()),
	 * articlePage.getList()); renderJson(DataGrid); }
	 * 
	 * public void single() { Integer articleId = getParaToInt("articleId", 1);
	 * setAttr("article", Article.dao.selectArticleByArticleId(articleId));
	 * setAttr("categoryList", Category.dao.selectAllCategories());
	 * setAttr("popularArticleList", Article.dao.selectPopularArticles(5));
	 * render("front/single.htm"); }
	 * 
	 * public void work() { Integer articleId = getParaToInt("articleId", 1);
	 * setAttr("article", Article.dao.selectArticleByArticleId(articleId));
	 * setAttr("categoryList", Category.dao.selectAllCategories());
	 * setAttr("popularArticleList", Article.dao.selectPopularArticles(5));
	 * render("front/work.htm"); }
	 * 
	 * public void page() { render("front/page.htm"); }
	 * 
	 * public void pageTypography() { render("front/page-typography.htm"); }
	 * 
	 * public void pageElements() { render("front/page-elements.htm"); }
	 * 
	 * public void contact() { render("front/contact.htm"); }
	 * 
	 * @ActionKey("cms/back/article/manage")
	 * 
	 * @RequiresPermissions("cms:article") public void manageArticle() { Integer
	 * pageNumber = getParaToInt("pageNumber", 1); Integer pageSize =
	 * getParaToInt("pageSize", 10); setAttr("articlePage",
	 * Article.dao.selectAllArticles(pageNumber, pageSize));
	 * render("back/article-manage.htm"); }
	 * 
	 * @ActionKey("cms/back/article/add")
	 * 
	 * @RequiresPermissions("cms:article") public void addArticle() { String
	 * articleTitle = getPara("articleTitle", ""); String categoryId =
	 * getPara("categoryId", ""); String thumbnail = getPara("thumbnail", "");
	 * String editorValue = getPara("editorValue", ""); if
	 * ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
	 * if (Article.dao.insertArticle(articleTitle, categoryId, thumbnail,
	 * editorValue, ShiroKit.whoAmI())) { setAttr("errorMsg", new Message("200",
	 * "alert-success", "添加文章成功！")); } else { setAttr("errorMsg", new
	 * Message("200", "alert-error", "添加文章失败！")); } } setAttr("categoryList",
	 * Category.dao.selectAllCategories()); setAttr("articleTitle",
	 * articleTitle); setAttr("categoryId", categoryId); setAttr("editorValue",
	 * editorValue); render("back/article-add.htm"); }
	 */
}
