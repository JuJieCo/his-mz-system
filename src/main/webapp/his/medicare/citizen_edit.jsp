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

<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/validator/validationEngine.jquery.css" type="text/css"/>
<script src="${pageContext.request.contextPath}/resource/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/validator/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/js/validator/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

 <script language="JavaScript" type="text/javascript">  
	jQuery(document).ready(function(){
    	// binds form submission and fields to the validation engine
    	jQuery("#formID").validationEngine('attach');
    
	});
 </script>

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
		//置空t6
		$("input[name='citizenMedDE.grblzftotal']").val(parseFloat(0).toFixed(2));
	} 
	function getT3(){
		var p3=$("input[name='citizenMedDE.otherfper']").val();
		$("input[name='citizenMedDE.otherftotal']").val(parseFloat(p3).toFixed(2));//t3
		//格式化
		$("input[name='citizenMedDE.otherfper']").val(parseFloat(p3).toFixed(2));
		//置空t6
		$("input[name='citizenMedDE.grblzftotal']").val(parseFloat(0).toFixed(2));
	}
	function getT4(){
		var p4=$("input[name='citizenMedDE.ybxezfper']").val();
		$("input[name='citizenMedDE.ybxezftotal']").val(parseFloat(p4).toFixed(2));//t4
		//格式化
		$("input[name='citizenMedDE.ybxezfper']").val(parseFloat(p4).toFixed(2));
		//置空t6
		$("input[name='citizenMedDE.grblzftotal']").val(parseFloat(0).toFixed(2));
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



</head>

<body>
<form  action=" ${pageContext.request.contextPath}/medicare/citizenMedAction!citizenMedDEEdit?operateType=${operateType }" name="form1" method="post" id="formID">
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
	  	<font size="5" >西安市居民医保患者住院费用定额结算信息登记</font>
	  	<input name="citizenMedDE.citizenId" type="hidden" value="${citizenMedDE.citizenId }">
	  </td>
	 </tr>

	 <tr>
	  <td class="STYLE4"> 
	  <hr>
	  &nbsp;
	   	姓名：<input name="citizenMedDE.name"  type="text"  value="${citizenMedDE.name }" id="name" class="validate[required] text-input" />
	  &nbsp;
	  	性别：<input name="citizenMedDE.sex"  type="radio" value="男" checked="checked"  id=""/>&nbsp;男
	  	     <input name="citizenMedDE.sex"  type="radio" value="女"   id=""/>&nbsp;女
	  &nbsp;
	  	年龄：<input name="citizenMedDE.age"  type="text" size="5"  value="${citizenMedDE.age }"  id="age" class="validate[required,custom[integer],min[0]]" />
	  &nbsp;
	  	身份证号：<input name="citizenMedDE.sfcode"  type="text" value="${citizenMedDE.sfcode }"  id="sfcode" class="validate[required] text-input" />
	  &nbsp;
	  	医保编号：<input name="citizenMedDE.ybcode"  type="text" value="${citizenMedDE.ybcode }"  id="ybcode" class="validate[required] text-input" />
	  </td>
	 </tr> 
	 <tr>
	 <td class="STYLE4"> 
	  &nbsp;
	  	社区：<input name="citizenMedDE.addr"  type="text" size="60" value="${citizenMedDE.addr }"  id="addr" class="validate[required] text-input" />
	 </td>
	 </tr>
	 <tr>
	  <td class="STYLE4"> 
	  &nbsp;
	  	住院号：<input name="citizenMedDE.zycode"  type="text" value="${citizenMedDE.zycode }"  id="zycode" class="validate[required] text-input" />
	  &nbsp;
	  	科室：<input name="citizenMedDE.dept"  type="text" value="${citizenMedDE.dept }"  id="dept" class="validate[required] text-input" />
	  &nbsp;
	  	入院日期：<input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-M-d'})" class="Wdate ; validate[required] text-input" id="indate" size="14" name="citizenMedDE.indate"  type="text" value="${citizenMedDE.indate }"/>
	  &nbsp;
	  	出院日期：<input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-M-d'})" class="Wdate ; validate[required] text-input" id="outdate" size="14" name="citizenMedDE.outdate"  type="text" value="${citizenMedDE.outdate }"/>
	  &nbsp;
	  	天数：<input name="citizenMedDE.dates"  type="text" size="5"  value="${citizenMedDE.dates }"  id="dates"  class="validate[required,custom[integer],min[0]]" />
	  </td>
	 </tr> 
	 <tr>
	 	<td class="STYLE4">
	 	  &nbsp;
	  	出院诊断：<input name="citizenMedDE.clies"  type="text" size="55"  value="${citizenMedDE.clies }"  id="clies" class="validate[required] text-input" />
	  	  &nbsp;
	  	治疗结果：<input name="citizenMedDE.result"  type="text" size="55"  value="${citizenMedDE.result }"  id="result" class="validate[required] text-input" />	  
	 	</td>
	 </tr>
	 <tr>
	  <td class="STYLE4">
	   &nbsp;
	   	既往统筹累计：<input name="citizenMedDE.oldtcjjlj"  type="text" value="${citizenMedDE.oldtcjjlj }"  id="oldtcjjlj"  class="validate[required,custom[decimal]],min[0] text-input"/>
	   &nbsp;
	   	本次统筹累计：<input name="citizenMedDE.nowtcjjlj"  type="text" value="${citizenMedDE.nowtcjjlj }"  id="nowtcjjlj"  class="validate[required,custom[decimal]],min[0] text-input"/>	
	   &nbsp;
	   	住院费用总额 ：<input name="citizenMedDE.zyfytotal"  type="text" value="${citizenMedDE.zyfytotal }"  id="zyfytotal"  class="validate[required,custom[decimal]],min[0.01] text-input"/> 
	  </td>
	 </tr> 
	 <tr>
	 	<td class="STYLE4">
	   &nbsp;
	   	审核号：<input name="citizenMedDE.checkcode"  type="text" value="${citizenMedDE.checkcode }"  id="checkcode"  class="validate[required] text-input" /> 
	   &nbsp;
	   	人员类别：<input name="citizenMedDE.persontype" type="text" readonly="readonly" value="大学生">
	   &nbsp;
	   	结算类别：<input name="citizenMedDE.balancetype"  type="text" readonly="readonly" value="定额结算"  id=""/>
	   &nbsp;
	         结算期：${year }年<select name="citizenMedDE.balancedate">
	         		<option value="${year }年1月" ${citizenMedDE.balancedate eq '2012年1月'?'selected':'' }>1月</option>
	         		<option value="${year }年2月" ${citizenMedDE.balancedate eq '2012年2月'?'selected':'' }>2月</option>
	         		<option value="${year }年3月" ${citizenMedDE.balancedate eq '2012年3月'?'selected':'' }>3月</option>
	         		<option value="${year }年4月" ${citizenMedDE.balancedate eq '2012年4月'?'selected':'' }>4月</option>
	         		<option value="${year }年5月" ${citizenMedDE.balancedate eq '2012年5月'?"selected":"" }>5月</option>
	         		<option value="${year }年6月" ${citizenMedDE.balancedate eq '2012年6月'?'selected':'' }>6月</option>
	         		<option value="${year }年7月" ${citizenMedDE.balancedate eq '2012年7月'?'selected':'' }>7月</option>
	         		<option value="${year }年8月" ${citizenMedDE.balancedate eq '2012年8月'?'selected':'' }>8月</option>
	         		<option value="${year }年9月" ${citizenMedDE.balancedate eq '2012年9月'?'selected':'' }>9月</option>
	         		<option value="${year }年10月" ${citizenMedDE.balancedate eq '2012年10月'?'selected':'' }>10月</option>
	         		<option value="${year }年11月" ${citizenMedDE.balancedate eq '2012年11月'?'selected':'' }>11月</option>
	         		<option value="${year }年12月" ${citizenMedDE.balancedate eq '2012年12月'?'selected':'' }>12月</option>
	           </select>
	   <hr>
	 	</td>
	 </tr>
	</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" >
		
          <tr align="center">
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="25%" rowspan="2" colspan="2"><span class="STYLE3">项目</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="20%" rowspan="2"><span class="STYLE3">小计</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="30%" colspan="2"><span class="STYLE3">个人支付</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  width="15%" rowspan="2" ><span class="STYLE3">统筹基金支付（元）</span><span class="STYLE3"></span><span class="STYLE3"></span><span class="STYLE3"></span><span class="STYLE3"></span></td>
           </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="10%"><span class="STYLE3">比例 </span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="15%"><span class="STYLE3">金额（元）</span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  rowspan="4"><span class="STYLE4">自费费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">自费药品费</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.zfyftotal"  type="text" size="10"  value="${citizenMedDE.zfyftotal }" readonly="readonly" id="t1" /></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.zfyfper"  type="text" size="10"  value="${citizenMedDE.zfyfper }"  id="p1" onchange="getT1();" class="validate[required,custom[decimal]],min[0] text-input"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">超床位费</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.overcwftotal"  type="text" size="10"  value="${citizenMedDE.overcwftotal }" readonly="readonly" id="t2"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.overcwfper"  type="text" size="10"  value="${citizenMedDE.overcwfper }"  id="p2" onchange="getT2();" class="validate[required,custom[decimal]],min[0] text-input"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">其他费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.otherftotal"  type="text" size="10"  value="${citizenMedDE.otherftotal }" readonly="readonly" id="t3"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.otherfper"  type="text" size="10"  value="${citizenMedDE.otherfper }"  id="p3" onchange="getT3();" class="validate[required,custom[decimal]],min[0] text-input"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">医保限额以上自费</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.ybxezftotal"  type="text" size="10"  value="${citizenMedDE.ybxezftotal }" readonly="readonly" id="t4"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.ybxezfper"  type="text" size="10"  value="${citizenMedDE.ybxezfper }"  id="p4" onchange="getT4();" class="validate[required,custom[decimal]],min[0] text-input"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" rowspan="2"><span class="STYLE4">医保费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">起付标准（自付）</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.qfbztotal"  type="text" size="10"  value="500.00"  readonly="readonly" id="t5"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.qfbzper"  type="text" size="10"  value="500.00"  readonly="readonly" id="p5"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
            <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">个人按比例自付</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.grblzftotal"  type="text" size="10"  value="${citizenMedDE.grblzftotal }"  id="t6" onchange="getP6();" class="validate[required,custom[decimal]],min[0.01] text-input"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" >
				<select name="citizenMedDE.zfbiliper">
	  			<option value="0.45">45%</option>
	 			</select>
       		</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.grblzfper"  type="text" size="10"  value="${citizenMedDE.grblzfper }" readonly="readonly" id="p6"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><input name="citizenMedDE.tczfper"  type="text" size="15"  value="${citizenMedDE.tczfper }"  id="k4" class="validate[required,custom[decimal]],min[0] text-input"/><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  colspan="2"><span class="STYLE4">合计</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.alltotal"  type="text" size="10"  value="${citizenMedDE.alltotal }"  id="k1" class="validate[required,custom[decimal]],min[0] text-input"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE.peralltotal"  type="text" size="10"  value="${citizenMedDE.peralltotal }"  id="k2" class="validate[required,custom[decimal]],min[0] text-input"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" colspan="3" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.tczfalltotal"  type="text" size="15"  value="${citizenMedDE.tczfalltotal }"  id="k3" class="validate[required,custom[decimal]],min[0] text-input"/><span class="STYLE4"></span></td>
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
                <input type="submit" value="提交"/>	
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