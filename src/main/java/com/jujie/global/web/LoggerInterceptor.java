package com.jujie.global.web;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jujie.log.Logger;
import com.jujie.log.server.LoggerServerImpl;
import com.jujie.user.User;
import com.jujie.util.DataUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class LoggerInterceptor extends AbstractInterceptor {

	@SuppressWarnings("unused")
	private ActionContext ctx ;
	private Map<String, Object> session ;
	@SuppressWarnings("unused")
	private Map<String, Object> parameters ;
	private HttpServletRequest request ;
	private Object action ;
	private String method ; 
	private String ip ;
	
	@SuppressWarnings("unused")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String result = "";

		ctx = invocation.getInvocationContext();
		session = invocation.getInvocationContext().getSession();  
		parameters = invocation.getInvocationContext().getParameters(); 
		request = ServletActionContext.getRequest(); 
        action = invocation.getAction();   
        method = invocation.getProxy().getMethod(); 
        ip = request.getRemoteAddr(); 
        try{   
            if(StringUtils.isBlank(method)) method = "method";   
            
            if(!"logout".equals(method)){ 
            	result = invocation.invoke();
            }
   
            Properties prop = new Properties();
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("action-log.properties");
            prop.load(in);
            
            
            String[] modules = prop.get("action.root.module").toString().split(",");
            for (String module : modules) {
            	if(prop.get("action.module."+module)==null){
            		continue;
            	}
            	String[] actions = prop.get("action.module."+module).toString().split(",");
            	for (String name : actions) {
            		boolean flag = false;
                	try{
                		flag = Class.forName(name).isInstance(action);
                	}catch(Exception e){
                		//e.printStackTrace();
                	}
                	if(flag){
                		name = name.substring(name.lastIndexOf(".")+1);
                		if(prop.get("action."+name+".method")==null){
                    		continue;
                    	}
                		String[] methods = prop.get("action."+name+".method").toString().split(",");
                		for (String m : methods) {
                			if(method.equals(m)){
                    			String log = prop.getProperty("action."+name+"."+method+".log").toString();
                    			Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*\\}|\\[[^\\[\\]]*\\])");
                    			Matcher matcher = pattern.matcher(log);
                    			while(matcher.find()){
                    				String key = matcher.group().substring(1,matcher.group().length()-1);
//                    				System.out.println("ct:"+invocation.getInvocationContext().get(key));
//                    				System.out.println("sf:"+invocation.getStack().findValue(key));
//                    				System.out.println("pa:"+parameters.get(key));
//                    				System.out.println("se:"+session.get(key));
                    				String value = getSorRValue(matcher.group());
                    				log = matcher.replaceFirst(value);	
                    				matcher = pattern.matcher(log);

                    			}
 //                   			System.out.println(log);
                    			String type = "";
                    			try{
                    				type = prop.getProperty("action."+name+"."+method+".log.type").toString();
                    				type = getSorRValue(type);
                    			}catch(Exception e){
                    				
                    			}
                    			String object = "";
                    			try{
                    				object = prop.getProperty("action."+name+"."+method+".log.object").toString();
                    				object = getSorRValue(object);
                    			}catch(Exception e){
                    				
                    			}
                    			saveLogger(module,name+":"+m,log,type,object);
                    		}
    					}
                    }
				}
			}   
        }catch(Exception e){   
            e.printStackTrace();   
        }   
        if(!"".equals(result)){
        	return result;
        }
		return invocation.invoke();
	}

	public String getSorRValue(String key)  throws Exception {
		if(key.indexOf("[")!=-1){
			key = key.substring(1, key.length()-1);
			return getSessionAttributeValue(key);
		}else if(key.indexOf("{")!=-1){
			key = key.substring(1, key.length()-1);
			return getRequestAttributeValue(key);
		}
		return key;
	}
	
	public String getSessionAttributeValue(String key) throws Exception {
		String[] keys = key.split("\\.");
		Object sobj = session.get(keys[0]);
		if(keys.length>1){
			for(int i = 1 ; i < keys.length ; i++){
				Method method = sobj.getClass().getMethod(getMethodName(keys[i]),null);
				sobj =  method.invoke(sobj, null);
			}
			
		}
		return DataUtils.getStringK(sobj);
	}
	
	public String getRequestAttributeValue(String key)  throws Exception {
		String[] strs = request.getParameterValues(key);
		String value = "";
		if(strs==null||strs.length==0){
			return value;
		}else{
			value = Arrays.toString(strs);
			value = value.substring(1,value.length()-1);
		}
		return value;
	}
	
	public String getMethodName(String methodName){
		return "get"+methodName.substring(0,1).toUpperCase()+ methodName.substring(1);
	}
	
	public void saveLogger(String module,String method,String content,String type,String object){
		ApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		LoggerServerImpl loggerServerImpl = (LoggerServerImpl)appctx.getBean("loggerServer"); 
		Logger logger = new Logger();
		logger.setLogContent(content);
		logger.setLogCpMethod(method);
		logger.setLogCpModule(module);
		logger.setLogCreatetime(new Date());
		logger.setLogIP(ip);
		logger.setLogObject(DataUtils.getInt(object));
		User sysUser = (User)request.getSession().getAttribute("sessionUser");
		logger.setLogOper(sysUser.getSysUserId());
		logger.setLogType(DataUtils.getInt(type));
		try {
			loggerServerImpl.saveLogger(logger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
