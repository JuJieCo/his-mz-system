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
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.Medicinal;
import com.jujie.his.inventory.HisInventoryBill;
import com.jujie.his.inventory.HisInventoryHistory;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class HisInventoryHistoryDaoImpl extends BaseJdbcDao{

	@SuppressWarnings("unchecked")
	public HisInventoryHistory queryOneInventoryHistory(int cbuid) throws Exception{
		 String sql = "select * from his_mz_inventoryHistory where inventoryHistory_id="+cbuid;
			List<HisInventoryHistory> HisInventoryHistoryList = this.getJdbcTemplate().query(sql,new HisInventoryHistory());
			if(HisInventoryHistoryList!=null&&HisInventoryHistoryList.size()>0){
				return HisInventoryHistoryList.get(0);
			}
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<HisInventoryHistory> queryAllInventoryHistory(Object[] objs) throws Exception{
		    String sql = " SELECT hy.*,bd.buytime,bd.createtime,bd.inventoryBill_opertime,bd.inventoryBill_statue,bd.inventoryBill_type,bd.sys_user_id,bd.house_id,ml.medicinal_name,ml.medicinal_pycode,ml.medicinal_statue,ml.unit_id,ml.standard_id,ml.medicinal_type,cy.company_name,cy.company_alias,cy.company_statue " +
		    		    " ,hs.standard_name,hs.standard_statue,hu.unit_name,hu.unit_statue "+
		    			" FROM his_mz_inventoryHistory hy "+ 
		    			" LEFT JOIN his_mz_company  cy ON hy.company_id=cy.company_id "+ 
		    			" LEFT JOIN his_mz_medicinal ml ON ml.medicinal_id=hy.medicinal_id "+ 
		    			" LEFT JOIN his_mz_inventoryBill bd ON bd.inventoryBill_id=hy.inventorybill_id   "+
		    			" left join his_mz_unit  hu on hu.unit_id=ml.unit_id "+
		    			" left join his_mz_standard hs on hs.standard_id=ml.standard_id "+
		    			" where 1=1 ";
		    
		    List<Object> obj = new ArrayList<Object>();
			 if(objs!=null&&objs.length>0){
				 if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
						sql += " and hy.inventoryHistory_id =? ";
						obj.add(objs[0]);
					}
				 if(objs[1]!=null&&!"".equals(DataUtils.getStringK(objs[1]))){
						sql += " and hy.inventoryBill_id =? ";
						obj.add(objs[1]);
				}
				if(objs[2]!=null&&!"".equals(DataUtils.getStringK(objs[2]))){
					sql += " and hy.company_id =? ";
					obj.add(objs[2]);
				}
				if(objs[3]!=null&&!"".equals(DataUtils.getStringK(objs[3]))){
					sql += " and hy.item_code =? ";
					obj.add(objs[3]);
				}
			  }
			return this.getJdbcTemplate().query(sql,obj.toArray(),new HisInventoryHistory());
			
			
	}
	
	public HisInventoryHistory queryOneInventoryHistory(Object[] objs) throws Exception{
		 List<HisInventoryHistory> list=queryAllInventoryHistory(objs);
		 if(list!=null&&list.size()>0){
			 return list.get(0);
		 }
		 return null;
			
			
	}
	
	
	@SuppressWarnings("static-access")
	public List<HisInventoryHistory> queryAllInventoryHistory(Object[] objs,Page page) throws Exception{
		final List<HisInventoryHistory> hisInventoryHistoryList=new ArrayList<HisInventoryHistory>();
		List<Object> obj = new ArrayList<Object>();
		String sql = "select * from his_mz_inventoryHistory where 1=1 ";
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
				sql += " and inventoryHistory_id =?";
				obj.add(objs[0]);
			}
		}
		sql += " order by opertime desc ";
		
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				HisInventoryHistory history=new HisInventoryHistory();
				history.setInventoryHistory_id(rs.getInt("inventoryHistory_id"));
				Company company=new Company();
				company.setCompanyId(rs.getInt("company_id"));
				history.setCompany(company);
				history.setItem_code(rs.getString("item_code"));
				Medicinal medicinal=new Medicinal();
				medicinal.setMedicinalId(rs.getInt("medicinal_id"));
				history.setMedicinal(medicinal);
				history.setValidtime(rs.getTimestamp("validtime"));
				history.setBuytime(rs.getTimestamp("buytime"));
				history.setOpertime(rs.getTimestamp("opertime"));
				history.setHqty(rs.getInt("hqty"));
				history.setPurchase_price(rs.getDouble("purchase_price"));
				history.setResale_price(rs.getDouble("resale_price"));
				HisInventoryBill inventoryBill=new HisInventoryBill();
				inventoryBill.setInventoryBill_id(rs.getInt("inventoryBill_id"));
				history.setInventoryBill(inventoryBill);
				hisInventoryHistoryList.add(history);
			}
		 });
		return hisInventoryHistoryList;
	}
	
	public Integer addHisInventoryHistory(final HisInventoryHistory inventoryHistoryBean) throws Exception {
		
		final StringBuffer sql = new StringBuffer("insert into his_mz_inventoryHistory( ");
		sql.append("  company_id,item_code,medicinal_id,validtime,buytime,opertime,hqty,purchase_price,resale_price,inventoryBill_id ) ");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 try{
			 this.getJdbcTemplate().update(new PreparedStatementCreator() {	
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
					if(inventoryHistoryBean.getCompany().getCompanyId()==null||inventoryHistoryBean.getCompany().getCompanyId()==0){
						ps.setNull(1, Type.INT);
					}else{
						ps.setInt(1, inventoryHistoryBean.getCompany().getCompanyId());
					}
					ps.setString(2, inventoryHistoryBean.getItem_code());
					if(inventoryHistoryBean.getMedicinal().getMedicinalId()==null){
						ps.setNull(3, Type.INT);
					}else{
						ps.setInt(3, inventoryHistoryBean.getMedicinal().getMedicinalId());
					}
					ps.setTimestamp(4, new Timestamp(inventoryHistoryBean.getValidtime().getTime()));
					ps.setTimestamp(5, new Timestamp(inventoryHistoryBean.getBuytime().getTime()));
					ps.setTimestamp(6, new Timestamp(inventoryHistoryBean.getOpertime().getTime()));
					ps.setInt(7,inventoryHistoryBean.getHqty());
					ps.setDouble(8, inventoryHistoryBean.getPurchase_price());
					ps.setDouble(9, inventoryHistoryBean.getResale_price());
					ps.setInt(10, inventoryHistoryBean.getInventoryBill().getInventoryBill_id());
					
					return ps;
				}
			}, keyHolder);
			}catch(Exception e){
				e.printStackTrace();
			}
			return keyHolder.getKey().intValue();
	}
	public void deleteHisInventoryHistoryByBllid(int billid)throws Exception{
		String sql=" delete from his_mz_inventoryHistory where  inventoryBill_id=? ";
		this.getJdbcTemplate().update(sql,new Object[]{billid});
		
	}
	public void deleteHisInventoryHistory(final List<Integer> ids) throws Exception {
		String sql=" delete from his_mz_inventoryHistory where inventoryHistory_id=? ";
		this.getJdbcTemplate().batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int arg1) throws SQLException {
				ps.setInt(1, ids.get(arg1));
			}
			@Override
			public int getBatchSize() {
				return ids.size();
			}
		});
	}
}
