package com.jujie.his.baseinfo.dao;

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
import com.jujie.his.baseinfo.Medicinal;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;


@SuppressWarnings("unchecked")
public class MedicinalDaoImpl  extends BaseJdbcDao{

	// 取his_mz_medicinal表 返回药品List
	
	
				
				
				public List<Medicinal> queryAllMedicinals() throws Exception {
					final String sql = "SELECT m.medicinal_id,m.medicinal_name,m.medicinal_pycode,m.medicinal_statue,m.medicinal_type,"
						+"m.unit_id,u.unit_name,u.unit_statue,m.standard_id,s.standard_name,s.standard_statue  "
						+"FROM his_mz_medicinal m LEFT JOIN his_mz_unit u ON m.unit_id=u.unit_id LEFT JOIN his_mz_standard s ON m.standard_id=s.standard_id "
						+"WHERE m.medicinal_statue=1";
					List<Medicinal> medicinalList = new ArrayList<Medicinal>();
					medicinalList = this.getJdbcTemplate().query(sql,new Medicinal());
					return medicinalList;
				}
	


				@SuppressWarnings("static-access")
				public List<Medicinal> queryMedicinalList(Object[] objs, Page page)throws Exception{
					 String sql = "SELECT s.standard_name , u.unit_name  , m.* " +
							" FROM his_mz_medicinal m, his_mz_standard s , his_mz_unit u " +
							" WHERE m.standard_id = s.standard_id AND m.unit_id = u.unit_id";
					final List<Medicinal> medicinalList = new ArrayList<Medicinal>();
					List<Object> obj = new ArrayList<Object>();
					if(objs!=null&&objs.length>0){
						if(!"".equals(objs[0])&&objs[0]!=null&&!"".equals(DataUtils.getStringK(objs[0]))){
							sql += "  and m.medicinal_name like ?";
							// obj.add("%"+objs[0]+"%");
							 obj.add(objs[0]);
						}
					}
					sql += " order by m.medicinal_id desc ";
					
					System.out.println(sql);
					this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new RowCallbackHandler(){
						 public void processRow(ResultSet rs) throws SQLException{
							 Medicinal medicinal = new Medicinal();
							 medicinal.setMedicinalId(rs.getInt("medicinal_id"));
							 medicinal.setMedicinalName(rs.getString("medicinal_name"));
							 medicinal.setMedicinalPycode(rs.getString("medicinal_pycode"));
							 medicinal.setMedicinalStatue(rs.getInt("medicinal_statue"));
							 medicinal.setMedicinalType(rs.getInt("medicinal_type"));
							 medicinal.getUnit().setUnitName(rs.getString("unit_name"));
							 medicinal.getStandard().setStandardName(rs.getString("standard_name"));
							 medicinalList.add(medicinal);
						 }
					 }); 
					return medicinalList;
				}
				
				
				//新增药品
				public Integer addMedicinal(Medicinal medicinal) throws Exception {
					
					final String sql = "insert into his_mz_medicinal (unit_id,standard_id,medicinal_name,medicinal_pycode,medicinal_statue,medicinal_type) values(?,?,?,?,?,?)";
					
					final Object[] objs ={medicinal.getUnit().getUnitId(),medicinal.getStandard().getStandardId(),medicinal.getMedicinalName(),medicinal.getMedicinalPycode(),medicinal.getMedicinalStatue(),medicinal.getMedicinalType()};
					KeyHolder keyHolder = new GeneratedKeyHolder();
					
					try {
						this.getJdbcTemplate().update(new PreparedStatementCreator(){
							public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
								int i = 0;
								int n = 0;
								PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
								ps.setInt(++i,    DataUtils.getInt(objs[n++]));
								ps.setInt(++i,    DataUtils.getInt(objs[n++]));
								ps.setString(++i, DataUtils.getStringK(objs[n++]));
								ps.setString(++i, DataUtils.getStringK(objs[n++]));
								ps.setInt(++i,    DataUtils.getInt(objs[n++]));
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
				
				public Medicinal queryMedicinal(String medicinalId) throws Exception{

					final String sql ="select * from  his_mz_medicinal where medicinal_id = "+medicinalId;
					List<Medicinal> list = this.getJdbcTemplate().query(sql, new Medicinal());
					if(list!=null&&list.size()>0){
						return list.get(0);
					}
					return null;
				}
				
				//修改药品
				public void updateMedicinal(Medicinal medicinal ,String statue) throws Exception{

					if(null!=statue&&!"".equals(statue)){
						if(statue.equals("isStatue")){
							String sql = "update his_mz_medicinal set medicinal_statue=? where medicinal_id=?";
							Object[] objs = {medicinal.getMedicinalStatue(),medicinal.getMedicinalId()};
							this.getJdbcTemplate().update(sql,objs);
						}
						if(statue.equals("noStatue")){
							String sql = "update his_mz_medicinal set medicinal_name=?, medicinal_pycode=?,medicinal_type=?,unit_id=?,standard_id=? where medicinal_id=?";
							Object[] objs = {medicinal.getMedicinalName(),medicinal.getMedicinalPycode(),medicinal.getMedicinalType(),medicinal.getUnit().getUnitId(),medicinal.getStandard().getStandardId(),medicinal.getMedicinalId()};
							this.getJdbcTemplate().update(sql,objs);
						}
						
					}
				}
				
				//删除药品
				public void deleteMedicinal(String[] medicinalId) throws Exception{
					String[] sql = new String[medicinalId.length];
					 sql[0] = "delete from his_mz_medicinal where medicinal_id="+medicinalId[0];
					this.getJdbcTemplate().batchUpdate(sql);
				}	
}
