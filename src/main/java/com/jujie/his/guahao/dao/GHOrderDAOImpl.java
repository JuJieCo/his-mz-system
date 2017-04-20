package com.jujie.his.guahao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.guahao.GHInfo;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.GHSit;
import com.jujie.his.guahao.Sick;
import com.jujie.util.DataUtils;
import com.jujie.util.DateUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class GHOrderDAOImpl extends BaseJdbcDao {
	

	
	/***
	 *获得挂号表列表 
	 * 
	 */
	@SuppressWarnings("static-access")
	public List<GHOrder> queryGHOrder(Page page)throws Exception{	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dotime = sdf.format(new Date());
		final String sql = "select   o.order_id, o.ghsit_id , o.order_statue , o.sick_id , o.order_index , " +
				" g.ghinfo_dotime ,s.sick_name ,s.sick_casehistory ,g.ghinfo_ghmoney , g.ghinfo_zlmoney ,d1.doctor_name , d2.dept_name" +
				" from  his_mz_ghinfo g , his_mz_order o ,   his_mz_sick s , his_mz_ghsit h , his_mz_doctor d1 , his_mz_dept d2" +
				" where g.order_id = o.order_id and o.sick_id = s.sick_id and" +
				" o.ghsit_id = h.ghsit_id and h.doctor_id = d1.doctor_id and h.dept_id = d2.dept_id and g.ghinfo_dotime = "+dotime+
				" order by  o.sick_id desc ";
		
		final List<GHOrder> ghorderList=new ArrayList<GHOrder>();
		List<Object> obj = new ArrayList<Object>();
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				GHOrder ghorder = new GHOrder();
				ghorder.setOrderId(rs.getInt("order_id"));
				ghorder.getGhsit().setGhsitId(rs.getInt("ghsit_id"));
				ghorder.setOrderStatue(rs.getInt("order_statue"));
				ghorder.getGhinfo().setGhinfoDoTime(rs.getDate("ghinfo_dotime"));
				ghorder.setOrderIndex(rs.getInt("order_index"));
				ghorder.getSick().setSickName(rs.getString("sick_name"));
				ghorder.getSick().setSickCasehistory(rs.getString("sick_casehistory"));
				ghorder.getGhinfo().setGhinfoGHMoney(rs.getDouble("ghinfo_ghmoney"));
				ghorder.getGhinfo().setGhinfoZLMoney(rs.getDouble("ghinfo_zlmoney"));
				ghorder.getGhsit().getDoctor().setDoctorName(rs.getString("doctor_name"));
				ghorder.getGhsit().getDept().setDeptName(rs.getString("dept_name"));
				ghorderList.add(ghorder);
			}
		 });
		return ghorderList;
	}
	/**
	 * 
	 *获得挂号预设信息列表 
	 */

	public List<GHSit> queryGHSitList()throws Exception{	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dotime = sdf.format(new Date());
		
		final String sql = "select g.ghsit_id , g.ghsit_type1 , g.ghsit_type2 , g.ghsit_dotime , g.dept_id , " +
				" g.doctor_id , g.ghsit_amnum , g.ghsit_pmnum ,d1.dept_name,  d2.doctor_name " +
				" from his_mz_ghsit g ,his_mz_dept d1 , his_mz_doctor d2 " +
				" where g.dept_id=d1.dept_id  and g.doctor_id=d2.doctor_id and g.ghsit_dotime =" +dotime+
				" order by g.ghsit_id desc ";		
		final List<GHSit> ghsitList=new ArrayList<GHSit>();
		
		this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				GHSit ghsit = new GHSit();
				ghsit.setGhsitId(rs.getInt("ghsit_id"));
				ghsit.setGhsitType1(rs.getInt("ghsit_type1"));
				ghsit.setGhsitType2(rs.getInt("ghsit_type2"));
				ghsit.setGhsitDoTime(rs.getDate("ghsit_dotime"));
				ghsit.getDept().setDeptId(rs.getInt("dept_id"));
				ghsit.getDept().setDeptName(rs.getString("dept_name"));
				ghsit.getDoctor().setDoctorId(rs.getInt("doctor_id"));
				ghsit.getDoctor().setDoctorName(rs.getString("doctor_name"));
				ghsit.setGhsitAmNum(rs.getInt("ghsit_amnum"));
				ghsit.setGhsitPmNum(rs.getInt("ghsit_pmnum"));
				ghsitList.add(ghsit);
			}
		 });
		return ghsitList;
	}
	
	/**
	 * 
	 *保存病人信息 
	 */
	public Integer saveSick(Sick sick) throws Exception {
		final String sql = "insert into his_mz_sick (sick_name,sick_age,sick_sex,sick_cosetype,sick_treattype,sick_address,sick_casehistory,sick_ybcode,sick_code) " +
				"values(?,?,?,?,?,?,?,?,?)";
		final Object[] objs = {sick.getSickName(),sick.getSickAge(),sick.getSickSex(),sick.getSickCosetype(),sick.getSickTreattype(),
				sick.getSickAddress(),sick.getSickCasehistory(),sick.getSickYbCode(),sick.getSickCode()};
				KeyHolder keyHolder = new GeneratedKeyHolder();
				try {
					this.getJdbcTemplate().update(new PreparedStatementCreator(){
						public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
							int i = 0;
							int n = 0;
							PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);						
							ps.setString(++i, 	  DataUtils.getString(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setString(++i, 	  DataUtils.getString(objs[n++]));
							ps.setString(++i, 	  DataUtils.getString(objs[n++]));	
							ps.setString(++i, 	  DataUtils.getString(objs[n++]));	
							ps.setString(++i, 	  DataUtils.getString(objs[n++]));	
							return ps;
						}
					}, keyHolder);
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				return keyHolder.getKey().intValue();	
	}
	
	
	/**
	 *保存就诊号信息 
	 * 
	 */
	public Integer saveGHOrder(GHOrder ghorder) throws Exception {
		final String sql = "insert into his_mz_order (ghsit_id,order_statue,sick_id,order_index) " +
				"values(?,?,?,?)";
		final Object[] objs = {ghorder.getGhsit().getGhsitId(),ghorder.getOrderStatue(),ghorder.getSick().getSickId(),ghorder.getOrderIndex()};
				KeyHolder keyHolder = new GeneratedKeyHolder();
				try {
					this.getJdbcTemplate().update(new PreparedStatementCreator(){
						public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
							int i = 0;
							int n = 0;
							PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							return ps;
						}
					}, keyHolder);
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				return keyHolder.getKey().intValue();	
	}
	
	/**
	 *保存挂号信息
	 * 
	 */
	public void saveGHInfo(GHInfo ghinfo) throws Exception {
		final String sql = "insert into his_mz_ghinfo (order_id ,ghinfo_ghmoney,ghinfo_zlmoney,ghinfo_dotime,sys_user_id) " +
				"values(?,?,?,?,?)";
		final Object[] objs = {ghinfo.getOrderId(), ghinfo.getGhinfoGHMoney(),ghinfo.getGhinfoZLMoney(),ghinfo.getGhinfoDoTime(),ghinfo.getSysUserId()};
				KeyHolder keyHolder = new GeneratedKeyHolder();
				try {
					this.getJdbcTemplate().update(new PreparedStatementCreator(){
						public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
							int i = 0;
							int n = 0;
							PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setDouble(++i, 	  DataUtils.getDouble(objs[n++]));
							ps.setDouble(++i, 	  DataUtils.getDouble(objs[n++]));
							ps.setDate(++i,   DateUtils.getSqlDate(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							return ps;
						}
					}, keyHolder);
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				
	}
	

//	//查询是否号大于0
//	
//	public GHSit selectOperatGHSit(String ghsitId , String ampm ) throws Exception{	
//		final GHSit ghsit = new GHSit();
//		if("am".equals(ampm)){
//		
//			final String sql ="select ghsit_amnum from his_mz_ghsit   where ghsit_id= = "+ghsitId;
//			this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
//				public void processRow(ResultSet rs) throws SQLException {
//					ghsit.setGhsitAmNum(rs.getInt("ghsit_amnum"));	
//				}
//			 });
//		}
//		if("pm".equals(ampm)){
//			final String sql ="select ghsit_pmnum from his_mz_ghsit   where ghsit_id= = "+ghsitId;
//			this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
//				public void processRow(ResultSet rs) throws SQLException {
//					ghsit.setGhsitPmNum(rs.getInt("ghsit_pmnum"));	
//				}
//			 });
//		}	
//		return ghsit;
//	} 

	//挂号操作限号
	
	public void operatGHSit(String ghsitId , String ampm ) throws Exception{	
		if("am".equals(ampm)){
			String  sql = "update his_mz_ghsit set ghsit_amnum= (ghsit_amnum-1)  where ghsit_id=?";
			Object[] objs =  {ghsitId};
			this.getJdbcTemplate().update(sql,objs);
		}
		if("pm".equals(ampm)){
			String  sql = "update his_mz_ghsit set ghsit_pmnum= (ghsit_pmnum-1)  where ghsit_id=?";
			Object[] objs =  {ghsitId};
			this.getJdbcTemplate().update(sql,objs);
		}	
	} 
	
	/**
	 * 
	 * 退号操作  
	 * 不删除记录 只改变状态
	 */

	public void deleteGHOrder(GHOrder ghorder) throws Exception{

		String sql = "update his_mz_order set order_statue=? where order_id=?";
		Object[] objs = {ghorder.getOrderStatue() , ghorder.getOrderId()};
		this.getJdbcTemplate().update(sql,objs);
	} 
	
	
	//退号返还号
	public void reOperatGHSit(String ghsitId , String ampm ) throws Exception{	
		if("am".equals(ampm)){
			String  sql = "update his_mz_ghsit set ghsit_amnum= (ghsit_amnum+1)  where ghsit_id=?";
			Object[] objs =  {ghsitId};
			this.getJdbcTemplate().update(sql,objs);
		}
		if("pm".equals(ampm)){
			String  sql = "update his_mz_ghsit set ghsit_pmnum= (ghsit_pmnum+1)  where ghsit_id=?";
			Object[] objs =  {ghsitId};
			this.getJdbcTemplate().update(sql,objs);
		}	
	} 
	
	
	
	// 查询单条 用作退号事先查询状态

	public GHOrder queryGHOrderByID(Integer  orderId) throws Exception{
		final String sql ="select order_statue from his_mz_order  where order_id = "+orderId;
		final GHOrder ghorder = new GHOrder();
		this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ghorder.setOrderStatue(rs.getInt("order_statue"));	
			}
		 });
		return ghorder;
	}


}
