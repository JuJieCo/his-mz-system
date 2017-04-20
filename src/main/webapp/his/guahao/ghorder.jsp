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


<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/validator/validationEngine.jquery.css" type="text/css"/>
<script src="${pageContext.request.contextPath}/resource/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/validator/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/js/validator/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

</head>

 <script language="JavaScript" type="text/javascript"> 
 
 function isNumber(oNum) { 
		if(!oNum) return false; 
		var strP=/^\d+(\.\d+)?$/; 
		if(!strP.test(oNum)) return false; 
		try{ 
			if(parseFloat(oNum)!=oNum) return false; 
		} catch(ex) { 
		return false; 
		} 
		return true; 
		}
	function selectPage(currentPage){
		var form = document.forms.form2;
		var pz = $("#fsz").val();
		var opz = $("#odfsz").val();
	    if(parseInt(pz)>parseInt(opz)){
			currentPage = 1;
		}
	   
		if(jQuery.trim(pz)!=''&&isNumber(pz)&&pz.charAt(0)!='0'){
			form.action = form.action+"?page.currentPage="+currentPage;
			form.action = form.action+"&page.pageSize="+pz;
		}else{
		alert("请先确认输入的每页显示记录数的格式正确！");
		return;
		}
	   form.submit();
		}
 
 </script>
 
 <script language="JavaScript" type="text/javascript">  
	jQuery(document).ready(function(){
    	// binds form submission and fields to the validation engine
    	jQuery("#formID").validationEngine('attach');
    
	});
 </script>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif">
    	<%@include file="/common/table_top.jsp" %>
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td > 
 <form action="${pageContext.request.contextPath}/guahao/ghorderAction!saveGHOrder" name="form1" method="post" id="formID" >
 <s:token name="s_token"></s:token>
