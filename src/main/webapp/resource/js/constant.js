function TranslationTaskStatus(status){
	var name = "";
	if(typeof status == "string"){
		status = parseInt(status);
	}
	
	switch(status){
		case 0: name = "未下达";break;
		case 1: name = "已下达";break;
		case -1:name = "取消下达";break;
		case 8: name = "已完成";break;
		case -8:name = "取消完成";break;
		case 9: name = "已归档";break;
		case -9:name = "取消归档";break;
		default : name = "未知";
	}
	return name;
}

function TranslationTaskLevel(level){
	var name = "";
	if(typeof level == "string"){
		level = parseInt(level);
	}
	switch(level){
		case 0: name = "默认";break;
		case 1: name = "缓";break;
		case 2: name = "急";break;
		case 3: name = "紧急";break;
		default : name = "未知";
	}
	return name;
}

function TranslationUserRank(rank){
	var name = "";
	if(typeof rank == "string"){
		rank = parseInt(rank);
	}
	switch(rank){
		case 0: name = "未知";break;
		case 1: name = "A";break;
		case 2: name = "B";break;
		case 3: name = "C";break;
		default : name = "未知";
	}
	return name;
}

function TranslationDateColor(sdc){
	var color = "";
	if(typeof sdc == "string"){
		sdc = parseInt(sdc);
	}
	if(sdc>=0&&sdc<=3){
		return "<span style='color:red;font-size:12px'>"+sdc+"天<span>";
	}
	if(sdc>3&&sdc<=7){
		return "<span style='color:#d9a008;font-size:12px'>"+sdc+"天<span>";
	}
	if(sdc>7){
		return "<span style='color:green;font-size:12px'>"+sdc+"天<span>";
	}
	return "";
}