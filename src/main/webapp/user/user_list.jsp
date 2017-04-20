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
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

<script language=JavaScript>
	jQuery(function($) {
	  	var checkboxAll = $("input[name=checkboxAll][type=checkbox]");
	  	checkboxAll.unbind().click(function(){
	  		if($(this).attr("checked")==true){
	  			$("input[name=checkbox_uuid][type=checkbox]").attr("checked",true);
	  		}else{
	  			$("input[name=checkbox_uuid][type=checkbox]").attr("checked",false);
	  		}
	  	});
	  	var checkboxUUid = $("input[name=checkbox_uuid][type=checkbox]");
	  	checkboxUUid.unbind().click(function(){
	  		var checked_num = $("input[name=checkbox_uuid][type=checkbox]:checked").size();
	  		var checkbox_num = $("input[name=checkbox_uuid][type=checkbox]").size();
	  		if(checked_num==checkbox_num){
	  			checkboxAll.attr("checked",true);
	  		}else{
	  			checkboxAll.attr("checked",false);
	  		}
	  	});
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
    	<%@include file="../common/table_top.jsp" %>
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <!-- <form action="${pageContext.request.contextPath}/user/userAction!queryAllUser" name="form1" method="post">
	  <td width="58%"  class="STYLE4" >按网点机构查询：
	  	<select name="user.sysUserAgence.agenceId">
	  		<option value="">全部</option>
	  		<s:iterator value="agenceList" var="one">
	  			<option value="${one.agenceId}" ${one.agenceId==user.sysUserAgence.agenceId?'selected':''}>${one.agenceName}</option>
	  		</s:iterator>
   		</select>
	  &nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">&nbsp;</td>
	  </form> -->
	  <td width="24%">&nbsp;</td>
	  <td width="18%"  class="STYLE4">  
	  		<input type="button"  value="新增用户" class="btn_mouseout" onclick="location.href='${pageContext.request.contextPath}/user/userAction!showOneUser?&type=add'"/>	
	 </td>
	  </tr>  
		</table></td>
	
				
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <form action="${pageContext.request.contextPath}/user/userAction!deleteUser" name="form2" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>        
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">姓名</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">登陆名称</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">医生姓名</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">所在药房</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="20%"><span class="STYLE1">操作</span></td>
          </tr>
		  <!-- 迭代-->
		 <s:iterator value="userList" var="one">
		 <tr>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.sysUserName}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            	<span class="STYLE1">
            		<s:iterator value="loginList" var="lot">
            			<s:if test="#lot.user.sysUserId==#one.sysUserId">${lot.loginName}</s:if>
            		</s:iterator>
            	</span>
            </td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.doctor.doctorName}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.house.houseName}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            	<span class="STYLE1"><a href="${pageContext.request.contextPath}/user/userAction!showOneUser?user.sysUserId=${one.sysUserId}&type=show" >查看</a></span>
            	<span class="STYLE1"><a href="${pageContext.request.contextPath}/user/userAction!showOneUser?user.sysUserId=${one.sysUserId}&type=edit" >修改</a></span>
            	<c:if test="${sessionUser.sysUserId!=one.sysUserId}">
            	<span class="STYLE1"><a href="javascript:if(confirm('确定要删除吗？')){location.href='${pageContext.request.contextPath}/user/userAction!deleteUser?user.sysUserId=${one.sysUserId}'}" >删除</a></span>
           		<span class="STYLE1"><a href="${pageContext.request.contextPath}/user/userAction!toAuthorUser?user.sysUserId=${one.sysUserId}" >授权</a></span>
            	</c:if>
            </td>
          </tr> 
          </s:iterator>
		 <!--迭代 end  -->
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
        <td><%@include file="../common/page.jsp" %></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>