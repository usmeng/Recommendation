/**
 * 
 */

function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("test").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "../recommendations?subtopic="+document.getElementById("topic").value, true);
  xhttp.send();
}

function mysubmit() {
	if (confirm("Press a button!") == true) {
		document.submit();
	}
	document.getElementById("result").innerHTML = "Micheal";
}