/**
 
  * 
  * 
  * Other functions defined in this file
 */

//this fn will open sidebar model
function test(modalID) {
    $(modalID).modal("toggle");
}
function showProgress(){
    $('#progressBar').modal("show");
    $('.modal-header h3').html('Processing...')
};
//this fn will close sidebar model on escape
$(document).keydown(function(event){
	 if (event.keyCode == 27) { 
		 $('#closeModal').click();
	    }
});

//this fn will delete the table row
function deleteRow(r){
    var i=r.parentNode.parentNode.rowIndex;
	document.getElementById('goodsTbl').deleteRow(i);
	$('#countDecreaser').click();
}

$(document).ready(function() {
	$('#curDate').click();
});



//this fn will display current date
function getDate() {
	var curDate=new Date();
	var yyyy = curDate.getFullYear();
	var m = curDate.getMonth()+1; // getMonth() is zero-based
	var d  = curDate.getDate();
	m=m/100;
	d=d/100;
	var mon=m.toString().split('.');
	var day=d.toString().split('.');
	mm=mon[1];
	dd=day[1];
	newDate=yyyy +"-"+ mm +"-"+ dd;
	document.getElementById("curDate").value = newDate;
};


$(".sub-menu li").on("click", function() {
    $(".sub-menu li").removeClass("active");
    $(this).addClass("active");
  });

function w3_open() {
	  document.getElementById("main").style.marginLeft = "25%";
	  document.getElementById("mySidebar").style.width = "25%";
	  document.getElementById("mySidebar").style.display = "block";
	  document.getElementById("openNav").style.display = 'none';
	}
function w3_close() {
	  document.getElementById("main").style.marginLeft = "0%";
	  document.getElementById("mySidebar").style.display = "none";
	  document.getElementById("openNav").style.display = "inline-block";
	}


//function for read more - show less
function readMore() {
	  var x = document.getElementById("myDIV");
	  var more = document.getElementById("more");
	  var text = more.textContent;
	  
	  if (x.style.display === "none") {
		    x.style.display = "block";
		    more.innerHTML = "Show Less";
		  } else {
		    x.style.display = "none";
		    more.innerHTML = "Read More";
		  }
	}
//////////////////function for hra calculator\\\\\\\\\\\\\\\\\\\\\\\\\\\\
 function calculateHRA()
{
	
    var bs = document.getElementById('bs').value;
 
    var da = document.getElementById('da').value; 
    
    var hra = document.getElementById('hra').value;
    
    var total = document.getElementById('total').value;
    var radios = document.getElementsByName('isMetroPolitanCity');

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
        	 document.getElementById('basicSal').innerHTML=0.5*(parseInt(bs)+parseInt(da)); 
              document.getElementById('per').innerHTML="50% of Basic Salary";
        }
        else {
        		 document.getElementById('basicSal').innerHTML=0.4*(parseInt(bs)+parseInt(da)); 
        		 document.getElementById('per').innerHTML="40% of Basic Salary";
        	 }

          
            break;
        }
    document.getElementById('hraReceived').innerHTML= parseInt(hra);
    document.getElementById('rentPaid').innerHTML =parseInt(total)-( 0.1*(parseInt(bs)+parseInt(da)));
    document.getElementById('exempted').innerHTML = Math.min(parseInt( document.getElementById('basicSal').innerHTML),parseInt(document.getElementById('hraReceived').innerHTML),parseInt(document.getElementById('rentPaid').innerHTML));
    document.getElementById('hraChargeable').innerHTML= document.getElementById('hraReceived').innerHTML- document.getElementById('exempted').innerHTML;
    document.getElementById('tax').innerHTML= document.getElementById('hraChargeable').innerHTML;
   }



/////////////////////////function for tds calculator\\\\\\\\\\\\\\\\\\\\\\\\\
 
 var catAndActs = {};
 catAndActs['Resident'] = ['Company', 'Firm', 'AOP', 'BOI', 'Individual'];
 catAndActs['Non-Resident'] = ['Non-Corporate', 'Corporate'];
 
 function ChangecatList() {
	    var catList = document.getElementById("validationCustom03");
	    var actList = document.getElementById("validationCustom04");
	    var selCat = catList.options[catList.selectedIndex].value;
	    while (actList.options.length) {
	        actList.remove(0);
	    }
	    var cats = catAndActs[selCat];
	    if (cats) {
	        var i;
	        for (i = 0; i < cats.length; i++) {
	            var cat = new Option(cats[i], i);
	            actList.options.add(cat);
	        }
	    }
	} 

 function calculateTDS()
 {
	    var fy = document.getElementById('fy').value;
	 
	    var rs = document.getElementById('validationCustom03').value;
	    
	    var rt = document.getElementById('validationCustom04').value;	 
	    
	    var section = document.getElementById('section').value;
	    
	    var amt = document.getElementById('amount').value;
	    if(fy==="2018-19" && section==="192A" && rs==="Resident")
	    	{
	    	 // alert("Cool");
	    	 document.getElementById('tds').value=0.2*(parseInt(amt));
	    	 document.getElementById('sur').value=0;
	    	 document.getElementById('cess').value=0;
	    	 document.getElementById('scess').value=0;
	    	 document.getElementById('totaltds').value=parseInt( document.getElementById('tds').value)+parseInt( document.getElementById('sur').value)+
	    	 parseInt( document.getElementById('cess').value)+parseInt( document.getElementById('scess').value);
	    	}
	 }
 
 
 //////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
 
 function calc() {
	  var x = document.getElementById("hra");
	  if (x.style.display === "none") {
		    x.style.display = "block";
		  } else {
		    x.style.display = "none";
		  }
	}



 /* function for open and close sidenavbar*/
 function slideIn(){
 	$(".page-wrapper").removeClass("toggled");
 }
     	
 function slideOut(){
 	$(".page-wrapper").addClass("toggled");
 	closeRemark();
 }
    

 
 
 
 
 function openRemark() {
		document.getElementById("mySidenav2").style.width = "30%";
		document.getElementById("body1").style.backgroundColor = "#c8ccc9";
		document.getElementById("comment").style.backgroundColor = "#c8ccc9";
		
		slideIn();
	}
	function closeRemark() {
		document.getElementById("mySidenav2").style.width = "0";
		document.getElementById("body1").style.backgroundColor = "#fff";
		document.getElementById("comment").style.backgroundColor = "#fff";
		

	}



//function compares new password and conform password 
function comparePwd() {
	  var newPasword = document.getElementById("newPasword");
	  var conformPwd = document.getElementById("conformPwd");
	  var errMsg = document.getElementById("errMsg");
	  
	  if(newPasword.value != conformPwd.value){
		  errMsg.innerHTML = "Password should match with New Password.";
	  }else
		  {
		  errMsg.innerHTML = " ";
		  }
	}


function myFunction() {
	  var x = document.getElementById("newP");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}

function confirmP() {
	  var x = document.getElementById("confirmP");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}




function showProgress(){
    $('#progressBar').modal("show");
    $('.modal-header h3').html('Processing...')
};


$(document).on('hidden.bs.modal', '#errorMsg', function () { 
	$('.msg').empty(); 
	});

$(document).on('hidden.bs.modal', '#successMsg', function () { 
	$('.headingMsg').empty();
	$('.msg').empty();
	});

$(document).on('hidden.bs.modal', '#errorMsg1', function () { 
	$('.msg').empty(); 
	});


function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}

function closeSideNavForModel(){
	$(".page-wrapper").removeClass("toggled");
}
