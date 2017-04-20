package com.jujie.his.baseinfo.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.Medicinal;
import com.jujie.his.baseinfo.Standard;
import com.jujie.his.baseinfo.Unit;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.util.page.Page;

public class MedicinalAction  extends BaseActionSupper{
	
private static final long serialVersionUID = 1L;
	
	private Page page;
	private String s_token;
	private  List<Medicinal> medicinalList;
	private List<Standard> standardList ;
	private List<Unit>  unitList  ;
	private  Medicinal medicinal;

		public List<Standard> getStandardList() {
		return standardList;
	}


	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}


	public List<Unit> getUnitList() {
		return unitList;
	}


	public void setUnitList(List<Unit> unitList) {
		this.unitList = unitList;
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


	public List<Medicinal> getMedicinalList() {
		return medicinalList;
	}


	public void setMedicinalList(List<Medicinal> medicinalList) {
		this.medicinalList = medicinalList;
	}


	public Medicinal getMedicinal() {
		return medicinal;
	}


	public void setMedicinal(Medicinal medicinal) {
		this.medicinal = medicinal;
	}


		// 得到药品列表
		@SuppressWarnings("unchecked")
		public String showMedicinal(){
			
			if(page==null){
				page = new Page(1);
			}
		BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		
		String medicinalName = request.getParameter("medicinalName");
		
		
				try {
					if(medicinalName==null){
						Object[] objs = new Object[] {null};
						Map<String, Object>   map =  baseInfoServer.queryMedicinalList(objs, page);
						 medicinalList = (List<Medicinal>)map.get("medicinalList");
						 unitList = (List<Unit>)map.get("unitList");
						 standardList = (List<Standard>)map.get("standardList");
					}else{
						Object[] objs = new Object[] {medicinalName};
						Map<String, Object>   map =  baseInfoServer.queryMedicinalList(objs, page);
						 medicinalList = (List<Medicinal>)map.get("medicinalList");
						 unitList = (List<Unit>)map.get("unitList");
						 standardList = (List<Standard>)map.get("standardList");
						
						 //130306  
						 request.setAttribute("medicinalName", medicinalName);
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
		
			 
	
		return "medicinal";
	}
		
		
		//创建/修改药品
		
	
		public String editMedicinal(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			
		
			
			String tmp = request.getParameter("isUpdate");
			
			if(null!=tmp&&!"".equals(tmp)&&"isUpdate".equals(tmp)){
				try {
					baseInfoServer.updateMedicinal(medicinal,"noStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					
					medicinal.setMedicinalStatue(1);
					baseInfoServer.addMedicinal(medicinal);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return this.showMedicinal();
		}
		
		
		//修改状态
		public String updateMedicinalStatue(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			
			String medicinalId = request.getParameter("medicinalId");
			try {
				medicinal = baseInfoServer.queryMedicinal(medicinalId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				if(medicinal.getMedicinalStatue()==0){
					medicinal.setMedicinalStatue(1);
				}else{
					medicinal.setMedicinalStatue(0);
				}	
				try {
					
					baseInfoServer.updateMedicinal(medicinal,"isStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}

				 //130306 
				request.setAttribute("medicinalName", medicinal.getMedicinalName());
				 
				
				 
				 
				 
				medicinal.setMedicinalId(null);
			return this.showMedicinal();
		}
		
		
		//显示要修改的
		public String showUpdateMedicinal(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String medicinalId = request.getParameter("medicinalId");
			try {
				medicinal = baseInfoServer.queryMedicinal(medicinalId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("medicinalName", medicinal.getMedicinalName());
			request.setAttribute("isUpdate", "isUpdate");
			
			return this.showMedicinal();
		}
		
		
		//删除药品
		public String deleteMedicinal(){
			String[] medicinalIds =new String[1] ;
			medicinalIds[0] = request.getParameter("medicinalId");
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			try {
				baseInfoServer.deleteMedicinal(medicinalIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.showMedicinal();
		}
		
		
		


}
