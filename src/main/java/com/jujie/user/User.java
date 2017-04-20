package com.jujie.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;
import com.jujie.his.baseinfo.Doctor;
import com.jujie.his.baseinfo.House;

public class User extends BaseBean{

	private Integer sysUserId;
	//private String  sysUserDept;
	private String  sysUserName;
	//private Integer sysUserLevel;
	private String  sysUserCode;
	private Integer sysUserStatus;
	//private Integer sysUserIsManger;
	
	private Doctor doctor;
	private House house;
	
	public User(){
		this.doctor = new Doctor();
		this.house = new House();
	}

//	public Integer getSysUserIsManger() {
//		return sysUserIsManger;
//	}
//
//
//
//	public void setSysUserIsManger(Integer sysUserIsManger) {
//		this.sysUserIsManger = sysUserIsManger;
//	}



	public Doctor getDoctor() {
		return doctor;
	}



	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}



	public House getHouse() {
		return house;
	}



	public void setHouse(House house) {
		this.house = house;
	}



	public Integer getSysUserId() {
		return sysUserId;
	}



	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}



//	public String getSysUserDept() {
//		return sysUserDept;
//	}
//
//
//
//	public void setSysUserDept(String sysUserDept) {
//		this.sysUserDept = sysUserDept;
//	}



	public String getSysUserName() {
		return sysUserName;
	}



	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}



//	public Integer getSysUserLevel() {
//		return sysUserLevel;
//	}
//
//
//
//	public void setSysUserLevel(Integer sysUserLevel) {
//		this.sysUserLevel = sysUserLevel;
//	}

	public String getSysUserCode() {
		return sysUserCode;
	}



	public void setSysUserCode(String sysUserCode) {
		this.sysUserCode = sysUserCode;
	}



	public Integer getSysUserStatus() {
		return sysUserStatus;
	}



	public void setSysUserStatus(Integer sysUserStatus) {
		this.sysUserStatus = sysUserStatus;
	}



	@Override
	public User mapRow(ResultSet rs, int rownum) throws SQLException {
		User user = new User();
		//user.setSysUserDept(rs.getString("sys_user_dept"));
		//user.setSysUserLevel(rs.getInt("sys_user_level"));
		user.setSysUserName(rs.getString("sys_user_name"));
		//user.setSysUserAgence(new Agence().mapRow(rs, rownum));
		user.setSysUserCode(rs.getString("sys_user_code"));
		user.setSysUserStatus(rs.getInt("sys_user_status"));
		user.setSysUserId(rs.getInt("sys_user_id"));
		//user.setSysUserIsManger(rs.getInt("sys_user_isManger"));
		user.setDoctor(new Doctor().mapRow(rs, rownum));
		user.setHouse(new House().mapRow(rs, rownum));
		return user;
	}
	
	
}
