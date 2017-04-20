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
//应收金额
function countCharge(e,displayAmount,medicinal_num){
	
	 var sumXytm = 0;;
	 var sumZcytm = 0;
	 var sumZcytm2 = 0;
	 $("input[type=radio]:checked","#_medTable"+e).each(function(i,v){
		 var medicinal_num = $(v).val();
		 var index = $(v).attr("id").replace("medicinal_num","");
		 var dj = $("#dj"+index).val();
		 var yflb = $("#yflb"+index).val();
		 if(yflb == 1){
		     sumXytm = accAdd(sumXytm,accMul(dj,medicinal_num));
		  }else if(yflb == 2){  
		     sumZcytm = accAdd(sumZcytm,accMul(dj,medicinal_num));
		    }else{
		     sumZcytm2 = accAdd(sumZcytm2,accMul(dj,medicinal_num));
	   }
	 });
	   
	 $("#hjinfo_xytm"+e).val(sumXytm);
     $("#hjinfo_zcytm"+e).val(sumZcytm);
     $("#hjinfo_zcytm2"+e).val(sumZcytm2);
    
    
	 
	 
 	var totalAmount;
    var yflb = $("#yflb"+displayAmount).val();
    var dj = $("#dj"+displayAmount).val();
    var medicinal_num = $("#medicinal_num"+displayAmount).val();
    if(yflb == 1){
	 $("#hjinfo_xytm"+e).val(accMul(dj,medicinal_num));
	}else if(yflb == 2){  
	 $("#hjinfo_zcytm"+e).val(accMul(dj,medicinal_num));
    }else{
	 $("#hjinfo_zcytm2"+e).val(accMul(dj,medicinal_num));
 	}
    totalAmount = accAdd($("#hjinfo_zcytm2"+e).val(),(accAdd($("#hjinfo_xytm"+e).val(),$("#hjinfo_zcytm"+e).val())));
    $("#hjinfo_totalM"+e).val(totalAmount);
    $("#hjinfo_getM"+e).val(totalAmount);
    sumCharge(displayAmount);
 }
 
//算收款
function sumCharge(e){
	var hjinfo_getM = $("#hjinfo_getM"+e).val();//实 收金 额
	var sk_getM = $("#sk_getM"+e).val();//收款金 额
	var hjinfo_backM = $("#hjinfo_backM"+e);//找 回 金 额
    $("#hjinfo_backM"+e).val(accSubtr(sk_getM,hjinfo_getM));
    
}
 
//药品收费
function check(orderId,e,uuid,table_id){
	var z=0;
    var invertoryID;
    var medicinalid;
    var hjinfo_totalM = $("#hjinfo_totalM"+e).val();//应 收 金 额
	var hjinfo_getM = $("#hjinfo_getM"+e).val();//实 收 金 额
    var hjinfo_xytm = $("#hjinfo_xytm"+e).val();//西药费合计
	var hjinfo_zcytm = $("#hjinfo_zcytm"+e).val();//中成药费合计
	var hjinfo_zcytm2 = $("#hjinfo_zcytm2"+e).val();//中草药费合计
  
	
     $("._MidNum","#"+table_id).each(function(i,v){
    	 
        if($(v).val() != 0 && $(v).attr("checked") == true){
          if(z == 0){
       		 invertoryID = $(v).attr("[data@id]")+"_";
       		 medicinalid = $(v).attr("[data@medicinalid]")+"_";
       		
        	}else{
       		 invertoryID += $(v).attr("[data@id]")+"_";
       		 medicinalid += $(v).attr("[data@medicinalid]")+"_";
        	}
          z++;
         }
       });
	   var dataVal={orderid:orderId,orderTcfyId:uuid,invertoryIds:invertoryID,medicinalids:medicinalid,hjinfo_totalM:hjinfo_totalM,hjinfo_getM:hjinfo_getM,hjinfo_xytm:hjinfo_xytm,hjinfo_zcytm:hjinfo_zcytm,hjinfo_zcytm2:hjinfo_zcytm2};
	   $.ajax({
       type:"POST",
       data:dataVal,
       dataType:"json",
       url:"${pageContext.request.contextPath}/mz/mzChargeAction!updateCharge",
       success:function(data){
       $("#medSK"+e).hide();
       $("#medDY"+e).show();
       },
       error:function(){alert("药品划价失败！");}
	 });
    
  }
  
  
