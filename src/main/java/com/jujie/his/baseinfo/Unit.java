package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class Unit extends BaseBean{
	
	  private Integer unitId;//主键ID
	  private String unitName;//单位名称
	  private Integer unitStatue;//单位状态
	  
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getUnitStatue() {
		return unitStatue;
	}
	public void setUnitStatue(Integer unitStatue) {
		this.unitStatue = unitStatue;
	}
	
	
	@Override
	public Unit mapRow(ResultSet rs, int rownum) throws SQLException {
		Unit unit=new Unit();
		try{
			unit.setUnitId(rs.getInt("unit_id"));
		}catch(Exception e){}
		try{
			unit.setUnitName(rs.getString("unit_name"));
		}catch(Exception e){}
		try{
			unit.setUnitStatue(rs.getInt("unit_statue"));
		}catch(Exception e){}
		
		return unit;
	}
	  
	  

}
