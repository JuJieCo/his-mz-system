package com.jujie.his.mz.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.xwork.Type;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.baseinfo.Medicinal;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.Sick;
import com.jujie.his.inventory.HisMedicinalInventory;
import com.jujie.his.mz.MzCharge;
import com.jujie.his.mz.MzJz;
import com.jujie.his.mz.MzYp;
import com.jujie.util.DataUtils;
import com.jujie.util.DateUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;
 
public class MzZlDaoImpl extends BaseJdbcDao {
	
	 
	 
	@SuppressWarnings("unchecked")
	public  Sick findSickByIDByID(Integer sickId) throws Exception {
		String sql = "select * from his_mz_sick where sick_id=?";
		List<Sick> list = this.getJdbcTemplate().query(sql,new Object[]{sickId}, new Sick());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	 
//	@SuppressWarnings("static-access")
//	public List<MzCharge> findAllMzCharge(Object[] objs,Page page) throws Exception {
//		final List<MzCharge>  mzChargeList=new ArrayList<MzCharge>();
//		List<Object> obj = new ArrayList<Object>();
//		String sql = "select * from his_mz_hjinfo where 1=1 ";
//		if(objs!=null&&objs.length>0){
//			if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
//				sql += " and order_id =?";
//				obj.add(objs[0]);
//			}
//		}
//		sql += " order by order_id desc ";
//		
//		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MSSQL), obj.toArray(),new RowCallbackHandler(){
//
//			@Override
//			public void processRow(ResultSet rs) throws SQLException {
//			 	MzCharge mzCharge = new MzCharge();
//				mzCharge.setOrder_id(rs.getInt("order_id"));
//				mzCharge.setHjinfo_totalM(rs.getDouble("hjinfo_totalM"));
//				mzCharge.setHjinfo_getM(rs.getDouble("hjinfo_getM"));
//				mzCharge.setHjinfo_backM(rs.getDouble("hjinfo_backM"));
//				mzCharge.setHjinfo_xytm(rs.getDouble("hjinfo_xytm"));
//				mzCharge.setHjinfo_zcytm(rs.getDouble("hjinfo_zcytm"));
//				mzCharge.setHjinfo_zcytm2(rs.getDouble("hjinfo_zcytm2"));
//				mzCharge.setHjinfo_zltm(rs.getDouble("hjinfo_zltm"));
//				mzCharge.setSys_user_id(rs.getInt("sys_user_id"));
//				mzCharge.setHjinfo_dotime(rs.getDate("hjinfo_dotime"));
//				mzCharge.setFlag(rs.getInt("flag"));
//				mzChargeList.add(mzCharge);
//			}
//		 });
//		return mzChargeList;
//	}
//	public void updateMzCharge(MzCharge mzCharge) throws Exception{
//		final String sql = "update his_mz_hjinfo set flag=?,void_userid=?,void_dotime where order_id=?";
//		final Object[] objs = {mzCharge.getFlag(),mzCharge.getVoid_userid(),mzCharge.getVoid_dotime(),mzCharge.getOrder_id()};
//		this.getJdbcTemplate().update(sql,objs);   
//	}
   //发药查询
  public List<GHOrder> queryFyList(Object[] objs,Page page) {
    final List<GHOrder>  orderList=new ArrayList<GHOrder>();
    List<Object> obj = new ArrayList<Object>();	
	String where = "";
	String wherenewzl = "";
     if(objs!=null && objs.length>0){
      if(objs.length>=1 && objs[0]!=null&&!"".equals(objs[0])){
		 wherenewzl += " where e.zlinfo_dotime like ? ";
		 obj.add("%"+objs[1]+"%");
		 }
		if(objs.length>=2 && objs[1]!=null&&!"".equals(objs[1])){
			where += " and od.order_statue in(?,?) ";
			obj.add(objs[1]);
			obj.add(objs[2]);
	 	}
	  }
      StringBuffer sql = new StringBuffer("SELECT hj.id,hj.UUID,hj.hjinfo_totalM,hj.hjinfo_dotime,od.order_statue,hj.flag,zl.order_id,si.sick_name,si.sick_sex,si.sick_age,si.sick_casehistory FROM his_mz_hjinfo hj,(SELECT d.UUID,d.order_id,d.zlinfo_dotime,d.flag FROM his_mz_zlinfo d WHERE d.UUID IN (SELECT MAX(e.UUID) FROM his_mz_zlinfo e ");
      sql.append(wherenewzl).append(" GROUP BY e.order_id)) zl");
      sql.append(",his_mz_order od ,his_mz_sick si WHERE hj.UUID = zl.UUID AND od.sick_id = si.sick_id AND zl.order_id = od.order_id ");
      sql.append(where);
      sql.append(" order by hj.id ");  
     this.getJdbcTemplate().query(PageUtils.fyPage(sql.toString(), obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){
         @Override
		public void processRow(ResultSet rs) throws SQLException {
			 GHOrder ghorder = new GHOrder();
			 ghorder.setOrderId(rs.getInt("order_id"));
			 ghorder.setUuid(rs.getInt("uuid"));
			 ghorder.setOrderStatue(rs.getInt("order_statue"));
		     Sick sick = new Sick();
			 sick.setSickName(rs.getString("sick_name"));
			 sick.setSickSex(rs.getInt("sick_sex"));
			 sick.setSickAge(rs.getInt("sick_age"));
		 	 sick.setSickCasehistory(rs.getString("sick_casehistory"));
			 MzCharge mzCharge = new MzCharge();
			 mzCharge.setId(rs.getInt("id"));
			 mzCharge.setUuid(rs.getInt("uuid"));
			 mzCharge.setHjinfo_dotime(rs.getDate("hjinfo_dotime"));
			 mzCharge.setFlag(rs.getInt("flag"));
			 mzCharge.setHjinfo_totalM(rs.getDouble("hjinfo_totalM"));
			 ghorder.setMzCharge(mzCharge);
		     ghorder.setSick(sick);
		     orderList.add(ghorder);
		}
	 });
    return orderList;
   }
   
  ///
    public List<GHOrder> queryGhOrderList(Object[] objs,Page page , Integer doctorId) {
		
		List<Object> obj = new ArrayList<Object>();	
			String where = "";
		    if(objs!=null && objs.length>0){
		    	if(objs.length==2){
		    		if(objs.length>=1 && objs[0]!=null&&!"".equals(objs[0])){
						 where += " and a.order_id = ? ";
						 obj.add(objs[0]);
						 }
						if(objs.length>=2 && objs[1]!=null&&!"".equals(objs[1])){
							where += " and a.order_statue = ? ";
							obj.add(objs[1]);
							 
						}	
		    	}else if(objs.length==3){
		    		if(objs.length>=1 && objs[0]!=null&&!"".equals(objs[0])){
					 where += " and a.order_id = ? ";
					 obj.add(objs[0]);
					 }
					if(objs.length>=2 && objs[1]!=null&&!"".equals(objs[1])){
						where += " and a.order_statue in(?,?) ";
						obj.add(objs[1]);
						obj.add(objs[2]);
						 
					}
		    	}
				
				
		     }
		    
		    //这个SQL  13-03-14  之前是 如下三行
		    
//		    String sql = "SELECT a.order_id,a.ghsit_id,a.order_statue,a.order_index,b.sick_id,b.sick_name,b.sick_sex,b.sick_age,b.sick_cosetype,b.sick_treattype,b.sick_address,b.sick_casehistory " +
//		    		" FROM his_mz_order a , his_mz_sick b  " +
//		    		" where a.sick_id = b.sick_id "+where+ "order by a.order_id ";  
		    
		    
		    
		    String sql = "SELECT a.order_id,a.ghsit_id,a.order_statue,a.order_index,b.sick_id,b.sick_name,b.sick_sex,b.sick_age,b.sick_cosetype,b.sick_treattype,b.sick_address,b.sick_casehistory " +
					" FROM his_mz_order a , his_mz_sick b  " +
					" where a.sick_id = b.sick_id "+where+ "order by a.order_id ";  
		    
			if(!"".equals(doctorId)&&null!=doctorId&&0!=doctorId){ // 代表登录的是医生 上面不判断挂号时间，和病人对应医生
				
				
					  sql = "SELECT a.order_id,a.ghsit_id,a.order_statue,a.order_index,b.sick_id,b.sick_name,b.sick_sex,b.sick_age,b.sick_cosetype,b.sick_treattype,b.sick_address,b.sick_casehistory " +
					    		" FROM his_mz_order a , his_mz_sick b  , his_mz_ghsit t " +
					    		" where a.sick_id = b.sick_id  AND  t.ghsit_dotime = CURDATE()  AND t.doctor_id = "+doctorId +" AND a.ghsit_id = t.ghsit_id "+where+ "order by a.order_id ";  
			
				  
		 	}
		  
		    return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(), new GHOrder());
	 	}
   @SuppressWarnings("unchecked")
   public List<MzJz> queryMzJzList(Object[] objs) {
   		
   	    	List<Object> obj = new ArrayList<Object>();	
   			String where = "";
   		    if(objs!=null && objs.length>0){
   				if(objs.length>=1 && objs[0]!=null&&!"".equals(objs[0])){
   				 where += " and a.order_id = ? ";
   				 obj.add(objs[0]);
   				 }
             }
   		    
   		    String sql = "SELECT a.uuid,a.order_id,a.sick_casehistory,a.zlinfo_content,a.zlinfo_remark,a.sys_user_id,a.zlinfo_dotime,hj.flag FROM his_mz_zlinfo a left join his_mz_hjinfo hj on a.uuid=hj.uuid "+where+ "order by a.uuid desc";  
   		    List<MzJz> list = (List<MzJz>)this.getJdbcTemplate().query(sql,obj.toArray(), new MzJz());
   		    for (MzJz mzJz : list) {
   		    	mzJz.setMzYpList(queryMzYmList(mzJz.getUuid()));
			}
   		    return list;
    }
   
