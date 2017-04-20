package com.jujie.his.medicare.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.medicare.CitizenMedDE;
import com.jujie.his.medicare.CitizenMedDE205;
import com.jujie.his.medicare.server.MedicareServerImpl;
import com.jujie.util.page.Page;

public class CitizenMedAction extends BaseActionSupper {
	private static final long serialVersionUID = -6663602421623975646L;

	private Page page;
	private CitizenMedDE citizenMedDE;
	private List<CitizenMedDE> citizenMedList;
	
	//PDFList
	private List<CitizenMedDE> citizenMedpdfList;
	
	//205BEAN
	private CitizenMedDE205 citizenMedDE205;
	//205List
	private List<CitizenMedDE205> citizenMed205pdfList;

	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public CitizenMedDE getCitizenMedDE() {
		return citizenMedDE;
	}
	public void setCitizenMedDE(CitizenMedDE citizenMedDE) {
		this.citizenMedDE = citizenMedDE;
	}
	public List<CitizenMedDE> getCitizenMedList() {
		return citizenMedList;
	}
	public void setCitizenMedList(List<CitizenMedDE> citizenMedList) {
		this.citizenMedList = citizenMedList;
	}
	public List<CitizenMedDE> getCitizenMedpdfList() {
		return citizenMedpdfList;
	}
	public void setCitizenMedpdfList(List<CitizenMedDE> citizenMedpdfList) {
		this.citizenMedpdfList = citizenMedpdfList;
	}
	public CitizenMedDE205 getCitizenMedDE205() {
		return citizenMedDE205;
	}
	public void setCitizenMedDE205(CitizenMedDE205 citizenMedDE205) {
		this.citizenMedDE205 = citizenMedDE205;
	}
	public List<CitizenMedDE205> getCitizenMed205pdfList() {
		return citizenMed205pdfList;
	}
	public void setCitizenMed205pdfList(List<CitizenMedDE205> citizenMed205pdfList) {
		this.citizenMed205pdfList = citizenMed205pdfList;
	}
	
	


	
	//居民定额信息列表
	public String queryCitizenMedDEList() {
		if (page == null) {
			page = new Page(1);
		}
		MedicareServerImpl medicareServerImpl = (MedicareServerImpl) this.getService("medicareServer");
		String ybcode = request.getParameter("citizenMedDE.ybcode");
		try {
			Object[] objs = new Object[] {ybcode,null};
			citizenMedList = medicareServerImpl.queryCitizenMedDEList(objs,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showList";
	}
	
	
	//居民定额信息新增页面
	public String showCitizenMedDEAdd() {
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR) ;
		request.setAttribute("year", year);
		request.setAttribute("operateType", "add");
		citizenMedDE = new CitizenMedDE();
		return "showEdit";
	}
	
	//居民定额信息修改页面
	public String showCitizenMedDEUpdate(){
		MedicareServerImpl medicareServerImpl = (MedicareServerImpl) this.getService("medicareServer");
		try {
			String citizenId = request.getParameter("citizenId");
			citizenMedDE=medicareServerImpl.queryCitizenMedDEByID(String.valueOf(citizenId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR) ;
		request.setAttribute("year", year);
		request.setAttribute("operateType", "update");
		return "showEdit";
	}
	
	//居民定额信息编辑
	public String citizenMedDEEdit() {
		MedicareServerImpl medicareServerImpl = (MedicareServerImpl) this.getService("medicareServer");
		try {
			String operateType = request.getParameter("operateType");
			medicareServerImpl.citizenMedDEEdit(citizenMedDE,operateType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.queryCitizenMedDEList();
	}
	
	/**
	 * ////////////////////////////////////////////////////////////////////////////////////////////////////////
	 */
	
	//预览审核单页面
	public String queryCitizenMedDESHD(){
		MedicareServerImpl medicareServerImpl = (MedicareServerImpl) this.getService("medicareServer");
		
		String citizenId = request.getParameter("citizenId");
		try {
			citizenMedDE=medicareServerImpl.queryCitizenMedDEByID(String.valueOf(citizenId));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "showDESHD";
	}
	
	
	//预览审核单PDF
	public String printCitizenMedDESHD(){
		citizenMedpdfList= new ArrayList<CitizenMedDE>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String printDate = sdf.format(new Date());
		citizenMedDE.setOpertime(printDate.toString());
		citizenMedpdfList.add(citizenMedDE);
		return SUCCESS;
	}
	

	//预览结算表页面
	public String queryCitizenMedDEJSB(){
		MedicareServerImpl medicareServerImpl = (MedicareServerImpl) this.getService("medicareServer");
		
		String citizenId = request.getParameter("citizenId");
		try {
			citizenMedDE=medicareServerImpl.queryCitizenMedDEByID(String.valueOf(citizenId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showEDJSB";
	}
	
	
	//预览结算表PDF
	public String printCitizenMedDEJSB(){
		citizenMedpdfList= new ArrayList<CitizenMedDE>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String printDate = sdf.format(new Date());
		citizenMedDE.setOpertime(printDate.toString());
		citizenMedpdfList.add(citizenMedDE);
		return SUCCESS;
	}
	
	
	//204表列表页面
	public String queryCitizenMedDE204(){
		if (page == null) {
			page = new Page(1);
		}
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR) ;
		int month = rightNow.get(Calendar.MONTH)+1;
		
		MedicareServerImpl medicareServerImpl = (MedicareServerImpl) this.getService("medicareServer");
		String balancedate = request.getParameter("balancedate");
		if(null!=balancedate&&!"".equals(balancedate)){
			String monthT = balancedate.substring(5, balancedate.length()-1);
			month = Integer.valueOf(monthT);
		}
		try {
			Object[] objs = new Object[] {null,balancedate};
			citizenMedList = medicareServerImpl.queryCitizenMedDEList(objs,page);
		} catch (Exception e) {

			e.printStackTrace();
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		return "showED204";
	}
	
	
	//预览204表pdf
	public String printCitizenMedDE204(){
		
		return SUCCESS;
	}
	

	//205表页面
	public String queryCitizenMedDE205(){
		
		Calendar rightNow = Calendar.getInstance();
		int year = rightNow.get(Calendar.YEAR) ;
		int month = rightNow.get(Calendar.MONTH)+1;
		
		MedicareServerImpl medicareServerImpl = (MedicareServerImpl) this.getService("medicareServer");
		String balancedate = request.getParameter("balancedate");
		if(null!=balancedate&&!"".equals(balancedate)){
			String monthT = balancedate.substring(5, balancedate.length()-1);
			month = Integer.valueOf(monthT);
		}
		try {
			citizenMedDE205 = medicareServerImpl.querycitizenMedDE205(balancedate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		return "showED205";
	}
	
	
	//预览205表PDF
	public String printCitizenMedDE205(){
		MedicareServerImpl medicareServerImpl = (MedicareServerImpl) this.getService("medicareServer");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String balancedate = "";
		if(null!=year&&!"".equals(year)&&null!=month&&!"".equals(month)){
			balancedate = year+"年"+month+"月";
			citizenMed205pdfList= new ArrayList<CitizenMedDE205>();
			try {
				citizenMedDE205 = medicareServerImpl.querycitizenMedDE205(balancedate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			citizenMed205pdfList.add(citizenMedDE205);
		}
		return SUCCESS;
	}
	

	

}
