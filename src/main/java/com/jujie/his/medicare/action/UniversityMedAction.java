package com.jujie.his.medicare.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.icu.text.SimpleDateFormat;
import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.medicare.UniversityMedDE;
import com.jujie.his.medicare.UniversityMedTotal;
import com.jujie.his.medicare.server.UniversityMedServerImpl;
import com.jujie.util.DataUtils;
import com.jujie.util.ExportExcel;
import com.jujie.util.page.Page;

public class UniversityMedAction extends BaseActionSupper {
	
	private UniversityMedTotal universityMedTotal;
	private UniversityMedDE universityMedDE;
	private List<UniversityMedDE> universityMedDEList = new ArrayList<UniversityMedDE>();
	private Page page;
	private String s_token;
	private String year=new Date().getYear()+1900+"";
	private String month=(new Date().getMonth()+1<10?("0"+(new Date().getMonth()+1)):(new Date().getMonth()+1))+"";
	private int opertype;

	public UniversityMedTotal getUniversityMedTotal() {
		return universityMedTotal;
	}

	public void setUniversityMedTotal(UniversityMedTotal universityMedTotal) {
		this.universityMedTotal = universityMedTotal;
	}

	public UniversityMedDE getUniversityMedDE() {
		return universityMedDE;
	}

	public void setUniversityMedDE(UniversityMedDE universityMedDE) {
		this.universityMedDE = universityMedDE;
	}

	public List<UniversityMedDE> getUniversityMedDEList() {
		return universityMedDEList;
	}

