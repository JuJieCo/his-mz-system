package com.jujie.global.action;

import java.util.ArrayList;
import java.util.List;

import com.jujie.global.PrintReport;
import com.jujie.global.server.PrintReportServer;

public class PrintReportAction extends BaseActionSupper {
	

	private PrintReport printReport;
	List<PrintReport> printReportList;
	
	public PrintReport getPrintReport() {
		return printReport;
	}

	public void setPrintReport(PrintReport printReport) {
		this.printReport = printReport;
	}

	public List<PrintReport> getPrintReportList() {
		return printReportList;
	}

	public void setPrintReportList(List<PrintReport> printReportList) {
		this.printReportList = printReportList;
	}

	public String toPrintpdf(){
		String orderId = request.getParameter("orderId");
		PrintReportServer printReportServer = (PrintReportServer) this.getService("printReportServer");
		try {
			printReport = printReportServer.queryGHPrintInfo(orderId);
			printReportList= new ArrayList<PrintReport>();
			printReportList.add(printReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	


}
