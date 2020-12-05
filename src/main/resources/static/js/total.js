function calcTotal(input, total, classId, totalId, text) {
	var x = document.getElementById(input).value;
	var y = document.getElementById(total).value;
	if (y != '') {
		y = parseInt(y);
		y = y - text.oldvalue;
		var z = parseInt(x) + y;
	} else {
		var z = parseInt(x);
	}
	document.getElementById(total).value = z;
	var x = document.getElementsByClassName(classId);
	var totalValue = 0;
	for (var i = 0; i < x.length; i++) {

		if (!isNaN(parseInt(x[i].value))) {
			totalValue = totalValue + parseInt(x[i].value);
		}
	}
	document.getElementById(totalId).innerHTML = "Total :" + totalValue;
}
function studentNotHostel(total, isHostel, hostelFee) {
	if (isHostel == "0") {
		hostelFee = hostelFee.replace(/['"]+/g, '');
		var totalFee = document.getElementById(total).innerHTML;
		document.getElementById(total).value = totalFee - hostelFee;
	}
}
function makeReadOnly(hostel) {
	document.getElementById(hostel).readOnly = true;
}
function currentDate(date) {
	var tempDate = document.getElementById(date).value;
	if (tempDate == "") {
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();
		today = yyyy + '-' + mm + '-' + dd;
		document.getElementById(date).value = today;
	}
}
function showbox() {
	$('#modalDialog').modal('show');
}
function displayPlans(fees, totalFees) {
	document.getElementById("annual").innerHTML = '1 * ' + totalFees + ' = ' + totalFees;
	if (totalFees != 0) {
		var x = document.getElementsByClassName("planViewBody");
		for (var i = 0; i < x.length; i++) {	
				x[i].style.display = "unset";
				x[i].style.display = "block";
		}
		document.getElementById("feesLink").style.display = "none";
		if ((totalFees / 3) % 1 == 0) {
			document.getElementById("term").innerHTML = '3 * ' + totalFees / 3 + ' = ' + totalFees;
		}
		else {
			var intvalue = Math.floor(totalFees / 3);
			var txt = '2 * ' + intvalue + ' = ' + 2 * intvalue + '<br> 1 * ' +  ((totalFees) - 2 * intvalue) + ' = ' + ((totalFees) - 2 * intvalue);
			document.getElementById("term").innerHTML = txt;
		}

		if ((totalFees / 10) % 1 == 0) {
			document.getElementById("month").innerHTML = '10 * ' + totalFees / 10 + ' = ' + totalFees;;
		}
		else {
			var intvalue = Math.floor(totalFees / 10);
			var txt = '9 * ' + intvalue + ' = ' + 9 * intvalue + '<br> 1 * ' + ((totalFees) - 9 * intvalue) + ' = ' + ((totalFees) - 9 * intvalue);
			document.getElementById("month").innerHTML = txt;
		}
	}
	else {
		var x = document.getElementsByClassName("planViewBody");
		for (var i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		document.getElementById("feesLink").style.display = "unset";
	}
}
function viewPlans(fees, standard, batch) {
	var x = document.getElementById(standard).value;
	var y = document.getElementById(batch).value;
	fees = fees.replace(/^["'](.+(?=["']$))["']$/, '$1');
	fees = JSON.parse(fees);
	var totalFees = 0;
	for (var i = 0; i < fees.length; i++) {
		if (fees[i].section.standard == x && fees[i].year.year == y) {
			if (fees[i].total != null) {
				totalFees = fees[i].total;
			}
		}
	}
	displayPlans(fees, totalFees);
}
function initailizeTotal(classId, totalId) {
	var x = document.getElementsByClassName(classId);
	var totalValue = 0;
	for (var i = 0; i < x.length; i++) {
		if (!isNaN(parseInt(x[i].value))) {
			totalValue = totalValue + parseInt(x[i].value);
		}
	}
	document.getElementById(totalId).innerHTML = "Total :" + totalValue;
}