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

<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>

<script type="text/javascript">
function valitForm(){
	var lname = $("input[name=login.loginName]").val();
	var pwd = $("input[name=login.loginPwd]").val();
	var uname = $("input[name=user.sysUserName]").val();
	
	if(jQuery.trim(lname)==""){
		alert("登陆名称不能为空！");
		document.getElementById('lname').focus();
		return false;
	}
	if(jQuery.trim(pwd)==""){
		alert("登陆密码不能为空！");
		document.getElementById('pwd').focus();
		return false;
	}
	if(jQuery.trim(uname)==""){
		alert("用户姓名不能为空！");
		document.getElementById('uname').focus();
		return false;
	}
	return true;
}

function doCheckName(){
	var lname = $("input[name=login.loginName]").val();
	lname = jQuery.trim(lname);
	if(lname==""){
		alert("请先输入登陆名称");
		return;
	}
	if('${type}'=='edit'){
		if(lname=='${login.loginName}'){
			alert("登陆名称未修改，不需检测！");
			$("#form_oper").attr("disabled",false);
			return;
		}
	}
	
	$.post("${pageContext.request.contextPath}/login/loginAction!checkName",{lname:lname},function(data){
		if(data==0){
			$("#form_oper").attr("disabled",false);
			alert("登陆名可以使用！");
		}else{
			$("#form_oper").attr("disabled",true);
			alert("登陆名已存在，请更换其他名称！");
		}
	});
}

function doCdn(){
	if('${type}'=='edit'){
		var lname = $("input[name=login.loginName]").val();
		lname = jQuery.trim(lname);
		if(lname==""){
			alert("请先输入登陆名称");
			return;
		}
		if(lname=='${login.loginName}'){
			return;
		}else{
			$("#form_oper").attr("disabled",true);
		}
	}else{
		$("#form_oper").attr("disabled",true);
	}
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
    <td>
	<form action="${pageContext.request.contextPath}/user/userAction!${type=='edit'?'editOneUser':'saveOneUser'}" name="" method="post" onsubmit="return valitForm();">
	<s:token name="s_token"></s:token>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <input type="hidden" name="user.sysUserId" value="${user.sysUserId}" />
        <input type="hidden" name="login.uuid" value="${login.uuid}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">
            	<font style="color:red">*</font>登陆名称：
            </td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="login.loginName" type="text" value="${login.loginName}" id="lname"  onfocus="doCdn()"/>
				&nbsp;&nbsp;<input  type="button"  value="检查登陆名是否可用" onclick="doCheckName()"/>
			</td>
		  <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">
            	<font style="color:red">*</font>登陆密码：
            </td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="login.loginPwd" type="password" value="${login.loginPwd}" id="pwd"/></td>
          </tr>
          <tr>  
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">
            	<font style="color:red">*</font>姓名：
            </td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<input  name="user.sysUserName" type="text" value="${user.sysUserName}" id="uname"/> </td>
          </tr>
          <tr>  
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">
            	<font style="color:red">*</font>医生：
            </td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
				<s:select theme="simple" list="doctorList" name="user.doctor.doctorId" listKey="doctorId" listValue="doctorName" headerKey="0" headerValue="无"></s:select>
			</td>
          </tr>
          <tr>  
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">
            	<font style="color:red">*</font>药房：
            </td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
				<s:radio  theme="simple" list="houseList" name="user.house.houseId" listKey="houseId" listValue="houseName"></s:radio>
			</td>
          </tr>
        </table>

		
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
              <table border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> <td >
				<input  type="submit"  value="提交" id="form_oper" ${type!='edit'?'disabled=disabled':''}/>
				<input  type="button"  value="返回" onclick="history.back()"/>
				</td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table>
    </form>
    </td>
  </tr>
</table>

<script type="text/javascript">
	if('show'=='${type}'){
		$("#form_oper").hide();
		$("input").attr("readonly",true);
		$("input").css({"background-color":"#e7e9ea"});
	}
</script>

</body>
</html>