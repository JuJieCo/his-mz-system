package com.jujie.his.guahao.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.guahao.GHInfo;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.GHSit;
import com.jujie.his.guahao.Sick;
import com.jujie.his.guahao.server.GuaHaoServerImpl;
import com.jujie.util.page.Page;

@SuppressWarnings("serial")
public class GHOrderAction extends BaseActionSupper {
		
	private List<GHSit> ghsitList;
	private List<GHOrder> ghordeList;
	private Sick sick;
	private GHInfo ghinfo;
    private Page page;
    
    
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<GHOrder> getGhordeList() {
		return ghordeList;
	}
	public void setGhordeList(List<GHOrder> ghordeList) {
		this.ghordeList = ghordeList;
	}
	public GHInfo getGhinfo() {
		return ghinfo;
	}
	public void setGhinfo(GHInfo ghinfo) {
		this.ghinfo = ghinfo;
	}
	public Sick getSick() {
		return sick;
	}
	public void setSick(Sick sick) {
		this.sick = sick;
	}
	public List<GHSit> getGhsitList() {
		return ghsitList;
	}
	public void setGhsitList(List<GHSit> ghsitList) {
		this.ghsitList = ghsitList;
	}


	/**
	 * 显示挂号页面
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String showGHOrder(){
		if(page==null){
			page = new Page(1);
		}
		GuaHaoServerImpl guaHaoServerImpl = (GuaHaoServerImpl) this.getService("ghServer");
		Map<String,Object> map = new HashMap<String,Object>();	
		try {
			map = guaHaoServerImpl.queryGHSitListForOrder(page);
			//获得挂号预设表列表
			ghsitList = (List<GHSit>)map.get("ghtsitList");
			//获得挂号信息表列表
			ghordeList = (List<GHOrder>)map.get("ghordeList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		String nowHour = sdf.format(new Date()); //当前时间 小时
		int noeHourInt = Integer.valueOf(nowHour);
	
		if(0<=noeHourInt&&noeHourInt<12){
			request.setAttribute("ampm", 0);
		}
		if(12<=noeHourInt&&noeHourInt<24){
			request.setAttribute("ampm", 1);
		}
	
		return "ghorder";
	}
	
	/**
	 * 保存挂号信息
	 * 
	 */
	public String saveGHOrder(){
		GuaHaoServerImpl guaHaoServerImpl = (GuaHaoServerImpl) this.getService("ghServer");
		try {
			
			//挂号信息表
			ghinfo.setSysUserId(1);//操作员取系统登录用户
			ghinfo.setGhinfoDoTime(new Date());//挂号时间默认为系统时间
			
			//就诊号表中插入挂号预设表外键
			 String ghsitId = request.getParameter("ghsitid");//选取的号表
			 
			 guaHaoServerImpl.saveGHOrder(sick  , ghsitId ,ghinfo);
			
			//操作限号
			SimpleDateFormat sdf = new SimpleDateFormat("HH");
			
			String nowHour = sdf.format(new Date()); //当前时间 小时
			int noeHourInt = Integer.valueOf(nowHour);
			String tmp ="";
			if(0<=noeHourInt&&noeHourInt<12){
				 tmp = "am";
			}else if(12<=noeHourInt&&noeHourInt<24){
				tmp="pm";
			}
			if("am".equals(tmp)||"pm".equals(tmp)){
				guaHaoServerImpl.operatGHSit(ghsitId, tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.showGHOrder();
	}
	
	
	/**
	 * 退号操作 
	 * 不删除记录 只改变状态
	 */
	public String deleteGHOrder(){
		GuaHaoServerImpl guaHaoServerImpl = (GuaHaoServerImpl) this.getService("ghServer");
		String orderId = request.getParameter("orderId");
		String ghsitId = request.getParameter("ghsitId");
		try {
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH");
			
			String nowHour = sdf.format(new Date()); //当前时间 小时
			int noeHourInt = Integer.valueOf(nowHour);
			String tmp ="";
			if(0<=noeHourInt&&noeHourInt<12){
				 tmp = "am";
			}else if(12<=noeHourInt&&noeHourInt<24){
				tmp="pm";
			}
			if("am".equals(tmp)||"pm".equals(tmp)){
				guaHaoServerImpl.deleteGHOrder(orderId, ghsitId , tmp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.showGHOrder();
	}

}
