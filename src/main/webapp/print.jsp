<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<html>
	<head>
		<title></title>
	</head>

	<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resource/js/jquery.PrintArea.js" type="text/javascript"></script>
	<body>
		<input id="btnPrint" type="button" value="打印"/>
		<div id="myPrintArea">
			====打印区域====
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			//打印
			$("#btnPrint").bind("click",function(event){
				$("#myPrintArea").printArea();
			});
		});
	</script>
</html>