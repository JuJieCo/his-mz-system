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
import com.jujie.his.inventory.HisChangepriceBill;
import com.jujie.his.inventory.HisChangepriceDetail;
import com.jujie.user.User;
import com.jujie.util.DataUtils;
import com.jujie.util.DateUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

public class HisChangepriceBillDaoImpl extends BaseJdbcDao{

	@SuppressWarnings("unchecked")
	public HisChangepriceBill queryOneChangepriceBill(int cbuid) throws Exception{
		 String sql = "select * from his_mz_changepriceBill where changepriceBill_id="+cbuid;
			List<HisChangepriceBill> hisChangepriceBillList = this.getJdbcTemplate().query(sql,new HisChangepriceBill());
			if(hisChangepriceBillList!=null&&hisChangepriceBillList.size()>0){
				return hisChangepriceBillList.get(0);
			}
			return null;
	}
	@SuppressWarnings("static-access")
	public List<HisChangepriceBill> queryAllChangepriceBill(Object[] objs,Page page) throws Exception{
		final List<HisChangepriceBill> hisChangepriceBillList=new ArrayList<HisChangepriceBill>();
		List<Object> obj = new ArrayList<Object>();
		String sql = "select * from his_mz_changepriceBill where 1=1 ";
		if(objs!=null&&objs.length>0){
			if(objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
				sql += " and changepriceBill_id =?";
				obj.add(objs[0]);
			}
		}
		sql += " order by changepriceBill_opertime desc ";
		
		this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				HisChangepriceBill hisChangepriceBill=new HisChangepriceBill();
				 try {
					 hisChangepriceBill.setChangepriceBill_id(rs.getInt("changepriceBill_id"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					 hisChangepriceBill.setChangepriceBill_cause(rs.getString("changepriceBill_cause"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					hisChangepriceBill.setChangepriceBill_createtime(rs.getTimestamp("changepriceBill_createtime"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					 hisChangepriceBill.setChangepriceBill_opertime(rs.getTime("changepriceBill_opertime"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					 hisChangepriceBill.setChangepriceBill_statue(rs.getInt("changepriceBill_statue"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					 User userbean=new User();
					 userbean.setSysUserId(rs.getInt("sys_user_id"));
					 hisChangepriceBill.setUser(userbean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hisChangepriceBillList.add(hisChangepriceBill);
			}
		 });
		return hisChangepriceBillList;
	}
	
public Integer addhisChangepriceBill(final HisChangepriceBill billBean) throws Exception {
		
		final StringBuffer sql = new StringBuffer("insert into his_mz_changepriceBill( ");
		sql.append(" changepriceBill_cause,changepriceBill_createtime,changepriceBill_opertime,changepriceBill_statue,sys_user_id ) ");
		sql.append(" values(?,?,?,?,?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 try{
			 this.getJdbcTemplate().update(new PreparedStatementCreator() {	
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setString(1, billBean.getChangepriceBill_cause());
					ps.setTimestamp(2, DateUtils.getTimestamp(billBean.getChangepriceBill_createtime()));
					if(billBean.getChangepriceBill_opertime()==null){
						ps.setNull(3, Type.OBJECT);
					}else{
						ps.setTimestamp(3, DateUtils.getTimestamp(billBean.getChangepriceBill_opertime()));
					}
					ps.setInt(4, billBean.getChangepriceBill_statue());
					ps.setInt(5, billBean.getUser().getSysUserId());
					return ps;
				}
			}, keyHolder);
			}catch(Exception e){
				e.printStackTrace();
			}
			return keyHolder.getKey().intValue();
	}
	
	public void deleteIntegraHisChangepriceBill(final List<Integer> changepriceBill_ids) throws Exception {
		String sql=" delete from his_mz_changepriceBill where changepriceBill_id=? ";
		this.getJdbcTemplate().batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int arg1) throws SQLException {
				ps.setInt(1, changepriceBill_ids.get(arg1));
			}
			@Override
			public int getBatchSize() {
				return changepriceBill_ids.size();
			}
		});
	}
	public void updateHisChangepriceBill(final HisChangepriceBill hischangepricebill ) throws Exception {
		String sql="update his_mz_db.his_mz_changepricebill set  changepriceBill_cause = ? , changepriceBill_opertime = ? , changepriceBill_statue = ? , sys_user_id = ? where changepriceBill_id = ? ";
		this.getJdbcTemplate().update(sql,new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,hischangepricebill.getChangepriceBill_cause());
				ps.setTimestamp(2, new Timestamp(hischangepricebill.getChangepriceBill_opertime().getTime()) );
				ps.setInt(3, hischangepricebill.getChangepriceBill_statue());
				ps.setInt(4, hischangepricebill.getUser().getSysUserId());
				ps.setInt(5, hischangepricebill.getChangepriceBill_id());
			}});
	}
	@SuppressWarnings("unchecked")
	public List<HisChangepriceDetail> queryChangepriceDetailByid(int changepriceBill_id) throws Exception{
		 String sql = "select * from his_mz_changepriceDetail where changepriceBill_id="+changepriceBill_id;
		 return this.getJdbcTemplate().query(sql,new HisChangepriceDetail());
	}
	public void deleteIntegraHisChangepriceBill(int changepriceBill_id) throws Exception {
		String sql=" delete from his_mz_changepriceBill where changepriceBill_id=? ";
		this.getJdbcTemplate().update(sql,new Object[]{changepriceBill_id});
	}
}
