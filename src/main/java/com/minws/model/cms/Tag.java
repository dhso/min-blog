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
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
@TableBind(tableName = "cms_tags", pkName = "tag_id")
public class Tag extends Model<Tag> {
	public static final Tag dao = new Tag();

	public List<Record> getTags() {
		return Db.find(SqlKit.sql("cms.getTags"));
	}

}