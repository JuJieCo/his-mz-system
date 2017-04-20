package com.jujie.his.guahao.action;

import java.util.List;
import java.util.Map;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.Dept;
import com.jujie.his.baseinfo.DoctorDept;
import com.jujie.his.guahao.GHSit;
import com.jujie.his.guahao.server.GuaHaoServerImpl;
import com.jujie.util.page.Page;

@SuppressWarnings("serial")
public class GHSitAction extends BaseActionSupper {
	
	private String s_token;
	private GHSit ghsit;
	private List<GHSit> ghsitList;
	private List<Dept> deptList;
	private List<DoctorDept>ddList;
    private Page page;
    
    
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
	public GHSit getGhsit() {
		return ghsit;
	}
	public void setGhsit(GHSit ghsit) {
		this.ghsit = ghsit;
	}
	public List<GHSit> getGhsitList() {
		return ghsitList;
	}
	public void setGhsitList(List<GHSit> ghsitList) {
		this.ghsitList = ghsitList;
	}
	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}
	public List<DoctorDept> getDdList() {
		return ddList;
	}
	public void setDdList(List<DoctorDept> ddList) {
		this.ddList = ddList;
	}
	
	
	/**
	 *显示挂号预设页面 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String  showGHSit(){
		if(page==null){
			page = new Page(1);
		}
		GuaHaoServerImpl guaHaoServerImpl = (GuaHaoServerImpl) this.getService("ghServer");
		try {
			 Map<String,Object> map = guaHaoServerImpl.queryGHSitList(page);
			 //获得挂号预设信息列表
			 ghsitList = (List<GHSit>)map.get("ghtsitList");
			//获得科室列表
			 deptList = (List<Dept>)map.get("deptList");	
			 //获得科室对应的医生列表
			 ddList = (List<DoctorDept>)map.get("ddList");	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ghsit";
	}
	
	/**
	 *修改and新增 
	 */
	public String editGHSit(){
		GuaHaoServerImpl guaHaoServerImpl = (GuaHaoServerImpl) this.getService("ghServer");
		String isUpdate = request.getParameter("isUpdate");
		if(null!=isUpdate&&!"".equals(isUpdate)&&"isUpdate".equals(isUpdate)){
			try {
				guaHaoServerImpl.updateGHSit(ghsit);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				ghsit.setGhsitId(null);
				guaHaoServerImpl.saveGHSit(ghsit);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.showGHSit();
	}
	
	/**
	 * 
	 *获得要修改的单条信息 
	 */
	public String showEditGHSit(){
		GuaHaoServerImpl guaHaoServerImpl = (GuaHaoServerImpl) this.getService("ghServer");
		try {
			ghsit = guaHaoServerImpl.queryGHSitByID(String.valueOf(ghsit.getGhsitId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("isUpdate", "isUpdate");
		return this.showGHSit();
	}
	
	/**
	 * 
	 * 删除单条
	 */
	public String deleteGHSit(){
		GuaHaoServerImpl guaHaoServerImpl = (GuaHaoServerImpl) this.getService("ghServer");
		String[] ghsitIds = new String[1]; 
		 ghsitIds[0] = request.getParameter("ghsitId");
		try {
			if(null!=ghsitIds[0]&&!"".equals(ghsitIds[0])){
				guaHaoServerImpl.deleteGHSit(ghsitIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.showGHSit();
	}
	


}
