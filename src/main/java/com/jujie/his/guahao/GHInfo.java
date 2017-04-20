package com.jujie.his.guahao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jujie.global.BaseBean;

public class GHInfo extends BaseBean {

	private Integer orderId; // 就诊号
	private double ghinfoGHMoney;// 挂号费
	private double ghinfoZLMoney;// 诊疗费
	private Date ghinfoDoTime;// 挂号时间
	private Integer sysUserId;// 操作员

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public double getGhinfoGHMoney() {
		return ghinfoGHMoney;
	}

	public void setGhinfoGHMoney(double ghinfoGHMoney) {
		this.ghinfoGHMoney = ghinfoGHMoney;
	}

	public double getGhinfoZLMoney() {
		return ghinfoZLMoney;
	}

	public void setGhinfoZLMoney(double ghinfoZLMoney) {
		this.ghinfoZLMoney = ghinfoZLMoney;
	}

	public Date getGhinfoDoTime() {
		return ghinfoDoTime;
	}

	public void setGhinfoDoTime(Date ghinfoDoTime) {
		this.ghinfoDoTime = ghinfoDoTime;
	}

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
