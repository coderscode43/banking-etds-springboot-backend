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

			function fetch(entity, fy, pageNo) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/list/' + fy
								+ '/get/' + pageNo + '/100').success(
						function(data) {
							entityList = data;
							deferred.resolve(data);
						}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}

			function save(entitySave, entity, fy) {
				var deferred = $q.defer();

				$http.post(REST_SERVICE_URI + entity + '/add/' + fy,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}

			function detail(fy, entity, detailId) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/detail/' + fy + '/'
								+ detailId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function search(fy, entity, map) {
				var deferred = $q.defer();
				$http
						.post(
								REST_SERVICE_URI + entity + '/searchEntity/'
										+ fy, map).success(
								function(data) {
									entityData = data;
									deferred.resolve(data);
								}).error(function(status) {
							deferred.reject(status);
						});

				return deferred.promise;

			}

			function searchEntities(fy, entity, map) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/search/' + fy + '/'
								+ map).success(function(data) {
					count = 0;
					entityList = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function ajax(entity, ajax, fy) {
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + entity + '/ajax/' + fy,
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

			function update(entitySave, entity, fy) {
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/update/' + fy,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			function approveUpdate(entitySave, entity, fy){
				entitySave.fy = fy;
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/approve/' + fy,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			function rejectUpdate(entitySave, entity, fy){
				entitySave.fy = fy;
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/reject/' + fy,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			
			
			
			function updateStatus(entitySave, entity, fy) {
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/updateStatus/' + fy,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			
			
			
			
			
			

			function download(fy, entity, map, fileName) {
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/download/' + fileName
								+ '/' + fy, map, {
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

			function countFunction(entity, fy) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/list/' + fy
								+ '/count/').success(function(data) {
					count = data.count;
					entityList = data.entities;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});
				return deferred.promise;
			}
			
			function countForBranchFunction(entity, fy,barchCode) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/listBranch/' + fy + barchCode +'/count/').success(function(data) {
					count = data.count;
					entityList = data.entities;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});
				return deferred.promise;
			}
			function countForFyFunction(entity, fy) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/listFy/' + fy+'/count/').success(function(data) {
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

			function deleteEntity(fy, entity, deleteId) {
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/delete/' + fy + '/'
								+ deleteId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function importData(entitySearch, fy, entity, type) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/import/' + fy + '/'
								+ type, entitySearch).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}
			function saveDoc(entitySave, entity, fy) {
				entitySave.dec.fy=fy;
				var dat = new FormData();
				if(entitySave.file==null || entitySave.file==undefined){
				return save(entitySave.dec, entity, fy)
				}else{
				      dat.append('file', entitySave.file)
				dat.append('dec', JSON.stringify(entitySave.dec));
				
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/uploadFile/' + fy,
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
			function updateDoc(entitySave, entity, fy) {
				entitySave.dec.fy=fy;
				var datt = new FormData();
				if(entitySave.file==null || entitySave.file==undefined){
				return update(entitySave.dec, entity, fy)
				}else{
					datt.append('file',entitySave.file)
				 
				datt.append('dec', JSON.stringify(entitySave.dec));
				
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/updateFile/' + fy,
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
			
			function getFile(fy, entity, detailId) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/getFile/' + fy + '/'
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
