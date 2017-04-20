package com.jujie.his.mz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jujie.global.BaseBean;

public class Fee_Type  extends BaseBean{
	/*0划价  1作废 2 收费 */
  public static int FLAG_YOUXIAO=0;
  public static int FLAG_ZUOFEE=1;
  public static int FLAG_SHOUFEI=2;
  private  int feeid;
  private int order_id;
  private  int jytpye;
  private double hjinfo_totalM;
  private double hjinfo_getM;
  private int sys_user_id;
  private Date hjinfo_dotime;
  private int flag;
  private int void_userid;
  private Date void_dotime;
  private String fph;
  private int sf_userid;//收费员
  private Date sf_dotime;//收费时间
  
public int getFeeid() {
	return feeid;
}
public void setFeeid(int feeid) {
	this.feeid = feeid;
}
public int getOrder_id() {
	return order_id;
}
public void setOrder_id(int order_id) {
	this.order_id = order_id;
}
public int getJytpye() {
	return jytpye;
}
public void setJytpye(int jytpye) {
	this.jytpye = jytpye;
}
public double getHjinfo_totalM() {
	return hjinfo_totalM;
}
public void setHjinfo_totalM(double hjinfo_totalM) {
	this.hjinfo_totalM = hjinfo_totalM;
}
public double getHjinfo_getM() {
	return hjinfo_getM;
}
public void setHjinfo_getM(double hjinfo_getM) {
	this.hjinfo_getM = hjinfo_getM;
}
public Date getHjinfo_dotime() {
	return hjinfo_dotime;
}
public void setHjinfo_dotime(Date hjinfo_dotime) {
	this.hjinfo_dotime = hjinfo_dotime;
}
public int getFlag() {
	return flag;
}
public void setFlag(int flag) {
	this.flag = flag;
}
public Date getVoid_dotime() {
	return void_dotime;
}
public void setVoid_dotime(Date void_dotime) {
	this.void_dotime = void_dotime;
}
public String getFph() {
	return fph;
}
public void setFph(String fph) {
	this.fph = fph;
}
public int getSys_user_id() {
	return sys_user_id;
}
public void setSys_user_id(int sys_user_id) {
	this.sys_user_id = sys_user_id;
}
public int getVoid_userid() {
	return void_userid;
}
public void setVoid_userid(int void_userid) {
	this.void_userid = void_userid;
}


public int getSf_userid() {
	return sf_userid;
}
public void setSf_userid(int sf_userid) {
	this.sf_userid = sf_userid;
}
public Date getSf_dotime() {
	return sf_dotime;
}
public void setSf_dotime(Date sf_dotime) {
	this.sf_dotime = sf_dotime;
}
@Override
public Object mapRow(ResultSet rs, int rownum) throws SQLException {
	// TODO Auto-generated method stub
	Fee_Type feetype=new Fee_Type();
	try {
		feetype.setFeeid(rs.getInt("feeid"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setOrder_id(rs.getInt("order_id"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setJytpye(rs.getInt("jytpye"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		feetype.setHjinfo_totalM(rs.getDouble("hjinfo_totalM"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setHjinfo_getM(rs.getDouble("hjinfo_getM"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		feetype.setSys_user_id(rs.getInt("sys_user_id"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setHjinfo_dotime(rs.getTimestamp("hjinfo_dotime"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setFlag(rs.getInt("flag"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		feetype.setVoid_userid(rs.getInt("void_userid"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setVoid_dotime(rs.getTimestamp("void_dotime"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setFph(rs.getString("fph"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setSf_dotime(rs.getTimestamp("sf_dotime"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	try {
		feetype.setSf_userid(rs.getInt("sf_userid"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	return feetype;
}
 
  
}
