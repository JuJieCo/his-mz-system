package com.jujie.his.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jujie.global.BaseBean;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.Medicinal;

/**
 * 出入库历史
 * @author 
 *
 */
public class HisInventoryHistory extends BaseBean {
 private int inventoryHistory_id;
 private Company company;
 private String item_code;
 private Medicinal medicinal;
 private Date validtime=new Date();
 private Date buytime=new Date();
 private Date opertime=new Date();
 private int hqty;
 private double purchase_price;
 private double resale_price;
 private HisInventoryBill inventoryBill;
 
 public HisInventoryHistory(){
	 this.company = new Company();
	 this.medicinal = new Medicinal();
	 this.inventoryBill = new HisInventoryBill();
 }
 
public Company getCompany() {
	return company;
}
public void setCompany(Company company) {
	this.company = company;
}
public Medicinal getMedicinal() {
	return medicinal;
}
public void setMedicinal(Medicinal medicinal) {
	this.medicinal = medicinal;
}
public int getInventoryHistory_id() {
	return inventoryHistory_id;
}
public void setInventoryHistory_id(int inventoryHistory_id) {
	this.inventoryHistory_id = inventoryHistory_id;
}

public String getItem_code() {
	return item_code;
}
public void setItem_code(String item_code) {
	this.item_code = item_code;
}

public Date getValidtime() {
	return validtime;
}
public void setValidtime(Date validtime) {
	this.validtime = validtime;
}
public Date getBuytime() {
	return buytime;
}
public void setBuytime(Date buytime) {
	this.buytime = buytime;
}
public Date getOpertime() {
	return opertime;
}
public void setOpertime(Date opertime) {
	this.opertime = opertime;
}
public int getHqty() {
	return hqty;
}
public void setHqty(int hqty) {
	this.hqty = hqty;
}
public double getPurchase_price() {
	return purchase_price;
}
public void setPurchase_price(double purchase_price) {
	this.purchase_price = purchase_price;
}
public double getResale_price() {
	return resale_price;
}
public void setResale_price(double resale_price) {
	this.resale_price = resale_price;
}
public HisInventoryBill getInventoryBill() {
	return inventoryBill;
}
public void setInventoryBill(HisInventoryBill inventoryBill) {
	this.inventoryBill = inventoryBill;
}
@Override
public Object mapRow(ResultSet rs, int rownum) throws SQLException {
	 HisInventoryHistory history=new HisInventoryHistory();
	 try {
		history.setInventoryHistory_id(rs.getInt("inventoryHistory_id"));
		} catch (Exception e10) {
		// TODO Auto-generated catch block
		e10.printStackTrace();
	}
	 try {
		Company hiscompany=(Company)new Company().mapRow(rs, rownum);
		 history.setCompany(hiscompany);
	} catch (Exception e9) {
		// TODO Auto-generated catch block
		e9.printStackTrace();
	}
	 try {
		history.setItem_code(rs.getString("item_code"));
	} catch (Exception e8) {
		// TODO Auto-generated catch block
		e8.printStackTrace();
	}
	 try {
		Medicinal hismedicinal=(Medicinal)new Medicinal().mapRow(rs, rownum);
		 history.setMedicinal(hismedicinal);
	} catch (Exception e7) {
		// TODO Auto-generated catch block
		e7.printStackTrace();
	}
	 try {
		history.setValidtime(rs.getTimestamp("validtime"));
	} catch (Exception e6) {
		// TODO Auto-generated catch block
		e6.printStackTrace();
	}
	 try {
		history.setBuytime(rs.getTimestamp("buytime"));
	} catch (Exception e5) {
		// TODO Auto-generated catch block
		e5.printStackTrace();
	}
	 try {
		history.setOpertime(rs.getTimestamp("opertime"));
	} catch (Exception e4) {
		// TODO Auto-generated catch block
		e4.printStackTrace();
	}
	 try {
		history.setHqty(rs.getInt("hqty"));
	} catch (Exception e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}
	 try {
		history.setPurchase_price(rs.getDouble("purchase_price"));
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	 try {
		history.setResale_price(rs.getDouble("resale_price"));
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 try {
		HisInventoryBill inbentorybill=(HisInventoryBill)new HisInventoryBill().mapRow(rs, rownum);
		 history.setInventoryBill(inbentorybill);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return history;
}
 
}
