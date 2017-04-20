package com.jujie.his.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.Medicinal;

/**
 * 调价单明细
 * 
 * @author
 * 
 */
public class HisChangepriceDetail extends BaseBean{
	private int changepriceDetail_id;
	private HisChangepriceBill hisChangepriceBill;
	private Company hisCompany;
	private String item_code;
	private Medicinal hismedicinal;
	private double old_resale_price;
	private double new_resale_price;

	public Company getHisCompany() {
		return hisCompany;
	}

	public void setHisCompany(Company hisCompany) {
		this.hisCompany = hisCompany;
	}

	public Medicinal getHismedicinal() {
		return hismedicinal;
	}

	public void setHismedicinal(Medicinal hismedicinal) {
		this.hismedicinal = hismedicinal;
	}

	public int getChangepriceDetail_id() {
		return changepriceDetail_id;
	}

	public void setChangepriceDetail_id(int changepriceDetail_id) {
		this.changepriceDetail_id = changepriceDetail_id;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}


	public HisChangepriceBill getHisChangepriceBill() {
		return hisChangepriceBill;
	}

	public void setHisChangepriceBill(HisChangepriceBill hisChangepriceBill) {
		this.hisChangepriceBill = hisChangepriceBill;
	}

	public double getOld_resale_price() {
		return old_resale_price;
	}

	public void setOld_resale_price(double old_resale_price) {
		this.old_resale_price = old_resale_price;
	}

	public double getNew_resale_price() {
		return new_resale_price;
	}

	public void setNew_resale_price(double new_resale_price) {
		this.new_resale_price = new_resale_price;
	}

	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		HisChangepriceDetail hisChangepriceDetail=new HisChangepriceDetail();
		try {
			hisChangepriceDetail.setChangepriceDetail_id(rs.getInt("changepriceDetail_id"));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			HisChangepriceBill hisbill=(HisChangepriceBill)new HisChangepriceBill().mapRow(rs, rownum);
			hisChangepriceDetail.setHisChangepriceBill(hisbill);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			Company hisHouse=(Company)new Company().mapRow(rs, rownum);
			hisChangepriceDetail.setHisCompany(hisHouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Medicinal hismedicinal=(Medicinal)new Medicinal().mapRow(rs, rownum);
			hisChangepriceDetail.setHismedicinal(hismedicinal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			hisChangepriceDetail.setItem_code(rs.getString("item_code"));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			hisChangepriceDetail.setOld_resale_price(rs.getDouble("old_resale_price"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			hisChangepriceDetail.setNew_resale_price(rs.getDouble("new_resale_price"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hisChangepriceDetail;
	}

}
