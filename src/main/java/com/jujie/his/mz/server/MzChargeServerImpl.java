package com.jujie.his.mz.server;

import java.util.HashMap;
import java.util.List;

import com.jujie.his.guahao.GHOrder;
import com.jujie.his.inventory.HisMedicinalInventory;
import com.jujie.his.mz.Fee_Type;
import com.jujie.his.mz.HisInventoryFy;
import com.jujie.his.mz.MzCharge;
import com.jujie.his.mz.MzHj;
import com.jujie.his.mz.MzYp;
import com.jujie.his.mz.dao.MzChargeDaoImpl;
import com.jujie.util.page.Page;

public class MzChargeServerImpl {
	
	private MzChargeDaoImpl mzChargeDaoImpl;

	 
	public void setmzChargeDaoImpl(MzChargeDaoImpl mzChargeDaoImpl){
		this.mzChargeDaoImpl = mzChargeDaoImpl;
	}
	
	public List<GHOrder> queryGhOrderList(Object[] objs,Page page) throws Exception{
		 return mzChargeDaoImpl.queryGhOrderList(objs,page);
			
		}
   //收费列表	
	public List<GHOrder> queryAllSfList(Object[] objs,Page page) throws Exception{
		 return mzChargeDaoImpl.queryAllSfList(objs,page);
			
		}
	//诊疗收费列表	
	public List<GHOrder> queryAllZlSfList(Object[] objs,Page page) throws Exception{
		 return mzChargeDaoImpl.queryAllZlSfList(objs,page);
			
		}
	
	public void addMzCharge(MzCharge mzCharge,List<MzYp> mzYpList,String orderid) throws Exception {
	    mzChargeDaoImpl.addMzCharge(mzCharge);
	    mzChargeDaoImpl.updateMzYP(mzYpList);
	    mzChargeDaoImpl.updateMzZl(2,mzCharge.getUuid());
	    mzChargeDaoImpl.updateMzOrder(7,Integer.valueOf(orderid));
	} 
   //收费
	public void addMzSf(MzCharge mzCharge,Integer orderid) throws Exception {
	   mzChargeDaoImpl.updateMzCharge(mzCharge);
	   mzChargeDaoImpl.updateMzOrder(5,orderid);
	} 
	public  MzCharge findMzChargeByID(Integer order_id) throws Exception {
	 	return mzChargeDaoImpl.findMzChargeByID(order_id);
 	}
	public GHOrder findMzChargeByID(String order_id) throws Exception {
	 	return mzChargeDaoImpl.findMzChargeByID(order_id);
 	}
	 //诊疗划价
	public GHOrder queryZlHjByID(String sickCasehistory) throws Exception {
	 	return mzChargeDaoImpl.findMzChargeByID(sickCasehistory);
 	}
	
	public List<MzCharge> findAllMzCharge(Object[] objs,Page page) throws Exception {
	    return mzChargeDaoImpl.findAllMzCharge(objs, page);
	}
 	//药品发放
	public void addYpff(HisInventoryFy hisInventoryFy,List<HisMedicinalInventory> medicinalInventoryList) throws Exception {
	    mzChargeDaoImpl.addYpff(hisInventoryFy);
		mzChargeDaoImpl.updateMzOrder(2,hisInventoryFy.getOrder_id());
 	} 
	//添加诊疗划价
	public void addZlHj(Fee_Type feetypebean)throws Exception{
		mzChargeDaoImpl.addZlHj(feetypebean);
	}
	//诊疗收费
	public void updateZlSf(Fee_Type feetypebean) throws Exception {
	 mzChargeDaoImpl.updateZlSf(feetypebean);
	}
	/****************************************查询、 退费 start***********************************************************/
	public List<Fee_Type> queryFeeType(HashMap<Object,Object> condition)throws Exception{
		return mzChargeDaoImpl.queryFeeType(condition);
	}
	public List<MzCharge> queryMzCharge(HashMap<Object,Object> condition)throws Exception{
		return mzChargeDaoImpl.queryMzCharge(condition);
	}
	public void modifyFeeType(Fee_Type feetypebean)throws Exception{
		mzChargeDaoImpl.modifyFeeType(feetypebean);
	}
	public void modifyHjInfo(MzCharge hjinfoBean)throws Exception{
		mzChargeDaoImpl.modifyHjInfo(hjinfoBean);
	}
	/****************************************查询、 退费 end***********************************************************/
	

}
