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
	
	var rulesName = document.getElementById("rulesNameID").value;
	var rulesCpFunds = document.getElementById("rulesCpFundsID").value; 
    var rulesMoneyUnits = document.getElementById("rulesMoneyUnitsID").value;
	var rulesCoefficient = document.getElementById("rulesCoefficientID").value;
    var rulesCreateTime = document.getElementById("rulesCreateTimeID").value;
    var rulesCpCost = document.getElementById("rulesCpCostID").value;
	
    if(rulesName==""){
		alert('请填写积分条目!');
		document.getElementById('rulesNameID').focus();
		return false;
	}
	if(rulesCpFunds==""){
		alert('请填写单位积分对应资金!');
		document.getElementById('rulesCpFundsID').focus();
		return false;
	}
	if(rulesCpCost==""){
		alert('请填写单位积分对应成本!');
		document.getElementById('rulesCpCostID').focus();
		return false;
		
	}
	if(rulesMoneyUnits==""){
		alert('请填写单位!');
		document.getElementById('rulesMoneyUnitsID').focus();
		return false;
		
	}
	if(rulesCoefficient==""){
		alert('请填写1元对应积分!');
		document.getElementById('rulesCoefficientID').focus();
		return false;
		
	}
	 
	if(rulesCreateTime==""){
		alert('请填写创建时间!');
		document.getElementById('rulesCreateTimeID').focus();
		return false;
		
	}
 
	

}
</script>

<script type="text/javascript">
function chageAction (obj){	
	rulesform.action="${pageContext.request.contextPath}/rules/rulesAction!updateRules";
	}
</script>


<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>
</head>

<body>
<form action="${pageContext.request.contextPath}/rules/rulesAction!addRules" name="rulesform" method="post" onsubmit="return validateForm(this)" >
<s:token name="s_token"></s:token>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
 <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif">
    	<%@include file="../common/table_top.jsp" %>
    </td>
  </tr>
  <tr>
    <td>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
		<tr>
            <td>
			<input type="hidden" name="rules.rulesId" value="${rules.rulesId }">
			</td>
		</tr>
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">
              <font color="RED" class="STYLE3">*</font>积分条目：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
			<input name="rules.rulesName" type="text" value="${rules.rulesName }" id="rulesNameID" size="45"/></td>
         </tr>
         
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">
            <font color="RED" class="STYLE3">*</font>单位积分对应资金：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;&nbsp;<input name="rules.rulesCpFunds" type="text" value="${rules.rulesCpFunds }" id="rulesCpFundsID" /></td>
         </tr>
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">标准：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;&nbsp;<input name="rules.rulesStandard" type="text" value="${rules.rulesStandard }" id="rulesStandardID" /></td>
         </tr>
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%"><font color="RED" class="STYLE3">*</font>单位积分对应成本：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;&nbsp;<input name="rules.rulesCpCost" type="text" value="${rules.rulesCpCost }" id="rulesCpCostID" /></td>
         </tr>
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">
            <font color="RED" class="STYLE3">*</font>单位：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;&nbsp;<input name="rules.rulesMoneyUnits" type="text" value="${rules.rulesMoneyUnits }" id="rulesMoneyUnitsID" size="20"/></td>
         </tr>
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">
            <font color="RED" class="STYLE3">*</font>1元对应积分：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;&nbsp;<input name="rules.rulesCoefficient" type="text" value="${rules.rulesCoefficient }" id="rulesCoefficientID" /></td>
         </tr>
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">积分状态：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
            	<select name="rules.rulesStatus" id="rulesStatusID">
            	    <option value="1" ${rules.rulesStatus==1?"selected":"" }>使用</option>
					<option value="2" ${rules.rulesStatus==2?"selected":"" }>停用</option>
				</select>
			</td>
         </tr>
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">
            <font color="RED" class="STYLE3">*</font>创建时间：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;&nbsp;
			<c:if test="${rulesCreateTime==''}">
			 <input name="rules.rulesCreateTime" type="text" value="${rules.rulesCreateTime}" readonly="readonly" id="rulesCreateTimeID" />
			</c:if>
			<c:if test="${rulesCreateTime!=''}">
			 <input name="rules.rulesCreateTime" type="text" value="${rulesCreateTime}" readonly="readonly" id="rulesCreateTimeID" />
			</c:if>
		 	</td>
         </tr>
         
         <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">备注：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;&nbsp;<textarea name="rules.rulesRemark" cols="30" rows="6">${rules.rulesRemark }</textarea></td>
         </tr>
            	
        </table>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
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