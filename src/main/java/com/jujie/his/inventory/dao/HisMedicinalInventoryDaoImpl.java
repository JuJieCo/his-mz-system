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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.House;
import com.jujie.his.baseinfo.Medicinal;
import com.jujie.his.inventory.HisMedicinalInventory;
import com.jujie.his.mz.MzYp;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class HisMedicinalInventoryDaoImpl extends BaseJdbcDao{

	@SuppressWarnings("unchecked")
	public HisMedicinalInventory queryOneMedicinalInventory(int cbuid) throws Exception{
		String sql = " select my.*,hy.company_name,hy.company_alias,hy.company_statue,hm.medicinal_name,hm.medicinal_pycode,hm.medicinal_statue,hm.unit_id,hm.standard_id,hm.medicinal_type,hh.house_name,hh.house_statue "+
		 " ,hs.standard_name,hs.standard_statue,hu.unit_name,hu.unit_statue "+
		 " from his_mz_medicinalInventory my "+
		 " left join his_mz_company hy  on my.company_id=hy.company_id "+
		 " left join his_mz_medicinal hm on hm.medicinal_id=my.medicinal_id "+
		 " left join his_mz_house hh on hh.house_id=my.house_id "+
		 " left join his_mz_standard hs on hm.standard_id=hs.standard_id "+
		 " left join his_mz_unit hu on hu.unit_id=hm.unit_id "+
		 "  where  my.invertory_id="+cbuid;
			List<HisMedicinalInventory> HisMedicinalInventoryList = this.getJdbcTemplate().query(sql,new HisMedicinalInventory());
			if(HisMedicinalInventoryList!=null&&HisMedicinalInventoryList.size()>0){
				return HisMedicinalInventoryList.get(0);
			}
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<HisMedicinalInventory> queryAllMedicinalInventory(Object[] objs,Page page) throws Exception{ 
		 String sql = " select my.*,hy.company_name,hy.company_alias,hy.company_statue,hm.medicinal_name,hm.medicinal_pycode,hm.medicinal_statue,hm.unit_id,hm.standard_id,hm.medicinal_type,hh.house_name,hh.house_statue "+
		 " ,hs.standard_name,hs.standard_statue,hu.unit_name,hu.unit_statue "+
		 " from his_mz_medicinalInventory my "+
		 " left join his_mz_company hy  on my.company_id=hy.company_id "+
		 " left join his_mz_medicinal hm on hm.medicinal_id=my.medicinal_id "+
		 " left join his_mz_house hh on hh.house_id=my.house_id "+
		 " left join his_mz_standard hs on hm.standard_id=hs.standard_id "+
		 " left join his_mz_unit hu on hu.unit_id=hm.unit_id "+
		 "  where 1=1 ";
		 List<Object> obj = new ArrayList<Object>();
		 if(objs!=null&&objs.length>0){
			 if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
					sql += " and my.invertory_id =? ";
					obj.add(objs[0]);
				}
			 if(objs[1]!=null&&!"".equals(DataUtils.getStringK(objs[1]))){
					sql += " and my.company_id =? ";
					obj.add(objs[1]);
			}
			if(objs[2]!=null&&!"".equals(DataUtils.getStringK(objs[2]))){
				sql += " and my.medicinal_id =? ";
				obj.add(objs[2]);
			}
			if(objs[3]!=null&&!"".equals(DataUtils.getStringK(objs[3]))){
				sql += " and my.item_code =? ";
				obj.add(objs[3]);
			}
			if(objs[4]!=null&&!"".equals(DataUtils.getStringK(objs[4]))){
				sql += " and my.inventory_type=? ";
				obj.add(objs[4]);
			}
			if(objs[5]!=null&&!"".equals(DataUtils.getStringK(objs[5]))){
				sql += " and hm.medicinal_name like '%"+objs[5]+"%' ";
			}
			if(objs[6]!=null&&!"".equals(DataUtils.getStringK(objs[6]))){
				sql += " and hh.house_id=? ";
				obj.add(objs[5]);
			}
		  }
		if(page!=null){
			sql = PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL);
		}
		return this.getJdbcTemplate().query(sql,obj.toArray(),new HisMedicinalInventory());
			
	}
	public List<HisMedicinalInventory> queryAllMedicinalInventory(List<Integer> medicinalIds,int type) throws Exception{ 
		List<Integer> listids = new ArrayList<Integer>(); 
		String sql = " select my.*,hy.company_name,hy.company_alias,hy.company_statue,hm.medicinal_name,hm.medicinal_pycode,hm.medicinal_statue,hm.unit_id,hm.standard_id,hm.medicinal_type,hh.house_name,hh.house_statue "+
		 " ,hs.standard_name,hs.standard_statue,hu.unit_name,hu.unit_statue "+
		 " from his_mz_medicinalInventory my "+
		 " left join his_mz_company hy  on my.company_id=hy.company_id "+
		 " left join his_mz_medicinal hm on hm.medicinal_id=my.medicinal_id "+
		 " left join his_mz_house hh on hh.house_id=my.house_id "+
		 " left join his_mz_standard hs on hm.standard_id=hs.standard_id "+
		 " left join his_mz_unit hu on hu.unit_id=hm.unit_id "+
		 "  where 1=1 ";
		 if(medicinalIds.size()>0){
			 sql+=" and my.medicinal_id in ( ";
			 for(int i=0;i<medicinalIds.size();i++){
				sql+=(i+1==medicinalIds.size()?"?":"?,");
				listids.add(medicinalIds.get(i));
			 }
			 sql+=" )";
		 }
		 sql+=" and my.inventory_type=? ";
		 listids.add(type);
		 return this.getJdbcTemplate().query(sql,listids.toArray(),new HisMedicinalInventory());
	}
	
	public HisMedicinalInventory queryOneMedicinalInventory(Object[] objs ) throws Exception{
		List<HisMedicinalInventory> list=queryAllMedicinalInventory(objs,null);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("static-access")
	public List<HisMedicinalInventory> queryAllHisMedicinalInventoryList(Object[] objs,Page page) throws Exception{
		final List<HisMedicinalInventory> HisMedicinalInventoryList=new ArrayList<HisMedicinalInventory>();
		List<Object> obj = new ArrayList<Object>();
		String sql = "select * from his_mz_medicinalInventory where 1=1 ";
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
				sql += " and invertory_id =?";
				obj.add(objs[0]);
			}
		}
		sql += " order by opertime desc ";
		
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				HisMedicinalInventory mecInventory=new HisMedicinalInventory();
				mecInventory.setInvertory_id(rs.getInt("invertory_id"));
				Company company=new Company();
				company.setCompanyId(rs.getInt("company_id"));
				mecInventory.setCompany(company);
				Medicinal his_mz=new Medicinal();
				mecInventory.setItem_code(rs.getString("item_code"));
				
				his_mz.setMedicinalId(rs.getInt("medicinal_id"));
				mecInventory.setMedicinal(his_mz);
				mecInventory.setValidtime(rs.getTimestamp("validtime"));
				mecInventory.setHqty(rs.getInt("hqty"));
				mecInventory.setRqty(rs.getInt("rqty"));
				mecInventory.setPurchase_price(rs.getDouble("purchase_price"));
				mecInventory.setResale_price(rs.getDouble("resale_price"));
				mecInventory.setInventory_type(rs.getInt("inventory_type"));
				House hishouse=new House();
				hishouse.setHouseId(rs.getInt("house_id"));
				mecInventory.setHouse(hishouse);
				HisMedicinalInventoryList.add(mecInventory);
			}
		 });
		return HisMedicinalInventoryList;
	}
	
	public Integer addHisMedicinalInventory(final HisMedicinalInventory medicinalInventoryBean) throws Exception {
		
		final StringBuffer sql = new StringBuffer("insert into his_mz_medicinalInventory( ");
		sql.append("  company_id,item_code,medicinal_id,validtime,hqty,rqty,purchase_price,resale_price,inventory_type,house_id) ");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 try{
			 this.getJdbcTemplate().update(new PreparedStatementCreator() {	
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
					if(medicinalInventoryBean.getCompany()==null||medicinalInventoryBean.getCompany().getCompanyId()==0){
						ps.setNull(1,Type.INT);
					}else{
						ps.setInt(1, medicinalInventoryBean.getCompany().getCompanyId());
					}
					ps.setString(2, medicinalInventoryBean.getItem_code());
					if(medicinalInventoryBean.getMedicinal()==null||medicinalInventoryBean.getMedicinal().getMedicinalId()==0){
						ps.setNull(3, Type.INT);
					}else{
						ps.setInt(3, medicinalInventoryBean.getMedicinal().getMedicinalId());
					}
					if(medicinalInventoryBean.getValidtime()==null){
						ps.setNull(4, Type.OBJECT);
					}else{
						ps.setTimestamp(4, new Timestamp(medicinalInventoryBean.getValidtime().getTime()));
					}
					ps.setInt(5, medicinalInventoryBean.getHqty());
					ps.setInt(6,medicinalInventoryBean.getRqty());
					ps.setDouble(7,medicinalInventoryBean.getPurchase_price());
					ps.setDouble(8, medicinalInventoryBean.getResale_price());
					ps.setInt(9, medicinalInventoryBean.getInventory_type());
					if(medicinalInventoryBean.getHouse()!=null&&medicinalInventoryBean.getHouse().getHouseId()!=0){
						ps.setInt(10, medicinalInventoryBean.getHouse().getHouseId());	
					}else{
						ps.setNull(10,Type.INT);
					}
					
					return ps;
				}
			}, keyHolder);
			}catch(Exception e){
				e.printStackTrace();
			}
			return keyHolder.getKey().intValue();
	}
	
	public void updateMedicinalHqty(int id,int num) throws Exception {
	  	String sql="update his_mz_medicinalInventory set hqty=?,rqty=? where invertory_id=? ";
	  	this.getJdbcTemplate().update(sql,new Object[]{num,num,id});
	}
	
	public void updateMedicinal(final HisMedicinalInventory medicinalInvertory ) throws Exception {
	  	String sql="  update his_mz_medicinalinventory"+
	  			   "  set  company_id = ? , item_code = ? , medicinal_id = ? , validtime = ? , hqty = ? , rqty =? , purchase_price =?, resale_price =? , inventory_type = ? , house_id = ? "+
	  			   "  where invertory_id = ? ";
	  	this.getJdbcTemplate().update(sql,new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				if(medicinalInvertory.getCompany().getCompanyId() == null || medicinalInvertory.getCompany().getCompanyId() == 0){
					ps.setNull(1, Type.INT);
				}else{
					ps.setInt(1, medicinalInvertory.getCompany().getCompanyId());
				}
				ps.setString(2, medicinalInvertory.getItem_code());
				ps.setInt(3, medicinalInvertory.getMedicinal().getMedicinalId());
				if(medicinalInvertory.getValidtime() == null){
					ps.setNull(4, Type.OBJECT);
				}else{
					ps.setTimestamp(4, new Timestamp(medicinalInvertory.getValidtime().getTime()));
				}
				ps.setInt(5, medicinalInvertory.getHqty());
				ps.setInt(6, medicinalInvertory.getRqty());
				ps.setDouble(7, medicinalInvertory.getPurchase_price());
				ps.setDouble(8, medicinalInvertory.getResale_price());
				ps.setInt(9,medicinalInvertory.getInventory_type());
				if(medicinalInvertory.getHouse().getHouseId() == null || medicinalInvertory.getHouse().getHouseId() == 0){
					ps.setNull(10, Type.INT);
				}else{
					ps.setInt(10, medicinalInvertory.getHouse().getHouseId());
				}
				
				ps.setInt(11, medicinalInvertory.getInvertory_id());
			}
	  	});
	}
	
	public void deleteHisMedicinalInventory(final List<Integer> ids) throws Exception {
		String sql=" delete from his_mz_medicinalInventory where invertory_id=? ";
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

	public void updateMedicinalprice(HisMedicinalInventory medicinal) throws Exception {
	  	String sql="update his_mz_medicinalInventory set resale_price=? where company_id=? and item_code=? and medicinal_id=? ";
	  	this.getJdbcTemplate().update(sql,new Object[]{medicinal.getResale_price(),medicinal.getCompany().getCompanyId(),medicinal.getItem_code(),medicinal.getMedicinal().getMedicinalId()});
	}
	public int getMediciNum(int medicinalid , int type){
		String sql=" select sum(hqty) count from  his_mz_medicinalInventory where inventory_type=? and medicinal_id=? ";
		int num=0;
		List<Integer> list=this.getJdbcTemplate().query(sql, new Object[]{type,medicinalid},new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				int count=rs.getInt("count");
				return count;
			}});
		if(list.size()>0){
			num=list.get(0);
		}
		return num;
	}
	public List<MzYp> getMzyp(int orderid){
		String sql=" select * from  his_mz_medicinaldetail where uuid=? ";
		return this.getJdbcTemplate().query(sql,new Object[]{orderid},new  MzYp());
	}
}
