package com.jujie.his.mz.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.Sick;
import com.jujie.his.inventory.server.MedicinalInventoryServer;
import com.jujie.his.mz.MzJz;
import com.jujie.his.mz.MzYp;
import com.jujie.his.mz.server.MzZlServerImpl;
import com.jujie.user.User;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;

@SuppressWarnings("serial")
public class MzZlAction extends BaseActionSupper {
	
	
	private GHOrder ghOrder;
	private List<GHOrder>  ghOrderList;
	private Sick sick;
    private Page page;
	private MzYp mzYp;
	private MzJz mzJz;
	private List<MzJz>  mzJzList;
	private String zlinfoContent;
	private String zlinfoRemark;
	private String s_token;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
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
	public String getZlinfoContent() {
		return zlinfoContent;
	}
	public void setZlinfoContent(String zlinfoContent) {
		this.zlinfoContent = zlinfoContent;
	}
	public String getZlinfoRemark() {
		return zlinfoRemark;
	}
	public void setZlinfoRemark(String zlinfoRemark) {
		this.zlinfoRemark = zlinfoRemark;
	}
	
	public String getS_token() {
		return s_token;
	}
	public void setS_token(String s_token) {
		this.s_token = s_token;
	}
	
	//
	public String queryAllMzSickList() {
	 	if(page==null){
			page = new Page(1);
		}
	 	MzZlServerImpl mzZlServerImpl = (MzZlServerImpl) this.getService("mzZlServerImpl");
	 	
	 	//因为 病人挂号的医生和 登录医生看到的病人 不对应 13-03-14 修改如下
	 	
	 	User user = (User)request.getSession().getAttribute("sessionUser");
	 	
	 	Integer doctorId =0;
	 	if(null!=user){
	 		 doctorId = user.getDoctor().getDoctorId();
	 	}
	 
	 
	 	
	 	
	 	//13-03-14 修改到这  把 doctorId 传到DAO
	 	
	 	try {
			if (ghOrder == null) {
				Object[] objs = new Object[] {null,null};
			  	ghOrderList = mzZlServerImpl.queryGhOrderList(objs,page,doctorId);
			  	mzJzList = mzZlServerImpl.queryMzJzList(objs);
			  	for (GHOrder gHOrder : ghOrderList) {
			  		for (MzJz mzJz : mzJzList) {
						if(mzJz.getOrder_id()==gHOrder.getOrderId()){
							gHOrder.getMzJzList().add(mzJz);
						}
					}
				}	
			} else {
				Object[] objs = new Object[] {ghOrder.getOrderId(),null};
				ghOrderList = mzZlServerImpl.queryGhOrderList(objs,page,doctorId);
				mzJzList = mzZlServerImpl.queryMzJzList(objs);
				for (GHOrder gHOrder : ghOrderList) {
			  		for (MzJz mzJz : mzJzList) {
						if(mzJz.getOrder_id()==gHOrder.getOrderId()){
							gHOrder.getMzJzList().add(mzJz);
							break;
						}
					}
				}
			}
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	return "list";
	}
	
	public String queryzlByID() {
		MzZlServerImpl mzZlServerImpl = (MzZlServerImpl) this.getService("mzZlServerImpl");
	 	String sickId = request.getParameter("sickId");
		String method = request.getParameter("method");
		try {
		  if(!"".equals(method) && method!=null && sickId !=null && !"".equals(sickId)  ){
			  sick=mzZlServerImpl.findSickByIDByID(DataUtils.getInt(sickId));
		 	  return "addofedit";
			  }
			} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
	public String addorupdatezl() {
		MzZlServerImpl mzZlServerImpl = (MzZlServerImpl) this.getService("mzZlServerImpl");
		String[] medicinalNums  = request.getParameterValues("medicinal_num");
		String[] medicinalIDs  = request.getParameterValues("medicinalID");
		 
		List<MzYp> mzYpList = new ArrayList<MzYp>();
		MzJz mzJz = new MzJz();
		if(!"".equals(request.getParameter("orderid"))&&request.getParameter("orderid") != null && medicinalNums!=null && medicinalIDs !=null ){
		mzJz.setOrder_id(Integer.valueOf(request.getParameter("orderid")));
		mzJz.setSick_casehistory(request.getParameter("sick_casehistory"));
		mzJz.setZlinfo_content(zlinfoContent);
        mzJz.setZlinfo_remark(zlinfoRemark);
        if (session.get("sessionUser") != null) {
			User user = (User) session.get("sessionUser");
	        mzJz.setSys_user_id(user.getSysUserId());
	    }
      
		
        mzJz.setZlinfo_dotime(new Date());
        int i=0;
        for (String medicinalID : medicinalIDs) {
        	 if(medicinalNums[i]!=null && !"".equals(medicinalNums[i])){
             MzYp mzYp = new MzYp();
         //    mzYp.setOrder_id(Integer.valueOf(request.getParameter("orderid")));
             mzYp.setMedicinal_id(Integer.valueOf(medicinalID));
             mzYp.setMedicinal_num(Integer.valueOf(medicinalNums[i]));
			 mzYp.setInvertory_id(null);
             mzYpList.add(mzYp);
			 } 
            i++;
        }
		try {
			if(mzYpList != null && mzYpList.size()>0){
			   mzZlServerImpl.addJz(mzJz, mzYpList);
			}
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		}
		return queryAllMzSickList();
	}
	public String isEmpty(){
		request.setAttribute("message", "请输入至少一个查询条件!");
		return "mangerMain";
	}
  
	 public String getMedicinalNum(){
		 String medicinalid=request.getParameter("medicinalID");
		 int sum=0;
		 try {
			MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this.getService("medicinalInventoryServer");
			 if(medicinalid!=null&&!"".equals(medicinalid)){
				 sum=medicinalInventoryServer.getMedicianlSum(DataUtils.getInt(medicinalid));	 
			 }
			 response.getWriter().write(sum+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 
}

	
	


