<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调价</title>

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
/*查询药品 中 将 历史单的购入字段 作为 调价单的新零售价字段 后台接收时处理 */
function addInventoryToDetails(parent,mid){
	var dataVal={medicinalID:mid};
	 $.ajax({
	        type:"POST",
	        data:dataVal,
	        dataType:"json",
	        url:"${pageContext.request.contextPath}/inventory/inventoryAction!jsonMedicinalInventoryByMedicinalID",
	        success:function(data){
	        	var str="";
	    		$.each(data,function(i,v){
	       			str += '<tr>'
	       	   			str +=  '<input type="hidden" name="historyList['+i_num+'].medicinal.medicinalId" value='+v.medicinal.medicinalId+' />'
	       	   			str +=  '<input type="hidden" name="historyList['+i_num+'].company.companyId" value='+v.company.companyId+' />'
	       	   			str +=  '<input type="hidden" name="historyList['+i_num+'].item_code" value="'+v.item_code+'" />'
	       	   			str +=  '<input type="hidden" name="historyList['+i_num+'].resale_price" value="'+v.resale_price+'"  />'
	       	   			str +=	'<td width="50%" bgcolor="#FFFFFF"><span class="STYLE1">'+v.company.companyName+' - '+v.item_code+'</span></td>'
	       	   			str +=	'<td width="50%" bgcolor="#FFFFFF"><span class="STYLE1">'+v.resale_price+'</span></td>'
	       	   		    str +=	'<td width="50%" bgcolor="#FFFFFF"><input type="text" name="historyList['+i_num+'].purchase_price" id="pur_'+i_num+'" value="" style="width:60px"/></td>'
	       	   		    str +='</tr>';    
	       		   	i_num++;
	       		});	
	    		$(parent).find("table").eq(0).html(str);
	        },
	        error:function(){alert("获取列表失败！");}
	    });
}
var i_num = 0;
var count=0;
function addDetails(){
	var str = "";
	str += '<tr>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><input type="hidden" name="medicinalInfo" value="" /><input type="text" name="medicinalInfo" value="" id="medici_'+count+'" /></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center">'
	str += 		'<table width="100%" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">'
	str += 		'</table>'
	str += '</td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3"><span  onclick="removeDetails(this)" style="cursor:pointer;color:blue">移除</span></td>'
	str += '</tr> ';
	count++;
	$("#details").append(str);
	$("#_doper").show();
	initMedicinalAutocom();
}
function removeDetails(e){
	$(e).parent().parent().remove();
	if($("#details").find("tr").size()<=1){
		$("#_doper").hide();
	}
}
var returnRs;
function saveCheck(){
	if($("#id_cause").val()==""||$("#id_cause").val()==null){
		$("#sp_msg").text("调价事由不能为空！");
		return false;
	}
	for(var j=0;j<count;j++){
		checkName(j);
	}
	for(var i=0 ;i<i_num;i++){
		checkInput(i);
	}
	return returnRs;
}
function checkName(namecount){
	if($("#medici_"+namecount).val()==""||$("#medici_"+namecount).val()==null){
		$("#sp_msg").text("请输入药品名称！");
		return returnRs=false;
	}
	$("#sp_msg").text("");
	return returnRs=true;
}
function checkInput(hqtyid){
	if($("#pur_"+hqtyid+"").val()==""||$("#pur_"+hqtyid+"").val()==null){
		$("#sp_msg").text("药品新售价不能为空！");
		return returnRs=false;	
	}else if(!isNumber($("#pur_"+hqtyid+"").val())){
		$("#sp_msg").text("请输入正确的数字格式！");
		return returnRs=false;
	}
	$("#sp_msg").text("");
	return returnRs=true;
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
	  	<td>
		  <form action="${pageContext.request.contextPath}/inventory/inventoryAction!savechangeprice" onsubmit="return saveCheck();" name="form2" method="post">
		  <s:token name="s_token"></s:token>
		  <table width="100%"  bgcolor="#EFF6FE">
		  	 <tr>
		  		<td class="STYLE3">新建调价单</td>
		  		<td align="right" class="STYLE3"><a href="javascript:addDetails()">添加明细</a></td>
		 	 </tr> 
		 	 <tr>
		  		<td colspan="2" class="STYLE3">事由：<input name="changepriceBill_cause" id="id_cause" type="text" style="width:90%"/></td>
		 	 </tr> 
		 	 <tr>
		 	 	<td colspan="2">
		 	 		<table id="details" width="100%" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
			          <tr>        
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">药品名称</span></td>
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">规格</span></td>
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">单位</span></td>
			          	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">批号-原零售价-新零售价</span></td>
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">操作</span></td>
			          </tr>
					  <!-- 迭代-->
 
					 <!--迭代 end  -->
			        </table>
		 	 	</td>
		 	 </tr>
		 	 <tr>
		 	 	<td colspan="2">
		 	 		<table id="_doper" width="100%" align="center" border="0" cellpadding="0" cellspacing="0" style="display:none">
			        	<tr>
			        		<td align="center" bgcolor="#FFFFFF">
			        			<input type="submit"  value="保存" class="btn_mouseout" />	<span id="sp_msg" style="color:red;font-size:12px" ></span>
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
	  <tr>
	  	<td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  	<td><hr/></td>
	  	<td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="b5d6e6">
        	<tr><td class="STYLE3" bgcolor="#FFFFFF">调价单列表</td></tr>
        </table>
        <form action="${pageContext.request.contextPath}/inventory/inventoryAction!listchangeprice" name="form2" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>        
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">调价单号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">事由</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">创建日期</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">状态</span></td>          
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">操作</span></td>
          </tr>
		  <!-- 迭代-->
		 <s:iterator value="changepriceList" var="changeprice" status="i">
		 <tr>
		    <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${changeprice.changepriceBill_id}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${changeprice.changepriceBill_cause}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><fmt:formatDate value="${changeprice.changepriceBill_createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
            ${changeprice.changepriceBill_statue==0?'新建':''}
            ${changeprice.changepriceBill_statue==1?'已调价':''}
            </span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
            <s:if test="#changeprice.changepriceBill_statue==1">
            <span style="color:#666" >调价 &nbsp;&nbsp;删除</span>
            </s:if>
            <s:if test="#changeprice.changepriceBill_statue==0">
            <a href="${pageContext.request.contextPath}/inventory/inventoryAction!dochangeprice?chagepriceid=${changeprice.changepriceBill_id}">调价</a>&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/inventory/inventoryAction!deletechangeprice?chagepriceid=${changeprice.changepriceBill_id}">删除</a>
            </s:if>
            </span></td>
          </tr> 
          <tr id="cont_${i.count}" style="display:none">
          	<td colspan="6" bgcolor=#F0F0F0 height="30">
          		<table width="95%" border="0" cellpadding="0" cellspacing="0" style="line-height:25px">
          		</table>
          	</td>
          </tr>
		 </s:iterator>
		  
		 <!--迭代 end  -->
        </table>
        </form>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
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