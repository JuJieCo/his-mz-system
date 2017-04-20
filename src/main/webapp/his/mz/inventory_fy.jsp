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
	
function check(e){
	 
	var medicinalnum=$("input[name='medicinal_num"+e+"']");
     
    $(medicinalnum).each(function(i,v){
	  if(jQuery.trim($(v).val())=='' || !isNumber($(v).val())){
			alert("请先确认输入的用量的格式正确！");
			return false;
		 }
	});
    $("form[name=form"+e+"]").submit();
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
	  <form action="${pageContext.request.contextPath}/inventory/inventoryAction!queryFyList" name="form1" method="post">
	  <!--<form action="${pageContext.request.contextPath}/inventory/inventoryAction!queryAllMzSickList" name="form1" method="post">-->
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	  &nbsp;药品发放列表
	  </td>
	  <td class="STYLE4" align="right" > 
	  &nbsp;就诊号 ：<input name="sick_casehistory" type="text" size="25" value="${sick_casehistory}"/>
	  &nbsp;&nbsp;&nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">
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
                  <th width="20%" align="left"  scope="col"><a href="javascript:void(0);" id="就诊号" title="就诊号"><span class="STYLE1">就诊号</span></a></th>
                  <th width="5%" align="left" scope="col" ><span class="nbsp"></span></th>
                  <th width="20%" align="left" scope="col" ><a href="javascript:void(0);" id="病人姓名" title="病人姓名"><span class="STYLE1">病人姓名</span></a></th>
                  <th width="12%" align="left" scope="col" ><a href="javascript:void(0);" id="性别" title="性别"><span class="STYLE1">性别</span></a></th>
                  <th width="12%" align="left" scope="col" ><a href="javascript:void(0);" id="状态" title="状态"><span class="STYLE1">状态</span></a></th>
                  
                 </tr>
              </thead>
              <tbody>
               <s:iterator value="ghOrderList" var="list" status="st">	
               <form action="${pageContext.request.contextPath}/inventory/inventoryAction!savefayao" name="formform${st.index }" method="post">
                <tr class ="main even">
                  <td><c:if test="${list.orderStatue eq 5 ||list.orderStatue eq 2 }" ><a class="bluecollapse" href="javascript:void(0);"></a></c:if></td>
                   <td><input name="uuid" value="${list.uuid}" type="hidden"/><input name="orderid" value="${list.orderId}" type="hidden"/>${list.orderId}</td>
                   <td><span title=""></span></td>
                   <td>${list.sick.sickName }</td>
                    <td ><c:if test="${list.sick.sickSex eq 0 }" >男</c:if><c:if test="${list.sick.sickSex eq 1 }">女</c:if></td>
                   <td >
                   <c:if test="${list.orderStatue eq 7 }" >已划价</c:if>
                   <c:if test="${list.orderStatue eq 6 }" >发票报废</c:if>
                   <c:if test="${list.orderStatue eq 5 }" >已缴费</c:if>
                   <c:if test="${list.orderStatue eq 4 }" >诊疗</c:if>
                   <c:if test="${list.orderStatue eq 3 }">缴费</c:if>
                   <c:if test="${list.orderStatue eq 2 }" >已取药</c:if>
                   <c:if test="${list.orderStatue eq 1 }">离院</c:if>
                   <c:if test="${list.orderStatue eq 0 }">未诊疗</c:if>
                   </td>
                  </tr>
                <tr class="details expand-child" id ="row_1">
                  <td colspan ="8">
                  <div class ="child clearfix">
                  
               <s:token name="s_token"></s:token>
                 <table  width="100%" border="0" cellpadding="0" cellspacing="1" >
				     <tr>
				     <td  width="10%"><div align="left">药品大类</div></td>
				     <td  width="10%"><div align="left">药品名称</div></td>
					 <td  width="13%"><div align="left">规格(单位)</div></td>
				     <td  width="10%"><div align="left">单位</div></td>
				     <td  width="20%"><div align="left">批号(公司)</div></td>
				     <td  width="9%"><div align="right">数量</div></td> 
				    </tr>
				  <hr class="dotted" />
				   <s:iterator value="#list.medInventoryList" var="medinventorylist" status="status">
				   <tr> 
				   <td height="20"   align="left">
				   <input type="hidden" name="yflb" id="yflb${status.index}" value="0" />
				   <input type="hidden" name="medicinalInventoryList[${status.index}].medicinal.medicinalId" value="${medinventorylist.medicinal.medicinalId}" />
				   <input type="hidden" name="medicinalInventoryList[${status.index}].invertory_id" value="${medinventorylist.invertory_id}" />
				   <input type="hidden" name="medicinalInventoryList[${status.index}].meidicinal_sum" value="${medinventorylist.meidicinal_sum}" />
				   <span class="STYLE1">
				   <c:if test="${medinventorylist.medicinal.medicinalType==1}" var="a">西药</c:if>
				   <c:if test="${medinventorylist.medicinal.medicinalType==2}" var="b">中成药</c:if>
				   <c:if test="${medinventorylist.medicinal.medicinalType==3}" var="c">中草药</c:if>
				   </span>
				   </td>
	               <td height="20"   align="left"><span class="STYLE1">${medinventorylist.medicinal.medicinalName}</span></td>
                   <td height="20"   align="left"><span class="STYLE1">${medinventorylist.medicinal.standard.standardName}</span></td>
                   <td height="20"   align="left"><span class="STYLE1">${medinventorylist.medicinal.unit.unitName}</span></td>
	               <td height="20"   align="left"><span class="STYLE1">${medinventorylist.item_code}</span></td>
	               <td height="20"   align="right"><span class="STYLE1">${medinventorylist.meidicinal_sum}</span></td>
	               </tr>
	              </s:iterator>
	             </table>
             <div class="clear"></div>
             <hr class="dotted" />
            <div class="floatleft" style="padding-top: 8px">
             <span class="STYLE1 floatright" style="padding-right:20px">
             <c:if test="${list.orderStatue eq 5 }" ><input  type="submit" value="发药" onclick="check(${st.index});"></c:if>
             <c:if test="${list.orderStatue eq 2 }" ><input  type="submit" value="已取药" disabled="disabled" /></c:if>
             </span>
              </div>
              </div>
              </td>
             </tr>
             </form>
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