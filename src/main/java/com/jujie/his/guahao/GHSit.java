package com.jujie.his.guahao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jujie.global.BaseBean;
import com.jujie.his.baseinfo.Dept;
import com.jujie.his.baseinfo.Doctor;

public class GHSit  extends BaseBean{
	
	
	
	private Integer ghsitId; //主键ID
	private Integer ghsitType1; //号别
	private Integer ghsitType2; //号类
	private Date ghsitDoTime; //执行日期
	private Dept dept ;//int dept_id;//科室ID
	private Doctor doctor;//int doctor_idi; //医生ID
	private Integer ghsitAmNum;//上午号限
	private Integer ghsitPmNum;//下午号限
	
    public GHSit(){
    	dept =new Dept();
    	doctor=new Doctor();
}
	
	public Integer getGhsitId() {
		return ghsitId;
	}
	public void setGhsitId(Integer ghsitId) {
		this.ghsitId = ghsitId;
	}
	public Integer getGhsitType1() {
		return ghsitType1;
	}
	public void setGhsitType1(Integer ghsitType1) {
		this.ghsitType1 = ghsitType1;
	}
	public Integer getGhsitType2() {
		return ghsitType2;
	}
	public void setGhsitType2(Integer ghsitType2) {
		this.ghsitType2 = ghsitType2;
	}
	public Date getGhsitDoTime() {
		return ghsitDoTime;
	}
	public void setGhsitDoTime(Date ghsitDoTime) {
		this.ghsitDoTime = ghsitDoTime;
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
	public Integer getGhsitAmNum() {
		return ghsitAmNum;
	}
	public void setGhsitAmNum(Integer ghsitAmNum) {
		this.ghsitAmNum = ghsitAmNum;
	}
	public Integer getGhsitPmNum() {
		return ghsitPmNum;
	}
	public void setGhsitPmNum(Integer ghsitPmNum) {
		this.ghsitPmNum = ghsitPmNum;
	}


	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		GHSit ghsit = new GHSit();
		ghsit.setGhsitId(rs.getInt("ghsit_id"));
		ghsit.setGhsitType1(rs.getInt("ghsit_type1"));
		ghsit.setGhsitType2(rs.getInt("ghsit_type2"));
		ghsit.setGhsitDoTime(rs.getDate("ghsit_dotime"));
		
		ghsit.getDept().setDeptId(rs.getInt("dept_id"));	
//		dept.setDeptId(rs.getInt("dept_id"));
//		ghsit.setDept(dept);
		
		ghsit.getDoctor().setDoctorId(rs.getInt("doctor_id"));
//		doctor.setDoctorId(rs.getInt("doctor_id"));
//		ghsit.setDoctor(doctor);

		ghsit.setGhsitAmNum(rs.getInt("ghsit_amnum"));
		ghsit.setGhsitPmNum(rs.getInt("ghsit_pmnum"));
		return ghsit;
	}
}
