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
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif">
    	<%@include file="/common/table_top.jsp" %>
    </td>
  </tr>
  
<tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  
	  <form action=" ${pageContext.request.contextPath}/baseinfo/unitAction!editUnit" name="form2" method="post" >
	  <s:token name="s_token"></s:token>
	  
	  <table width="100%"  bgcolor="#EFF6FE"  align="center">
	 
	  <tr align="center">
	  <td class="STYLE2" >
	 
	  <font size="5" >居民医保患者住院费用信息登记4</font></td>
	  </tr>
	  
	  <tr align="center">
	  <td class="STYLE4"> 
	  <hr>
	 
	  &nbsp;姓名：<input name=""  type="text" size="10"  value=""  id=""/>
	  &nbsp;性别：<input name=""  type="radio" size="30"  value="1" checked="checked"  id=""/>&nbsp;男
	  			 <input name=""  type="radio" size="30"  value="0"   id=""/>&nbsp;女
	  &nbsp;年龄：<input name=""  type="text" size="5"  value=""  id=""/>
	  &nbsp;身份证号：<input name=""  type="text" size="21"  value=""  id=""/>
	  &nbsp;单位：<input name=""  type="text" size="32"  value=""  id=""/>
	  
	  <br>
	  <br>
	  &nbsp;医保编号：<input name=""  type="text" size="15"  value=""  id=""/>
	  &nbsp;类别：西安市 
	  <select>
	  	<option>--请选择--</option>
	  	<option>企业</option>
	  	<option>事业</option>
	  </select>
	  <select>
	  	<option>--请选择--</option>
	  	<option>退休</option>
	  	<option>在职</option>
	  </select>
	  &nbsp;住院号：<input name=""  type="text" size="10"  value=""  id=""/>
	  &nbsp;次数：<input name=""  type="text" size="5"  value=""  id=""/>
	  &nbsp;科室：
	  <select>
	  	<option>--请选择--</option>
	  	<option>呼吸血液科</option>
	  	<option>神经科</option>
	  </select>
	   <hr>
	  &nbsp; 入院日期：<input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-M-d'})" size="13" class="Wdate" name="sTime"  type="text" value="${sTime }"/>
	  &nbsp; 出院日期：<input  onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-M-d'})" size="13" class="Wdate" name="sTime"  type="text" value="${eTime }"/>
	   &nbsp;天数：<input name=""  type="text" size="5"  value=""  id="" readonly="readonly"/>
	   &nbsp;出院诊断：<input name=""  type="text" size="21"  value=""  id=""/>
	  &nbsp;治疗结果：<input name=""  type="text" size="11"  value=""  id=""/>
	   
	   <br>
	  <br>
	  &nbsp; 登记号：<input name=""  type="text" size="10"  value=""  id=""/>
	  &nbsp;结算类别：
	  <select>
	  	<option>--请选择--</option>
	  	<option>定额结算</option>
	  	<option>常规定额1</option>
	  </select>
	  &nbsp; 结算号：<input name=""  type="text" size="10"  value=""  id=""/>
	  &nbsp; 住院费用：<input name=""  type="text" size="10"  value=""  id=""/>
	  &nbsp; 大写：<input name=""  type="text" size="26"  value=""  id=""/>
	  <hr>
	  &nbsp; 10%乙类药品：<input name=""  type="text" size="10"  value=""  id=""/>
	  &nbsp; 20%乙类药品：<input name=""  type="text" size="10"  value=""  id=""/>
	  &nbsp; 30%乙类药品：<input name=""  type="text" size="10"  value=""  id=""/>
	  &nbsp; 乙类药品按比例自费的费用：<input name=""  type="text" size="15"  value=""  id=""/>
	   <br>
	  <br>
	   &nbsp; 大病医保限额以上费用：<input name=""  type="text" size="13"  value=""  id=""/>
	   &nbsp;统筹基金累计支出：<input name=""  type="text" size="13"  value=""  id=""/>
	   &nbsp;本次统筹基金累计：<input name=""  type="text" size="13"  value=""  id=""/>
	   &nbsp;审核号：<input name=""  type="text" size="8"  value=""  id=""/>
	   <hr>
	  </td>
	   </tr> 
		</table>
		
		
		<table width="68%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" align="center">
		
          <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="30	%" rowspan="2" colspan="2"><span class="STYLE3">项目</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="20%" rowspan="2"><span class="STYLE3">小计</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="25%" colspan="2"><span class="STYLE3">个人支付</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  width="15%" rowspan="2" ><span class="STYLE3">统筹基金支付（元）</span><span class="STYLE3"></span><span class="STYLE3"></span><span class="STYLE3"></span><span class="STYLE3"></span></td>
           </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="10%"><span class="STYLE3">比例 </span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" width="15%"><span class="STYLE3">金额（元）</span></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  rowspan="3"><span class="STYLE4">自费费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  ><span class="STYLE4">乙类药品按比例支付</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R1"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="金额R1"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">自费药品费</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R2"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="金额R2"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">超范围服务费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R3"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name=""  type="text" size="10"  value="金额R3"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" rowspan="5"><span class="STYLE4">基本医保支付费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">起付标准</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4"> 5000 -- 10000 元</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name=""  type="text" size="10"  value="小计R5"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" >
            <select>
	  			<option>12%</option>
	 		</select>
       		</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="金额R5"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"></td>
            </tr>
           
            <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4"> 10000 -- 20000 元 </span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R6"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" >
		<select>
	  	<option>8%</option>
	 	</select>
       </td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name=""  type="text" size="10"  value="金额R6"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><input name=""  type="text" size="15"  value="统筹R6"  id=""/></td>
            </tr>
            
            
            <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">20000 -- 统筹支付最高限 </span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R7"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" >
		<select>
	  	<option>8%</option>
	 	</select>
       </td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name=""  type="text" size="10"  value="金额R7"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><input name=""  type="text" size="15"  value="统筹R7"  id=""/></td>
            </tr>
            
            
            <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">特检特治特药费用 </span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R8"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" >
		<select>
	  	<option>30%</option>
	 	</select>
       </td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name=""  type="text" size="10"  value="金额R8"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><input name=""  type="text" size="15"  value="统筹R8"  id=""/></td>
            </tr>
            
            
            <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" rowspan="2"><span class="STYLE4">大病医保支付费用</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">按比例支付</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"></td>
            </tr>
            
            <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><span class="STYLE4">特检特治特药费用 </span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R10"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name=""  type="text" size="10"  value="金额R10"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><input name=""  type="text" size="15"  value="统筹R10"  id=""/></td>
            </tr>
            
            
            <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  colspan="2"><span class="STYLE4">大病医保限额以上费用（自费）</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R11"  id=""/></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF"><input name=""  type="text" size="10"  value="金额R11"  id=""/>&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="3"><input name=""  type="text" size="15"  value="统筹R11"  id=""/></td>
            </tr>
           
           <tr>
            <td  height="22" align="center"  bgcolor="#FFFFFF"  colspan="2"><span class="STYLE4">合计</span></td>
            <td  height="22" align="center"  bgcolor="#FFFFFF" ><input name=""  type="text" size="10"  value="小计R12"  id=""/></td>
            <td  height="22" align=right  bgcolor="#FFFFFF"  colspan="2"><input name=""  type="text" size="10"  value="金额R12"  id=""/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td  height="22" colspan="3" align="center"  bgcolor="#FFFFFF"><input name=""  type="text" size="15"  value="统筹R12"  id=""/><span class="STYLE4"></span></td>
            </tr>
           
           
           <tr>
           <td  height="22" align="center"  bgcolor="#FFFFFF" colspan="9">
           <input type="button"  value="确定" class="btn_mouseout"  onclick="document.forms.form2.submit();">
	    <input type="reset"  value="重置" class="btn_mouseout">
           </td>
           </tr>
           
		 </table>
		
		
		</form></td>	
		
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
        <td><%@include file="/common/page.jsp" %></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>