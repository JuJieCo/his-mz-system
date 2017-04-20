package com.jujie.his.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.jujie.global.BaseBean;
import com.jujie.user.User;

/**
 * 调价单
 * 
 * @author
 * 
 */
public class HisChangepriceBill extends BaseBean {
	private int changepriceBill_id;
	private String changepriceBill_cause;
	private Date changepriceBill_createtime;
	private Date changepriceBill_opertime;
	private int changepriceBill_statue;
	private User user ;
    private List<HisChangepriceDetail> detailList;
    private String serNID;
    public String getSerNID() {
		if(changepriceBill_id<10){
			serNID = "000000"+changepriceBill_id;
		}
		if(changepriceBill_id<100&&changepriceBill_id>=10){
			serNID = "00000"+changepriceBill_id;
		}
		if(changepriceBill_id<1000&&changepriceBill_id>=100){
			serNID = "0000"+changepriceBill_id;
		}
		if(changepriceBill_id<10000&&changepriceBill_id>=1000){
			serNID = "000"+changepriceBill_id;
		}
		if(changepriceBill_id<100000&&changepriceBill_id>=10000){
			serNID = "00"+changepriceBill_id;
		}
		if(changepriceBill_id<1000000&&changepriceBill_id>=100000){
			serNID = "0"+changepriceBill_id;
		}
		return serNID;
	}
 	public List<HisChangepriceDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<HisChangepriceDetail> detailList) {
		this.detailList = detailList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getChangepriceBill_id() {
		return changepriceBill_id;
	}

	public void setChangepriceBill_id(int changepriceBill_id) {
		this.changepriceBill_id = changepriceBill_id;
	}

	public String getChangepriceBill_cause() {
		return changepriceBill_cause;
	}

	public void setChangepriceBill_cause(String changepriceBill_cause) {
		this.changepriceBill_cause = changepriceBill_cause;
	}

	
	public Date getChangepriceBill_createtime() {
		return changepriceBill_createtime;
	}

	public void setChangepriceBill_createtime(Date changepriceBill_createtime) {
		this.changepriceBill_createtime = changepriceBill_createtime;
	}

	public Date getChangepriceBill_opertime() {
		return changepriceBill_opertime;
	}

	public void setChangepriceBill_opertime(Date changepriceBill_opertime) {
		this.changepriceBill_opertime = changepriceBill_opertime;
	}

	public int getChangepriceBill_statue() {
		return changepriceBill_statue;
	}

	public void setChangepriceBill_statue(int changepriceBill_statue) {
		this.changepriceBill_statue = changepriceBill_statue;
	}

	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		HisChangepriceBill hisChangepriceBill=new HisChangepriceBill();
		 try {
			 hisChangepriceBill.setChangepriceBill_id(rs.getInt("changepriceBill_id"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 hisChangepriceBill.setChangepriceBill_cause(rs.getString("changepriceBill_cause"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hisChangepriceBill.setChangepriceBill_createtime(rs.getTimestamp("changepriceBill_createtime"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 hisChangepriceBill.setChangepriceBill_opertime(rs.getTime("changepriceBill_opertime"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 hisChangepriceBill.setChangepriceBill_statue(rs.getInt("changepriceBill_statue"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 User userbean=(User)new User().mapRow(rs, rownum);
//			 hisChangepriceBill.setSys_user_id(rs.getInt("sys_user_id"));
			 hisChangepriceBill.setUser(userbean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return hisChangepriceBill;
	}

}
