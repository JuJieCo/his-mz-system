package com.jujie.global.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.jujie.global.PrintReport;

public class PrintReportDAOImpl extends BaseJdbcDao {
	
	
	public PrintReport queryGHPrintInfo(String  orderId) throws Exception{
		final String sql ="SELECT  s.sick_name AS sickName , g.ghinfo_dotime AS ghTime , (g.ghinfo_ghmoney +g.ghinfo_zlmoney) AS sshMoney " +
				" FROM his_mz_ghinfo g, his_mz_order o, his_mz_sick s " +
				" WHERE g.order_id = o.order_id AND o.sick_id = s.sick_id AND  o.order_id =  "+orderId;
		final PrintReport printReport = new PrintReport();
		this.getJdbcTemplate().query(sql,new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
			
				printReport.setSickName(rs.getString("sickName"));
				printReport.setGhTime(rs.getDate("ghTime"));
				printReport.setSshMoney(rs.getDouble("sshMoney"));
			}
		 });
		return printReport;
	}

}
