package com.jujie.global.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValue implements JsonValueProcessor {
	
	 public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";   
	 private DateFormat dateFormat;   
	   
     
     public DateJsonValue(String datePattern) {   
         try {   
        	 if(datePattern==null || "".equals(datePattern)){
        		 dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);   
        	 }else{
        		 dateFormat = new SimpleDateFormat(datePattern);  
        	 }
         } catch (Exception ex) {   
             dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);   
         }   
     }   
	  
	
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		 return process(value);   
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {	
		 return process(value);   
	}
	
	private Object process(Object value) {   
		if(value==null){
			return "";
		}else if(value instanceof Date){
			return dateFormat.format((Date) value);   
		}else{
			return "";
		}
	}
}
