<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
<!--
	var medicinals = [];
//-->
</script>
    
<%
	java.util.List<java.util.Map> list = new java.util.ArrayList<java.util.Map>();
	
	java.util.List<com.jujie.his.baseinfo.Medicinal> m_list = com.jujie.his.baseinfo.utils.GetAllMedicinals.getInstance().getMedicinalList(application);
	for(int i = 0 ; i < m_list.size() ; i++){
		com.jujie.his.baseinfo.Medicinal medicinal = m_list.get(i);
		java.util.Map map = new java.util.HashMap();
	    map.put("name",medicinal.getMedicinalName());
	    map.put("complate",medicinal.getMedicinalName()+medicinal.getMedicinalPycode());
	    map.put("gg",medicinal.getStandard().getStandardName());
	    map.put("dw",medicinal.getUnit().getUnitName());
	    map.put("to",medicinal.getMedicinalId());
	    list.add(map);
	}

	for(int i = 0 ; i < list.size() ; i++){
		java.util.Map map = list.get(i);
%>
		<script type="text/javascript">
		<!--
		
		medicinals.push({name:'<%=map.get("name")%>',complate:'<%=map.get("complate")%>',to:'<%=map.get("to")%>',gg:'<%=map.get("gg")%>',dw:'<%=map.get("dw")%>'});
		//-->
		</script>		
<%
	}

%>    

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery.autocomplete.min.js"></script>
<link rel="Stylesheet" href="${pageContext.request.contextPath}/resource/css/jquery.autocomplete.css" />


<script type="text/javascript">
function initMedicinalAutocom(){
	$('input[name=medicinalInfo]').autocomplete(medicinals, {
        max: 12,    //列表里的条目数
        minChars: 0,    //自动完成激活之前填入的最小字符
        width: 400,     //提示的宽度，溢出隐藏
        scrollHeight: 300,   //提示的高度，溢出显示滚动条
        matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: false,    //自动填充
        formatItem: function(row, i, max) {
            return row.name+" "+row.gg+" "+row.dw;
        },
        formatMatch: function(row, i, max) {
            return row.complate;
        },
        formatResult: function(row) {
            return row.name;
        }
    }).result(function(event, row, formatted) {
       	$("input[type=hidden]",$(this).parent()).val(row.to);
       	$(this).parent().parent().find("span").eq(0).html(row.gg);
       	$(this).parent().parent().find("span").eq(1).html(row.dw);
       	var parent = $(this).parent().parent();
       	addInventoryToDetails(parent,row.to);
    });
}

$(function() {
	initMedicinalAutocom();
});
</script>



