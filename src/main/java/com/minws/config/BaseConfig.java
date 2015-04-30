package com.minws.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.config.ConfigPlugin;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.ext.plugin.sqlinxml.SqlInXmlPlugin;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.RedirectRender;
import com.jfinal.render.Render;
import com.minws.controller.cms.CmsController;
import com.minws.controller.sys.SecurityController;
import com.minws.frame.kit.HttpKit;
import com.minws.frame.plugin.shiro.FreemarketShiroTags;
import com.minws.frame.sdk.alipay.ctrl.AlipayController;

public class BaseConfig extends JFinalConfig {

	private Routes routes;

	@Override
	public void configConstant(Constants cs) {
		loadPropertyFile("config.txt");
		cs.setDevMode(getPropertyToBoolean("wx.devMode"));
		cs.setError401View("/security/signin");
		cs.setError403View("/security/signin");
		cs.setError404View("/security/err404");
		cs.setError500View("/security/err500");
		cs.setErrorRenderFactory(new IErrorRenderFactory() {
			@Override
			public Render getRender(int errorCode, String view) {
				return new RedirectRender(view);
			}
		});
	}

	@Override
	public void configRoute(Routes rs) {
		this.routes = rs;
		rs.add("/security", SecurityController.class, "/security");// 安全
		rs.add("/", CmsController.class, "/cms");// CMS
		rs.add("/alipay", AlipayController.class, "/alipay");// 支付宝
	}

	@Override
	public void configPlugin(Plugins ps) {
		// 添加shiro支持
		ps.add(new ShiroPlugin(routes));
		// 添加缓存支持
		ps.add(new EhCachePlugin(BaseConfig.class.getClassLoader().getResource("ehcache-model.xml")));
		// add sql xml plugin
		ps.add(new SqlInXmlPlugin());
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("wx.jdbcUrl"), getProperty("wx.jdbcUser"), getProperty("wx.jdbcPassword"), getProperty("wx.jdbcDriver"));
		WallFilter wallFilter = new WallFilter();
		wallFilter.setDbType(JdbcConstants.MYSQL);
		druidPlugin.addFilter(wallFilter);
		druidPlugin.addFilter(new StatFilter());
		ps.add(druidPlugin);
		// 添加自动绑定model与表插件
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin, SimpleNameStyles.LOWER_UNDERLINE);
		autoTableBindPlugin.setShowSql(true);
		autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
		// autoTableBindPlugin.setAutoScan(false);
		ps.add(autoTableBindPlugin);
		// 添加资源文件
		ps.add(new ConfigPlugin("message_zh.txt", "config.txt"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// shiro拦截器
		me.add(new ShiroInterceptor());
		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new UrlSkipHandler(".*/static/.*", false));
		me.add(new ContextPathHandler("baseUrl"));
		me.add(new DruidStatViewHandler("/druid"));
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}

	@Override
	public void afterJFinalStart() {
		if (getPropertyToBoolean("wx.proxy.open", false))
			HttpKit.setProxy(getProperty("wx.proxy.http.host"), getProperty("wx.proxy.http.port"), getProperty("wx.proxy.auth.username"), getProperty("wx.proxy.auth.password"));
		super.afterJFinalStart();
		FreeMarkerRender.getConfiguration().setSharedVariable("shiro", new FreemarketShiroTags());
	}
}
