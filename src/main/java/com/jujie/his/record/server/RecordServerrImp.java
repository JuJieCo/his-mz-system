package com.jujie.his.record.server;

import java.util.List;

import com.jujie.his.guahao.Sick;
import com.jujie.his.record.dao.RecordDAOImpl;
import com.jujie.util.page.Page;

public class RecordServerrImp {
	
	private RecordDAOImpl recorddao;
	public void setRecorddao(RecordDAOImpl recorddao) {
		this.recorddao = recorddao;
	}
	
	public List<Sick> queryAllSickInfo(Object[] objs,Page page) throws Exception {
		return recorddao.queryAllSickInfo(objs, page);
	}

}

