package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class House extends BaseBean{
	
	private Integer houseId;//主键
	private String houseName;//药房名称
	private Integer houseStatue;//药房状态
	
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public Integer getHouseStatue() {
		return houseStatue;
	}
	public void setHouseStatue(Integer houseStatue) {
		this.houseStatue = houseStatue;
	}
	
	
	@Override
	public House mapRow(ResultSet rs, int rownum) throws SQLException {
		House house=new House();
		try{
			house.setHouseId(rs.getInt("house_id"));
		}catch(Exception e){}
		try{
			house.setHouseName(rs.getString("house_name"));
		}catch(Exception e){}
		try{
			house.setHouseStatue(rs.getInt("house_statue"));
		}catch(Exception e){}

		return house;
	}
	
	

}
