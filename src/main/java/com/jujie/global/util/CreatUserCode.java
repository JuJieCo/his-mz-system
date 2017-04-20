package com.jujie.global.util;

import java.util.Random;

public class CreatUserCode {
	
	public static String creatCode(int prex){
		String px = "P";
		switch(prex){
			case 1 : px+="DR";break;
			case 2 : px+="OM";break;
			case 3 : px+="BS";break;
		}
		Random random = new Random();
		int v = 0;
		while(v <100000){
			v = random.nextInt(1000000);
		}
		return px+v;
	}
	
	public static void main(String[] args) {
		for(int i = 0 ; i < 20 ; i++){
			System.out.println(CreatUserCode.creatCode(1));
		}
	}
	
}
