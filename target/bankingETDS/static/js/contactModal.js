function showModal(a){var b={type:"alert",modalSize:"modal-sm",messageText:"Message",alertType:"default",};
$.extend(b,a);
var c=function(){var e="navbar-default";
$("BODY").append('<div id="contactModal" class="modal fade"><div class="modal-dialog" class="'+b.modalSize+'"><div class="modal-content"><div id="contactModal-body" class="modal-body"><center><div id="contactModal-message" >Thank you for contacting with us!<br><br>We will back to you soon..<br></div></center></div></div></div></div>');
var d="false",f="static";
$("#contactModal").modal({show:false,backdrop:f,keyboard:d}).modal("show");
setTimeout(function(){$("#contactModal").remove();
$(".modal-backdrop").remove()
},2000)
};
c()
};