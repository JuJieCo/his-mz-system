package com.jujie.his.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.jujie.global.BaseBean;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.House;
import com.jujie.his.baseinfo.Medicinal;

/**
 * 药品库存
 * @author 
 *
 */
public class HisMedicinalInventory  extends BaseBean{
	
	/*1. 药库*/
	public static  int TYPE_KUFANG = 1;
	/*2. 药房*/
	public static  int TYPE_YAOFANG = 2;
	
	private int invertory_id;
	private Company company;
	private String item_code;
	private Medicinal medicinal;
	private Date validtime;
	private int hqty;
	private int rqty;
	private double purchase_price;
	private double resale_price;
	private int inventory_type;
	private House house;
	private int meidicinal_sum;//剩余数量
	
    public int getMeidicinal_sum() {
		return meidicinal_sum;
	}


	public void setMeidicinal_sum(int meidicinal_sum) {
		this.meidicinal_sum = meidicinal_sum;
	}


	public HisMedicinalInventory(){
    	this.company = new Company();
    	this.medicinal = new Medicinal();
    	this.house = new House();
    }
	
	
	public int getInvertory_id() {
		return invertory_id;
	}


	public void setInvertory_id(int invertory_id) {
		this.invertory_id = invertory_id;
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


	public int getHqty() {
		return hqty;
	}


	public void setHqty(int hqty) {
		this.hqty = hqty;
	}


	public int getRqty() {
		return rqty;
	}


	public void setRqty(int rqty) {
		this.rqty = rqty;
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


	public int getInventory_type() {
		return inventory_type;
	}


	public void setInventory_type(int inventory_type) {
		this.inventory_type = inventory_type;
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


	public House getHouse() {
		return house;
	}


	public void setHouse(House house) {
		this.house = house;
	}


	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		HisMedicinalInventory inventory=new HisMedicinalInventory();
		try {
			inventory.setInvertory_id(rs.getInt("invertory_id"));
		} catch (Exception e10) {
			// TODO Auto-generated catch block
			e10.printStackTrace();
		}
		try {
			Company hiscompany=(Company)new Company().mapRow(rs, rownum);
			inventory.setCompany(hiscompany);
		} catch (Exception e9) {
			// TODO Auto-generated catch block
			e9.printStackTrace();
		}
		try {
			inventory.setItem_code(rs.getString("item_code"));
		} catch (Exception e8) {
			// TODO Auto-generated catch block
			e8.printStackTrace();
		}
		try {
			Medicinal hismedicinal=(Medicinal)new Medicinal().mapRow(rs, rownum);
			inventory.setMedicinal(hismedicinal);
		} catch (Exception e7) {
			// TODO Auto-generated catch block
			e7.printStackTrace();
		}
		try {
			inventory.setValidtime(rs.getTimestamp("validtime"));
		} catch (Exception e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		try {
			inventory.setHqty(rs.getInt("hqty"));
		} catch (Exception e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		try {
			inventory.setRqty(rs.getInt("rqty"));
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		try {
			inventory.setPurchase_price(rs.getDouble("purchase_price"));
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			inventory.setResale_price(rs.getDouble("resale_price"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			inventory.setInventory_type(rs.getInt("inventory_type"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			House hishouse=(House)new House().mapRow(rs, rownum);
			inventory.setHouse(hishouse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventory;
	}

}
