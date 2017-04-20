package com.jujie.his.medicare.server;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jujie.his.medicare.CitizenMedDE;
import com.jujie.his.medicare.CitizenMedDE205;
import com.jujie.his.medicare.dao.CitizenMedDAOImpl;
import com.jujie.util.page.Page;

public class MedicareServerImpl {
	
	private CitizenMedDAOImpl citizenMedDAOImpl;

	public void setCitizenMedDAOImpl(CitizenMedDAOImpl citizenMedDAOImpl) {
		this.citizenMedDAOImpl = citizenMedDAOImpl;
	}
	
	public List<CitizenMedDE> queryCitizenMedDEList(Object[] objs ,Page page)throws Exception {
		return citizenMedDAOImpl.queryCitizenMedDEList(objs,page);
	}
	
	public Integer citizenMedDEEdit(CitizenMedDE citizenMedDE,String operateType)throws Exception {
		if("add".equals(operateType)&&null!=operateType){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String opertime = sdf.format(new Date());
			citizenMedDE.setOpertime(opertime);
			return citizenMedDAOImpl.addCitizenMedDE(citizenMedDE);
		}else if("update".equals(operateType)&&null!=operateType){
			return citizenMedDAOImpl.updateCitizenMedDE(citizenMedDE);
		}else{
			return  null;
		}
		
	}
	
	public CitizenMedDE queryCitizenMedDEByID(String  citizenId) throws Exception{
		return citizenMedDAOImpl.queryCitizenMedDEByID(citizenId);
	}
	
	
	
	public CitizenMedDE205 querycitizenMedDE205(String balancedate)throws Exception{
		CitizenMedDE205 citizenMedDE205 = new CitizenMedDE205();
		

		String balancetype="定额结算";//结算类型
		
		double debzPer = 5000; //1定额标准？？？默认值
		Integer sickNum = 0; //2本月住院人数count来的
		double debzTotal = 0.00; //3本月定额总额 3=1*2
		double ybfy = 0.00; //4 本月费用总额  sum来的
		
		double under90 = 0.00; //5 低于或等于90%的部分？？？默认值
		
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
		double zffy = 0.00;  // 16 个人按比例自付金额 sum来的
		double tzfy = 0.00; //17 门诊特诊特疗费用总额 ？？？默认值
		double yjfyTotal = 0.00; //18 应结费用总计 18 = 15-16+17
		double kcbz = 0.00;//20  扣除保证金 18*0.05
		double sjje;//21  实结金额  21 = 18-19-20
		
	
		
		DecimalFormat df = new DecimalFormat("##.00");
		
		
		if(null!=balancedate&&!"".equals(balancedate)){
			
			CitizenMedDE citizenMedDE = citizenMedDAOImpl.querySickNumAndTczfalltotal(balancedate);
			
			// 结算类型
			citizenMedDE205.setBalancetype(balancetype);
			
			//结算期
			citizenMedDE205.setBalancedate(balancedate);
			
			// debzPer 1 定额标准
			citizenMedDE205.setDebzPer(debzPer);
			
			// sickNum 2 本月住院人数
			sickNum = citizenMedDE.getCountNum();
			citizenMedDE205.setSickNum(sickNum);
			
			// debzTotal 3 本月定额总额 3=1*2
			debzTotal = debzPer * sickNum;
			citizenMedDE205.setDebzTotal(debzTotal);
			
			// ybfy 4 本月费用总额  sum来的
			ybfy = citizenMedDE.getSumNum();
			citizenMedDE205.setYbfy(ybfy);
			
			/**
			 * 计算奖励部分
			 */
			
			
			// under90 5 低于或等于90%的部分???
			// defy90to100Money 6 高于90%低于100%的部分 金额
			// defy90to100Reward 7 高于90%低于100%的部分 奖励
			if(ybfy < debzTotal){ // 医保费用<定额总数
				if(ybfy <= debzTotal * 0.9){
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
			citizenMedDE205.setYjfy(yjfy);
			
			// zffy 16 个人按比例自付金额 sum来的
			zffy = citizenMedDE.getSumNumber();
			citizenMedDE205.setZffy(Double.parseDouble(df.format(zffy)));
			
			// tzfy 17 门诊特诊特疗费用总额 
			citizenMedDE205.setTzfy(Double.parseDouble(df.format(tzfy)));
			
			// yjfyTotal 18  应结费用总计 18 = 15-16+17
			yjfyTotal = yjfy - zffy + tzfy;
			citizenMedDE205.setYjfyTotal(Double.parseDouble(df.format(yjfyTotal)));
			
			// kcbz// 20  扣除保证金 18*0.05
			kcbz = yjfyTotal * 0.05;
			citizenMedDE205.setKcbz(Double.parseDouble(df.format(kcbz)));
			
			//sjje 21  实结金额  21 = 18-19-20
			sjje = yjfyTotal - kcbz;
			citizenMedDE205.setSjje(Double.parseDouble(df.format(sjje)));
			
			//打印时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String printDate = sdf.format(new Date());
			citizenMedDE205.setOpertime(printDate.toString());
			
		}
		
		return citizenMedDE205;
	}

}
