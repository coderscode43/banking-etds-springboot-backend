'use strict';
 
App.factory('LoginService', ['$http', '$q', 'restUrl',function($http, $q,restUrl){
	var REST_SERVICE_URI = restUrl+'login/';
    var factory = {
    		authenticate: authenticate
    };
    return factory;
 
    function authenticate(credentials) {
    	console.log('Logging Inn');
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, credentials)
            .then(
                function (response) {
                	console.log('Successfully logged Inn : ');
                	deferred.resolve(response.data);
                	},
                function (errResponse) {
                   console.error('Error while logging in : '+errResponse.data.errorMessage);
                   deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);