   public List<MzYp> queryMzYmList(int uuid) {
		 String sql = "select * from his_mz_medicinaldetail where uuid="+uuid;
		 List<MzYp> mzYpList = this.getJdbcTemplate().query(sql,new MzYp());
		 for (MzYp mzYp : mzYpList) {
			  String m_sql = "SELECT m.medicinal_id,m.medicinal_name,m.medicinal_pycode,m.medicinal_statue,m.medicinal_type,"
					+"m.unit_id,u.unit_name,u.unit_statue,m.standard_id,s.standard_name,s.standard_statue  "
					+"FROM his_mz_medicinal m LEFT JOIN his_mz_unit u ON m.unit_id=u.unit_id LEFT JOIN his_mz_standard s ON m.standard_id=s.standard_id where m.medicinal_id="+mzYp.getMedicinal_id();
			  HisMedicinalInventory hmi = new HisMedicinalInventory();
			  if(this.getJdbcTemplate().query(m_sql, new Medicinal())!=null&&this.getJdbcTemplate().query(m_sql, new Medicinal()).size()>0){
				  hmi.setMedicinal((Medicinal)this.getJdbcTemplate().query(m_sql, new Medicinal()).get(0));
			  }
			  mzYp.getMedicinalInventoryList().add(hmi);
		 }
		 return mzYpList;
   }
    
