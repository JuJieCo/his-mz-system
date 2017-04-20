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
import com.jujie.his.baseinfo.Standard;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;


@SuppressWarnings("unchecked")
public class StandardDaoImpl  extends BaseJdbcDao{

	// 取his_mz_standard表 返回规格List
	
	
		@SuppressWarnings("static-access")
		public List<Standard> queryStandardList(Page page)throws Exception{
			final String sql = "select * from his_mz_standard  where standard_id>0 order by standard_id desc ";
			List<Object> obj = new ArrayList<Object>();
			return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new Standard());


		}
		
		
		//新增规格
		public Integer addStandard(Standard standard) throws Exception {
			
			final String sql = "insert into his_mz_standard (standard_name,standard_statue) values(?,?)";
			
			final Object[] objs ={standard.getStandardName(),standard.getStandardStatue()};
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
		
		public Standard queryStandard(String standardId) throws Exception{

			final String sql ="select * from  his_mz_standard where standard_id = "+standardId;
			List<Standard> list = this.getJdbcTemplate().query(sql, new Standard());
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
			return null;
		}
		
		
		//修改规格
		public void updateStandard(Standard standard ,String statue) throws Exception{

			if(null!=statue&&!"".equals(statue)){
				if(statue.equals("isStatue")){
					String sql = "update his_mz_standard set standard_statue=? where standard_id=?";
					Object[] objs = {standard.getStandardStatue(),standard.getStandardId()};
					this.getJdbcTemplate().update(sql,objs);
				}
				if(statue.equals("noStatue")){
					String sql = "update his_mz_standard set standard_name=? where standard_id=?";
					Object[] objs = {standard.getStandardName(),standard.getStandardId()};
					this.getJdbcTemplate().update(sql,objs);
				}
				
			}
		}
		
		//删除规格
		public void deleteStandard(String[] standardId) throws Exception{
			String[] sql = new String[standardId.length];
			 sql[0] = "delete from his_mz_standard where standard_id="+standardId[0];
			this.getJdbcTemplate().batchUpdate(sql);
		}	
}
