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

 <script language="JavaScript" type="text/javascript">  
	jQuery(document).ready(function(){
    	// binds form submission and fields to the validation engine
    	jQuery("#formID").validationEngine('attach');
    
	});
 </script>


<script type="text/javascript">

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
	var form = document.forms.form1;
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
	  
	  <form action=" ${pageContext.request.contextPath}/baseinfo/houseAction!editHouse" name="form2" method="post"  id="formID"  >
	  <s:token name="s_token"></s:token>
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	  
	  &nbsp;创建药房 <br> <br>
	  
	  &nbsp;名称：<input name="house.houseName"  type="text" size="30"  value="${house.houseName }"  id="houseName" class="validate[required] text-input" />
	  <input name="house.houseId"  type="hidden"  value="${house.houseId }" />
	  <input name="isUpdate"  type="hidden"  value="${isUpdate }" />
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="确定" class="btn_mouseout" >
	   &nbsp;&nbsp;&nbsp;<input type="reset"  value="重置" class="btn_mouseout">
	   
	   <hr>
	   <br>
	    &nbsp;药房列表
	    
	  </td>
	   </tr> 
		</table>
		</form></td>	
		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
         <form action=" ${pageContext.request.contextPath}/baseinfo/houseAction!showHouse" name="form1" method="post" >
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="25%"><span class="STYLE1">药房名称</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="10%"><span class="STYLE1">状态</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="20%"><span class="STYLE1">操作</span></td>
            
           </tr>
		  <!-- 迭代-->
	     <s:iterator value="houseList" var="list" >	
		 <tr>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.houseName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.houseStatue eq 0?"无效":"有效" }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
          <a href="${pageContext.request.contextPath}/baseinfo/houseAction!showUpdateHouse?houseId=${list.houseId}">修改</a>&nbsp;&nbsp;
          <a href="${pageContext.request.contextPath}/baseinfo/houseAction!deleteHouse?houseId=${list.houseId}" onclick="return(confirm('确认要删除吗?'))">删除</a>&nbsp;&nbsp;
          <a href="${pageContext.request.contextPath}/baseinfo/houseAction!updateHouseStatue?houseId=${list.houseId}">${list.houseStatue eq 1?"无效":"有效" }</a>
            </span></td>
            
          </tr> 
       </s:iterator>
		 <!--迭代 end  -->
        </table>
        </form>
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