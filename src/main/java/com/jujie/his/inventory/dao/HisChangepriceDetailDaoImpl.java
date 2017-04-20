package com.jujie.his.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.Medicinal;
import com.jujie.his.inventory.HisChangepriceBill;
import com.jujie.his.inventory.HisChangepriceDetail;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class HisChangepriceDetailDaoImpl extends BaseJdbcDao{

	@SuppressWarnings("unchecked")
	public HisChangepriceDetail queryOneChangepriceDetail(int cbuid) throws Exception{
		 String sql = "select * from his_mz_changepriceDetail where changepriceDetail_id="+cbuid;
			List<HisChangepriceDetail> HisChangepriceDetailList = this.getJdbcTemplate().query(sql,new HisChangepriceDetail());
			if(HisChangepriceDetailList!=null&&HisChangepriceDetailList.size()>0){
				return HisChangepriceDetailList.get(0);
			}
			return null;
	}
	@SuppressWarnings("static-access")
	public List<HisChangepriceDetail> queryAllChangepriceBillDetail(Object[] objs,Page page) throws Exception{
		final List<HisChangepriceDetail> hisChangepriceBillList=new ArrayList<HisChangepriceDetail>();
		List<Object> obj = new ArrayList<Object>();
		String sql = "select * from his_mz_changepriceDetail where 1=1 ";
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
				sql += " and changepriceDetail_id =?";
				obj.add(objs[0]);
			}
		}
//		sql += " order by medicinal_id desc ";
		
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				 HisChangepriceDetail detail=new HisChangepriceDetail();
					detail.setChangepriceDetail_id(rs.getInt("changepriceDetail_id"));
					HisChangepriceBill bill=new HisChangepriceBill();
					bill.setChangepriceBill_id(rs.getInt("changepriceBill_id"));
					detail.setHisChangepriceBill(bill);
					Company hisCompany=new Company();
					hisCompany.setCompanyId(rs.getInt("company_id"));
					detail.setHisCompany(hisCompany);
					detail.setItem_code(rs.getString("item_code"));
					Medicinal his_medicinal=new Medicinal();
					his_medicinal.setMedicinalId(rs.getInt("medicinal_id"));
					detail.setHismedicinal(his_medicinal);
					detail.setNew_resale_price(rs.getDouble("new_resale_price"));
					detail.setOld_resale_price(rs.getDouble("old_resale_price"));
					hisChangepriceBillList.add(detail);
			}
		 });
		return hisChangepriceBillList;
	}
	
public Integer addhisChangepriceDatail(final HisChangepriceDetail billDetailBean) throws Exception {
		
		final StringBuffer sql = new StringBuffer("insert into his_mz_changepriceDetail( ");
		sql.append("  changepriceBill_id,company_id,item_code,medicinal_id,old_resale_price,new_resale_price ) ");
		sql.append(" values(?,?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 try{
			 this.getJdbcTemplate().update(new PreparedStatementCreator() {	
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, billDetailBean.getHisChangepriceBill().getChangepriceBill_id());
					ps.setInt(2,  billDetailBean.getHisCompany().getCompanyId());
					ps.setString(3,  billDetailBean.getItem_code());
					ps.setInt(4,  billDetailBean.getHismedicinal().getMedicinalId());
					ps.setDouble(5,  billDetailBean.getOld_resale_price());
					ps.setDouble(6,  billDetailBean.getNew_resale_price());
					return ps;
				}
			}, keyHolder);
			}catch(Exception e){
				e.printStackTrace();
			}
			return keyHolder.getKey().intValue();
	}
	
	public void deleteHisChangepriceDetail(final List<Integer> HisChangepriceDetailids) throws Exception {
		String sql=" delete from his_mz_changepriceDetail where changepriceDetail_id=? ";
		this.getJdbcTemplate().batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int arg1) throws SQLException {
				ps.setInt(1, HisChangepriceDetailids.get(arg1));
			}
			@Override
			public int getBatchSize() {
				return HisChangepriceDetailids.size();
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<HisChangepriceDetail> queryChangepriceDetailByid(int changepriceBill_id) throws Exception{
		 String sql = "select * from his_mz_changepriceDetail where changepriceBill_id="+changepriceBill_id;
		 return this.getJdbcTemplate().query(sql,new HisChangepriceDetail());
	}
	public void deleteHisChangepriceDetailByid(final int HisChangepriceBillid) throws Exception {
		String sql=" delete from his_mz_changepriceDetail where changepriceBill_id=? ";
		this.getJdbcTemplate().update(sql,new Object[]{HisChangepriceBillid});
	}
	
}
