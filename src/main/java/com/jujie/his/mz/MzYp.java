package com.jujie.his.mz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jujie.global.BaseBean;
import com.jujie.his.inventory.HisMedicinalInventory;

public class MzYp extends BaseBean {
	private int id;
	private int uuid;//就诊编号
 	private Integer medicinal_id;//药品ID
	private Integer medicinal_num;//药品数量
	private Integer invertory_id;//药品库存ID
	private Integer medInvSize;//药房剩余SIZE
	private int flag ; //有效标志
	private List<HisMedicinalInventory> medicinalInventoryList;//药房剩余，单价

	
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public Integer getMedicinal_id() {
		return medicinal_id;
	}

	public void setMedicinal_id(Integer medicinal_id) {
		this.medicinal_id = medicinal_id;
	}

	public Integer getMedicinal_num() {
		return medicinal_num;
	}

	public void setMedicinal_num(Integer medicinal_num) {
		this.medicinal_num = medicinal_num;
	}

	public Integer getInvertory_id() {
		return invertory_id;
	}

	public void setInvertory_id(Integer invertory_id) {
		this.invertory_id = invertory_id;
	}
	
	public List<HisMedicinalInventory> getMedicinalInventoryList() {
		return medicinalInventoryList;
	}

	public void setMedicinalInventoryList(
			List<HisMedicinalInventory> medicinalInventoryList) {
		this.medicinalInventoryList = medicinalInventoryList;
	}

	public MzYp(){
	    medicinalInventoryList = new ArrayList<HisMedicinalInventory>();
	    }
	
	
	public Integer getMedInvSize() {
		return medInvSize;
	}

	public void setMedInvSize(Integer medInvSize) {
		this.medInvSize = medInvSize;
	}

	@Override
	public MzYp mapRow(ResultSet rs, int rownum) throws SQLException {
		MzYp mzYp = new MzYp();
		mzYp.setId(rs.getInt("id"));
		mzYp.setUuid(rs.getInt("uuid"));
		mzYp.setInvertory_id(rs.getInt("invertory_id"));
		mzYp.setMedicinal_id(rs.getInt("medicinal_id"));
		mzYp.setMedicinal_num(rs.getInt("medicinal_num"));
	   
		return mzYp;
	}
	
	
}