<table width="100%"  bgcolor="#EFF6FE">

	  <tr >
	  <td class="STYLE4" >&nbsp;&nbsp;
	 <br>
	  <br>
	  病历号：<input  name="sick.sickCasehistory"  type="text" value="" id="sickCasehistory" class="validate[required] text-input" />
		&nbsp;&nbsp;&nbsp;&nbsp;
	  医保号：<input  name="sick.sickYbCode"  type="text" value="" id="sickYbCode" class="validate[required] text-input" />
	 	&nbsp;&nbsp;&nbsp;&nbsp; 
	  身份证号：<input  name="sick.sickCode"  type="text" value="" id="sickCode" class="validate[required] text-input" />
	  </td>
	  <td class="STYLE4" ></td>
	  <td class="STYLE4" ></td>
	  <td align="right"> </td>
	  </tr>
	  <tr >
	  <td class="STYLE4" >
	  <br>
	  &nbsp;&nbsp;&nbsp; 姓名：<input  name="sick.sickName"  type="text" value="" id="sickName" class="validate[required] text-input" />
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   年龄：<input  name="sick.sickAge"  type="text" value="" size="4" id="sickAge"	class="validate[required,custom[integer],min[0]]" />
		&nbsp;&nbsp;&nbsp;&nbsp;
	   性别：<input  name="sick.sickSex"  type="radio" value="0" checked="checked"/>&nbsp;男&nbsp;&nbsp;<input  name="sick.sickSex"  type="radio" value="1"  />&nbsp;女
	   &nbsp;&nbsp;&nbsp;&nbsp;
	 <!--
	   费别：<select name="sick.sickCosetype">
	   		<option value="0">自费&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   		<option value="1">医保&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   </select>
	  &nbsp;&nbsp;&nbsp;&nbsp;
	   就诊：<select name="sick.sickTreattype">
	   		<option value="0">初诊&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   		<option value="1">复诊&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   </select>
	 -->
	  </td>
	  <td class="STYLE4" ></td>
	  <td class="STYLE4" ></td>
	  <td align="right"> </td>
	  </tr> 
	  <tr >
	  <td class="STYLE4" >
	  <br>
	   &nbsp;&nbsp;&nbsp;地址：<input  name="sick.sickAddress"  type="text" value="" size="100"/>
	  </td>
	  <td class="STYLE4" > </td>
	  <td class="STYLE4" > </td>
	  <td align="right"> </td>
	  </tr> 
	 <tr >
	  <td class="STYLE4"  >
	  <br>
	  &nbsp;&nbsp;当日号表：
	<table width="90%"  align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
         <tr> 
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1"></span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">科室</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">医生</span></td>
            <!-- 
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">号别</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">号类</span></td>
             -->
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">上午限号</span></td> 
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">下午限号</span></td> 
          </tr>
              <!-- 迭代   	-->
		<s:iterator value="ghsitList" var="list" >	    
		 <tr>
		 	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
		 	<c:if test="${ampm eq 0 && list.ghsitAmNum>0 }">
		 	<input type="radio" value="${list.ghsitId }" name="ghsitid" id="radio"  class="validate[required] radio">
		 	</c:if>
		 	<c:if test="${ampm eq 1 && list.ghsitPmNum>0 }">
		 	<input type="radio" value="${list.ghsitId }" name="ghsitid" id="radio"  class="validate[required] radio">
		 	</c:if>
		 	</span></td>
           	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.dept.deptName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.doctor.doctorName }</span></td>
            <!--
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitType1 eq 0?"普通号":"专家号" }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitType2 eq 0?"即日号":"预约号"}</span></td>
             -->
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitAmNum }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitPmNum } </span></td>   
          </tr>  
            </s:iterator> 
		 <!--迭代 end -->
        </table>
	  </td>
	 <td class="STYLE4" ></td>
	  <td class="STYLE4" ></td>
	  <td align="right"> </td>
	  </tr>
	  	  <tr >
	  <td class="STYLE4" >
	  
	  <br>
	  <br>
	  &nbsp;&nbsp;
	   挂号费：<input  name="ghinfo.ghinfoGHMoney"  type="text" value="" id="ghinfoGHMoney" class="validate[required,custom[decimal]],min[0] text-input"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
	   检查费：<input  name="ghinfo.ghinfoZLMoney"  type="text" value="" id="ghinfoZLMoney" class="validate[required,custom[decimal]],min[0] text-input"/>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <input type="submit"  value="保存" class="btn_mouseout"  >&nbsp;&nbsp;
	  <input type="reset"  value="重置" class="btn_mouseout">
	  </td>
	  <td class="STYLE4" ></td>
	  <td class="STYLE4" ></td>
	  <td align="right"> </td>
	  </tr> 
	</table>
	 </form>
	  </td>		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td class="STYLE4" ><hr /> &nbsp;&nbsp;&nbsp;&nbsp;
        当日病人挂号列表：
		<form action="${pageContext.request.contextPath}/guahao/ghorderAction!showGHOrder" name="form2"  method="post" >
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">日期</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">病历号</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">科室</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">医生</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">病人姓名</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">挂号费</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">检查费</span></td>
            <td  height="20" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">操作</span></td>
          </tr>
           <!-- 迭代   	-->
          <s:iterator value="ghordeList" var="list" >
		 <tr>
		 	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><fmt:formatDate value="${list.ghinfo.ghinfoDoTime }"  pattern="yyyy'年'M'月'd'日'"/></span></td>
           	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.sick.sickCasehistory }</span></td>
           	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsit.dept.deptName }</span></td>
           	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsit.doctor.doctorName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.sick.sickName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghinfo.ghinfoGHMoney } </span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghinfo.ghinfoZLMoney } </span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
            <c:if test="${list.orderStatue eq 0 }" ><a href="${pageContext.request.contextPath}/guahao/ghorderAction!deleteGHOrder?orderId=${list.orderId}&ghsitId=${list.ghsit.ghsitId}">退号</a></c:if>            
            <c:if test="${list.orderStatue eq 1 }">不可退号</c:if>
            <c:if test="${list.orderStatue eq 2 }">不可退号</c:if>
            <c:if test="${list.orderStatue eq 3 }">不可退号</c:if>
            <c:if test="${list.orderStatue eq 4 }">不可退号</c:if>
            <c:if test="${list.orderStatue eq 5 }">不可退号</c:if>
            <c:if test="${list.orderStatue eq 9 }">已退号</c:if>
            <c:if test="${list.orderStatue eq 0 }" ><a href="${pageContext.request.contextPath}/reports/toPrintpdf!toPrintpdf?orderId=${list.orderId}">打印</a></c:if>
            
            </span></td>
          </tr> 
          </s:iterator> 
		 <!--迭代 end -->
        </table>
       </form>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td><%@include file="/common/page.jsp" %></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>