'use strict';

App.factory('CommonService', [
	'$http',
	'$q',
	'restUrl',
	'$stateParams',
	function($http, $q, restUrl, $stateParams) {
		var REST_SERVICE_URI = restUrl + 'api';
		var entityList = [];
		var resultPerPage = {};
		var entityData = {};
		var loginData = {};// Vaibhav
		var count = {};
		var factory = {
			fetch: fetch,// This will be for fetching the list
			fetchSearch: fetchSearch,
			search: search,
			searchEntities: searchEntities,
			searchTable: searchTable,
			detail: detail,
			detailForm: detailForm,
			update: update,
			save: save,
			documentSave: documentSave,
			ajax: ajax,
			deleteById: deleteById,
			getEntityList: getEntityList,
			getEntity: getEntity,
			countFunction: countFunction,
			getCount: getCount,
			changePassword: changePassword,
			deleteEntity: deleteEntity,
			getUserData: getUserData,
			detail1: detail1,
			submitTicket: submitTicket,
			saveTicketRemarkWithFile: saveTicketRemarkWithFile,
			saveRemarkWithFile: saveRemarkWithFile,
			saveCorrection: saveCorrection,
			saveRemark: saveRemark,
			saveWithFile: saveWithFile,
			saveBulkRemark: saveBulkRemark,
			sendReminder: sendReminder,
			regenerateCorrectionRequest: regenerateCorrectionRequest,
			getMysql: getMysql,
			updatedeductee: updatedeductee,
			rejectductee: rejectductee,
			uploadCertificate: uploadCertificate,
			saveRegularReturn: saveRegularReturn,
			getDataWithAI: getDataWithAI,
			getStatus : getStatus,

		};
		return factory;

		function loginDetail() {
			var deferred = $q.defer();
			$http.get(REST_SERVICE_URI + 'Login/detail').success(
				function(data) {
					loginData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;

		}
		function fetch(entity, pageNo) {
			entityList = [];
			var deferred = $q.defer();
			$http.get(
				REST_SERVICE_URI + entity + '/list' + '/get/' + pageNo
				+ '/100').success(function(data) {
					entityList = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}
		function fetchSearch(entity, map, pageNo) {
			entityList = [];
			var deferred = $q.defer();
			$http.get(REST_SERVICE_URI + entity + '/search/get/' + pageNo + '/100/' + map).success(function(data) {
				entityList = data.entities;
				deferred.resolve(data);
			})
				.error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function deleteById(entity, id) {
			var deferred = $q.defer();
			$http.delete(REST_SERVICE_URI + entity + '/delete/' + id)
				.success(function(data) {
					deferred.resolve(data);
				})
				.error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function saveWithFile(entitySave, entity) {
			var dat = new FormData();
			if (entitySave.blob == null || entitySave.blob == undefined) {
				return save(entitySave, entity)
			} else {
				dat.append('blob', entitySave.blob)
				dat.append('dec', JSON.stringify(entitySave));
				delete entitySave.blob
				var deferred = $q.defer();
				$http.post(
					REST_SERVICE_URI + entity + '/addWithFile',
					dat, {
					transformRequest: angular.identity,
					headers: { 'Content-Type': undefined }
				}).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			}
		}

		function save(entitySave, entity) {
			var deferred = $q.defer();

			$http.post(REST_SERVICE_URI + entity + '/add', entitySave)
				.success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function saveBulkRemark(entitySave, entity) {
			var deferred = $q.defer();

			$http.post(REST_SERVICE_URI + entity + '/addBulkRemark', entitySave)
				.success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function sendReminder(entitySave, entity) {
			var deferred = $q.defer();

			$http.post(REST_SERVICE_URI + entity + '/sendReminder', entitySave)
				.success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function documentSave(object, entity) {
			var dat = new FormData();
			if (object.zipFile != null) {
				dat.append('zipFile', object.zipFile);
			}
			if (object.TAN != null) {
				dat.append('TAN', object.TAN);
			}
			if (object.fy != null) {
				dat.append('fy', object.fy);
			}
			if (object.quarter != null) {
				dat.append('quarter', object.quarter);
			}
			if (object.form != null) {
				dat.append('form', object.form);
			}
			if (object.uploadedTime != null) {
				dat.append('uploadedTime', object.uploadedTime);
			}
			var deferred = $q.defer();
			$http.post(
				REST_SERVICE_URI + entity + '/addFile',
				dat, {
				transformRequest: angular.identity,
				headers: { 'Content-Type': undefined }
			}).success(function(data) {

				deferred.resolve(data);
			}).error(function(status) {
				deferred.reject(status);
			});

			return deferred.promise;
		}

		function submitTicket(file, entitySave, entity) {
			var dat = new FormData();
			if (file == null || file == undefined) {
				return save(entitySave, entity)
			}
			else {

				dat.append('branchCode', entitySave.branchCode);
				dat.append('custVendId', entitySave.custVendId);
				dat.append('dateOfChange', entitySave.dateOfChange);
				dat.append('dateOfOpening', entitySave.dateOfOpening);
				dat.append('description', entitySave.description);
				dat.append('file', file);
				dat.append('form', entitySave.form);
				dat.append('fy', entitySave.fy);
				dat.append('pan', entitySave.pan);
				dat.append('quarter', entitySave.quarter);
				dat.append('resolved', entitySave.resolved);
				dat.append('status', entitySave.status);
				var deferred = $q.defer();
				$http.post(REST_SERVICE_URI + entity + '/addTicket',
					dat, {
					transformRequest: angular.identity,
					headers: { 'Content-Type': undefined }
				}).success(function(data) {
					deferred.resolve(data);
				}).error(function(status) {
					console.log("Error in Save CommonService");
					deferred.reject(status);
				});
				return deferred.promise;
			}
		}

		function uploadCertificate(downloadFile, entitySave, entity) {
			var dat = new FormData();
			dat.append('downloadFile', downloadFile)
			dat.append('tan', entitySave.tan);
			dat.append('typeofCertificate', entitySave.typeofCertificate);
			dat.append('fy', entitySave.financialYear);
			dat.append('quarter', entitySave.quarter);
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/uploadCertificate',
				dat, {
				transformRequest: angular.identity,
				headers: { 'Content-Type': undefined }
			}).success(function(data) {
				deferred.resolve(data);
			}).error(function(status) {
				console.log("Error in Save CommonService");
				deferred.reject(status);
			});
			return deferred.promise;
		}

		// documentSave
		function saveRemarkWithFile(downloadFile, entitySave, entity) {
			var dat = new FormData();
			dat.append('downloadFile', downloadFile)
			dat.append('branchCode', entitySave.branchCode);
			dat.append('crId', entitySave.correctionRequestId);
			dat.append('remark', entitySave.correctionRemark);
			dat.append('status', entitySave.remarkStatus);
			dat.append('fy', entitySave.fy);
			dat.append('quarter', entitySave.quarter);
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/addRemarkWithDocument',
				dat, {
				transformRequest: angular.identity,
				headers: { 'Content-Type': undefined }
			}).success(function(data) {
				deferred.resolve(data);
			}).error(function(status) {
				console.log("Error in Save CommonService");
				deferred.reject(status);
			});
			return deferred.promise;
		}

		function regenerateCorrectionRequest(entitySave, entity) {
			var dat = new FormData();
			dat.append('crId', entitySave.correctionRequestId);
			dat.append('crRemark', entitySave.correctionRemark);

			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/regenerateCorrectionRequest',
				dat, {
				transformRequest: angular.identity,
				headers: { 'Content-Type': undefined }
			}).success(function(data) {
				deferred.resolve(data);
			}).error(function(status) {
				console.log("Error in Save CommonService");
				deferred.reject(status);
			});
			return deferred.promise;
		}

		// documentSave
		function saveTicketRemarkWithFile(downloadFile, entitySave, entity) {
			var dat = new FormData();
			dat.append('downloadFile', downloadFile)
			dat.append('branchCode', entitySave.branchCode);
			dat.append('deducteeId', entitySave.deducteeId);
			dat.append('remark', entitySave.remark);
			dat.append('fy', entitySave.fy);
			dat.append('quarter', entitySave.quarter);
			dat.append('form', entitySave.form);
			dat.append('deducteeForm', entitySave.deducteeForm);
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/addRemarkWithDocument',
				dat, {
				transformRequest: angular.identity,
				headers: { 'Content-Type': undefined }
			}).success(function(data) {
				deferred.resolve(data);
			}).error(function(status) {
				console.log("Error in Save CommonService");
				deferred.reject(status);
			});
			return deferred.promise;
		}

		/*function saveCorrection(entitySave, entity) {
				var dat = new FormData();
				if(entitySave.blob==null || entitySave.blob==undefined){
				return save(entitySave, entity)
				}else{
					  dat.append('blob', entitySave.blob)
				dat.append('dec', JSON.stringify(entitySave));
				
				var deferred = $q.defer();
				$http.post(
						REST_SERVICE_URI + entity + '/addCorrection',
						dat, {
							transformRequest: angular.identity,
							headers: {'Content-Type': undefined}
						}).success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

				return deferred.promise;

			} }*/


		function saveRegularReturn(entitySave, entity) {
			var dat = new FormData();

			for (let i = 0; i < entitySave.docs.length; i++) {
				if (entitySave.docs[i].blob != undefined) {
					dat.append('blob', entitySave.docs[i].blob)
				}

			}

			dat.append('tdsfileblob', entitySave.tdsfileblob);
			//dat.append('reportfileblob', entitySave.reportfileblob);
			dat.append('dec', JSON.stringify(entitySave));
			var deferred = $q.defer();
			$http.post(
				REST_SERVICE_URI + entity + '/addRegularReturnRO',
				dat, {
				transformRequest: angular.identity,
				headers: { 'Content-Type': undefined }
			}).success(function(data) {

				deferred.resolve(data);
			}).error(function(status) {
				deferred.reject(status);
			});

			return deferred.promise;
		}


		function saveCorrection(entitySave, entity) {
			var dat = new FormData();
			if (entitySave.cad != undefined) {
				var b = entitySave.docs;
				var cd = entitySave.cad.challanSupportingDoc;
				if ((b.length == 0) && (cd == null || cd == undefined)) {
					return save(entitySave, entity)
				} else {
					if (b.length != 0 && (cd == null || cd == undefined)) {
						for (let i = 0; i < entitySave.docs.length; i++) {
							dat.append('blob', entitySave.docs[i].blob)
						}
						dat.append('dec', JSON.stringify(entitySave));

						var deferred = $q.defer();
						$http.post(
							REST_SERVICE_URI + entity + '/addCorrection/singleFile',
							dat, {
							transformRequest: angular.identity,
							headers: { 'Content-Type': undefined }
						}).success(function(data) {

							deferred.resolve(data);
						}).error(function(status) {
							deferred.reject(status);
						});

						return deferred.promise;
					} else if (b.length == 0 && cd != null) {
						dat.append('blob', cd)
						dat.append('dec', JSON.stringify(entitySave));
						var deferred = $q.defer();
						$http.post(
							REST_SERVICE_URI + entity + '/addCorrection/singleFile',
							dat, {
							transformRequest: angular.identity,
							headers: { 'Content-Type': undefined }
						}).success(function(data) {

							deferred.resolve(data);
						}).error(function(status) {
							deferred.reject(status);
						});

						return deferred.promise;
					} else {
						for (let i = 0; i < entitySave.docs.length; i++) {
							dat.append('blob', entitySave.docs[i].blob)
						}
						dat.append('blob2', cd)
						dat.append('dec', JSON.stringify(entitySave));
						var deferred = $q.defer();
						$http.post(
							REST_SERVICE_URI + entity + '/addCorrection/multipleFile',
							dat, {
							transformRequest: angular.identity,
							headers: { 'Content-Type': undefined }
						}).success(function(data) {

							deferred.resolve(data);
						}).error(function(status) {
							deferred.reject(status);
						});

						return deferred.promise;

					}



				}
			} else {
				var b = entitySave.docs;
				if (b.length == 0) {
					return save(entitySave, entity)
				} else {
					for (let i = 0; i < entitySave.docs.length; i++) {
						if (entitySave.docs[i].blob != undefined) {
							dat.append('blob', entitySave.docs[i].blob)
						} else {
							var deferred = $q.defer();
							deferred.reject("Supporting Document is Required");
							return deferred.promise;
						}
					}
					dat.append('dec', JSON.stringify(entitySave));
					var deferred = $q.defer();
					$http.post(
						REST_SERVICE_URI + entity + '/addCorrection/singleFile',
						dat, {
						transformRequest: angular.identity,
						headers: { 'Content-Type': undefined }
					}).success(function(data) {

						deferred.resolve(data);
					}).error(function(status) {
						deferred.reject(status);
					});

					return deferred.promise;
				}
			}
		}



		function saveRemark(entitySave, entity) {
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/addRemark', entitySave)
				.success(function(data) {
					deferred.resolve(data);
				}).error(function(status) {
					console.log("Error in Save CommonService");
					deferred.reject(status);
				});
			return deferred.promise;
		}
		function getUserData() {
			return loginData;
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


		function updatedeductee(entitySave, remarkId, deducteeId, entity) {
			var deferred = $q.defer();

			$http.put(REST_SERVICE_URI + entity + '/updateDeductee/' + remarkId + '/' + deducteeId, entitySave)

				.success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function rejectductee(rejectRemark, entitySave, deducteeId, deducteeformid, entity) {
			var deferred = $q.defer();

			$http.put(REST_SERVICE_URI + entity + '/rejectDeductee/' + deducteeId + '/' + deducteeformid + '/' + rejectRemark,
				entitySave)

				.success(function(data) {

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

		function detail(entity, detailId) {
			var deferred = $q.defer();
			$http.get(REST_SERVICE_URI + entity + '/detail/' + detailId)
				.success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;

		}
		//pranay-Notice
		function detail1(entity, id, din) {
			var deferred = $q.defer();
			$http.get(REST_SERVICE_URI + entity + '/detail/' + id + '/' + din)
				.success(function(data) {
					console.log("success inDetail of CommonService");
					if (data.count != null) {
						count = data.count;
					}
					entityData = data;
					entityList = data;
					deferred.resolve(data);
				}).error(function(status) {
					console.log("error inDetail of CommonService");
					deferred.reject(status);
				});

			return deferred.promise;
		}

		//pranay-Notice
		function detailForm(entity, fy, branchCode, detailId) {
			var deferred = $q.defer();
			$http.get(
				REST_SERVICE_URI + entity + '/detail/' + fy + "/"
				+ branchCode + "/" + detailId).success(
					function(data) {
						entityData = data;
						entityList = data;
						deferred.resolve(data);
					}).error(function(status) {
						deferred.reject(status);
					});

			return deferred.promise;

		}

		function search(entity, map) {
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/searchEntity/' + map)
				.success(function(data) {
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
			$http.get(REST_SERVICE_URI + entity + '/search' + '/get/0/100/' + map).success(function(data) {
				count = data.count;
				entityList = data.entities;
				deferred.resolve(data);
			}).error(function(status) {
				deferred.reject(status);
			});

			return deferred.promise;

		}

		function searchTable(entity, map, pageNo) {
			entityList = [];
			var deferred = $q.defer();
			$http.get(REST_SERVICE_URI + entity + '/searchTable' + '/get/' + pageNo + '/100/' + map).success(function(data) {
				count = data.count;
				entityList = data.entities;
				deferred.resolve(data);
			}).error(function(status) {
				deferred.reject(status);
			});

			return deferred.promise;

		}


		/* //Pranay */
		function ajax(entity, ajax) {
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/ajax', ajax).success(
				function(data) {
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

			$http.put(REST_SERVICE_URI + entity + '/update', entitySave)
				.success(function(data) {

					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function countFunction(entity) {
			entityList = [];
			var deferred = $q.defer();
			$http.get(REST_SERVICE_URI + entity + '/list/count/').success(
				function(data) {
					count = data.count;
					entityList = data.entities;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});
			return deferred.promise;


			function getData(entity) {
				entityList = [];
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI + entity + '/list/count/').success(
					function(data) {
						count = data.count;
						entityList = data.entities;
						deferred.resolve(data);
					}).error(function(status) {
						deferred.reject(status);
					});
				return deferred.promise;
			}
		}

		function getCount() {
			return count;
		}

		function deleteEntity(entity, deleteId) {
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/delete/' + deleteId)
				.success(function(data) {
					entityData = data;
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;

		}

		function getMysql(data, entity) {
			var deferred = $q.defer();
			$http.get(REST_SERVICE_URI + entity + '/get/' + data)
				.success(function(data) {
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function getDataWithAI(data, entity) {
			var deferred = $q.defer();
			$http.post(REST_SERVICE_URI + entity + '/TextToSQL', data)
				.success(function(data) {
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

		function getStatus(requestId) {
			var deferred = $q.defer();
			$http.get(REST_SERVICE_URI + 'promptQuery/status/' + requestId)
				.success(function(data) {
					deferred.resolve(data);
				}).error(function(status) {
					deferred.reject(status);
				});

			return deferred.promise;
		}

	}]);
