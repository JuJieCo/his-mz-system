package com.jujie.global.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jujie.user.User;

public class TTTT {

	/**
	 * @param args
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
//		String log = "用户{login.loginName},登录{code}";
//		Pattern pattern = Pattern.compile("\\{[^\\{\\}]*\\}");
//		Matcher matcher = pattern.matcher(log);
//		while(matcher.find()){
//			log = matcher.replaceFirst("1231233");	
//			matcher = pattern.matcher(log);
//			
//		}
//		System.out.println(log);
		User user = new User();
		user.setSysUserId(100);
		user.setSysUserCode("p83sldfj");
		Object obj =user;
		Field field = obj.getClass().getDeclaredField("sysUserId");
		System.out.println(field.getClass());
		System.out.println(field.getName());
		System.out.println(field.getGenericType());
		System.out.println(field.getType());
		
		Method method = obj.getClass().getMethod("getSysUserId",null);
		System.out.println(method.invoke(obj, null));
		method = obj.getClass().getMethod("getSysUserCode",null);
		System.out.println(method.invoke(obj, null));
	}

}
