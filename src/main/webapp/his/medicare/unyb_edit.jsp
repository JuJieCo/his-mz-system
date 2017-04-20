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
<form action=" ${pageContext.request.contextPath}/medicare/universityMedAction!${opertype==1?'savePersonInfo':'editPersonInfo'}" name="formname" method="post">
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
	  	<font size="5" >西安市大学生门诊统筹结算单</font>
	  </td>
	 </tr>
		
		
	 <tr>
	  <td class="STYLE4"> 
	  <hr>
	 
	  </td>
	 </tr> 
	</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6"  align="center">
		  <input type="hidden" value="${universityMedDE.university_id}" name="universityMedDE.university_id" />
		  <input type="hidden" value="1234567890" name="universityMedDE.university_hcode" />
		  <tr>
		   <td  height="55" align="center"  bgcolor="#FFFFFF" width="10%"  colspan="2"><span class="STYLE4">定点医疗机构名称（盖章）</span></td>
		   <td  height="55" align="center"  bgcolor="#FFFFFF"  colspan="4"><input name="universityMedDE.university_hname"  type="text" size="85"  value="陕西职业技术学院" /></td>
		   <td  height="55" align="center"  bgcolor="#FFFFFF" width="10%" ><span class="STYLE4">就诊编号</span></td>
           <td  height="55" align="center"  bgcolor="#FFFFFF" width="10%" ><input name="universityMedDE.university_jcode"  type="text" size="10"  value="${universityMedDE.university_jcode}"/></td>
		  </tr>
           <tr align="center">
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%" ><span class="STYLE4">姓名</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="20%" ><input name="universityMedDE.university_pname"  type="text" size="25" value="${universityMedDE.university_pname}"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%" ><span class="STYLE4">性别</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%" >
             <input name="universityMedDE.university_psex" type="radio" size="7"  value="男" ${universityMedDE.university_psex=='男'?'checked=checked':''}${universityMedDE.university_psex=='1'?'checked=checked':''}/>&nbsp;<span class="STYLE4">男</span>
	  	     <input name="universityMedDE.university_psex" type="radio" size="7"  value="女" ${universityMedDE.university_psex=='女'?'checked=checked':''}${universityMedDE.university_psex=='0'?'checked=checked':''}/>&nbsp;<span class="STYLE4">女</span>
	  	    </td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%" ><span class="STYLE4">年龄</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%" ><input name="universityMedDE.university_page"  type="text" size="12"  value="${universityMedDE.university_page}"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%" ><span class="STYLE4">医保编号</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%" ><input name="universityMedDE.university_ycode"  type="text" size="10"  value="${universityMedDE.university_ycode}"/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">身份证号码</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="universityMedDE.university_pcode"  type="text" size="25"  value="${universityMedDE.university_pcode}"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">高校名称</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="5"><input name="universityMedDE.university_name"  type="text" size="114"  value="陕西职业技术学校"/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">人员类别</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="universityMedDE.university_ptype"  type="text" size="25"  value="医保类大学生"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">门诊次数</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="universityMedDE.university_mztimes"  type="text" size="10"  value="${universityMedDE.university_mztimes}"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">就诊时间</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-M-d'})" class="Wdate" size="12" name="universityMedDE.university_mzdate"  type="text" value="${universityMedDE.university_mzdate}"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">是否转诊</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="universityMedDE.university_istomz"  type="text" size="10"  value="${universityMedDE.university_istomz}"/></td>
           </tr>
           
            <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">疾病诊断</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="7">&nbsp;&nbsp;&nbsp;<input name="universityMedDE.university_ccontent"  type="text" size="185"  value="${universityMedDE.university_ccontent}"/></td>
            </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">本年度门诊统筹费用累计</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" colspan="2" ><input name="universityMedDE.university_yeartctotal"  type="text" size="44"  value="${universityMedDE.university_yeartctotal }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">本年度门诊统筹基金累计支出</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" colspan="2" ><input name="universityMedDE.university_yearjjtotal"  type="text" size="29"  value="${universityMedDE.university_yearjjtotal }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">就诊年度</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_mzyear"  type="text" size="10"  value="${universityMedDE.university_mzyear }"/></td>
            </tr>
            
            <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">本次门诊总费用</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">药费</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">检查费</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">化验费</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">治疗费</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">其他费用</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">全自费</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">门诊统筹费用</span></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_mztotalcost"  type="text" size="10"  value="${universityMedDE.university_mztotalcost }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_medcost"  type="text" size="10"  value="${universityMedDE.university_medcost }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_checkcost"  type="text" size="10"  value="${universityMedDE.university_checkcost }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_assaycost"  type="text" size="10"  value="${universityMedDE.university_assaycost }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_treatcost"  type="text" size="10"  value="${universityMedDE.university_treatcost }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_othercost"  type="text" size="10"  value="${universityMedDE.university_othercost }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_ppaytotal"  type="text" size="10"  value="${universityMedDE.university_ppaytotal }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="universityMedDE.university_mztc"  type="text" size="10"  value="${universityMedDE.university_mztc }"/></td>
           </tr>
           
           <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">备注</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="7">&nbsp;&nbsp;&nbsp;<input name="universityMedDE.university_remark"  type="text" size="185"  value="${universityMedDE.university_remark }"/></td>
            </tr>
            
            <tr>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">起付标准费</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="universityMedDE.university_paystand"  type="text" size="25"  value="${universityMedDE.university_paystand }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">报销比例</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="universityMedDE.university_applyper" type="text" value="0.5" /></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">本次个人应付金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="universityMedDE.university_ppay"  type="text" size="10"  value="${universityMedDE.university_ppay }"/></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">本次门诊统筹报销金额</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  ><input name="universityMedDE.university_mztcbx"  type="text" size="10"  value="${universityMedDE.university_mztcbx }"/></td>
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
                <s:if test="#show!='only'">
                <input type="submit" value="提交"/>
                </s:if>	
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
<s:if test="#show=='only'">
	<script>
		$("input").attr("readonly",true);
		$("input").css({"background-color":"#D6D6D8"});
	</script>
</s:if>
</body>
</html>