<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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
function tuifei(id,fph,tag){
	var dataVal={table_tag:tag,id:id,fph:fph};
	$.ajax({
		 type:"POST",
	     data:dataVal,
	     dataType:"json",
      	 url:"${pageContext.request.contextPath}/mz/mzChargeAction!updateTuifee",
      	 success:function(data){
      		 $("#sp_status_"+id).text("");
      		 $("#sp_status_"+id).text("已退费");
	    	  $("#btn_"+id).attr("disabled","disabled");
	     },
      	 error:function(){alert("退费失败！");}
	 });
} 
</script>
<script type="text/javascript">

function validateForm(formname){
	
	var orderid = document.getElementById("orderid").value;
	

	  
	   if(jQuery.trim(orderid)=='' || !isNumber(orderid)){
			alert("请先确认输入就诊号的格式正确！");
			document.getElementById('orderid').focus();
			return false;
		 }
}
//减法函数
function accSubtr(arg1,arg2){
    var t1 = 0, t2 = 0, m, n;
    try
    {
        t1 = arg1.toString().split(".")[1].length;
    }
    catch(e)
    {t1 = 0;}
    try
    {
        t2 = arg2.toString().split(".")[1].length;
    }
    catch(e)
    {t2 = 0;}
    with(Math)
    {
        //动态控制精度长度
        n = Math.max(t1,t2);
        m = Math.pow(10, n);
        //return (arg1  * m - arg2 * m) / m;
        return ((arg1  * m - arg2 * m) / m).toFixed(n);
    }
}
//算收款
function sumCharge(){
	
	var hjinfo_totalM = $("#hjinfo_totalM").val();
	var hjinfogetm = $("#hjinfogetm").val();
    var hjinfo_backM = $("#hjinfo_backM");
    $("#hjinfo_backM").val(accSubtr(hjinfogetm,hjinfo_totalM));
 
}
//退费
// function tf(orderid){
	 
// 	var dataVal={orderTcfyId:orderid};
// 	 $.ajax({
//       type:"POST",
//       data:dataVal,
//       dataType:"json",
//       url:"${pageContext.request.contextPath}/mz/mzChargeAction!updateMzCharge",
//       success:function(data){
//       $("#tf").hide();
//       $("#dytf").show();
//       },
//       error:function(){alert("退费失败！");}
// 	 });
// } 

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
	  <td >
	  <form action=" ${pageContext.request.contextPath}/mz/mzChargeAction!queryHjInfo" name="form2" method="post"  >
	  <s:token name="s_token"></s:token>
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	   &nbsp;退费<br> <br>&nbsp;时间：<input name="txt_dateTime" id="txt_dateTime" type="text" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"  class="Wdate" style="width:95px"/>
	   发票号：<input name="fph_code"  type="text" size="30"  value="${orderid }"  id="fph_code"/>
	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="查询" class="btn_mouseout" >
	  <hr>
      </td>
	   </tr>
	    <tr>
	 	 	<td colspan="2" height="370px" valign="top">
	 	 	<c:if test="${fn:length(feeTypeList)>0}" var="list">
	 	 		<table id="details" width="100%" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
		          <tr>        
		          	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">发票号</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">就诊号</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">状态</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">检验类型</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">检验费用</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">检验时间</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1"></span></td>
		          </tr>
				  <!-- 迭代-->
						<s:iterator value="feeTypeList" var="fee_Type" status="status">
							<tr>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${fee_Type.fph}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${fee_Type.order_id}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1" id="sp_status_${fee_Type.feeid}">
								${fee_Type.flag eq 0 ?'未退费':''}
								${fee_Type.flag eq 1 ?'已退费':''}
								</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
								${fee_Type.jytpye eq 1?'检 查 费':''}
            					${fee_Type.jytpye eq 2?' 化 验 费':''}
            					${fee_Type.jytpye eq 3?'治疗费':''}
            					${fee_Type.jytpye eq 4?'其 他 费 用':''}
								</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${fee_Type.hjinfo_totalM}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><fmt:formatDate value="${fee_Type.hjinfo_dotime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></td>
								<td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3">
									<c:if test="${fee_Type.flag eq 0}"><button type="button" id="btn_${fee_Type.feeid}" onclick="tuifei('${fee_Type.feeid}','${fee_Type.fph}','1')" value="退费">退费</button></c:if>
									<c:if test="${fee_Type.flag eq 1}"><button type="button" disabled="disabled" value="退费">退费</button></c:if>
								 </td>
							</tr>
						</s:iterator>
				 <!--迭代 end  -->
		        </table>
		        </c:if>
		        <c:if test="${fn:length(mzchangeList)>0}" var="x">
		        <table id="details" width="100%" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
		          <tr>        
		          	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">发票号</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">就诊号</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">状态</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">西药合计</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">中成药合计</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">中草药合计</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">收取费用</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">划价时间</span></td>
		            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1"></span></td>
		          </tr>
				  <!-- 迭代-->
				  <s:iterator value="mzchangeList" var="mzCharge" status="status">
							<tr>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${mzCharge.fph}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${mzCharge.uuid}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1" id="sp_status_${mzCharge.id}" >
								 ${mzCharge.flag eq 0 ?'未划价':''}
								 ${mzCharge.flag eq 1 ?'已划价':''}
								 ${mzCharge.flag eq 2 ?'已收费':''}
								 ${mzCharge.flag eq 3 ?'已退费':''}
								</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${mzCharge.hjinfo_xytm}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${mzCharge.hjinfo_zcytm}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${mzCharge.hjinfo_zcytm2}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${mzCharge.hjinfo_getM}</span></td>
								<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><fmt:formatDate value="${mzCharge.hjinfo_dotime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></td>
								<td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3">
								 <c:if test="${mzCharge.flag eq 2}"><button type="button" id="btn_${mzCharge.id}" onclick="tuifei('${mzCharge.id}','${mzCharge.fph}','2')" value="退费">退费</button></c:if>
								 <c:if test="${mzCharge.flag eq 1 || mzCharge.flag eq 3|| mzCharge.flag eq 0}"><button type="button" disabled="disabled">退费</button></c:if>
								 </td>
							</tr>
						</s:iterator>
				 <!--迭代 end  -->
		        </table>
		        </c:if>
		        
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
        <td colspan="2"></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>