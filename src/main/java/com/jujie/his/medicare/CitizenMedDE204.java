package com.jujie.his.medicare;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jujie.global.BaseBean;

public class CitizenMedDE204 extends BaseBean {

	
	
	
	private String balancetype;// 结算类别  定额结算  单病种
	private String balancedate;// 结算期 1-12月
	private String opertime;// 操作时间
	
	
	private String addr;// 1 社区
	private String name;// 2 姓名

	private String sex;// 3 性别  男  女
	private String indate;//4 出入院日期
	private String outdate;//4 出入院日期
	private String zycode;// 5 住院号
	private String clies;// 6 出院诊断

	private double zyfytotal;// 7 住院医疗费用总额  204用到
	
	private double qfbztotal;// 9 起付标准（自付）小计 t5
	private double grblzftotal;// 10 个人按比例自付小计 t6
	private double zfyftotal;// 11 自费药品费小计 t1
	private double ybxezftotal;// 12 医保限额以上自费小计 t4
	

	


	

	
	
	private double sumGR; // 8 个人合计 204用到 
	private double sumNum7; // 204合计
	private double sumNum8; // 204合计
	private double sumNum9; // 204合计
	private double sumNum10; // 204合计
	private double sumNum11; // 204合计
	private double sumNum12; // 204合计

	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		
		
		return null;
	}

}
