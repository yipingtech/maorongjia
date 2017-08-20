// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov Date: 2010-5-13
// 14:17:14
// Home Page : http://members.fortunecity.com/neshkov/dj.html - Check often for
// new version!
// Decompiler options: packimports(3)
// Source File Name: AuthorityIntercept.java
package cc.modules.security;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityIntercept extends AbstractInterceptor {

	private static final long serialVersionUID = -5184793850414042013L;

	public AuthorityIntercept() {
	}

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = ActionContext.getContext().getSession();
		Object loginname = session.get("userID");
		if (loginname == null)
			return "login";
		else
			return invocation.invoke();
	}
}