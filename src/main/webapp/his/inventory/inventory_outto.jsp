<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出库</title>

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
function addInventoryToDetails(parent,mid){
	var dataVal={medicinalID:mid};
	$.ajax({
		type:"POST",
        data:dataVal,
        dataType:"json",
        url:"${pageContext.request.contextPath}/inventory/inventoryAction!jsonMedicinalInventoryByMedicinalID",
        success:function(data){
        	var str ="";
       		$.each(data,function(i,v){
       			str += '<tr>'
       	   			str +=  '<input type="hidden"  name="historyList['+i_num+'].medicinal.medicinalId" value='+v.medicinal.medicinalId+' />'
       	   			str +=  '<input type="hidden" name="historyList['+i_num+'].company.companyId" value='+v.company.companyId+' />'
       	   			str +=  '<input type="hidden" name="historyList['+i_num+'].item_code" value="'+v.item_code+'" />'   			
       	   			str +=	'<td width="50%" bgcolor="#FFFFFF"><span class="STYLE1">'+v.company.companyName+' - '+v.item_code+'</span></td>'
       	   		    str +=	'<td width="50%" bgcolor="#FFFFFF"><input type="text" id="hqty_'+i_num+'" name="historyList['+i_num+'].hqty" value="'+v.hqty+'" onblur="oncheckhqty('+i_num+','+v.hqty+')" style="width:60px" /></td>'
       	   		    str +='</tr>';   
       		   	i_num++;
       		});	
       		$(parent).find("table").eq(0).html(str); 
        },
        error:function(){alert("获取数据失败！");}
	});
// 	$.getJSON('${pageContext.request.contextPath}/inventory/inventoryAction!jsonMedicinalInventoryByMedicinalID',{medicinalID:mid},function(data){
// 		var str ="";
//    		$.each(data,function(i,v){
//    			str += '<tr>'
//    	   			str +=  '<input type="hidden"  name="historyList['+i_num+'].medicinal.medicinalId" value='+v.medicinal.medicinalId+' />'
//    	   			str +=  '<input type="hidden" name="historyList['+i_num+'].company.companyId" value='+v.company.companyId+' />'
//    	   			str +=  '<input type="hidden" name="historyList['+i_num+'].item_code" value="'+v.item_code+'" />'   			
//    	   			str +=	'<td width="50%" bgcolor="#FFFFFF"><span class="STYLE1">'+v.company.companyName+' - '+v.item_code+'</span></td>'
//    	   		    str +=	'<td width="50%" bgcolor="#FFFFFF"><input type="text" id="hqty_'+i_num+'" name="historyList['+i_num+'].hqty" value="'+v.hqty+'" onblur="oncheckhqty('+i_num+','+v.hqty+')" style="width:60px" /></td>'
//    	   		    str +='</tr>';   
//    		   	i_num++;
//    		});	
//    		$(parent).find("table").eq(0).html(str); 
//    	});
	
	
	
}
var returnRs;
function saveCheck(){
	for(var j=0;j<count;j++){
		if(!checkName(j)){
			return false;
		}
	}
	for(var i=0 ;i<i_num;i++){
		if(!checkInput(i)){
			return false;
		}
	}
	return returnRs;
		
}
function oncheckhqty(hqtyid,value){
	if($("#hqty_"+hqtyid+"").val()==""||$("#hqty_"+hqtyid+"").val()==null){
		$("#sp_msg").text("药品数量不能为空！");
		return returnRs=false;	
	}else if(!isNumber($("#hqty_"+hqtyid+"").val())){
		$("#sp_msg").text("请输入正确的数字格式！");
		return returnRs=false;
	}
	if($("#hqty_"+hqtyid+"").val()>value){
		$("#sp_msg").text("超出实际数量，请从新输入！");
		$("#hqty_"+hqtyid).val(value);
		//$("#hqty_"+hqtyid).attr(onblur,"onblur");//onfocus=""  onblur="
		return returnRs=false;   
	}
	$("#sp_msg").text("");
	return returnRs=true;
}
function checkName(num){
	if($("#medici_"+num+"").val()==""||$("#medici_"+num+"").val()==null){
		$("#sp_msg").text("请输入药品名称！");
		return false;
	}
	$("#sp_msg").text("");
	return true;
}
function checkInput(hqtyid){
	if($("#hqty_"+hqtyid+"").val()==""||$("#hqty_"+hqtyid+"").val()==null){
		$("#sp_msg").text("药品数量不能为空！");
		return false;	
	}else if(!isNumber($("#hqty_"+hqtyid+"").val())){
		$("#sp_msg").text("请输入正确的数字格式！");
		return false;
	}
	$("#sp_msg").text("");
	return true;
}
var i_num = 0;
var count=0;
function addDetails(){
	var str = "";
	str += '<tr>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><input type="text"    name="medicinalInfo" id="medici_'+count+'" value="" /></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center">'
	str += '<table width="100%" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">'
	str +='</table>'
	str += '</td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3"><span onclick="removeDetails(this)" style="cursor:pointer;color:blue">移除</span></td>'
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
			var dataVal={billID:$(this).attr('[data@b_v]')};
			$.ajax({
				type:"POST",
		        data:dataVal,
		        dataType:"json",
		        url:"${pageContext.request.contextPath}/inventory/inventoryAction!jsonBillDetails",
		        success:function(data){
		        	var str = "";
		        	
					$.each(data,function(i,v){
						str+="<tr>";
						str+="<td style='font-size:12px'><font color=#ED9020>药品名称 : </font>"+v.medicinal.medicinalName+"</td>";
						str+="<td style='font-size:12px'><font color=#ED9020>规格 : </font>"+v.medicinal.standard.standardName+"</td>";
						str+="<td style='font-size:12px'><font color=#ED9020>单位 : </font>"+v.medicinal.unit.unitName+"</td>";
						str+="<td style='font-size:12px'><font color=#ED9020>药品批号 : </font>"+v.item_code+"</td>";
						str+="<td style='font-size:12px'><font color=#ED9020>出库数量 : </font>"+v.hqty+"</td>";
						str+="</tr>";
					});
					$("#"+$(obj).attr('[data@t_v]')).find("table").html(str);
		        },
		        
		        error:function(){alert("获取列表失败！");}
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
	  	 <form action="${pageContext.request.contextPath}/inventory/inventoryAction!saveAndEditInventoryBill"  onsubmit="return saveCheck();" name="form2" method="post">
		  <s:token name="s_token"></s:token>
		  <table width="100%"  bgcolor="#EFF6FE">
		 	 <tr>
		  		<td class="STYLE3">新建出库单</td>
		  		<td></td>
		 	 </tr> 
		 	 <tr>
		 		<td  class="STYLE3" align="left">
		 		药房<s:select theme="simple" name="inventoryBill.hisHouse.houseId" list="houseList" listKey="houseId" listValue="houseName"></s:select></td>
		 		<td  class="STYLE3" align="right"><a href="javascript:addDetails()">添加明细</a></td>
		 		<input name="inventoryBill.inventoryBill_type" type="hidden" value="2" />
		 	 </tr> 
		 	 <tr>
		 	 	<td colspan="2">
		 	 		<table id="details" width="100%" align="center" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
			          <tr>        
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">药品名称</span></td>
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">规格</span></td>
			            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">单位</span></td>
			          	<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">批号-数量</span></td>
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
			        			<input type="submit"  value="保存" class="btn_mouseout"/>	<span id="sp_msg" style="color:red;font-size:12px" ></span>
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
	  <form action="${pageContext.request.contextPath}/inventory/inventoryAction!listOfoutto" name="form1" method="post">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="b5d6e6">
        	<tr><td class="STYLE3" bgcolor="#FFFFFF">出库单列表</td></tr>
        </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>        
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">出库单号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">出库日期</span></td>
			<td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">入库药房</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">状态</span></td>          
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="auto"><span class="STYLE1">操作</span></td>
          </tr>
		  <!-- 迭代-->
		  <s:iterator value="billList" var="bill" status="i">
		  <tr>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${bill.serNID}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><fmt:formatDate value="${bill.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${bill.hisHouse.houseName}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            	<span class="STYLE1">
            	${bill.inventoryBill_statue==1?'新建':''}
            	${bill.inventoryBill_statue==2?'已提交':''}
            	${bill.inventoryBill_statue==3?'已出库':''}
            	</span>
            </td>
            <td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3">
           		<a class="showD" href="javascript:void(0)" [data@t_v]="cont_${i.count}" [data@b_v]="${bill.inventoryBill_id}">查看</a>
            	<s:if test="#bill.inventoryBill_statue==1">
            	<a href="${pageContext.request.contextPath}/inventory/inventoryAction!doOutInventory?inventoryBill.inventoryBill_id=${bill.inventoryBill_id}&ftype=outto">出库</a>
            	<a href="javascript:if(confirm('确定要删除吗？')){location.href='${pageContext.request.contextPath}/inventory/inventoryAction!delete?inventoryBill.inventoryBill_id=${bill.inventoryBill_id}&ftype=outto'};">删除</a>
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
  </form>
</table>
</body>
</html>