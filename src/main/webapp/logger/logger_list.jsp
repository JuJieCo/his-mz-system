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
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>





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
	  <form action="${pageContext.request.contextPath}/log/loggerAction" name="form1" method="post" style="margin:0px;padding:0px">
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr>
	  <td class="STYLE4" >
	  	&nbsp;操作人：<select name="logger.logOper">
	  		<option value="">全部</option>
            <option value="1" ${logger.logOper==1?'selected':''}>系统管理员</option>
   		</select>
   		&nbsp;操作类型：<select name="logger.logType">
	  		<option value="">全部</option>
            <option value="1" ${logger.logType==1?'selected':''}>登陆</option>
            <option value="2" ${logger.logType==2?'selected':''}>登出</option>
            <option value="3" ${logger.logType==3?'selected':''}>新增</option>
            <option value="4" ${logger.logType==4?'selected':''}>修改</option>
            <option value="5" ${logger.logType==5?'selected':''}>删除</option>
            <option value="6" ${logger.logType==6?'selected':''}>查询</option>
            <option value="7" ${logger.logType==7?'selected':''}>改状态</option>
            <option value="8" ${logger.logType==8?'selected':''}>授权</option>
   		</select>
   		&nbsp;操作模块：<select name="logger.logCpModule">
	  		<option value="">全部</option>
            <option value="login" ${logger.logCpModule=='login'?'selected':''}>登陆模块</option>
   		</select>
	    &nbsp;开始时间：<input name="st" type="text" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:95px" value="${st}"/>
	    &nbsp;完成时间：<input name="et" type="text" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:95px" value="${et}"/>
   		<td>
   		</tr>
   		<tr>
   		<td class="STYLE4" >
   		操作内容：<textarea rows="1" cols="80" name="logger.logContent">${logger.logContent}</textarea>
	    &nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">&nbsp;</td>
	  </tr>  
	  </table>
	  </form>
	  </td>
	
				
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td style="padding:10px">
        <s:iterator value="loggerList" var="one" status="i">
        <s:if test="#i.Even">
        <div style="background-color:#EFF6FE;height:75px;line-height:20px;font-size:13px">
        </s:if>
        <s:else>
        <div style="background-color:white;height:75px;line-height:20px;font-size:13px">
        </s:else>
			<div style="float:left;width:60%"><span>操作人：${one.logOper}</span><span>&nbsp;&nbsp;&nbsp;IP地址：${one.logIP}</span></div>
			<div style="float:left;width:40%"><span>操作类型：${one.logType}</span><span>&nbsp;&nbsp;&nbsp;操作对象：${one.logObject}</span></div>
			<div style="clear:both;font-size:0px;height:0;"></div>
			<div style="float:left;width:60%"><span>模块-方法：${one.logCpModule} ${one.logCpMethod}</span></div>
			<fmt:formatDate value="${one.logCreatetime}" var="c_time" pattern="yyyy-MM-dd HH:mm:ss" type="date"/> 
			<div style="float:left;width:40%"><span>创建时间：${c_time}</span></div>
			<div style="clear:both;font-size:0;height:0;"></div>
			<div style="width:100%"><span>日志内容：${one.logContent}</span></div>
		</div>
        </s:iterator>
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