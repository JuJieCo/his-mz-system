package com.jujie.global;

import java.util.Date;

public class PrintReport {

	private String fpNo;//发票号
	
	private String sickName;//姓名
	
	private String itemGHF;//收费项目---挂号费
	private String itemZCF;//收费项目---诊察费
	
	private String item;//收费项目--单条的
	
	private double tcMoney;//统筹挂账
	
	private String dept; //收费科室
	private double yshMoney;//应收金额
	private double sshMoney;//实收金额
	
	private String userName;//操作员
	private String printTime;//操作时间
	private Date ghTime;//挂号时间
	
	public String getFpNo() {
		return fpNo;
	}
	public void setFpNo(String fpNo) {
		this.fpNo = fpNo;
	}
	public String getSickName() {
		return sickName;
	}
	public void setSickName(String sickName) {
		this.sickName = sickName;
	}
	public String getItemGHF() {
		return itemGHF;
	}
	public void setItemGHF(String itemGHF) {
		this.itemGHF = itemGHF;
	}
	public String getItemZCF() {
		return itemZCF;
	}
	public void setItemZCF(String itemZCF) {
		this.itemZCF = itemZCF;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getTcMoney() {
		return tcMoney;
	}
	public void setTcMoney(double tcMoney) {
		this.tcMoney = tcMoney;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public double getYshMoney() {
		return yshMoney;
	}
	public void setYshMoney(double yshMoney) {
		this.yshMoney = yshMoney;
	}
	public double getSshMoney() {
		return sshMoney;
	}
	public void setSshMoney(double sshMoney) {
		this.sshMoney = sshMoney;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPrintTime() {
		return printTime;
	}
	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}
	public Date getGhTime() {
		return ghTime;
	}
	public void setGhTime(Date ghTime) {
		this.ghTime = ghTime;
	}
}
