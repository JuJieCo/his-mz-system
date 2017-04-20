package com.jujie.his.mz.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.guahao.GHOrder;
import com.jujie.his.guahao.Sick;
import com.jujie.his.mz.Fee_Type;
import com.jujie.his.mz.HisInventoryFy;
import com.jujie.his.mz.MzCharge;
import com.jujie.his.mz.MzJz;
import com.jujie.his.mz.MzYp;
import com.jujie.util.DataUtils;
import com.jujie.util.DateUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;
 
public class MzChargeDaoImpl extends BaseJdbcDao {
	
     @SuppressWarnings("static-access")
	public List<GHOrder> queryGhOrderList(Object[] objs,Page page) {
        final List<GHOrder>  orderList=new ArrayList<GHOrder>();
	    List<Object> obj = new ArrayList<Object>();	
		String where = "";
		String wherenewzl = "";
	    if(objs!=null && objs.length>0){
	    	if(objs.length>=3 && objs[2]!=null&&!"".equals(objs[2])){
	    		 wherenewzl += " where e.zlinfo_dotime like ? ";
				 obj.add("%"+objs[2]+"%");
	 		    }
			if(objs.length>=1 && objs[0]!=null&&!"".equals(objs[0])){
			 where += " and b.sick_casehistory = ? ";
			 obj.add(objs[0]);
			 }
			if(objs.length>=2 && objs[1]!=null&&!"".equals(objs[1])){
			 where += " and a.order_statue = ? ";
			 obj.add(objs[1]);
				 
			}
			
	     }
         StringBuffer sql = new StringBuffer("select a.order_id,a.ghsit_id,a.order_statue,a.order_index,b.sick_id,b.sick_name,b.sick_sex,b.sick_age,b.sick_cosetype,b.sick_treattype,b.sick_address,b.sick_casehistory,c.uuid,c.flag from his_mz_order a ,his_mz_sick b,(SELECT d.UUID,d.order_id,d.zlinfo_dotime,d.flag FROM his_mz_zlinfo d WHERE d.UUID IN (SELECT MAX(e.UUID) FROM his_mz_zlinfo e ");
	     sql.append(wherenewzl).append(" GROUP BY e.order_id)) c");
	     sql.append(" where a.sick_id = b.sick_id and c.order_id = a.order_id ");
	     sql.append(where);
	     sql.append(" order by a.order_id ");  
	     this.getJdbcTemplate().query(PageUtils.fyPage(sql.toString(), obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){
            @Override
			public void processRow(ResultSet rs) throws SQLException {
				 GHOrder ghorder = new GHOrder();
				 ghorder.setOrderId(rs.getInt("order_id"));
			     ghorder.setOrderStatue(rs.getInt("order_statue"));
			 	 ghorder.setOrderIndex(rs.getInt("order_index"));
				 Sick sick = new Sick();
				 sick.setSickId(rs.getInt("sick_id"));
				 sick.setSickName(rs.getString("sick_name"));
				 sick.setSickSex(rs.getInt("sick_sex"));
				 sick.setSickAge(rs.getInt("sick_age"));
				 sick.setSickCosetype(rs.getInt("sick_cosetype"));
				 sick.setSickCasehistory(rs.getString("sick_casehistory"));
				 sick.setSickAddress(rs.getString("sick_address"));
			     sick.setSickTreattype(rs.getInt("sick_treattype"));
                 ghorder.setSick(sick);
			     List<MzJz>  mzJzList = new ArrayList<MzJz>();
			     MzJz mzJz = new MzJz();
			     mzJz.setUuid(rs.getInt("uuid"));
			     ghorder.setZlflag(rs.getInt("flag"));
			     ghorder.setUuid(rs.getInt("uuid"));
			     mzJzList.add(mzJz);
			     ghorder.setMzJzList(mzJzList);
			     orderList.add(ghorder);
			}
		 });
	    return orderList;
 	}
     
