package com.jujie.his.medicare;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class CitizenMedDE extends BaseBean {

	private int citizenId;// 主键

	private String name;// 姓名

	private String sex;// 性别  男  女
	private int age;// 年龄

	private String sfcode;// 身份证号
	private String addr;// 社区
	private String ybcode;// 医保编号
	private String zycode;// 住院号
	private String dept;// 科室
	private String clies;// 出院诊断
	private String result;// 治疗结果

	private String indate;// 入院日期
	private String outdate;// 出院日期

	private int dates;// 天数
	private String balancetype;// 结算类别  定额结算  单病种
	private String balancedate;// 结算期 1-12月
	private String persontype;// 人员类别  非从业居民  大学生

	private double oldtcjjlj;// 既往统筹基金累计
	private double nowtcjjlj;// 本次统筹基金累计

	private String checkcode;// 审核号

	private double zfyfper;// 自费药品费个人 P1
	private double zfyftotal;// 自费药品费小计 t1

	private double overcwfper;// 超床位费个人 p2
	private double overcwftotal;// 超床位费小计 t2

	private double otherfper;// 其他费用个人 p3
	private double otherftotal;// 其他费用小计 t3

	private double ybxezfper;// 医保限额以上自费个人 p4
	private double ybxezftotal;// 医保限额以上自费小计 t4

	private double qfbzper;// 起付标准（自付）个人 p5
	private double qfbztotal;// 起付标准（自付）小计 t5

	private double zfbiliper;// 个人支付比例 45%=非从业居民 30%大学生

	private double grblzfper;// 个人按比例自付个人 p6
	private double grblzftotal;// 个人按比例自付小计 t6

	private double peralltotal;// 个人支付总计 k2
	private double tczfper;// 统筹基金支付 k4
	private double tczfalltotal;// 统筹基金支付总计 k3
	private double alltotal;// 合计 k1
	private String opertime;// 操作时间
	

	// private int diseasetype;// 单病种类别 
	private double zyfytotal;// 住院医疗费用总额  204需要
	
	//表里没有的字段
	private Integer countNum;  // 204 、205 统计病人数
	private double sumNum;//205合计
	private double sumNumber;//205合计

	
	
	
	

	public int getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(int citizenId) {
		this.citizenId = citizenId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSfcode() {
		return sfcode;
	}

	public void setSfcode(String sfcode) {
		this.sfcode = sfcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getYbcode() {
		return ybcode;
	}

	public void setYbcode(String ybcode) {
		this.ybcode = ybcode;
	}

	public String getZycode() {
		return zycode;
	}

	public void setZycode(String zycode) {
		this.zycode = zycode;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getClies() {
		return clies;
	}

	public void setClies(String clies) {
		this.clies = clies;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getOutdate() {
		return outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}

	public int getDates() {
		return dates;
	}

	public void setDates(int dates) {
		this.dates = dates;
	}

	public String getBalancetype() {
		return balancetype;
	}

	public void setBalancetype(String balancetype) {
		this.balancetype = balancetype;
	}

	public String getBalancedate() {
		return balancedate;
	}

	public void setBalancedate(String balancedate) {
		this.balancedate = balancedate;
	}

	public String getPersontype() {
		return persontype;
	}

	public void setPersontype(String persontype) {
		this.persontype = persontype;
	}

	public double getOldtcjjlj() {
		return oldtcjjlj;
	}

	public void setOldtcjjlj(double oldtcjjlj) {
		this.oldtcjjlj = oldtcjjlj;
	}

	public double getNowtcjjlj() {
		return nowtcjjlj;
	}

	public void setNowtcjjlj(double nowtcjjlj) {
		this.nowtcjjlj = nowtcjjlj;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public double getZfyfper() {
		return zfyfper;
	}

	public void setZfyfper(double zfyfper) {
		this.zfyfper = zfyfper;
	}

	public double getZfyftotal() {
		return zfyftotal;
	}

	public void setZfyftotal(double zfyftotal) {
		this.zfyftotal = zfyftotal;
	}

	public double getOvercwfper() {
		return overcwfper;
	}

	public void setOvercwfper(double overcwfper) {
		this.overcwfper = overcwfper;
	}

	public double getOvercwftotal() {
		return overcwftotal;
	}

	public void setOvercwftotal(double overcwftotal) {
		this.overcwftotal = overcwftotal;
	}

	public double getOtherfper() {
		return otherfper;
	}

	public void setOtherfper(double otherfper) {
		this.otherfper = otherfper;
	}

	public double getOtherftotal() {
		return otherftotal;
	}

	public void setOtherftotal(double otherftotal) {
		this.otherftotal = otherftotal;
	}

	public double getYbxezfper() {
		return ybxezfper;
	}

	public void setYbxezfper(double ybxezfper) {
		this.ybxezfper = ybxezfper;
	}

	public double getYbxezftotal() {
		return ybxezftotal;
	}

	public void setYbxezftotal(double ybxezftotal) {
		this.ybxezftotal = ybxezftotal;
	}

	public double getQfbzper() {
		return qfbzper;
	}

	public void setQfbzper(double qfbzper) {
		this.qfbzper = qfbzper;
	}

	public double getQfbztotal() {
		return qfbztotal;
	}

	public void setQfbztotal(double qfbztotal) {
		this.qfbztotal = qfbztotal;
	}

	public double getZfbiliper() {
		return zfbiliper;
	}

	public void setZfbiliper(double zfbiliper) {
		this.zfbiliper = zfbiliper;
	}

	public double getGrblzfper() {
		return grblzfper;
	}

	public void setGrblzfper(double grblzfper) {
		this.grblzfper = grblzfper;
	}

	public double getGrblzftotal() {
		return grblzftotal;
	}

	public void setGrblzftotal(double grblzftotal) {
		this.grblzftotal = grblzftotal;
	}

	public double getPeralltotal() {
		return peralltotal;
	}

	public void setPeralltotal(double peralltotal) {
		this.peralltotal = peralltotal;
	}

	public double getTczfper() {
		return tczfper;
	}

	public void setTczfper(double tczfper) {
		this.tczfper = tczfper;
	}

	public double getTczfalltotal() {
		return tczfalltotal;
	}

	public void setTczfalltotal(double tczfalltotal) {
		this.tczfalltotal = tczfalltotal;
	}

	public double getAlltotal() {
		return alltotal;
	}

	public void setAlltotal(double alltotal) {
		this.alltotal = alltotal;
	}

	public String getOpertime() {
		return opertime;
	}

	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	
	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public double getSumNum() {
		return sumNum;
	}

	public void setSumNum(double sumNum) {
		this.sumNum = sumNum;
	}

	public double getSumNumber() {
		return sumNumber;
	}

	public void setSumNumber(double sumNumber) {
		this.sumNumber = sumNumber;
	}

	public double getZyfytotal() {
		return zyfytotal;
	}

	public void setZyfytotal(double zyfytotal) {
		this.zyfytotal = zyfytotal;
	}


	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		CitizenMedDE citizenMed = new  CitizenMedDE();
		citizenMed.setCitizenId(rs.getInt("citizenid"));
		citizenMed.setName(rs.getString("name"));
		citizenMed.setSex(rs.getString("sex"));
		citizenMed.setAge(rs.getInt("age"));
		citizenMed.setSfcode(rs.getString("sfcode"));
		citizenMed.setAddr(rs.getString("addr"));
		citizenMed.setYbcode(rs.getString("ybcode"));
		citizenMed.setZycode(rs.getString("zycode"));
		citizenMed.setDept(rs.getString("dept"));
		citizenMed.setClies(rs.getString("clies"));
		citizenMed.setResult(rs.getString("result"));
		citizenMed.setIndate(rs.getString("indate"));
		citizenMed.setOutdate(rs.getString("outdate"));
		citizenMed.setDates(rs.getInt("dates"));
		citizenMed.setBalancetype(rs.getString("balancetype"));
		citizenMed.setBalancedate(rs.getString("balancedate"));
		citizenMed.setPersontype(rs.getString("persontype"));
		citizenMed.setOldtcjjlj(rs.getDouble("oldtcjjlj"));
		citizenMed.setNowtcjjlj(rs.getDouble("nowtcjjlj"));
		citizenMed.setCheckcode(rs.getString("checkcode"));
		citizenMed.setZfyfper(rs.getDouble("zfyfper"));
		citizenMed.setZfyftotal(rs.getDouble("zfyftotal"));
		citizenMed.setOvercwfper(rs.getDouble("overcwfper"));
		citizenMed.setOvercwftotal(rs.getDouble("overcwftotal"));
		citizenMed.setOtherfper(rs.getDouble("otherfper"));
		citizenMed.setOtherftotal(rs.getDouble("otherftotal"));
		citizenMed.setYbxezfper(rs.getDouble("ybxezfper"));
		citizenMed.setYbxezftotal(rs.getDouble("ybxezftotal"));
		citizenMed.setQfbzper(rs.getDouble("qfbzper"));
		citizenMed.setQfbztotal(rs.getDouble("qfbztotal"));
		citizenMed.setZfbiliper(rs.getDouble("zfbiliper"));
		citizenMed.setGrblzfper(rs.getDouble("grblzfper"));
		citizenMed.setGrblzftotal(rs.getDouble("grblzftotal"));
		citizenMed.setPeralltotal(rs.getDouble("peralltotal"));
		citizenMed.setTczfper(rs.getDouble("tczfper"));
		citizenMed.setTczfalltotal(rs.getDouble("tczfalltotal"));
		citizenMed.setAlltotal(rs.getDouble("alltotal"));
		citizenMed.setOpertime(rs.getString("opertime"));
		//citizenMed.setDiseasetype(rs.getInt("diseasetype")); 
		citizenMed.setZyfytotal(rs.getDouble("zyfytotal"));
		
		return citizenMed;
	}

}
