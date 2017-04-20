package com.jujie.his.inventory.server;

import com.jujie.his.inventory.dao.HisChangepriceDetailDaoImpl;
/**
 * 调价单明细
 * @author 
 *
 */
public class ChangepriceDetailServer {
 private HisChangepriceDetailDaoImpl changepriceDetailDao;

public void setChangepriceDetailDao(
		HisChangepriceDetailDaoImpl changepriceDetailDao) {
	this.changepriceDetailDao = changepriceDetailDao;
}
 
}
