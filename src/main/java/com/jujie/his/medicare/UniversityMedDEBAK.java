package com.jujie.his.medicare;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;


public class UniversityMedDEBAK extends UniversityMedDE {
	private Date university_update;

	public Date getUniversity_update() {
		return university_update;
	}

	public void setUniversity_update(Date university_update) {
		this.university_update = university_update;
	}
	
	@Override
	public UniversityMedDEBAK mapRow(ResultSet rs, int rownum) throws SQLException {
		UniversityMedDE universityMedD = (UniversityMedDE)super.mapRow(rs, rownum);
		UniversityMedDEBAK universityMedDEBAK = new UniversityMedDEBAK();
		try {
			BeanUtils.copyProperties(universityMedDEBAK,universityMedD);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		try{
			universityMedDEBAK.setUniversity_update(rs.getTimestamp("university_update"));
		}catch(Exception e){}
		return universityMedDEBAK;
	}
}
