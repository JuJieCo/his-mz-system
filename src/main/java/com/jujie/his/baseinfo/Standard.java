package com.jujie.his.baseinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class Standard   extends BaseBean{
	
		private Integer standardId;//主键ID
	    private String standardName;//规格名称
	    private Integer standardStatue;//规格状态
	    
		public Integer getStandardId() {
			return standardId;
		}
		public void setStandardId(Integer standardId) {
			this.standardId = standardId;
		}
		public String getStandardName() {
			return standardName;
		}
		public void setStandardName(String standardName) {
			this.standardName = standardName;
		}
		public Integer getStandardStatue() {
			return standardStatue;
		}
		public void setStandardStatue(Integer standardStatue) {
			this.standardStatue = standardStatue;
		}
		
		@Override
		public Standard mapRow(ResultSet rs, int rownum) throws SQLException {
			Standard standard =new Standard();
			try{
				standard.setStandardId(rs.getInt("standard_id"));
			}catch(Exception e){}
			try{
				standard.setStandardName(rs.getString("standard_name"));
			}catch(Exception e){}
			try{
				standard.setStandardStatue(rs.getInt("standard_statue"));
			}catch(Exception e){}

			return standard;
		}
	    
	    

}
