<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

.clicked{
	color: red;
}

.tab_bg{
	background: url(${pageContext.request.contextPath}/resource/images/tab_bg.gif);
	border: 1px solid #adb9c2;
	color: red;
}
-->
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/left.js"></script>

<script type="text/javascript">
	function lmenu_mouseOver(obj){
		$(obj).addClass("tab_bg");
	}
	function lmenu_mouseOut(obj){
		$(obj).removeClass("tab_bg");
	}
</script>

</head>
<body>
<table width="171" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
<td valign="top">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
<tr>
	<td style="width:3px; background:#0a5c8e;">&nbsp;</td>
	<td><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
	  <tr>
	    <td height="5" style="line-height:5px; background:#0a5c8e;">&nbsp;</td>
	    </tr>
	  <tr>
	    <td height="23" background="${pageContext.request.contextPath}/resource/images/main_29.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="12%">&nbsp;</td>
	        <td align="center"><font style="height:1;font-size:10pt;font-weight:bold;color:#bfdbeb;filter:glow(color=#1070a3,strength=1)" id="tmenu">医院门诊模块</font></td>
	        <td width="12%">&nbsp;</td>
	        </tr>
	      </table></td>
	    </tr>
	  <tr>
	    <td bgcolor="#e5f4fd">
	      <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	      <!--  left -->
	      <tr>
	        <td valign="top"><!--1开始-->
	        <c:forEach items="${sessionLogin.treeRole.funList}" var="root">
	        <c:if test="${root.uuid==param.fid}">
	        	<script>$("#tmenu").html('${root.funName}')</script>
	        	<c:forEach items="${root.funList}" var="one" varStatus="i">
	        		<table width="100%" height="23" border="0" cellspacing="0" cellpadding="0">
		            	<tr>
		              		<td></td>
		              		<td onClick="showDiv(${i.count})" style="cursor:pointer" align="center" background="${pageContext.request.contextPath}/resource/images/main_43.gif"><font style="height:1;font-size:10pt; color:#bfdbeb;">${one.funName}</font></td>
		              		<td></td>
		              	</tr>
		            </table>
		            
		            <div style="display:${i.count==1?'black':'none'};width:100%;height:auto;padding-top:5px;" id="c${i.count}">
		            <table width="82%" border="0" align="center" cellpadding="0" cellspacing="0">
		            <c:forEach items="${one.funList}" var="two">
		              <tr>
		                <td height="38"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		                  <tr>
		                    <td ></td>
		                    <td ><table width="100%" height="25" border="0" cellspacing="0" cellpadding="0">
		                      <tr>
		                        <td align="center" class="STYLE4" style="cursor:pointer" onClick="toPage(this,'${two.funSign}')" 
		                        onMouseOver="lmenu_mouseOver(this)" onmouseout="lmenu_mouseOut(this)"> ${two.funName} </td>
		                        <a id="${two.funSign}" href="..${two.funUrl}" target="mainFrame" style="display:none;"></a>
		                        </tr>
		                      </table></td>
		                    </tr>
		                  </table></td>
		                </tr>
		                </c:forEach>
		              <tr><td height="10">&nbsp;</td></tr>
		            </table>
		            </div>
	        	</c:forEach>
	        </c:if>
	        </c:forEach>
	        </td>
	      </tr>
	      <tr><td></td></tr>
	      </table>
	      </td>
	    </tr>
	  <tr>
	    <td height="23" background="${pageContext.request.contextPath}/resource/images/main_45.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="18%">&nbsp;</td>
	        <td width="64%"><div align="center"><font style="height:1;font-size:9pt; color:#bfdbeb;filter:glow(color=#1070a3,strength=1)">版本2012 V1.0 </font></div></td>
	        <td width="18%">&nbsp;</td>
	        </tr>
	      </table></td>
	    </tr>
	  <tr>
	    <td height="9" style="line-height:9px; background:#0a5c8e;">&nbsp;</td>
	    </tr>
	  </table></td>
	<td style="width:3px; background:#0a5c8e;">&nbsp;</td>
</tr>
</table>
	</td>
</tr>
</table>
</body>
</html>
