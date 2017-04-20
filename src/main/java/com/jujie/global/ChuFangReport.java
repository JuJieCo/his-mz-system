package com.jujie.global;

import java.util.Date;
import java.util.List;

public class ChuFangReport {
	
	private String bingLiNo;//病历号  
	private String dept;//科别
	private Date jiuZhenDate;//日期 
	private String sickName;//姓名 
	private String sickSex;//性别 
	private String sickAge;//年龄 
	private String zhenDuan;//诊断
	private String yaoPinDetail;//药品明细
	
	public String getBingLiNo() {
		return bingLiNo;
	}
	public void setBingLiNo(String bingLiNo) {
		this.bingLiNo = bingLiNo;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getJiuZhenDate() {
		return jiuZhenDate;
	}
	public void setJiuZhenDate(Date jiuZhenDate) {
		this.jiuZhenDate = jiuZhenDate;
	}
	public String getSickName() {
		return sickName;
	}
	public void setSickName(String sickName) {
		this.sickName = sickName;
	}
	public String getSickSex() {
		return sickSex;
	}
	public void setSickSex(String sickSex) {
		this.sickSex = sickSex;
	}
	public String getSickAge() {
		return sickAge;
	}
	public void setSickAge(String sickAge) {
		this.sickAge = sickAge;
	}
	public String getZhenDuan() {
		return zhenDuan;
	}
	public void setZhenDuan(String zhenDuan) {
		this.zhenDuan = zhenDuan;
	}
	public String getYaoPinDetail() {
		return yaoPinDetail;
	}
	public void setYaoPinDetail(String yaoPinDetail) {
		this.yaoPinDetail = yaoPinDetail;
	}
}
