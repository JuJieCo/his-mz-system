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
import com.jujie.his.baseinfo.Dept;
import com.jujie.his.baseinfo.DoctorDept;
import com.jujie.his.guahao.GHSit;
import com.jujie.util.DataUtils;
import com.jujie.util.DateUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class GHSitDAOImpl extends BaseJdbcDao {
	

	/**
	 * 
	 *获得挂号预设信息列表 
	 */
	@SuppressWarnings("static-access")
	public List<GHSit> queryGHSitList(Page page)throws Exception{	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dotime = sdf.format(new Date());
		
		final String sql = "select g.ghsit_id , g.ghsit_type1 , g.ghsit_type2 , g.ghsit_dotime , g.dept_id , " +
				" g.doctor_id , g.ghsit_amnum , g.ghsit_pmnum ,d1.dept_name,  d2.doctor_name " +
				" from his_mz_ghsit g ,his_mz_dept d1 , his_mz_doctor d2 " +
				" where g.dept_id=d1.dept_id  and g.doctor_id=d2.doctor_id and g.ghsit_dotime >=" +dotime+
				" order by g.ghsit_id desc ";		
		final List<GHSit> ghsitList=new ArrayList<GHSit>();
		List<Object> obj = new ArrayList<Object>();
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){
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
	 *获得科室列表 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Dept> queryDeptList()throws Exception{	

		final String sql = "select d.* from  his_mz_dept d where d.dept_statue=1  ";
		return this.getJdbcTemplate().query(sql, new Dept());
	}
	
	
	/**
	 * 
	 *获得科室对应的医生列表 
	 * 
	 */
	public List<DoctorDept> queryDoctorDept()throws Exception{
		
		final String sql = "select   d.doctor_id , d.doctor_name , dd.dept_id from his_mz_doctor d , doctor_dept dd " +
				" where d.doctor_id = dd.doctor_id and d.doctor_statue=1";
		
		final List<DoctorDept> ddList=new ArrayList<DoctorDept>();
		
		this.getJdbcTemplate().query(sql, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				DoctorDept doctorDept = new DoctorDept();
				doctorDept.getDoctor().setDoctorId(rs.getInt("doctor_id"));
				doctorDept.getDoctor().setDoctorName(rs.getString("doctor_name"));
				/////doctorDept.setDeptId(rs.getInt("dept_id"));
				doctorDept.getDept().setDeptId(rs.getInt("dept_id"));
				ddList.add(doctorDept);
			}
		 });
		return ddList;
	}
	
	
	
	public Integer saveGHSit(GHSit ghsit) throws Exception {
		final String sql = "insert into his_mz_ghsit (ghsit_type1,ghsit_type2,ghsit_dotime, "
				+ " dept_id,doctor_id,ghsit_amnum,ghsit_pmnum) values(?,?,?,?,?,?,?)";
		final Object[] objs = { ghsit.getGhsitType1(),ghsit.getGhsitType2(),ghsit.getGhsitDoTime(),
				ghsit.getDept().getDeptId(),ghsit.getDoctor().getDoctorId(),
				ghsit.getGhsitAmNum(),ghsit.getGhsitPmNum()};
				KeyHolder keyHolder = new GeneratedKeyHolder();
				try {
					this.getJdbcTemplate().update(new PreparedStatementCreator(){
						public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
							int i = 0;
							int n = 0;
							PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setDate(++i,   DateUtils.getSqlDate(objs[n++]));
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
	
	@SuppressWarnings("unchecked")
	public GHSit queryGHSitByID(String  ghsitId) throws Exception{
		final String sql ="select g.* , d1.dept_name , d2.doctor_name from his_mz_ghsit g ,his_mz_dept d1 ,his_mz_doctor d2" +
				" where g.dept_id = d1.dept_id and g.doctor_id = d2.doctor_id and ghsit_id="+ghsitId;
		
		List<GHSit> list = this.getJdbcTemplate().query(sql, new GHSit());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	public void updateGHSit(GHSit ghsit) throws Exception{
		String sql = "update his_mz_ghsit set ghsit_type1=?,ghsit_type2=?," +
				" ghsit_dotime=?,dept_id=?,doctor_id=?,ghsit_amnum=?,ghsit_pmnum=? where ghsit_id=?";
		Object[] objs = {ghsit.getGhsitType1(), ghsit.getGhsitType2() , ghsit.getGhsitDoTime(),ghsit.getDept().getDeptId(),
				ghsit.getDoctor().getDoctorId(),ghsit.getGhsitAmNum(),ghsit.getGhsitPmNum(),ghsit.getGhsitId()};
		this.getJdbcTemplate().update(sql,objs);
	} 
	
	public void deleteGHSit(String[] ghsitId) throws Exception{
		String[] sql = new String[ghsitId.length];
		 sql[0] = "delete from his_mz_ghsit where ghsit_id="+ghsitId[0];
		this.getJdbcTemplate().batchUpdate(sql);
	}	
	
}
