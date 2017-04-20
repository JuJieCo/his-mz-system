package com.jujie.his.baseinfo.action;

import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.Standard;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.util.page.Page;

public class StandardAction  extends BaseActionSupper{

private static final long serialVersionUID = 1L;
	
	private Page page;
	private String s_token;
	private  List<Standard> standardList;
	private  Standard standard;
	
	
	

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


	public List<Standard> getStandardList() {
		return standardList;
	}


	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}


	public Standard getStandard() {
		return standard;
	}


	public void setStandard(Standard standard) {
		this.standard = standard;
	}


	// 得到规格列表
	public String showStandard(){
		if(page==null){
			page = new Page(1);
		}
	BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
	try {
		standardList = baseInfoServer.queryStandardList(page);
	} catch (Exception e) {
		e.printStackTrace();
	}
		return "standard";
	}
		
		
		//创建/修改规格
		
		public String editStandard(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String tmp = request.getParameter("isUpdate");
			
			if(null!=tmp&&!"".equals(tmp)&&"isUpdate".equals(tmp)){
				try {
					baseInfoServer.updateStandard(standard,"noStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					standard.setStandardStatue(1);
					baseInfoServer.addStandard(standard);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return this.showStandard();
		}
		
		
		//修改状态
		public String updateStandardStatue(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			
			String standardId = request.getParameter("standardId");
			try {
				standard = baseInfoServer.queryStandard(standardId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				if(standard.getStandardStatue()==0){
					standard.setStandardStatue(1);
				}else{
					standard.setStandardStatue(0);
				}	
				try {
					
					baseInfoServer.updateStandard(standard,"isStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}

			
				standard.setStandardId(null);
			return this.showStandard();
		}
		
		
		//显示要修改的
		public String showUpdateStandard(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String standardId = request.getParameter("standardId");
			try {
				standard = baseInfoServer.queryStandard(standardId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("isUpdate", "isUpdate");
			
			return this.showStandard();
		}
		
		
		//删除规格
		public String deleteStandard(){
			String[] standardIds =new String[1] ;
			standardIds[0] = request.getParameter("standardId");
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			try {
				baseInfoServer.deleteStandard(standardIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.showStandard();
		}
}
