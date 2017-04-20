<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/validator/validationEngine.jquery.css" type="text/css"/>
<script src="${pageContext.request.contextPath}/resource/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/validator/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/js/validator/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

 <script language="JavaScript" type="text/javascript">  
	jQuery(document).ready(function(){
    	// binds form submission and fields to the validation engine
    	jQuery("#formID").validationEngine('attach');
    
	});
 </script>



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

</script>

<script type="text/javascript">

var i_num = 0;
function addDetails(){
	var str = "";
	str += '<tr>';
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><input type="hidden" name="historyList['+i_num+'].medicinal.medicinalId" value="" /><input type="text" name="medicinalInfo" value="" /></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"></span></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center"><input type="text" name="historyList['+i_num+'].hqty" value="" style="width:60px"/></td>'
	str += '<td height="20" bgcolor="#FFFFFF" align="center" class="STYLE3"><span onclick="removeDetails(this);" style="cursor:pointer;color:blue">移除</span></td>'
	str += '</tr> ';
	
	$("#details").append(str);
	$("#_doper").show();
	i_num++;
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
		    $.getJSON('${pageContext.request.contextPath}/baseinfo/doctorAction!jsonBillDetails',{doctorId:$(this).attr('[data@b_v]')},function(data){
				var str = "";
				$.each(data,function(i,v){
					str+="<tr>";
					str+="<td style='font-size:12px'><font color=#ED9020>医生简介 : </font>"+v.doctorIntrodcut+"</td>";
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
    </td>
  </tr>
  
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  
	  <form action=" ${pageContext.request.contextPath}/baseinfo/doctorAction!editDoctor" name="form2" method="post" id="formID" >
	  <s:token name="s_token"></s:token>
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <td class="STYLE4" > 
	  
	  &nbsp;创建医生 
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  
	  <input type="submit"  value="确定" class="btn_mouseout" >
	   &nbsp;&nbsp;&nbsp;<input type="reset"  value="重置" class="btn_mouseout"><br> <br>
	  <br>
	   <br>
	  &nbsp;姓名：&nbsp;<input name="doctor.doctorName"  type="text" size="15"  value="${doctor.doctorName}"  id="doctorName"  class="validate[required] text-input" />
	  
	  &nbsp;&nbsp;&nbsp;性别：<input name="doctor.doctorSex"  type="radio" size="30"  value="1" ${doctor.doctorSex==1?'checked':''}   id="doctorSex" checked="checked"/>&nbsp;男
	  					  <input name="doctor.doctorSex"  type="radio" size="30"  value="0"  ${doctor.doctorSex==0?'checked':''}  id="doctorSex"/>&nbsp;女
	  					  
	  <br>
	  &nbsp;简介：
	  <br>
	  <textarea name="doctor.doctorIntrodcut" cols="35" rows="6">${doctor.doctorIntrodcut}</textarea>
	  <br>
	   &nbsp;所在科室：
	  
	  <s:iterator value="deptList" var="list"  >
	  <input name="deptId"  value="${list.deptId}" type="checkbox"  class="validate[required]  " id="checkbox">&nbsp;${list.deptName}  
	  </s:iterator>
	  
	 
	  <input name="deptmassage" type="hidden">
	  <input name="doctor.doctorId"  type="hidden"  value="${doctor.doctorId }" />
	  <input name="isUpdate"  type="hidden"  value="${isUpdate}" />
	  
	   
	   <hr>
	   <br>
	    &nbsp;医生列表
	    
	  </td>
	   </tr> 
		</table>
		</form></td>	
		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
         <form action=" ${pageContext.request.contextPath}/baseinfo/doctorAction!showDoctor" name="form1" method="post" >
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="25%"><span class="STYLE1">医生姓名</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="10%"><span class="STYLE1">性别</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="20%"><span class="STYLE1">所在科室</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="20%"><span class="STYLE1">状态</span></td>
           <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF" width="20%"><span class="STYLE1">操作</span></td>
           </tr>
		  <!-- 迭代-->
	     <s:iterator value="ddList" var="list"  status="i">	
		 <tr>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.doctor.doctorName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.doctor.doctorSex==1?"男":"女"}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.dept.deptName }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${list.doctor.doctorStatue eq 0?"无效":"有效" }</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
            <a class="showD" href="javascript:void(0)" [data@t_v]="cont_${i.count}" [data@b_v]="${list.doctor.doctorId}">查看简介</a>
            <a href="${pageContext.request.contextPath}/baseinfo/doctorAction!showUpdateDoctor?doctorId=${list.doctor.doctorId}&deptId=${list.dept.deptId}">修改</a>&nbsp;&nbsp;
          	<a href="${pageContext.request.contextPath}/baseinfo/doctorAction!deleteDoctor?doctorId=${list.doctor.doctorId}&deptId=${list.dept.deptId}" onclick="return(confirm('确认要删除吗?'))">删除</a>&nbsp;&nbsp;
          	<a href="${pageContext.request.contextPath}/baseinfo/doctorAction!updateDoctorStatue?doctorId=${list.doctor.doctorId}">${list.doctor.doctorStatue eq 1?"无效":"有效" }</a>
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
    </table>
    </td>
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