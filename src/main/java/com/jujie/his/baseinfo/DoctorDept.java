package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class DoctorDept extends BaseBean {

	private Dept dept;
	private Doctor doctor;
	
	
	public DoctorDept(){
		dept = new  Dept();
		doctor = new Doctor();
	}
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}



	public Doctor getDoctor() {
		return doctor;
	}





	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}





	@Override
	public DoctorDept mapRow(ResultSet rs, int rownum) throws SQLException {
		return null;
	}

}
