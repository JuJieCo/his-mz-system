package com.jujie.log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jujie.global.BaseBean;
import com.jujie.util.DataUtils;

public class Logger extends BaseBean {

	private Integer logId;
	private String logCpModule;
	private String logCpMethod;
	private String logContent;
	private Integer logOper;
	private Integer logType;
	private Integer logObject;
	private String logIP;
	private Date logCreatetime;
	private String logRemark;
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getLogCpModule() {
		return logCpModule;
	}
	public void setLogCpModule(String logCpModule) {
		this.logCpModule = logCpModule;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public Integer getLogOper() {
		return logOper;
	}
	public void setLogOper(Integer logOper) {
		this.logOper = logOper;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	public Integer getLogObject() {
		return logObject;
	}
	public void setLogObject(Integer logObject) {
		this.logObject = logObject;
	}
	public Date getLogCreatetime() {
		return logCreatetime;
	}
	public void setLogCreatetime(Date logCreatetime) {
		this.logCreatetime = logCreatetime;
	}
	public String getLogIP() {
		return logIP;
	}
	public void setLogIP(String logIP) {
		this.logIP = logIP;
	}
	public String getLogRemark() {
		return logRemark;
	}
	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}
	public String getLogCpMethod() {
		return logCpMethod;
	}
	public void setLogCpMethod(String logCpMethod) {
		this.logCpMethod = logCpMethod;
	}
	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		Logger logger = new Logger();
		logger.setLogId(DataUtils.getInt(rs.getInt("log_id")));
		logger.setLogCpModule(DataUtils.getStringK(rs.getString("log_cp_module")));
		logger.setLogContent(DataUtils.getStringK(rs.getString("log_content")));
		logger.setLogOper(DataUtils.getInt(rs.getInt("log_oper")));
		logger.setLogType(DataUtils.getInt(rs.getInt("log_type")));
		logger.setLogObject(DataUtils.getInt(rs.getInt("log_object")));
		logger.setLogCreatetime(rs.getTimestamp("log_createtime"));
		logger.setLogIP(DataUtils.getStringK(rs.getString("log_ip")));
		logger.setLogRemark(DataUtils.getStringK(rs.getString("log_remark")));
		logger.setLogCpMethod(DataUtils.getStringK(rs.getString("log_cp_method")));
		return logger;
	}
	
	
	
}
