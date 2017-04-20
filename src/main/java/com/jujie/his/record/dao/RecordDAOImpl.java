package com.jujie.his.record.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.guahao.Sick;
import com.jujie.his.record.TreatInfo;
import com.jujie.util.DataUtils;
import com.jujie.util.DateUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class RecordDAOImpl extends BaseJdbcDao{
	
	//查询病人基本信息 // 时间以挂号时间为准
	@SuppressWarnings("static-access")
	public List<Sick> queryAllSickInfo(Object[] objs,Page page) throws Exception {
		final List<Sick>  sickList=new ArrayList<Sick>();
		List<Object> obj = new ArrayList<Object>();
		String sql = "select  s.sick_id , s.sick_casehistory , s.sick_name , s.sick_sex , s.sick_age , " +
				" s.sick_cosetype ,s.sick_treattype , s.sick_address  ,  g.ghinfo_dotime" +
				" from his_mz_sick s , his_mz_order o , his_mz_ghinfo g  " +
				" where s.sick_id = o.sick_id and o.order_id = g.order_id ";
		if(objs!=null&&objs.length>0){
			if(!"".equals(objs[0])&&objs[0]!=null&&!"".equals(DateUtils.getSqlDate(objs[0]))){
				sql += "  and g.ghinfo_dotime >= ? ";
				obj.add(objs[0]);
			}
			if(!"".equals(objs[1])&&objs[1]!=null&&!"".equals(DateUtils.getSqlDate(objs[1]))){
				sql += " and g.ghinfo_dotime <= ? ";
				obj.add(objs[1]);
			}
			if(!"".equals(objs[2])&&objs[2]!=null&&!"".equals(DataUtils.getStringK(objs[2]))){
				sql += " and s.sick_name = ? ";
				obj.add(objs[2]);
			}
		}
		sql += " order by s.sick_id desc ";
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
			 	Sick sick = new Sick();
				sick.setSickId(rs.getInt("sick_id"));
				sick.setSickCasehistory(rs.getString("sick_casehistory"));
				sick.setSickName(rs.getString("sick_name"));
				sick.setSickSex(rs.getInt("sick_sex"));
				sick.setSickAge(rs.getInt("sick_age"));
				sick.setSickCosetype(rs.getInt("sick_cosetype"));
				sick.setSickTreattype(rs.getInt("sick_treattype"));
				sick.setSickAddress(rs.getString("sick_address"));
				sickList.add(sick);
			}
		 });
		return sickList;
	}
	
	
	//查询病人就诊信息 // 时间以就诊时间为准
	@SuppressWarnings("static-access")
	public List<TreatInfo> queryAllTreatInfo(Object[] objs,Page page) throws Exception {
		final List<TreatInfo>  sickList=new ArrayList<TreatInfo>();
	
		List<Object> obj = new ArrayList<Object>();
		String sql = "select z.uuid,z.zlinfo_dotime , s.sick_casehistory  ,  s.sick_name , s.sick_sex , z.zlinfo_content , m.order_id" +
				" FROM  his_mz_order AS o , his_mz_sick AS s ,  his_mz_zlinfo AS z , his_mz_medicinaldetail AS m " +
				" WHERE o.sick_id =  s.sick_id AND o.order_id = z.order_id AND o.order_id = m.order_id ";
//		if(objs!=null&&objs.length>0){
//			if(!"".equals(objs[0])&&objs[0]!=null&&!"".equals(DateUtils.getSqlDate(objs[0]))){
//				sql += "  and g.ghinfo_dotime >= ? ";
//				obj.add(objs[0]);
//			}
//			if(!"".equals(objs[1])&&objs[1]!=null&&!"".equals(DateUtils.getSqlDate(objs[1]))){
//				sql += " and g.ghinfo_dotime <= ? ";
//				obj.add(objs[1]);
//			}
//			if(!"".equals(objs[2])&&objs[2]!=null&&!"".equals(DataUtils.getStringK(objs[2]))){
//				sql += " and s.sick_name = ? ";
//				obj.add(objs[2]);
//			}
//		}
		sql += " order by s.sick_id desc ";
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				TreatInfo treat = new TreatInfo();
				treat.getMzjz().setZlinfo_dotime(rs.getDate("zlinfo_dotime"));
				treat.getSick().setSickAddress(rs.getString("sick_casehistory"));
				treat.getSick().setSickName(rs.getString("sickName"));
				treat.getSick().setSickSex(rs.getInt("sick_sex"));
				treat.getMzjz().setZlinfo_content(rs.getString("zlinfo_content"));
				treat.getMzyp().setUuid(rs.getInt("uuid"));
				sickList.add(treat);
			}
		 });
		return sickList;
	}
	
	//查询病人就诊号对应的药品明细（处方）以及药品名称
	public TreatInfo queryMedicinalById(String orderId) throws Exception {
		final String sql =" select  m.medicinal_num , mm.medicinal_name " +
				" from his_mz_medicinaldetail as m  ,  his_mz_medicinal  as  mm " +
				" where m.medicinal_id = mm.medicinal_id  and order_id = "+orderId;
		final TreatInfo treatinfo = new TreatInfo();
		this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				treatinfo.getMzjz().getMzYpList().get(0).setMedicinal_num(rs.getInt("medicinal_num"));
				treatinfo.getMedicinal().setMedicinalName(rs.getString("medicinal_name"));
			}
		 });
		return treatinfo;
	}
}
