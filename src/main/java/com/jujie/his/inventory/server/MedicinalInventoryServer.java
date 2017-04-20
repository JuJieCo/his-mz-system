package com.jujie.his.inventory.server;



import java.util.List;

import com.jujie.his.inventory.HisMedicinalInventory;
import com.jujie.his.inventory.dao.HisMedicinalInventoryDaoImpl;
import com.jujie.his.mz.HisInventoryFy;
import com.jujie.his.mz.MzYp;
import com.jujie.his.mz.dao.MzChargeDaoImpl;
import com.jujie.util.page.Page;
/**
 * 药品药方库存
 * @author 
 *
 */
public class MedicinalInventoryServer {
 private HisMedicinalInventoryDaoImpl  medicinalDao;
 private MzChargeDaoImpl mzChargeDaoImpl;

 
	public void setmzChargeDaoImpl(MzChargeDaoImpl mzChargeDaoImpl){
		this.mzChargeDaoImpl = mzChargeDaoImpl;
	}
	
	public void setMedicinalDao(HisMedicinalInventoryDaoImpl medicinalDao) {
		this.medicinalDao = medicinalDao;
	}
	public  List<HisMedicinalInventory> queryMedicinalList(int medicinalid) throws Exception{
		return medicinalDao.queryAllMedicinalInventory(new Object[]{null,null,medicinalid,null,null,null,null},null);
	}
	/*药房 、药库盘库列表*/
	public  List<HisMedicinalInventory> queryMedicinalListByName(int type, String  medicinal_name,Page page) throws Exception{
		if(null!=medicinal_name&&!"".equals(medicinal_name)){
			if(type==HisMedicinalInventory.TYPE_YAOFANG){
				return medicinalDao.queryAllMedicinalInventory(new Object[]{null,null,null,null,HisMedicinalInventory.TYPE_YAOFANG,medicinal_name,null},page);
			}
			if(type==HisMedicinalInventory.TYPE_KUFANG){
				return medicinalDao.queryAllMedicinalInventory(new Object[]{null,null,null,null,HisMedicinalInventory.TYPE_KUFANG,medicinal_name,null},page);
			}
			return null;
				
		}else{
			if(type==HisMedicinalInventory.TYPE_YAOFANG){
				return medicinalDao.queryAllMedicinalInventory(new Object[]{null,null,null,null,HisMedicinalInventory.TYPE_YAOFANG,null,null},page);
			}
			if(type==HisMedicinalInventory.TYPE_KUFANG){
				return medicinalDao.queryAllMedicinalInventory(new Object[]{null,null,null,null,HisMedicinalInventory.TYPE_KUFANG,null,null},page);
			}
			return null;
		}
		
	}
	/*保存 药房 、药库盘库*/
	public void saveMedicinal(List<HisMedicinalInventory> medicinals,boolean syn) throws Exception{
		for(HisMedicinalInventory medicinal:medicinals){
		   HisMedicinalInventory hismedicinal=medicinalDao.queryOneMedicinalInventory(medicinal.getInvertory_id());
		   hismedicinal.setRqty(medicinal.getRqty());
		   if(syn){
			   hismedicinal.setHqty(medicinal.getRqty());
		   }
		   medicinalDao.updateMedicinal(hismedicinal);
		}
	}
	/*根据id 获取药库信息 */
	public List<HisMedicinalInventory> queryMedicialListById(int medicialId)throws Exception{
		return medicinalDao.queryAllMedicinalInventory(new Object[]{null,null,medicialId,null,null,null,null},null);
	}
	
	public List<HisMedicinalInventory> queryMedicialListById(int medicialId,int invertoryType)throws Exception{
		return medicinalDao.queryAllMedicinalInventory(new Object[]{null,null,medicialId,null,invertoryType,null,null},null);
	}
	public void updateMedicialPrice(HisMedicinalInventory medicinal)throws Exception{
		medicinalDao.updateMedicinalprice(medicinal);
	}
	public int getMedicianlSum(int medicinalid){
		return medicinalDao.getMediciNum(medicinalid,HisMedicinalInventory.TYPE_YAOFANG);
	}
	/*mz*/
	public List<HisMedicinalInventory> queryMedicialList(List<Integer> medicialIds)throws Exception{
			List<HisMedicinalInventory> medicinal=medicinalDao.queryAllMedicinalInventory(medicialIds,HisMedicinalInventory.TYPE_YAOFANG);
			for (HisMedicinalInventory med:medicinal) {
				int sum=getMedicianlSum(med.getMedicinal().getMedicinalId());
				med.setMeidicinal_sum(sum);
			}
		return  medicinal;
	}
	//药品发放
	public void addYpff(HisInventoryFy hisInventoryFy,int uuid) throws Exception {
	    mzChargeDaoImpl.addYpff(hisInventoryFy);
		mzChargeDaoImpl.updateMzOrder(2,hisInventoryFy.getOrder_id());
		//更新药品库存
	    List<MzYp>	list=medicinalDao.getMzyp(uuid);
	    for(MzYp mzyp:list){
	    	List<HisMedicinalInventory> hisMedicinalList=medicinalDao.queryAllMedicinalInventory(new Object[]{mzyp.getInvertory_id(),null,null,null,HisMedicinalInventory.TYPE_YAOFANG,null,null}, null);
	    	int hqtyvalue=0;
	    	if(hisMedicinalList.size()>0){
	    		hqtyvalue=hisMedicinalList.get(0).getHqty();
	    	}
	    	System.out.println("hqtyvalue:"+hqtyvalue);
	    	System.out.println("num:"+mzyp.getMedicinal_num());
	    	medicinalDao.updateMedicinalHqty(mzyp.getInvertory_id(), hqtyvalue-mzyp.getMedicinal_num());	
	    }
		
	}
	public List<MzYp> getMzyp(int orderid){
		return medicinalDao.getMzyp(orderid);
	}
	public HisMedicinalInventory queryAllMedicinalInventory(Object[] objs,Page page) throws Exception{
		List<HisMedicinalInventory>  list=medicinalDao.queryAllMedicinalInventory(objs, page);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
