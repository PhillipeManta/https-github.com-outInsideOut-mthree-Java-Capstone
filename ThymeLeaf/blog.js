$(document).ready(function() {
	var href = "";

	console.log("ready!!");

	$(nav).click(function() {
		console.log("clicked");
		console.log(this.text());
	});

});