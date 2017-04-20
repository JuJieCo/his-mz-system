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



<script>
function validateForm(formname){
	
	var zlinfo_content = document.getElementById("zlinfo_content").value;
    if(zlinfo_content==""){
		alert('请填写病历内容!');
		document.getElementById('zlinfo_content').focus();
		return false;
	}
}


function addTr2(e,cname,sname){
	var td = $(e).parent().parent();
								   
	str ='<div align="center"><input name="type" value="'+cname+'" type="hidden"><input name="levels" value="'+sname+'" type="hidden"><select name="numbers"><option value="1">第一届</option><option value="2">第二届</option><option value="3">第三届</option><option value="4">第四届</option><option value="5">第五届</option><option value="6">第六届</option><option value="7">第七届</option><option value="8">第八届</option><option value="9">第九届</option><option value="10">第十届</option><option value="11">第十一届</option><option value="12">第十二届</option><option value="13">第十三届</option><option value="14">第十四届</option><option value="15">第十五届</option><option value="16">第十六届</option><option value="17">第十七届</option><option value="18">第十八届</option><option value="19">第十九届</option><option value="20">第二十届</option></select> <select name="title"><option value="">请选择</option><option value="1">名誉主委</option><option value="2">顾问</option><option value="3">主任委员</option><option value="4">副主任委员</option><option value="5">副主任委员兼秘书</option><option value="6">常务委员</option><option value="7">常委员兼秘书</option><option value="8">委员</option><option value="9">委员兼秘书</option></select>'
				+' <input name="society" type="text" /><img src="${pageContext.request.contextPath}/web/images/btn_move.jpg" style="width:15px;height:15px;" onclick="moveTr(this)"/></div>';
    td.append(str);
    
    $("input[@name=society]").autocomplete(society1s, {
		minChars: 0,width: 310,max:100,matchContains: "word",autoFill: false,
		formatItem: function(row, i, max) {return row.name;},
		formatMatch: function(row, i, max) {return row.name;},
		formatResult: function(row) {return row.name;}
	});
  }
  
  function moveTr(e){
	var div = $(e).parent();
	div.remove();
  }
</script>

<script type="text/javascript">
function zlAction (obj){	
	sickform.action="${pageContext.request.contextPath}/mz/mzZlAction!updateZl";
	
	}
</script>


<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>
</head>

<body>
<form action="${pageContext.request.contextPath}/mz/mzZlAction!addZl" name="sickform" method="post" onsubmit="return validateForm(this)" >
<s:token name="s_token"></s:token>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif">
    	<%@include file="/common/table_top.jsp" %>
    </td>
  </tr>
  <tr>
    <td>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <div>
        <fieldset  style="width:95%;">
          <legend>
         <b class="STYLE1">病人基本信息</b></legend>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
		<tr>
            <td>
			<input type="hidden" name="ghOrder.orderId" value="${ghOrder.orderId }">
			</td>
		</tr>
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%"> 病人姓名：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input name="sick.sickName" type="text" value="${sick.sickName }" id="sickName" size="25"/></td>
          
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%"> 病人性别：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">
             <input type="radio" ${sick.sickSex== '0'?'checked':'' } value="男" name="sickSex" disabled="disabled" readonly="readonly" />男
			 <input type="radio" ${sick.sickSex== '1'?'checked':'' } value="女" name="sickSex" disabled="disabled" readonly="readonly" />女
			</td>
         </tr>
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">病人年龄：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input name="sick.sickAge" type="text" value="${sick.sickAge }" id="sickAge" /></td>
         
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">费用类型：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">
			 &nbsp;<select name="sickCosetype">
				  <option ${sick.sickCosetype=='1'?'selected':'' } readonly="readonly" disabled="disabled" value="1">自费</option>
			   </select>	 
			 </td>
         </tr>
        </table>
        </fieldset>
        </div>
        <div>
        <fieldset  style="width:95%;padding-top:10px">
          <legend>
         <b class="STYLE1">西医</b></legend>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
		   <tr>
		    <td  bgcolor="#F7F7F7"><div align="center">药品名称</div></td>
			<td  bgcolor="#F7F7F7"><div align="center">规格</div></td>
		    <td  bgcolor="#F7F7F7"><div align="center">单位</div></td>
		    <td  bgcolor="#F7F7F7"><div align="center">药房剩余</div></td>
			<td  bgcolor="#F7F7F7"><div align="center">用量</div></td>
		    <td  bgcolor="#F7F7F7"><div align="center">操作</div></td>
		   </tr>
		   <tr>
           <td width="15%" bgcolor="#F7F7F7" style="padding-right:10px"><div align="center"></div></td>
           <td width="15%" bgcolor="#F7F7F7" style="padding-right:10px"><div align="center"></div></td>
           <td width="7%" bgcolor="#F7F7F7" style="padding-right:10px"><div align="center"></div></td>
           <td width="7%" bgcolor="#F7F7F7" style="padding-right:10px"><div align="center"></div></td>
           <td width="7%" bgcolor="#F7F7F7" style="padding-right:10px"><div align="center"></div></td>
           <td width="7%" bgcolor="#F7F7F7" style="padding-right:10px"><div align="center"><img src="${pageContext.request.contextPath}/resource/images/btn_new.gif" style="width:15px;height:15px;" onclick=""/></div></td>
         </tr>
        </table>
        </fieldset>
        </div>
      </td>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table> 
    </td>
 </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
              <table border="0" align="center" cellpadding="0" cellspacing="0" id="form_oper">
                <tr> <td >
                <c:if test="${editsub==0 }">
					<input  type="submit"  value="提交"/>
					<input  type="reset"   value="清空"/>
				</c:if>
				<c:if test="${editsub==1 }">
					<input  type="submit"  value="提交" onclick="chageAction(); ">
				</c:if>
					
					<input  type="button" value="返回" onclick="history.go(-1)"/>	
				</td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table>
    
    </td>
  </tr>
</table>
 </form>
</body>
</html>