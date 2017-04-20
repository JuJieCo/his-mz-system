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
    	<%@include file="/common/table_top.jsp" %>
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  <form action="${pageContext.request.contextPath}/medicare/universityMedAction!queryMZnotCheckPerson" name="form1" method="post">
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	 	&nbsp; 统计 ：
	 	年<select name="year">
	 		<script>
	 			var check_year = '${year}';
	 			var now_year = new Date().getFullYear();
	 			for(i=0;i<now_year-2012+1;i++){
	 				var checked = "";
	 				if(check_year==(now_year-i)+""){
	 					checked = "selected=selected";
	 				}
	 				document.write('<option '+checked+' value='+(now_year-i)+'>'+(now_year-i)+'</option>');
	 			}
	 		</script>
	 	</select>
	 	月<select name="month">
	 		<script>
	 			var check_month = '${month}';
	 			var now_month = new Date().getMonth()+1;
	 			for(i=1;i<13;i++){
	 				var checked = "";
	 				if(now_month==i&&check_month==''){
	 					checked = "selected=selected";
	 				}
	 				if(check_month==(i<10?("0"+i):(i+""))){
	 					checked = "selected=true";
	 				}
	 				document.write('<option '+checked+' value='+(i<10?("0"+i):(i+""))+'>'+(i<10?("0"+i):(i+""))+'</option>');
	 			}
	 		</script>
	 	</select>
	 	&nbsp; <input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">
	  </td>
	  <td class="STYLE4" > </td>
	  <td class="STYLE4" > </td>
	  <td align="right"></td>
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
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">就诊编号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">医保编号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">姓名</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">性别</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">身份证号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">门诊次数</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">门诊总费用</span></td>
           	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">药费</span></td>
           	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">检测费</span></td>
           	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">化验费</span></td>
           	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">治疗费</span></td>
           	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">其他费用</span></td>
           	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">就诊时间</span></td>           	           	
           	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto" ><span class="STYLE1">操作</span></td>          
          </tr>
		  <!-- 迭代-->
	     <s:iterator value="universityMedDEList" var="one" >	
		 <tr>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_jcode}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_ycode}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_pname}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_psex eq 0 ? '女':'男'}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_pcode}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_mztimes}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_mztotalcost}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_medcost}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_checkcost}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_assaycost}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_treatcost}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_othercost}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.university_mzdate}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
            	<a href="${pageContext.request.contextPath}/medicare/universityMedAction!showCheckPage?opertype=1&ybcode=${one.university_ycode}&year=${year}&month=${month}">结算</a>
            </span></td>
          </tr> 
       </s:iterator>
		 <!--迭代 end  -->
        </table>
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