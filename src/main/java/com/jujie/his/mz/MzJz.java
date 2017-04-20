package com.jujie.his.mz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jujie.global.BaseBean;

public class MzJz extends BaseBean implements  Cloneable{
	private int uuid;//就诊编号
	private Integer order_id; //就诊号
 	private String sick_casehistory;//病历号
	private String zlinfo_content;//病历内容
	private String zlinfo_remark;//备注说明
	private Integer sys_user_id;//操作员
	private Date zlinfo_dotime;//就诊时间 
	private MzYp mzYp ;
	private List<MzYp> mzYpList; // 药品明细
	private int flag ; //有效标志
	
	

	public Integer getOrder_id() {
		return order_id;
	}



	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}



	public int getUuid() {
		return uuid;
	}



	public void setUuid(int uuid) {
		this.uuid = uuid;
	}



	public String getSick_casehistory() {
		return sick_casehistory;
	}



	public void setSick_casehistory(String sick_casehistory) {
		this.sick_casehistory = sick_casehistory;
	}



	public String getZlinfo_content() {
		return zlinfo_content;
	}



	public void setZlinfo_content(String zlinfo_content) {
		this.zlinfo_content = zlinfo_content;
	}



	public String getZlinfo_remark() {
		return zlinfo_remark;
	}



	public void setZlinfo_remark(String zlinfo_remark) {
		this.zlinfo_remark = zlinfo_remark;
	}



	public Integer getSys_user_id() {
		return sys_user_id;
	}



	public void setSys_user_id(Integer sys_user_id) {
		this.sys_user_id = sys_user_id;
	}



	public Date getZlinfo_dotime() {
		return zlinfo_dotime;
	}



	public void setZlinfo_dotime(Date zlinfo_dotime) {
		this.zlinfo_dotime = zlinfo_dotime;
	}

	public List<MzYp> getMzYpList() {
		return mzYpList;
	}
	public void setMzYpList(List<MzYp> mzYpList) {
		this.mzYpList = mzYpList;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public MzYp getMzYp() {
		return mzYp;
	}



	public void setMzYp(MzYp mzYp) {
		this.mzYp = mzYp;
	}

	public Object clone() {
		try {
			return super.clone();

		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public MzJz(){
		mzYp = new MzYp();
		this.mzYpList = new ArrayList<MzYp>();
	}
	
	@Override
	public MzJz mapRow(ResultSet rs, int rownum) throws SQLException {
		MzJz mzJz = new MzJz();
		mzJz.setUuid(rs.getInt("uuid"));
		mzJz.setOrder_id(rs.getInt("order_id"));
		mzJz.setSick_casehistory(rs.getString("sick_casehistory"));
		mzJz.setZlinfo_content(rs.getString("zlinfo_content"));
		mzJz.setZlinfo_remark(rs.getString("zlinfo_remark"));
		mzJz.setZlinfo_dotime(rs.getTimestamp("zlinfo_dotime"));
		mzJz.setSys_user_id(rs.getInt("sys_user_id"));
		mzJz.setFlag(rs.getInt("flag"));
		return mzJz;
	}
	
	
}
