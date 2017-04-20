package com.jujie.his.baseinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.baseinfo.Doctor;
import com.jujie.his.baseinfo.DoctorDept;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

@SuppressWarnings("unchecked")
public class DoctorDaoImpl  extends BaseJdbcDao{

	
	// 取his_mz_doctor表 返回医生List
	
		public List<Doctor> queryDoctorList()throws Exception{
			final String sql = "select * from his_mz_doctor ";
			return this.getJdbcTemplate().query(sql, new Doctor());
		}

		@SuppressWarnings("static-access")
		public List<DoctorDept> queryDoctorDeptList(Page page)throws Exception{
			final String sql = "select d1.dept_name , d1.dept_id , d2.*  from his_mz_dept d1 , his_mz_doctor d2 , doctor_dept dd where d1.dept_id = dd.dept_id and dd.doctor_id= d2.doctor_id " +
					" order by d2.doctor_id desc";
			
			final List<DoctorDept> ddList = new ArrayList<DoctorDept>();
			List<Object> obj = new ArrayList<Object>();
			this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			 public void processRow(ResultSet rs) throws SQLException{
				 DoctorDept doctordept = new DoctorDept();
				 
				 doctordept.getDept().setDeptName(rs.getString("dept_name"));
				 doctordept.getDept().setDeptId(rs.getInt("dept_id"));
				 doctordept.getDoctor().setDoctorId(rs.getInt("doctor_id"));
				 doctordept.getDoctor().setDoctorName(rs.getString("doctor_name"));
				 doctordept.getDoctor().setDoctorSex(rs.getInt("doctor_sex"));
				 doctordept.getDoctor().setDoctorIntrodcut(rs.getString("doctor_introdcut"));
				 doctordept.getDoctor().setDoctorStatue(rs.getInt("doctor_statue"));
				 ddList.add(doctordept);
			 }
		 }); 
		 		return  ddList;
		}
		
		
		//新增医生
		public Integer addDoctor(Doctor doctor) throws Exception {
			
			final String sql = "insert into his_mz_doctor (doctor_name,doctor_sex,doctor_introdcut,doctor_statue) values(?,?,?,?)";
			
			final Object[] objs ={doctor.getDoctorName(),doctor.getDoctorSex(),doctor.getDoctorIntrodcut(),doctor.getDoctorStatue()};
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			try {
				this.getJdbcTemplate().update(new PreparedStatementCreator(){
					public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
						int i = 0;
						int n = 0;
						PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
						ps.setString(++i, DataUtils.getStringK(objs[n++]));
						ps.setInt(++i,    DataUtils.getInt(objs[n++]));
						ps.setString(++i, DataUtils.getStringK(objs[n++]));
						ps.setInt(++i,    DataUtils.getInt(objs[n++]));

						return ps;
					}
				}, keyHolder);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			return keyHolder.getKey().intValue();
			}
		
			//新增时操作关联表
			public void addDoctorDept(Integer doctorId, Integer deptId) throws Exception {
			
			final String sql = "insert into doctor_dept (doctor_id,dept_id) values(?,?)";
			
			final Object[] objs ={doctorId,deptId};
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			try {
				this.getJdbcTemplate().update(new PreparedStatementCreator(){
					public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
						int i = 0;
						int n = 0;
						PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
						
						ps.setInt(++i,    DataUtils.getInt(objs[n++]));
						ps.setInt(++i,    DataUtils.getInt(objs[n++]));

						return ps;
					}
				}, keyHolder);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}

			}
			
			
			//查询医生byID
			
			public Doctor queryDoctor(String doctorId) throws Exception{

				final String sql ="select * from  his_mz_doctor where doctor_id = "+doctorId;
				List<Doctor> list = this.getJdbcTemplate().query(sql, new Doctor());
				if(list!=null&&list.size()>0){
					return list.get(0);
				}
				return null;
			}
			
//			//查询医生的同时还需要查询该医生对应的所有科室。
//			public List<DoctorDept> queryDoctorDept(String doctorId) throws Exception{
//				final String sql ="select dept_id from  doctor_dept  where doctor_id = "+doctorId;
//				List<DoctorDept> list = this.getJdbcTemplate().query(sql, new DoctorDept());
//				return list;
//			}
			
			
			//修改医生
			public void updateDoctor(Doctor doctor ,String statue) throws Exception{

				if(null!=statue&&!"".equals(statue)){
					if(statue.equals("isStatue")){
						String sql = "update his_mz_doctor set doctor_statue=? where doctor_id=?";
						Object[] objs = {doctor.getDoctorStatue(),doctor.getDoctorId()};
						this.getJdbcTemplate().update(sql,objs);
					}
					if(statue.equals("noStatue")){
						//String sql = "update his_mz_doctor set doctor_name=?,doctor_sex=?,doctor_introdcut=?,dept_id where doctor_id=?";
						String sql = "update his_mz_doctor set doctor_name=?,doctor_sex=?,doctor_introdcut=?  where doctor_id=?";
						Object[] objs = {doctor.getDoctorName(),doctor.getDoctorSex(),doctor.getDoctorIntrodcut(),doctor.getDoctorId()};
						this.getJdbcTemplate().update(sql,objs);
					}
					
				}
			}
			
			
			
			//删除医生
			public void deleteDoctor(String doctorId) throws Exception{

				String sql = "delete from his_mz_doctor where doctor_id="+doctorId;
				 this.getJdbcTemplate().update(sql);
			}	
			
			//删除时操作关联表
			public void DoctorDept(String doctorId , String deptId)  throws Exception{

			String sql =  "delete from doctor_dept where doctor_id= " + doctorId +"  and  dept_id = " +deptId;
				this.getJdbcTemplate().update(sql);
			}
			
			//Json
			public Doctor queryOneDoctorIntro(String doctorId) throws Exception{
				 String sql = "select doctor_introdcut from his_mz_doctor where doctor_id="+doctorId;
				 	List<Doctor> list = this.getJdbcTemplate().query(sql, new Doctor());
					if(list!=null&&list.size()>0){
						return list.get(0);
					}
					return null;
				}
			
}
