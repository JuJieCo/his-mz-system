package com.jujie.global.web;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext ctx = invocation.getInvocationContext();
		Map<String,Object> session = ctx.getSession();
		if(session.get("sessionUser")!=null&&session.get("sessionLogin")!=null){
			return invocation.invoke();
		}
		ctx.put("mesg", "登录时间以超时，请重新登录！");
		return Action.LOGIN;
	}

}
