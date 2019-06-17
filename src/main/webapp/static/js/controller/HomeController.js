'use strict';
 
App.controller('HomeController', ['$scope','$state','LoginService', function($scope, $state,LoginService) {
    
	var self = this;
	self.credentials;
	self.successMessage = '';
    self.errorMessage = '';
    self.submitLogin = function(){
		self.loader = true;
		console.log('Submitting');
		LoginService.authenticate(self.credentials)
         .then(
             function (response) {
                 console.log('User successfully logged inn');
                 self.successMessage = 'User successfully logged Inn';
                 self.errorMessage='';
                 self.done = true;
                 self.credentials={};
                 
         		$state.go('home.homepage');
             },
             function (errResponse) {
                 console.error('Error while logging in');
                 self.errorMessage = 'Error while logging in : ' + errResponse.data.errorMessage;
                 self.successMessage='';
             }
         );
	
	
	
}
}

]);
