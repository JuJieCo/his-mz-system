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

</head>

<body>
<form action="${pageContext.request.contextPath}/reports/toCitizenMedDEJSBpdf!printCitizenMedDEJSB" name="formname" method="post">
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
	  
	  
	  <s:token name="s_token"></s:token>
	  

	<table width="100%"  bgcolor="#EFF6FE" align="center">	
	 <tr align="center">
	  <td  class="STYLE2">
	  	<font size="5" >西安市居民医保患者住院费用结算表</font>
	  </td>
	 </tr>

	 <tr>
	  <td class="STYLE4"> 
	  <input name="citizenMedDE.citizenId" type="hidden" value="${citizenMedDE.citizenId }">
	  <input name="citizenMedDE.opertime" type="hidden" value="${citizenMedDE.opertime }">
	  <hr>
	  </td>
	 </tr> 
	</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" >
		
		  <tr>
		   <td  height="44" align="center"  bgcolor="#EFF6FE"  colspan="10"></td>
		  </tr>
		
          <tr align="center">
            <td  height="33" align="center"  bgcolor="#EFF6FE" width="4%"><span class="STYLE4">医保编号</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="6%"><input name="citizenMedDE.ybcode "  type="text" size="12"  value="${citizenMedDE.ybcode }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" width="3%" ><span class="STYLE4">姓名</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="6%"><input name="citizenMedDE.name"  type="text" size="12"  value="${citizenMedDE.name }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" width="5%" ><span class="STYLE4">性别</span></td>
            <td  height="33" align="left"  bgcolor="#FFFFFF" width="10%">&nbsp;<input name="citizenMedDE.sex"  type="text" size="7"  value="${citizenMedDE.sex }"   id=""/>
            <td  height="33" align="center"  bgcolor="#EFF6FE" width="4%" ><span class="STYLE4">年龄</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%"><input name="citizenMedDE.age"  type="text" size="22"  value="${citizenMedDE.age }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" width="5%"><span class="STYLE4">身份证号</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="10%" ><input name="citizenMedDE.sfcode"  type="text" size="22"  value="${citizenMedDE.sfcode }"  id=""/></td>
           </tr>
           
           <tr>
           	<td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">社区名称</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF"  colspan="3"><input name="citizenMedDE.addr"  type="text" size="43"  value="${citizenMedDE.addr }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">人员类别</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.persontype"  type="text" size="22"  value="${citizenMedDE.persontype }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">住院科室</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.dept"  type="text" size="22"  value="${citizenMedDE.dept }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">住院号</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.zycode"  type="text" size="22"  value="${citizenMedDE.zycode }"  id=""/></td>
            </tr>
            
            <tr>
           	<td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">入院日期</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.indate"  type="text" size="12" value="${citizenMedDE.indate }"/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">出院日期</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.outdate"  type="text" size="12" value="${citizenMedDE.outdate }"/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">住院天数</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.dates"  type="text" size="22"  value="${citizenMedDE.dates }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">出院诊断</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.clies"  type="text" size="22"  value="${citizenMedDE.clies }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">治疗结果</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.result"  type="text" size="22"  value="${citizenMedDE.result }"  id=""/></td>
            </tr>
            
            <tr>
           	<td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">既往统筹基金累计</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.oldtcjjlj"  type="text" size="12"  value="${citizenMedDE.oldtcjjlj }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">本次统筹基金累计</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" width="6%"><input name="citizenMedDE.nowtcjjlj"  type="text" size="12"  value="${citizenMedDE.nowtcjjlj }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">住院费用</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.alltotal"  type="text" size="22"  value="${citizenMedDE.alltotal }"  id="alltotal"/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">结算类别</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.balancetype"  type="text" size="22"  value="${citizenMedDE.balancetype }"  id=""/></td>
            <td  height="33" align="center"  bgcolor="#EFF6FE" ><span class="STYLE4">审核号</span></td>
            <td  height="33" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.checkcode"  type="text" size="22"  value="${citizenMedDE.checkcode }"  id=""/></td>
            </tr>
            
            <tr>
             <td  height="40" align="center"  bgcolor="#EFF6FE"  colspan="6"><span class="STYLE4"><font size="4" >超医保范围费用</font></span></td>
             <td  height="40" align="center"  bgcolor="#EFF6FE"  colspan="4"><span class="STYLE4"><font size="4" >医保范围内费用</font></span></td>
            </tr>
            
            <tr>
             <td  height="33" align="center"  bgcolor="#EFF6FE"  colspan="2" rowspan="2"><span class="STYLE4">自费药品</span></td>
             <td  height="33" align="center"  bgcolor="#EFF6FE"  colspan="2" rowspan="2"><span class="STYLE4">超床位费</span></td>
             <td  height="33" align="center"  bgcolor="#EFF6FE"  rowspan="2"><span class="STYLE4">其他费用</span></td>
             <td  height="33" align="center"  bgcolor="#EFF6FE"  rowspan="2"><span class="STYLE4">医保限额以上自费</span></td>
             <td  height="33" align="center"  bgcolor="#EFF6FE"  colspan="2" rowspan="2"><span class="STYLE4">起付标准</span></td>
             <td  height="33" align="center"  bgcolor="#EFF6FE"  ><span class="STYLE4">个人比例自付</span></td>
             <td  height="33" align="center"  bgcolor="#EFF6FE"  ><span class="STYLE4">统筹基金支付</span></td>
            </tr>
           
           <tr>
           	<td  height="33" align="center"  bgcolor="#EFF6FE"  width="10%"><span class="STYLE4">成人</span></td>
           	<td  height="33" align="center"  bgcolor="#EFF6FE"  ><span class="STYLE4">成人</span></td>
           </tr>
          
           <tr>
            <td  height="44" align="center"  bgcolor="#FFFFFF" colspan="2" ><input name="citizenMedDE.zfyftotal"  type="text" size="12"  value="${citizenMedDE.zfyftotal }"  id=""/></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" colspan="2" ><input name="citizenMedDE.overcwftotal"  type="text" size="12"  value="${citizenMedDE.overcwftotal }"  id=""/></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.otherftotal"  type="text" size="6"  value="${citizenMedDE.otherftotal }"  id=""/></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.ybxezftotal"  type="text" size="12"  value="${citizenMedDE.ybxezftotal }"  id=""/></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" colspan="2" ><input name="citizenMedDE.qfbztotal"  type="text" size="12"  value="${citizenMedDE.qfbztotal }"  id=""/></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.grblzftotal"  type="text" size="12"  value="${citizenMedDE.grblzftotal }"  id=""/></td>
            <td  height="44" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.tczfalltotal"  type="text" size="12"  value="${citizenMedDE.tczfalltotal }"  id=""/></td>
          </tr>
          
          <tr>
          	<td  height="44" align="center"  bgcolor="#EFF6FE"  colspan="2" rowspan="2"><span class="STYLE4">个人自付费用</span></td>
          	<td  height="44" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE.peralltotal"  type="text" size="12"  value="${citizenMedDE.peralltotal }"  id=""/></td>
          	<td  height="44" align="center"  bgcolor="#EFF6FE"  colspan="2" rowspan="2"><span class="STYLE4">个人自付费用大写</span></td>
          	<td  height="44" align="left"  bgcolor="#FFFFFF"  colspan="4">&nbsp;</td>
          
          </tr>
          
		 </table>
		</td>	
		
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
         
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
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
              <table border="0" align="center" cellpadding="0" cellspacing="0" id="form_oper">
                <tr>
                <td>
                <input type="submit" value="生成打印"/>	
				<input type="button" value="返回" onclick="history.go(-1)"/>	
				</td>
                </tr>
            </table>
            </td>
          </tr>
        </table>
        </td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>