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
import com.jujie.his.baseinfo.Company;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;
import com.jujie.util.page.PageUtils;

@SuppressWarnings("unchecked")
public class CompanyDaoImpl  extends BaseJdbcDao{
	
	// 取his_mz_company表 返回药品供应公司List
	
	
			@SuppressWarnings("static-access")
			public List<Company> queryCompanyList(Page page)throws Exception{
				final String sql = "select * from his_mz_company order by company_id desc";
				List<Object> obj = new ArrayList<Object>();
				return this.getJdbcTemplate().query(PageUtils.fyPage(sql, obj.toArray(), page, this.getJdbcTemplate(), page.DATABASE_TYPE_MYSQL), obj.toArray(),new Company());
			}
			
			
			//新增药品供应公司
			public Integer addCompany(Company company) throws Exception {
				
				final String sql = "insert into his_mz_company (company_name,company_alias,company_statue) values(?,?,?)";
				
				final Object[] objs ={company.getCompanyName(),company.getCompanyAlias(),company.getCompanyStatue()};
				KeyHolder keyHolder = new GeneratedKeyHolder();
				
				try {
					this.getJdbcTemplate().update(new PreparedStatementCreator(){
						public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
							int i = 0;
							int n = 0;
							PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
							ps.setString(++i, DataUtils.getStringK(objs[n++]));
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
			
			public Company queryCompany(String companyId) throws Exception{

				final String sql ="select * from  his_mz_company where company_id = "+companyId;
				List<Company> list = this.getJdbcTemplate().query(sql, new Company());
				if(list!=null&&list.size()>0){
					return list.get(0);
				}
				return null;
			}
			
			
			//修改药品供应公司
			public void updateCompany(Company company ,String statue) throws Exception{

				if(null!=statue&&!"".equals(statue)){
					if(statue.equals("isStatue")){
						String sql = "update his_mz_company set company_statue=? where company_id=?";
						Object[] objs = {company.getCompanyStatue(),company.getCompanyId()};
						this.getJdbcTemplate().update(sql,objs);
					}
					if(statue.equals("noStatue")){
						String sql = "update his_mz_company set company_name=?,company_alias=? where company_id=?";
						Object[] objs = {company.getCompanyName(),company.getCompanyAlias(),company.getCompanyId()};
						this.getJdbcTemplate().update(sql,objs);
					}
					
				}
			}
			
			//删除药品供应公司
			public void deleteCompany(String[] companyId) throws Exception{
				String[] sql = new String[companyId.length];
				 sql[0] = "delete from his_mz_company where company_id="+companyId[0];
				this.getJdbcTemplate().batchUpdate(sql);
			}	

}
