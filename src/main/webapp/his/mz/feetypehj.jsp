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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/general.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/tabs.js" ></script>
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
   function sumCharge(jytpye){
	   //检 查 费
	    var jytpyejcf = $("#jytpyejcf").val();
		var jcf_skje = $("#jcf_skje").val();
		//化验 费
		var jytpyehyf = $("#jytpyehyf").val();
		var jytpyehyf_skje = $("#jytpyehyf_skje").val();
		//治疗费
	    var jytpyezlf = $("#jytpyezlf").val();
	    var jytpyezlf_skje = $("#jytpyezlf_skje").val();
	    //其他 费
		var jytpyeqtfy = $("#jytpyeqtfy").val();
	    var jytpyeqtfy_skje = $("#jytpyeqtfy_skje").val();
		 
	    $("#jcf_zhje").val(accSubtr(jcf_skje,jytpyejcf));
	    $("#jytpyehyf_zhje").val(accSubtr(jytpyehyf_skje,jytpyehyf));
	    $("#jytpyezlf_zhje").val(accSubtr(jytpyezlf_skje,jytpyezlf));
	    $("#jytpyeqtfy_zhje").val(accSubtr(jytpyeqtfy_skje,jytpyeqtfy));
	 
	}
   //退费
  function zlhj(e){
    var orderId = $("#orderId").val();
    var zlsf=0;
	if(orderId==''){
	 alert("请先查询病人信息,在划价!");	
	 return false;
	}
 
	if(e == 1){
		//检 查 费
		zlsf = $("#jytpyejcf").val();
	}else if(e == 2){
		//化验 费
		zlsf = $("#jytpyehyf").val();
	}else if(e == 3){
		 //治疗费
		zlsf = $("#jytpyezlf").val();
	}else if(e == 4){
		//其他 费
		zlsf = $("#jytpyeqtfy").val();
	}
	if(!isNumber(zlsf)){
	 alert("请正确输入要划价金额!");	
	 return false;	
 	}
  	var dataVal={orderTcfyId:orderId,jytpye:e,totalM:zlsf};
 	 $.ajax({
       type:"POST",
        data:dataVal,
        dataType:"json",
        url:"${pageContext.request.contextPath}/mz/mzChargeAction!updateZlHj",
        success:function(data){
        alert("划价成功！");
        },
        error:function(){alert("划价失败！");}
  	 });
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
	  <td >
	  <form action="${pageContext.request.contextPath}/mz/mzChargeAction!queryZlHjByID"  name="form1" method="post"  >
	  <s:token name="s_token"></s:token>
	  <table width="76%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	   &nbsp;诊疗划价
	  </td>
	  <td class="STYLE4" align="right" > 
	  &nbsp;病历号 ：<input name="sickCasehistory" type="text" size="25" value="${sickCasehistory}"/>
	  &nbsp;&nbsp;&nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">
	  </td>
	  <td class="STYLE4" > </td>
	  <td class="STYLE4" > </td>
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
    <td>
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  <form action="" name="form2" method="post"  >
	  <s:token name="s_token"></s:token>
	  <table width="100%"  bgcolor="">
	  <tr >
	  <td class="STYLE4" > 
	    <div style="padding-bottom:10px;">
		        <fieldset  style="width:75%;">
		         <legend>
		         <b class="STYLE1">病人信息</b></legend>
				  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				   <tr>
		            <td height="22" style="background-color: white;" align="right" class="STYLE1"  width="20%"> 病历号：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">&nbsp;<input type="hidden" value="${ghOrder.orderId}" name="orderId" id="orderId"></input>${ghOrder.sick.sickCasehistory }</td>
				    <td height="22" style="background-color: white;" align="right" class="STYLE1"  width="20%"> 病人姓名：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">&nbsp;${ghOrder.sick.sickName }</td>
		          </tr>
		         <tr>
		           <td height="22" align="right" style="background-color: white;" class="STYLE1"  width="20%"> 病人性别：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">
		             &nbsp;${ghOrder.sick.sickSex== '0'?'男':'' }${ghOrder.sick.sickSex== '1'?'女':'' }
					</td>
				    <td height="22" align="right" style="background-color: white;" class="STYLE1" width="20%">病人年龄：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">&nbsp;${ghOrder.sick.sickAge }</td>
		          </tr>
		        </table>
		        </fieldset>
              </div>
	   <DIV id=container style="padding-bottom:10px;">
		<UL class=menu>
		  <LI class=active id=jcf>检 查 费</LI>
		  <LI id=hyf>化 验 费</LI>
		  <LI id=zlf>治疗费</LI>
		  <LI id=qtfy>其 他 费 用</LI>
		  </UL>
		   <SPAN class=clear></SPAN>
	       <div class="content jcf" style="padding-top: 20px" >
           <span class="STYLE1" style="color:red;">检 查 费：</span><span><input type="text" name="jytpyejcf" style="width:63px" id="jytpyejcf" onkeyup="sumCharge(1);"> 元</span>
           <span class="STYLE1" style="color:red;padding-right:5px;"><input type="button" value="检查费划价" class="btn_mouseout" onclick="zlhj(1);"></span>
           </div>
          <div class="content hyf" style="padding-top: 25px" >
           <span class="STYLE1" style="color:red;">化 验 费：</span><span><input type="text" name="jytpyehyf" style="width:63px" id="jytpyehyf" onkeyup="sumCharge(2);"> 元</span>
           <span class="STYLE1" style="color:red;padding-right:5px;"><input type="button" value="化验费划价" class="btn_mouseout" onclick="zlhj(2);"></span>
           </div>
           <div class="content zlf" style="padding-top: 25px" >
           <span class="STYLE1" style="color:red;">治 疗 费：</span><span><input type="text" name="jytpyezlf" style="width:63px" id="jytpyezlf" onkeyup="sumCharge(3);"> 元</span>
            <span class="STYLE1" style="color:red;padding-right:5px;"><input type="button" value="治疗费划价" class="btn_mouseout" onclick="zlhj(3);"></span>
           </div>
		  <div class="content qtfy" style="padding-top: 25px" >
           <span class="STYLE1" style="color:red;">其 他 费：</span><span><input type="text" name="jytpyeqtfy" style="width:63px" id="jytpyeqtfy" onkeyup="sumCharge(4);"> 元</span>
          <span class="STYLE1" style="color:red;padding-right:5px;"><input type="button" value="其他费划价" class="btn_mouseout" onclick="zlhj(4);"></span>
           </div>
		</DIV>
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