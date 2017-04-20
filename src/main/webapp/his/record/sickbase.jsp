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

function validateForm(formname){
	
	var deptName = document.getElementById("deptName").value;
	

	if(deptName==""){
		alert('请填写科室名称!');
		document.getElementById('deptName').focus();
		return false;
	}
	
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
	  
	  <form action=" ${pageContext.request.contextPath}/baseinfo/deptAction!editDept" name="deptform" method="post" onsubmit="return validateForm(this)" >
	  <s:token name="s_token"></s:token>
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	  
	 
	    &nbsp;病人基本信息列表
		<br>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    就诊时间：
	    
	    <input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy年M月d日'})" class="Wdate"  id="ghsitDoTime" name="ghsit.ghsitDoTime"  type="text" value="<fmt:formatDate value="${ghsit.ghsitDoTime }"  pattern="yyyy'年'M'月'd'日'"/>"/>
	    &nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;
	    <input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy年M月d日'})" class="Wdate"  id="ghsitDoTime" name="ghsit.ghsitDoTime"  type="text" value="<fmt:formatDate value="${ghsit.ghsitDoTime }"  pattern="yyyy'年'M'月'd'日'"/>"/>
	    &nbsp;&nbsp;姓名：<input name="" type="text" value=""  size="" id="">
	    <input type="submit"  value="查询" class="btn_mouseout" >
	  </td>
	   </tr> 
		</table>
		</form></td>	
		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="20%"><span class="STYLE1">病历号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="20%"><span class="STYLE1">姓名</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="10%"><span class="STYLE1">性别</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="10%"><span class="STYLE1">年龄</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="10%"><span class="STYLE1">费别</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="10%"><span class="STYLE1">就诊</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="25%"><span class="STYLE1">地址</span></td>
           </tr>
		  <!-- 迭代-->
	     <s:iterator value="deptList" var="list" >	
		 <tr>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.deptName }</span></td>
            
            
          </tr> 
       </s:iterator>
		 <!--迭代 end  -->
        </table>
        
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
        <td><%@include file="/common/page.jsp" %></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>