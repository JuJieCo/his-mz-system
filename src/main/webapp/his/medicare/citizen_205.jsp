<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>



<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

</head>

<body>
<form action="${pageContext.request.contextPath}/medicare/citizenMedAction!queryCitizenMedDE205" name="formname" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif">
    	<%@include file="/common/table_top.jsp" %>
    </td>
  </tr>
  
<tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  
	  
	  <s:token name="s_token"></s:token>
	  

	<table width="100%"  bgcolor="#EFF6FE" align="center">	
	 <tr align="center">
	  <td  class="STYLE2">
	  	<font size="5" >西安市城镇居民基本医疗保险费用</font>
	  	<br>
	  	<font size="5" >统筹基金结算申请表</font>
	  </td>
	 </tr>
		
		
	 <tr>
	  <td class="STYLE4"> 
	  结算期：<select>
	  			<option>${year }年</option>
	  		</select>
	  		<select name="balancedate">
	  		    <option value="${year }年1月" ${month eq 1?'selected':'' }>1月</option>
	  		    <option value="${year }年2月" ${month eq 2?'selected':'' }>2月</option>
	         	<option value="${year }年3月" ${month eq 3?'selected':'' }>3月</option>
	         	<option value="${year }年4月" ${month eq 4?'selected':'' }>4月</option>
	         	<option value="${year }年5月" ${month eq 5?"selected":"" }>5月</option>
	         	<option value="${year }年6月" ${month eq 6?'selected':'' }>6月</option>
	         	<option value="${year }年7月" ${month eq 7?'selected':'' }>7月</option>
	         	<option value="${year }年8月" ${month eq 8?'selected':'' }>8月</option>
	         	<option value="${year }年9月" ${month eq 9?'selected':'' }>9月</option>
	         	<option value="${year }年10月" ${month eq 10?'selected':'' }>10月</option>
	         	<option value="${year }年11月" ${month eq 11?'selected':'' }>11月</option>
	         	<option value="${year }年12月" ${month eq 12?'selected':'' }>12月</option>				
	  		</select>
	  		<input type="submit" name="button1" value="查询" >
	 
	  </td>
	 </tr> 
	</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6"  align="center">
		
		  
		
           <tr align="center">
            <td  height="44" align="center"  bgcolor="#EFF6FE"  colspan="3" width="48%" ><span class="STYLE4"><font size="3" >项目</font></span></td>
            <td  height="44" align="center"  bgcolor="#EFF6FE" width="6%" ><span class="STYLE4"><font size="3" >编号</font></span></td>
            <td  height="44" align="center"  bgcolor="#EFF6FE" width="48%" colspan="2"><span class="STYLE4"><font size="3" >医院申报数</font></span></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">定额标准</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">1</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.debzPer"  type="text" size="15"  value="${citizenMedDE205.debzPer }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">本月出院人数</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">2</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.sickNum"  type="text" size="15"  value="${citizenMedDE205.sickNum }"  id=""/></td>
           </tr>
           
            <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">本月出院平均费用定额标准总额（3 = 1 × 2）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">3</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.debzTotal"  type="text" size="15"  value="${citizenMedDE205.debzTotal }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">本月出院费用总额（医保费用）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">4</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.ybfy"  type="text" size="15"  value="${citizenMedDE205.ybfy }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  rowspan="10"  width="2%"><span class="STYLE4"><font size="3" >出院费用总额与出院平均费用定额标准总额比较</font></span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><span class="STYLE4">低于或等于90%部分的金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">5</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.under90"  type="text" size="15"  value="${citizenMedDE205.under90 }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  rowspan="2"><span class="STYLE4">高于90%至低于100%的部分</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">6</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.defy90to100Money"  type="text" size="15"  value="${citizenMedDE205.defy90to100Money }"  id=""/></td>
           </tr>
           
          <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">医院奖励（70%）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">7</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.defy90to100Reward"  type="text" size="15"  value="${citizenMedDE205.defy90to100Reward }"  id=""/></td>
           </tr>
           
            <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  rowspan="3"><span class="STYLE4">高于100%至低于115%的部分</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">8</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.defy100to115Money"  type="text" size="15"  value="${citizenMedDE205.defy100to115Money }"  id=""/></td>
           </tr>
           
          <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">医院负担比例（20%）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">9</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.defy100to115YY"  type="text" size="15"  value="${citizenMedDE205.defy100to115YY }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">统筹基金负担比例（80%）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">10</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.defy100to115YB"  type="text" size="15"  value="${citizenMedDE205.defy100to115YB }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  rowspan="3"><span class="STYLE4">高于115%至低于130%的部分</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">11</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.defy115to130Money"  type="text" size="15"  value="${citizenMedDE205.defy115to130Money }"  id=""/></td>
           </tr>
           
          <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">医院负担比例（40%）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">12</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.defy115to130YY"  type="text" size="15"  value="${citizenMedDE205.defy115to130YY }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">统筹基金负担比例（60%）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">13</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.defy115to130YB"  type="text" size="15"  value="${citizenMedDE205.defy115to130YB }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">高于130%的部分</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">医院负担比例（100%）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">14</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.exceed130"  type="text" size="15"  value="${citizenMedDE205.exceed130 }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">出院应结费用金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">15</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.yjfy"  type="text" size="15"  value="${citizenMedDE205.yjfy }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">扣除个人按比例自付金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">16</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.zffy"  type="text" size="15"  value="${citizenMedDE205.zffy }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">门诊特殊病种挂账的费用总额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">17</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.tzfy"  type="text" size="15"  value="${citizenMedDE205.tzfy }"  id=""/></td>
           </tr>
            
            <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">应结费用总计（18 = 15 - 16 + 17）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">18</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.yjfyTotal"  type="text" size="15"  value="${citizenMedDE205.yjfyTotal }"  id=""/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">审核扣除金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">19</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"></td>
           </tr>
           
            <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3" ><span class="STYLE4">扣除保证金（5%）</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">20</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE205.kcbz"  type="text" size="15"  value="${citizenMedDE205.kcbz }"  id=""/></td>
           </tr>
            
            <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3"  rowspan="2"><span class="STYLE4"><font size="3" >实结金额（盖财务章） <br> （21 = 18 - 19 -20）</font></span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  rowspan="2"><span class="STYLE4">21</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">小写</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="citizenMedDE205.sjje"  type="text" size="36"  value="${citizenMedDE205.sjje }"  id=""/></td>
           </tr>
           
           <tr>
           	<td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">大写</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ></td>
           </tr>
            
		 </table>
		</td>	
		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
         
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  
  <tr>
    <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
              <table border="0" align="center" cellpadding="0" cellspacing="0" id="form_oper">
                <tr>
                <td>
                <input type="button" value="生成打印" onclick="location.href='${pageContext.request.contextPath}/reports/toCitizenMedDE205pdf!printCitizenMedDE205?year=${year }&month=${month }';"/>	
				<input type="button" value="返回" onclick="history.go(-1)"/>	
				</td>
                </tr>
            </table>
            </td>
          </tr>
        </table>
        </td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html> 