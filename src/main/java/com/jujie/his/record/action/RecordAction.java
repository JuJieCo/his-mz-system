package com.jujie.his.record.action;

import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.guahao.Sick;
import com.jujie.his.record.server.RecordServerrImp;
import com.jujie.util.page.Page;

@SuppressWarnings("serial")
public class RecordAction extends BaseActionSupper {

	private Sick sick;
    private Page page;
    private List<Sick> sickList;
	
	public List<Sick> getSickList() {
		return sickList;
	}
	public void setSickList(List<Sick> sickList) {
		this.sickList = sickList;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Sick getSick() {
		return sick;
	}
	public void setSick(Sick sick) {
		this.sick = sick;
	}
	
	
	
	
	/**
	 *查询病人基本信息 
	 * 
	 */
	public String querySickInfo() {
		if(page==null){
			page = new Page(1);
		}
		RecordServerrImp recordServerrImp = (RecordServerrImp) this.getService("recordServer");
		String sTime = request.getParameter("sTime");
		String eTime = request.getParameter("eTime");
		try {
			if(sick==null){
				Object[] objs = new Object[] {null,null,null};
				sickList = recordServerrImp.queryAllSickInfo(objs, page);
			}else{
				Object[] objs = new Object[] {sTime,eTime,sick.getSickName()};
				sickList = recordServerrImp.queryAllSickInfo(objs, page);
				request.setAttribute("sTime", sTime);
				request.setAttribute("eTime", eTime);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sickinfo";
	}
	
	
	/**
	 *查询病人就诊信息 
	 * 
	 */
	public String queryTreatInfo() {
		
		
		//  his_mz_zlinfo  病历 zlinfo_content  处方？
		
		
		return "treatinfo";
	}

}
