package com.jujie.log.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.log.Logger;
import com.jujie.util.DataUtils;
import com.jujie.util.DateUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class LoggerDaoImpl extends BaseJdbcDao {
	
	public int saveLogger(Logger logger) throws Exception {
		final String sql = "insert into integral_log_info(log_cp_module,log_cp_method,log_content,log_oper,log_type,log_object,log_ip,log_createtime,log_remark) values(?,?,?,?,?,?,?,?,?)";
		final Object[] objs = {logger.getLogCpModule(),logger.getLogCpMethod(),logger.getLogContent(),logger.getLogOper(),logger.getLogType(),logger.getLogObject(),logger.getLogIP(),logger.getLogCreatetime(),logger.getLogRemark()};
	    KeyHolder keyHolder = new GeneratedKeyHolder();     
		try{
			this.getJdbcTemplate().update(new PreparedStatementCreator(){         
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);                                                 
					ps.setString(++i, DataUtils.getStringK(objs[n++]));    
					ps.setString(++i, DataUtils.getStringK(objs[n++]));    
					ps.setString(++i, DataUtils.getStringK(objs[n++]));    
					if(DataUtils.getInt(objs[n])==0){
						ps.setNull(++i, Type.INT);  
						n++;
					}else{
						ps.setInt(++i, DataUtils.getInt(objs[n++]));  
					}
					if(DataUtils.getInt(objs[n])==0){
						ps.setNull(++i, Type.INT);  
						n++;
					}else{
						ps.setInt(++i, DataUtils.getInt(objs[n++]));  
					}
					if(DataUtils.getInt(objs[n])==0){
						ps.setNull(++i, Type.INT);  
						n++;
					}else{
						ps.setInt(++i, DataUtils.getInt(objs[n++]));  
					}
					ps.setString(++i, DataUtils.getStringK(objs[n++]));    
					ps.setTimestamp(++i, DateUtils.getTimestamp(objs[n++]));    
					ps.setString(++i, DataUtils.getStringK(objs[n++]));    
					return ps;    
				}             
			}, keyHolder);    
		}catch(Exception e){
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();
	}
	
	public void editLogger(Logger logger) throws Exception {
		String sql = "update integral_log_info set log_cp_module=?,log_cp_method=?,log_content=?,log_oper=?,log_type=?,log_object=?,log_ip=?,log_createtime=?,log_remark=? where log_id=?";
		Object[] objs = {logger.getLogCpModule(),logger.getLogCpMethod(),logger.getLogContent(),logger.getLogType(),logger.getLogObject(),logger.getLogIP(),logger.getLogCreatetime(),logger.getLogRemark(),logger.getLogId()};
		this.getJdbcTemplate().update(sql, objs);
	}
	
	public void deleteLogger(int uuid) throws Exception {
		String sql = "delete from integral_log_info where log_id="+uuid;
		this.getJdbcTemplate().update(sql);
	}
	
	
	@SuppressWarnings("unchecked")
	public Logger getOneLogger(int uuid) throws Exception {
		String sql = "select * from integral_log_info where log_id="+uuid;
		List<Logger> list = this.getJdbcTemplate().query(sql, new Logger());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Logger> getAllLogger(Object[] objs,Page page) throws Exception {
		String sql = "select * from integral_log_info where 1=1 ";
		
		List<Object> list = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&DataUtils.getInt(objs[0])!=0){
				sql += " and log_oper=? ";
				list.add(DataUtils.getInt(objs[0]));
			}
			if(objs[1]!=null&&DataUtils.getInt(objs[1])!=0){
				sql += " and log_type=? ";
				list.add(DataUtils.getInt(objs[1]));
			}
			if(objs[2]!=null&&!"".equals(DataUtils.getStringK(objs[2]))){
				sql += " and log_cp_module=? ";
				list.add(DataUtils.getStringK(objs[2]));
			}
			if(objs[3]!=null&&!"".equals(DataUtils.getStringK(objs[3]))){
				sql += " and log_content like '%'+?+'%' ";
				list.add(DataUtils.getStringK(objs[3]));
			}
			if(objs[4]!=null&&!"".equals(DataUtils.getStringK(objs[4]))){
				sql += " and convert(varchar(10),log_createtime,120) >= ? ";
				list.add(DataUtils.getStringK(objs[4]));
			}
			if(objs[5]!=null&&!"".equals(DataUtils.getStringK(objs[5]))){
				sql += " and convert(varchar(10),log_createtime,120) <= ? ";
				list.add(DataUtils.getStringK(objs[5]));
			}
		}
		sql += " order by log_id desc ";
		return this.getJdbcTemplate().query(PageUtils.fyPage(sql, list.toArray(), page, this.getJdbcTemplate(), Page.DATABASE_TYPE_MSSQL),list.toArray(), new Logger());
	}
	
}
