package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class Feetype extends BaseBean{
	
	private Integer feetypeId;//主键ID
	private String feeName;//公司名称
	private String feeContent;//费别简介
	private Double feeMoney;//费价格
	private String feeGroup;//费科室
	private String dw;//单位
	private String feeRemark;//备注 
	
	public Integer getFeetypeId() {
		return feetypeId;
	}


	public void setFeetypeId(Integer feetypeId) {
		this.feetypeId = feetypeId;
	}


	public String getFeeName() {
		return feeName;
	}


	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}


	public String getFeeContent() {
		return feeContent;
	}


	public void setFeeContent(String feeContent) {
		this.feeContent = feeContent;
	}


	public Double getFeeMoney() {
		return feeMoney;
	}


	public void setFeeMoney(Double feeMoney) {
		this.feeMoney = feeMoney;
	}


	public String getFeeGroup() {
		return feeGroup;
	}


	public void setFeeGroup(String feeGroup) {
		this.feeGroup = feeGroup;
	}


	public String getDw() {
		return dw;
	}


	public void setDw(String dw) {
		this.dw = dw;
	}


	public String getFeeRemark() {
		return feeRemark;
	}


	public void setFeeRemark(String feeRemark) {
		this.feeRemark = feeRemark;
	}
 
	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		Feetype feetype = new Feetype();
		feetype.setFeetypeId(rs.getInt("feetypeId"));
		feetype.setFeeName(rs.getString("feeName"));
		feetype.setFeeContent(rs.getString("feeContent"));
		feetype.setFeeMoney(rs.getDouble("feeMoney"));
		feetype.setFeeRemark(rs.getString("feeRemark"));
		feetype.setDw(rs.getString("dws"));
		feetype.setFeeGroup(rs.getString("feeGroup"));
		return feetype;
	}
}
