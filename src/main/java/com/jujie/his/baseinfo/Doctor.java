package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class Doctor extends BaseBean {

	private Integer doctorId;//主键ID
	private String doctorName;//医生姓名
	private Integer doctorSex;//医生性别
	private String doctorIntrodcut;//医生简介
	private Integer doctorStatue;//医生状态
	
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Integer getDoctorSex() {
		return doctorSex;
	}
	public void setDoctorSex(Integer doctorSex) {
		this.doctorSex = doctorSex;
	}
	public String getDoctorIntrodcut() {
		return doctorIntrodcut;
	}
	public void setDoctorIntrodcut(String doctorIntrodcut) {
		this.doctorIntrodcut = doctorIntrodcut;
	}
	public Integer getDoctorStatue() {
		return doctorStatue;
	}
	public void setDoctorStatue(Integer doctorStatue) {
		this.doctorStatue = doctorStatue;
	}
	@Override
	public Doctor mapRow(ResultSet rs, int rownum) throws SQLException {
		Doctor doctor=new Doctor();
		try{
			doctor.setDoctorId(rs.getInt("doctor_id"));
		}catch(Exception e){}
		try{
			doctor.setDoctorName(rs.getString("doctor_name"));
		}catch(Exception e){}
		try{
			doctor.setDoctorSex(rs.getInt("doctor_sex"));
		}catch(Exception e){}
		try{
			doctor.setDoctorIntrodcut(rs.getString("doctor_introdcut"));
		}catch(Exception e){}
		try{
			doctor.setDoctorStatue(rs.getInt("doctor_statue"));
		}catch(Exception e){}
		
		return doctor;
	}
	
	
	
}
