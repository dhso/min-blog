/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.model.cms;

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

	public Record getArticleByArticleId(Integer articleId) {
		return Db.findFirst(SqlKit.sql("cms.getArticleByArticleId"), articleId);
	}

}