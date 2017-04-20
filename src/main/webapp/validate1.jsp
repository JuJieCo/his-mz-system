<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'validate1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/jquery.validate.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery.messages_cn.js"></script>

	<script type="text/javascript">
	
	
	
$().ready(function() {

	 $("#testForm").validate({
		 errorPlacement:function(error,element) {  error.appendTo(element.parent("td"));
		 },
		 
		 
		   /*  
		    errorPlacement: function(error, element) { 
		  if (element.attr("id") == "selfvalid") {//需要特殊处理的验证元素
		         error.insertAfter("#span_mself");  //控制显示位置
		   } 
		  else{
		         error.insertAfter(element); //默认位置，可灵活改动
		   } 
		 }
		  
		 */
 
        rules: {

        
   username: "required",
   
   email: {
    required: true,
    email: true
   },
   
   password: {
    required: true,
    minlength: 5
   },
   
   confirm_password: {
    required: true,
    minlength: 5,
    equalTo: "#password"
   },
   
   age: {
   	required: true,
   	number: true,
   	min:0,
   	max:100
   },
   
   sex: {
   	required: true
   },
   
  dept: {
   	required: true
   },
   
   decimal: {
   required: true,
   number: true,
   decimal: true,
   min:0
   }
  
  },
  
            
  messages: {

    }
       
    
  /*
          messages: {
   username: "请输入姓名",
   
   email: {
    required: "请输入Email地址",
    email: "请输入正确的email地址"
   },
   
   password: {
    required: "请输入密码",
    minlength: jQuery.format("密码不能小于{0}个字 符")
   },
   
   confirm_password: {
    required: "请输入确认密码",
    minlength: "确认密码不能小于5个字符",
    equalTo: "两次输入密码不一致"
   },
   
   age:{
    required: "请输入数字",
   	number:"请输入数字"
   }

   
  }
    */

    });
});
	
	</script>

	
  </head>
  
  <body>
  <form action="" id="testForm">
  <table>
  		
  			<tr><td> 用户名：</td><td><input name="username" type="text"></td> </tr>
  			<tr><td> 密码：</td> <td><input name="password" type="text"  id="password"></td><tr>
  			<tr><td>确认密码：</td><td> <input name="confirm_password" type="text"></td><tr>
  			<tr><td>电子邮箱：</td><td>  <input name="email"  type="text" ></td><tr>
  			<tr><td>年龄：</td><td> <input name="age" type="text"></td><tr>
  			<tr><td>性别：</td><td><input name="sex" type="radio">男<input name="sex" type="radio">女</td><tr>
  			<tr><td>部门：</td><td><input name="dept" type="checkbox" >部门1<input name="dept" type="checkbox" >部门2</td></tr>
  			<tr><td>工资：</td><td><input name="decimal" type="text"></td></tr>
  			<tr><td>&nbsp;</td><td><input type="submit"></td><tr>
  		</tr>
  
  </table>
  
    

     
      
  </form>

  </body>
</html>
