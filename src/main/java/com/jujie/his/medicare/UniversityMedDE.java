package com.jujie.his.medicare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jujie.global.BaseBean;

public class UniversityMedDE extends BaseBean {
	/**
	 * 状态  新建
	 */
	public static int OPERSTATUE_NEW = 1;
	/**
	 * 状态  修改
	 */
	public static int OPERSTATUE_MODFITY = 2;
	/**
	 * 主键
	 */
	private int university_id ;
	/**
	 * 医疗机构编码
	 */
	private String university_hcode ;
	/**
	 * 医院名称
	 */
	private String university_hname ;
	/**
	 * 就诊编号
	 */
	private String university_jcode ;
	/**
	 * 姓名
	 */
	private String university_pname ;
	/**
	 * 性别
	 */
	private String university_psex ;
	/**
	 * 年龄
	 */
	private int university_page ;
	/**
	 * 医保编号
	 */
	private String university_ycode ;
	/**
	 * 身份证号码
	 */
	private String university_pcode ;
	/**
	 * 高校名称
	 */
	private String university_name ;
	/**
	 * 人员类别
	 */
	private String university_ptype ;
	/**
	 * 门诊次数
	 */
	private int university_mztimes ;
	/**
	 * 就诊时间
	 */
	private Date university_mzdate ;
	/**
	 * 是否转诊
	 */
	private String university_istomz ;
	/**
	 * 疾病诊断
	 */
	private String university_ccontent ;
	/**
	 * 本年度门诊统筹费用累计
	 */
	private double university_yeartctotal ;
	/**
	 * 本年度门诊统筹基金累计支出
	 */
	private double university_yearjjtotal ;
	/**
	 * 就诊年度
	 */
	private String university_mzyear ;
	/**
	 * 本次门诊总费用
	 */
	private double university_mztotalcost ;
	/**
	 * 药费
	 */
	private double university_medcost ;
	/**
	 * 检查费
	 */
	private double university_checkcost ;
	/**
	 * 化验费
	 */
	private double university_assaycost ;
	/**
	 * 治疗费
	 */
	private double university_treatcost ;
	/**
	 * 其他费用
	 */
	private double university_othercost ;
	/**
	 * 全自费
	 */
	private double university_ppaytotal ;
	/**
	 * 门诊统筹费用
	 */
	private double university_mztc ;
	/**
	 * 备注
	 */
	private String university_remark ;
	/**
	 * 起付标准费
	 */
	private double university_paystand ;
	/**
	 * 报销比例	
	 */
	private double university_applyper ;
	/**
	 * 本次个人应付金额
	 */
	private double university_ppay ;
	/**
	 * 本次门诊统筹报销金额
	 */
	private double university_mztcbx ;
	/**
	 * 经办时间
	 */
	private Date university_opertime ;
	/**
	 * 操作状态
	 * 1.新建  2.修改
	 */
	private int university_statue=1;
	/**
	 * 来源时间
	 */
	private Date university_fromdate ;
	
	
	private List<UniversityMedDEBAK> bakList;

	public List<UniversityMedDEBAK> getBakList() {
		return bakList;
	}
	public void setBakList(List<UniversityMedDEBAK> bakList) {
		this.bakList = bakList;
	}
	
