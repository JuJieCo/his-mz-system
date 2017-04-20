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

<script type="text/javascript">
	
	
	function getT1(){
		var p1=$("input[name='citizenMedDE.zfyfper']").val();
		$("input[name='citizenMedDE.zfyftotal']").val(parseFloat(p1).toFixed(2));//t1
		//格式化
		$("input[name='citizenMedDE.zfyfper']").val(parseFloat(p1).toFixed(2));
		//置空t6
		$("input[name='citizenMedDE.grblzftotal']").val(parseFloat(0).toFixed(2));
	
	}
	function getT2(){
		var p2=$("input[name='citizenMedDE.overcwfper']").val();
		$("input[name='citizenMedDE.overcwftotal']").val(parseFloat(p2).toFixed(2));//t2
		//格式化
		p2=$("input[name='citizenMedDE.overcwfper']").val(parseFloat(p2).toFixed(2));
	
	} 
	function getT3(){
		var p3=$("input[name='citizenMedDE.otherfper']").val();
		$("input[name='citizenMedDE.otherftotal']").val(parseFloat(p3).toFixed(2));//t3
		//格式化
		$("input[name='citizenMedDE.otherfper']").val(parseFloat(p3).toFixed(2));
	
	}
	function getT4(){
		var p4=$("input[name='citizenMedDE.ybxezfper']").val();
		$("input[name='citizenMedDE.ybxezftotal']").val(parseFloat(p4).toFixed(2));//t4
		//格式化
		$("input[name='citizenMedDE.ybxezfper']").val(parseFloat(p4).toFixed(2));
	
	}
	function getP6(){
		var t1=$("input[name='citizenMedDE.zfyftotal']").val();	
		var t2=$("input[name='citizenMedDE.overcwftotal']").val();
		var t3=$("input[name='citizenMedDE.otherftotal']").val();
		var t4=$("input[name='citizenMedDE.ybxezftotal']").val();
		var t5=$("input[name='citizenMedDE.qfbztotal']").val();
		var t6=$("input[name='citizenMedDE.grblzftotal']").val();  
		
		var p1=$("input[name='citizenMedDE.zfyfper']").val();	
		var p2=$("input[name='citizenMedDE.overcwfper']").val();
		var p3=$("input[name='citizenMedDE.otherfper']").val();
		var p4=$("input[name='citizenMedDE.ybxezfper']").val();
		var p5=$("input[name='citizenMedDE.qfbzper']").val();

		
		var p6=0 ;
			p6 = t6*0.45;// ==p6
			
			$("input[name='citizenMedDE.grblzfper']").val(parseFloat(p6).toFixed(2)); //p6
			$("input[name='citizenMedDE.tczfper']").val(parseFloat(t6-p6).toFixed(2)); //k4
			$("input[name='citizenMedDE.tczfalltotal']").val(parseFloat(t6-p6).toFixed(2)); //k3
			
		
		var k1=0;
		
			k1=parseFloat(t1)+parseFloat(t2)+parseFloat(t3)+parseFloat(t4)+parseFloat(t5)+parseFloat(t6); 
			$("input[name='citizenMedDE.alltotal']").val(parseFloat(k1).toFixed(2)); //k1
		
			
		var k2=0;
			k2=parseFloat(p1)+parseFloat(p2)+parseFloat(p3)+parseFloat(p4)+parseFloat(p5)+parseFloat(p6);
			$("input[name='citizenMedDE.peralltotal']").val(parseFloat(k2).toFixed(2)); //k2
			
			
			//格式化
			$("input[name='citizenMedDE.grblzftotal']").val(parseFloat(t6).toFixed(2)); //t6
	
	}
	

</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

</head>

<body>



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
	  
	  <form action="${pageContext.request.contextPath}/medicare/universityMedAction!showMZtcTotal" name="form1" method="post">
<table width="100%"  bgcolor="#EFF6FE">
	<tr>
		<td class="STYLE4" > 
		&nbsp; 统计 
		<select name="year">
		<script>
		var check_year = '${year}';
		var now_year = new Date().getFullYear();
		for(i=0;i<now_year-2012+1;i++){
			var checked = "";
			if(check_year==(now_year-i)+""){
				checked = "selected=selected";
			}
			document.write('<option '+checked+' value='+(now_year-i)+'>'+(now_year-i)+'</option>');
		}
		</script>
		</select>年
		<select name="month">
		<script>
		var check_month = '${month}';
		var now_month = new Date().getMonth()+1;
		for(i=1;i<13;i++){
			var checked = "";
			if(now_month==i&&check_month==''){
				checked = "selected=selected";
			}
			if(check_month==(i<10?("0"+i):(i+""))){
				checked = "selected=true";
			}
			document.write('<option '+checked+' value='+(i<10?("0"+i):(i+""))+'>'+(i<10?("0"+i):(i+""))+'</option>');
		}
		</script>
		</select>月
		&nbsp; <input type="button"  value="开始统计计算" class="btn_mouseout" onclick="document.forms.form1.submit();">
		</td>
		<td align="right"></td>
	</tr> 
</table>
</form>
	  
	  
	  <s:token name="s_token"></s:token>
	  

	<table width="100%"  bgcolor="#EFF6FE" align="center">	
	 <tr align="center">
	  <td  class="STYLE2">
	  	<font size="5" >西安市城镇居民基本医疗保险大学生门诊统筹医疗费用基金支付表</font>
	  </td>
	 </tr>
		
		
	 <tr>
	  <td class="STYLE4"> 
	  <hr>
	 
	  </td>
	 </tr> 
	</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6"  align="center">
		
		  
		
           <tr align="center">
            <td  height="44" align="center"  bgcolor="#FFFFFF"  colspan="2" width="40%" ><span class="STYLE4"><font size="3" >项目</font></span></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" width="30%" ><span class="STYLE4"><font size="3" >门诊统筹人次</font></span></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" width="30%" ><span class="STYLE4"><font size="3" >门诊统筹支出金额</font></span></td>
           </tr>
           
           <tr>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  rowspan="2" ><span class="STYLE4">门诊统筹基金支付金额</span></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">大学生：</span></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  ><!-- <input name=""  type="text" size="15"  value="t1"  id=""/> --> ${universityMedTotal.personNum }</td>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  ><!-- <input name=""  type="text" size="15"  value="p1"  id=""/> --> ${universityMedTotal.mztcTotal }</td>
           </tr>
           
           <tr>
            <td  height="44" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">合计：</span></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  ><!-- <input name=""  type="text" size="15"  value="t2"  id="" />--> ${universityMedTotal.personNum }</td>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  ><!-- <input name=""  type="text" size="15"  value="p2"  id=""/>--> ${universityMedTotal.mztcTotal }</td>
           </tr>
           
           <tr>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  rowspan="2" ><span class="STYLE4">核定金额</span></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">小写：</span></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  ></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  ><!-- <input name=""  type="text" size="15"  value="p3"  id=""/>--> </td>
           </tr>
           
           <tr>
            <td  height="44" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">大写：</span></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  ></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF"  ><!-- <input name=""  type="text" size="15"  value="p4"  id=""/>--> </td>
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
               <!--  <input type="submit" value="提交"/>	
				<input type="button" value="返回" onclick="history.go(-1)"/>	  -->
				<s:if test="universityMedTotal!=null">
				<input type="button" class="btn_mouseout" value="打印" onclick="location.href='${pageContext.request.contextPath}/reports/toUntTotalpdf!showMZtcTotal?print=true&id=${one.university_id}&year=${year}&month=${month}'"/>	
				</s:if>
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

</body>
</html>