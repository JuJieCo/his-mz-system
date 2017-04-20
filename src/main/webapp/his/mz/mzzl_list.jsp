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
	  if(jQuery.trim(medicinalnum)=='' || !isNumber(medicinalnum)){
			alert("请先确认输入的用量的格式正确！");
			return false;
		 }
	});
    $("form[name=form"+e+"]").submit();
  }
function addDetails(e){
	 
	var str = "";
	str += '<tr>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><input type="hidden" name="medicinal_sum" id="medi_sum_1" value="" /><input type="hidden" name="medicinalID" value="" /><input type="text" id="medicinalInfo" name="medicinalInfo" value="" /></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
    str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1" id="medicinal_sum_1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><input type="text" id="medicinal_num" name="medicinal_num" value="" style="width:60px"/></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3"><span onclick="$(this).parent().parent().remove();" style="cursor:pointer;color:blue">移除</span></td>'
	str += '</tr> ';
    $("#productdetils"+e).after(str);
    initMedicinalAutocom();
}
function addInventoryToDetails(parent,mid){
	var dataVal={medicinalID:mid};
	 $.ajax({
	        type:"POST",
	        data:dataVal,
	        dataType:"json",
	        url:"${pageContext.request.contextPath}/mz/mzZlAction!getMedicinalNum",
	        success:function(data){
	        	$("#medicinal_sum_1").text(data);
	        	$("#medi_sum_1").val(data);
	        },
	        error:function(){alert("获取失败！");}
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
	  <form action="${pageContext.request.contextPath}/mz/mzZlAction!queryAllMzSickList" name="form1" method="post">
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	  &nbsp;待诊病人列表
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
                 <th width="12%" align="left" scope="col" ><a href="javascript:void(0);" id="状态" title="状态"><span class="STYLE1">状态</span></a></th>
                 <th width="12%" align="left" scope="col" ><a href="javascript:void(0);" id="性别" title="性别"><span class="STYLE1">性别</span></a></th>
                 <th width="11%" align="left" scope="col" ><a href="javascript:void(0);" id="年龄" title="年龄"><span class="STYLE1">年龄</span></a></th>
                 </tr>
              </thead>
              <tbody>
               <s:iterator value="ghOrderList" var="list" status="st">	
                  <tr class ="main even">
                  <td><c:if test="${list.orderStatue != 1 || list.orderStatue != 2 }" ><a class="bluecollapse" href="javascript:void(0);"></a></c:if></td>
                   <td>${list.orderId}</td>
                   <td><span title=""></span></td>
                   <td>${list.sick.sickName }</td>
                   <td ><c:if test="${list.orderStatue eq 9 }" >已退号</c:if><c:if test="${list.orderStatue eq 7 }" >已划价</c:if><c:if test="${list.orderStatue eq 6 }" >发票报废</c:if><c:if test="${list.orderStatue eq 5 }" >已缴费</c:if><c:if test="${list.orderStatue eq 4 }" >诊疗</c:if><c:if test="${list.orderStatue eq 3 }">缴费</c:if><c:if test="${list.orderStatue eq 2 }" >已取药</c:if><c:if test="${list.orderStatue eq 1 }">离院</c:if><c:if test="${list.orderStatue eq 0 }">未诊疗</c:if></td>
                   <td ><c:if test="${list.sick.sickSex eq 0 }" >男</c:if><c:if test="${list.sick.sickSex eq 1 }">女</c:if></td>
                   <td align="left">${list.sick.sickAge }</td>
                  </tr>
                <tr class="details expand-child" id ="row_1">
                  <td colspan ="8" align="center">
                  <s:if test="#list.mzJzList.size()>0">
                  <div>
                  	<table width="98%" cellpadding="0" cellspacing="1" bgcolor="#BDE3FB">
                  		<tr><th colspan="20" style="color:blue">本日就诊历史</th></tr>
                  		<tr>
                  			<th>就诊时间</th>
                  			<th>病历</th>
                  			<th>疾病诊断</th>
                  			<th>处方</th>
                  		</tr>
                  		<s:iterator value="#list.mzJzList" var="mzJz">
	                  		<tr>
	                  			<td rowspan="<s:property value="#mzJz.mzYpList.size()+1"/>">${mzJz.zlinfo_dotime}<br/>状态：${mzJz.flag==1?'划价':(mzJz.flag==2?'缴费':(mzJz.flag==3?'退费':'待划价'))}</td>
	                  			<td rowspan="<s:property value="#mzJz.mzYpList.size()+1"/>">${mzJz.zlinfo_content}</td>
	                  			<td rowspan="<s:property value="#mzJz.mzYpList.size()+1"/>">${mzJz.zlinfo_remark}</td>
	                  		</tr>
	                  		
	                  		<s:iterator value="#mzJz.mzYpList" var="mzYp">
	                  		<tr>
	                  		<!--  
	                  			<td>${mzYp.medicinalInventoryList[0].medicinal.medicinalName} ${mzYp.medicinalInventoryList[0].medicinal.unit.unitName} ${mzYp.medicinalInventoryList[0].medicinal.standard.standardName} 数量：${mzYp.medicinal_num}</td>
	                  		-->
	                  		<td><a href="${pageContext.request.contextPath}/reports/toPrintChuFangpdf!toPrintChuFangpdf">处方</a></td>
	                  		
	                  		</tr>
	                  		</s:iterator>
                  		</s:iterator>
                  	</table>
                  </div>
                  </s:if>
                  <div class ="child clearfix">
                <div>
		        <fieldset  style="width:98%;">
		         <legend>
		         <b class="STYLE1">病人基本信息</b></legend>
				  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				   <tr>
		            <td height="22" style="background-color: white;" align="right" class="STYLE1"  width="20%"> 病人姓名：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">&nbsp;${sick.sickName }</td>
		          
		            <td height="22" align="right" style="background-color: white;" class="STYLE1"  width="20%"> 病人性别：</td>
					<td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">
		             &nbsp;${sick.sickSex== '0'?'男':'女' }
					</td>
		             </tr>
		           <tr>
		            <td height="22" align="right" style="background-color: white;" class="STYLE1" width="20%">病人年龄：</td>
					<td height="22" align="left" style="background-color: white;" colspan="3" class="STYLE1" width="30%">&nbsp;${sick.sickAge }</td>
		          </tr>
		        </table>
		        </fieldset>
              </div>
              
              <form action="${pageContext.request.contextPath}/mz/mzZlAction!addorupdatezl" name="form${st.index }" method="post">
                <s:token name="s_token"></s:token>
               <div>
		        <fieldset  style="width:98%;padding-top:10px">
		          <legend>
		         <b class="STYLE1">填写病历</b></legend>
				  <table  width="100%" border="0" cellpadding="0" cellspacing="1" >
				   <tr>
		             <td height="22" style="background-color: white;" align="right" class="STYLE1"  width="20%"> 填写病历：</td>
					 <td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">&nbsp;<textarea cols="24" rows="4" name="zlinfoContent" id="zlinfoContent${st.index }"></textarea></td>
		             <td height="22" align="right" style="background-color: white;" class="STYLE1"  width="20%"> 疾病诊断：</td>
					 <td height="22" align="left" style="background-color: white;" class="STYLE1" width="30%">
		             &nbsp;
		             <input name="orderid" value="${list.orderId}" type="hidden"/>
		             <textarea cols="24" rows="4" name="zlinfoRemark" id="zlinfoRemark${st.index }"></textarea>
					</td>
		           </tr>
		        
			    </table>
		        </fieldset>
              </div>
               <div>
		       <fieldset  style="width:98%;padding-top:10px">
		          <legend>
		         <b class="STYLE1">电子处方</b></legend>
				  <table  width="100%" border="0" cellpadding="0" cellspacing="1" >
				      
			         <tr id="productdetils${st.index }">
				     <td  bgcolor="#F7F7F7"><div align="center">药品名称</div></td>
					 <td  bgcolor="#F7F7F7"><div align="center">规格</div></td>
				     <td  bgcolor="#F7F7F7"><div align="center">单位</div></td>
				     <td  bgcolor="#F7F7F7"><div align="center">药房剩余</div></td>
					 <td  bgcolor="#F7F7F7"><div align="center">用量</div></td>
				     <td  bgcolor="#F7F7F7"><div align="center"><a href="javascript:addDetails(${st.index })">添加处方</a></div></td>
				    </tr>
			    </table>
		        </fieldset>
                </div>
                <div class="clear"></div>
                <hr class="dotted" />
                <div class="floatright">
                <input type="button" class="btn_mouseout" ${list.orderStatue==5 ? 'disabled=disabled':''} value="${list.orderStatue==5 ? '请先取药,再从新就诊':'就诊'}" onclick="check(${st.index});">
                </div>
               </div>
              </form>
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