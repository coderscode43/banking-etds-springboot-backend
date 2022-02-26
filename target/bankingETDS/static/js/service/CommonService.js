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

			function detail( entity, detailId) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/detail/' + 
								+ detailId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function search(, entity, map) {
				var deferred = $q.defer();
				$http
						.post(
								REST_SERVICE_URI + entity + '/searchEntity/'
										+ , map).success(
								function(data) {
									entityData = data;
									deferred.resolve(data);
								}).error(function(status) {
							deferred.reject(status);
						});

				return deferred.promise;

			}

			function searchEntities(, entity, map) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/search/' +  + '/'
								+ map).success(function(data) {
					count = 0;
					entityList = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function ajax(entity, ajax, ) {
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + entity + '/ajax/' + ,
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

			function update(entitySave, entity, ) {
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/update/' + ,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			function approveUpdate(entitySave, entity, ){
				entitySave. = ;
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/approve/' + ,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			function rejectUpdate(entitySave, entity, ){
				entitySave. = ;
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/reject/' + ,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			
			
			
			function updateStatus(entitySave, entity, ) {
				var deferred = $q.defer();

				$http.put(REST_SERVICE_URI + entity + '/updateStatus/' + ,
						entitySave).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;
			}
			
			
			
			
			
			
			

			function download(, entity, map, fileName) {
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/download/' + fileName
								+ '/' + , map, {
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

			function countFunction(entity, ) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/list/' + 
								+ '/count/').success(function(data) {
					count = data.count;
					entityList = data.entities;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});
				return deferred.promise;
			}
			
			function countForBranchFunction(entity, ,barchCode) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/listBranch/' +  + barchCode +'/count/').success(function(data) {
					count = data.count;
					entityList = data.entities;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});
				return deferred.promise;
			}
			function countForFyFunction(entity, ) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/listFy/' + +'/count/').success(function(data) {
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

			function deleteEntity(, entity, deleteId) {
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/delete/' +  + '/'
								+ deleteId).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}

			function importData(entitySearch, , entity, type) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/import/' +  + '/'
								+ type, entitySearch).success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}
			function saveDoc(entitySave, entity, ) {
				entitySave.dec.=;
				var dat = new FormData();
				if(entitySave.file==null || entitySave.file==undefined){
				return save(entitySave.dec, entity, )
				}else{
				      dat.append('file', entitySave.file)
				dat.append('dec', JSON.stringify(entitySave.dec));
				
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/uploadFile/' + ,
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
			function updateDoc(entitySave, entity, ) {
				entitySave.dec.=;
				var datt = new FormData();
				if(entitySave.file==null || entitySave.file==undefined){
				return update(entitySave.dec, entity, )
				}else{
					datt.append('file',entitySave.file)
				 
				datt.append('dec', JSON.stringify(entitySave.dec));
				
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/updateFile/' + ,
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
			
			function getFile(, entity, detailId) {
				var deferred = $q.defer();
				$http.get(
						REST_SERVICE_URI + entity + '/getFile/' +  + '/'
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
