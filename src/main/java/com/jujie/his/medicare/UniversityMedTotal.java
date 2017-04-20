package com.jujie.his.medicare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jujie.global.BaseBean;

public class UniversityMedTotal extends BaseBean {
	
	private int personNum;
	private double mztcTotal;
	private String name;
	private Date operDate;
	
	public int getPersonNum() {
		return personNum;
	}
	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}
	public double getMztcTotal() {
		return mztcTotal;
	}
	public void setMztcTotal(double mztcTotal) {
		this.mztcTotal = mztcTotal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getOperDate() {
		return operDate;
	}
	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}
	
	@Override
	public UniversityMedTotal mapRow(ResultSet rs, int rownum) throws SQLException {
		UniversityMedTotal universityMedTotal = new UniversityMedTotal();
		universityMedTotal.setMztcTotal(rs.getDouble("tcbx"));
		universityMedTotal.setPersonNum(rs.getInt("p_num"));
		universityMedTotal.setName("陕职院校医院");
		universityMedTotal.setOperDate(new Date());
		return universityMedTotal;
	}
}
