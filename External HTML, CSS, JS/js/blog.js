var href = "";

$(document).ready(function() {
	

	console.log("ready!!");

	$(".nav-link").click(function() {
		var $this = $(this);
		console.log("clicked");
		href = $this.text();
		console.log(href);
	});

});