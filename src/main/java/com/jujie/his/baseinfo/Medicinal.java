package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class Medicinal extends BaseBean{

	private Integer medicinalId;//主键ID
	private String medicinalName;//药品名称      
	private String medicinalPycode;//药品拼音缩写   
	private Integer medicinalStatue;//状态
	private Unit unit;//unitId 单位ID
	private Standard standard;//standardId 规格ID 
	private Integer medicinalType;//药品大类
	
	
	public Medicinal(){
		 unit = new Unit();
		 standard = new Standard();
	}
	
	public Integer getMedicinalId() {
		return medicinalId;
	}
	public void setMedicinalId(Integer medicinalId) {
		this.medicinalId = medicinalId;
	}
	public String getMedicinalName() {
		return medicinalName;
	}
	public void setMedicinalName(String medicinalName) {
		this.medicinalName = medicinalName;
	}
	public String getMedicinalPycode() {
		return medicinalPycode;
	}
	public void setMedicinalPycode(String medicinalPycode) {
		this.medicinalPycode = medicinalPycode;
	}
	public Integer getMedicinalStatue() {
		return medicinalStatue;
	}
	public void setMedicinalStatue(Integer medicinalStatue) {
		this.medicinalStatue = medicinalStatue;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Standard getStandard() {
		return standard;
	}
	public void setStandard(Standard standard) {
		this.standard = standard;
	}
	public Integer getMedicinalType() {
		return medicinalType;
	}
	public void setMedicinalType(Integer medicinalType) {
		this.medicinalType = medicinalType;
	}
	
	@Override
	public Medicinal mapRow(ResultSet rs, int rownum) throws SQLException {
		Medicinal medicinal=new Medicinal();
		try{
			medicinal.setMedicinalId(rs.getInt("medicinal_id"));
		}catch(Exception e){}
		try{
			medicinal.setMedicinalName(rs.getString("medicinal_name"));
		}catch(Exception e){}
		try{
			medicinal.setMedicinalPycode(rs.getString("medicinal_pycode"));
		}catch(Exception e){}
		try{
			medicinal.setMedicinalStatue(rs.getInt("medicinal_statue"));
		}catch(Exception e){}
		try{
			medicinal.setUnit(this.unit.mapRow(rs, rownum));
		}catch(Exception e){}
		try{
			medicinal.setStandard(this.standard.mapRow(rs, rownum));
		}catch(Exception e){}
		try{
			medicinal.setMedicinalType(rs.getInt("medicinal_type"));
		}catch(Exception e){}

		return medicinal;
	}
	
	
	
	
	
}
