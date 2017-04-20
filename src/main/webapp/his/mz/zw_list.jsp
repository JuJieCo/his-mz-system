<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/style-combined.css" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/activity.js"></script> 
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
 
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
    
	if(jQuery.trim(pz)!=''&&isNumber(pz)&&pz.charAt(0)!='0'&&isNumber(currentPage)){
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
<s:token name="s_token"></s:token>
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
	  <form action="${pageContext.request.contextPath}/mz/mzChargeAction!queryZwList" name="form1" method="post">
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" align="left" style="padding-left:8px"> 
	    医保号:<input name="orderID" type="text" size="9" value="${orderID}"/>
	  <td class="STYLE4" align="left" > 
	   医生:
	   <select name="doctor"><option value="">全部</option>
	   <s:iterator value="doctorList" var="doctorlist">
	    <option value="${doctorlist.doctorId }">${doctorlist.doctorName }</option>
	   </s:iterator>
	   </select>
	 <td class="STYLE4" align="left" > 
	   科室:<select name="ks"><option value="">全部</option>
	   	  <s:iterator value="deptList" var="deptlist">
	       <option value="${deptlist.deptId }">${deptlist.deptName }</option>
	      </s:iterator>
	      </select>
	  <td class="STYLE4" align="left" > 
	  收费时间: <input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" size="12" class="Wdate"  id="startDate" name="startDate"  type="text" value="${startDate }"/>
	  至 <input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" size="12" class="Wdate"  id="endDate" name="endDate"  type="text" value="${endDate }"/>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">
	  </td>
	  <td class="STYLE4" > </td>
	  <td class="STYLE4" > </td>
	  </tr> 
		</table>
		</form></td>		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        
		 <table width="100%" cellpadding="0" cellspacing="0"  class ="activitytable topmargin10" summary="" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()" id="activitytable">
              <thead>
                <tr>
                  <th width="5%" scope="col" ><span class="nbsp"></span></th>
                  <th width="8%" align="left"  scope="col"><a href="javascript:void(0);" id="医保号" title="医保号"><span class="STYLE1">医保号</span></a></th>
                  <th width="5%" align="left" scope="col" ><span class="nbsp"></span></th>
                  <th width="10%" align="left" scope="col" ><a href="javascript:void(0);" id="病人姓名" title="病人姓名"><span class="STYLE1">病人</span></a></th>
                  <th width="10%" align="left" scope="col" ><a href="javascript:void(0);" id="医生" title="医生"><span class="STYLE1">医生</span></a></th>
                  <th width="10%" align="left" scope="col" ><a href="javascript:void(0);" id="科室" title="科室"><span class="STYLE1">科室</span></a></th>
                  <th width="10%" align="left" scope="col" ><a href="javascript:void(0);" id="总金额" title="总金额"><span class="STYLE1">总金额</span></a></th>
                  <th width="10%" align="left" scope="col" ><a href="javascript:void(0);" id="西药费合计" title="西药费合计"><span class="STYLE1">西药费合计</span></a></th>           
                  <th width="10%" align="left" scope="col" ><a href="javascript:void(0);" id="中成药费合计" title="中成药费合计"><span class="STYLE1">中成药费合计</span></a></th>              
                  <th width="10%" align="left" scope="col" ><a href="javascript:void(0);" id="中草药费合计" title="中草药费合计"><span class="STYLE1">中草药费合计</span></a></th>                             
                </tr>
              </thead>
              <tbody>
                 <s:iterator value="rlist" var="map" status="st">	
                 <tr class ="main even">
                  <td>&nbsp;</td>
                  <td>${map['sick_ybcode']}</td>
                  <td>&nbsp;</td>
                  <td>${map['sick_name']}</td>
                  <td>${map['doctor_name']}</td>
                  <td>${map['dept_name']}</td>
                  <td>${map['total']}</td>
                  <td>${map['tytm']}</td>
                  <td>${map['zcytm']}</td>
                  <td>${map['zcytm2']}</td>
                 </tr>
                 </s:iterator>
              </tbody>
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