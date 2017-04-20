package com.jujie.his.guahao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class Sick extends BaseBean {

	private Integer sickId; // 主键ID
	private String sickName;// 病人姓名
	private Integer sickSex;// 病人性别
	private Integer sickAge;// 病人年龄
	private Integer sickCosetype;// 费用类型
	private Integer sickTreattype;// 治疗访次
	private String sickAddress;// 病人地址
	private String sickCasehistory;// 病人病历号
	private String sickYbCode;//医保号
	private String sickCode;//身份证号

	public Integer getSickId() {
		return sickId;
	}

	public void setSickId(Integer sickId) {
		this.sickId = sickId;
	}

	public String getSickName() {
		return sickName;
	}

	public void setSickName(String sickName) {
		this.sickName = sickName;
	}

	public Integer getSickSex() {
		return sickSex;
	}

	public void setSickSex(Integer sickSex) {
		this.sickSex = sickSex;
	}

	public Integer getSickAge() {
		return sickAge;
	}

	public void setSickAge(Integer sickAge) {
		this.sickAge = sickAge;
	}

	public Integer getSickCosetype() {
		return sickCosetype;
	}

	public void setSickCosetype(Integer sickCosetype) {
		this.sickCosetype = sickCosetype;
	}

	public Integer getSickTreattype() {
		return sickTreattype;
	}

	public void setSickTreattype(Integer sickTreattype) {
		this.sickTreattype = sickTreattype;
	}

	public String getSickAddress() {
		return sickAddress;
	}

	public void setSickAddress(String sickAddress) {
		this.sickAddress = sickAddress;
	}

	public String getSickCasehistory() {
		return sickCasehistory;
	}

	public void setSickCasehistory(String sickCasehistory) {
		this.sickCasehistory = sickCasehistory;
	}

	public String getSickYbCode() {
		return sickYbCode;
	}

	public void setSickYbCode(String sickYbCode) {
		this.sickYbCode = sickYbCode;
	}

	public String getSickCode() {
		return sickCode;
	}

	public void setSickCode(String sickCode) {
		this.sickCode = sickCode;
	}

	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		Sick sick = new Sick();
		sick.setSickId(rs.getInt("sick_id"));
		sick.setSickName(rs.getString("sick_name"));
		sick.setSickSex(rs.getInt("sick_sex"));
		sick.setSickAge(rs.getInt("sick_age"));
		sick.setSickCosetype(rs.getInt("sick_cosetype"));
		sick.setSickTreattype(rs.getInt("sick_treattype"));
		sick.setSickAddress(rs.getString("sick_address"));
		sick.setSickCasehistory(rs.getString("sick_casehistory"));
	    return sick;
	 
	}

}
