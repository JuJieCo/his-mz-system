<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Autocomplete</title>

<script type="text/javascript" src="../resource/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../resource/js/jquery.autocomplete.min.js"></script>
<link rel="Stylesheet" href="../resource/css/jquery.autocomplete.css" />

</head>
<body>

<script type="text/javascript">
    $(function() {
        var data = "Core Selectors Attributes Traversing Manipulation CSS Events Effects Ajax Utilities".split(" ");

        $('#keyword').autocomplete(data).result(function(event, data, formatted) {
            alert(data);
        });
    });
</script>



<form id="form1" runat="server">
	<div>
	    <input id="keyword" />
	    <input id="getValue" value="GetValue" type="button" />
	</div>
</form>
 
 
<script type="text/javascript">
    var emails = [
        { name: "Peter Pan", complate:"Peter Pan",to: "peter@pan.de" },
        { name: "Molly", complate:"",to: "molly@yahoo.com" },
        { name: "Forneria Marconi", complate:"Forneria Marconi",to: "live@japan.jp" },
        { name: "Master <em>Sync</em>", complate:"Master <em>Sync</em>",to: "205bw@samsung.com" },
        { name: "Dr. <strong>Tech</strong> de Log", complate:"Dr. <strong>Tech</strong> de Log",to: "g15@logitech.com" },
        { name: "阿莫西林 1mg*10 盒",complate:"阿莫西林 amxl",to: "12" },
        { name: "阿莫西林-健身卡 1mg*10 盒",complate:"阿莫西林-健身卡 jsk",to: "23" }
    ];

        $(function() {
            $('#keyword2').autocomplete(emails, {
                max: 12,    //列表里的条目数
                minChars: 0,    //自动完成激活之前填入的最小字符
                width: 400,     //提示的宽度，溢出隐藏
                scrollHeight: 300,   //提示的高度，溢出显示滚动条
                matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
                autoFill: false,    //自动填充
                formatItem: function(row, i, max) {
                    return i + '/' + max + ':"' + row.name + '"[' + row.to + ']';
                },
                formatMatch: function(row, i, max) {
                    return row.complate;
                },
                formatResult: function(row) {
                    return row.to;
                }
            }).result(function(event, row, formatted) {
                alert(row.to);
            });
        });
</script>

<form id="form2" runat="server">
	<div>
	    <input id="keyword2" />
	    <input id="getValue" value="GetValue" type="button" />
	</div>
</form>

</body>
</html>