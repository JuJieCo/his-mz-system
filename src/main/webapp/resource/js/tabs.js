/***************************/
//@Author: Adrian "yEnS" Mato Gondelle & Ivan Guardado Castro
//@website: www.yensdesign.com
//@email: yensamg@gmail.com
//@license: Feel free to use it, but keep this credits please!					
/***************************/

$(document).ready(function(){
	$(".menu > li").click(function(e){
		switch(e.target.id){
			case "jcf":
				//change status & style menu
				$("#jcf").addClass("active");
				$("#hyf").removeClass("active");
				$("#zlf").removeClass("active");
				$("#qtfy").removeClass("active");
				//display selected division, hide others
				$("div.jcf").fadeIn();
				$("div.hyf").css("display", "none");
				$("div.zlf").css("display", "none");
				$("div.qtfy").css("display", "none");
			break;
			case "hyf":
				//change status & style menu
				$("#jcf").removeClass("active");
				$("#hyf").addClass("active");
				$("#zlf").removeClass("active");
				$("#qtfy").removeClass("active");
				//display selected division, hide others
				$("div.hyf").fadeIn();
				$("div.jcf").css("display", "none");
				$("div.zlf").css("display", "none");
				$("div.qtfy").css("display", "none");
			break;
			case "zlf":
				//change status & style menu
				$("#jcf").removeClass("active");
				$("#hyf").removeClass("active");
				$("#zlf").addClass("active");
				$("#qtfy").removeClass("active");
				//display selected division, hide others
				$("div.zlf").fadeIn();
				$("div.jcf").css("display", "none");
				$("div.hyf").css("display", "none");
				$("div.qtfy").css("display", "none");
			break;
			case "qtfy":
				//change status & style menu
				$("#jcf").removeClass("active");
				$("#hyf").removeClass("active");
				$("#zlf").removeClass("active");
				$("#qtfy").addClass("active");
				//display selected division, hide others
				$("div.qtfy").fadeIn();
				$("div.jcf").css("display", "none");
				$("div.hyf").css("display", "none");
				$("div.zlf").css("display", "none");
			break;
		}
		//alert(e.target.id);
		return false;
	});
});