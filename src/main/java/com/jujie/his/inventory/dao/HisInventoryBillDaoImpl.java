package com.jujie.his.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.House;
import com.jujie.his.inventory.HisInventoryBill;
import com.jujie.user.User;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class HisInventoryBillDaoImpl extends BaseJdbcDao {
	@SuppressWarnings("unchecked")
	public HisInventoryBill queryOneInventoryBill(int cbuid) throws Exception{
		 String sql = "select * from his_mz_inventoryBill where inventoryBill_id="+cbuid;
			List<HisInventoryBill> hisInventoryBillList = this.getJdbcTemplate().query(sql,new HisInventoryBill());
			if(hisInventoryBillList!=null&&hisInventoryBillList.size()>0){
				return hisInventoryBillList.get(0);
			}
			return null;
	}
	@SuppressWarnings("static-access")
	public List<HisInventoryBill> queryAllInventoryBill(Object[] objs,Page page) throws Exception{
		final List<HisInventoryBill> hisInventoryBillList=new ArrayList<HisInventoryBill>();
		List<Object> obj = new ArrayList<Object>();
		String sql = "select bill.*,cm.company_name,hh.house_name from his_mz_inventoryBill bill left join his_mz_company cm on bill.company_id=cm.company_id" 
			+" left join his_mz_house hh on bill.house_id=hh.house_id "	
			+" where 1=1 ";
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
				sql += " and inventoryBill_id =?";
				obj.add(objs[0]);
			}
			if(objs[1]!=null&&!"".equals(DataUtils.getStringK(objs[1]))){
				sql += " and inventoryBill_type =?";
				obj.add(objs[1]);
			}
			if(objs[2]!=null&&!"".equals(DataUtils.getStringK(objs[2]))){
				sql += " and inventoryBill_statue =?";
				obj.add(objs[2]);
			}
		}
		sql += " order by createtime desc ";
		
		if(page!=null){
			sql = PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL);
		}
		
		this.getJdbcTemplate().query(sql, obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				HisInventoryBill hisInventoryBill=new HisInventoryBill();
				 try {
					 hisInventoryBill.setInventoryBill_id(rs.getInt("inventoryBill_id"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					 Company company=new Company();
					 company.setCompanyId(rs.getInt("company_id"));
					 company.setCompanyName(rs.getString("company_name"));
					 hisInventoryBill.setHiscompany(company);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					hisInventoryBill.setBuytime(rs.getTimestamp("buytime"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					 hisInventoryBill.setCreatetime(rs.getTimestamp("createtime"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					 hisInventoryBill.setInventoryBill_opertime(rs.getTimestamp("inventoryBill_opertime"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 hisInventoryBill.setInventoryBill_statue(rs.getInt("inventoryBill_statue"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					hisInventoryBill.setInventoryBill_type(rs.getInt("inventoryBill_type"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					User userbean=new User();
					userbean.setSysUserId(rs.getInt("sys_user_id"));
					hisInventoryBill.setUser(userbean);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try{
					House hisHouse=new House();
					hisHouse.setHouseId(rs.getInt("house_id"));
					hisHouse.setHouseName(rs.getString("house_name"));
					hisInventoryBill.setHisHouse(hisHouse);
				}catch(Exception e){
					e.printStackTrace();
				}
				hisInventoryBillList.add(hisInventoryBill);
			}
		 });
		return hisInventoryBillList;
	}
	
	public Integer addhisInventoryBill(final HisInventoryBill billBean) throws Exception {
		final StringBuffer sql = new StringBuffer("insert into his_mz_inventoryBill( ");
		sql.append(" company_id,buytime,createtime,inventoryBill_opertime,inventoryBill_statue,inventoryBill_type,sys_user_id,house_id) ");
		sql.append(" values(?,?,?,?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 try{
			 this.getJdbcTemplate().update(new PreparedStatementCreator() {	
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
					if(billBean.getHiscompany().getCompanyId()==null){
						ps.setNull(1, Type.INT);
					}else{
						ps.setInt(1, billBean.getHiscompany().getCompanyId());
					}
					if(billBean.getBuytime()==null){
						ps.setNull(2, Type.OBJECT);
					}else{
						ps.setTimestamp(2, new Timestamp(billBean.getBuytime().getTime()));
					}
					ps.setTimestamp(3, new Timestamp(billBean.getCreatetime().getTime()));
					ps.setTimestamp(4, new Timestamp(billBean.getInventoryBill_opertime().getTime()));
					ps.setInt(5, billBean.getInventoryBill_statue());
					ps.setInt(6, billBean.getInventoryBill_type());
					if(billBean.getUser().getSysUserId()==null){
						ps.setNull(7, Type.INT);
					}else{
						ps.setInt(7, billBean.getUser().getSysUserId());
					}
					if(billBean.getHisHouse().getHouseId()==null){
						ps.setNull(8, Type.INT);
					}else{
						ps.setInt(8, billBean.getHisHouse().getHouseId());
					}
					return ps;
				}
			}, keyHolder);
			}catch(Exception e){
				e.printStackTrace();
			}
			return keyHolder.getKey().intValue();
	}
	
	public void deleteInventoryBill(final List<Integer> inventoryBillids) throws Exception {
		String sql=" delete from his_mz_inventoryBill where inventoryBill_id=? ";
		this.getJdbcTemplate().batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int arg1) throws SQLException {
				ps.setInt(1, inventoryBillids.get(arg1));
			}
			@Override
			public int getBatchSize() {
				return inventoryBillids.size();
			}
		});
	}
	
	public void updateInventorybillStatus(int id,int status)throws Exception{
		String sql=" update his_mz_inventoryBill set inventoryBill_statue=? where inventoryBill_id=? ";
		this.getJdbcTemplate().update(sql,new Object[]{status,id});
	}
	public void updateInventorybill(final HisInventoryBill bill)throws Exception{
		String sql=" update his_mz_inventoryBill set company_id=?,buytime=?,createtime=?,inventoryBill_opertime=?,inventoryBill_statue=?,inventoryBill_type=?,sys_user_id=?,house_id=? where inventoryBill_id=? ";
		this.getJdbcTemplate().update(sql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				if(bill.getHiscompany().getCompanyId()==null||bill.getHiscompany().getCompanyId()==0){
					ps.setNull(1, Type.INT);
				}else{
					ps.setInt(1, bill.getHiscompany().getCompanyId());	
				}
				if(bill.getBuytime()==null){
					ps.setNull(2, Type.OBJECT);
				}else{
					ps.setTimestamp(2, new Timestamp(bill.getBuytime().getTime()));
				}
				if(bill.getCreatetime()==null){
					ps.setNull(3, Type.OBJECT);
			    }else{
			    	ps.setTimestamp(3, new Timestamp(bill.getCreatetime().getTime()));					
				}
				if(bill.getInventoryBill_opertime()==null){
					ps.setNull(4, Type.OBJECT);
				}else{
					ps.setTimestamp(4, new Timestamp(bill.getInventoryBill_opertime().getTime()));
				}
				ps.setInt(5, bill.getInventoryBill_statue());
				ps.setInt(6, bill.getInventoryBill_type());
				if( bill.getUser().getSysUserId()==null){
					ps.setNull(7, Type.INT);
				}else{
					ps.setInt(7, bill.getUser().getSysUserId());	
				}
				if( bill.getHisHouse().getHouseId()==null||bill.getHisHouse().getHouseId()==0){
					ps.setNull(8, Type.INT);
				}else{
					ps.setInt(8, bill.getHisHouse().getHouseId());	
				}
				ps.setInt(9, bill.getInventoryBill_id());
			}});
	}
}
