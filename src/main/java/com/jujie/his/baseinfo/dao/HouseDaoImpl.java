package com.jujie.his.baseinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.baseinfo.House;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

@SuppressWarnings("unchecked")
public class HouseDaoImpl  extends BaseJdbcDao{


	// 取his_mz_house表 返回药房List
	
	
		@SuppressWarnings("static-access")
		public List<House> queryHouseList(Page page)throws Exception{
			final String sql = "select * from his_mz_house order by house_id desc ";
			List<Object> obj = new ArrayList<Object>();
			return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new House());


		}
		
		
		//新增药房
		public Integer addHouse(House house) throws Exception {
			
			final String sql = "insert into his_mz_house (house_name,house_statue) values(?,?)";
			
			final Object[] objs ={house.getHouseName(),house.getHouseStatue()};
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			try {
				this.getJdbcTemplate().update(new PreparedStatementCreator(){
					public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
						int i = 0;
						int n = 0;
						PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
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

		
		//查询byID
		
		public House queryHouse(String houseId) throws Exception{

			final String sql ="select * from  his_mz_house where house_id = "+houseId;
			List<House> list = this.getJdbcTemplate().query(sql, new House());
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
			return null;
		}
		
		
		//修改药房
		public void updateHouse(House house ,String statue) throws Exception{

			if(null!=statue&&!"".equals(statue)){
				if(statue.equals("isStatue")){
					String sql = "update his_mz_house set house_statue=? where house_id=?";
					Object[] objs = {house.getHouseStatue(),house.getHouseId()};
					this.getJdbcTemplate().update(sql,objs);
				}
				if(statue.equals("noStatue")){
					String sql = "update his_mz_house set house_name=? where house_id=?";
					Object[] objs = {house.getHouseName(),house.getHouseId()};
					this.getJdbcTemplate().update(sql,objs);
				}
				
			}
		}
		
		//删除药房
		public void deleteHouse(String[] houseId) throws Exception{
			String[] sql = new String[houseId.length];
			 sql[0] = "delete from his_mz_house where house_id="+houseId[0];
			this.getJdbcTemplate().batchUpdate(sql);
		}	
}
