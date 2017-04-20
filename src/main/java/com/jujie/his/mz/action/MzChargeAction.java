package com.jujie.his.mz.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.baseinfo.Dept;
import com.jujie.his.baseinfo.Doctor;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.Sick;
import com.jujie.his.inventory.HisMedicinalInventory;
import com.jujie.his.inventory.server.MedicinalInventoryServer;
import com.jujie.his.mz.Fee_Type;
import com.jujie.his.mz.HisInventoryFy;
import com.jujie.his.mz.MzCharge;
import com.jujie.his.mz.MzHj;
import com.jujie.his.mz.MzJz;
import com.jujie.his.mz.MzYp;
import com.jujie.his.mz.server.MzChargeServerImpl;
import com.jujie.his.mz.server.MzZlServerImpl;
import com.jujie.user.User;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;

@SuppressWarnings("serial")
public class MzChargeAction extends BaseActionSupper{
	
	
	private MzCharge mzCharge;
	private List<MzCharge>  mzChargeList;
	private GHOrder ghOrder;
	private List<GHOrder>  ghOrderList;
	private Sick sick;
    private MzYp mzYp;
	private MzJz mzJz;
	private List<MzJz>  mzJzList;
    private Page page;
    private List<Doctor> doctorList;
	private List<Dept> deptList;
	private List<MzHj> mzHjList;
	private String s_token;
	private HisInventoryFy hisInventoryFy; 
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	  
	public MzCharge getMzCharge() {
		return mzCharge;
	}
	public void setMzCharge(MzCharge mzCharge) {
		this.mzCharge = mzCharge;
	}
	public List<MzCharge> getMzChargeList() {
		return mzChargeList;
	}
	public void setMzChargeList(List<MzCharge> mzChargeList) {
		this.mzChargeList = mzChargeList;
	}
	
	 
	public Sick getSick() {
		return sick;
	}
	public void setSick(Sick sick) {
		this.sick = sick;
	}
	public GHOrder getGhOrder() {
		return ghOrder;
	}
	public void setGhOrder(GHOrder ghOrder) {
		this.ghOrder = ghOrder;
	}
	 
	public List<MzJz> getMzJzList() {
		return mzJzList;
	}
	public void setMzJzList(List<MzJz> mzJzList) {
		this.mzJzList = mzJzList;
	}
	public MzYp getMzYp() {
		return mzYp;
	}
	public void setMzYp(MzYp mzYp) {
		this.mzYp = mzYp;
	}
	public MzJz getMzJz() {
		return mzJz;
	}
	public void setMzJz(MzJz mzJz) {
		this.mzJz = mzJz;
	}
	public List<GHOrder> getGhOrderList() {
		return ghOrderList;
	}
	public void setGhOrderList(List<GHOrder> ghOrderList) {
		this.ghOrderList = ghOrderList;
	}
	
