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
import com.jujie.his.baseinfo.Feetype;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

@SuppressWarnings("unchecked")
public class FeetypeDaoImpl  extends BaseJdbcDao{
	 
	 	@SuppressWarnings("static-access")
		public List<Feetype> queryFeetypeList(Page page)throws Exception{
			final String sql = "select * from fee_type order by feetypeId desc";
			List<Object> obj = new ArrayList<Object>();
			return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new Feetype());
		}
		
		 	public Integer addFeetype(Feetype feetype) throws Exception {
			
			final String sql = "insert into fee_type (feeName,feeContent,feeMoney,feeGroup,dw,feeRemark) values(?,?,?,?,?,?)";
			
			final Object[] objs ={feetype.getFeeName(),feetype.getFeeContent(),feetype.getFeeMoney(),feetype.getFeeGroup(),feetype.getDw(),feetype.getFeeRemark()};
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			try {
				this.getJdbcTemplate().update(new PreparedStatementCreator(){
					public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
						int i = 0;
						int n = 0;
						PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
						ps.setString(++i, DataUtils.getStringK(objs[n++]));
						ps.setString(++i, DataUtils.getStringK(objs[n++]));
						ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
						ps.setString(++i, DataUtils.getStringK(objs[n++]));
						ps.setString(++i, DataUtils.getStringK(objs[n++]));
						ps.setString(++i, DataUtils.getStringK(objs[n++]));
                        return ps;
					}
				}, keyHolder);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			return keyHolder.getKey().intValue();
			}
    	public Feetype queryFeetype(String feetypeId) throws Exception{

			final String sql ="select * from  fee_type where feetypeId = "+feetypeId;
			List<Feetype> list = this.getJdbcTemplate().query(sql, new Feetype());
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
			return null;
		}
    	public void updateFeetype(Feetype feetype) throws Exception{

	 
		String sql = "update fee_type set feeName=?,feeContent=?,feeMoney=?,feeGroup=?,dw=?,feeRemark=? where feetypeId=?";
		Object[] objs = {feetype.getFeeName(),feetype.getFeeContent(),feetype.getFeeMoney(),feetype.getFeeGroup(),feetype.getDw(),feetype.getFeeRemark(),feetype.getFeetypeId()};
		this.getJdbcTemplate().update(sql,objs);
			 
			 
		}
      public void deleteFeetype(String[] feetypeId) throws Exception{
			String[] sql = new String[feetypeId.length];
			 sql[0] = "delete from fee_type where Feetype_id="+feetypeId[0];
			this.getJdbcTemplate().batchUpdate(sql);
		}	

}
