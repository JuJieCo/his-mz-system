package com.jujie.his.medicare.utils;

import java.text.DecimalFormat;

import junit.framework.TestCase;

import com.jujie.his.medicare.CitizenMedDE205;

public class TestRewardJUnit extends TestCase {

	  
	public void testReward() {

		double debzTotal = 55800; //3本月定额总额 3=1*2
		double ybfy = 82503.69; //4 本月费用总额  sum来的
		
		double under90 = 0.00; //5 低于或等于90%的部分???
		
		double defy90to100Money = 0.00; //6 高于90%低于100%的部分 金额
		double defy90to100Reward = 0.00; //7 高于90%低于100%的部分 奖励
		
		double defy100to115Money = 0.00; //8 高于100%低于115%的部分 金额
		double defy100to115YY = 0.00; //9 高于100%低于115%的部分 医院承担20% 
		double defy100to115YB = 0.00; //10  高于100%低于115%的部分 医保承担80%
		
		double defy115to130Money = 0.00; //11   高于115%低于130%的部分 金额
		double defy115to130YY = 0.00; //12 高于115%低于130%的部分 医院承担40%
		double defy115to130YB = 0.00; //13 高于115%低于130%的部分 医保承担60%
		
		double exceed130 = 0.00; //14  高于130%的部分 医院承担100%
		
		double yjfy = 0.00;  //15 出院应结费用金额
		
		
		DecimalFormat df = new DecimalFormat("##.00");
		
		
		CitizenMedDE205 citizenMedDE205 = new CitizenMedDE205();
			
			/**
			 * 计算奖励部分
			 */
			
			
			// under90 5 低于或等于90%的部分???
			// defy90to100Money 6 高于90%低于100%的部分 金额
			// defy90to100Reward 7 高于90%低于100%的部分 奖励
			if(ybfy < debzTotal){ // 医保费用<定额总数
				if(ybfy <= debzTotal * 0.9){
					under90 = 0.00;
					
					citizenMedDE205.setUnder90(under90);
				}
				if (ybfy > (debzTotal * 0.9)) {// 医保费用>定额总数的0.9倍, 
					defy90to100Money = debzTotal - ybfy;
					defy90to100Reward = defy90to100Money * 0.7;
					
					citizenMedDE205.setDefy90to100Money(Double.parseDouble(df.format(defy90to100Money)));
					citizenMedDE205.setDefy90to100Reward(Double.parseDouble(df.format(defy90to100Reward)));
				}
			}
			
			// defy100to115Money 8  高于100%低于115%的部分 金额
			// defy100to115YY 9  高于100%低于115%的部分 医院承担20%
			// defy100to115YB 10  高于100%低于115%的部分 医保承担80%
			
			// defy115to130Money 11  高于115%低于130%的部分 金额
			// defy115to130YY 12  高于115%低于130%的部分 医院承担40%
			// defy115to130YB 13  高于115%低于130%的部分 医保承担60%
			
			// exceed130 14 高于130%的部分 医院承担100%
			
			if (ybfy > debzTotal) { // 医保费用>定额总数
				
				if(ybfy <= (debzTotal * 1.15)){ // 医保费用<定额总数的1.15倍
					defy100to115Money = ybfy - debzTotal;
					defy100to115YY = defy100to115Money * 0.2;
					defy100to115YB = defy100to115Money * 0.8;
					
					citizenMedDE205.setDefy100to115Money(Double.parseDouble(df.format(defy100to115Money)));
					citizenMedDE205.setDefy100to115YY(Double.parseDouble(df.format(defy100to115YY)));
					citizenMedDE205.setDefy100to115YB(Double.parseDouble(df.format(defy100to115YB)));
				}
				if(ybfy > (debzTotal * 1.15) && ybfy <= (debzTotal * 1.30)){ // 医保费用>定额总数的1.15倍 医保费用<=定额总数的1.30倍 
					defy100to115Money = debzTotal * 0.15;
					defy100to115YY = defy100to115Money * 0.2;
					defy100to115YB = defy100to115Money * 0.8;
					
					defy115to130Money =  (ybfy - debzTotal)-defy100to115Money;
					defy115to130YY = defy115to130Money * 0.4;
					defy115to130YB = defy115to130Money * 0.6;
					
					citizenMedDE205.setDefy100to115Money(Double.parseDouble(df.format(defy100to115Money)));
					citizenMedDE205.setDefy100to115YY(Double.parseDouble(df.format(defy100to115YY)));
					citizenMedDE205.setDefy100to115YB(Double.parseDouble(df.format(defy100to115YB)));
					
					citizenMedDE205.setDefy115to130Money(Double.parseDouble(df.format(defy115to130Money)));
					citizenMedDE205.setDefy115to130YY(Double.parseDouble(df.format(defy115to130YY)));
					citizenMedDE205.setDefy115to130YB(Double.parseDouble(df.format(defy115to130YB)));
				}
				if(ybfy > (debzTotal * 1.3)){ // 医保费用>定额总数的1.3倍
					
					defy100to115Money = debzTotal * 0.15;
					defy100to115YY = defy100to115Money * 0.2;
					defy100to115YB = defy100to115Money * 0.8;
					
					defy115to130Money =  debzTotal * 0.15;
					defy115to130YY = defy115to130Money * 0.4;
					defy115to130YB = defy115to130Money * 0.6;
					
					exceed130 = ybfy - debzTotal -  defy100to115Money -  defy115to130Money;
					
					citizenMedDE205.setDefy100to115Money(Double.parseDouble(df.format(defy100to115Money)));
					citizenMedDE205.setDefy100to115YY(Double.parseDouble(df.format(defy100to115YY)));
					citizenMedDE205.setDefy100to115YB(Double.parseDouble(df.format(defy100to115YB)));
					
					citizenMedDE205.setDefy115to130Money(Double.parseDouble(df.format(defy115to130Money)));
					citizenMedDE205.setDefy115to130YY(Double.parseDouble(df.format(defy115to130YY)));
					citizenMedDE205.setDefy115to130YB(Double.parseDouble(df.format(defy115to130YB)));
					
					citizenMedDE205.setExceed130(Double.parseDouble(df.format(exceed130)));
					
				}
				
			}
	
			// yjfy 15 出院应结费用金额 15 = 4 + 7 - 9 -12 -14
			yjfy = (ybfy + defy90to100Reward) - defy100to115YY - defy115to130YY - exceed130;
			citizenMedDE205.setYjfy(Double.parseDouble(df.format(yjfy)));

	}

}
