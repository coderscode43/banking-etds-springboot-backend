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
						'$q',
						'$window',
						function($http, restUrl, $scope, $state, $stateParams,
								CommonService, $q, $window) {

							var self = this;
							self.tan;
							self.branchId;
							self.loader='';
							self.entity = {};
							self.search = {};
							self.entityList = [];
							// self.ItemsPerPage=100;
							self.dropdown = [];
							self.ajax = [];
							self.temp = {};
							self.temp3 = {};
							self.temp1 = [];
							self.temp2 = {};
							self.company = {};
							self.currentPage = 1;
							self.batchColor = {};
							self.goodsColor = [];
							self.b = [];
							self.fileName = new FormData();
							self.reload = function() {
								$state.reload();
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
							
							self.gotoRestPqassword = function() {
								$state.go("resetPass");
							}

							self.gotoHomePage = function() {
								$state.go("home.homepage", {
								});

							}
							

							self.gotoHomePageWOT = function(id, fy) {
								$state.go("home.homepage", {
									"branchId" : id,
									"fy" : fy
								});
							}

							self.gotoDetailPage = function(entity, detailId,
									page) {
								$state.go("home.detail", {
									"entity" : entity,
									"detailId" : detailId,
									"page" : page
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
									"entity" : entity,
									"page" : page
								});

							}

							self.searchEntities = function(valid, entity, page) {
								if (valid = true) {
									$.each(self.search, function(key, value) {
										if (value === "" || value === null) {
											delete self.search[key];
										}
									});
									$state.go("home.search", {
										"entity" : entity,
										"page" : page,
										"searchParams" : JSON
												.stringify(self.search)
									});
								}
							}


							self.getEntityListData = function() {
								console
										.log("Common Controller get  getEntityList");
								return CommonService.getEntityList();
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

							
							
							
							self.submit = function(valid, entity, closeModalId) {
								if (valid == true) {
									console.log("Common Controller submit "
											+ entity);

									CommonService
											.save(self.entity, entity)
											.then(
													function(data) {
														console
																.log(entity
																		+ ' added successfully');
														self.gotoList(entity);
														self.gotoListPage(
																entity, entity);
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

							self.getEntityData = function() {
								console
										.log("Common Controller get Entity data");
								return CommonService.getEntity();
							}

							self.ajax1 = function(entity, name, term) {
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

							

							self.updateData = function(valid, entity, data,
									closeModalId) {
								if (valid == true) {
									console.log("Common Controller updateData "
											+ entity);


									CommonService
											.update(data, entity)
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
							
							self.getPage=function(valid,entity,pageNo){
								if(valid=true){
								self.currentPage=pageNo;
								CommonService.fetch(entity,pageNo-1);
								}
							}
							
							
							self.getNoPage=function(valid,entity,pageNo){
							
								return Math.ceil(CommonService.getCount()/100);
							
							}
							

						} ]);
App.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);
