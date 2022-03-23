'use strict';

App.factory('CommonServiceFY', [
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
			fetch: fetch,//This will be for fetching the list
			search: search,
			searchEntities: searchEntities,
			detail: detail,
			update: update,
			save: save,
			ajax: ajax,
			//deleteEntity:deleteEntity,
			getEntityList: getEntityList,
			updateStatus: updateStatus,
			getEntity: getEntity,
			countFunction: countFunction,
			getCount: getCount,
			deleteEntity: deleteEntity,
			importData: importData,
			getFile: getFile,
			approveUpdate: approveUpdate,
			rejectUpdate: rejectUpdate,
			add:add,
			check:check,
			downloadCertificate : downloadCertificate

		};
		return factory;

		

		function save(entitySave, entity) {
			var deferred = $q.defer();

			$http.post(REST_SERVICE_URI + entity + '/add/'+$stateParams.fy + '/' + $stateParams.branchCode,
				entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}
		
		function check(url) {
			var deferred = $q.defer();

			$http.get(url).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}
		
		function fetch(entity, fy, branchCode, pageNo) {
			entityList = [];
			var deferred = $q.defer();
			$http.get(
				REST_SERVICE_URI + entity + '/list/' + $stateParams.fy + '/' + $stateParams.branchCode
				+ '/get/' + $stateParams.pageNo + '/100').success(
					function(data) {
						entityList = data;
						deferred.resolve(data);
					}).error(function(status) {
						deferred.reject(status);
					});

			return deferred.promise;
		}

		function detail(entity, detailId, branchCode, fy) {
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

		function search(fy, entity, map, branchCode) {
			var deferred = $q.defer();
			$http
				.post(
					REST_SERVICE_URI + entity + '/searchEntity/'
					+ fy + '/' + branchCode, map).success(
						function(data) {
							entityData = data;
							deferred.resolve(data);
						}).error(function(status) {
							deferred.reject(status);
						});

			return deferred.promise;

		}
		function add(entitySave, entity, type, fy, branchCode) {
			var deferred = $q.defer();

			$http.post(REST_SERVICE_URI + entity + '/add/' + $stateParams.fy + '/' + $stateParams.branchCode +'/'+ type,
				entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}


		function searchEntities(fy, entity, map, branchCode) {
			entityList = [];
			var deferred = $q.defer();
			$http.get(
				REST_SERVICE_URI + entity + '/search/' + fy + '/' + branchCode + '/'
				+ map).success(function(data) {
					count = 0;
					entityList = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;

		}

		function ajax(entity, ajax, fy, branchCode) {
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/ajax/' + fy + '/' + branchCode,
				ajax).success(function(data) {
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

		function update(entitySave, entity, fy, branchCode) {
			var deferred = $q.defer();

			$http.put(REST_SERVICE_URI + entity + '/update/' + fy + '/' + branchCode,
				entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function approveUpdate(entitySave, entity, fy, branchCode) {
			entitySave.fy = fy;
			var deferred = $q.defer();

			$http.put(REST_SERVICE_URI + entity + '/approve/' + fy + '/' + branchCode,
				entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function rejectUpdate(entitySave, entity, fy, branchCode) {
			entitySave.fy = fy;
			var deferred = $q.defer();

			$http.put(REST_SERVICE_URI + entity + '/reject/' + fy + '/' + branchCode,
				entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function updateStatus(entitySave, entity, fy, branchCode) {
			var deferred = $q.defer();

			$http.put(REST_SERVICE_URI + entity + '/updateStatus/' + fy + '/' + branchCode,
				entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function countFunction(entity, fy, branchCode) {
			entityList = [];
			var deferred = $q.defer();
			$http.get(
				REST_SERVICE_URI + entity + '/list/' + fy + '/' + branchCode
				+ '/count/').success(function(data) {
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

		function deleteEntity(fy, entity, deleteId, branchCode) {
			var deferred = $q.defer();
			$http.post(
				REST_SERVICE_URI + entity + '/delete/' + fy + '/' + branchCode + '/'
				+ deleteId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;

		}

		function importData(entitySearch, fy, entity, type, branchCode) {
			var deferred = $q.defer();
			$http.get(
				REST_SERVICE_URI + entity + '/import/' + fy + '/' + branchCode + '/'
				+ type, entitySearch).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;

		}

		function getFile(fy, entity, detailId, branchCode) {
			var deferred = $q.defer();
			$http.get(
				REST_SERVICE_URI + entity + '/getFile/' + fy + '/' + branchCode + '/'
				+ detailId, { responseType: 'arraybuffer' }).success(function(data) {
					var file = new Blob([response], { type: 'application/pdf' });
					var fileURL = URL.createObjectURL(file);
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;

		}
		
		function downloadCertificate(deductee,certificate,fy,q,pan){
			
			var deferred = $q.defer();
			$http.get(
				'apichallan/files/'+deductee+'/'+certificate+'/'+fy+'/'+q+'/'+pan).success(function(data) {
					var file = new Blob([response], { type: 'application/pdf' });
					var fileURL = URL.createObjectURL(file);
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

	}]);
