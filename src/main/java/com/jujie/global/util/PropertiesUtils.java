package com.jujie.global.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {

	private static List<Map<String,String>> list  = new ArrayList<Map<String,String>>();

	public static List<Map<String, String>> getList() {
		return list;
	}

	public static void setList(List<Map<String, String>> list) {
		PropertiesUtils.list = list;
	}



	static { 
        Properties prop = new Properties(); 
        InputStream in = Object.class.getResourceAsStream("/action-log.properties"); 
        try { 
            prop.load(in); 
            for (Object obj : prop.keySet()) {
				if(obj!=null){
					Map<String, String> map = new HashMap<String,String>();
					map.put(obj.toString(), prop.getProperty(obj.toString()));
					list.add(map);
				}
			}
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 

    /** 
     * 私有构造方法，不需要创建对象 
     */ 
    private PropertiesUtils() { 

    } 


	
	public static void main(String[] args) {
		List<Map<String, String>> list = PropertiesUtils.getList();
		for (Map<String, String> map : list) {
			for (Object obj : map.keySet()) {
				System.out.println(obj.toString()+" = "+map.get(obj));
			}
		}
	}

}
