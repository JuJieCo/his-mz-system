package com.jujie.his.baseinfo.action;

import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.Feetype;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.util.page.Page;

public class FeetypeAction  extends BaseActionSupper{

private static final long serialVersionUID = 1L;
	
	private Page page;
	private String s_token;
	private  List<Feetype> feetypeList;
	private  Feetype feetype;
	
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

 

	public List<Feetype> getFeetypeList() {
		return feetypeList;
	}


	public void setFeetypeList(List<Feetype> feetypeList) {
		this.feetypeList = feetypeList;
	}


	public Feetype getFeetype() {
		return feetype;
	}


	public void setFeetype(Feetype feetype) {
		this.feetype = feetype;
	}

    public String showFeetype(){
			if(page==null){
				page = new Page(1);
			}
    BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		try {
			feetypeList = baseInfoServer.queryFeetypeList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "feetype";
	}
		
		
//		//创建/修改单位
//		
//		public String editFeetype(){
//			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
//			String tmp = request.getParameter("isUpdate");
//			
//			if(null!=tmp&&!"".equals(tmp)&&"isUpdate".equals(tmp)){
//				try {
//					baseInfoServer.updateFeetype(Feetype,"noStatue");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}else{
//				try {
//					Feetype.setFeetypeStatue(1);
//					baseInfoServer.addFeetype(Feetype);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			return this.showFeetype();
//		}
//		
		
		//修改状态
		public String updateFeetypeStatue(){
		BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		
		String feetypeId = request.getParameter("feetypeId");
		try {
			feetype = baseInfoServer.queryFeetype(feetypeId);
		 	baseInfoServer.updateFeetype(feetype);
			} catch (Exception e) {
				e.printStackTrace();
			}
         return this.showFeetype();
		}
		
		
		//显示要修改的
		public String showUpdateFeetype(){
		BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		String feetypeId = request.getParameter("feetypeId");
		try {
			feetype = baseInfoServer.queryFeetype(feetypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	request.setAttribute("isUpdate", "isUpdate");
		
		return this.showFeetype();
		}
		
		
		//删除单位
		public String deleteFeetype(){
			String[] feetypeIds =new String[1] ;
			feetypeIds[0] = request.getParameter("feetypeId");
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			try {
				baseInfoServer.deleteFeetype(feetypeIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	return this.showFeetype();
		}
}
