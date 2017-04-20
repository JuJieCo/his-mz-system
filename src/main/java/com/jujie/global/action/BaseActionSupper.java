package com.jujie.global.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseActionSupper extends ActionSupport implements 
	ApplicationContextAware,ServletContextAware,ServletRequestAware,ServletResponseAware,SessionAware{

	protected ApplicationContext context;
	protected ServletContext servletContext;
	protected ActionContext ctx; 
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object>  session;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
	
	public Object getService(String beanName){
		return this.context.getBean(beanName);
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ActionContext getCxt(){
		return ActionContext.getContext();
	}
}
