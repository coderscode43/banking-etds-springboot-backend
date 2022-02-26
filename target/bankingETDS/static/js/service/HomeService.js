'use strict';
 
App.factory('CommonService', ['$http', '$q', 'restUrl',function($http, $q,restUrl){
	var REST_SERVICE_URI = restUrl+'apiHome/';
    //var REST_SERVICE_URI = 'https://sample-env.wy3zsueabt.ap-south-1.elasticbeanstalk.com/apiCustomerDetail/';
    
    var factory = {
        create: create,
    };
    return factory;
 
    
    function create(customerDetail) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, customerDetail).success(function (data) {
        	
            deferred.resolve(data);
        })
        .error(function (status) {
            deferred.reject(status);
        });

    return deferred.promise;
    }
    
}]);