    //收费列表
     public List<GHOrder> queryAllSfList(Object[] objs,Page page) {
        final List<GHOrder>  orderList=new ArrayList<GHOrder>();
 	    List<Object> obj = new ArrayList<Object>();	
 		String where = "";
 		String wherenewzl = "";
 	    if(objs!=null && objs.length>0){
 	    	if(objs.length>=2 && objs[1]!=null&&!"".equals(objs[1])){
 	    	 wherenewzl += " where e.zlinfo_dotime like ? ";
			 obj.add("%"+objs[1]+"%");
			 }
 			if(objs.length>=1 && objs[0]!=null&&!"".equals(objs[0])){
 			 where += " and si.sick_casehistory = ? ";
 			 obj.add(objs[0]);
 			 }
 		  }
         StringBuffer sql = new StringBuffer("SELECT hj.id,hj.UUID,hj.hjinfo_totalM,hj.hjinfo_dotime,hj.flag,zl.order_id,si.sick_name,si.sick_sex,si.sick_age,si.sick_casehistory FROM his_mz_hjinfo hj,(SELECT d.UUID,d.order_id,d.zlinfo_dotime,d.flag FROM his_mz_zlinfo d WHERE d.UUID IN (SELECT MAX(e.UUID) FROM his_mz_zlinfo e ");
         sql.append(wherenewzl).append(" GROUP BY e.order_id)) zl");
	     sql.append(",his_mz_order od ,his_mz_sick si WHERE hj.UUID = zl.UUID AND od.sick_id = si.sick_id AND zl.order_id = od.order_id ");
         sql.append(where);
 	     sql.append(" order by hj.id ");  
 	     this.getJdbcTemplate().query(PageUtils.fyPage(sql.toString(), obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){
             @Override
 			public void processRow(ResultSet rs) throws SQLException {
 				 GHOrder ghorder = new GHOrder();
 				 ghorder.setOrderId(rs.getInt("order_id"));
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
     //诊疗收费列表
     public List<GHOrder> queryAllZlSfList(Object[] objs,Page page) {
        final List<GHOrder>  orderList=new ArrayList<GHOrder>();
 	    List<Object> obj = new ArrayList<Object>();	
 		String where = "";
 		String whereFee = "";
 	    if(objs!=null && objs.length>0){
 	    	if(objs.length>=2 && objs[1]!=null&&!"".equals(objs[1])){
 				whereFee += " where fee.hjinfo_dotime like ? ";
 			    obj.add("%"+objs[1]+"%");
 			}
 			if(objs.length>=1 && objs[0]!=null&&!"".equals(objs[0])){
 			 where += " and si.sick_casehistory = ? ";
 			 obj.add(objs[0]);
 			 }
 			
 			 
 	     }
         StringBuffer sql = new StringBuffer("SELECT od.order_id,si.sick_name,si.sick_sex,si.sick_age,si.sick_casehistory FROM his_mz_sick si, his_mz_order od WHERE od.sick_id = si.sick_id AND od.order_id IN (SELECT fee.order_id FROM feetype fee ");
         sql.append(whereFee).append("GROUP BY fee.order_id )"); 
         sql.append(where);
 	     this.getJdbcTemplate().query(PageUtils.fyPage(sql.toString(), obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){
             @Override
 			public void processRow(ResultSet rs) throws SQLException {
 				 GHOrder ghorder = new GHOrder();
 				 ghorder.setOrderId(rs.getInt("order_id"));
 			     Sick sick = new Sick();
 				 sick.setSickName(rs.getString("sick_name"));
 				 sick.setSickSex(rs.getInt("sick_sex"));
 				 sick.setSickAge(rs.getInt("sick_age"));
 			 	 sick.setSickCasehistory(rs.getString("sick_casehistory"));
 				 ghorder.setSick(sick);
 			     orderList.add(ghorder);
 			}
 		 });
 	    for (GHOrder gHOrder : orderList) {
 	    	 gHOrder.setFeeTypeList(feeTypeListByOrderid(gHOrder.getOrderId()));
		}
      return orderList;
  	}
     //根据order_id查询诊疗收费信息
     public List<Fee_Type> feeTypeListByOrderid(int orderid) {
        String sql=" select *  from feetype where 1=1 ";
		List<Object> list=new ArrayList<Object>();
	    sql+=" and order_id=? ";
	    list.add(orderid);
	 	List<Fee_Type> listFeeType=null;
	    try {
			listFeeType=this.getJdbcTemplate().query(sql,list.toArray(),new Fee_Type());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listFeeType;
    	 
   }
   public void addMzCharge(MzCharge mzCharge) throws Exception {
		final StringBuffer sql = new StringBuffer("insert into his_mz_hjinfo(");
		sql.append("uuid,hjinfo_totalM,hjinfo_getM,hjinfo_backM,");
		sql.append("hjinfo_xytm,hjinfo_zcytm,hjinfo_zcytm2,");
		sql.append("hjinfo_zltm,flag,sys_user_id,hjinfo_dotime)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
	    final Object[] objs = {mzCharge.getUuid(),mzCharge.getHjinfo_totalM(),mzCharge.getHjinfo_getM(),mzCharge.getHjinfo_backM()
	    		,mzCharge.getHjinfo_xytm(),mzCharge.getHjinfo_zcytm(),mzCharge.getHjinfo_zcytm2(),mzCharge.getHjinfo_zltm()
	    		,mzCharge.getFlag(),mzCharge.getSys_user_id(),mzCharge.getHjinfo_dotime()};
		 	
	        KeyHolder keyHolder = new GeneratedKeyHolder();   
			
			try{
				this.getJdbcTemplate().update(new PreparedStatementCreator(){         
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						int i = 0;
						int n = 0;
						PreparedStatement ps = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);  
					 	ps.setInt(++i, DataUtils.getInt(objs[n++]));
						ps.setDouble(++i, DataUtils.getDouble(objs[n++])); 
						ps.setDouble(++i, DataUtils.getDouble(objs[n++])); 
						ps.setDouble(++i, DataUtils.getDouble(objs[n++])); 
						ps.setDouble(++i, DataUtils.getDouble(objs[n++])); 
						ps.setDouble(++i, DataUtils.getDouble(objs[n++]));     
						ps.setDouble(++i, DataUtils.getDouble(objs[n++])); 
						ps.setDouble(++i, DataUtils.getDouble(objs[n++])); 
					 	ps.setInt(++i, DataUtils.getInt(objs[n++]));
					 	ps.setInt(++i, DataUtils.getInt(objs[n++]));
						ps.setTimestamp(++i, DateUtils.getTimestamp(objs[n++])); 
						 
						
						return ps;                   
					}

					          
				}, keyHolder);    
			}catch(Exception e){
				e.printStackTrace();
			} 
	    }
 
       public void updateMzYp(final List<MzYp> mzYpList) throws Exception {
		StringBuffer sd = new StringBuffer("update his_mz_medicinaldetail set medicinal_num=?,invertory_id=? where medicinal_id=?");
	 	this.getJdbcTemplate().batchUpdate(sd.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MzYp mzYp = mzYpList.get(i);
				int n = 0;
			    ps.setInt(++n, DataUtils.getInt(mzYp.getMedicinal_num()));
			    ps.setInt(++n, DataUtils.getInt(mzYp.getInvertory_id()));
			    ps.setInt(++n, DataUtils.getInt(mzYp.getMedicinal_id()));
		    }
		    @Override
			public int getBatchSize() {
			 return mzYpList.size();
			}
		});
	}
   public void updateMzOrder(Integer orderStatue,Integer uuid ) throws Exception{
		final String sql = "update his_mz_order set order_statue=? where order_id=?";
		final Object[] objs = {orderStatue,uuid};
		this.getJdbcTemplate().update(sql,objs);   
	}
	 
	public  MzCharge findMzChargeByID(Integer order_id) throws Exception {
		String sql = "select * from his_mz_hjinfo where order_id=?";
		List<MzCharge> list = this.getJdbcTemplate().query(sql,new Object[]{order_id}, new MzCharge());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
   public GHOrder findMzChargeByID(String sickCasehistory) {
	   final GHOrder ghorder = new GHOrder();
	   List<Object> obj = new ArrayList<Object>();	
	   String where = "";
       where += " and b.sick_casehistory = ? ";  
       obj.add(sickCasehistory);
       String sql = "SELECT a.order_id,a.order_statue,b.sick_id,b.sick_name,b.sick_sex,b.sick_age,b.sick_casehistory FROM his_mz_order a , his_mz_sick b WHERE a.sick_id = b.sick_id "+where;  
       this.getJdbcTemplate().query(sql, obj.toArray(),new RowCallbackHandler(){
           @Override
			public void processRow(ResultSet rs) throws SQLException {
				 ghorder.setOrderId(rs.getInt("order_id"));
			     ghorder.setOrderStatue(rs.getInt("order_statue"));
                 Sick sick = new Sick();
                 sick.setSickId(rs.getInt("sick_id"));
				 sick.setSickName(rs.getString("sick_name"));
				 sick.setSickSex(rs.getInt("sick_sex"));
				 sick.setSickAge(rs.getInt("sick_age"));
				 sick.setSickCasehistory(rs.getString("b.sick_casehistory"));  
			     ghorder.setSick(sick);
		    }
		 });
       return ghorder;
 	}
   //诊疗划价
   public GHOrder queryZlHjByID(String order_id) {
	   final GHOrder ghorder = new GHOrder();
	   List<Object> obj = new ArrayList<Object>();	
	   String where = "";
       where += " and a.order_id = ? ";  
       obj.add(order_id);
      // where += " and a.order_statue = '5' ";  
       String sql = "SELECT a.order_id,a.order_statue,b.sick_id,b.sick_name,b.sick_sex,b.sick_age,b.sick_cosetype,b.sick_treattype,b.sick_address,b.sick_casehistory,(IFNULL(c.hjinfo_getM,0)+IFNULL(c.hjinfo_jctm,0)+IFNULL(c.hjinfo_hytm,0)) hjinfo_total FROM his_mz_order a , his_mz_sick b,his_mz_hjinfo c where a.sick_id = b.sick_id and a.order_id = c.order_id "+where+ "order by a.order_id ";  
       this.getJdbcTemplate().query(sql, obj.toArray(),new RowCallbackHandler(){
           @Override
			public void processRow(ResultSet rs) throws SQLException {
				
				 ghorder.setOrderId(rs.getInt("order_id"));
			     ghorder.setOrderStatue(rs.getInt("order_statue"));
                 Sick sick = new Sick();
                 sick.setSickId(rs.getInt("sick_id"));
				 sick.setSickName(rs.getString("sick_name"));
				 sick.setSickSex(rs.getInt("sick_sex"));
				 sick.setSickAge(rs.getInt("sick_age"));
				 sick.setSickCosetype(rs.getInt("sick_cosetype"));  
				 MzCharge mzCharge = new MzCharge();
				 mzCharge.setHjinfo_totalM(rs.getDouble("hjinfo_total"));
			     ghorder.setSick(sick);
				 ghorder.setMzCharge(mzCharge);
				 
			}
		 });
       return ghorder;
 	}
   
	@SuppressWarnings("static-access")
	public List<MzCharge> findAllMzCharge(Object[] objs,Page page) throws Exception {
		final List<MzCharge>  mzChargeList=new ArrayList<MzCharge>();
		List<Object> obj = new ArrayList<Object>();
		String sql = "select * from his_mz_hjinfo where 1=1 ";
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
				sql += " and order_id =?";
				obj.add(objs[0]);
			}
		}
		sql += " order by order_id desc ";
		
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
			 	MzCharge mzCharge = new MzCharge();
				mzCharge.setId(rs.getInt("id"));
				mzCharge.setHjinfo_totalM(rs.getDouble("hjinfo_totalM"));
				mzCharge.setHjinfo_getM(rs.getDouble("hjinfo_getM"));
				mzCharge.setHjinfo_backM(rs.getDouble("hjinfo_backM"));
				mzCharge.setHjinfo_xytm(rs.getDouble("hjinfo_xytm"));
				mzCharge.setHjinfo_zcytm(rs.getDouble("hjinfo_zcytm"));
				mzCharge.setHjinfo_zcytm2(rs.getDouble("hjinfo_zcytm2"));
				mzCharge.setHjinfo_zltm(rs.getDouble("hjinfo_zltm"));
			    mzCharge.setSys_user_id(rs.getInt("sys_user_id"));
				mzCharge.setHjinfo_dotime(rs.getDate("hjinfo_dotime"));
				mzCharge.setFlag(rs.getInt("flag"));
				mzChargeList.add(mzCharge);
			}
		 });
	 return mzChargeList;
	}
	//收费
	public void updateMzCharge(MzCharge mzCharge) throws Exception{
		final String sql = "update his_mz_hjinfo set flag=?,fph=?,sf_userid=?,sf_dotime=? where id=?";
		final Object[] objs = {mzCharge.getFlag(),mzCharge.getFph(),mzCharge.getSf_userid(),mzCharge.getSf_dotime(),mzCharge.getId()};
	    this.getJdbcTemplate().update(sql,objs);   
	}
	public void updateMzYP(final List<MzYp> mzYpList) throws Exception {
		final StringBuffer sql = new StringBuffer("update his_mz_medicinaldetail set invertory_id=?,flag=? where id=?");
	  	this.getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MzYp mzYp = mzYpList.get(i);
				int n = 0;
			    ps.setInt(++n, DataUtils.getInt(mzYp.getInvertory_id()));
			    ps.setInt(++n, DataUtils.getInt(mzYp.getFlag()));
			    ps.setInt(++n, DataUtils.getInt(mzYp.getId()));
		    }
		    @Override
			public int getBatchSize() {
		    return mzYpList.size();
			}
		});
	}
	//根据处方状态 2 代表划价his_mz_zlinfo
	 public void updateMzZl(Integer orderStatue,Integer uuid ) throws Exception{
			final String sql = "update his_mz_zlinfo set flag=? where uuid=?";
			final Object[] objs = {orderStatue,uuid};
			this.getJdbcTemplate().update(sql,objs);   
    }
    public void addYpff(final HisInventoryFy hisInventoryFy) throws Exception {
		final StringBuffer sql = new StringBuffer("insert into his_mz_lyinfo(");
		sql.append("order_id,sys_user_id,lyinfo_dotime)"); 
		sql.append(" values(?,?,?)");
		final Object[] objs = {hisInventoryFy.getOrder_id(),hisInventoryFy.getSys_user_id(),hisInventoryFy.getLyinfo_dotime()};
	    KeyHolder keyHolder = new GeneratedKeyHolder();   
		 try{
			this.getJdbcTemplate().update(new PreparedStatementCreator(){         
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);  
					ps.setInt(++i, DataUtils.getInt(objs[n++]));
					ps.setTimestamp(++i, DateUtils.getTimestamp(objs[n++]));
					ps.setInt(++i, DataUtils.getInt(objs[n++])); 
				    return ps;                   
				}
		  }, keyHolder);    
			}catch(Exception e){
				e.printStackTrace();
			} 
	}
	//诊疗收费
	public void updateZlSf(Fee_Type feetypebean) throws Exception{
	 final String sql = "update feetype set sf_userid=?,sf_dotime=?,flag=?,fph=? where feeid=?";
	 final Object[] objs = {feetypebean.getSf_userid(),feetypebean.getSf_dotime(),feetypebean.getFlag(),feetypebean.getFph(),feetypebean.getFeeid()};
	 this.getJdbcTemplate().update(sql,objs);   
	}
	//诊疗划价
	public void addZlHj(Fee_Type feetypebean) throws Exception{
		final StringBuffer sql = new StringBuffer("insert into feetype(");
	    sql.append("order_id,jytpye,hjinfo_totalM,hjinfo_getM,sys_user_id,hjinfo_dotime,flag)");
		sql.append(" values(?,?,?,?,?,?,?)");
	    final Object[] objs = {feetypebean.getOrder_id(),feetypebean.getJytpye(),feetypebean.getHjinfo_totalM(),feetypebean.getHjinfo_getM(),feetypebean.getSys_user_id(),feetypebean.getHjinfo_dotime(),feetypebean.getFlag()};
	    KeyHolder keyHolder = new GeneratedKeyHolder();   
		 try{
			this.getJdbcTemplate().update(new PreparedStatementCreator(){         
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				int i = 0;
				int n = 0;
				PreparedStatement ps = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);  
				ps.setInt(++i, DataUtils.getInt(objs[n++]));
				ps.setInt(++i, DataUtils.getInt(objs[n++]));
			 	ps.setDouble(++i, DataUtils.getDouble(objs[n++])); 
				ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
				ps.setInt(++i, DataUtils.getInt(objs[n++]));
				ps.setTimestamp(++i, DateUtils.getTimestamp(objs[n++]));
				ps.setInt(++i, DataUtils.getInt(objs[n++]));
			    return ps;                   
		    }
              }, keyHolder);    
			}catch(Exception e){
				e.printStackTrace();
			}  
		}
	/****************************************查询、 退费 start***********************************************************/
	/*
	 * 查询收费类型列表
	 * */
	public  List<Fee_Type> queryFeeType(HashMap<Object,Object> condition){
		String sql=" select *  from feetype where 1=1 ";
		List<Object> list=new ArrayList<Object>();
		if(!condition.isEmpty()){
			if(condition.get("flag")!=null&&!"".equals(condition.get("flag"))){
			 	sql+=" and flag=? ";
			 	list.add(condition.get("flag"));
			}
			if(condition.get("hjinfo_dotime")!=null&&!"".equals(condition.get("hjinfo_dotime"))){
				sql+=" and hjinfo_dotime like ? ";
			 	list.add("%" + condition.get("hjinfo_dotime")+"%");
			}
			if(condition.get("fph")!=null&&!"".equals(condition.get("fph"))){
				sql+=" and fph=? ";
			 	list.add(condition.get("fph"));
			}
		}
		List<Fee_Type> listFeeType=null;
	    try {
			listFeeType=this.getJdbcTemplate().query(sql,list.toArray(),new Fee_Type());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listFeeType;
		
	}
	/*
	 * 修改收费类型表
	 * */
	public  void  modifyFeeType(final Fee_Type feetype){
		String sql=" update his_mz_db.feetype set  hjinfo_totalM = ? , flag = ?, void_userid =?, void_dotime = ? where feeid = ? and fph=? ";
	    try {
	    	this.getJdbcTemplate().update(sql,new PreparedStatementSetter(){
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
				  ps.setDouble(1,feetype.getHjinfo_totalM());
				  ps.setInt(2, feetype.getFlag());
				  ps.setInt(3, feetype.getVoid_userid());
				  ps.setTimestamp(4,new Timestamp(feetype.getVoid_dotime().getTime()) );
				  ps.setInt(5, feetype.getFeeid());
				  ps.setString(6,feetype.getFph());
				}
	    		
	    	});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 划价信息列表
	 * */
	public  List<MzCharge> queryMzCharge(HashMap<Object,Object> condition ){
		String sql=" select *  from  his_mz_hjinfo where 1=1 ";
		List<Object> listcondition=new ArrayList<Object>();
		if(!condition.isEmpty()){
			if(condition.get("flag")!=null&&!"".equals(condition.get("flag"))){
			 	sql+=" and flag=? ";
			 	listcondition.add(condition.get("flag"));
			}
			if(condition.get("hjinfo_dotime")!=null&&!"".equals(condition.get("hjinfo_dotime"))){
				sql+=" and hjinfo_dotime like ? ";
				listcondition.add("%" + condition.get("hjinfo_dotime")+"%");
			}
			if(condition.get("fph")!=null&&!"".equals(condition.get("fph"))){
				sql+=" and fph=? ";
				listcondition.add(condition.get("fph"));
			}
		}
		List<MzCharge> listhjinfo=null;
	    try {
	    	listhjinfo=this.getJdbcTemplate().query(sql,listcondition.toArray(),new MzCharge());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return listhjinfo;
		
	}
	/*
	 * 修改划价信息
	 * */
	public  void  modifyHjInfo(final MzCharge hjinfobean){
		String sql=" update his_mz_db.his_mz_hjinfo set hjinfo_totalM =?, flag = ?, void_userid = ? , void_dotime = ?, where id = ? and fph =? ";
	    try {
	    	this.getJdbcTemplate().update(sql,new PreparedStatementSetter(){

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					ps.setDouble(1, hjinfobean.getHjinfo_totalM());
					ps.setInt(2, hjinfobean.getFlag());
					ps.setInt(3,hjinfobean.getVoid_userid());
					ps.setTimestamp(4, new Timestamp(hjinfobean.getVoid_dotime().getTime()));
					ps.setInt(5, hjinfobean.getId());
					ps.setString(6,hjinfobean.getFph());
				}
	    		
	    	});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	/**************************************** 查询、退费 end***********************************************************/
}
