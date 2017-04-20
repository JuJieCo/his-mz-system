package com.jujie.his.medicare;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class CitizenMedDE205 extends BaseBean{
	
	private Integer c205Id;
	
	private String balancedate;//结算期
	private String balancetype;//结算类型
	

	private double debzPer;//1 定额标准
	private Integer sickNum; //2本月出院人数  count来的 
	private double debzTotal; //3 本月定额总额 3=1*2
	private double ybfy; //4 本月费用总额  sum来的
	
	private double under90; //5 低于或等于90%的部分？？？
	
	private double defy90to100Money;//6  高于90%低于100%的部分 金额
	private double defy90to100Reward;//7  高于90%低于100%的部分 奖励
	
	private double defy100to115Money;//8  高于100%低于115%的部分 金额
	private double defy100to115YY;//9  高于100%低于115%的部分 医院承担20%
	private double defy100to115YB;//10  高于100%低于115%的部分 医保承担80%
	
	private double defy115to130Money;//11  高于115%低于130%的部分 金额
	private double defy115to130YY;//12  高于115%低于130%的部分 医院承担40%
	private double defy115to130YB;//13  高于115%低于130%的部分 医保承担60%
	
	private double exceed130;//14 高于130%的部分 医院承担100%
	
	private double yjfy;//15 出院应结费用金额 15 =4+7
	private double zffy;//16 个人按比例自付金额
	private double tzfy;//17 门诊特诊特疗费用总额 
	private double yjfyTotal;//18 应结费用总计 18 = 15-16+17
	private double kcsh;//19  审核扣除金额--------医保中心填写
	private double kcbz;//20  扣除保证金
	private double sjje;//21 实结金额  21 = 18-19-20
	
	private String opertime;
	
	public Integer getC205Id() {
		return c205Id;
	}
	public void setC205Id(Integer c205Id) {
		this.c205Id = c205Id;
	}
	public String getBalancedate() {
		return balancedate;
	}
	public void setBalancedate(String balancedate) {
		this.balancedate = balancedate;
	}
	public String getBalancetype() {
		return balancetype;
	}
	public void setBalancetype(String balancetype) {
		this.balancetype = balancetype;
	}
	public double getDebzPer() {
		return debzPer;
	}
	public void setDebzPer(double debzPer) {
		this.debzPer = debzPer;
	}
	public Integer getSickNum() {
		return sickNum;
	}
	public void setSickNum(Integer sickNum) {
		this.sickNum = sickNum;
	}
	public double getDebzTotal() {
		return debzTotal;
	}
	public void setDebzTotal(double debzTotal) {
		this.debzTotal = debzTotal;
	}
	public double getYbfy() {
		return ybfy;
	}
	public void setYbfy(double ybfy) {
		this.ybfy = ybfy;
	}
	public double getUnder90() {
		return under90;
	}
	public void setUnder90(double under90) {
		this.under90 = under90;
	}
	public double getDefy90to100Money() {
		return defy90to100Money;
	}
	public void setDefy90to100Money(double defy90to100Money) {
		this.defy90to100Money = defy90to100Money;
	}
	public double getDefy90to100Reward() {
		return defy90to100Reward;
	}
	public void setDefy90to100Reward(double defy90to100Reward) {
		this.defy90to100Reward = defy90to100Reward;
	}
	public double getDefy100to115Money() {
		return defy100to115Money;
	}
	public void setDefy100to115Money(double defy100to115Money) {
		this.defy100to115Money = defy100to115Money;
	}
	public double getDefy100to115YY() {
		return defy100to115YY;
	}
	public void setDefy100to115YY(double defy100to115yy) {
		defy100to115YY = defy100to115yy;
	}
	public double getDefy100to115YB() {
		return defy100to115YB;
	}
	public void setDefy100to115YB(double defy100to115yb) {
		defy100to115YB = defy100to115yb;
	}
	public double getDefy115to130Money() {
		return defy115to130Money;
	}
	public void setDefy115to130Money(double defy115to130Money) {
		this.defy115to130Money = defy115to130Money;
	}
	public double getDefy115to130YY() {
		return defy115to130YY;
	}
	public void setDefy115to130YY(double defy115to130yy) {
		defy115to130YY = defy115to130yy;
	}
	public double getDefy115to130YB() {
		return defy115to130YB;
	}
	public void setDefy115to130YB(double defy115to130yb) {
		defy115to130YB = defy115to130yb;
	}
	public double getExceed130() {
		return exceed130;
	}
	public void setExceed130(double exceed130) {
		this.exceed130 = exceed130;
	}
	public double getYjfy() {
		return yjfy;
	}
	public void setYjfy(double yjfy) {
		this.yjfy = yjfy;
	}
	public double getZffy() {
		return zffy;
	}
	public void setZffy(double zffy) {
		this.zffy = zffy;
	}
	public double getTzfy() {
		return tzfy;
	}
	public void setTzfy(double tzfy) {
		this.tzfy = tzfy;
	}
	public double getYjfyTotal() {
		return yjfyTotal;
	}
	public void setYjfyTotal(double yjfyTotal) {
		this.yjfyTotal = yjfyTotal;
	}
	public double getKcsh() {
		return kcsh;
	}
	public void setKcsh(double kcsh) {
		this.kcsh = kcsh;
	}
	public double getKcbz() {
		return kcbz;
	}
	public void setKcbz(double kcbz) {
		this.kcbz = kcbz;
	}
	public double getSjje() {
		return sjje;
	}
	public void setSjje(double sjje) {
		this.sjje = sjje;
	}
	public String getOpertime() {
		return opertime;
	}
	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
	
		return null;
	}
	
	
	

}
