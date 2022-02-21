'use strict';
var App = angular.module('myApp', ['ui.router', 'angularUtils.directives.dirPagination', 'ngMaterial', 'ngMessages', 'ng.httpLoader']);
App.value('restUrl', this.window.location.protocol + '//' + this.window.location.hostname + ':' + this.window.location.port + '/bankingETDS/');
//https://www.taxosmart.com/dv/
//https://localhost:8443/DocumentVerification/
//http://159.65.157.15:8080/DocumentVerification/
App.filter('sumByKey', function() {
	return function(data, key) {
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

App.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {






	$urlRouterProvider.otherwise("/home/homepage")

	$stateProvider
		.state('logout', {
			url: "/logout",
			templateUrl: 'index/logout',
			controller: "CommonController as cCctr"

		})
	$stateProvider
		.state('signIn', {
			url: "/signIn",
			templateUrl: 'index/home/login',
			controller: "HomeController as hctr"

		})


		.state('resetPass', {
			url: "/resetPass",
			templateUrl: 'index/resetPass',
			controller: "CommonController as cCctr"

		}

		)


		//Home that contains Side bar and header
		.state('home', {
			url: "/home",
			templateUrl: 'index/home',
			controller: "CommonController as cCctr"

		}
		)

		//HomePage that is main homePage
		.state('home.homepage', {
			url: "/homepage",
			templateUrl: 'index/homePage/',


		})


		//Get Details detail

		.state('home.detail', {
			url: "/detail/:entity/:detailId/:page",
			params: {
				detailId: null,
				entity: null,
				page: null
			},
			templateUrl: function($stateParams) {
				return 'index/detail/homeSC/' + $stateParams.page;
			},
			resolve: {
				list: function($q, $state, CommonService, $stateParams) {
					console.log('Get Detail  ' + $stateParams.detailId);
					var deferred = $q.defer();
					CommonService.detail($stateParams.clientId, $stateParams.entity, $stateParams.detailId).then(deferred.resolve, deferred.resolve);
					return deferred.promise;
				}
			}

		})




		.state('home.list', {
			url: "/list/:entity/:page",
			params: {
				entity: null,
				page: null
			},
			templateUrl: function($stateParams) {
				return 'index/list/homeSC/'  + $stateParams.page;
			},
			resolve: {
				list: function($q, $state, CommonService, $stateParams) {
					console.log('Get List of ' + $stateParams.entity);
					var deferred = $q.defer();
					CommonService.countFunction($stateParams.entity, $stateParams.clientId).then(deferred.resolve, deferred.resolve);
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
					return 'index/list/homeSC/'+$stateParams.entity +'/'+$stateParams.page;
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
		
		
		
		
		
		
		
		

		.state('homeWot', {
			url: "/homeWot/:branchCode/:fy",
			params: {
				branchCode: null,
				fy: null,
			},
			templateUrl: function($stateParams) {
				return 'index/homeWot/' + $stateParams.fy;
			},
			controller: "CommonController as cCctr"

		}
		)

		//HomePage that is main homePage
		.state('homeWot.homepage', {
			url: "/homepage",
			templateUrl: 'index/homePageWOT/',


		})


		//Get Details detail

		.state('homeWot.detail', {
			url: "/detail/:entity/:detailId/:page",
			params: {
				detailId: null,
				entity: null,
				page: null
			},
			templateUrl: function($stateParams) {
				return 'index/detail/homeWOT/' + $stateParams.page;
			},
			resolve: {
				list: function($q, $state, CommonService, $stateParams) {
					console.log('Get Detail  ' + $stateParams.detailId);
					var deferred = $q.defer();
					CommonService.detail($stateParams.clientId, $stateParams.entity, $stateParams.detailId).then(deferred.resolve, deferred.resolve);
					return deferred.promise;
				}
			}

		})




		.state('homeWot.list', {
			url: "/list/:entity/:page",
			params: {
				entity: null,
				page: null
			},
			templateUrl: function($stateParams) {
				return 'index/list/homeWOT/' + $stateParams.page;
			},
			resolve: {
				list: function($q, $state, CommonServiceFY, $stateParams) {
					console.log('Get List of ' + $stateParams.entity);
					var deferred = $q.defer();
					CommonServiceFY.countFunction($stateParams.entity, $stateParams.fy, $stateParams.branchCode).then(deferred.resolve, deferred.resolve);
					return deferred.promise;
				}
			}

		})
		
		
		.state('homeWot.search',{
			url : "/listSearch/:entity/:page/:searchParams",
			params: {
				entity: null,
				page:null,
				searchParams:null
			   },
			   templateUrl : function($stateParams) {
					return 'index/list/homeWOT/'+$stateParams.entity +'/'+$stateParams.page;
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





}]);


App.config([
	'httpMethodInterceptorProvider',
	function(httpMethodInterceptorProvider) {
		// ...
		httpMethodInterceptorProvider.whitelistLocalRequests();
		// ...
	}
])




