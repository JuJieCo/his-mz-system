package com.jujie.his.medicare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jujie.global.dao.BaseJdbcDao;
import com.jujie.his.medicare.CitizenMedDE;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;


@SuppressWarnings("unchecked")
public class CitizenMedDAOImpl extends BaseJdbcDao{
	
	@SuppressWarnings("static-access")
	public List<CitizenMedDE> queryCitizenMedDEList(Object[] objs , Page page)throws Exception{
		String sql = "select * from  his_yb_citizen where 1=1";
		final List<Object> obj = new ArrayList<Object>();
		if(objs!=null&&objs.length>0){
			if(!"".equals(objs[0])&&objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
				sql += "  and ybcode = ? ";
				obj.add(objs[0]);
			}
			if(!"".equals(objs[1])&&objs[1]!=null&&!"".equals(DataUtils.getStringK(objs[1]))){
				sql += "  and balancedate = ? ";
				obj.add(objs[1]);
			}
		}
		sql += " order by citizenid desc ";

		return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new CitizenMedDE());
	}
	
	
	

	public Integer addCitizenMedDE(CitizenMedDE citizenMedDE)throws Exception {
		final String sql = "insert into his_yb_citizen ( " +
				" name,sex, age,sfcode,addr,ybcode,zycode,dept,clies,result,indate,outdate," +
				" dates,balancetype ,balancedate,persontype,oldtcjjlj,nowtcjjlj,checkcode," +
				" zfyfper,zfyftotal,overcwfper,overcwftotal,otherfper,otherftotal,ybxezfper," +
				" ybxezftotal,qfbzper,qfbztotal,zfbiliper,grblzfper,grblzftotal,peralltotal," +
				" tczfper,tczfalltotal,alltotal,opertime,zyfytotal" +
				" ) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		final Object[] objs = { citizenMedDE.getName(),citizenMedDE.getSex(),citizenMedDE.getAge(),
				citizenMedDE.getSfcode(),citizenMedDE.getAddr(),citizenMedDE.getYbcode(),citizenMedDE.getZycode(),
				citizenMedDE.getDept(),citizenMedDE.getClies(),citizenMedDE.getResult(),
				citizenMedDE.getIndate(),citizenMedDE.getOutdate(),citizenMedDE.getDates(),
				citizenMedDE.getBalancetype(),citizenMedDE.getBalancedate(),citizenMedDE.getPersontype(),
				citizenMedDE.getOldtcjjlj(),citizenMedDE.getNowtcjjlj(),citizenMedDE.getCheckcode(),
				citizenMedDE.getZfyfper(),citizenMedDE.getZfyftotal(),citizenMedDE.getOvercwfper(),
				citizenMedDE.getOvercwftotal(),citizenMedDE.getOtherfper(),citizenMedDE.getOtherftotal(),
				citizenMedDE.getYbxezfper(),citizenMedDE.getYbxezftotal(),citizenMedDE.getQfbzper(),
				citizenMedDE.getQfbztotal(),citizenMedDE.getZfbiliper(),citizenMedDE.getGrblzfper(),
				citizenMedDE.getGrblzftotal(),citizenMedDE.getPeralltotal(),citizenMedDE.getTczfper(),
				citizenMedDE.getTczfalltotal(),citizenMedDE.getAlltotal(),citizenMedDE.getOpertime(),
				citizenMedDE.getZyfytotal()
			};
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			this.getJdbcTemplate().update(new PreparedStatementCreator(){
				public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
					int i = 0;
					int n = 0;
					PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);						
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setInt(++i, 	  DataUtils.getInt(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));
					ps.setString(++i, DataUtils.getString(objs[n++]));
					ps.setDouble(++i, DataUtils.getDouble(objs[n++]));					
					return ps;
				}
			}, keyHolder);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return keyHolder.getKey().intValue();	
	}

	
	public Integer updateCitizenMedDE(CitizenMedDE citizenMedDE){
		String sql = "update his_yb_citizen set  name=?,sex=?,age=?,sfcode=?,addr=?,ybcode=?,zycode=?,dept=?," +
				" clies=?,result=?,indate=?,outdate=?,dates=?, balancetype=?, " +
				" balancedate=?,persontype=?,oldtcjjlj=?,nowtcjjlj=?,checkcode=?, " +
				" zfyfper=?,zfyftotal=?,overcwfper=?,overcwftotal=?,otherfper=?, " +
				" otherftotal=?,ybxezfper=?,ybxezftotal=?,qfbzper=?,qfbztotal=?," +
				" zfbiliper=?,grblzfper=?,grblzftotal=?,peralltotal=?,tczfper=?," +
				" tczfalltotal=?,alltotal=?,opertime=?,zyfytotal=? where citizenid=?";
		Object[] objs = {citizenMedDE.getName(),citizenMedDE.getSex(),citizenMedDE.getAge(),
				 citizenMedDE.getSfcode(),citizenMedDE.getAddr(),citizenMedDE.getYbcode(),
				 citizenMedDE.getZycode(),citizenMedDE.getDept(),citizenMedDE.getClies(),
				 citizenMedDE.getResult(),citizenMedDE.getIndate(),citizenMedDE.getOutdate(),
				 citizenMedDE.getDates(),citizenMedDE.getBalancetype(),citizenMedDE.getBalancedate(),
				 citizenMedDE.getPersontype(),citizenMedDE.getOldtcjjlj(),citizenMedDE.getNowtcjjlj(),
				 citizenMedDE.getCheckcode(),citizenMedDE.getZfyfper(),citizenMedDE.getZfyftotal(),
				 citizenMedDE.getOvercwfper(),citizenMedDE.getOvercwftotal(),citizenMedDE.getOtherfper(),
				 citizenMedDE.getOtherftotal(),citizenMedDE.getYbxezfper(),citizenMedDE.getYbxezftotal(),
				 citizenMedDE.getQfbzper(),citizenMedDE.getQfbztotal(),citizenMedDE.getZfbiliper(),
				 citizenMedDE.getGrblzfper(),citizenMedDE.getGrblzftotal(),citizenMedDE.getPeralltotal(),
				 citizenMedDE.getTczfper(),citizenMedDE.getTczfalltotal(),citizenMedDE.getAlltotal(),
				 citizenMedDE.getOpertime(),citizenMedDE.getZyfytotal(),citizenMedDE.getCitizenId()
				 };
		return this.getJdbcTemplate().update(sql,objs);
	}

	
	public CitizenMedDE queryCitizenMedDEByID(String  citizenId) throws Exception{
		final String sql ="select * from his_yb_citizen where citizenid="+citizenId;
		
		List<CitizenMedDE> list = this.getJdbcTemplate().query(sql, new CitizenMedDE());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	
	///////////////204 统计数据

	
	
	///////////////205- 2 本月出院人数  count来的==sickNum ， 4 本月费用总额  sum来的==ybfy
	public CitizenMedDE querySickNumAndTczfalltotal(String balancedate) throws Exception{
		final String sql ="select count(ybcode)  countnum , sum(zyfytotal-zfyftotal-ybxezftotal) sumnum , sum(qfbztotal+grblzftotal) sumnumber from  his_yb_citizen where balancedate='"+balancedate+"'";
		final CitizenMedDE citizenMedDE = new CitizenMedDE();
		this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				 citizenMedDE.setCountNum(rs.getInt("countnum"));
				 citizenMedDE.setSumNum(rs.getDouble("sumnum"));
				 citizenMedDE.setSumNumber(rs.getDouble("sumnumber"));
			}
		 });
		return citizenMedDE;
	}
		
				
}
