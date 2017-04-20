package com.jujie.his.medicare.utils;

import java.util.HashSet;
import java.util.Random;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		HashSet<String> hs = new HashSet<String>();
		Random r = new Random();
		for (;;) {
			int temp = r.nextInt(100);
			hs.add(temp + "");
			if (hs.size() == 10)
				break;
			System.out.println(temp);
		}
		

	}

}	
