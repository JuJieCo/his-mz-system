package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class Company extends BaseBean{
	
	private Integer companyId;//主键ID
	private String companyName;//公司名称
	private String companyAlias;//公司别名
	private Integer companyStatue;//公司状态
	
	
	
	public String getCompanyAlias() {
		return companyAlias;
	}
	public void setCompanyAlias(String companyAlias) {
		this.companyAlias = companyAlias;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getCompanyStatue() {
		return companyStatue;
	}
	public void setCompanyStatue(Integer companyStatue) {
		this.companyStatue = companyStatue;
	}
	
	
	@Override
	public Company mapRow(ResultSet rs, int rownum) throws SQLException {
		Company company=new Company();
		try{
			company.setCompanyId(rs.getInt("company_id"));
		}catch(Exception e){}
		try{
			company.setCompanyName(rs.getString("company_name"));
		}catch(Exception e){}
		try{
			company.setCompanyAlias(rs.getString("company_alias"));
		}catch(Exception e){}
		try{
			company.setCompanyStatue(rs.getInt("company_statue"));
		}catch(Exception e){}

		return company;
	}
	
	

}
