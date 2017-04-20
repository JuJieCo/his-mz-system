<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药品调拨</title>

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
var i_num = 0;
function addDetails(){
	var str = "";
	str += '<tr>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><input type="hidden" name="historyList['+i_num+'].medicinal.medicinalId" value="" /><input type="text" name="medicinalInfo" id="medicinal_'+i_num+'" value="" /></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><input type="text" name="historyList['+i_num+'].hqty" id="hqty_'+i_num+'" value="" style="width:60px"/></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3"><span onclick="removeDetails(this);" style="cursor:pointer;color:blue">移除</span></td>'
	str += '</tr> ';
	
	$("#details").append(str);
	$("#_doper").show();
	i_num++;
	initMedicinalAutocom();
}

var returnRs;
function saveCheck(){
	for(var i=0 ;i<i_num;i++){
		oncheckhqty(i);
	}
	return returnRs;
}
function oncheckhqty(hqtyid){
	if($("#medicinal_"+hqtyid).val()==""||$("#medicinal_"+hqtyid).val()==null){
		$("#ft_msg").text("药品名称不能为空！");
		return returnRs=false;
	}
	if($("#hqty_"+hqtyid).val()==""||$("#hqty_"+hqtyid).val()==null){
		$("#ft_msg").text("药品数量不能为空！");
		return returnRs= false;
	}else if(!isNumber($("#hqty_"+hqtyid).val())){
		$("#ft_msg").text("药品数量格式错误，请输入正确的数字格式！");
		return returnRs= false;
	}
	return returnRs=true;
}

function removeDetails(e){
	$(e).parent().parent().remove();
	if($("#details").find("tr").size()<=1){
		$("#_doper").hide();
	}
}

$().ready(function(){
	initShowDetails();
});

function getDateStr(v){
	return (v.year+1900)+"年"+((v.month+1)<10?'0'+(v.month+1):(v.month+1))+"月"+((v.date+1)<10?'0'+(v.date+1):(v.date+1))+"日";
}

function initShowDetails(){
	$(".showD").toggle(
		function(){
			var obj = $(this);
			$.getJSON('${pageContext.request.contextPath}/inventory/inventoryAction!jsonBillDetails',{billID:$(this).attr('[data@b_v]')},function(data){
				var str = "";
				$.each(data,function(i,v){
					str+="<tr>";
					str+="<td style='font-size:12px'><font color=#ED9020>药品名称 : </font>"+v.medicinal.medicinalName+"</td>";
					str+="<td style='font-size:12px'><font color=#ED9020>规格 : </font>"+v.medicinal.standard.standardName+"</td>";
					str+="<td style='font-size:12px'><font color=#ED9020>单位 : </font>"+v.medicinal.unit.unitName+"</td>";
					str+="<td style='font-size:12px'><font color=#ED9020>申请数量 : </font>"+v.hqty+"</td>";
					str+="</tr>";
				});
				$("#"+$(obj).attr('[data@t_v]')).find("table").html(str);
			});
			$("#"+$(this).attr('[data@t_v]')).show();
		},
		function(){
			$("#"+$(this).attr('[data@t_v]')).hide();
		}
	);
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
	  	<form action="${pageContext.request.contextPath}/inventory/inventoryAction!saveAndEditInventoryBill" name="form2" onsubmit="return saveCheck();" method="post">
		  <s:token name="s_token"></s:token>
		  <table width="100%"  bgcolor="#EFF6FE">
		  	 <tr>
		  		<td class="STYLE3">新建调拨单</td>
		  		<td></td>
		 	 </tr> 
		 	 <tr>
		 		<td  class="STYLE3" align="left">
		 		药房<s:select theme="simple" name="inventoryBill.hisHouse.houseId" list="houseList" listKey="houseId" listValue="houseName"></s:select></td>
		 		<td  class="STYLE3" align="right"><a href="javascript:addDetails()">添加明细</a></td>
		 		<input name="inventoryBill.inventoryBill_type" type="hidden" value="3"/>
		 	 </tr>
		 	 <tr>
		 	 	<td colspan="2">
		 	 		<table id="details" width="100%" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
			          <tr>        
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">药品名称</span></td>
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">规格</span></td>
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">单位</span></td>
			          	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">数量</span></td>
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
			        			<input type="submit"  value="保存" class="btn_mouseout" />	<font id="ft_msg" style="color:red;font-size:12px"></font>
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
        	<tr><td class="STYLE3" bgcolor="#FFFFFF">调拨单列表</td></tr>
        </table>
        <form action="${pageContext.request.contextPath}/inventory/inventoryAction!listOftack" name="form1" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>        
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">申请日期</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">调拨单号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">申请药房</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">状态</span></td>          
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">操作</span></td>
          </tr>
		  <!-- 迭代-->
		  <s:iterator value="billList" var="bill" status="i">
		  <tr>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><fmt:formatDate value="${bill.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${bill.serNID}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${bill.hisHouse.houseName}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            	<span class="STYLE1">
            	${bill.inventoryBill_statue==1?'新建':''}
            	${bill.inventoryBill_statue==2?'已提交':''}
            	${bill.inventoryBill_statue==3?'已调拨':''}
            	</span>
            </td>
            <td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3">
            	<a class="showD" href="javascript:void(0)" [data@t_v]="cont_${i.count}" [data@b_v]="${bill.inventoryBill_id}">查看</a>
            	<s:if test="#bill.inventoryBill_statue==1">
            	<a href="${pageContext.request.contextPath}/inventory/inventoryAction!changeStatueOfBill?inventoryBill.inventoryBill_id=${bill.inventoryBill_id}&inventoryBill.inventoryBill_statue=2&ftype=diaobo">提交</a>
				<a href="javascript:if(confirm('确定要删除吗？')){location.href='${pageContext.request.contextPath}/inventory/inventoryAction!delete?inventoryBill.inventoryBill_id=${bill.inventoryBill_id}&ftype=diaobo'};">删除</a>
            	</s:if>
            </td>
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
        <td><%@include file="/common/page.jsp" %></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>