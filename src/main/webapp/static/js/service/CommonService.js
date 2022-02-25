'use strict';

App.factory('CommonService', [
		'$http',
		'$q',
		'restUrl',
		'$stateParams',
		function($http, $q, restUrl, $stateParams) {
			var REST_SERVICE_URI = restUrl + 'api';
			var entityList = [];
			var entityData = {};
			var count = {};
			var factory = {
				fetch : fetch,//This will be for fetching the list
				search : search,
				searchEntities : searchEntities,
				detail : detail,
				detailForm:detailForm,
				update : update,
				save : save,
				ajax : ajax,
				getEntityList : getEntityList,
				getEntity : getEntity,
				countFunction : countFunction,
				getCount : getCount,
				changePassword :changePassword,
				deleteEntity : deleteEntity
	
			};
			return factory;

			function fetch(entity, pageNo) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/list' 
								+ '/get/' + pageNo + '/100').success(
						function(data) {
							entityList = data;
							deferred.resolve(data);
						}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}

			function save(entitySave, entity ) {
				var deferred = $q.defer();

				$http.post(REST_SERVICE_URI + entity + '/add',
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			function changePassword(password) {
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + 'login/changePassword', password)
					.success(function(data) {
						deferred.resolve(data);
					}).error(function(status) {
						deferred.reject(status);
					});
				return deferred.promise;
			}//
			
			
			
			

			function changePassword(password) {
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + 'login/changePassword', password)
					.success(function(data) {
						deferred.resolve(data);
					}).error(function(status) {
						deferred.reject(status);
					});
				return deferred.promise;
			}//
			
			
			
			

			function detail(entity, detailId) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/detail/' + detailId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}
			function detailForm(entity, fy, branchCode, detailId) {
				var deferred = $q.defer();
				$http.get(
					REST_SERVICE_URI + entity + '/detail/' + fy + "/" + branchCode + "/" + detailId).success(function(data) {
						entityData = data;
						entityList= data;
						deferred.resolve(data);
					}).error(function(status) {
						deferred.reject(status);
					});

				return deferred.promise;

			}

			function search(entity, map) {
				var deferred = $q.defer();
				$http
						.post(
								REST_SERVICE_URI + entity + '/searchEntity/'
										+ map).success(
								function(data) {
									entityData = data;
									deferred.resolve(data);
								}).error(function(status) {
							deferred.reject(status);
						});

				return deferred.promise;

			}

			function searchEntities(entity, map) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/search/get/0/100/'+map).success(function(data) {
					/*count = 0;-pranay*/
					count = 0;
					/*resultPerPage = 10;*/
					/*entityList = data;--Pranay*/ 
					entityList = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}
			/*//Pranay*/
			function ajax(entity, ajax) {
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + entity + '/ajax' ,ajax).success(function(data) {
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}

			function getEntityList() {
				return entityList;
			}

			function getEntity() {
				return entityData;
			}

			function update(entitySave, entity) {
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/update', 
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
		
			function countFunction(entity ) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/list/count/').success(function(data) {
					count = data.count;
					entityList = data.entities;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});
				return deferred.promise;
			}
			
			function getCount() {
				return count;
			}

			function deleteEntity( entity, deleteId) {
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/delete/' +  deleteId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

		} ]);
