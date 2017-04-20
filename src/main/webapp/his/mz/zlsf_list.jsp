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
	

  
//除法函数
function accDiv(arg1,arg2){
    var t1 = 0, t2 = 0, r1, r2, n;
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
        r1 = Number(arg1.toString().replace(".",""));
        r2 = Number(arg2.toString().replace(".",""));
        n = Math.max(t1,t2);
        return (r1/r2)*pow(10, t2-t1);
    }
}

//乘法函数
function accMul(arg1,arg2)
{
    var t1 = 0, t2 = 0, r1, r2;
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
        r1 = Number(arg1.toString().replace(".",""));
        r2 = Number(arg2.toString().replace(".",""));
        return (r1*r2)/pow(10, t2+t1);
    }
}


  
//加法函数
function accAdd(arg1,arg2){
    var t1 = 0, t2 = 0, m;
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
        m=Math.pow(10,Math.max(t1,t2));
        return (arg1  * m + arg2 * m) / m;
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
function sumCharge(e){
	var hjinfo_totalM = $("#hjinfo_totalM"+e).val();//应收金 额
	var sk_getM = $("#sk_getM"+e).val();//收款金 额
   $("#hjinfo_backM"+e).val(accSubtr(sk_getM,hjinfo_totalM));
    
}
//药品收费
function check(e,id){
    var dataVal={feeid:id};
   $.ajax({
     type:"POST",
     data:dataVal,
     dataType:"json",
     url:"${pageContext.request.contextPath}/mz/mzChargeAction!updateZlSf",
     success:function(data){
     $("#medSK"+e).hide();
     $("#medDY"+e).show();
     },
     error:function(){alert("收费失败！");}
});
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
	  <form action="${pageContext.request.contextPath}/mz/mzChargeAction!queryAllZlSfList" name="form1" method="post">
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	  &nbsp;划价收费列表
	  </td>
	  <td class="STYLE4" align="right" > 
	  &nbsp;病历号 ：<input name="sickcasehistory" type="text" size="25" value="${sickcasehistory}"/>
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
                  <th width="20%" align="left" scope="col" ><a href="javascript:void(0);" id="病历号" title="病历号"><span class="STYLE1">病历号</span></a></th>
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
                   <td><input name="orderid" value="${list.orderId}" type="hidden"/><input name="uuid" value="${list.uuid}" type="hidden"/>  ${list.orderId}</td>
                   <td><span title=""></span></td>
                   <td>${list.sick.sickCasehistory }</td>
                   <td>${list.sick.sickName }</td>
                  <!-- <td ><c:if test="${list.orderStatue eq 6 }" >发票报废</c:if><c:if test="${list.orderStatue eq 5 }" >已缴费</c:if><c:if test="${list.orderStatue eq 4 }" >诊疗</c:if><c:if test="${list.orderStatue eq 3 }">缴费</c:if><c:if test="${list.orderStatue eq 2 }" >已取药</c:if><c:if test="${list.orderStatue eq 1 }">离院</c:if><c:if test="${list.orderStatue eq 0 }">未诊疗</c:if></td> -->
                  <td ><c:if test="${list.sick.sickSex eq 0 }" >男</c:if><c:if test="${list.sick.sickSex eq 1 }">女</c:if></td>
                  <td align="left">${list.sick.sickAge }</td>
                  </tr>
                <tr class="details expand-child" id ="row_1">
                  <td colspan ="8">
                  <div class ="child clearfix">
                <div>
		        <fieldset  style="width:98%;">
		         <legend>
		         <b class="STYLE1">病人信息</b></legend>
				  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				   <tr>
		            <td height="22" style="background-color: white;" align="right" class="STYLE1"  width="20%"> 病历号：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">&nbsp;${sick.sickCasehistory }</td>
		             <td height="22" style="background-color: white;" align="right" class="STYLE1"  width="20%"> 病人姓名：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">&nbsp;${sick.sickName }</td>
		          </tr>
		         <tr>
		           <td height="22" align="right" style="background-color: white;" class="STYLE1"  width="20%"> 病人性别：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">
		             &nbsp;${list.sick.sickSex== '0'?'男':'女' }
					</td>
				    <td height="22" align="right" style="background-color: white;" class="STYLE1" width="20%">病人年龄：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">&nbsp;${sick.sickAge }</td>
		          </tr>
		        </table>
		        </fieldset>
              </div>
              <s:token name="s_token"></s:token>
              <div class="clear"></div>
              <c:forEach items="${list.feeTypeList}" var="typeList" varStatus="vst">
              <hr class="dotted" />
              <div class="floatleft">
              <span class="STYLE1" style="padding-right:15px;">收费类型：<c:if test="${typeList.jytpye eq 1 }" >检 查 费</c:if><c:if test="${typeList.jytpye eq 2 }" >化 验 费</c:if><c:if test="${typeList.jytpye eq 3 }" >治 疗 费</c:if><c:if test="${typeList.jytpye eq 4 }" >其他费用</c:if></span>
              <span class="STYLE1" style="color:red;">应 收 金 额：</span><span style="padding-right:8px;"><input  type="text" name="hjinfo_totalM" disabled="disabled" style="width:65px" id="hjinfo_totalM${vst.index}" value="${typeList.hjinfo_totalM}"> 元</span>
              <span class="STYLE1" style="color:red;">收 款 金 额：</span><span style="padding-right:8px;"><input  type="text" name="sk_getM" style="width:65px" id="sk_getM${vst.index}" onkeyup="sumCharge(${vst.index})" value=""> 元</span>
              <span class="STYLE1" style="color:red;">找 回 金 额：</span><span style="padding-right:8px;"><input  type="text" name="hjinfo_backM" disabled="disabled" style="width:65px" id="hjinfo_backM${vst.index}"> 元</span>
              <span class="STYLE1" id="medSK${vst.index}"><input class="btn_mouseout" type="button" ${typeList.flag ==2 ? 'disabled=disabled':''} value="${typeList.flag==2 ? '已收费':'收 费'}" onclick="check(${vst.index},${typeList.feeid})"></span>
              <span class="STYLE1" id="medDY${vst.index}"  style="display:none"><input class="btn_mouseout" type="button" value="打印收费单"></span>
              </div>
            </c:forEach>
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