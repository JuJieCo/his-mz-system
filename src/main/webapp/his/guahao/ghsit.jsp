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


<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/validator/validationEngine.jquery.css" type="text/css"/>
<script src="${pageContext.request.contextPath}/resource/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/validator/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/js/validator/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>


 <script language="JavaScript" type="text/javascript">  
	jQuery(document).ready(function(){
    	// binds form submission and fields to the validation engine
    	jQuery("#formID").validationEngine('attach');
    
	});
 </script>

 
 <script type="text/javascript">
 // function checkTime(field, rules, i, options){
  //   if (field.val()== "") {
         // this allows to use i18 for the error msgs
     //    return options.allrules.validate2fields.alertText;
    // }
// }
 </script>
 
 
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
<form action="${pageContext.request.contextPath}/guahao/ghsitAction!editGHSit" name="form1"  method="post"   id="formID">
 <s:token name="s_token"></s:token>
<table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" >
	  <br><br>
	   &nbsp;&nbsp; 执行日期：
	   <input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-M-d'})" class="Wdate ; validate[required] text-input"  id="ghsitDoTime" name="ghsit.ghsitDoTime"  type="text" value="<fmt:formatDate value="${ghsit.ghsitDoTime }" pattern="yyyy-M-d"/>"/>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="submit"  value="保存" class="btn_mouseout" >&nbsp;&nbsp;
	  <input type="reset"  value="重置" class="btn_mouseout">
	  <input type="hidden" name="ghsit.ghsitId" value="${ghsit.ghsitId }">
	  <input type="hidden" name="isUpdate" value="${isUpdate }">
	  </td>
	  <td class="STYLE4" > </td>
	  <td class="STYLE4" > </td>
	  <td align="right"> </td>
	  </tr> 
	  <tr >
	   <!--
	  <td class="STYLE4" >
	  <br>
	  <br>
	 <!-- 
	   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   号别：<select name="ghsit.ghsitType1" id="ghsitType1" >
	   				<option value="0"  ${ghsit.ghsitType1==0?'selected':''}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--普通号--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   				<option value="1"  ${ghsit.ghsitType1==1?'selected':''}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--专家号--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   			</select> 
	   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   号类：<select name="ghsit.ghsitType2"  id="ghsitType2" >
	   				<option value="0"  ${ghsit.ghsitType2==0?'selected':''}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--即日号--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   				<option value="1"  ${ghsit.ghsitType2==1?'selected':''}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--预约号--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   			</select>
	   
	  </td>
	  <td class="STYLE4" ></td>
	  <td class="STYLE4" ></td>
	  <td align="right"> </td>
	    -->
	  </tr>
	  <tr >
	  <td class="STYLE4" >
	  <br>
	  <br>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  科室：<select name="ghsit.dept.deptId"  id="deptid" class="validate[groupRequired[1]] text-input " >
	   				<option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--请选择--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   				<s:iterator value="deptList" var="list" >
	   				<option value="${list.deptId }" ${list.deptId==ghsit.dept.deptId?'selected':''}>${list.deptName }&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   				</s:iterator>
	   			</select>
	  &nbsp;&nbsp;
	   医生姓名：<select name="ghsit.doctor.doctorId" id="doctorid" class="validate[groupRequired[2]] text-input " >
	  					 <option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--请选择--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
	  					 <s:iterator value="ddList" var="list" >
	   				  	  <option value="${list.doctor.doctorId }" ${list.doctor.doctorId==ghsit.doctor.doctorId?'selected':''}>${list.doctor.doctorName }&nbsp;&nbsp;&nbsp;&nbsp;</option>
	   				  	  </s:iterator>
	   				  </select>  
	   	&nbsp;&nbsp;
	   上午限号：<input name="ghsit.ghsitAmNum"  id="ghsitAmNum"  type="text"  value="${ghsit.ghsitAmNum }" size="7"  class="validate[required,custom[integer],min[0]]" />
	   
	   &nbsp;&nbsp;
	   下午限号：<input name="ghsit.ghsitPmNum"  id="ghsitPmNum"  type="text" value="${ghsit.ghsitPmNum }" size="7" class="validate[required,custom[integer],min[0]]"/>
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
        <td>
	<form action="${pageContext.request.contextPath}/guahao/ghsitAction!showGHSit" name="form2"  method="post"  >
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">执行日期</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">科室</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">医生</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">号别</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">号类</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">上午限号</span></td> 
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">下午限号</span></td> 
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">操作</span></td>                     
          </tr>
		
	    <!-- 迭代   	-->
	    <s:iterator value="ghsitList" var="list" >
		 <tr>
		 	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitDoTime }</span></td>
           	<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.dept.deptName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.doctor.doctorName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitType1 eq 0?"普通号":"专家号" }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitType2 eq 0?"即日号":"预约号"}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitAmNum }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.ghsitPmNum } </span></td>   
          	<td height="20" bgcolor="#FFFFFF" align="center">
 				<span class="STYLE1"> 
 				<a href="${pageContext.request.contextPath}/guahao/ghsitAction!showEditGHSit?ghsit.ghsitId=${list.ghsitId }">修改</a>  
 				<a href="${pageContext.request.contextPath}/guahao/ghsitAction!deleteGHSit?ghsitId=${list.ghsitId }" onclick="return(confirm('确认要删除吗?'))" >删除</a></span>
          	</td>
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