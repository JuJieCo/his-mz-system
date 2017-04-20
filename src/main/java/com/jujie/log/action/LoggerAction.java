package com.jujie.log.action;

import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.log.Logger;
import com.jujie.log.server.LoggerServerImpl;
import com.jujie.util.page.Page;

@SuppressWarnings("serial")
public class LoggerAction extends BaseActionSupper {

	private Logger logger ;
	private List<Logger> loggerList ;
	private Page page;

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	public List<Logger> getLoggerList() {
		return loggerList;
	}

	public void setLoggerList(List<Logger> loggerList) {
		this.loggerList = loggerList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String execute() throws Exception {
		LoggerServerImpl server = (LoggerServerImpl)this.getService("loggerServer");
		String st = request.getParameter("st");
		String et = request.getParameter("et");
		if(page==null){
			page = new Page(1);
		}
		if(logger==null){
			loggerList = server.getAllLogger(new Object[]{null,null,null,null,null,null},page);
		}else{
			loggerList = server.getAllLogger(new Object[]{logger.getLogOper(),logger.getLogType(),logger.getLogCpModule(),logger.getLogContent(),st,et}, page);
		}
		this.getCxt().put("st", st);
		this.getCxt().put("et", et);
		return "log";
	}

}
