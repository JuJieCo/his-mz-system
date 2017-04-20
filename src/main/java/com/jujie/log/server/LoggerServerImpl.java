package com.jujie.log.server;

import java.util.List;

import com.jujie.log.Logger;
import com.jujie.log.dao.LoggerDaoImpl;
import com.jujie.util.page.Page;

public class LoggerServerImpl {
	
	private LoggerDaoImpl loggerDao;

	public void setLoggerDao(LoggerDaoImpl loggerDao) {
		this.loggerDao = loggerDao;
	}

	public int saveLogger(Logger logger) throws Exception {
		return loggerDao.saveLogger(logger);
	}
	
	public void editLogger(Logger logger) throws Exception {
		loggerDao.editLogger(logger);
	}
	
	public void deleteLogger(int uuid) throws Exception {
		loggerDao.deleteLogger(uuid);
	}
	
	
	public Logger getOneLogger(int uuid) throws Exception {
		return loggerDao.getOneLogger(uuid);
	}
	
	
	public List<Logger> getAllLogger(Object[] objs,Page page) throws Exception {
		return loggerDao.getAllLogger(objs, page);
	}
	
}
