package com.jujie.his.mz.server;

import java.util.List;
import java.util.Map;

import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.Sick;
import com.jujie.his.mz.MzJz;
import com.jujie.his.mz.MzYp;
import com.jujie.his.mz.dao.MzZlDaoImpl;
import com.jujie.util.page.Page;

public class MzZlServerImpl {
	
	private MzZlDaoImpl mzZlDaoImpl;
	
	public void setMzZlDaoImpl(MzZlDaoImpl mzZlDaoImpl) {
		this.mzZlDaoImpl = mzZlDaoImpl;
	}

	//
   public List<GHOrder> queryGhOrderList(Object[] objs,Page page , Integer doctorId) throws Exception{
	 return mzZlDaoImpl.queryGhOrderList(objs,page,doctorId);
		
	}
   public List<GHOrder> queryFyList(Object[] objs,Page page) throws Exception{
		 return mzZlDaoImpl.queryFyList(objs,page);
			
		}
   public List<MzJz> queryMzJzList(Object[] objs) throws Exception{
		 return mzZlDaoImpl.queryMzJzList(objs);
			
		}
   
	public  Sick findSickByIDByID(Integer sickId) throws Exception {
		
	return  mzZlDaoImpl.findSickByIDByID(sickId);
  }
 	public void addJz(MzJz mzJz,List<MzYp> mzYpList) throws Exception {
 		int uuid = mzZlDaoImpl.addMzJz(mzJz);
 		for (MzYp mzYp : mzYpList) {
 			mzYp.setUuid(uuid);
		}
 		mzZlDaoImpl.addMzYp(mzYpList);
		mzZlDaoImpl.updateMzOrder(4,mzJz.getOrder_id());
 	} 
    public List<GHOrder> queryZwList(Object[] objs,Page page) throws Exception{
       return mzZlDaoImpl.queryZwList(objs,page);
 	}	
 
    public List<Map<String,String>> queryZwBBList(Object[] objs,Page page) {
    	return mzZlDaoImpl.queryZwBBList(objs, page);
    }
	
}
