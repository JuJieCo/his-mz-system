package com.jujie.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.login.Login;
import com.jujie.login.Role;
import com.jujie.util.DataUtils;

public class LoginDaoImpl extends BaseJdbcDao {
	public int saveLogin(Login login) throws Exception {
		final String sql = "insert into login(login_name,login_pwd,sys_user_id) values(?,?,?)";
		final Object[] objs = {login.getLoginName(),login.getLoginPwd(),login.getUser().getSysUserId()};
	    KeyHolder keyHolder = new GeneratedKeyHolder();     
		try{
			this.getJdbcTemplate().update(new PreparedStatementCreator(){         
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);                                                 
					ps.setString(++i, DataUtils.getStringK(objs[n++])); 
					ps.setString(++i, DataUtils.getStringK(objs[n++])); 
					ps.setInt(++i, DataUtils.getInt(objs[n++])); 
					return ps;    
				}             
			}, keyHolder);    
		}catch(Exception e){
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();
	}
	
	public void editLogin(Login login) throws Exception {
		String sql = "update login set login_name=?,login_pwd=?,sys_user_id=? where uuid=?";
		Object[] objs = {login.getLoginName(),login.getLoginPwd(),login.getUser().getSysUserId(),login.getUuid()};
		this.getJdbcTemplate().update(sql, objs);
	}
	
	public void deleteLogin(int uuid){
		String sql = "delete from login where uuid="+uuid;
		this.getJdbcTemplate().update(sql);
	}
	
	public void deleteLoginByUserID(int UserUuid){
		String[] sqls = new String[2];
		sqls[0] = "delete from login_role where login_uuid in(select uuid from login where sys_user_id="+UserUuid+")";
		sqls[1] = "delete from login where sys_user_id="+UserUuid;
		this.getJdbcTemplate().batchUpdate(sqls);
	}
	
	public void authorizeLogin(Login login){
		String[] sqls = new String[login.getRoleList().size()+1];
		int i = 0;
		sqls[i++] = "delete from login_role where login_uuid="+login.getUuid();
		for (Role role : login.getRoleList()) {
			sqls[i++] = "insert into login_role(login_uuid,role_uuid) values("+login.getUuid()+","+role.getUuid()+")";
		}
		this.getJdbcTemplate().batchUpdate(sqls);
	}
	

	public boolean checkName(String name) throws Exception {
		String sql = "select count(*) from login where login_name='"+name+"'";
		int count = this.getJdbcTemplate().queryForInt(sql);
		if(count<1){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Login getOneLogin(int uuid) throws Exception {
		String sql = "select *,uuid login_uuid from login where uuid="+uuid;
		List<Login> list = this.getJdbcTemplate().query(sql,new Login());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Login getOneLoginByUserID(int userUuid) throws Exception {
		String sql = "select *,uuid login_uuid from login where sys_user_id="+userUuid;
		List<Login> list = this.getJdbcTemplate().query(sql,new Login());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Login getOneLogin(String name,String pwd) throws Exception {
		String sql = "select *,uuid login_uuid from login where login_name=? and login_pwd=?";
		Object[] objs = {name,pwd};
		List<Login> list = this.getJdbcTemplate().query(sql,objs,new Login());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Login> getAllLogins(Object[] objs) throws Exception {
		String sql = "select l.uuid login_uuid,l.*,eu.* from Login l left join sys_user eu on l.sys_user_id=eu.sys_user_id where 1=1 ";
		
		List<Object> list = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(objs[1]!=null&&!"".equals(DataUtils.getStringK(objs[1]))){
				sql += " and eu.sys_user_dept like '%'||?||'%' ";
				list.add(objs[1]);
			}
		}
		return this.getJdbcTemplate().query(sql,list.toArray(), new Login());
	}
	
	public boolean editPwd(int loginUuid,String pwd) {
		String sql = "update login set login_pwd='"+pwd+"' where uuid="+loginUuid;
		try{
			this.getJdbcTemplate().update(sql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
}
