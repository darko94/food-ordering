function copyText() {
	var element = document.getElementById("display");
	var range, selection, worked;

	if (document.body.createTextRange) {
		range = document.body.createTextRange();
		range.moveToElementText(element);
		range.select();
	} else if (window.getSelection) {
		selection = window.getSelection();
		range = document.createRange();
		range.selectNodeContents(element);
		selection.removeAllRanges();
		selection.addRange(range);
	}

	try {
		document.execCommand('copy');
	} catch (err) {
		alert('unable to copy text');
	}
}
document.addEventListener('DOMContentLoaded', function() {
	// Set the date we're counting down to
	var countDownDate = document.getElementById("created").value;

	// Update the count down every 1 second
	var x = setInterval(function() {

		// Get todays date and time
		var now = new Date().getTime();

		// Find the distance between now and the count down date
		var distance = countDownDate - now;

		// Time calculations for days, hours, minutes and seconds
		// var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		// var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 *
		// 60 * 60));
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		// Output the result in an element with id="demo"
		document.getElementById("demo").innerHTML = minutes + "m " + seconds
				+ "s)";

		// If the count down is over, write some text
		if (distance < 0) {
			clearInterval(x);
			document.getElementById("demo").innerHTML = "EXPIRED)";
			// document.getElementById('submitbutton').disabled = true;
			$("#table1").fadeOut(2500);
			$("#emailButton").fadeIn(2500);
		}
	}, 1000);
});
document.addEventListener('DOMContentLoaded', function() {
	setInterval(function() {
		var id = $("#OrderID").val();
		$('#refresh').load("/group-order/" + id + " #refresh");
	}, 1000);
});
