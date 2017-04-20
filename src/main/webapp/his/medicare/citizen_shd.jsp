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


</head>

<body>
<form action="${pageContext.request.contextPath}/reports/toCitizenMedDESHDpdf!printCitizenMedDESHD" name="form1" method="post" id="formcheck">
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
	  	<font size="5" >西安市居民医保患者费用审核单</font>
	  	
	  </td>
	 </tr> 
	 <tr>
	  <td class="STYLE4"> 
	  <input name="citizenMedDE.citizenId" type="hidden" value="${citizenMedDE.citizenId }">
	  <input name="citizenMedDE.opertime" type="hidden" value="${citizenMedDE.opertime }">
	  <hr>
	  &nbsp; 
	   	姓名：<input name="citizenMedDE.name"  type="text" size="15"  value="${citizenMedDE.name }" readonly="readonly"/>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	性别：<input name="citizenMedDE.sex"  type="text"   size="5"  value="${citizenMedDE.sex }"  readonly="readonly"/>	   
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	年龄：<input name="citizenMedDE.age"  type="text" size="5"  value="${citizenMedDE.age }"  readonly="readonly"/>
	  &nbsp;
	  	医保编号：<input name="citizenMedDE.ybcode"  type="text" size="15"  value="${citizenMedDE.ybcode }"  readonly="readonly"/>
	  &nbsp;&nbsp;&nbsp;&nbsp;
	   	人员类别：<input name="citizenMedDE.persontype"  type="text" size="15"  value="${citizenMedDE.persontype }" readonly="readonly"/>
	  </td>
	 </tr> 
	 <tr>
	  <td class="STYLE4">
	  &nbsp;
	  	社区：<input name="citizenMedDE.addr"  type="text" size="39"  value="${citizenMedDE.addr }"  readonly="readonly"/>
	  &nbsp;
	  	住院号：<input name="citizenMedDE.zycode"  type="text" size="5"  value="${citizenMedDE.zycode }"  readonly="readonly"/>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	科室：<input name="citizenMedDE.dept"  type="text" size="15"  value="${citizenMedDE.dept }"  readonly="readonly"/>
	  &nbsp;&nbsp;&nbsp;&nbsp;
	   	结算类别：<input name="citizenMedDE.balancetype"  type="text" size="15"  value="${citizenMedDE.balancetype }"  readonly="readonly"/>
	  </td>
	 </tr> 
	   
	   
	 <tr>
	  <td class="STYLE4">
	  &nbsp;
	  	入院日期：<input  name="citizenMedDE.indate"  type="text" size="15" value="${citizenMedDE.indate }" readonly="readonly"/>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	出院日期：<input  name="citizenMedDE.outdate"  type="text"  size="15" value="${citizenMedDE.outdate }" readonly="readonly"/>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	天数：<input name="citizenMedDE.dates"  type="text" size="5"  value="${citizenMedDE.dates }"  readonly="readonly" />
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	         结算期：<input name="citizenMedDE.balancedate"  type="text" size="15"  value="${citizenMedDE.balancedate }" readonly="readonly" />
 
	  </td>
	 </tr> 
	 <tr>
	 <td class="STYLE4">
	 &nbsp;
	    既往统筹基金累计：<input name="citizenMedDE.oldtcjjlj"  type="text" size="15"  value="${citizenMedDE.oldtcjjlj }"  readonly="readonly"/> 	
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    本次统筹基金累计：<input name="citizenMedDE.nowtcjjlj"  type="text" size="15"  value="${citizenMedDE.nowtcjjlj }"  readonly="readonly"/>	
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    审核号：<input name="citizenMedDE.checkcode"  type="text" size="15"  value="${citizenMedDE.checkcode }" readonly="readonly"/> 
	<hr>
	 </td>
	 </tr>
	 
	</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" >
		
          <tr align="center">
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="25%" rowspan="2" colspan="2"><span class="STYLE3">项目</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="20%" rowspan="2"><span class="STYLE3">小计</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="30%" colspan="2"><span class="STYLE3">个人支付</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  width="15%" rowspan="2" ><span class="STYLE3">统筹基金支付（元）</span><span class="STYLE3"></span><span class="STYLE3"></span><span class="STYLE3"></span><span class="STYLE3"></span></td>
           </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="10%"><span class="STYLE3">比例 </span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="15%"><span class="STYLE3">金额（元）</span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  rowspan="4"><span class="STYLE4">自费费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">自费药品费</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.zfyftotal"  type="text" size="10"  value="${citizenMedDE.zfyftotal }" readonly="readonly"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.zfyfper"  type="text" size="10"  value="${citizenMedDE.zfyfper }"  id="p1" readonly="readonly"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">超床位费</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.overcwftotal"  type="text" size="10"  value="${citizenMedDE.overcwftotal }" readonly="readonly"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.overcwfper"  type="text" size="10"  value="${citizenMedDE.overcwfper }"  id="p2" readonly="readonly"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">其他费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.otherftotal"  type="text" size="10"  value="${citizenMedDE.otherftotal }" readonly="readonly"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.otherfper"  type="text" size="10"  value="${citizenMedDE.otherfper }" readonly="readonly"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">医保限额以上自费</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.ybxezftotal"  type="text" size="10"  value="${citizenMedDE.ybxezftotal }" readonly="readonly"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.ybxezfper"  type="text" size="10"  value="${citizenMedDE.ybxezfper }" readonly="readonly"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" rowspan="2"><span class="STYLE4">医保费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">起付标准（自付）</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.qfbztotal"  type="text" size="10"  value="500.00"  readonly="readonly"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.qfbzper"  type="text" size="10"  value="500.00"  readonly="readonly" />&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
            <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">个人按比例自付</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name="citizenMedDE.grblzftotal"  type="text" size="10"  value="${citizenMedDE.grblzftotal }" readonly="readonly"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" >
            	<c:if test="${citizenMedDE.zfbiliper eq 0.45 }">45%</c:if> 
            </td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.grblzfper"  type="text" size="10"  value="${citizenMedDE.grblzfper }" readonly="readonly" id="p6"/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><input name="citizenMedDE.tczfper"  type="text" size="15"  value="${citizenMedDE.tczfper }" readonly="readonly"/><span class="STYLE4"></span><span class="STYLE4"></span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  colspan="2"><span class="STYLE4">合计</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.alltotal"  type="text" size="10"  value="${citizenMedDE.alltotal }" readonly="readonly"/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  colspan="2"><input name="citizenMedDE.peralltotal"  type="text" size="10"  value="${citizenMedDE.peralltotal }" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" colspan="3" align="center"  bgcolor="#FFFFFF"><input name="citizenMedDE.tczfalltotal"  type="text" size="15"  value="${citizenMedDE.tczfalltotal }" readonly="readonly"/><span class="STYLE4"></span></td>
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