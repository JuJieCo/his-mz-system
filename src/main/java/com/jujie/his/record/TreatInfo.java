package com.jujie.his.record;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;
import com.jujie.his.baseinfo.Medicinal;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.Sick;
import com.jujie.his.mz.MzJz;
import com.jujie.his.mz.MzYp;

public class TreatInfo extends BaseBean {

	
	private Sick sick; //病人
	private GHOrder ghorder;//就诊号
	private MzJz mzjz;//门诊诊疗信息
	private MzYp mzyp;//药品明细
	private Medicinal medicinal;//药品
	
	public Sick getSick() {
		return sick;
	}

	public void setSick(Sick sick) {
		this.sick = sick;
	}

	public GHOrder getGhorder() {
		return ghorder;
	}

	public void setGhorder(GHOrder ghorder) {
		this.ghorder = ghorder;
	}

	public MzJz getMzjz() {
		return mzjz;
	}

	public void setMzjz(MzJz mzjz) {
		this.mzjz = mzjz;
	}

	public MzYp getMzyp() {
		return mzyp;
	}

	public void setMzyp(MzYp mzyp) {
		this.mzyp = mzyp;
	}

	public Medicinal getMedicinal() {
		return medicinal;
	}

	public void setMedicinal(Medicinal medicinal) {
		this.medicinal = medicinal;
	}

	
	
	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
