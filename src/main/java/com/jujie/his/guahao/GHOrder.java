package com.jujie.his.guahao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jujie.global.BaseBean;
import com.jujie.his.inventory.HisMedicinalInventory;
import com.jujie.his.mz.Fee_Type;
import com.jujie.his.mz.MzCharge;
import com.jujie.his.mz.MzJz;


public class GHOrder extends BaseBean{
	
	private Integer orderId;	//主键ID
	private Integer uuid;
	private GHSit ghsit;//ghsit_id//挂号设置ID
	private Integer orderStatue ;//就诊号状态
	private Sick sick;//sick_id//病人ID
	private Integer orderIndex;//就诊序号
	private GHInfo ghinfo;//挂号信息
	private MzCharge mzCharge;//收费信息
	private List<MzJz>  mzJzList;
	private Integer medicinal_id;//药品ID
	private Integer medicinal_num; //药品数量
	private List<HisMedicinalInventory> medInventoryList;//药品信息
	private List<Fee_Type> feeTypeList;//诊疗收费信息
	private Integer zlflag ;//处方状态信息0 有效 1 无效 2 已结算 划价（已结算不能更改成无效状态）
	public GHOrder(){
	ghsit = new GHSit();
	sick = new Sick();
	ghinfo = new GHInfo();
	mzCharge = new MzCharge();
	mzJzList = new ArrayList<MzJz>();
	feeTypeList = new ArrayList<Fee_Type>();
	}
    public GHInfo getGhinfo() {
		return ghinfo;
	}
	public void setGhinfo(GHInfo ghinfo) {
		this.ghinfo = ghinfo;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public GHSit getGhsit() {
		return ghsit;
	}
	public void setGhsit(GHSit ghsit) {
		this.ghsit = ghsit;
	}
	public Integer getOrderStatue() {
		return orderStatue;
	}
	public void setOrderStatue(Integer orderStatue) {
		this.orderStatue = orderStatue;
	}
	public Sick getSick() {
		return sick;
	}
	public void setSick(Sick sick) {
		this.sick = sick;
	}
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
    public List<HisMedicinalInventory> getMedInventoryList() {
		return medInventoryList;
	}
 	public void setMedInventoryList(List<HisMedicinalInventory> medInventoryList) {
		this.medInventoryList = medInventoryList;
	}
 	public List<MzJz> getMzJzList() {
		return mzJzList;
	}
 	public void setMzJzList(List<MzJz> mzJzList) {
		this.mzJzList = mzJzList;
	}
 	
    public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
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
	
	public MzCharge getMzCharge() {
		return mzCharge;
	}
	public void setMzCharge(MzCharge mzCharge) {
		this.mzCharge = mzCharge;
	}
	
	
	public List<Fee_Type> getFeeTypeList() {
		return feeTypeList;
	}
	public void setFeeTypeList(List<Fee_Type> feeTypeList) {
		this.feeTypeList = feeTypeList;
	}
  
	public Integer getZlflag() {
		return zlflag;
	}
	public void setZlflag(Integer zlflag) {
		this.zlflag = zlflag;
	}
	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
	 GHOrder ghorder = new GHOrder();
	 ghorder.setOrderId(rs.getInt("order_id"));
     ghorder.setOrderStatue(rs.getInt("order_statue"));
	 ghorder.getSick().setSickId(rs.getInt("sick_id"));
	 ghorder.setOrderIndex(rs.getInt("order_index"));
 	 sick = new Sick();
	 sick.setSickName(rs.getString("sick_name"));
	 sick.setSickSex(rs.getInt("sick_sex"));
	 sick.setSickAge(rs.getInt("sick_age"));
	 sick.setSickCosetype(rs.getInt("sick_cosetype"));
	 sick.setSickCasehistory(rs.getString("sick_casehistory"));
	 sick.setSickAddress(rs.getString("sick_address"));
	 sick.setSickId(rs.getInt("sick_id"));
	 sick.setSickTreattype(rs.getInt("sick_treattype"));
	 ghorder.setSick(sick);
	 return ghorder;
	}
}
