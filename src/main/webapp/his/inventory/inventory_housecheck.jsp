<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药房盘库</title>

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

function doSearch(){
	$("input[type=hidden][name=medName]").val($("#medName").val());
	selectPage(1);
}

function snyNum(){
	if(confirm("确定要更新实盘数为库存数吗？此操作是不可逆的！")){
		var form = document.forms.form2;
		form.action = form.action+"?oty=syn";
		form.submit();
	}
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
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
         <table width="100%"  bgcolor="#EFF6FE">
		  <tr>
		  <td width="58%"  class="STYLE4" >药品名称：<input type="text" id="medName" value="${medName}"/>
		  &nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="doSearch()">&nbsp;</td>
		  <td width="24%">&nbsp;</td>
		  <td width="18%"  class="STYLE4" align="right">  
		  		<!-- <input type="button"  value="导出" class="btn_mouseout"/>
		  		<input type="button"  value="打印" class="btn_mouseout"/>  -->
		  </td>
		  </tr>  
		</table> 
       <form action="${pageContext.request.contextPath}/inventory/inventoryAction!saveRqty" method="post" name="form2">
        <s:token name="s_token"></s:token>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>        
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">药品名称</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">规格</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">单位</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">批号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">进价</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">单价</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">当前库存</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">实盘数</span></td>        
          </tr>
		  <!-- 迭代-->
		  <input type="hidden" name="type" value="yf" />
		  <s:iterator value="medicinalInventoryList" var="one" status="i">
		  <tr><input type="hidden" value="${one.invertory_id}" name="medicinalInventoryList[${i.index}].invertory_id" style="width:60px"/>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.medicinal.medicinalName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.medicinal.standard.standardName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.medicinal.unit.unitName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.item_code }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.purchase_price }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.resale_price }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.hqty }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><input type="text" value="${one.rqty }" name="medicinalInventoryList[${i.index}].rqty" style="width:60px"/></td>
          </tr> 
		  </s:iterator>
		 <!--迭代 end  -->
		 <tr>
	 	 	<td colspan="8">
	 	 		<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
		        	<tr>
		        		<td align="center" bgcolor="#FFFFFF">
		        			<input type="submit"  value="保存实盘数" class="btn_mouseout" />	
		        			<input type="button"  value="更新库存为实盘数" class="btn_mouseout" onclick="snyNum()"/>
		        		</td>
		        	</tr>
		        </table>
	 	 	</td>
	 	 </tr>
        </table>
        </form>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
    <form action="${pageContext.request.contextPath}/inventory/inventoryAction!checkInventory" name="form1" method="post">
	    <input type="hidden" name="type" value="yf" />
	    <input type="hidden" name="medName" value="${medName}" />
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
	        <td><%@include file="/common/page.jsp" %></td>
	        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
	      </tr>
	    </table>
    </form>
    </td>
  </tr>
</table>
</body>
</html>