package com.jujie.his.inventory.server;

import java.util.List;

import com.jujie.his.inventory.HisChangepriceBill;
import com.jujie.his.inventory.HisChangepriceDetail;
import com.jujie.his.inventory.dao.HisChangepriceBillDaoImpl;
import com.jujie.his.inventory.dao.HisChangepriceDetailDaoImpl;
import com.jujie.util.page.Page;
/**
 * 调价单
 * @author 
 *
 */
public class ChangepriceBillServer {
	 private HisChangepriceBillDaoImpl changepriceBillDao;
	private HisChangepriceDetailDaoImpl changepriceDetailDao;

	public void setChangepriceDetailDao(
			HisChangepriceDetailDaoImpl changepriceDetailDao) {
		this.changepriceDetailDao = changepriceDetailDao;
	}

	public void setChangepriceBillDao(HisChangepriceBillDaoImpl changepriceBillDao) {
		this.changepriceBillDao = changepriceBillDao;
	}

	public List<HisChangepriceBill> inventoryChangepriceList(Object[] objs,Page page)throws Exception{
		return changepriceBillDao.queryAllChangepriceBill(objs, page);
	}
	public void addChangeprice(HisChangepriceBill hisChangepricebill)throws Exception{
		 int id=changepriceBillDao.addhisChangepriceBill(hisChangepricebill);
		 for(HisChangepriceDetail detail : hisChangepricebill.getDetailList()){
			 hisChangepricebill.setChangepriceBill_id(id);
			 detail.setHisChangepriceBill(hisChangepricebill);
			 changepriceDetailDao.addhisChangepriceDatail(detail);
		 }
	}
	public HisChangepriceBill queryOneChangepriceBill(int cbuid) throws Exception{
		return changepriceBillDao.queryOneChangepriceBill(cbuid);
	}
	public void updateChangepriceBill(HisChangepriceBill hisChangepricebill)throws Exception{
		changepriceBillDao.updateHisChangepriceBill(hisChangepricebill);
	}
	public List<HisChangepriceDetail> querychangepricebill(int id)throws Exception{
	  	return changepriceDetailDao.queryChangepriceDetailByid(id);
	}
	public void deletechangepricebill(int id)throws Exception{
		changepriceDetailDao.deleteHisChangepriceDetailByid(id);
		changepriceBillDao.deleteIntegraHisChangepriceBill(id);
		
	}


 
}
