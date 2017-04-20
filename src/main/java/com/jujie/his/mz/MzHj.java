package com.jujie.his.mz;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class MzHj extends BaseBean {
	private Integer    order_id;//就诊号
    private Integer medicinal_num;//药品数量
	private Integer invertory_id;//药品库存ID
	 

	public Integer getOrder_id() {
		return order_id;
	}


	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
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


	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		MzHj mzHj = new MzHj();
     	mzHj.setInvertory_id(rs.getInt("invertory_id"));//药品库存ID
		mzHj.setMedicinal_num(rs.getInt("medicinal_num"));//药品数量
	    return mzHj;
	}
	
	
}
