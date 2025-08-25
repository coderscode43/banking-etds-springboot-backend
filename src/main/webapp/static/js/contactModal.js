function showModal (options) {
	var defaults = {
		type: "alert", //alert, prompt,confirm 
		modalSize: 'modal-sm', //modal-sm, modal-lg
		messageText: "Message",
		alertType: "default", //default, primary, success, info, warning, danger
	}
	$.extend(defaults, options);
  
	var _show = function(){
		var headClass = "navbar-default";	
		$('BODY').append(
			'<div id="contactModal" class="modal fade">' +
			'<div class="modal-dialog" class="' + defaults.modalSize + '">' +
			'<div class="modal-content" style="top: 40px;">' +
			'<div id="contactModal-body" class="modal-body">' +
			'<center>' +
			'<div id="contactModal-message" >Thank you for contacting with us!<br><br>' +
			'We will back to you soon..<br>' +
			'</div>' +
			'</center>' +
			'</div>' +
			'</div>' +
			'</div>' +
			'</div>'
		);
  

		var keyb = "false", backd = "static";
		
		$('#contactModal').modal({ 
          show: false, 	
          backdrop: backd, 
          keyboard: keyb 
        }).modal('show');
		setTimeout(function() {
			$('#contactModal').remove();
			$('.modal-backdrop').remove();
		},2000);
	}
    
  _show(); 
}
