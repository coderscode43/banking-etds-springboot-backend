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
				update : update,
				save : save,
				ajax : ajax,
				//	        deleteEntity:deleteEntity,
				getEntityList : getEntityList,
				updateStatus : updateStatus,
				getEntity : getEntity,
				download : download,
				countFunction : countFunction,
				countForBranchFunction : countForBranchFunction,
				countForFyFunction : countForFyFunction,
				getCount : getCount,
				deleteEntity : deleteEntity,
				importData : importData,
				saveDoc : saveDoc,
				updateDoc : updateDoc,
				getFile : getFile,
				approveUpdate : approveUpdate,
				rejectUpdate : rejectUpdate
	
			};
			return factory;

			function fetch(entity, clientId, pageNo) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/list/' + clientId
								+ '/get/' + pageNo + '/100').success(
						function(data) {
							entityList = data;
							deferred.resolve(data);
						}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}

			function save(entitySave, entity, clientId) {
				var deferred = $q.defer();

				$http.post(REST_SERVICE_URI + entity + '/add/' + clientId,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}

			function detail(clientId, entity, detailId) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/detail/' + clientId + '/'
								+ detailId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function search(clientId, entity, map) {
				var deferred = $q.defer();
				$http
						.post(
								REST_SERVICE_URI + entity + '/searchEntity/'
										+ clientId, map).success(
								function(data) {
									entityData = data;
									deferred.resolve(data);
								}).error(function(status) {
							deferred.reject(status);
						});

				return deferred.promise;

			}

			function searchEntities(clientId, entity, map) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/search/' + clientId + '/'
								+ map).success(function(data) {
					count = 0;
					entityList = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function ajax(entity, ajax, clientId) {
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + entity + '/ajax/' + clientId,
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

			function update(entitySave, entity, clientId) {
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/update/' + clientId,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			function approveUpdate(entitySave, entity, clientId){
				entitySave.clientId = clientId;
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/approve/' + clientId,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			function rejectUpdate(entitySave, entity, clientId){
				entitySave.clientId = clientId;
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/reject/' + clientId,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			
			
			
			function updateStatus(entitySave, entity, clientId) {
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/updateStatus/' + clientId,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			
			
			
			
			
			

			function download(clientId, entity, map, fileName) {
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/download/' + fileName
								+ '/' + clientId, map, {
							responseType : 'blob'
						}).success(function(data) {
					console.log("Dude dude dude ");
					var url = URL.createObjectURL(new Blob([ data ]));
					var a = document.createElement('a');
					a.href = url;
					a.download = 'document_name';
					a.target = '_blank';
					a.click();
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function countFunction(entity, clientId) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/list/' + clientId
								+ '/count/').success(function(data) {
					count = data.count;
					entityList = data.entities;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});
				return deferred.promise;
			}
			
			function countForBranchFunction(entity, clientId) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/listBranch/' + clientId+'/count/').success(function(data) {
					count = data.count;
					entityList = data.entities;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});
				return deferred.promise;
			}
			function countForFyFunction(entity, clientId) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/listFy/' + clientId+'/count/').success(function(data) {
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

			function deleteEntity(clientId, entity, deleteId) {
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/delete/' + clientId + '/'
								+ deleteId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function importData(entitySearch, clientId, entity, type) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/import/' + clientId + '/'
								+ type, entitySearch).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}
			function saveDoc(entitySave, entity, clientId) {
				entitySave.dec.clientId=clientId;
				var dat = new FormData();
				if(entitySave.file==null || entitySave.file==undefined){
				return save(entitySave.dec, entity, clientId)
				}else{
				      dat.append('file', entitySave.file)
				dat.append('dec', JSON.stringify(entitySave.dec));
				
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/uploadFile/' + clientId,
						dat, {
							transformRequest: angular.identity,
				            headers: {'Content-Type': undefined}
						}).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			} }
			function updateDoc(entitySave, entity, clientId) {
				entitySave.dec.clientId=clientId;
				var datt = new FormData();
				if(entitySave.file==null || entitySave.file==undefined){
				return update(entitySave.dec, entity, clientId)
				}else{
					datt.append('file',entitySave.file)
				 
				datt.append('dec', JSON.stringify(entitySave.dec));
				
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/updateFile/' + clientId,
						datt, {
							transformRequest: angular.identity,
				            headers: {'Content-Type': undefined}
						}).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}
			}
			
			function getFile(clientId, entity, detailId) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/getFile/' + clientId + '/'
								+ detailId,{responseType:'arraybuffer'}).success(function(data) {
									var file = new Blob([response], {type: 'application/pdf'});
								       var fileURL = URL.createObjectURL(file);
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}
	
			
			
			

		} ]);