function getTotalM(e){
	 var sumXytm = 0;;
	 var sumZcytm = 0;
	 var sumZcytm2 = 0;
	 $("input[type=radio]:checked","#_medTable"+e).each(function(i,v){
		 var medicinal_num = $(v).val();
		 var index = $(v).attr("id").replace("medicinal_num","");
		 var dj = $("#dj"+index).val();
		 var yflb = $("#yflb"+index).val();
		 if(yflb == 1){
		     sumXytm = accAdd(sumXytm,accMul(dj,medicinal_num));
		  }else if(yflb == 2){  
		     sumZcytm = accAdd(sumZcytm,accMul(dj,medicinal_num));
		    }else{
		     sumZcytm2 = accAdd(sumZcytm2,accMul(dj,medicinal_num));
	   }
	 });
	   
	 $("#hjinfo_xytm"+e).val(sumXytm);
     $("#hjinfo_zcytm"+e).val(sumZcytm);
     $("#hjinfo_zcytm2"+e).val(sumZcytm2);
	 totalAmount = accAdd($("#hjinfo_zcytm2"+e).val(),(accAdd($("#hjinfo_xytm"+e).val(),$("#hjinfo_zcytm"+e).val())));
     $("#hjinfo_totalM"+e).val(totalAmount);
     $("#hjinfo_getM"+e).val(totalAmount);
     
     sumCharge(e);
  
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
	  <form action="${pageContext.request.contextPath}/mz/mzChargeAction!queryAllMzSickList" name="form1" method="post">
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	  &nbsp;门诊划价列表
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
                  <!-- <td ><c:if test="${list.orderStatue eq 7 }" >已划价</c:if><c:if test="${list.orderStatue eq 6 }" >发票报废</c:if><c:if test="${list.orderStatue eq 5 }" >已缴费</c:if><c:if test="${list.orderStatue eq 4 }" >诊疗</c:if><c:if test="${list.orderStatue eq 3 }">缴费</c:if><c:if test="${list.orderStatue eq 2 }" >已取药</c:if><c:if test="${list.orderStatue eq 1 }">离院</c:if><c:if test="${list.orderStatue eq 0 }">未诊疗</c:if></td> -->
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
				     <td  bgcolor="#F7F7F7" width="8%"><div align="center">选择药品</div></td>
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
	                 </c:if>
	                 <td height="20" bgcolor="#FFFFFF" align="left"><span class=""></span>${inventoryList.item_code} (${inventoryList.company.companyName})</td>
	                 <td height="20" bgcolor="#FFFFFF" align="right"><span class=""></span>${inventoryList.hqty}</td>
                     <td height="20" bgcolor="#FFFFFF" align="right"><span class=""><input type="hidden" name="dj" id="dj${st.index+1}<%=i%>" value="${inventoryList.resale_price}"/><input type="hidden" name="yflb" id="yflb${st.index+1}<%=i%>" value="${inventoryList.house.houseId}"/>${inventoryList.resale_price}</span></td>
                     <!--  <td height="20" bgcolor="#FFFFFF" align="right"><input [data@id]=${inventoryList.invertory_id } class="_MidNum" type="text" id="medicinal_num${st.index+1}<%=i%>" name="medicinal_num" onchange="countCharge(${st.index},${st.index+1}<%=i%>);" value="" style="width:35px"/></td>-->
	                 <td height="20" bgcolor="#FFFFFF" align="center">
	                 <input [data@id]=${inventoryList.invertory_id } class="_MidNum" [data@medicinalid]=${jzList.mzYp.id} type="radio"  id="medicinal_num${st.index+1}<%=i%>" name="medicinal_num${jlt.index}" onclick="getTotalM(${st.index})" value="${jzList.mzYp.medicinal_num}" style="width:35px" ${fn:length(jzList.mzYp.medicinalInventoryList)==1 ?'disabled=disabled':''}  ${vst.index == 0 ?'checked=checked':''}/></td>
	                 <c:if test="${vst.index == 0}">
	                 <td height="20" bgcolor="#FFFFFF" align="right" rowspan="${jzList.mzYp.medInvSize}">${jzList.mzYp.medicinal_num}</td>
	                </c:if>
	               </tr>
	               </c:forEach>
	               </s:iterator>
	              </table>
		         </fieldset>
                </div>
                </form>
             <div class="clear"></div>
             <hr class="dotted" />
             <div class="floatleft">
              <span class="STYLE1" style="color:red;padding-left:5px;">西药费合计：</span><span style="padding-right:10px;"><input  type="text" name="hjinfo_xytm" style="width:50px" id="hjinfo_xytm${st.index}" disabled="disabled" value=""> 元</span>
              <span class="STYLE1" style="color:red;">中成药费合计：</span><span style="padding-right:10px;"><input  type="text" name="hjinfo_zcytm" style="width:50px" id="hjinfo_zcytm${st.index}" disabled="disabled" value=""> 元</span>
              <span class="STYLE1" style="color:red;">中草药费合计：</span><span style="padding-right:10px;"><input  type="text" name="hjinfo_zcytm2" style="width:50px" id="hjinfo_zcytm2${st.index}"  disabled="disabled" value=""> 元</span>
              <span class="STYLE1" style="color:red;">应 收 金 额：</span><span style="padding-right:10px;"><input  type="text" name="hjinfo_totalM" disabled="disabled" style="width:50px" id="hjinfo_totalM${st.index}" value=""> 元</span>
              <span class="STYLE1" style="color:red;display:none">实 收 金 额：</span><span style="padding-right:25px;"><input  type="text" name="hjinfo_getM" style="width:50px;display:none" id="hjinfo_getM${st.index}" onkeyup="sumCharge(${st.index})" value=""></span>
              <span class="STYLE1" id="medSK${st.index}"><input class="btn_mouseout" type="button" ${list.zlflag==2 ? 'disabled=disabled':''} value="${list.zlflag==2 ? '已划价':'划价'}" onclick="check(${list.orderId},${st.index},${list.uuid},'_medTable${st.index}')"></span>
              <span class="STYLE1" id="medDY${st.index}"><input class="btn_mouseout" type="button" style="display:none" value="划价成功"></span>
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