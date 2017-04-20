 package com.jujie.global.server;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.jujie.global.PrintReport;
import com.jujie.global.dao.PrintReportDAOImpl;

public class PrintReportServer {
	

	private PrintReportDAOImpl printReportDAOImpl;

	public void setPrintReportDAOImpl(PrintReportDAOImpl printReportDAOImpl) {
		this.printReportDAOImpl = printReportDAOImpl;
	}
	
	

	public PrintReport queryGHPrintInfo(String  orderId) throws Exception{
		PrintReport printReport = new PrintReport();
		printReport =  printReportDAOImpl.queryGHPrintInfo(orderId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		DecimalFormat df = new DecimalFormat("##.00");
		
		
		printReport.setPrintTime(sdf.format(printReport.getGhTime()));
		printReport.setYshMoney(Double.parseDouble(df.format(printReport.getSshMoney())));
		printReport.setSshMoney(Double.parseDouble(df.format(printReport.getSshMoney())));
		printReport.setDept("挂号室");
		printReport.setItem("挂号费");
		printReport.setUserName("系统管理员");
		return printReport;
	}
	

}
