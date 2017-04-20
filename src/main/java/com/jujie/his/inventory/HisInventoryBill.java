package com.jujie.his.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.jujie.global.BaseBean;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.House;
import com.jujie.user.User;
import java.util.List;

/**
 * 出入单
 * @author 
 *
 */
public class HisInventoryBill extends BaseBean{

	/*新建*/
	public static int STATUE_NEW=1;
	/*2、提交*/
	public static int STATUE_SUB=2;
	/*3、处理*/
	public static int STATUE_EXE=3;
	/*1. 入库*/
	public static int TYPE_INTO=1;
	/*2. 出库*/
	public static int TYPE_OUT=2;
	/*3. 调拨*/
	public static int TYPE_DIAOBO=3;
	/*4. 回退*/
	public static int TYPE_HUITUI=4;

	private int inventoryBill_id;
	private Company hiscompany;
	private Date buytime;
	private Date createtime=new Date();
	private Date inventoryBill_opertime=new Date();
	private int inventoryBill_statue=1;
	private int inventoryBill_type;
	private User user;
	private House hisHouse;
	private List<HisInventoryHistory> historyList;
	
	private String serNID;
	
	public HisInventoryBill(){
		this.historyList = new ArrayList<HisInventoryHistory>();
		this.hiscompany = new Company();
		this.user = new User();
		this.hisHouse = new House();
	}
	
	
	public String getSerNID() {
		if(inventoryBill_id<10){
			serNID = "000000"+inventoryBill_id;
		}
		if(inventoryBill_id<100&&inventoryBill_id>=10){
			serNID = "00000"+inventoryBill_id;
		}
		if(inventoryBill_id<1000&&inventoryBill_id>=100){
			serNID = "0000"+inventoryBill_id;
		}
		if(inventoryBill_id<10000&&inventoryBill_id>=1000){
			serNID = "000"+inventoryBill_id;
		}
		if(inventoryBill_id<100000&&inventoryBill_id>=10000){
			serNID = "00"+inventoryBill_id;
		}
		if(inventoryBill_id<1000000&&inventoryBill_id>=100000){
			serNID = "0"+inventoryBill_id;
		}
		return serNID;
	}

	public List<HisInventoryHistory> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<HisInventoryHistory> historyList) {
		this.historyList = historyList;
	}

	public int getInventoryBill_id() {
		return inventoryBill_id;
	}

	public void setInventoryBill_id(int inventoryBill_id) {
		this.inventoryBill_id = inventoryBill_id;
	}

	
	public Date getBuytime() {
		return buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getInventoryBill_opertime() {
		return inventoryBill_opertime;
	}

	public void setInventoryBill_opertime(Date inventoryBill_opertime) {
		this.inventoryBill_opertime = inventoryBill_opertime;
	}

	public int getInventoryBill_statue() {
		return inventoryBill_statue;
	}

	public void setInventoryBill_statue(int inventoryBill_statue) {
		this.inventoryBill_statue = inventoryBill_statue;
	}

	public int getInventoryBill_type() {
		return inventoryBill_type;
	}

	public void setInventoryBill_type(int inventoryBill_type) {
		this.inventoryBill_type = inventoryBill_type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Company getHiscompany() {
		return hiscompany;
	}

	public void setHiscompany(Company hiscompany) {
		this.hiscompany = hiscompany;
	}

	public House getHisHouse() {
		return hisHouse;
	}

	public void setHisHouse(House hisHouse) {
		this.hisHouse = hisHouse;
	}

	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		HisInventoryBill hisinventoryBill=new HisInventoryBill();
		try {
			hisinventoryBill.setInventoryBill_id(rs.getInt("inventoryBill_id"));
		} catch (Exception e8) {
			// TODO Auto-generated catch block
			e8.printStackTrace();
		}
		try {
			Company company=(Company)new Company().mapRow(rs, rownum);
			hisinventoryBill.setHiscompany(company);
		} catch (Exception e7) {
			// TODO Auto-generated catch block
			e7.printStackTrace();
		}
		try {
			hisinventoryBill.setBuytime(rs.getTimestamp("buytime"));
		} catch (Exception e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		try {
			hisinventoryBill.setCreatetime(rs.getTimestamp("createtime"));
		} catch (Exception e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		try {
			hisinventoryBill.setInventoryBill_opertime(rs.getTimestamp("inventoryBill_opertime"));
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		try {
			hisinventoryBill.setInventoryBill_statue(rs.getInt("inventoryBill_statue"));
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			hisinventoryBill.setInventoryBill_type(rs.getInt("inventoryBill_type"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			User user=(User)new User().mapRow(rs, rownum);
			hisinventoryBill.setUser(user);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			House house=(House)new House().mapRow(rs, rownum);
			hisinventoryBill.setHisHouse(house);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return hisinventoryBill;
	}

}
