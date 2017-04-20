<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理退药单</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
    </td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  	<td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  	<td>
		    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="b5d6e6">
			 <tr><td class="STYLE3" bgcolor="#FFFFFF">退药单列表</td></tr>
	        </table>
	        <form action="${pageContext.request.contextPath}/inventory/inventoryAction!listOfDoback" name="form1" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
	          <tr>        
	            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">申请日期</span></td>
	            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">退药单号</span></td>
	            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">来源药房</span></td>
	            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">状态</span></td>          
	            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">操作</span></td>
	          </tr>
			  <!-- 迭代-->
			  <s:iterator value="billList" var="bill">
			  <c:if test="${bill.inventoryBill_statue!=1}">
			  <tr>
	            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><fmt:formatDate value="${bill.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
	            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${bill.serNID}</span></td>
	            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${bill.hisHouse.houseName}</span></td>
	            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${bill.inventoryBill_statue==3?'已回退':'待处理'}</span></td>
	            <td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3">
	            <c:if test="${bill.inventoryBill_statue!=3}">
	            <a href="${pageContext.request.contextPath}/inventory/inventoryAction!getBillDetail?inventoryBill.inventoryBill_id=${bill.inventoryBill_id}">退药</a>
	            </c:if>
	            <c:if test="${bill.inventoryBill_statue==3}">
	            <span style="color:#666" >退药</span>
	            </c:if>
	            </td>
	          </tr>
			  </c:if> 
	          </s:iterator>
			 <!--迭代 end  -->
			 <tr><td colspan="5" align="right" bgcolor="#FFFFFF"><%@include file="/common/page.jsp" %></td></tr>
	        </table>
	        </form>
	  	</td>		
	  	<td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  <tr>
	  	<td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  	<td><hr/></td>
	  	<td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  <tr>
	    <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	    <td>
	    	<form action="${pageContext.request.contextPath}/inventory/inventoryAction!doTBackBill">
	    	<s:token name="s_token"></s:token>
	    	<table width="100%"  bgcolor="#EFF6FE" <s:if test="#show!='true'"> style='display:none' </s:if> >
			 <tr><td class="STYLE3" bgcolor="#FFFFFF" height="25">申请退药单明细</td></tr>
			 <tr>
				<td colspan="2">
				   <table width="100%" cellpadding="0" cellspacing="1" width="100%" align="center" border="0">
					  <s:iterator value="historyList" var="history">
					  	<tr>
							<td style='font-size:12px'><font color=#ED9020>药品名称 : </font>${history.medicinal.medicinalName}</td>
							<td style='font-size:12px'><font color=#ED9020>规格 : </font>${history.medicinal.standard.standardName}</td>
							<td style='font-size:12px'><font color=#ED9020>单位 : </font>${history.medicinal.unit.unitName}</td>
							<td style='font-size:12px'><font color=#ED9020>申请数量 : </font>${history.hqty}</td>
					    </tr>
					  </s:iterator>
					</table>
					<hr/>			
					<table id="details" width="100%" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
					  <tr>        
					    <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">药品名称</span></td>
					    <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">规格</span></td>
					    <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">单位</span></td>
						<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">公司 - 批号</span></td>
						<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">数量</span></td>
					  </tr>
					  <!-- 迭代-->
					  <input type="hidden" name="inventoryBill.inventoryBill_id" value="${inventoryBill.inventoryBill_id}" />
					  <input type="hidden" name="inventoryBill.hisHouse.houseId" value="${inventoryBill.hisHouse.houseId}" />
					  <input type="hidden" name="inventoryBill.inventoryBill_type" value="${inventoryBill.inventoryBill_type}" />
					  <s:iterator value="medicinalInventoryList" var="one" status="i">
					  <tr>
					  	<input type="hidden" name="medicinalInventoryList[${i.index}].invertory_id" value="${one.invertory_id}" />
					  	<input type="hidden" name="medicinalInventoryList[${i.index}].company.companyId" value="${one.company.companyId}" />
					  	<input type="hidden" name="medicinalInventoryList[${i.index}].medicinal.medicinalId" value="${one.medicinal.medicinalId}" />
					  	<input type="hidden" name="medicinalInventoryList[${i.index}].item_code" value="${one.item_code}" />
						<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.medicinal.medicinalName}</span></td>
						<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.medicinal.standard.standardName}</span></td>
						<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.medicinal.unit.unitName}</span></td>
						<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.company.companyName} ${one.item_code}</span></td>
						<td height="20" bgcolor="#FFFFFF" align="center"><input name="medicinalInventoryList[${i.index}].hqty" value="" type="text" /></td>
					  </tr>
					  </s:iterator>
					 <!--迭代 end  -->
					</table>
				</td>
 			</tr>
 			<tr>
			<td colspan="2">
				<table id="details" width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" bgcolor="#FFFFFF">
						<input type="submit"  value="退药" class="btn_mouseout" />	
					</td>
				</tr>
				</table>
			</td>
 			</tr>
		</table>
		</form>
	    </td>
	    <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	</table>
	</td>
  </tr>  
  <tr>
    <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td>&nbsp;</td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>