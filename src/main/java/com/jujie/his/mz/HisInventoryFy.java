package com.jujie.his.mz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.jujie.global.BaseBean;

public class HisInventoryFy extends BaseBean {
	private Integer order_id;//就诊号
    private Date lyinfo_dotime;//取药时间
	private Integer sys_user_id;//操作员
 
	public Integer getOrder_id() {
		return order_id;
	}
 
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
 
	public Date getLyinfo_dotime() {
		return lyinfo_dotime;
	}
 
	public void setLyinfo_dotime(Date lyinfo_dotime) {
		this.lyinfo_dotime = lyinfo_dotime;
	}

 
	public Integer getSys_user_id() {
		return sys_user_id;
	}
 
	public void setSys_user_id(Integer sys_user_id) {
		this.sys_user_id = sys_user_id;
	}
 
	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		HisInventoryFy hisInventoryFy = new HisInventoryFy();
		hisInventoryFy.setOrder_id(order_id);
		hisInventoryFy.setSys_user_id(sys_user_id);
		hisInventoryFy.setLyinfo_dotime(lyinfo_dotime);
	    return hisInventoryFy;
	}
	
	
}
