/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.model.cms;

import java.util.List;

import com.jfinal.ext.plugin.sqlinxml.SqlKit;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
@TableBind(tableName = "cms_articles", pkName = "article_id")
public class Article extends Model<Article> {
	public static final Article dao = new Article();

	public Page<Record> selectArticlesByCategoryId(int category_id, int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, SqlKit.sql("cms.getArticles"), SqlKit.sql("cms.getArticlesEx"));
	}

	public Page<Record> getArticles(int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, SqlKit.sql("cms.getArticles"), SqlKit.sql("cms.getArticlesEx"));
	}

	public Article selectArticleByArticleId(int articleId) {
		return Article.dao.findFirst(SqlKit.sql("blog.selectArticleByArticleIdSql"), articleId);
	}

	public List<Article> selectPopularArticles(int limitNum) {
		return Article.dao.find(SqlKit.sql("blog.selectPopularArticlesSql"), limitNum);
	}

	public List<Article> selectRecentArticles(int limitNum) {
		return Article.dao.find(SqlKit.sql("blog.selectRecentArticlesSql"), limitNum);
	}

	public boolean insertArticle(String articleTitle, String categoryId, String thumbnail, String editorValue, String uname) {
		return new Article().set("category_id", categoryId).set("title", articleTitle).set("thumbnail", thumbnail).set("content", editorValue).set("author", uname).save();
	}

}