package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class Dept extends BaseBean{
	
	private Integer deptId;//主键ID
	private String deptName;//科室名称
	private Integer deptStatue;//状态
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getDeptStatue() {
		return deptStatue;
	}
	public void setDeptStatue(Integer deptStatue) {
		this.deptStatue = deptStatue;
	}
	
	
	@Override
	public Dept mapRow(ResultSet rs, int rownum) throws SQLException {
		Dept dept=new Dept();
		try{
			dept.setDeptId(rs.getInt("dept_id"));
		}catch(Exception e){}
		try{
			dept.setDeptName(rs.getString("dept_name"));
		}catch(Exception e){}
		try{
			dept.setDeptStatue(rs.getInt("dept_statue"));
		}catch(Exception e){}

		return dept;
	}
	
	

}
