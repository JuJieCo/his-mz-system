package com.jujie.his.baseinfo.action;

import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.util.page.Page;

public class CompanyAction  extends BaseActionSupper{
	
private static final long serialVersionUID = 1L;
	
	private Page page;
	private String s_token;
	private  List<Company> companyList;
	private  Company company;
	
	
	

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


	public List<Company> getCompanyList() {
		return companyList;
	}


	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


		// 得到药品供应公司列表
		public String showCompany(){
			if(page==null){
				page = new Page(1);
			}
		BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		try {
			companyList = baseInfoServer.queryCompanyList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "company";
	}
		
		
		//创建/修改药品供应公司
		
		public String editCompany(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String tmp = request.getParameter("isUpdate");
			
			if(null!=tmp&&!"".equals(tmp)&&"isUpdate".equals(tmp)){
				try {
					baseInfoServer.updateCompany(company,"noStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					company.setCompanyStatue(1);
					baseInfoServer.addCompany(company);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return this.showCompany();
		}
		
		
		//修改状态
		public String updateCompanyStatue(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			
			String companyId = request.getParameter("companyId");
			try {
				company = baseInfoServer.queryCompany(companyId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				if(company.getCompanyStatue()==0){
					company.setCompanyStatue(1);
				}else{
					company.setCompanyStatue(0);
				}	
				try {
					
					baseInfoServer.updateCompany(company,"isStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}

			
				company.setCompanyId(null);
			return this.showCompany();
		}
		
		
		//显示要修改的
		public String showUpdateCompany(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String companyId = request.getParameter("companyId");
			try {
				company = baseInfoServer.queryCompany(companyId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("isUpdate", "isUpdate");
			
			return this.showCompany();
		}
		
		
		//删除药品供应公司
		public String deleteCompany(){
			String[] companyIds =new String[1] ;
			companyIds[0] = request.getParameter("companyId");
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			try {
				baseInfoServer.deleteCompany(companyIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.showCompany();
		}

}
