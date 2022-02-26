function test(a){$(a).modal("toggle")
}$(document).keydown(function(a){if(a.keyCode==27){$("#closeModal").click()
}});
function deleteRow(b){var a=b.parentNode.parentNode.rowIndex;
document.getElementById("goodsTbl").deleteRow(a);
$("#countDecreaser").click()
}$(document).ready(function(){$("#curDate").click()
});
function getDate(){var c=new Date();
var g=c.getFullYear();
var a=c.getMonth()+1;
var f=c.getDate();
a=a/100;
f=f/100;
var e=a.toString().split(".");
var b=f.toString().split(".");
mm=e[1];
dd=b[1];
newDate=g+"-"+mm+"-"+dd;
document.getElementById("curDate").value=newDate
}$(".sub-menu li").on("click",function(){$(".sub-menu li").removeClass("active");
$(this).addClass("active")
});
function w3_open(){document.getElementById("main").style.marginLeft="25%";
document.getElementById("mySidebar").style.width="25%";
document.getElementById("mySidebar").style.display="block";
document.getElementById("openNav").style.display="none"
}function w3_close(){document.getElementById("main").style.marginLeft="0%";
document.getElementById("mySidebar").style.display="none";
document.getElementById("openNav").style.display="inline-block"
}function readMore(){var a=document.getElementById("myDIV");
var b=document.getElementById("more");
var c=b.textContent;
if(a.style.display==="none"){a.style.display="block";
b.innerHTML="Show Less"
}else{a.style.display="none";
b.innerHTML="Read More"
}}function calculateHRA(){var c=document.getElementById("bs").value;
var b=document.getElementById("da").value;
var a=document.getElementById("hra").value;
var f=document.getElementById("total").value;
var g=document.getElementsByName("isMetroPolitanCity");
for(var d=0,e=g.length;
d<e;
d++){if(g[d].checked){document.getElementById("basicSal").innerHTML=0.5*(parseInt(c)+parseInt(b));
document.getElementById("per").innerHTML="50% of Basic Salary"
}else{document.getElementById("basicSal").innerHTML=0.4*(parseInt(c)+parseInt(b));
document.getElementById("per").innerHTML="40% of Basic Salary"
}break
}document.getElementById("hraReceived").innerHTML=parseInt(a);
document.getElementById("rentPaid").innerHTML=parseInt(f)-(0.1*(parseInt(c)+parseInt(b)));
document.getElementById("exempted").innerHTML=Math.min(parseInt(document.getElementById("basicSal").innerHTML),parseInt(document.getElementById("hraReceived").innerHTML),parseInt(document.getElementById("rentPaid").innerHTML));
document.getElementById("hraChargeable").innerHTML=document.getElementById("hraReceived").innerHTML-document.getElementById("exempted").innerHTML;
document.getElementById("tax").innerHTML=document.getElementById("hraChargeable").innerHTML
}var catAndActs={};
catAndActs.Resident=["Company","Firm","AOP","BOI","Individual"];
catAndActs["Non-Resident"]=["Non-Corporate","Corporate"];
function ChangecatList(){var e=document.getElementById("validationCustom03");
var f=document.getElementById("validationCustom04");
var b=e.options[e.selectedIndex].value;
while(f.options.length){f.remove(0)
}var c=catAndActs[b];
if(c){var d;
for(d=0;
d<c.length;
d++){var a=new Option(c[d],d);
f.options.add(a)
}}}function calculateTDS(){var c=document.getElementById("fy").value;
var b=document.getElementById("validationCustom03").value;
var a=document.getElementById("validationCustom04").value;
var d=document.getElementById("section").value;
var e=document.getElementById("amount").value;
if(c==="2018-19"&&d==="192A"&&b==="Resident"){document.getElementById("tds").value=0.2*(parseInt(e));
document.getElementById("sur").value=0;
document.getElementById("cess").value=0;
document.getElementById("scess").value=0;
document.getElementById("totaltds").value=parseInt(document.getElementById("tds").value)+parseInt(document.getElementById("sur").value)+parseInt(document.getElementById("cess").value)+parseInt(document.getElementById("scess").value)
}}function calc(){var a=document.getElementById("hra");
if(a.style.display==="none"){a.style.display="block"
}else{a.style.display="none"
}}function slideIn(){$(".page-wrapper").removeClass("toggled")
}function slideOut(){$(".page-wrapper").addClass("toggled");
closeRemark()
}function openRemark(){document.getElementById("mySidenav2").style.width="30%";
document.getElementById("body1").style.backgroundColor="#c8ccc9";
document.getElementById("comment").style.backgroundColor="#c8ccc9";
slideIn()
}function closeRemark(){document.getElementById("mySidenav2").style.width="0";
document.getElementById("body1").style.backgroundColor="#fff";
document.getElementById("comment").style.backgroundColor="#fff"
}function comparePwd(){var a=document.getElementById("newPasword");
var c=document.getElementById("conformPwd");
var b=document.getElementById("errMsg");
if(a.value!=c.value){b.innerHTML="Password should match with New Password."
}else{b.innerHTML=" "
}}function myFunction(){var a=document.getElementById("newP");
if(a.type==="password"){a.type="text"
}else{a.type="password"
}}function confirmP(){var a=document.getElementById("confirmP");
if(a.type==="password"){a.type="text"
}else{a.type="password"
}}function showProgress(){$("#progressBar").modal("show");
$(".modal-header h3").html("Processing...")
}$(document).on("hidden.bs.modal","#errorMsg",function(){$(".msg").empty()
});
$(document).on("hidden.bs.modal","#successMsg",function(){$(".headingMsg").empty();
$(".msg").empty()
});
$(document).on("hidden.bs.modal","#errorMsg1",function(){$(".msg").empty()
});
function openNav(){document.getElementById("mySidenav").style.width="250px"
}function closeNav(){document.getElementById("mySidenav").style.width="0"
}function closeSideNavForModel(){$(".page-wrapper").removeClass("toggled")
};