<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>
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
    	<%@include file="/plugin/medicinalAutocom.jsp" %>
    </td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  <form action="${pageContext.request.contextPath}/mz/mzChargeAction!queryAllTreatList" name="form1" method="post">
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	  &nbsp;划价收费列表
	  </td>
	  <td class="STYLE4" align="right" > 
	  &nbsp;就诊号 ：<input name="ghOrder.orderId" type="text" size="25" value="${ghOrder.orderId}"/>
	  &nbsp;&nbsp;&nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">
	  </td>
	  <td class="STYLE4" > </td>
	  <td class="STYLE4" > </td>
	  </tr> 
		</table>
		</form></td>		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  <tr >
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        
		 <table width="100%" cellpadding="0" cellspacing="0"  class ="activitytable topmargin10" summary="" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()" id="activitytable">
              <thead>
                <tr>
                  <th width="5%" scope="col" ><span class="nbsp"></span></th>
                  <th width="20%" align="left"  scope="col"><a href="javascript:void(0);" id="就诊号" title="就诊号"><span class="STYLE1">就诊号</span></a></th>
                  <th width="5%" align="left" scope="col" ><span class="nbsp"></span></th>
                  <th width="20%" align="left" scope="col" ><a href="javascript:void(0);" id="病人姓名" title="病人姓名"><span class="STYLE1">病人姓名</span></a></th>
                  <!--  <th width="12%" align="left" scope="col" ><a href="javascript:void(0);" id="状态" title="状态"><span class="STYLE1">状态</span></a></th> -->
                  <th width="12%" align="left" scope="col" ><a href="javascript:void(0);" id="性别" title="性别"><span class="STYLE1">性别</span></a></th>
                  <th width="11%" align="left" scope="col" ><a href="javascript:void(0);" id="年龄" title="年龄"><span class="STYLE1">年龄</span></a></th>
                  </tr>
              </thead>
              <tbody>
               <s:iterator value="ghOrderList" var="list" status="st">	
                <tr class ="main even">
                  <td><c:if test="${list.orderStatue != 1 || list.orderStatue != 2 }" ><a class="bluecollapse" onclick="getTotalM(${st.index})" href="javascript:void(0);">#</a></c:if></td>
                   <td><input name="orderid" value="${list.orderId}" type="hidden"/>  ${list.orderId}</td>
                   <td><span title=""></span></td>
                   <td>${list.sick.sickName }</td>
                  <!-- <td ><c:if test="${list.orderStatue eq 6 }" >发票报废</c:if><c:if test="${list.orderStatue eq 5 }" >已缴费</c:if><c:if test="${list.orderStatue eq 4 }" >诊疗</c:if><c:if test="${list.orderStatue eq 3 }">缴费</c:if><c:if test="${list.orderStatue eq 2 }" >已取药</c:if><c:if test="${list.orderStatue eq 1 }">离院</c:if><c:if test="${list.orderStatue eq 0 }">未诊疗</c:if></td> -->
                  <td ><c:if test="${list.sick.sickSex eq 0 }" >男</c:if><c:if test="${list.sick.sickSex eq 1 }">女</c:if></td>
                  <td align="left">${list.sick.sickAge }</td>
                  </tr>
                <tr class="details expand-child" id ="row_1">
                  <td colspan ="8">
                  <div class ="child clearfix">
                <form action="${pageContext.request.contextPath}/mz/mzChargeAction!addCharge" name="formform${st.index }" method="post">
                <s:token name="s_token"></s:token>
                <div>
		        <fieldset  style="width:98%;padding-top:10px">
		          <legend>
		         <b class="STYLE1">处方</b></legend>
				  <table  width="100%" border="0" cellpadding="0" cellspacing="1"  id="_medTable${st.index}">
				     <tr>
				     <td  bgcolor="#F7F7F7" width="8%"><div align="center">药品大类</div></td>
				     <td  bgcolor="#F7F7F7" width="10%"><div align="center">药品名称</div></td>
					 <td  bgcolor="#F7F7F7" width="9%"><div align="center">规格</div></td>
				     <td  bgcolor="#F7F7F7" width="6%"><div align="center">单位</div></td>
				     <td  bgcolor="#F7F7F7" width="27%"><div align="center">批号(公司)</div></td>
				     <td  bgcolor="#F7F7F7" width="8%"><div align="center">药房剩余</div></td>
				     <td  bgcolor="#F7F7F7" width="6%"><div align="center">单价</div></td>
				     <td  bgcolor="#F7F7F7" width="8%"><div align="center">处方数量</div></td>
				    </tr>
				    <%
				     int i=0;
				    %>
				    <s:iterator value="#list.mzJzList" var="jzList" status="jlt">
				     <c:forEach var="inventoryList" items="${jzList.mzYp.medicinalInventoryList}" varStatus="vst">
				     <%
				       ++i;
				     %>
				    <tr> 
				     <c:if test="${vst.index == 0}">
				     <td height="20" bgcolor="#FFFFFF" align="left" rowspan="${jzList.mzYp.medInvSize}"><span class="">${inventoryList.house.houseName }</span></td>
	                 <td height="20" bgcolor="#FFFFFF" align="left" rowspan="${jzList.mzYp.medInvSize}"><input type="hidden" name="invertoryID" value="${inventoryList.invertory_id}"/>${inventoryList.medicinal.medicinalName}</td>
	                 <td height="20" bgcolor="#FFFFFF" align="left" rowspan="${jzList.mzYp.medInvSize}"><span class="">${inventoryList.medicinal.standard.standardName}</span></td>
                     <td height="20" bgcolor="#FFFFFF" align="left" rowspan="${jzList.mzYp.medInvSize}"><span class="">${inventoryList.medicinal.unit.unitName}</span></td>
	                 <td height="20" bgcolor="#FFFFFF" align="left"><span class=""></span>${inventoryList.item_code} (${inventoryList.company.companyName})</td>
	                 <td height="20" bgcolor="#FFFFFF" align="right"><span class=""></span>${inventoryList.hqty}</td>
                     <td height="20" bgcolor="#FFFFFF" align="right"><span class=""><input type="hidden" name="dj" id="dj${st.index+1}<%=i%>" value="${inventoryList.resale_price}"/><input type="hidden" name="yflb" id="yflb${st.index+1}<%=i%>" value="${inventoryList.house.houseId}"/>${inventoryList.resale_price}</span></td>
                     <!--  <td height="20" bgcolor="#FFFFFF" align="right"><input [data@id]=${inventoryList.invertory_id } class="_MidNum" type="text" id="medicinal_num${st.index+1}<%=i%>" name="medicinal_num" onchange="countCharge(${st.index},${st.index+1}<%=i%>);" value="" style="width:35px"/></td>-->
	                 <td height="20" bgcolor="#FFFFFF" align="right" rowspan="${jzList.mzYp.medInvSize}">${jzList.mzYp.medicinal_num}</td>
	                </c:if>
	               </tr>
	               </c:forEach>
	               </s:iterator>
	              </table>
		         </fieldset>
                </div>
                </form>
               <hr class="dotted" />
              </div>
             </td>
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