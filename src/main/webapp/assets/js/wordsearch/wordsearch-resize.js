$(function() {
		var liWidth = $("li").css("width");
		$("li").css("height",liWidth);
		$("li").css("lineHeight",liWidth);
		var totalHeight = $("#wordsearch").css("width");
		$("#wordsearch").css("height",totalHeight);
	});
	
	causeRepaintsOn = $("h1, h2, h3, p");
	
	$(window).resize(function() {
	  causeRepaintsOn.css("z-index", 1);
	});
	
	$(window).on('resize', function(){
		var liWidth = $(".one").css("width");
		$("li").css("height",liWidth);
		$("li").css("lineHeight",liWidth);
		var totalHeight = $("#wordsearch").css("width");
		$("#wordsearch").css("height",totalHeight);
	});