	public UniversityMedDE(){
		bakList = new ArrayList<UniversityMedDEBAK>();
	}
	
	
	public int getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}
	public String getUniversity_hcode() {
		return university_hcode;
	}
	public void setUniversity_hcode(String university_hcode) {
		this.university_hcode = university_hcode;
	}
	public String getUniversity_hname() {
		return university_hname;
	}
	public void setUniversity_hname(String university_hname) {
		this.university_hname = university_hname;
	}
	public String getUniversity_jcode() {
		return university_jcode;
	}
	public void setUniversity_jcode(String university_jcode) {
		this.university_jcode = university_jcode;
	}
	public String getUniversity_pname() {
		return university_pname;
	}
	public void setUniversity_pname(String university_pname) {
		this.university_pname = university_pname;
	}
	public String getUniversity_psex() {
		return university_psex;
	}
	public void setUniversity_psex(String university_psex) {
		this.university_psex = university_psex;
	}
	public int getUniversity_page() {
		return university_page;
	}
	public void setUniversity_page(int university_page) {
		this.university_page = university_page;
	}
	public String getUniversity_ycode() {
		return university_ycode;
	}
	public void setUniversity_ycode(String university_ycode) {
		this.university_ycode = university_ycode;
	}
	public String getUniversity_pcode() {
		return university_pcode;
	}
	public void setUniversity_pcode(String university_pcode) {
		this.university_pcode = university_pcode;
	}
	public String getUniversity_name() {
		return university_name;
	}
	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}
	public String getUniversity_ptype() {
		return university_ptype;
	}
	public void setUniversity_ptype(String university_ptype) {
		this.university_ptype = university_ptype;
	}
	public int getUniversity_mztimes() {
		return university_mztimes;
	}
	public void setUniversity_mztimes(int university_mztimes) {
		this.university_mztimes = university_mztimes;
	}
	public Date getUniversity_mzdate() {
		return university_mzdate;
	}
	public void setUniversity_mzdate(Date university_mzdate) {
		this.university_mzdate = university_mzdate;
	}
	public String getUniversity_istomz() {
		return university_istomz;
	}
	public void setUniversity_istomz(String university_istomz) {
		this.university_istomz = university_istomz;
	}
	public String getUniversity_ccontent() {
		return university_ccontent;
	}
	public void setUniversity_ccontent(String university_ccontent) {
		this.university_ccontent = university_ccontent;
	}
	public double getUniversity_yeartctotal() {
		return university_yeartctotal;
	}
	public void setUniversity_yeartctotal(double university_yeartctotal) {
		this.university_yeartctotal = university_yeartctotal;
	}
	public double getUniversity_yearjjtotal() {
		return university_yearjjtotal;
	}
	public void setUniversity_yearjjtotal(double university_yearjjtotal) {
		this.university_yearjjtotal = university_yearjjtotal;
	}
	public String getUniversity_mzyear() {
		return university_mzyear;
	}
	public void setUniversity_mzyear(String university_mzyear) {
		this.university_mzyear = university_mzyear;
	}
	public double getUniversity_mztotalcost() {
		return university_mztotalcost;
	}
	public void setUniversity_mztotalcost(double university_mztotalcost) {
		this.university_mztotalcost = university_mztotalcost;
	}
	public double getUniversity_medcost() {
		return university_medcost;
	}
	public void setUniversity_medcost(double university_medcost) {
		this.university_medcost = university_medcost;
	}
	public double getUniversity_checkcost() {
		return university_checkcost;
	}
	public void setUniversity_checkcost(double university_checkcost) {
		this.university_checkcost = university_checkcost;
	}
	public double getUniversity_assaycost() {
		return university_assaycost;
	}
	public void setUniversity_assaycost(double university_assaycost) {
		this.university_assaycost = university_assaycost;
	}
	public double getUniversity_treatcost() {
		return university_treatcost;
	}
	public void setUniversity_treatcost(double university_treatcost) {
		this.university_treatcost = university_treatcost;
	}
	public double getUniversity_othercost() {
		return university_othercost;
	}
	public void setUniversity_othercost(double university_othercost) {
		this.university_othercost = university_othercost;
	}
	public double getUniversity_ppaytotal() {
		return university_ppaytotal;
	}
	public void setUniversity_ppaytotal(double university_ppaytotal) {
		this.university_ppaytotal = university_ppaytotal;
	}
	public double getUniversity_mztc() {
		return university_mztc;
	}
	public void setUniversity_mztc(double university_mztc) {
		this.university_mztc = university_mztc;
	}
	public String getUniversity_remark() {
		return university_remark;
	}
	public void setUniversity_remark(String university_remark) {
		this.university_remark = university_remark;
	}
	public double getUniversity_paystand() {
		return university_paystand;
	}
	public void setUniversity_paystand(double university_paystand) {
		this.university_paystand = university_paystand;
	}
	public double getUniversity_applyper() {
		return university_applyper;
	}
	public void setUniversity_applyper(double university_applyper) {
		this.university_applyper = university_applyper;
	}
	public double getUniversity_ppay() {
		return university_ppay;
	}
	public void setUniversity_ppay(double university_ppay) {
		this.university_ppay = university_ppay;
	}
	public double getUniversity_mztcbx() {
		return university_mztcbx;
	}
	public void setUniversity_mztcbx(double university_mztcbx) {
			this.university_mztcbx = university_mztcbx;	
	}
	public Date getUniversity_opertime() {
		return university_opertime;
	}
	public void setUniversity_opertime(Date university_opertime) {
		this.university_opertime = university_opertime;
	}
	public int getUniversity_statue() {
		return university_statue;
	}
	public void setUniversity_statue(int university_statue) {
		this.university_statue = university_statue;
	}
	public Date getUniversity_fromdate() {
		return university_fromdate;
	}
	public void setUniversity_fromdate(Date university_fromdate) {
		this.university_fromdate = university_fromdate;
	}
	@Override
	public UniversityMedDE mapRow(ResultSet rs, int rownum) throws SQLException {
		UniversityMedDE universityMedDE = new UniversityMedDE();
		try{
			universityMedDE.setUniversity_applyper(rs.getDouble("university_applyper"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_assaycost(rs.getDouble("university_assaycost"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_ccontent(rs.getString("university_ccontent"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_checkcost(rs.getDouble("university_checkcost"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_hcode(rs.getString("university_hcode"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_hname(rs.getString("university_hname"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_id(rs.getInt("university_id"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_istomz(rs.getString("university_istomz"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_jcode(rs.getString("university_jcode"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_medcost(rs.getDouble("university_medcost"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_mzdate(rs.getTimestamp("university_mzdate"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_mztc(rs.getDouble("university_mztc"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_mztimes(rs.getInt("university_mztimes"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_mztotalcost(rs.getDouble("university_mztotalcost"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_mztcbx(rs.getDouble("university_mztcbx"));
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			universityMedDE.setUniversity_mzyear(rs.getString("university_mzyear"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_name(rs.getString("university_name"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_opertime(rs.getTimestamp("university_opertime"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_othercost(rs.getDouble("university_othercost"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_page(rs.getInt("university_page"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_paystand(rs.getDouble("university_paystand"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_pcode(rs.getString("university_pcode"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_pname(rs.getString("university_pname"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_ppay(rs.getDouble("university_ppay"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_ppaytotal(rs.getDouble("university_ppaytotal"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_psex(rs.getString("university_psex"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_ptype(rs.getString("university_ptype"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_remark(rs.getString("university_remark"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_treatcost(rs.getDouble("university_treatcost"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_ycode(rs.getString("university_ycode"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_yearjjtotal(rs.getDouble("university_yearjjtotal"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_yeartctotal(rs.getDouble("university_yeartctotal"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_statue(rs.getInt("university_statue"));
		}catch(Exception e){}
		try{
			universityMedDE.setUniversity_fromdate(rs.getTimestamp("university_fromdate"));
		}catch(Exception e){}
		return universityMedDE;
	}

}