	public void setUniversityMedDEList(List<UniversityMedDE> universityMedDEList) {
		this.universityMedDEList = universityMedDEList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getS_token() {
		return s_token;
	}

	public void setS_token(String s_token) {
		this.s_token = s_token;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getOpertype() {
		return opertype;
	}

	public void setOpertype(int opertype) {
		this.opertype = opertype;
	}

	public String Utils_DateYM(){
		if(month!=null && year !=null){
			return year+"-"+(DataUtils.getInt(month)<10?("0"+DataUtils.getInt(month)):month);
		}else{
			return "";
		}
	}
	public String Utils_DateYM_next(){
		if(month!=null && year !=null){
			if("12".equals(month)){
				return (DataUtils.getInt(year)+1)+"-01";
			}else{
				return year+"-"+(DataUtils.getInt(month)+1<10?("0"+(DataUtils.getInt(month)+1)):(DataUtils.getInt(month)+1))+"";
			}
			
		}else{
			return "";
		}
	}
	
	public String queryMZnotCheckPerson(){
		UniversityMedServerImpl universityMedServerImpl = (UniversityMedServerImpl) this.getService("universityMedServer");
		if (page == null) {
			page = new Page(1);
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("sdate", Utils_DateYM());
		map.put("edate", Utils_DateYM_next());
		try {
			universityMedDEList = universityMedServerImpl.queryUniversityMedDEListFromMZ(page, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mznotclist";
	}
	
	public String queryCheckPerson(){
		UniversityMedServerImpl universityMedServerImpl = (UniversityMedServerImpl) this.getService("universityMedServer");
		if (page == null) {
			page = new Page(1);
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("sdate", Utils_DateYM());
		map.put("edate", Utils_DateYM_next());
		try {
			universityMedDEList = universityMedServerImpl.queryUniversityMedDEList(page, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cklist";
	}
	
	public String savePersonInfo(){
		UniversityMedServerImpl universityMedServerImpl = (UniversityMedServerImpl) this.getService("universityMedServer");
		universityMedDE.setUniversity_fromdate(universityMedDE.getUniversity_mzdate());
		universityMedDE.setUniversity_opertime(new Date());
		universityMedDE.setUniversity_statue(UniversityMedDE.OPERSTATUE_NEW);
		universityMedServerImpl.saveUniversityMedDE(universityMedDE);
		return queryMZnotCheckPerson();
	}
	
	public String editPersonInfo(){
		UniversityMedServerImpl universityMedServerImpl = (UniversityMedServerImpl) this.getService("universityMedServer");
		universityMedDE.setUniversity_fromdate(universityMedDE.getUniversity_mzdate());
		universityMedDE.setUniversity_opertime(new Date());
		universityMedDE.setUniversity_statue(UniversityMedDE.OPERSTATUE_MODFITY);
		universityMedServerImpl.editUniversityMedDE(universityMedDE);
		return queryCheckPerson();
	}
	
	public String showCheckPage(){
		UniversityMedServerImpl universityMedServerImpl = (UniversityMedServerImpl) this.getService("universityMedServer");
		Map<String,String> map = new HashMap<String,String>();
		map.put("sdate", Utils_DateYM());
		map.put("edate", Utils_DateYM_next());
		map.put("id", request.getParameter("id"));
		map.put("ybcode", request.getParameter("ybcode"));
		map.put("update", request.getParameter("update"));
		this.getCxt().put("show", request.getParameter("show"));
		try {
			universityMedDE = universityMedServerImpl.queryOneUniversityMedDE(opertype, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(request.getParameter("print")!=null&&"true".equals(request.getParameter("print"))){
			universityMedDEList.add(universityMedDE);
			return  SUCCESS;
		}
		return "show";
	}
	
	public String showMZtcTotal(){
		UniversityMedServerImpl universityMedServerImpl = (UniversityMedServerImpl) this.getService("universityMedServer");
		Map<String,String> map = new HashMap<String,String>();
		map.put("sdate", Utils_DateYM());
		map.put("edate", Utils_DateYM_next());
		try {
			universityMedTotal = universityMedServerImpl.queryUniversityMedTotalByMonth(map);
			List<UniversityMedTotal> universityMedTotalList = new ArrayList<UniversityMedTotal>();
			universityMedTotalList.add(universityMedTotal);
			this.getCxt().put("universityMedTotalList", universityMedTotalList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(request.getParameter("print")!=null&&"true".equals(request.getParameter("print"))){
			universityMedDEList.add(universityMedDE);
			return  SUCCESS;
		}
		return "unybtotal";
	}
	
	public String exportDetails() throws IOException{
		List<String> titles = new ArrayList<String>();
		titles.add("医疗机构编码 ");
		titles.add("医院名称");
		titles.add("就诊编号");
		titles.add("医保编号");
		titles.add("姓名");
		titles.add("性别");
		titles.add("就诊时间");
		titles.add("门诊次数");
		titles.add("年度");
		titles.add("本次门诊总费用");
		titles.add("药费");
		titles.add("检测费");
		titles.add("化验费");
		titles.add("治疗费");
		titles.add("其他费用");
		titles.add("全自费");
		titles.add("本次门诊统筹费用");
		titles.add("本次门诊统筹支付金额");
		titles.add("本年度门诊统筹累计支出");
		titles.add("本年度门诊统筹基金累计支出");
		titles.add("是否转诊");
		titles.add("就诊年月");
		titles.add("疾病诊断");
		titles.add("经办时间");
		
		UniversityMedServerImpl universityMedServerImpl = (UniversityMedServerImpl) this.getService("universityMedServer");
		Map<String,String> map = new HashMap<String,String>();
		map.put("sdate", Utils_DateYM());
		map.put("edate", Utils_DateYM_next());
		try {
			universityMedDEList = universityMedServerImpl.queryUniversityMedDEList(null, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		List<List<Object>> values  = new ArrayList<List<Object>>();
		for (UniversityMedDE med : universityMedDEList) {
			List<Object> list = new ArrayList<Object>();
			list.add("2196");
			list.add(med.getUniversity_name());
			list.add(med.getUniversity_jcode());
			list.add(med.getUniversity_ycode());
			list.add(med.getUniversity_pname());
			list.add(med.getUniversity_psex());
			list.add(sf.format(med.getUniversity_mzdate()));
			list.add(med.getUniversity_mztimes());
			list.add(med.getUniversity_mzyear());
			list.add(med.getUniversity_mztotalcost());
			list.add(med.getUniversity_medcost());
			list.add(med.getUniversity_checkcost());
			list.add(med.getUniversity_assaycost());
			list.add(med.getUniversity_treatcost());
			list.add(med.getUniversity_othercost());
			list.add(med.getUniversity_ppaytotal());
			list.add(med.getUniversity_mztc());
			list.add(med.getUniversity_mztcbx());
			list.add(med.getUniversity_yeartctotal());
			list.add(med.getUniversity_yearjjtotal());
			list.add(med.getUniversity_istomz());
			list.add(year+"年"+month+"月");
			list.add(med.getUniversity_ccontent());
			list.add(sf.format(med.getUniversity_opertime()));
			values.add(list);
		}
		
		try {
			response.setContentType("text/plain");
			response.setHeader("Location", "门诊统筹结算明细单");
			response.setHeader("Content-Disposition", "attachment; filename="+year+"-"+month+"_dxsmztjjsmx.xls");
			ExportExcel.exportExcel(year+"年"+month+"月  大学生 门诊统筹结算明细单", titles, values, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("<script>alert('导出 门诊统筹结算明细单失败！')</script>");
		}
		
		return null;
	}
}
