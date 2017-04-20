package com.jujie.his.inventory.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.comparator.InvertibleComparator;

import com.jujie.his.inventory.HisInventoryBill;
import com.jujie.his.inventory.HisInventoryHistory;
import com.jujie.his.inventory.HisMedicinalInventory;
import com.jujie.his.inventory.dao.HisInventoryBillDaoImpl;
import com.jujie.his.inventory.dao.HisInventoryHistoryDaoImpl;
import com.jujie.his.inventory.dao.HisMedicinalInventoryDaoImpl;
import com.jujie.util.page.Page;
/**
 * 出入单
 * @author 
 *
 */
public class InventoryBillServer {
private HisInventoryBillDaoImpl inventoryBillDao;
private HisInventoryHistoryDaoImpl  inventoryHistoryDao;
private HisMedicinalInventoryDaoImpl medicinalDao;

public void setMedicinalDao(HisMedicinalInventoryDaoImpl medicinalDao) {
	this.medicinalDao = medicinalDao;
}
public void setInventoryHistoryDao(
		HisInventoryHistoryDaoImpl inventoryHistoryDao) {
	this.inventoryHistoryDao = inventoryHistoryDao;
}
public void setInventoryBillDao(HisInventoryBillDaoImpl inventoryBillDao) {
	this.inventoryBillDao = inventoryBillDao;
}
	/*入库单列表*/
	public List<HisInventoryBill> intoInventory(Object[] objs,Page page)throws Exception{
		return inventoryBillDao.queryAllInventoryBill(objs, page);
	}
	/*创建入库单*/
	public Integer addInventroy(HisInventoryBill inventorybillBean)throws Exception{
		/*1*/
	    Integer id=inventoryBillDao.addhisInventoryBill(inventorybillBean);
	    inventorybillBean.setInventoryBill_id(id);
		/*2*/
	    for(int i=0;i<inventorybillBean.getHistoryList().size();i++){
	    	HisInventoryHistory hisInventoryHistory = inventorybillBean.getHistoryList().get(i);
	    	if(hisInventoryHistory!=null){
	    		hisInventoryHistory.setInventoryBill(inventorybillBean);
		      	inventoryHistoryDao.addHisInventoryHistory(hisInventoryHistory);	
	    	}
	    }
	    return id;
	}
	/* 1. 入库2. 出库 3. 调拨  4. 回退*/
	public void addMedicinalInventory(int id,int type)throws Exception{
		 HisMedicinalInventory medicinalBean=null;
		 HisInventoryBill  bill=inventoryBillDao.queryOneInventoryBill(id);
		 List<HisInventoryHistory> historylist=inventoryHistoryDao.queryAllInventoryHistory(new Object[]{null,id,null,null});
		 if(bill.getInventoryBill_type()==type){
			 if(type==HisInventoryBill.TYPE_INTO){
				 bill.setInventoryBill_statue(HisInventoryBill.STATUE_EXE);
				 if(historylist.size()>0&&(bill.getHisHouse()==null||bill.getHisHouse().getHouseId()==0)){
					 for(HisInventoryHistory history:historylist ){
						 medicinalBean=new HisMedicinalInventory();
						 HisMedicinalInventory medicinal=medicinalDao.queryOneMedicinalInventory(new Object[]{null,history.getCompany().getCompanyId(),history.getMedicinal().getMedicinalId(),history.getItem_code(),HisMedicinalInventory.TYPE_KUFANG,null,null});
						 if(medicinal!=null){
							 medicinalDao.updateMedicinalHqty(medicinal.getInvertory_id(),medicinal.getHqty()+history.getHqty()); 
						 }else{
							 medicinalBean.setCompany(history.getCompany());
							 medicinalBean.setItem_code(history.getItem_code());
							 medicinalBean.setMedicinal(history.getMedicinal());
							 medicinalBean.setValidtime(history.getValidtime());
							 medicinalBean.setHqty(history.getHqty());
							 medicinalBean.setRqty(history.getHqty());
							 medicinalBean.setPurchase_price(history.getPurchase_price());
							 medicinalBean.setResale_price(history.getResale_price());
							 medicinalBean.setInventory_type(HisMedicinalInventory.TYPE_KUFANG);
							 medicinalBean.setHouse(null);
							 medicinalDao.addHisMedicinalInventory(medicinalBean);
						 }
					 }
					 	
				 }
			 }else if(bill.getInventoryBill_type()==HisInventoryBill.TYPE_OUT){
				 bill.setInventoryBill_statue(HisInventoryBill.STATUE_EXE);
				 if(historylist.size()>0){
					 for(HisInventoryHistory history:historylist ){
						 HisMedicinalInventory medicinal=medicinalDao.queryOneMedicinalInventory(new Object[]{null,history.getCompany().getCompanyId(),history.getMedicinal().getMedicinalId(),history.getItem_code(),HisMedicinalInventory.TYPE_KUFANG,null,null});
						 if(medicinal!=null){
							 medicinalDao.updateMedicinalHqty(medicinal.getInvertory_id(),medicinal.getHqty()-history.getHqty()); 
						 }
						 HisMedicinalInventory yaofangmedicinal=medicinalDao.queryOneMedicinalInventory(new Object[]{null,history.getCompany().getCompanyId(),history.getMedicinal().getMedicinalId(),history.getItem_code(),HisMedicinalInventory.TYPE_YAOFANG,null,null});
						 if(yaofangmedicinal!=null&&bill.getHisHouse().getHouseId()==yaofangmedicinal.getHouse().getHouseId()){
							 medicinalDao.updateMedicinalHqty(yaofangmedicinal.getInvertory_id(), yaofangmedicinal.getHqty()+history.getHqty());
						 }else{
							 medicinal.setHqty(history.getHqty());
							 medicinal.setRqty(history.getHqty());
							 medicinal.setInventory_type(HisMedicinalInventory.TYPE_YAOFANG);
							 medicinal.getHouse().setHouseId(bill.getHisHouse().getHouseId());
							 medicinalDao.addHisMedicinalInventory(medicinal);
						 }
					 }
				 }
			 }else if(bill.getInventoryBill_type()==HisInventoryBill.TYPE_HUITUI){
				 bill.setInventoryBill_statue(HisInventoryBill.STATUE_EXE);
				 if(historylist.size()>0){
					 for(HisInventoryHistory history:historylist ){
						 HisMedicinalInventory medicinal=medicinalDao.queryOneMedicinalInventory(new Object[]{null,history.getCompany().getCompanyId(),history.getMedicinal().getMedicinalId(),history.getItem_code(),HisMedicinalInventory.TYPE_KUFANG,null,null});
						 if(medicinal!=null){
							 medicinalDao.updateMedicinalHqty(medicinal.getInvertory_id(),medicinal.getHqty()+history.getHqty()); 
						 }
						 HisMedicinalInventory yaofangmedicinal=medicinalDao.queryOneMedicinalInventory(new Object[]{null,history.getCompany().getCompanyId(),history.getMedicinal().getMedicinalId(),history.getItem_code(),HisMedicinalInventory.TYPE_YAOFANG,null,null});
						 if(yaofangmedicinal!=null&&bill.getHisHouse().getHouseId()==yaofangmedicinal.getHouse().getHouseId()){
							 medicinalDao.updateMedicinalHqty(yaofangmedicinal.getInvertory_id(), yaofangmedicinal.getHqty()-history.getHqty());
						 }
					 }
				 }
				 
			 }else if(bill.getInventoryBill_type()==HisInventoryBill.TYPE_DIAOBO){
				 bill.setInventoryBill_statue(HisInventoryBill.STATUE_EXE);
				 if(historylist.size()>0){
					 for(HisInventoryHistory history:historylist ){
						 HisMedicinalInventory medicinal=medicinalDao.queryOneMedicinalInventory(new Object[]{null,history.getCompany().getCompanyId(),history.getMedicinal().getMedicinalId(),history.getItem_code(),HisMedicinalInventory.TYPE_KUFANG,null,null});
						 if(medicinal!=null){
							 medicinalDao.updateMedicinalHqty(medicinal.getInvertory_id(),medicinal.getHqty()-history.getHqty()); 
						 }
						 HisMedicinalInventory yaofangmedicinal=medicinalDao.queryOneMedicinalInventory(new Object[]{null,history.getCompany().getCompanyId(),history.getMedicinal().getMedicinalId(),history.getItem_code(),HisMedicinalInventory.TYPE_YAOFANG,null,null});
						 if(yaofangmedicinal!=null&&bill.getHisHouse().getHouseId()==yaofangmedicinal.getHouse().getHouseId()){
							 medicinalDao.updateMedicinalHqty(yaofangmedicinal.getInvertory_id(), yaofangmedicinal.getHqty()+history.getHqty());
						 }else{
							 medicinal.setHqty(history.getHqty());
							 medicinal.setRqty(history.getHqty());
							 medicinal.setInventory_type(HisMedicinalInventory.TYPE_YAOFANG);
							 medicinal.getHouse().setHouseId(bill.getHisHouse().getHouseId());
							 medicinalDao.addHisMedicinalInventory(medicinal);
						 }
					 }
				 }  
			 }
			 inventoryBillDao.updateInventorybill(bill);
		 }
		
	}
	/*回显入库单*/
	public void showInventory(HisInventoryBill inventorybillBean)throws Exception{
		
	}
	/*修改*/
	public void modifyInventroy(HisInventoryBill inventorybillBean)throws Exception{
		
	}
	/*删除*/
	public void deleteInventroy(int id)throws Exception{
		inventoryHistoryDao.deleteHisInventoryHistoryByBllid(id);
		List<Integer> list=new ArrayList<Integer>();
		list.add(id);
		inventoryBillDao.deleteInventoryBill(list);
	}
	/*更新出入单状态*/
	public void updateInventoryBill(HisInventoryBill inventorybillBean)throws Exception{
		HisInventoryBill bill=inventoryBillDao.queryOneInventoryBill(inventorybillBean.getInventoryBill_id());
		bill.setInventoryBill_statue(inventorybillBean.getInventoryBill_statue());
		bill.setUser(inventorybillBean.getUser());
		bill.setInventoryBill_opertime(inventorybillBean.getInventoryBill_opertime());
	  	inventoryBillDao.updateInventorybill(bill);
	}
}
