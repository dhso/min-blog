/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.model.cms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;

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

	public Page<Record> getArticles(int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, SqlKit.sql("cms.getArticles"), SqlKit.sql("cms.getArticlesEx"));
	}

	public Page<Record> getArticles(Integer pageNumber, Integer pageSize, Integer tagId) {
		return Db.paginate(pageNumber, pageSize, SqlKit.sql("cms.getArticlesByTagId"), SqlKit.sql("cms.getArticlesByTagIdEx"), tagId);
	}

	public Record getArticleByArticleId(Integer articleId) {
		return Db.findFirst(SqlKit.sql("cms.getArticleByArticleId"), articleId);
	}

	public List<Record> getPopularArticles(int number) {
		return Db.find(SqlKit.sql("cms.getPopularArticles"), number);
	}

	public void addArticle(String articleTitle, String editorValue, String articleTag) {
		List<String> tags = Arrays.asList(articleTag.split(","));
		List<Integer> tagsId = new ArrayList<Integer>();
		Iterator<String> it = tags.iterator();
		while (it.hasNext()) {
			String tagName = it.next();
			Record record = Db.findFirst("select * from cms_tags where tag_name = ?", tagName);
			if (null == record) {
				Db.update("insert into cms_tags (tag_name) values (?)", tagName);
				record = Db.findFirst("select * from cms_tags where tag_name = ?", tagName);
				Integer tagId = record.getInt("tag_id");
				tagsId.add(tagId);
			} else {
				Integer tagId = record.getInt("tag_id");
				tagsId.add(tagId);
			}
		}
		Db.update(SqlKit.sql("cms.addArticle"), articleTitle, editorValue, SecurityUtils.getSubject().getPrincipal(), new Date());
		Integer articleId = Db.findFirst("select article_id from cms_articles where article_title = ?", articleTitle).getInt("article_id");
		Iterator<Integer> tagsIt = tagsId.iterator();
		while (tagsIt.hasNext()) {
			Db.update("insert into cms_article_tag (article_id,tag_id) values(?,?)", articleId, tagsIt);
		}
	}

	public void updateArticle(Integer articleId, String articleTitle, String editorValue, String articleTag) {
		Db.update(SqlKit.sql("cms.updateArticle"), articleId, articleTitle, editorValue, articleTag);
	}

}