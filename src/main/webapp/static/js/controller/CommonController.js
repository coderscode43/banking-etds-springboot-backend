'use strict';

App
	.controller(
		'CommonController',
		[
			'$http',
			'restUrl',
			'$scope',
			'$state',
			'$stateParams',
			'CommonService',
			'CommonServiceFY',
			'$q',
			'$window',
			function($http, restUrl, $scope, $state, $stateParams,
				CommonService, CommonServiceFY, $q, $window) {

				var self = this;
				self.tan;
				self.branchId;
				self.loader = '';
				self.entity = {};
				self.resolve = {};
				self.search = {};// Pranay
				self.entityList = [];
				// self.ItemsPerPage=100;
				self.dropdown = [];
				self.ajax = [];
				self.temp = {};
				self.temp3 = {};
				self.temp1 = [];
				self.temp2 = {};
				self.company = {};
				self.delete = {};
				self.currentPage = 1;
				self.batchColor = {};
				self.goodsColor = [];
				self.b = [];
				self.fileName = new FormData();
				self.reload = function() {
					$state.reload();
				}
				self.getStateParamValue = function(a) {
					return $stateParams[a];
				}
				self.setTemp1 = function(a) {
					self.temp1 = a;
				}
				self.logout = function() {
					$state.go("logout");
				}

				self.presentDate = function() {
					return new Date();
				}

				self.presentMonth = function() {
					return new Date().getMonth();
				}

				self.gotoHomePage = function() {
					self.show = false;
					self.entity = {};
					self.search = {};
					self.entityList = [];
					// self.ItemsPerPage=100;
					self.dropdown = [];
					self.ajax = [];
					self.temp = {};
					self.temp3 = {};
					self.temp1 = [];
					self.company = {};
					self.currentPage = 1;
					self.batchColor = {};
					self.goodsColor = [];
					self.b = [];
					self.fileName = new FormData();
					$state.go("home.homepage", {});

				}
				self.gotoRestPassword = function() {
					self.entity = {};
					$state.go("resetPass");
				}
				self.getUserData = function() {
					return CommonService.getUserData();
				}

				self.changePassword = function(valid, password) {
					if (valid == true) {
						CommonService
							.changePassword(password)
							.then(
								function(data) {
									console
										.log('Course added successfully');
									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull!");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Saved Successfully!");
									$("#successMsg")
										.modal();
									$state
										.go("home.homepage");
								},
								function(error) {
									console
										.error('Error while creating saving Details, '
											+ status);
									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Save "
												+ error.entityName
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}
								});
					}
				}//

				self.gotoHomePageWOTOFPresentFY = function(
					branchCode) {
					self.show = false;
					self.entity = {};
					self.search = {};
					self.entityList = [];
					// self.ItemsPerPage=100;
					self.dropdown = [];
					self.ajax = [];
					self.temp = {};
					self.temp3 = {};
					self.temp1 = [];
					self.company = {};
					self.currentPage = 1;
					self.batchColor = {};
					self.goodsColor = [];
					self.b = [];
					self.fileName = new FormData();
					$state.go("homeWot.homepage", {
						"branchCode": branchCode,
						"fy": '2022-23'
					});
				}


				self.gotoDetailPage = function(entity, detailId,
					page) {
					$state.go("home.detail", {
						"entity": entity,
						"detailId": detailId,
						"page": page
					});
				}
				self.gotoDetailFormPage = function(entity,
					detailId, fy, branchCode, page) {
					$state.go("home.detailForm", {
						"entity": entity,
						"fy": fy,
						"detailId": detailId,
						"branchCode": branchCode,
						"page": page
					});
				}
				self.gotoWFYDetailPage = function(entity, detailId,
					page) {
					self.show = false;
					self.entity = {};
					self.search = {};
					self.entityList = [];
					// self.ItemsPerPage=100;
					self.dropdown = [];
					self.ajax = [];
					self.temp = {};
					self.temp3 = {};
					self.temp1 = [];
					self.company = {};
					self.currentPage = 1;
					self.batchColor = {};
					self.goodsColor = [];
					self.b = [];
					self.fileName = new FormData();

					$state.go("homeWot.detail", {
						"entity": entity,
						"detailId": detailId,
						"page": page,

					});

				}

				self.gotoListPage = function(entity, page) {
					self.show = false;
					self.entity = {};
					self.search = {};
					self.entityList = [];
					// self.ItemsPerPage=100;
					self.dropdown = [];
					self.ajax = [];
					self.temp = {};
					self.temp3 = {};
					self.temp1 = [];
					self.company = {};
					self.currentPage = 1;
					self.batchColor = {};
					self.goodsColor = [];
					self.b = [];
					self.fileName = new FormData();

					$state.go("home.list", {
						"entity": entity,
						"page": page
					});

				}

				self.gotoWFYListPage = function(entity, page) {
					self.show = false;
					self.entity = {};
					self.search = {};
					self.entityList = [];
					// self.ItemsPerPage=100;
					self.dropdown = [];
					self.ajax = [];
					self.temp = {};
					self.temp3 = {};
					self.temp1 = [];
					self.company = {};
					self.currentPage = 1;
					self.batchColor = {};
					self.goodsColor = [];
					self.b = [];
					self.fileName = new FormData();

					$state.go("homeWot.list", {
						"entity": entity,
						"page": page
					});

				}
				self.changeState = function(year) {
					self.show = false;
					self.entity = {};
					self.search = {};
					self.entityList = [];
					// self.ItemsPerPage=100;
					self.dropdown = [];
					self.ajax = [];
					self.temp = {};
					self.temp3 = {};
					self.temp1 = [];
					self.company = {};
					self.currentPage = 1;
					self.batchColor = {};
					self.goodsColor = [];
					self.b = [];
					self.fileName = new FormData();
					$state.go("homeWot.homepage", {
						"fy": year,
						"branchCode": $stateParams.branchCode
					});

				}

				self.getEntityListDataFY = function() {
					console
						.log("Common Controller get  getEntityList");
					return CommonServiceFY.getEntityList();
				}

				self.getEntityListData = function() {
					console
						.log("Common Controller get  getEntityList");
					return CommonService.getEntityList();
				}

				self.addRemark = function(valid, entity, object, type,
					closeModalId) {
					if (valid == true) {
						console.log("Common Controller submit "
							+ entity);

						progressBar();
						CommonServiceFY
							.add(object, entity, type,
								$stateParams.fy,
								$stateParams.branchCode)
							.then(
								function(data) {
									progressBar();
									console
										.log(entity
											+ ' added successfully');

									angular
										.element(
											'#'
											+ closeModalId)
										.trigger(
											'click');

									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Saved Successfully");
									$("#successMsg")
										.modal();

								},
								function(error) {
									progressBar();
									console
										.error('Error while creating saving Details, '
											+ status);
									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Save "
												+ error.entityName
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}
								});
					}
				}

				self.submitFile = function(valid, entity, form,
					closeModalId) {
					if (valid == true) {
						console.log("Common Controller submit "
							+ entity);

						progressBar();
						CommonService
							.saveDoc(form, entity)
							.then(
								function(data) {
									progressBar();
									console
										.log(entity
											+ ' added successfully');

									angular
										.element(
											'#'
											+ closeModalId)
										.trigger(
											'click');

									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Saved Successfully");
									$("#successMsg")
										.modal();

								},
								function(error) {
									progressBar();
									console
										.error('Error while creating saving Details, '
											+ status);
									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Save "
												+ error.entityName
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}
								});
					}
				}
				self.submitFileFY = function(valid, entity, form,
					closeModalId) {
					if (valid == true) {
						console.log("Common Controller submit "
							+ entity);

						progressBar();
						CommonServiceFY
							.save(form, entity)
							.then(
								function(data) {
									progressBar();
									console
										.log(entity
											+ ' added successfully');

									angular
										.element(
											'#'
											+ closeModalId)
										.trigger(
											'click');

									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Saved Successfully");
									$("#successMsg")
										.modal();

								},
								function(error) {
									progressBar();
									console
										.error('Error while creating saving Details, '
											+ status);
									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Save "
												+ error.entityName
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}
								});
					}
				}

				var progressBar = function() {
					angular.element('#viewProgressBar').trigger(
						'click');
					// $('#myModalShower').hide();

				}

				self.updateFile = function(valid, entity, form,
					closeModalId) {
					if (valid == true) {
						console.log("Common Controller submit "
							+ entity);
						if (form.dec.status == undefined
							|| form.dec.status == "Pending Verification") {
							progressBar();
							CommonService
								.updateDoc(form, entity)
								.then(
									function(data) {
										console
											.log(entity
												+ ' updated successfully');
										progressBar();
										angular
											.element(
												'#'
												+ closeModalId)
											.trigger(
												'click');
										$('#successMsg')
											.find(
												'.modal-header')
											.find(
												'.headingMsg')
											.append(
												"Successfull");
										$('#successMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												" Updated Successfully");
										$("#successMsg")
											.modal();

									},
									function(error) {
										progressBar();
										console
											.error('Error while updating Details, '
												+ status);
										if (error.exceptionMsg != null
											&& error.exceptionMsg != undefined) {
											$('#errorMsg')
												.find(
													'.modal-body')
												.find(
													'.msg')
												.append(
													"Can not Update "
													+ error.entityName
													+ " : "
													+ error.exceptionMsg);
											$("#errorMsg")
												.modal();
										} else {
											for (var i = 0; i < error.fieldErrors.length; i++) {
												var obj = error.fieldErrors[i];
												document
													.getElementById(obj.fieldName
														+ 'U').innerHTML = obj.message;

											}
										}

									});
						}

						else {
							angular.element('#' + closeModalId)
								.trigger('click');
							$('#successMsg1').find('.modal-header')
								.find('.headingMsg').append(
									"Information");
							$('#successMsg1')
								.find('.modal-body')
								.find('.msg')
								.append(
									" Update allowed only for Pending Verification");
							$("#successMsg1").modal();

						}

					}
				}
				self.submit = function(valid, object, entity,
					closeModalId) {// remove
					// closeModalId-pranay
					if (valid == true) {
						console.log("Common Controller submit "
							+ entity);
						self.object = object;
						CommonService
							.save(self.object, entity)
							.then(
								function(data) {
									console
										.log(entity
											+ ' Add successfully');
									self.gotoListPage(
										entity, entity);
									$('.modal').modal(
										"hide");
									angular
										.element(
											'#'
											+ closeModalId)
										.trigger(
											'click');
									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull!");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Saved Successfully!");
									$("#successMsg")
										.modal();

								},
								function(error) {
									console
										.error('Error while creating saving Details, '
											+ status);
									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Save "
												+ error.entity
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}

								});
					}
				}
				self.submitFY = function(valid, object, entity,
					closeModalId) {
					if (valid == true) {
						console.log("Common Controller submit "
							+ entity);

						CommonServiceFY
							.save(object, entity)
							.then(
								function(data) {
									console
										.log(entity
											+ ' Add successfully');
									self.gotoWFYListPage(
										entity, entity);
									$('.modal').modal(
										"hide");
									angular
										.element(
											'#'
											+ closeModalId)
										.trigger(
											'click');
									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull!");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Saved Successfully!");
									$("#successMsg")
										.modal();

								},
								function(error) {
									console
										.error('Error while creating saving Details, '
											+ status);
									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Save "
												+ error.entity
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}

								});
					}
				}

				self.getEntityData = function() {
					console
						.log("Common Controller get Entity data");
					return CommonService.getEntity();
				}
				self.getEntityDataFY = function() {
					console
						.log("Common Controller get Entity data");
					return CommonServiceFY.getEntity();
				}
				/* Pranay */
				self.ajax = function(entity, name, term) {
					var ajax = {};
					ajax.name = name;
					ajax.term = term;
					var deferred = $q.defer();
					return CommonService.ajax(entity, ajax).then(
						function(data) {
							console.log(name
								+ ' dynamic drop down');
							var items = data;
							self.ajax = data;
							return data;
						});
				}
				self.searchEntities = function(valid, entity, page) {
					if (valid = true) {
						$.each(self.search, function(key, value) {
							if (value === "" || value === null) {
								delete self.search[key];
							}
						});
						self.lastSearch = self.search;
						var param = JSON.stringify(self.search);
						$state.go("home.search", {
							"entity": entity,
							"page": page,
							"searchParams": param
						});
					}
				}
				self.searchFYEntities = function(valid, entity,
					page) {
					if (valid = true) {
						$.each(self.search, function(key, value) {
							if (value === "" || value === null) {
								delete self.search[key];
							}
						});
						self.lastSearch = self.search;
						var param = JSON.stringify(self.search);
						$state.go("homeWot.search", {
							"entity": entity,
							"page": page,
							"searchParams": param
						});
					}
				}
				self.gotoAddPage = function(page) {
					self.entity = {};
					$state.go("home.add", {
						"page": page
					});
				}
				self.gotoAddWFYPage = function(page) {
					self.entity = {};
					$state.go("homeWot.add", {
						"page": page
					});
				}
				self.updateData = function(valid, object, entity, remark) {// --remove
					// closeModalId-pranay
					if (valid == true) {
						console.log("Common Controller updateData "
							+ entity);
						self.object = object;
						self.object.remark = remark;

						CommonService
							.update(object, entity)
							.then(
								function(data) {
									console
										.log(entity
											+ ' updated successfully');
									$('.modal').modal(
										"hide");
									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Updated Successfully");
									$("#successMsg")
										.modal();

								},
								function(error) {
									console
										.error('Error while updating Details, '
											+ status);

									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Update "
												+ error.entityName
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}

								});

					}
				}

				self.updateTicket = function(valid, object, entity, remark) {// --remove
					// closeModalId-pranay
					if (valid == true) {
						console.log("Common Controller updateData "
							+ entity);
						self.object = object;
						self.object.remark = remark;
						CommonService
							.updateTicket(object, entity)
							.then(
								function(data) {
									console
										.log(entity
											+ ' updated successfully');
									$('.modal').modal(
										"hide");
									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Updated Successfully");
									$("#successMsg")
										.modal();

								},
								function(error) {
									console
										.error('Error while updating Details, '
											+ status);

									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Update "
												+ error.entityName
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}

								});

					}
				}
				/* pranay */

				// ///////////////////////\\\\\\\\\\\\\
				self.updateStatus = function(valid, entity, data,
					closeModalId) {
					if (valid == true) {
						console.log("Common Controller updateData "
							+ entity);

						CommonService
							.updateStatus(data, entity)
							.then(
								function(data) {
									console
										.log(entity
											+ ' updated successfully');
									angular
										.element(
											'#'
											+ closeModalId)
										.trigger(
											'click');

									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successfull");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Updated Successfully");
									$("#successMsg")
										.modal();

								},
								function(error) {
									console
										.error('Error while updating Details, '
											+ status);

									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Update "
												+ error.entityName
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}

								});

					}
				}
				self.download = function(url) {
					wait(1000);
					self.fileLoader = url;
					CommonServiceFY.check(url)
						.then(
							function(data) {
								window.open(self.fileLoader, "_blank")
								console
									.log(' Download successfully');

								$('#successMsg')
									.find(
										'.modal-header')
									.find(
										'.headingMsg')
									.append(
										"Successful");
								$('#successMsg')
									.find(
										'.modal-body')
									.find('.msg')
									.append(
										"File Open Successfully");
								$("#successMsg")
									.modal();


							},
							function(error) {
								console
									.error('Error while Downloading Details, '
										+ status);

								$('#errorMsg')
									.find(
										'.modal-body')
									.find(
										'.msg')
									.append("File can not found.");
								$("#errorMsg")
									.modal();


							});

				}

				self.downloadCertificate = function(valid, deductee, certificate, fy, q, pan) {
					if (valid == true) {
						CommonServiceFY.downloadCertificate(deductee, certificate, fy, q, pan)
							.then(
								function(data) {
									console
										.log(entity
											+ ' Download successfully');
									angular
										.element(
											'#'
											+ closeModalId)
										.trigger(
											'click');

									$('#successMsg')
										.find(
											'.modal-header')
										.find(
											'.headingMsg')
										.append(
											"Successful");
									$('#successMsg')
										.find(
											'.modal-body')
										.find('.msg')
										.append(
											" Downloaded Successfully");
									$("#successMsg")
										.modal();

								},
								function(error) {
									console
										.error('Error while updating Details, '
											+ status);

									if (error.exceptionMsg != null
										&& error.exceptionMsg != undefined) {
										$('#errorMsg')
											.find(
												'.modal-body')
											.find(
												'.msg')
											.append(
												"Can not Update "
												+ error.entityName
												+ " : "
												+ error.exceptionMsg);
										$("#errorMsg")
											.modal();
									} else {
										for (var i = 0; i < error.fieldErrors.length; i++) {
											var obj = error.fieldErrors[i];
											document
												.getElementById(obj.fieldName).innerHTML = obj.message;
										}
									}

								});
					}

				}

				var wait = function(ms) {
					var start = new Date().getTime();
					var end = start;
					while (end < start + ms) {
						end = new Date().getTime();
					}
				}

				self.date = function(date) {
					date = new Date(
						date.getTime()
						+ Math
							.abs(date
								.getTimezoneOffset() * 60000));
					// var dateString =date.toUTCString();
					// dateString = dateString.split(' ').slice(0,
					// 4).join(' ');
					return date;

				}

				self.getPage = function(valid, entity, pageNo) {
					if (valid = true) {
						self.currentPage = pageNo;
						self.getDataSC = $stateParams.searchParams;
						if (self.isNotEmpty(self.getDataSC)) {
							/*CommonService.fetchSearch(entity, JSON.stringify(self.lastSearch), pageNo - 1);*/
							CommonService.fetchSearch(entity, self.getDataSC, pageNo - 1); /*Search Next--Pranay*/
						} else {
							CommonService.fetch(entity, pageNo - 1); /*Direct Next-pranay*/
						}
					}
				}
				self.getPages = function(valid, entity, pageNo) {
					if (valid = true) {
						self.currentPage = pageNo;
						self.getDataWOT = $stateParams.searchParams;
						if (self.isNotEmpty(self.getDataWOT)) {
							CommonServiceFY.fetchSearch(entity, self.getDataWOT, pageNo - 1); /*Search Next--Pranay*/
						} else {
							CommonServiceFY.fetch(entity, pageNo - 1); /*Direct Next-pranay*/
						}
					}
				}
				self.isNotEmpty = function(obj) {
								for (var key in obj) {
									if (obj.hasOwnProperty(key))
										return true;
								}
								return false;
				}
				self.date = function(date) {
					date = new Date(
						date.getTime()
						+ Math
							.abs(date
								.getTimezoneOffset() * 60000));
					// var dateString =date.toUTCString();
					// dateString = dateString.split('
					// ').slice(0,
					// 4).join(' ');
					return date;

				}

				self.getNoPage = function(valid, entity, pageNo) {

					return Math
						.ceil(CommonService.getCount() / 100);

				}

				self.getNoPages = function(valid, entity, pageNo) {

					return Math
						.ceil(CommonServiceFY.getCount() / 100);

				}
				self.deleteById = function(entity, id) {
					CommonService.deleteEntity(entity, id)
						.then(function(data) {

							$('#successMsg')
								.find('.modal-header')
								.find('.headingMsg')
								.text("Successfull!");
							$('#successMsg')
								.find('.modal-body')
								.find('.msg')
								.text(" Deleted Successfully!");
							$("#successMsg").modal();

						},
							function(error) {
								console.error('Error while creating saving Details, ' + status);
								if (error.exceptionMsg != null
									&& error.exceptionMsg != undefined) {
									$('#errorMsg')
										.find('.modal-body')
										.find('.msg')
										.text("Can not Save " + entity + " : "
											+ error.exceptionMsg);
									$("#errorMsg").modal();
								} else {
									for (var i = 0; i < error.fieldErrors.length; i++) {
										var obj = error.fieldErrors[i];
										document.getElementById(obj.fieldName).innerHTML = obj.message;
									}
								}
							});
				}

				self.generateExcel = function(url, modal) {

					$.each(self.search, function(key, value) {
						if (value === "" || value === null) {
							delete self.search[key];
						}
					});
					self.getSearchData = self.search;
					if (self.isEmpty(self.search)) {

						if ($stateParams.searchParams == undefined) {
							self.search = {};
							url = url.concat(JSON.stringify(self.search));
						} else {
							self.search = $stateParams.searchParams;
							url = url.concat(self.search);
						}
					}

					/*self.search = $stateParams.searchParams;*/
					url = url.concat(self.search);

					wait(1000);
					self.loader = url;
					window.open(self.loader, "_blank");
				}
				self.isEmpty = function(obj) {
					for (var key in obj) {
						if (obj.hasOwnProperty(key))
							return false;
					}
					return true;
				}

				self.generateProcessExcel = function(url, modal) {
					self.search.branchCode = $stateParams.branchCode;
					self.search.fy = $stateParams.fy;
					$.each(self.search, function(key, value) {
						if (value === "" || value === null) {
							delete self.search[key];
						}
					});
					if (self.isEmpty(self.search)) {
						var param1 = $stateParams.param1;
						if ($stateParams.searchParams == undefined) {
							self.search = {};
						} else {
							self.search = $stateParams.searchParams;
						}
					}
					wait(1000);
					url = url.concat(JSON.stringify(self.search));
					self.loader = url;
					window.open(self.loader, "_blank");
				}
				self.isEmpty = function(obj) {
					for (var key in obj) {
						if (obj.hasOwnProperty(key))
							return false;
					}
					return true;
				}

			}]);
App.directive('fileModel', ['$parse', function($parse) {
	return {
		restrict: 'A',
		link: function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
}]);
