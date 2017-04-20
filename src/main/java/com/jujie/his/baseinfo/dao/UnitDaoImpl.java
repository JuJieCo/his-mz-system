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
import com.jujie.his.baseinfo.Unit;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

@SuppressWarnings("unchecked")
public class UnitDaoImpl  extends BaseJdbcDao{

	// 取his_mz_unit表 返回单位List
	
	
	@SuppressWarnings("static-access")
	public List<Unit> queryUnitList(Page page)throws Exception{
		final String sql = "select * from his_mz_unit where unit_id>0 order by unit_id desc  ";
		List<Object> obj = new ArrayList<Object>();
		return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new Unit());



	}
	
	
	//新增单位
	public Integer addUnit(Unit unit) throws Exception {
		
		final String sql = "insert into his_mz_unit (unit_name,unit_statue) values(?,?)";
		
		final Object[] objs ={unit.getUnitName(),unit.getUnitStatue()};
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
	
	public Unit queryUnit(String unitId) throws Exception{

		final String sql ="select * from  his_mz_unit where unit_id = "+unitId;
		List<Unit> list = this.getJdbcTemplate().query(sql, new Unit());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	//修改单位
	public void updateUnit(Unit unit ,String statue) throws Exception{

		if(null!=statue&&!"".equals(statue)){
			if(statue.equals("isStatue")){
				String sql = "update his_mz_unit set unit_statue=? where unit_id=?";
				Object[] objs = {unit.getUnitStatue(),unit.getUnitId()};
				this.getJdbcTemplate().update(sql,objs);
			}
			if(statue.equals("noStatue")){
				String sql = "update his_mz_unit set unit_name=? where unit_id=?";
				Object[] objs = {unit.getUnitName(),unit.getUnitId()};
				this.getJdbcTemplate().update(sql,objs);
			}
			
		}
	}
	
	//删除单位
	public void deleteUnit(String[] unitId) throws Exception{
		String[] sql = new String[unitId.length];
		 sql[0] = "delete from his_mz_unit where unit_id="+unitId[0];
		this.getJdbcTemplate().batchUpdate(sql);
	}	
}
