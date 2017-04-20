package com.jujie.his.baseinfo.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.baseinfo.Dept;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

@SuppressWarnings("unchecked")
public class DeptDaoImpl  extends BaseJdbcDao{

	// 取his_mz_dept表 返回科室List
	
	
	@SuppressWarnings("static-access")
	public List<Dept> queryDeptList(Page page)throws Exception{
		final String sql = "select * from his_mz_dept order by dept_id desc";
		List<Object> obj = new ArrayList<Object>();
		return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new Dept());


	}
	
	
	//新增科室
	public Integer addDept(Dept dept) throws Exception {
		
		final String sql = "insert into his_mz_dept (dept_name,dept_statue) values(?,?)";
		
		final Object[] objs ={dept.getDeptName(),dept.getDeptStatue()};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		try {
			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(++i, DataUtils.getStringK(objs[n++]));
					ps.setInt(++i,    DataUtils.getInt(objs[n++]));

					return ps;
				}
			}, keyHolder);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();
		}

	
	//查询byID
	
	public Dept queryDept(String deptId) throws Exception{

		final String sql ="select * from  his_mz_dept where dept_id = "+deptId;
		List<Dept> list = this.getJdbcTemplate().query(sql, new Dept());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	//修改科室
	public void updateDept(Dept dept ,String statue) throws Exception{

		if(null!=statue&&!"".equals(statue)){
			if(statue.equals("isStatue")){
				String sql = "update his_mz_dept set dept_statue=? where dept_id=?";
				Object[] objs = {dept.getDeptStatue(),dept.getDeptId()};
				this.getJdbcTemplate().update(sql,objs);
			}
			if(statue.equals("noStatue")){
				String sql = "update his_mz_dept set dept_name=? where dept_id=?";
				Object[] objs = {dept.getDeptName(),dept.getDeptId()};
				this.getJdbcTemplate().update(sql,objs);
			}
			
		}
	}
	
	//删除科室
	public void deleteDept(String[] deptId) throws Exception{
		String[] sql = new String[deptId.length];
		 sql[0] = "delete from his_mz_dept where dept_id="+deptId[0];
		this.getJdbcTemplate().batchUpdate(sql);
	}	
}
