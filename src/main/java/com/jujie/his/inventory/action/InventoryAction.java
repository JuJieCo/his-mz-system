package com.jujie.his.inventory.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.DateJsonValue;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.his.baseinfo.utils.GetAllMedicinals;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.inventory.HisChangepriceBill;
import com.jujie.his.inventory.HisChangepriceDetail;
import com.jujie.his.inventory.HisInventoryBill;
import com.jujie.his.inventory.HisInventoryHistory;
import com.jujie.his.inventory.HisMedicinalInventory;
import com.jujie.his.inventory.server.ChangepriceBillServer;
import com.jujie.his.inventory.server.InventoryBillServer;
import com.jujie.his.inventory.server.InventoryHistoryServer;
import com.jujie.his.inventory.server.MedicinalInventoryServer;
import com.jujie.his.mz.HisInventoryFy;
import com.jujie.his.mz.MzYp;
import com.jujie.his.mz.server.MzZlServerImpl;
import com.jujie.user.User;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;

public class InventoryAction extends BaseActionSupper {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(InventoryAction.class);

	private Page page;
	private String s_token;
	private String type;
	private GHOrder ghOrder;
	private List<GHOrder>  ghOrderList;
	private HisInventoryBill inventoryBill;
	private List<HisInventoryHistory> historyList;
	private HisInventoryFy hisInventoryFy;
	private List<HisMedicinalInventory> medicinalInventoryList;
	
