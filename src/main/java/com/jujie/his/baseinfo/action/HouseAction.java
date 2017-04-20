package com.jujie.his.baseinfo.action;

import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.House;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.util.page.Page;

public class HouseAction  extends BaseActionSupper{

private static final long serialVersionUID = 1L;
	
	private Page page;
	private String s_token;
	private  List<House> houseList;
	private  House house;
	
	
	

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


	public List<House> getHouseList() {
		return houseList;
	}


	public void setHouseList(List<House> houseList) {
		this.houseList = houseList;
	}


	public House getHouse() {
		return house;
	}


	public void setHouse(House house) {
		this.house = house;
	}


		// 得到药房列表
		public String showHouse(){
			if(page==null){
				page = new Page(1);
			}
		BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		try {
			houseList = baseInfoServer.queryHouseList(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "house";
	}
		
		
		//创建/修改药房
		
		public String editHouse(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String tmp = request.getParameter("isUpdate");
			
			if(null!=tmp&&!"".equals(tmp)&&"isUpdate".equals(tmp)){
				try {
					baseInfoServer.updateHouse(house,"noStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				try {
					house.setHouseStatue(1);
					baseInfoServer.addHouse(house);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return this.showHouse();
		}
		
		
		//修改状态
		public String updateHouseStatue(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			
			String houseId = request.getParameter("houseId");
			try {
				house = baseInfoServer.queryHouse(houseId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				if(house.getHouseStatue()==0){
					house.setHouseStatue(1);
				}else{
					house.setHouseStatue(0);
				}	
				try {
					
					baseInfoServer.updateHouse(house,"isStatue");
				} catch (Exception e) {
					e.printStackTrace();
				}

			
				house.setHouseId(null);
			return this.showHouse();
		}
		
		
		//显示要修改的
		public String showUpdateHouse(){
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			String houseId = request.getParameter("houseId");
			try {
				house = baseInfoServer.queryHouse(houseId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("isUpdate", "isUpdate");
			
			return this.showHouse();
		}
		
		
		//删除药房
		public String deleteHouse(){
			String[] houseIds =new String[1] ;
			houseIds[0] = request.getParameter("houseId");
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			try {
				baseInfoServer.deleteHouse(houseIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.showHouse();
		}
}
