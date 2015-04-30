/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.wechat.model.cms;

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
		return Db.paginate(pageNumber, pageSize, "select art.article_id as articleid,art.article_thumb as articlethumb,art.article_title as articletitle,art.article_content as articlecontent,usr.username as articleauthor,art.article_view as articleview,art.create_dt as articlecreatedt,art.update_dt as articleupdatedt,cat.category_id as categoryid,cat.category_name as categoryname, group_concat(tag.tag_id) as tagid, group_concat(tag.tag_name) as tagname", "from cms_articles as art left join cms_categories as cat on cat.category_id = art.category_id left join users as usr on usr.id = art.user_id left join cms_article_tag as art_tag on art_tag.article_id = cat.category_id left join cms_tags as tag on tag.tag_id = art_tag.tag_id group by art.article_id order by art.update_dt desc");
	}

	public Page<Record> getArticles(int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, "select art.article_id as articleid,art.article_thumb as articlethumb,art.article_title as articletitle,art.article_content as articlecontent,usr.username as articleauthor,art.article_view as articleview,art.create_dt as articlecreatedt,art.update_dt as articleupdatedt,cat.category_id as categoryid,cat.category_name as categoryname, group_concat(tag.tag_id) as tagid, group_concat(tag.tag_name) as tagname", "from cms_articles as art left join cms_categories as cat on cat.category_id = art.category_id left join users as usr on usr.id = art.user_id left join cms_article_tag as art_tag on art_tag.article_id = cat.category_id left join cms_tags as tag on tag.tag_id = art_tag.tag_id group by art.article_id order by art.update_dt desc");
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