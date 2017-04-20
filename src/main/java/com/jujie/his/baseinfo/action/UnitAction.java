package com.jujie.his.baseinfo.action;

import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.Unit;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.util.page.Page;

public class UnitAction  extends BaseActionSupper{

private static final long serialVersionUID = 1L;
	
	private Page page;
	private String s_token;
	private  List<Unit> unitList;
	private  Unit unit;
	
	
	

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


	public List<Unit> getUnitList() {
		return unitList;
	}


	public void setUnitList(List<Unit> unitList) {
		this.unitList = unitList;
	}


	public Unit getUnit() {
		return unit;
	}


	public void setUnit(Unit unit) {
		this.unit = unit;
	}


		// 得到单位列表
		public String showUnit(){
			if(page==null){
				page = new Page(1);
			}
		BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		try {
			unitList = baseInfoServer.queryUnitList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "unit";
	}
		
		
		//创建/修改单位
		
		public String editUnit(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String tmp = request.getParameter("isUpdate");
			
			if(null!=tmp&&!"".equals(tmp)&&"isUpdate".equals(tmp)){
				try {
					baseInfoServer.updateUnit(unit,"noStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					unit.setUnitStatue(1);
					baseInfoServer.addUnit(unit);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return this.showUnit();
		}
		
		
		//修改状态
		public String updateUnitStatue(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			
			String unitId = request.getParameter("unitId");
			try {
				unit = baseInfoServer.queryUnit(unitId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				if(unit.getUnitStatue()==0){
					unit.setUnitStatue(1);
				}else{
					unit.setUnitStatue(0);
				}	
				try {
					
					baseInfoServer.updateUnit(unit,"isStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}

			
				unit.setUnitId(null);
			return this.showUnit();
		}
		
		
		//显示要修改的
		public String showUpdateUnit(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String unitId = request.getParameter("unitId");
			try {
				unit = baseInfoServer.queryUnit(unitId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("isUpdate", "isUpdate");
			
			return this.showUnit();
		}
		
		
		//删除单位
		public String deleteUnit(){
			String[] unitIds =new String[1] ;
			unitIds[0] = request.getParameter("unitId");
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			try {
				baseInfoServer.deleteUnit(unitIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.showUnit();
		}
}
