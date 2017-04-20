package com.jujie.his.inventory.server;

import java.util.ArrayList;
import java.util.List;

import com.jujie.his.inventory.HisInventoryHistory;
import com.jujie.his.inventory.dao.HisInventoryHistoryDaoImpl;
/**
 * 出入库历史
 * @author 
 *
 */
public class InventoryHistoryServer {
	private HisInventoryHistoryDaoImpl  inventoryHistoryDao;

	public void setInventoryHistoryDao(
			HisInventoryHistoryDaoImpl inventoryHistoryDao) {
		this.inventoryHistoryDao = inventoryHistoryDao;
	} 
	public List<HisInventoryHistory> queryALLInventoryHistory(int id) throws Exception{
		return inventoryHistoryDao.queryAllInventoryHistory(new Object[]{null,id,null,null});
	}
	
	
	/*删除出入历史记录*/
	public void deleteInventoryHistory(List<Integer> listId)throws Exception{
		inventoryHistoryDao.deleteHisInventoryHistory(listId);
	}
	/*删除出入历史记录*/
	public void deleteInventoryHistory(int billid)throws Exception{
		inventoryHistoryDao.deleteHisInventoryHistoryByBllid(billid);
	}
	/*创建出入历史记录*/
	public List<Integer> saveInventoryHistory(List<HisInventoryHistory> listHistory)throws Exception{
		List<Integer> listIds=new ArrayList<Integer>();
		for(HisInventoryHistory list:listHistory){
			int id=inventoryHistoryDao.addHisInventoryHistory(list);
			listIds.add(id);
		}
		return listIds;
	}
	
}
