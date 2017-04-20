package com.jujie.his.baseinfo.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jujie.his.baseinfo.Medicinal;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;

public class GetAllMedicinals {
	
	private static GetAllMedicinals gAMed;
	private List<Medicinal> list = new ArrayList<Medicinal>();
	private GetAllMedicinals(){
		
	}
	
	public static GetAllMedicinals getInstance(){
		if(gAMed==null){
			return new GetAllMedicinals();
		}
		return gAMed;
	}
	public List<Medicinal> getMedicinalList(ServletContext servletContext){
		
		WebApplicationContext  ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext); 
		BaseInfoServerImpl baseInfoServerImpl = (BaseInfoServerImpl)ctx.getBean("baseInfoServer");
		try {
			list = baseInfoServerImpl.queryAllMedicinals();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public  void refreshMedicinalList(ServletContext servletContext){
		@SuppressWarnings("unused")
		List<Medicinal> list = new ArrayList<Medicinal>();
		WebApplicationContext  ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext); 
		BaseInfoServerImpl baseInfoServerImpl = (BaseInfoServerImpl)ctx.getBean("baseInfoServer");
		try {
		 list=baseInfoServerImpl.queryAllMedicinals();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
