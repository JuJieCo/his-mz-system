package com.jujie.his.medicare.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.jujie.his.medicare.UniversityMedDE;
import com.jujie.his.medicare.UniversityMedDEBAK;
import com.jujie.his.medicare.UniversityMedTotal;
import com.jujie.his.medicare.dao.UniversityMedDAOImpl;
import com.jujie.util.page.Page;

public class UniversityMedServerImpl {

	private UniversityMedDAOImpl universityMedDAOImpl;

	public void setUniversityMedDAOImpl(UniversityMedDAOImpl universityMedDAOImpl) {
		this.universityMedDAOImpl = universityMedDAOImpl;
	}
	
	public List<UniversityMedDE> queryUniversityMedDEList(Page page,Map<String,String> map)throws Exception{
		List<UniversityMedDE> list = universityMedDAOImpl.queryUniversityMedDEList(page, map);
		for (UniversityMedDE universityMedDE : list) {
			universityMedDE.setBakList(queryUniversityMedDEBAKList(universityMedDE.getUniversity_id()));
		}
		return list;
	}
	
	public List<UniversityMedDEBAK> queryUniversityMedDEBAKList(int id)throws Exception{
		return universityMedDAOImpl.queryUniversityMedDEBAKList(id);
	}
	
	public List<UniversityMedDE> queryUniversityMedDEListFromMZ(Page page,Map<String,String> map)throws Exception{
		return universityMedDAOImpl.queryUniversityMedDEListFromMZ(page, map);
	}
	
	public UniversityMedDE queryOneUniversityMedDE(int type,Map<String,String> map)throws Exception{
		List<UniversityMedDE> universityMedDEList = new ArrayList<UniversityMedDE>();
		if(type==1){
			universityMedDEList = universityMedDAOImpl.queryUniversityMedDEListFromMZ(null, map);
		}
		if(type==2){
			universityMedDEList = universityMedDAOImpl.queryUniversityMedDEList(null, map);
		}
		if(type==3){
			 return universityMedDAOImpl.queryOneUniversityMedDEBAK(map.get("id"), map.get("update"));
		}
		return universityMedDEList.get(0);
	}
	
	public void saveUniversityMedDE(UniversityMedDE universityMedDE){
		universityMedDAOImpl.saveUniversityMedDE(universityMedDE);
	}
	
	public void editUniversityMedDE(UniversityMedDE universityMedDE){
		try {
			UniversityMedDE ude = universityMedDAOImpl.queryUniversityMedDE(universityMedDE.getUniversity_id());
			UniversityMedDEBAK bak = new UniversityMedDEBAK();
			BeanUtils.copyProperties(bak,ude);
			universityMedDAOImpl.saveUniversityMedDEBAK(bak);
		} catch (Exception e) {
			e.printStackTrace();
		}
		universityMedDAOImpl.editUniversityMedDE(universityMedDE);
	}
	
	public UniversityMedTotal queryUniversityMedTotalByMonth(Map<String,String> map)throws Exception{
		return universityMedDAOImpl.queryUniversityMedTotalByMonth(map);
	}
}
