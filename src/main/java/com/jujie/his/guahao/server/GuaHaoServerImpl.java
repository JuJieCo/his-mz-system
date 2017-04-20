package com.jujie.his.guahao.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jujie.his.baseinfo.Dept;
import com.jujie.his.baseinfo.DoctorDept;
import com.jujie.his.guahao.GHInfo;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.GHSit;
import com.jujie.his.guahao.Sick;
import com.jujie.his.guahao.dao.GHOrderDAOImpl;
import com.jujie.his.guahao.dao.GHSitDAOImpl;
import com.jujie.util.page.Page;

public class GuaHaoServerImpl {
	
	private GHSitDAOImpl ghsitDAOImpl;
	private GHOrderDAOImpl ghorderDAOImpl;
	
	public void setGhsitDAOImpl(GHSitDAOImpl ghsitDAOImpl) {
		this.ghsitDAOImpl = ghsitDAOImpl;
	}
	public void setGhorderDAOImpl(GHOrderDAOImpl ghorderDAOImpl) {
		this.ghorderDAOImpl = ghorderDAOImpl;
	}


	public Map<String,Object> queryGHSitList(Page page)throws Exception{	
		//获得挂号预设信息列表
		List<GHSit>ghtsitList = ghsitDAOImpl.queryGHSitList(page);
		//获得科室列表
		List<Dept> deptList = ghsitDAOImpl.queryDeptList();
		//获得科室对应的医生列表
		List<DoctorDept> ddList = ghsitDAOImpl.queryDoctorDept();
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("ghtsitList", ghtsitList);
		map.put("deptList", deptList);
		map.put("ddList", ddList);
		
		return map;
	}
	
	public Integer saveGHSit(GHSit ghsit) throws Exception {
		return ghsitDAOImpl.saveGHSit(ghsit);
	}
	
	public GHSit queryGHSitByID(String  ghsitId) throws Exception{
		return  ghsitDAOImpl.queryGHSitByID(ghsitId);
	}
	public void updateGHSit(GHSit ghsit) throws Exception{
		ghsitDAOImpl.updateGHSit(ghsit);
	}
	
	public void deleteGHSit(String[] ghsitId) throws Exception{
		ghsitDAOImpl.deleteGHSit(ghsitId);
	}
	
	
	public Map<String,Object> queryGHSitListForOrder(Page page)throws Exception{	
		
		//获得挂号预设表列表
		List<GHSit>ghtsitList = ghorderDAOImpl.queryGHSitList();
		//获得挂号信息表列表
		List<GHOrder> ghordeList = ghorderDAOImpl.queryGHOrder(page);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ghtsitList", ghtsitList);
		map.put("ghordeList", ghordeList);
		
		return map;
	}
	/**
	 * 9 退号
	 * 5 发票报废
	 * 4 诊疗
	 * 3 缴费
     * 2 取药
     * 1 离院
     * 0 未诊疗 
	 * 
	 */
	public void saveGHOrder(Sick sick , String ghsitId, GHInfo ghinfo )throws Exception{
		
		//保存病人信息
		int pks = ghorderDAOImpl.saveSick(sick);//返回病人主键
		
		//保存就诊号信息
		GHOrder ghorder = new GHOrder();
		ghorder.getSick().setSickId(pks);
		ghorder.getGhsit().setGhsitId(Integer.valueOf(ghsitId));
		ghorder.setOrderIndex(1);//生成就诊序号??????????????????????????
		ghorder.setOrderStatue(0);//设置就诊号状态 0 未诊疗
		int pko = ghorderDAOImpl.saveGHOrder(ghorder);//返回就诊号主键
		
		//保存挂号信息
		ghinfo.setOrderId(pko);//设置挂号信息主键
		ghorderDAOImpl.saveGHInfo(ghinfo);
	}
	
	
	//操作限号
	public void operatGHSit(String ghsitId ,String ampm ) throws Exception{
		ghorderDAOImpl.operatGHSit(ghsitId, ampm);
	}
	
	//退号 置状态  不删除记录 只改变状态
	public void deleteGHOrder(String orderId, String ghsitId , String ampm) throws Exception{
		//先查询状态是否为未诊疗再做操作
		GHOrder ghorderT = ghorderDAOImpl.queryGHOrderByID(Integer.valueOf(orderId));
		if(ghorderT.getOrderStatue()==0){// 0 为未诊疗状态
			GHOrder ghorder = new GHOrder();
			ghorder.setOrderId(Integer.valueOf(orderId));
			ghorder.setOrderStatue(9);//设置就诊号状态 9 已退号
			ghorderDAOImpl.deleteGHOrder(ghorder);
			ghorderDAOImpl.reOperatGHSit(ghsitId, ampm);//返还退号
		}
	}

}