 public Integer updateSick(Integer orderStatue,Integer orderId) throws Exception {
	final String sql = "update his_mz_order set order_statue=? where order_id=?";
	final Object[] objs = {orderStatue,orderId};
			KeyHolder keyHolder = new GeneratedKeyHolder();
			try {
				this.getJdbcTemplate().update(new PreparedStatementCreator(){
					public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
						int i = 0;
						int n = 0;
						PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			            ps.setInt(++i,DataUtils.getInt(objs[n++]));
						ps.setInt(++i,DataUtils.getInt(objs[n++]));
					    return ps;
					}
				}, keyHolder);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			return keyHolder.getKey().intValue();	
}   
 public int addMzJz(MzJz mzJz) throws Exception {
		final String sql = "insert into his_mz_zlinfo (order_id,sick_casehistory,zlinfo_content,zlinfo_remark,sys_user_id,zlinfo_dotime,flag) " +
				"values(?,?,?,?,?,?,?)";
		final Object[] objs = {mzJz.getOrder_id(), mzJz.getSick_casehistory(),mzJz.getZlinfo_content(),mzJz.getZlinfo_remark(),mzJz.getSys_user_id(),mzJz.getZlinfo_dotime(),mzJz.getFlag()};
				KeyHolder keyHolder = new GeneratedKeyHolder();
				try {
					this.getJdbcTemplate().update(new PreparedStatementCreator(){
						public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
							int i = 0;
							int n = 0;
							PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setString(++i, DataUtils.getStringK(objs[n++]));
							ps.setString(++i, DataUtils.getStringK(objs[n++]));
						    ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
							ps.setTimestamp(++i,   DateUtils.getTimestamp(objs[n++]));
							ps.setInt(++i, DataUtils.getInt(objs[n++]));
							return ps;
						}
					}, keyHolder);
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				return keyHolder.getKey().intValue();
	}
 public void addMzYp(final List<MzYp> mzYpList) throws Exception {
		StringBuffer sd = new StringBuffer("insert into his_mz_medicinaldetail(");
		sd.append("uuid,medicinal_id,medicinal_num,invertory_id,flag)");
		sd.append(" values(?,?,?,?,?)");
		this.getJdbcTemplate().batchUpdate(sd.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MzYp mzYp = mzYpList.get(i);
				int n = 0;
				ps.setInt(++n, DataUtils.getInt(mzYp.getUuid()));
				ps.setInt(++n, DataUtils.getInt(mzYp.getMedicinal_id()));
				ps.setInt(++n, DataUtils.getInt(mzYp.getMedicinal_num()));
				if(mzYp.getInvertory_id()==null){
					ps.setNull(++n, Type.INT);
				}else{
					ps.setInt(++n, DataUtils.getInt(mzYp.getInvertory_id()));
				};
				ps.setInt(++n, DataUtils.getInt(mzYp.getFlag()));
		    }
		    @Override
			public int getBatchSize() {
			 return mzYpList.size();
			}
		});
	}
  
 public void updateMzOrder(Integer orderStatue,Integer orderId ) throws Exception{
		final String sql = "update his_mz_order set order_statue=? where order_id=?";
		final Object[] objs = {orderStatue,orderId};
		this.getJdbcTemplate().update(sql,objs);   
	}
 //账务查询
 @SuppressWarnings("unchecked")
  public List<GHOrder> queryZwList(Object[] objs,Page page) {
 		
 		List<Object> obj = new ArrayList<Object>();	
 			String where = "";
 		    if(objs!=null && objs.length>0){
 				if(objs.length>=1 && objs[0]!=null&&!"".equals(objs[0])){
 				 where += " and a.order_id = ? ";
 				 obj.add(objs[0]);
 				 }
 				if(objs.length>=2 && objs[1]!=null&&!"".equals(objs[1])){
 					where += " and a.order_statue = ? ";
 					obj.add(objs[1]);
 					 
 				}
 		     }
 		    String sql = "SELECT a.order_id,a.ghsit_id,a.order_statue,a.order_index,b.sick_id,b.sick_name,b.sick_sex,b.sick_age,b.sick_cosetype,b.sick_treattype,b.sick_address,b.sick_casehistory FROM his_mz_order a , his_mz_sick b where a.sick_id = b.sick_id "+where+ "order by a.order_id ";  
 		   return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(), new GHOrder());
   }
 
 public List<Map<String,String>> queryZwBBList(Object[] objs,Page page) {
		
		final List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<Object> obj = new ArrayList<Object>();	
		String where = " where 1=1 ";
		if(objs!=null && objs.length>0){
			if(objs[0]!=null&&!"".equals(objs[0])){
				where += " and sk.sick_ybcode=? ";
				obj.add(objs[0]);
			}
			if(objs[1]!=null&&!"".equals(objs[1])){
				where += " and dr.doctor_id=? ";
				obj.add(objs[1]);
			}
			
			if(objs[2]!=null&&!"".equals(objs[2])){
				where += " and hj.hjinfo_dotime>='"+objs[2]+"' ";
			}
			if(objs[3]!=null&&!"".equals(objs[3])){
				where += " and hj.hjinfo_dotime<='"+objs[3]+"' ";
			}
			if(objs[4]!=null&&!"".equals(objs[4])){
				where += " and dp.dept_id=? ";
				obj.add(objs[4]);
			}
		}
		String sql = "SELECT sk.sick_ybcode,sk.sick_name,dr.doctor_name,dp.dept_name, SUM(hj.hjinfo_xytm) tytm, SUM(hj.hjinfo_zcytm) zcytm, SUM(hj.hjinfo_zcytm2) zcytm2,"
					+"SUM(hj.hjinfo_xytm)+SUM(hj.hjinfo_zcytm)+SUM(hj.hjinfo_zcytm2) total FROM his_mz_hjinfo hj "
					+"INNER JOIN his_mz_order od ON hj.order_id=od.order_id INNER JOIN his_mz_sick sk ON od.sick_id=sk.sick_id  "
					+"INNER JOIN his_mz_ghsit gt ON od.ghsit_id=gt.ghsit_id INNER JOIN his_mz_doctor dr ON gt.doctor_id=dr.doctor_id "
					+"INNER JOIN his_mz_dept dp ON gt.dept_id=dp.dept_id  "+where+ " GROUP BY sk.sick_ybcode ORDER BY hj.hjinfo_dotime DESC ";  
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Map<String,String> map = new HashMap<String,String>();
				map.put("sick_ybcode", rs.getString("sick_ybcode"));
				map.put("sick_name", rs.getString("sick_name"));
				map.put("doctor_name", rs.getString("doctor_name"));
				map.put("dept_name", rs.getString("dept_name"));
				map.put("tytm", rs.getString("tytm"));
				map.put("zcytm", rs.getString("zcytm"));
				map.put("zcytm2", rs.getString("zcytm2"));
				map.put("total", rs.getString("total"));
				list.add(map);
			}
		 });
		return list;
	}


}
