<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 12px;
	color: #2b7dbb;
}
a:link,a:visited     {
    color:#000000;
    text-decoration:none;
}    
a:hover,a:active    {
    color:#FF0000;
    text-decoration:underline;
}
-->
</style>
<script type="text/JavaScript">
<!--
	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
	    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}
	
	function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
	}
	
	function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}
	
	function MM_swapImage() { //v3.0
	  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
	}
//-->
</script>



<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>


<script type="text/javascript">
	function openWindow(type){
		var option = "width=500,height=300,location=no";
		if('pwd'==type){
			window.open("../login/edit_pwd.jsp","修改密码",option);
		}else{
			window.open("../user/user_simpinfo.jsp","用户信息",option);
		}
	}
	
</script>

</head>
<body onload="MM_preload${pageContext.request.contextPath}/resource/images('${pageContext.request.contextPath}/resource/images/main_07_1.gif','${pageContext.request.contextPath}/resource/images/main_08_1.gif','${pageContext.request.contextPath}/resource/images/main_10_1.gif','${pageContext.request.contextPath}/resource/images/main_11_1.gif','${pageContext.request.contextPath}/resource/images/main_12_1.gif','${pageContext.request.contextPath}/resource/images/main_13_1.gif','${pageContext.request.contextPath}/resource/images/main_14_1.gif')">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="57" background="${pageContext.request.contextPath}/resource/images/main_02.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="110" height="57" background="${pageContext.request.contextPath}/resource/images/main_01.gif">&nbsp;</td>
        <td><table width="743" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="28" background="${pageContext.request.contextPath}/resource/images/main_03.gif">&nbsp;</td>
          </tr>
          <tr>
            <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="61" height="29" background="${pageContext.request.contextPath}/resource/images/main_06.gif">&nbsp;</td>
                <td width="72"><a href="javascript:openWindow('pwd')"><img src="${pageContext.request.contextPath}/resource/images/main_07.gif" name="Image1" width="72" height="29" border="0" id="Image1" onmouseover="MM_swapImage('Image1','','${pageContext.request.contextPath}/resource/images/main_07_1.gif',1)" onmouseout="MM_swapImgRestore()" /></a></td>
                <td width="72"><a href="javascript:openWindow('uif')"><img src="${pageContext.request.contextPath}/resource/images/main_08.gif" name="Image2" width="72" height="29" border="0" id="Image2" onmouseover="MM_swapImage('Image2','','${pageContext.request.contextPath}/resource/images/main_08_1.gif',1)" onmouseout="MM_swapImgRestore()" /></a></td>
                <td width="292" background="${pageContext.request.contextPath}/resource/images/main_09.gif">&nbsp;</td>
                <td width="40"><a href="javascript:window.top.location.href='${pageContext.request.contextPath}/common/index.jsp'"><img src="${pageContext.request.contextPath}/resource/images/main_10.gif" name="Image3" width="40" height="29" border="0" id="Image3" onmouseover="MM_swapImage('Image3','','${pageContext.request.contextPath}/resource/images/main_10_1.gif',1)" onmouseout="MM_swapImgRestore()" /></a></td>
				<td width="39"><a href="javascript:location.href='${pageContext.request.contextPath}/login/loginAction!logout'"><img src="${pageContext.request.contextPath}/resource/images/main_14.gif" name="Image7" width="39" height="29" border="0" id="Image7" onmouseover="MM_swapImage('Image7','','${pageContext.request.contextPath}/resource/images/main_14_1.gif',1)" onmouseout="MM_swapImgRestore()" /></a></td>
                <td width="36"><img src="${pageContext.request.contextPath}/resource/images/main_15.gif" width="36" height="29" /></td>
				<td width="44"></td>
                <td width="43"></td>
                <td width="44"></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="102" background="${pageContext.request.contextPath}/resource/images/main_05.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="26" background="${pageContext.request.contextPath}/resource/images/main_18.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="180" height="26" background="${pageContext.request.contextPath}/resource/images/main_16.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <c:forEach items="${sessionLogin.treeRole.funList}" var="root">
            <td id="top_menu" width="${fn:length(root.funName)*12+40}" valign="bottom" style="padding-left:3px">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td height="18" style="background:url(${pageContext.request.contextPath}/resource/images/topbg.gif); border:solid 1px #a6d0e7;font-size:12px; color:#2b7dbb;">
	                	<div align="center">
	                		<img src="${pageContext.request.contextPath}/resource/images/003.gif" width="12" height="10" />
	                		<a href="left.jsp?fid=${root.uuid}" target="leftFrame">${root.funName}</a>
	                	</div>
	                </td>
	              </tr>
                </table>
            </td>
            </c:forEach>
            <td valign="bottom">&nbsp;</td>
          </tr>
        </table></td>
        <td width="130px"><img src="${pageContext.request.contextPath}/resource/images/user.gif" width="10" height="10" /> <span class="STYLE1 floatleft" style="padding-right:10px">欢迎您:${sessionUser.sysUserName}</span></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>