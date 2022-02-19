'use strict';
var App = angular.module('myApp', [ 'ui.router','angularUtils.directives.dirPagination','ngMaterial', 'ngMessages','ng.httpLoader']);
App.value('restUrl', this.window.location.protocol+'//'+this.window.location.hostname+':'+this.window.location.port+'/bankingETDS/');
//https://www.taxosmart.com/dv/
//https://localhost:8443/DocumentVerification/
//http://159.65.157.15:8080/DocumentVerification/
App.filter('sumByKey', function () {
    return function (data, key) {
        if (typeof (data) === 'undefined' || typeof (key) === 'undefined') {
            return 0;
        }

        var sum = 0;
        for (var i = data.length - 1; i >= 0; i--) {
            sum += parseFloat(data[i][key]);
        }

        return sum.toFixed(2);
    };
})

App.config([ '$stateProvider', '$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
	
	
	

    
    
	$urlRouterProvider.otherwise("/main")
   
	$stateProvider
	.state('logout', {
		url : "/logout",
		templateUrl : 'index/logout',		
		controller : "CommonController as cCctr"
	
		})	
	$stateProvider
	.state('signIn', {
		url : "/signIn",
		templateUrl : 'index/home/login',
		controller : "HomeController as hctr"
			
		})	
	
	.state('main', {
		url : "/main",
		templateUrl : 'index/main',
		controller : "CommonController as cCctr"
		
		}
		
	)
	
	.state('resetPass', {
		url : "/resetPass",
		templateUrl : 'index/resetPass',
		controller : "CommonController as cCctr"
		
		}
		
	)
	
		
	//Home that contains Side bar and header
	.state('home', {
		url : "/home/:clientId/:action/:branchId",
		params: {
			clientId: null,
			action: null,
			branchId:null
		   },
		templateUrl : function($stateParams) {
				return 'index/home/' + $stateParams.clientId+'/'+$stateParams.action+'/'+$stateParams.branchId; 
			},
			
		controller : "CommonController as cCctr"
	
		}
	)

	//HomePage that is main homePage
	.state('home.homepage',{
			url : "/homepage",
			templateUrl : function($stateParams) {
					return 'index/homePage/' + $stateParams.clientId+'/'+$stateParams.action;
			}
			
				
		})
		
	
			//Get Details detail
	
	.state('home.detail',{
			url : "/detail/:entity/:detailId/:page", 
			params: {
				detailId: null,
				entity: null,
				page:null
			   },
				templateUrl : function($stateParams) {
					return 'index/detail/' + $stateParams.clientId+'/'+$stateParams.action+ '/'+$stateParams.page;
				},
				resolve : {
					list : function($q, $state,CommonService,$stateParams) {
						console.log('Get Detail  '+$stateParams.detailId);
						var deferred = $q.defer();
						CommonService.detail($stateParams.clientId,$stateParams.entity,$stateParams.detailId).then(deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
			
		})
	
	
		
	
	.state('home.list',{
			url : "/list/:entity/:page", 
			params: {
				entity: null,
				page:null
			   },
				templateUrl : function($stateParams) {
					return 'index/list/' + $stateParams.clientId+'/'+$stateParams.action+'/'+$stateParams.page;
				},
				resolve : {
					list : function($q, $state,CommonService,$stateParams) {
						console.log('Get List of '+$stateParams.entity);
						var deferred = $q.defer();
						CommonService.countFunction($stateParams.entity,$stateParams.clientId).then(deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
				
		})
		.state('home.listBranch',{
			url : "/listBranch/:entity/:page", 
			params: {
				entity: null,
				page:null,
				branchId:null
			   },
				templateUrl : function($stateParams) {
					return 'index/list/' + $stateParams.clientId+'/'+$stateParams.action+'/'+$stateParams.page;
				},
				resolve : {
					list : function($q, $state,CommonService,$stateParams) {
						console.log('Get List of '+$stateParams.entity);
						var deferred = $q.defer();
						CommonService.countForBranchFunction($stateParams.entity,$stateParams.clientId,$stateParams.branchId).then(deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
				
		})
		.state('home.listFy',{
			url : "/listFy/:entity/:page", 
			params: {
				entity: null,
				page:null
			   },
				templateUrl : function($stateParams) {
					return 'index/list/' + $stateParams.clientId+'/'+$stateParams.action+'/'+$stateParams.page;
				},
				resolve : {
					list : function($q, $state,CommonService,$stateParams) {
						console.log('Get List of '+$stateParams.entity);
						var deferred = $q.defer();
						CommonService.countForFyFunction($stateParams.entity,$stateParams.clientId).then(deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
				
		})
		.state('home.search',{
			url : "/listSearch/:entity/:page/:searchParams",
			params: {
				entity: null,
				page:null,
				searchParams:null
			   },
			   templateUrl : function($stateParams) {
					return 'index/list/' + $stateParams.clientId+'/'+$stateParams.action +'/'+$stateParams.page;
				},
				resolve : {
					list : function($q, $state,CommonService,$stateParams) {
						console.log('Get Search List of '+$stateParams.entity);
						var deferred = $q.defer();
						CommonService.searchEntities($stateParams.clientId,$stateParams.entity,$stateParams.searchParams).then(deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
				
			
		})
		
		
		
		.state('home.search.detail2',{
			url : "/searchDetail/:entity2/:detailId/:page2", 
			params: {
				entity2: null,
				detailId: null,
				page2:null
				
			   },
				templateUrl : function($stateParams) {
					return 'index/detail/' + $stateParams.clientId+'/'+$stateParams.action+'/'+$stateParams.page2;
				},
				resolve : {
					list : function($q, $state,CommonService,$stateParams) {
						console.log('Get Detail  '+$stateParams.detailId);
						var deferred = $q.defer();
						CommonService.detail($stateParams.clientId,$stateParams.entity2,$stateParams.detailId).then(deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
				
		})
	


			.state('home.list.list2',{
			url : "/list2/:entity2/:page2/:searchParams",
			params: {
				searchParams: null,
				entity2: null,
				page2:null
			   },
				templateUrl : function($stateParams) {
					return 'index/list/' + $stateParams.clientId+'/'+$stateParams.action+ '/'+$stateParams.page2;
				},
				resolve : {
					list : function($q, $state,CommonService,$stateParams) {
						console.log('Get Search List of '+$stateParams.entity2);
						var deferred = $q.defer();
						CommonService.searchEntities($stateParams.clientId,$stateParams.entity2,$stateParams.searchParams).then(deferred.resolve, deferred.resolve);
						return deferred.promise;
					}
				}
				
		})

		
			
	} ]);


App.config([
	  'httpMethodInterceptorProvider',
	  function (httpMethodInterceptorProvider) {
	    // ...
	    httpMethodInterceptorProvider.whitelistLocalRequests();
	    // ...
	  }
	])

	
	

