/**
 * All calculations will done here
 */

function calTotal(r) {
	var index=r.parentNode.parentNode.rowIndex;
	var ind=index - 1;
	var cgstRt=1.5;
	var sgstRt=1.5;
	var igstRt=3;
	var total=0;
	var states1=document.getElementById("state1").value.split("-");
	var states2=document.getElementById("state2").value.split("-");
	window.state1=states1[0];
	window.state2=states2[0];
		
		var quantity=document.getElementsByClassName("quantity")[ind].value;
		var rate=document.getElementsByClassName("rate")[ind].value;
		var amt=Math.round(quantity * rate);
		document.getElementsByClassName("amount")[ind].value=amt;
	
		if(state1=='Maharashtra' && state2=='Maharashtra'){
			cgstAmt=Math.round(amt * cgstRt /100);
			sgstAmt=Math.round(amt * sgstRt /100);
			igstAmt=0;
			total=+amt.valueOf() + +cgstAmt.valueOf() + +sgstAmt.valueOf() + +igstAmt.valueOf();
			document.getElementsByClassName("cgstRate")[ind].value=cgstRt;
			document.getElementsByClassName("cgstAmt")[ind].value=cgstAmt;
			document.getElementsByClassName("sgstRate")[ind].value=sgstRt;
			document.getElementsByClassName("sgstAmt")[ind].value=sgstAmt;
			document.getElementsByClassName("igstRate")[ind].value=0;
			document.getElementsByClassName("igstAmt")[ind].value=igstAmt;
			document.getElementsByClassName("total")[ind].value=total;
		}else{
			cgstAmt=0;
			sgstAmt=0;
			igstAmt=Math.round(amt * igstRt /100);
			total=Math.round(+amt.valueOf() + +cgstAmt.valueOf() + +sgstAmt.valueOf() + +igstAmt.valueOf());
			document.getElementsByClassName("cgstRate")[ind].value=0;
			document.getElementsByClassName("cgstAmt")[ind].value=cgstAmt;
			document.getElementsByClassName("sgstRate")[ind].value=0;
			document.getElementsByClassName("sgstAmt")[ind].value=sgstAmt;
			document.getElementsByClassName("igstRate")[ind].value=igstRt;
			document.getElementsByClassName("igstAmt")[ind].value=igstAmt;
			document.getElementsByClassName("total")[ind].value=total;
		}
		calGrandTotal();
}



//This function will calculate grand Totals
function calGrandTotal(){
	//packagingCharges=pkgCharge.value;
	var subTotal=0;
	var cgstTotal=0;
	var sgstTotal=0;
	var igstTotal=0;
	var grandTotal=0;
	var amts=document.getElementsByClassName("amount");
	//var packagingCharges=document.getElementById("packagingCharges").value;
	
	for(var i=0;i<amts.length;i++){
		subTotal= subTotal + +(amts[i].value).valueOf();
	}
	//subTotal= Math.round(subTotal + +packagingCharges.valueOf());
	if(state1=='Maharashtra' && state2=='Maharashtra'){
		cgstTotal= Math.round(subTotal * 1.5 /100);
		sgstTotal= Math.round(subTotal * 1.5 /100);
		igstTotal= 0;
	}else{
		igstTotal= Math.round(subTotal * 3 /100);
		cgstTotal= 0;
		sgstTotal= 0;
	}
	
	grandTotal=Math.round(+subTotal.valueOf() + +cgstTotal.valueOf() + +sgstTotal.valueOf() + +igstTotal.valueOf());
	document.getElementById("subTotal").value=subTotal;
	document.getElementById("cgstTotal").value=cgstTotal;
	document.getElementById("sgstTotal").value=sgstTotal;
	document.getElementById("igstTotal").value=igstTotal;
	document.getElementById("grandTotal").value=grandTotal;
}