	public List<GHOrder> getGhOrderList() {
		return ghOrderList;
	}
	public void setGhOrderList(List<GHOrder> ghOrderList) {
		this.ghOrderList = ghOrderList;
	}
	public GHOrder getGhOrder() {
		return ghOrder;
	}
	public void setGhOrder(GHOrder ghOrder) {
		this.ghOrder = ghOrder;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<HisMedicinalInventory> getMedicinalInventoryList() {
		return medicinalInventoryList;
	}

	public void setMedicinalInventoryList(
			List<HisMedicinalInventory> medicinalInventoryList) {
		this.medicinalInventoryList = medicinalInventoryList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getS_token() {
		return s_token;
	}

	public void setS_token(String s_token) {
		this.s_token = s_token;
	}

	public HisInventoryBill getInventoryBill() {
		return inventoryBill;
	}

	public void setInventoryBill(HisInventoryBill inventoryBill) {
		this.inventoryBill = inventoryBill;
	}

	public List<HisInventoryHistory> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<HisInventoryHistory> historyList) {
		this.historyList = historyList;
	}

	public String list() {

		if (page == null) {
			page = new Page(1);
		}
		BaseInfoServerImpl baseInfoServer = (BaseInfoServerImpl) this
				.getService("baseInfoServer");
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		try {
			this.getCxt().put("companyList", baseInfoServer.queryCompanyList(new Page()));
			this.getCxt().put(
					"billList",
					inventoryBillServer.intoInventory(new Object[] { null, 1,
							null }, page));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "into";
	}

	public String listOfoutto() {

		if (page == null) {
			page = new Page(1);
		}
		BaseInfoServerImpl baseInfoServer = (BaseInfoServerImpl) this
				.getService("baseInfoServer");
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		try {
			this.getCxt().put("houseList", baseInfoServer.queryHouseList(new Page()));
			this.getCxt().put(
					"billList",
					inventoryBillServer.intoInventory(new Object[] { null, 2,
							null }, page));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "outto";
	}

	public String listOftack() {
		if (page == null) {
			page = new Page(1);
		}
		BaseInfoServerImpl baseInfoServer = (BaseInfoServerImpl) this
				.getService("baseInfoServer");
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		try {
			this.getCxt().put("houseList", baseInfoServer.queryHouseList(new Page()));
			this.getCxt().put(
					"billList",
					inventoryBillServer.intoInventory(new Object[] { null, 3,
							null }, page));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "tack";
	}

	public String listOfback() {
		if (page == null) {
			page = new Page(1);
		}
		BaseInfoServerImpl baseInfoServer = (BaseInfoServerImpl) this
				.getService("baseInfoServer");
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		try {
			this.getCxt().put("houseList", baseInfoServer.queryHouseList(new Page()));
			this.getCxt().put(
					"billList",
					inventoryBillServer.intoInventory(new Object[] { null, 4,
							null }, page));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "back";
	}

	public String listOfDotack() {
		if (page == null) {
			page = new Page(1);
		}
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		try {
			this.getCxt().put(
					"billList",
					inventoryBillServer.intoInventory(
							new Object[] { null, 3,null }, page));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "dotack";
	}

	public String listOfDoback() {
		if (page == null) {
			page = new Page(1);
		}
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		try {
			this.getCxt().put(
					"billList",
					inventoryBillServer.intoInventory(
							new Object[] { null, 4, null }, page));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "doback";
	}

	public String getBillDetail() {
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		InventoryHistoryServer inventoryHistoryServer = (InventoryHistoryServer) this
				.getService("inventoryHistoryServer");
		MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this
				.getService("medicinalInventoryServer");
		try {
			List<HisInventoryBill> blist = inventoryBillServer.intoInventory(
					new Object[] { inventoryBill.getInventoryBill_id(), null,
							null }, null);
			if (blist != null && blist.size() > 0) {
				inventoryBill = blist.get(0);
			}
			historyList = inventoryHistoryServer.queryALLInventoryHistory(inventoryBill.getInventoryBill_id());
			List<HisMedicinalInventory> mlist = new ArrayList<HisMedicinalInventory>();
			for (HisInventoryHistory hisInventoryHistory : historyList) {
				mlist.addAll(medicinalInventoryServer.queryMedicialListById(
						hisInventoryHistory.getMedicinal().getMedicinalId(),HisMedicinalInventory.TYPE_KUFANG));
			}
			this.getCxt().put("medicinalInventoryList", mlist);
			this.getCxt().put("show", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (inventoryBill.getInventoryBill_type() == HisInventoryBill.TYPE_DIAOBO) {
			return listOfDotack();
		}
		if (inventoryBill.getInventoryBill_type() == HisInventoryBill.TYPE_HUITUI) {
			return listOfDoback();
		}
		return "";
	}

	public String doTBackBill() {
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		InventoryHistoryServer inventoryHistoryServer = (InventoryHistoryServer) this
				.getService("inventoryHistoryServer");
		MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this
				.getService("medicinalInventoryServer");
		if (medicinalInventoryList != null && medicinalInventoryList.size() > 0) {
			for (HisMedicinalInventory hisMedicinalInventory : medicinalInventoryList) {
				HisInventoryHistory hisInventoryHistory = new HisInventoryHistory();
				hisInventoryHistory.setCompany(hisMedicinalInventory
						.getCompany());
				hisInventoryHistory.setMedicinal(hisMedicinalInventory
						.getMedicinal());
				hisInventoryHistory.setItem_code(hisMedicinalInventory
						.getItem_code());
				hisInventoryHistory.setHqty(hisMedicinalInventory.getHqty());
				hisInventoryHistory.setInventoryBill(inventoryBill);
				inventoryBill.getHistoryList().add(hisInventoryHistory);
			}

			try {
				// 删除原来history
				inventoryHistoryServer.deleteInventoryHistory(inventoryBill
						.getInventoryBill_id());
				// 创建history
				inventoryHistoryServer.saveInventoryHistory(inventoryBill
						.getHistoryList());
				// 修改药房库存
				inventoryBillServer.addMedicinalInventory(
						inventoryBill.getInventoryBill_id(),
						inventoryBill.getInventoryBill_type());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if(inventoryBill.getInventoryBill_type()==3){
			return this.listOfDotack();
		}else if(inventoryBill.getInventoryBill_type()==4){
			return this.listOfDoback();
		}
		return null;
	}

	public String checkInventory() {
		if (page == null) {
			page = new Page(1);
		}
		MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this
				.getService("medicinalInventoryServer");
		int otp = 0;
		String forward = "";
		if ("yk".equals(type)) {
			otp = HisMedicinalInventory.TYPE_KUFANG;
			forward = "checkyk";
		}
		if ("yf".equals(type)) {
			otp = HisMedicinalInventory.TYPE_YAOFANG;
			forward = "checkyf";
		}
		try {
			medicinalInventoryList = medicinalInventoryServer
					.queryMedicinalListByName(otp,request.getParameter("medName"),page);
			this.getCxt().put("medName", request.getParameter("medName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

	public String saveRqty() {
		MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this
				.getService("medicinalInventoryServer");
		try {
			boolean syn = false;
			if(null!=request.getParameter("oty")&&"syn".equals(request.getParameter("oty"))){
				syn = true;
			}
			medicinalInventoryServer.saveMedicinal(medicinalInventoryList,syn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkInventory();
	}
	
	public String changeStatueOfBill() {
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		if (session.get("sessionUser") != null) {
			User user = (User) session.get("sessionUser");
			inventoryBill.getUser().setSysUserId(user.getSysUserId());
		}
		try {
			inventoryBillServer.updateInventoryBill(inventoryBill);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String ftype = request.getParameter("ftype");
		if ("diaobo".equals(ftype)) {
			return this.listOftack();
		}
		if ("huitui".equals(ftype)) {
			return this.listOfback();
		}
		return "";
	}

	public String saveAndEditInventoryBill() {
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		try {
			if(inventoryBill.getInventoryBill_type()==1){
				for(HisInventoryHistory history:historyList){
					Company company=new Company();
					company.setCompanyId(inventoryBill.getHiscompany().getCompanyId());
					history.setCompany(company);
					
				}
			}
			
			inventoryBill.setHistoryList(historyList);
			if (session.get("sessionUser") != null) {
				User user = (User) session.get("sessionUser");
				inventoryBill.getUser().setSysUserId(user.getSysUserId());
			}
			inventoryBillServer.addInventroy(inventoryBill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (inventoryBill.getInventoryBill_type() == 1) {
			return list();
		}
		if (inventoryBill.getInventoryBill_type() == 2) {
			return listOfoutto();
		}
		if (inventoryBill.getInventoryBill_type() == 3) {
			return listOftack();
		}
		if (inventoryBill.getInventoryBill_type() == 4) {
			return listOfback();
		}
		return list();
	}

	public String delete() {
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this
				.getService("inventoryBillServer");
		try {
			inventoryBillServer.deleteInventroy(inventoryBill
					.getInventoryBill_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String ftype = request.getParameter("ftype");
		if ("into".equals(ftype)) {
			return this.list();
		}
		if ("outto".equals(ftype)) {
			return this.listOfoutto();
		}
		if ("diaobo".equals(ftype)) {
			return this.listOftack();
		}
		if ("huitui".equals(ftype)) {
			return this.listOfback();
		}
		return list();
	}

	public String doIntoInventory() {
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this.getService("inventoryBillServer");
		String ftype = request.getParameter("ftype");
		try {
			inventoryBillServer.addMedicinalInventory(inventoryBill.getInventoryBill_id(),HisInventoryBill.TYPE_INTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("into".equals(ftype)) {
			return this.list();
		}
		if ("outto".equals(ftype)) {
			return this.listOfoutto();
		}
		if ("diaobo".equals(ftype)) {
			return this.listOftack();
		}
		if ("huitui".equals(ftype)) {
			return this.listOfback();
		}
		return list();
	}
	
	public String doOutInventory() {
		InventoryBillServer inventoryBillServer = (InventoryBillServer) this.getService("inventoryBillServer");
		String ftype = request.getParameter("ftype");
		try {
			inventoryBillServer.addMedicinalInventory(inventoryBill.getInventoryBill_id(),HisInventoryBill.TYPE_OUT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("into".equals(ftype)) {
			return this.list();
		}
		if ("outto".equals(ftype)) {
			return this.listOfoutto();
		}
		if ("diaobo".equals(ftype)) {
			return this.listOftack();
		}
		if ("huitui".equals(ftype)) {
			return this.listOfback();
		}
		return list();
	}

	public String jsonMedicinalInventoryByMedicinalID() {
		List<HisMedicinalInventory> list = new ArrayList<HisMedicinalInventory>();
		String medicinalid=request.getParameter("medicinalID");
		System.out.println(medicinalid);
		int dateid=DataUtils.getInt(medicinalid);
		MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this.getService("medicinalInventoryServer");
		try {
			list=medicinalInventoryServer.queryMedicialListById(dateid,HisMedicinalInventory.TYPE_KUFANG);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONArray jSONArray = JSONArray.fromObject(list);
		String str = jSONArray.toString();
		log.info(str);
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String jsonBillDetails(){
		int billID=DataUtils.getInt(request.getParameter("billID"));
		InventoryHistoryServer inventoryHistoryServer = (InventoryHistoryServer) this.getService("inventoryHistoryServer");
		try {
			List<HisInventoryHistory> h_List = inventoryHistoryServer.queryALLInventoryHistory(billID);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValue("yyyy-MM-dd HH:mm:ss"));
			JSONArray jSONArray = JSONArray.fromObject(h_List);
			String str = jSONArray.toString();
			log.info(str);
			try {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String listchangeprice(){
		if (page == null) {
			page = new Page(1);
		}
		ChangepriceBillServer changepriceBillServer = (ChangepriceBillServer) this.getService("changepriceBillServer");
		 
		try {
			this.getCxt().put("changepriceList",changepriceBillServer.inventoryChangepriceList(new Object[]{null}, page));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "changeprice";
	}
	public String savechangeprice(){
		ChangepriceBillServer changepriceBillServer = (ChangepriceBillServer) this.getService("changepriceBillServer");
		HisChangepriceBill chagepricebill=new HisChangepriceBill();
		chagepricebill.setChangepriceBill_cause(request.getParameter("changepriceBill_cause"));
		chagepricebill.setChangepriceBill_createtime(new Date());
		chagepricebill.setChangepriceBill_opertime(null);
		chagepricebill.setChangepriceBill_statue(0);
		if (session.get("sessionUser") != null) {
			User user = (User) session.get("sessionUser");
			chagepricebill.setUser(user);
			List<HisChangepriceDetail> detailList=new ArrayList<HisChangepriceDetail>();
			for(HisInventoryHistory history:historyList){
				 if(history!=null){
					HisChangepriceDetail detail=new HisChangepriceDetail();
					 detail.setHisCompany(history.getCompany());
					 detail.setItem_code(history.getItem_code());
					 detail.setHismedicinal(history.getMedicinal());
					 detail.setOld_resale_price(history.getResale_price());
					 detail.setNew_resale_price(history.getPurchase_price());
					 detailList.add(detail);	
				}
			}
			chagepricebill.setDetailList(detailList);
			try {
				changepriceBillServer.addChangeprice(chagepricebill);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.listchangeprice();
	}
	public String  dochangeprice(){
		String id=request.getParameter("chagepriceid");
		ChangepriceBillServer changepriceBillServer = (ChangepriceBillServer) this.getService("changepriceBillServer");
		MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this.getService("medicinalInventoryServer");
		try {
			//更新调价单 操作时间 状态  操作员
			HisChangepriceBill bill=changepriceBillServer.queryOneChangepriceBill(DataUtils.getInt(id));
			bill.setChangepriceBill_opertime(new Date());
			bill.setChangepriceBill_statue(1);
			if (session.get("sessionUser") != null) {
				User user = (User) session.get("sessionUser");
				bill.setUser(user);
				changepriceBillServer.updateChangepriceBill(bill);
				//根据调价单查询药品供应公司 批号 药品id  调价单表
				List<HisChangepriceDetail> listdetail=changepriceBillServer.querychangepricebill(DataUtils.getInt(id));
				for(HisChangepriceDetail detail:listdetail){
					//根据上述条件更新药房药库零售价
					HisMedicinalInventory medicinal=new HisMedicinalInventory();
					medicinal.setCompany(detail.getHisCompany());
					medicinal.setItem_code(detail.getItem_code());
					medicinal.setResale_price(detail.getNew_resale_price());
					medicinal.setMedicinal(detail.getHismedicinal());
					medicinalInventoryServer.updateMedicialPrice(medicinal);
				}	
			 GetAllMedicinals.getInstance().refreshMedicinalList(ServletActionContext.getServletContext());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.listchangeprice();
	}
	public String deletechangeprice(){
		String id=request.getParameter("chagepriceid");
		ChangepriceBillServer changepriceBillServer = (ChangepriceBillServer) this.getService("changepriceBillServer");
		try {
			changepriceBillServer.deletechangepricebill(DataUtils.getInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listchangeprice();
	}
	
	//药品发放查询
	public String queryFyList() {
	 	if(page==null){
			page = new Page(1);
		}
	 	MzZlServerImpl mzZlServerImpl = (MzZlServerImpl) this.getService("mzZlServerImpl");
	 	MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this.getService("medicinalInventoryServer");
	 	List<HisMedicinalInventory> medicinalInventoryList =null;
	 	String sick_casehistory = request.getParameter("sickcasehistory");
	 	try {
			if (ghOrder == null) {
				Object[] objs = new Object[] {sick_casehistory,5,2};
			  	ghOrderList = mzZlServerImpl.queryFyList(objs,page);
			  	for(GHOrder ghorder:ghOrderList){
			  		medicinalInventoryList = new ArrayList<HisMedicinalInventory>();
			  	    List<MzYp>  mzhjList=medicinalInventoryServer.getMzyp(ghorder.getUuid());
			  	    for(MzYp mzyp:mzhjList){
			  	    	HisMedicinalInventory  medicinalInventory=medicinalInventoryServer.queryAllMedicinalInventory(new Object[]{mzyp.getInvertory_id(),null,null,null,null,null,null},null);
			  	    	ghorder.setMedicinal_num(mzyp.getMedicinal_num());
			  	    	medicinalInventory.setMeidicinal_sum(mzyp.getMedicinal_num());
			  	    	medicinalInventoryList.add(medicinalInventory);
			  	    }
			  	   ghorder.setMedInventoryList(medicinalInventoryList);
			  	}
			  	
			} else {
				Object[] objs = new Object[] {sick_casehistory,5,2};
				ghOrderList = mzZlServerImpl.queryFyList(objs,page);
				for(GHOrder ghorder:ghOrderList){
			  		medicinalInventoryList = new ArrayList<HisMedicinalInventory>();
			  	    List<MzYp>  mzhjList=medicinalInventoryServer.getMzyp(ghorder.getOrderId());
			  	    for(MzYp mzyp:mzhjList){
			  	    	HisMedicinalInventory  medicinalInventory=medicinalInventoryServer.queryAllMedicinalInventory(new Object[]{mzyp.getInvertory_id(),null,null,null,null,null,null},null);
			  	    	ghorder.setMedicinal_num(mzyp.getMedicinal_num());
			  	    	medicinalInventory.setMeidicinal_sum(mzyp.getMedicinal_num());
			  	    	medicinalInventoryList.add(medicinalInventory);
			  	    }
			  	   ghorder.setMedInventoryList(medicinalInventoryList);
			  	}
			}
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sickcasehistory", sick_casehistory);
	 	return "ypff";
	}
	//药品发放
	public String savefayao() {
		MedicinalInventoryServer medicinalInventoryServer = (MedicinalInventoryServer) this.getService("medicinalInventoryServer"); 
		 String order_Id = request.getParameter("orderid");
		 String uuid=request.getParameter("uuid");
		 System.out.println("orderid: "+order_Id);
//		 List<HisMedicinalInventory> medicinalList = new ArrayList<HisMedicinalInventory>();
			if(!StringUtils.isBlank(order_Id+"")&&!StringUtils.isBlank(uuid+"")){
				HisInventoryFy fy=new HisInventoryFy();
				fy.setLyinfo_dotime(new Date());
			    if (session.get("sessionUser") != null) {
					User user = (User) session.get("sessionUser");
				 	fy.setSys_user_id(user.getSysUserId());
				 	fy.setOrder_id(DataUtils.getInt(order_Id));
			    }
			 	try {
//					medicinalList=medicinalInventoryList;
					medicinalInventoryServer.addYpff(fy,DataUtils.getInt(uuid));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 	 	}
		return  this.queryFyList();
	}
	
}
