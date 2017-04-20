package com.jujie.global.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jujie.global.ChuFangReport;

public class ChuFangReportAction extends BaseActionSupper {
	
	//PDFList
	private List<ChuFangReport> chuFangReportPdfList;
	
	public List<ChuFangReport> getChuFangReportPdfList() {
		return chuFangReportPdfList;
	}
	public void setChuFangReportPdfList(List<ChuFangReport> chuFangReportPdfList) {
		this.chuFangReportPdfList = chuFangReportPdfList;
	}








	//预览PDF
	public String toPrintChuFangpdf(){
		
		//.........................................................................................
		

		
		ChuFangReport chufang1= new ChuFangReport();
		chufang1.setBingLiNo("binglihao1");
		chufang1.setDept("外科");
		chufang1.setJiuZhenDate(new Date());
		chufang1.setSickName("张三的");
		chufang1.setSickSex("男");
		chufang1.setSickAge("22");
		chufang1.setZhenDuan("有病啊有病");
		chufang1.setYaoPinDetail("1号药品");
		
		ChuFangReport chufang2= new ChuFangReport();
		chufang2.setBingLiNo("binglihao1");
		chufang2.setDept("外科");
		chufang2.setJiuZhenDate(new Date());
		chufang2.setSickName("张三的");
		chufang2.setSickSex("男");
		chufang2.setSickAge("22");
		chufang2.setZhenDuan("有病啊有病");
		chufang2.setYaoPinDetail("2号药品");
		
		//.........................................................................................
		
		chuFangReportPdfList= new ArrayList<ChuFangReport>();
		chuFangReportPdfList.add(0,chufang1);
		chuFangReportPdfList.add(1,chufang2);
		return SUCCESS;
	}

}
