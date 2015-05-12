package com.minws.frame.plugin.shiro;

import cn.dreampie.shiro.freemarker.ShiroTags;

public class FreemarketShiroTags extends ShiroTags{

	private static final long serialVersionUID = -7020254321481370854L;

	public FreemarketShiroTags(){
		super();
		super.remove("principal");
		put("principal", new FreemarketPrincipalTag());
	}
}
