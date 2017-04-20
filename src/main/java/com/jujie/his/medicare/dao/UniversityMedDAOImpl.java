package com.jujie.his.medicare.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.medicare.UniversityMedDE;
import com.jujie.his.medicare.UniversityMedDEBAK;
import com.jujie.his.medicare.UniversityMedTotal;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class UniversityMedDAOImpl extends BaseJdbcDao{

	public List<UniversityMedDE> queryUniversityMedDEList(Page page,Map<String,String> map)throws Exception{
		String sql = "select * from  his_yb_university where university_fromdate>='"+map.get("sdate")+"%' and university_fromdate<'"+map.get("edate")+"%' ";
		if(map.get("id")!=null){
			sql += " and university_id="+map.get("id") ;
		}
		sql += " order by university_id desc ";
		List<Object> obj = new ArrayList<Object>();
		if(page!=null){
			sql = PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL);
		}
		return this.getJdbcTemplate().query(sql,obj.toArray(),new UniversityMedDE());
	}
	
	public List<UniversityMedDEBAK> queryUniversityMedDEBAKList(int id)throws Exception{
		final String sql = "select * from  his_yb_university_bak where university_id="+id+" order by university_update asc";
		return this.getJdbcTemplate().query(sql,new UniversityMedDEBAK());
	}
	
	public UniversityMedDEBAK queryOneUniversityMedDEBAK(String id,String update)throws Exception{
		final String sql = "select * from  his_yb_university_bak where university_id="+id+" and university_update='"+update+"' order by university_update asc";
		return (UniversityMedDEBAK)this.getJdbcTemplate().query(sql,new UniversityMedDEBAK()).get(0);
	}
	
	public UniversityMedDE queryUniversityMedDE(int id)throws Exception{
		String sql = "select * from  his_yb_university where university_id="+id;
		return (UniversityMedDE)this.getJdbcTemplate().query(sql,new UniversityMedDE()).get(0);
	}
	
	public List<UniversityMedDE> queryUniversityMedDEListFromMZ(Page page,Map<String,String> map)throws Exception{
//		StringBuffer sb = new StringBuffer("SELECT * FROM "); 
//		sb.append("(SELECT sk.sick_ybcode university_ycode,COUNT(ho.order_id) university_mztimes,GROUP_CONCAT(zl.zlinfo_content) university_ccontent,(SUM(hj.hjinfo_xytm)+SUM(hj.hjinfo_zcytm)+SUM(hj.hjinfo_zcytm2)) university_medcost, ")
//		.append("SUM(hj.hjinfo_jctm) university_checkcost,SUM(hj.hjinfo_hytm) university_assaycost,SUM(hj.hjinfo_zltm2) university_treatcost,SUM(hj.hjinfo_othertm) university_othercost, ")
//		.append("(SUM(hj.hjinfo_xytm)+SUM(hj.hjinfo_zcytm)+SUM(hj.hjinfo_zcytm2)+SUM(hj.hjinfo_jctm)+SUM(hj.hjinfo_hytm)+SUM(hj.hjinfo_zltm2)+SUM(hj.hjinfo_othertm)) university_mztotalcost  ")
//		.append("FROM his_mz_order ho ")
//		.append("INNER JOIN his_mz_sick sk ON ho.sick_id=sk.sick_id ")
//		.append("INNER JOIN his_mz_zlinfo zl ON ho.order_id=zl.order_id ")
//		.append("INNER JOIN his_mz_hjinfo hj ON ho.order_id=hj.order_id ")
//		.append("WHERE zl.zlinfo_dotime >= '"+map.get("sdate")+"%' AND zl.zlinfo_dotime < '"+map.get("edate")+"%' ")
//		.append("GROUP BY sk.sick_ybcode ) a ")
//		.append("INNER JOIN ")
//		.append("(SELECT ho.order_id university_jcode,sk.sick_name university_pname,sk.sick_sex university_psex,MAX(sk.sick_age) university_page,sk.sick_ybcode,sk.sick_code university_pcode,MIN(zl.zlinfo_dotime) university_mzdate ")
//		.append("FROM his_mz_order ho ")
//		.append("INNER JOIN his_mz_sick sk ON ho.sick_id=sk.sick_id ")
//		.append("INNER JOIN his_mz_zlinfo zl ON ho.order_id=zl.order_id ")
//		.append("WHERE zl.zlinfo_dotime >= '"+map.get("sdate")+"%' AND zl.zlinfo_dotime < '"+map.get("edate")+"%' ")
//		.append("GROUP BY sk.sick_ybcode ) b ")
//		.append("ON a.university_ycode = b.sick_ybcode ")
//		.append("WHERE university_ycode NOT IN (SELECT university_ycode FROM his_yb_university WHERE university_fromdate >= '"+map.get("sdate")+"%' AND university_fromdate < '"+map.get("edate")+"%') ");
//		if(map.get("ybcode")!=null){
//			sb.append(" and university_ycode='"+map.get("ybcode")+"'" );
//		}
		StringBuffer sb = new StringBuffer("SELECT *, (university_medcost+university_checkcost+university_assaycost+university_treatcost+university_othercost) university_mztotalcost,  "); 
		sb.append("(university_medcost+university_checkcost+university_assaycost+university_treatcost+university_othercost) university_mztc,  ");
		sb.append("(university_medcost+university_checkcost+university_assaycost+university_treatcost+university_othercost)*0.7 university_mztcbx  FROM ");
		sb.append("(SELECT sk.sick_ybcode university_ycode,COUNT(ho.order_id) university_mztimes,GROUP_CONCAT(zl.zlinfo_content) university_ccontent,");
		sb.append("(SUM(hj.hjinfo_xytm)+SUM(hj.hjinfo_zcytm)+SUM(hj.hjinfo_zcytm2)) university_medcost ");
	//	sb.append("(SUM(hj.hjinfo_xytm)+SUM(hj.hjinfo_zcytm)+SUM(hj.hjinfo_zcytm2)) university_mztotalcost  ");
		sb.append("FROM his_mz_order ho ");
		sb.append("INNER JOIN his_mz_sick sk ON ho.sick_id=sk.sick_id ");
		sb.append("INNER JOIN his_mz_zlinfo zl ON ho.order_id=zl.order_id ");
		sb.append("INNER JOIN his_mz_hjinfo hj ON zl.uuid=hj.uuid ");
		sb.append("WHERE zl.zlinfo_dotime >= '"+map.get("sdate")+"%' AND zl.zlinfo_dotime < '"+map.get("edate")+"%' ");
		sb.append("GROUP BY sk.sick_ybcode ) a ");
	    sb.append("INNER JOIN ");
		sb.append("(SELECT ho.order_id university_jcode,sk.sick_name university_pname,sk.sick_sex university_psex,MAX(sk.sick_age) university_page,sk.sick_ybcode,sk.sick_code university_pcode,MIN(zl.zlinfo_dotime) university_mzdate ");
		sb.append("FROM his_mz_order ho ");
		sb.append("INNER JOIN his_mz_sick sk ON ho.sick_id=sk.sick_id ");
		sb.append("INNER JOIN his_mz_zlinfo zl ON ho.order_id=zl.order_id ");
		sb.append("WHERE zl.zlinfo_dotime >= '"+map.get("sdate")+"%' AND zl.zlinfo_dotime < '"+map.get("edate")+"%'");
		sb.append("GROUP BY sk.sick_ybcode ) b ");
		sb.append("ON a.university_ycode = b.sick_ybcode ");
		sb.append("LEFT JOIN ");
		sb.append("(SELECT f1.order_id,SUM(f1.hjinfo_totalM) university_checkcost,SUM(f2.hjinfo_totalM) university_assaycost,SUM(f3.hjinfo_totalM) university_treatcost,SUM(f4.hjinfo_totalM) university_othercost FROM feetype f1 LEFT JOIN feetype f2 ON f1.order_id=f2.order_id ");
		sb.append("LEFT JOIN feetype f3 ON f2.order_id=f3.order_id LEFT JOIN feetype f4 ON f3.order_id=f4.order_id ");
	    sb.append("WHERE f1.hjinfo_dotime >= '"+map.get("sdate")+"%' AND f1.hjinfo_dotime < '"+map.get("edate")+"%' AND f2.hjinfo_dotime >= '"+map.get("sdate")+"%' AND f2.hjinfo_dotime < '"+map.get("edate")+"%' ");
		sb.append("AND f3.hjinfo_dotime >= '"+map.get("sdate")+"%' AND f3.hjinfo_dotime < '"+map.get("edate")+"%' AND f4.hjinfo_dotime >= '"+map.get("sdate")+"%' AND f4.hjinfo_dotime < '"+map.get("edate")+"%' ");
		sb.append("AND f1.jytpye=1 AND f2.jytpye=2 AND f3.jytpye=3 AND f4.jytpye=4) c ");
		sb.append("ON b.university_jcode=c.order_id ");
		sb.append("WHERE university_ycode NOT IN (SELECT university_ycode FROM  his_yb_university WHERE university_fromdate >= '"+map.get("sdate")+"%' AND university_fromdate < '"+map.get("edate")+"%') ");
		if(map.get("ybcode")!=null){
			sb.append(" and university_ycode='"+map.get("ybcode")+"'" );
		}

		List<Object> obj = new ArrayList<Object>();
		String sql = sb.toString();
		if(page!=null){
			sql = PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL);
		}
		return this.getJdbcTemplate().query(sql,obj.toArray(),new UniversityMedDE());
	}
	
	public void saveUniversityMedDE(UniversityMedDE universityMedDE){
		String sql = "insert into his_yb_university(university_hcode,university_hname,university_jcode,university_pname,university_psex,university_page,university_ycode,university_pcode," +
				"university_name,university_ptype,university_mztimes,university_mzdate,university_istomz,university_ccontent,university_yeartctotal,university_yearjjtotal,university_mzyear," +
				"university_mztotalcost,university_medcost,university_checkcost,university_assaycost,university_treatcost,university_othercost,university_ppaytotal,university_mztc," +
				"university_remark,university_paystand,university_applyper,university_ppay,university_mztcbx,university_opertime,university_statue,university_fromdate) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] objs = {universityMedDE.getUniversity_hcode(),universityMedDE.getUniversity_hname(),universityMedDE.getUniversity_jcode(),universityMedDE.getUniversity_pname(),universityMedDE.getUniversity_psex(),universityMedDE.getUniversity_page(),universityMedDE.getUniversity_ycode(),universityMedDE.getUniversity_pcode(),
				universityMedDE.getUniversity_name(),universityMedDE.getUniversity_ptype(),universityMedDE.getUniversity_mztimes(),universityMedDE.getUniversity_mzdate(),universityMedDE.getUniversity_istomz(),universityMedDE.getUniversity_ccontent(),universityMedDE.getUniversity_yeartctotal(),universityMedDE.getUniversity_yearjjtotal(),universityMedDE.getUniversity_mzyear(),
				universityMedDE.getUniversity_mztotalcost(),universityMedDE.getUniversity_medcost(),universityMedDE.getUniversity_checkcost(),universityMedDE.getUniversity_assaycost(),universityMedDE.getUniversity_treatcost(),universityMedDE.getUniversity_othercost(),universityMedDE.getUniversity_ppaytotal(),universityMedDE.getUniversity_mztc(),
				universityMedDE.getUniversity_remark(),universityMedDE.getUniversity_paystand(),universityMedDE.getUniversity_applyper(),universityMedDE.getUniversity_ppay(),universityMedDE.getUniversity_mztcbx(),universityMedDE.getUniversity_opertime(),universityMedDE.getUniversity_statue(),universityMedDE.getUniversity_fromdate()};
		this.getJdbcTemplate().update(sql, objs);
	}
	
	public void saveUniversityMedDEBAK(UniversityMedDEBAK universityMedDEBAK){
		String sql = "insert into his_yb_university_bak(university_id,university_hcode,university_hname,university_jcode,university_pname,university_psex,university_page,university_ycode,university_pcode," +
				"university_name,university_ptype,university_mztimes,university_mzdate,university_istomz,university_ccontent,university_yeartctotal,university_yearjjtotal,university_mzyear," +
				"university_mztotalcost,university_medcost,university_checkcost,university_assaycost,university_treatcost,university_othercost,university_ppaytotal,university_mztc," +
				"university_remark,university_paystand,university_applyper,university_ppay,university_mztcbx,university_opertime,university_statue,university_fromdate,university_update) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] objs = {universityMedDEBAK.getUniversity_id(),universityMedDEBAK.getUniversity_hcode(),universityMedDEBAK.getUniversity_hname(),universityMedDEBAK.getUniversity_jcode(),universityMedDEBAK.getUniversity_pname(),universityMedDEBAK.getUniversity_psex(),universityMedDEBAK.getUniversity_page(),universityMedDEBAK.getUniversity_ycode(),universityMedDEBAK.getUniversity_pcode(),
				universityMedDEBAK.getUniversity_name(),universityMedDEBAK.getUniversity_ptype(),universityMedDEBAK.getUniversity_mztimes(),universityMedDEBAK.getUniversity_mzdate(),universityMedDEBAK.getUniversity_istomz(),universityMedDEBAK.getUniversity_ccontent(),universityMedDEBAK.getUniversity_yeartctotal(),universityMedDEBAK.getUniversity_yearjjtotal(),universityMedDEBAK.getUniversity_mzyear(),
				universityMedDEBAK.getUniversity_mztotalcost(),universityMedDEBAK.getUniversity_medcost(),universityMedDEBAK.getUniversity_checkcost(),universityMedDEBAK.getUniversity_assaycost(),universityMedDEBAK.getUniversity_treatcost(),universityMedDEBAK.getUniversity_othercost(),universityMedDEBAK.getUniversity_ppaytotal(),universityMedDEBAK.getUniversity_mztc(),
				universityMedDEBAK.getUniversity_remark(),universityMedDEBAK.getUniversity_paystand(),universityMedDEBAK.getUniversity_applyper(),universityMedDEBAK.getUniversity_ppay(),universityMedDEBAK.getUniversity_mztcbx(),universityMedDEBAK.getUniversity_opertime(),universityMedDEBAK.getUniversity_statue(),universityMedDEBAK.getUniversity_fromdate(),new Date()};
		this.getJdbcTemplate().update(sql, objs);
	}
	
	public void editUniversityMedDE(UniversityMedDE universityMedDE){
		String sql = "update his_yb_university set university_hcode=?,university_hname=?,university_jcode=?,university_pname=?,university_psex=?,university_page=?,university_ycode=?,university_pcode=?," +
		"university_name=?,university_ptype=?,university_mztimes=?,university_mzdate=?,university_istomz=?,university_ccontent=?,university_yeartctotal=?,university_yearjjtotal=?,university_mzyear=?," +
		"university_mztotalcost=?,university_medcost=?,university_checkcost=?,university_assaycost=?,university_treatcost=?,university_othercost=?,university_ppaytotal=?,university_mztc=?," +
		"university_remark=?,university_paystand=?,university_applyper=?,university_ppay=?,university_mztcbx=?,university_opertime=?,university_statue=?,university_fromdate=? " +
		"where university_id=? ";
		Object[] objs = {universityMedDE.getUniversity_hcode(),universityMedDE.getUniversity_hname(),universityMedDE.getUniversity_jcode(),universityMedDE.getUniversity_pname(),universityMedDE.getUniversity_psex(),universityMedDE.getUniversity_page(),universityMedDE.getUniversity_ycode(),universityMedDE.getUniversity_pcode(),
				universityMedDE.getUniversity_name(),universityMedDE.getUniversity_ptype(),universityMedDE.getUniversity_mztimes(),universityMedDE.getUniversity_mzdate(),universityMedDE.getUniversity_istomz(),universityMedDE.getUniversity_ccontent(),universityMedDE.getUniversity_yeartctotal(),universityMedDE.getUniversity_yearjjtotal(),universityMedDE.getUniversity_mzyear(),
				universityMedDE.getUniversity_mztotalcost(),universityMedDE.getUniversity_medcost(),universityMedDE.getUniversity_checkcost(),universityMedDE.getUniversity_assaycost(),universityMedDE.getUniversity_treatcost(),universityMedDE.getUniversity_othercost(),universityMedDE.getUniversity_ppaytotal(),universityMedDE.getUniversity_mztc(),
				universityMedDE.getUniversity_remark(),universityMedDE.getUniversity_paystand(),universityMedDE.getUniversity_applyper(),universityMedDE.getUniversity_ppay(),universityMedDE.getUniversity_mztcbx(),universityMedDE.getUniversity_opertime(),universityMedDE.getUniversity_statue(),universityMedDE.getUniversity_fromdate(),
				universityMedDE.getUniversity_id()};
		this.getJdbcTemplate().update(sql, objs);
	}
	
	public UniversityMedTotal queryUniversityMedTotalByMonth(Map<String,String> map)throws Exception{
		String sql = "select COUNT(*) p_num,SUM(university_mztcbx) tcbx  FROM his_yb_university where university_mzdate>='"+map.get("sdate")+"%' and university_mzdate<'"+map.get("edate")+"%' ";
		return (UniversityMedTotal)this.getJdbcTemplate().query(sql,new UniversityMedTotal()).get(0);
	}
}