	public List<Doctor> getDoctorList() {
		return doctorList;
	}
	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}
	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}
  
	public List<MzHj> getMzHjList() {
		return mzHjList;
	}
	public void setMzHjList(List<MzHj> mzHjList) {
		this.mzHjList = mzHjList;
	}
	
	public String getS_token() {
		return s_token;
	}
	public void setS_token(String s_token) {
		this.s_token = s_token;
	}
	


	public HisInventoryFy getHisInventoryFy() {
		return hisInventoryFy;
	}
	public void setHisInventoryFy(HisInventoryFy hisInventoryFy) {
		this.hisInventoryFy = hisInventoryFy;
	}
	public String queryAllMzSickList() {
	 	if(page==null){
			page = new Page(1);
		}
	 	MzZlServerImpl mzZlServerImpl = (MzZlServerImpl) this.getService("mzZlServerImpl");
	    MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
	 	MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this.getService("medicinalInventoryServer");
	  	List<Integer> medicialIds = null;
	 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String defaultDate = sdf.format(new Date());
	    String sick_casehistory = request.getParameter("sickcasehistory");
	 	try {
			if (ghOrder == null) {
				Object[] objs = new Object[] {sick_casehistory,4,defaultDate};
			  	ghOrderList = mzChargeServerImpl.queryGhOrderList(objs,page);
			  	mzJzList = mzZlServerImpl.queryMzJzList(null);
			  	//一个就诊号包含的药品数量和根据药品id查询相关药品信息eg：药品单价。
			  	for (GHOrder ghOrder : ghOrderList) {
			  		List<MzJz> mzjzList = new ArrayList<MzJz>();
			  		for (MzJz mzJz : mzJzList) {
			  		  if(mzJz.getUuid() == ghOrder.getMzJzList().get(0).getUuid()){
			  			  if(mzJz.getMzYpList() != null && mzJz.getMzYpList().size()>0){
			  			   for (MzYp mzYp : mzJz.getMzYpList()) {
			  				 medicialIds = new ArrayList<Integer>();
			  				 MzJz mzjz = (MzJz)mzJz.clone();
			  			     medicialIds.add(mzYp.getMedicinal_id()); 
			  				 List<HisMedicinalInventory> inventoryList = medicinalInventoryServer.queryMedicialList(medicialIds);
			  				 mzYp.setMedInvSize(inventoryList.size());
							 mzYp.setMedicinalInventoryList(inventoryList);	 
							 mzjz.setMzYp(mzYp);
							 mzjzList.add(mzjz);
			  			   }
				  		 }
					     
					   }
					}
			  	 ghOrder.setMzJzList(mzjzList);
				}
			  	
			} else {
				Object[] objs = new Object[] {sick_casehistory,4,defaultDate};
				ghOrderList = mzChargeServerImpl.queryGhOrderList(objs,page);
			  	mzJzList = mzZlServerImpl.queryMzJzList(null);
			    	//一个就诊号包含的药品数量和根据药品id查询相关药品信息eg：药品单价。
			  	for (GHOrder ghOrder : ghOrderList) {
			  		List<MzJz> mzjzList = new ArrayList<MzJz>();
			  		for (MzJz mzJz : mzJzList) {
			  		  if(mzJz.getUuid() == ghOrder.getMzJzList().get(0).getUuid()){
			  			  if(mzJz.getMzYpList() != null && mzJz.getMzYpList().size()>0){
			  			   for (MzYp mzYp : mzJz.getMzYpList()) {
			  				 medicialIds = new ArrayList<Integer>();
			  				 MzJz mzjz = (MzJz)mzJz.clone();
			  			     medicialIds.add(mzYp.getMedicinal_id()); 
			  				 List<HisMedicinalInventory> inventoryList = medicinalInventoryServer.queryMedicialList(medicialIds);
			  				 mzYp.setMedInvSize(inventoryList.size());
							 mzYp.setMedicinalInventoryList(inventoryList);	 
							 mzjz.setMzYp(mzYp);
							 mzjzList.add(mzjz);
			  			   }
				  		 }
					     
					   }
					}
			  	 ghOrder.setMzJzList(mzjzList);
				}
			}
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sickcasehistory", sick_casehistory);
	 	return "list";
	}
	//查询收费列表
   public String queryAllSfList() {
	 	if(page==null){
			page = new Page(1);
		}
	    MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String defaultDate = sdf.format(new Date());
	    String sick_casehistory = request.getParameter("sickcasehistory");
	 	try {
			if (ghOrder == null) {
				Object[] objs = new Object[] {sick_casehistory,defaultDate};
			  	ghOrderList = mzChargeServerImpl.queryAllSfList(objs,page);
		 	  	
			} else {
				Object[] objs = new Object[] {sick_casehistory,defaultDate};
				ghOrderList = mzChargeServerImpl.queryAllSfList(objs,page);
		 	}
		 } catch (Exception e) {
			e.printStackTrace();
		}
	    request.setAttribute("sickcasehistory", sick_casehistory);
	 	return "sflist";
	}
 //查询治疗收费列表
   public String queryAllZlSfList() {
	 	if(page==null){
			page = new Page(1);
		}
	    MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String defaultDate = sdf.format(new Date());
	    String sick_casehistory = request.getParameter("sickcasehistory");
        try {
			if (ghOrder == null) {
				Object[] objs = new Object[] {sick_casehistory,defaultDate};
			  	ghOrderList = mzChargeServerImpl.queryAllZlSfList(objs,page);
		 	  	
			} else {
				Object[] objs = new Object[] {sick_casehistory,defaultDate};
				ghOrderList = mzChargeServerImpl.queryAllZlSfList(objs,page);
		 	}
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 request.setAttribute("sickcasehistory", sick_casehistory);
	 	return "zlsflist";
	}
	public String queryAllTreatList() {
	 	if(page==null){
			page = new Page(1);
		}
	 	MzZlServerImpl mzZlServerImpl = (MzZlServerImpl) this.getService("mzZlServerImpl");
	 	MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
	 	MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this.getService("medicinalInventoryServer");
	  	List<Integer> medicialIds = null;
	  	 String sick_casehistory = request.getParameter("sickcasehistory");
	 	try {
			if (ghOrder == null) {
				Object[] objs = new Object[] {sick_casehistory,null};
			  	ghOrderList = mzChargeServerImpl.queryGhOrderList(objs,page);
			  	mzJzList = mzZlServerImpl.queryMzJzList(objs);
			  	//一个就诊号包含的药品数量和根据药品id查询相关药品信息eg：药品单价。
			  	for (GHOrder ghOrder : ghOrderList) {
			  		List<MzJz> mzjzList = new ArrayList<MzJz>();
			  		for (MzJz mzJz : mzJzList) {
				  	   if(mzJz.getUuid() == ghOrder.getMzJzList().get(0).getUuid()){
			  			  if(mzJz.getMzYpList() != null && mzJz.getMzYpList().size()>0){
			  			   for (MzYp mzYp : mzJz.getMzYpList()) {
			  				 medicialIds = new ArrayList<Integer>();
			  				 MzJz mzjz = (MzJz)mzJz.clone();
			  			     medicialIds.add(mzYp.getMedicinal_id()); 
			  				 List<HisMedicinalInventory> inventoryList = medicinalInventoryServer.queryMedicialList(medicialIds);
			  				 mzYp.setMedInvSize(inventoryList.size());
							 mzYp.setMedicinalInventoryList(inventoryList);	 
							 mzjz.setMzYp(mzYp);
							 mzjzList.add(mzjz);
			  			   }
				  		 }
					     
						   }
				     }
			  	 ghOrder.setMzJzList(mzjzList);
				}
			  	
			} else {
				Object[] objs = new Object[] {sick_casehistory,null};
				ghOrderList = mzChargeServerImpl.queryGhOrderList(objs,page);
			  	mzJzList = mzZlServerImpl.queryMzJzList(objs);
			    	//一个就诊号包含的药品数量和根据药品id查询相关药品信息eg：药品单价。
			  	for (GHOrder ghOrder : ghOrderList) {
			  		List<MzJz> mzjzList = new ArrayList<MzJz>();
			  		for (MzJz mzJz : mzJzList) {
			  		  if(mzJz.getUuid() == ghOrder.getMzJzList().get(0).getUuid()){
			  			  if(mzJz.getMzYpList() != null && mzJz.getMzYpList().size()>0){
			  			   for (MzYp mzYp : mzJz.getMzYpList()) {
			  				 medicialIds = new ArrayList<Integer>();
			  				 MzJz mzjz = (MzJz)mzJz.clone();
			  			     medicialIds.add(mzYp.getMedicinal_id()); 
			  				 List<HisMedicinalInventory> inventoryList = medicinalInventoryServer.queryMedicialList(medicialIds);
			  				 mzYp.setMedInvSize(inventoryList.size());
							 mzYp.setMedicinalInventoryList(inventoryList);	 
							 mzjz.setMzYp(mzYp);
							 mzjzList.add(mzjz);
			  			   }
				  		 }
					     
					   }
						}
			  	 ghOrder.setMzJzList(mzjzList);
				}
			}
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sickcasehistory", sick_casehistory);
	 	return "treatlist";
	}
	
	
	
//	public String queryAllMzCharge() {
//	 	if(page==null){
//			page = new Page(1);
//		}
//	    MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
//	 	try {
//			if (mzCharge == null) {
//				Object[] objs = new Object[] { null,null,null,null,null};
//				mzChargeList = mzChargeServerImpl.findAllMzCharge(objs, page);
//		
//			} else {
//				Object[] objs = new Object[] {mzCharge.getOrder_id()};
//				mzChargeList = mzChargeServerImpl.findAllMzCharge(objs, page);
//			}
//			    
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	 	return "list";
//	}
 
    public String showAdd() {
	  request.setAttribute("editmzCharge", "0");
      return "addofedit";
	}
    public String queryChargeByID() {
		MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
		if(!StringUtils.isBlank(request.getParameter("orderid"))){
	    try {
	    	ghOrder = mzChargeServerImpl.findMzChargeByID(request.getParameter("orderid"));
	    } catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
       request.setAttribute("orderid", request.getParameter("orderid"));
 	   return "zf";
	 }
	 
  
  public String updateCharge() {
	  
    MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
	String  medicinaldetail  = request.getParameter("medicinalids");
 	String  invertoryID  = request.getParameter("invertoryIds");
    String orderTcfyId=request.getParameter("orderTcfyId");
    String orderid = request.getParameter("orderid");
	if(!StringUtils.isBlank(orderTcfyId) && !StringUtils.isBlank(medicinaldetail) && !StringUtils.isBlank(invertoryID) ){
		MzCharge charge = new MzCharge();
		charge.setUuid(Integer.valueOf(orderTcfyId));
	 	String hjinfo_totalM = request.getParameter("hjinfo_totalM");
		String hjinfo_getM = request.getParameter("hjinfo_getM");
		String hjinfo_backM = request.getParameter("hjinfo_backM");
		String hjinfo_xytm = request.getParameter("hjinfo_xytm");
		String hjinfo_zcytm = request.getParameter("hjinfo_zcytm");
		String hjinfo_zcytm2 = request.getParameter("hjinfo_zcytm2");
	 	String hjinfo_zltm = request.getParameter("hjinfo_zltm"); //诊疗费用
	  
		
	 	if(!StringUtils.isBlank(hjinfo_totalM)){
        	charge.setHjinfo_totalM(Double.valueOf(hjinfo_totalM));
		 }else{
			charge.setHjinfo_totalM(0.0);
		 }
	 	if(!StringUtils.isBlank(hjinfo_getM)){
        	charge.setHjinfo_getM(Double.valueOf(hjinfo_getM));
		 }else{
			charge.setHjinfo_getM(0.0);
		 }
	 	if(!StringUtils.isBlank(hjinfo_backM)){
        	charge.setHjinfo_backM(Double.valueOf(hjinfo_backM));
		 }else{
			charge.setHjinfo_backM(0.0);
		 }
	 	if(!StringUtils.isBlank(hjinfo_xytm)){
        	charge.setHjinfo_xytm(Double.valueOf(hjinfo_xytm));
		 }else{
			charge.setHjinfo_xytm(0.0);
		 }
	 	if(!StringUtils.isBlank(hjinfo_zcytm)){
        	charge.setHjinfo_zcytm(Double.valueOf(hjinfo_zcytm));
		 }else{
			charge.setHjinfo_zcytm(0.0);
		 }
	 	if(!StringUtils.isBlank(hjinfo_zcytm2)){
        	charge.setHjinfo_zcytm2(Double.valueOf(hjinfo_zcytm2));
		 }else{
			charge.setHjinfo_zcytm2(0.0);
		 }
	 	if(!StringUtils.isBlank(hjinfo_zltm)){
        	charge.setHjinfo_zltm(Double.valueOf(hjinfo_zltm));
		 }else{
			charge.setHjinfo_zltm(0.0);
		 }
	 	 
	    
		charge.setHjinfo_dotime(new Date());
		charge.setFlag(1);
		
		String[] invertoryIDs = invertoryID.substring(0, invertoryID.length()-1).split("_");
		String[] medicinaldetails = medicinaldetail.substring(0, medicinaldetail.length()-1).split("_");
		List<MzYp> ypList = new ArrayList<MzYp>();
        int i=0;
        for (String invertoryId : invertoryIDs) {
         MzYp mzYp = new MzYp();
         mzYp.setId(Integer.valueOf(medicinaldetails[i]));
         mzYp.setInvertory_id(Integer.valueOf(invertoryId));
         mzYp.setFlag(1);
         ypList.add(mzYp);
         i++;
    }
	try {
		if (session.get("sessionUser") != null) {
			User user = (User) session.get("sessionUser");
			charge.setSys_user_id(user.getSysUserId());
		    mzChargeServerImpl.addMzCharge(charge, ypList,orderid);
	    }
		
	} catch (Exception e) {
		e.printStackTrace();
	 }
	}
	return null;
  }
  // 诊疗划价根据就诊号
  public String queryZlHjByID() {
	MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
	if(!StringUtils.isBlank(request.getParameter("sickCasehistory"))){
    try {
    	ghOrder = mzChargeServerImpl.queryZlHjByID(request.getParameter("sickCasehistory"));
    } catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
   request.setAttribute("sickCasehistory", request.getParameter("sickCasehistory"));
   return "zlhj";
 }
  
  
  //收费
   public String updateSf() {
	    MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
	    String orderTcfyId=request.getParameter("orderTcfyId");
	    String orderid=request.getParameter("orderid");
		if(!StringUtils.isBlank(orderTcfyId) && !StringUtils.isBlank(orderid)){
			MzCharge charge = new MzCharge();
			charge.setId(Integer.valueOf(orderTcfyId));
		    charge.setSf_dotime(new Date());
		   // charge.setFph(fph);//发票号
			charge.setFlag(2);
	   try {
			if (session.get("sessionUser") != null) {
				User user = (User) session.get("sessionUser");
				charge.setSf_userid(user.getSysUserId());
			    mzChargeServerImpl.addMzSf(charge,Integer.valueOf(orderid));
		    }
		  } catch (Exception e) {
			e.printStackTrace();
		 }
		}
		return null;
	  }
  
 public String isEmpty(){
		request.setAttribute("message", "请输入至少一个查询条件!");
		return "mangerMain";
	}
 public String queryZwList() {
	 	if(page==null){
			page = new Page(1);
		}
        MzZlServerImpl mzZlServerImpl = (MzZlServerImpl) this.getService("mzZlServerImpl");
	 	BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
	 	
	 	String orderID = request.getParameter("orderID");
	 	String doctor = request.getParameter("doctor");
	 	String startDate = request.getParameter("startDate");
	 	String endDate = request.getParameter("endDate");
	 	String ks = request.getParameter("ks");//科室
	 	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	 	Object[] objs = new Object[] {orderID,doctor,startDate,endDate,ks};
	 	try {
         //ghOrderList = mzZlServerImpl.queryZwList(objs,page);
		 list = mzZlServerImpl.queryZwBBList(objs, page);
	  	 mzJzList = mzZlServerImpl.queryMzJzList(objs);
	     deptList = baseInfoServer.queryDeptList(new Page());
	  	 doctorList =baseInfoServer.queryDoctorList();
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("orderID", orderID);
		request.setAttribute("doctor", doctor);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("ks", ks);
		this.getCxt().put("rlist", list);
	 	return "zwlist";
	}
 
	//添加诊疗划价
	public String updateZlHj(){
	  String orderTcfyId=request.getParameter("orderTcfyId");
	  String totalM=request.getParameter("totalM");//费用
	  String jytpye=request.getParameter("jytpye");//费用类型
	  MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
	  if(!StringUtils.isBlank(orderTcfyId) && !StringUtils.isBlank(totalM) && !StringUtils.isBlank(jytpye)){
	   try {
	       Fee_Type feetype=new Fee_Type();
	       feetype.setOrder_id(Integer.valueOf(orderTcfyId));
	       feetype.setHjinfo_totalM(Double.valueOf(totalM));
	       feetype.setHjinfo_getM(Double.valueOf(totalM));
		   feetype.setFlag(Fee_Type.FLAG_YOUXIAO);
		   feetype.setHjinfo_dotime(new Date());
		   feetype.setJytpye(Integer.valueOf(jytpye));
		   if (session.get("sessionUser") != null) {
		   User user = (User) session.get("sessionUser");
		   feetype.setSys_user_id(user.getSysUserId());
	       mzChargeServerImpl.addZlHj(feetype);
		   }
     } catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }
   return null;
  }
   //诊疗收费
	public String updateZlSf(){
	  String feeid=request.getParameter("feeid");
      MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
	  if(!StringUtils.isBlank(feeid)){
	   try {
	       Fee_Type feetype=new Fee_Type();
	       feetype.setFeeid(Integer.valueOf(feeid));
	       feetype.setFlag(Fee_Type.FLAG_SHOUFEI);
	       feetype.setSf_dotime(new Date());
	       //feetype.setFph(fph);//发票号
		   if (session.get("sessionUser") != null) {
		   User user = (User) session.get("sessionUser");
		   feetype.setSf_userid(user.getSysUserId());
	       mzChargeServerImpl.updateZlSf(feetype);
	   }
     } catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }
   return null;
  }
	/********************************************************************************************/
	private List<Fee_Type> feeTypeList;
	private List<MzCharge> mzchangeList;
	public List<Fee_Type> getFeeTypeList() {
		return feeTypeList;
	}
	public void setFeeTypeList(List<Fee_Type> feeTypeList) {
		this.feeTypeList = feeTypeList;
	}
	public List<MzCharge> getMzchangeList() {
		return mzchangeList;
	}
	public void setMzchangeList(List<MzCharge> mzchangeList) {
		this.mzchangeList = mzchangeList;
	}
	/*查询  退费 */
	public String queryHjInfo() {
		MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
		String datetime=request.getParameter("txt_dateTime");
		String fph=request.getParameter("fph_code");
		if(datetime!=null&&!"".equals(datetime)&&fph!=null&&!"".equals(fph)){
			HashMap<Object,Object> condition=new HashMap<Object, Object>();
//			condition.put("flag", Fee_Type.FLAG_YOUXIAO);
			condition.put("hjinfo_dotime", datetime);
			condition.put("fph", fph);
			try {
				feeTypeList=mzChargeServerImpl.queryFeeType(condition);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<Object,Object> condition2=new HashMap<Object, Object>();
//			condition2.put("flag",MzCharge.FLAG_SHOUFEE);
			condition2.put("hjinfo_dotime", datetime);
			condition2.put("fph", fph);
			try {
				mzchangeList=mzChargeServerImpl.queryMzCharge(condition);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 	  return "zf";
	}
	/*退费*/
	public String updateTuifee() {
		MzChargeServerImpl mzChargeServerImpl = (MzChargeServerImpl) this.getService("mzChargeServerImpl");
		String tag=request.getParameter("table_tag");//1 feetype biao 其他表示 his_mz_hjinfo 
		String id=request.getParameter("id");
		String fph=request.getParameter("fph");
		if (session.get("sessionUser") != null) {
			User user = (User) session.get("sessionUser");
			if(tag!=null&&!"".equals(tag)){
				   if("1".equals(tag)){
					   if(id!=null&&!"".equals(id)&&fph!=null&&!"".equals(fph)){
						   Fee_Type feetype=new Fee_Type();
						   feetype.setFeeid(DataUtils.getInt(id));
						   feetype.setFph(fph);
						   feetype.setFlag(Fee_Type.FLAG_ZUOFEE);
						   feetype.setVoid_dotime(new Date());
						   feetype.setHjinfo_totalM(0);
						   feetype.setVoid_userid(user.getSysUserId());
						   try {
							mzChargeServerImpl.modifyFeeType(feetype);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					   }
				   }else{
					   if(id!=null&&!"".equals(id)&&fph!=null&&!"".equals(fph)){
						   MzCharge mzcharge=new MzCharge();
						   mzcharge.setId(DataUtils.getInt(id));
						   mzcharge.setFph(fph);
						   mzcharge.setFlag(MzCharge.FLAG_TUIFEI);
						   mzcharge.setVoid_dotime(new Date());
						   mzcharge.setVoid_userid(user.getSysUserId());
						   mzcharge.setHjinfo_totalM(0d);
						   try {
							mzChargeServerImpl.modifyHjInfo(mzcharge);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						   
					   }
				   }
			}
		}
		return null;
	}
	
	/********************************************************************************************/
}

	
	


