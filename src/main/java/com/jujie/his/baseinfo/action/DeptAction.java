package com.jujie.his.baseinfo.action;


import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.Dept;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.util.page.Page;

public class DeptAction  extends BaseActionSupper{
	
	
	private static final long serialVersionUID = 1L;
	
	private Page page;
	private String s_token;
	private  List<Dept> deptList;
	private  Dept dept;
	
	
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

	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}

	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	

		// 得到科室列表
		public String showDept(){
			
			if(page==null){
				page = new Page(1);
			}
			
		BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		try {
			deptList = baseInfoServer.queryDeptList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dept";
	}
		
		
		//创建/修改科室
		
		public String editDept(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String tmp = request.getParameter("isUpdate");
			
			if(null!=tmp&&!"".equals(tmp)&&"isUpdate".equals(tmp)){
				try {
					baseInfoServer.updateDept(dept,"noStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					dept.setDeptStatue(1);
					baseInfoServer.addDept(dept);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return this.showDept();
		}
		
		
		//修改状态
		public String updateDeptStatue(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			
			String deptId = request.getParameter("deptId");
			try {
				dept = baseInfoServer.queryDept(deptId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				if(dept.getDeptStatue()==0){
					dept.setDeptStatue(1);
				}else{
					dept.setDeptStatue(0);
				}	
				try {
					
					baseInfoServer.updateDept(dept,"isStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}

			
				dept.setDeptId(null);
			return this.showDept();
		}
		
		
		//显示要修改的
		public String showUpdateDept(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String deptId = request.getParameter("deptId");
			try {
				dept = baseInfoServer.queryDept(deptId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("isUpdate", "isUpdate");
			
			return this.showDept();
		}
		
		
		//删除科室
		public String deleteDept(){
			String[] deptIds =new String[1] ;
			deptIds[0] = request.getParameter("deptId");
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			try {
				baseInfoServer.deleteDept(deptIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.showDept();
		}

}
