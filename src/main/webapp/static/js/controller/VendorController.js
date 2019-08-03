'use strict';

App.controller('VendorController',[
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
							self.branchId;
							self.vendorId;
							self.entity = {};
							
							self.entity = {};
							self.ajax = [];
							self.ajax1 = function(entity, name, term) {
								var ajax = {};
								ajax.name = name;
								ajax.term = term;
								var deferred = $q.defer();
								return CommonService.ajax(entity, ajax,
										$stateParams.clientId).then(
										function(data) {
											console.log(name
													+ ' dynamic drop down');
											var items = data;
											self.ajax = data;
											return data;
										});
							}

							self.getVendorDetail = function(gstNo, entity) {
								console.log("Common Controller is working");
								var map = {};
								map.gstNo = gstNo;
								if (gstNo != null) {
									CommonService
											.search($stateParams.clientId,
													entity, map)
											.then(
													function(data) {
														console
																.log(name
																		+ ' dynamic drop down');

														var items = data;
														self.entity.vendorName = data.vendorName;
														self.entity.vendorPAN = data.vendorPAN;
														self.entity.vendorNo = data.vendorNo;
														self.entity.vendorId = data.id
														self.entity.clientId = data.id

													});
								}
							}
							
							self.getEntityData = function() {
								console
										.log("Common Controller get Entity data");
								return CommonService.getEntity();
							}

							self.getVendorDetail1 = function(vendorNo, entity) {
								console.log("Common Controller is working");
								var map = {};
								map.vendorNo = vendorNo;
								if (vendorNo != null) {
									CommonService
											.search($stateParams.clientId,
													entity, map)
											.then(
													function(data) {
														console
																.log(name
																		+ ' dynamic drop down');

														var items = data;
														self.entity.vendorName = data.vendorName;
														self.entity.vendorPAN = data.vendorPAN;
														self.entity.gstNo = data.gstNo;
														self.entity.vendorId = data.id

													});
								}
							}

							self.getVendorDetail2 = function(vendorPAN, entity) {
								console.log("Common Controller is working");
								var map = {};
								map.vendorPAN = vendorPAN;
								if (vendorPAN != null) {
									CommonService
											.search($stateParams.clientId,
													entity, map)
											.then(
													function(data) {
														console
																.log(name
																		+ ' dynamic drop down');

														var items = data;
														self.entity.vendorName = data.vendorName;
														self.entity.vendorNo = data.vendorNo;
														self.entity.gstNo = data.gstNo;
														self.entity.vendorId = data.id
														self.entity.clientId = data.id

													});
								}
							}

							self.getVendorDetail3 = function(vendorName, entity) {
								console.log("Common Controller is working");
								
								var map = {};
								map.vendorName = vendorName;
								if (vendorName != null) {
									CommonService
											.search($stateParams.clientId,
													entity, map)
											.then(
													function(data) {
														console
																.log(name
																		+ ' dynamic drop down');

														var items = data;
														self.entity.vendorPAN = data.vendorPAN;
														self.entity.vendorNo = data.vendorNo;
														self.entity.gstNo = data.gstNo;
														self.entity.vendorId = data.id
														self.entity.clientId = data.id

													});
								}
							}
							
							self.gotoListPage = function(entity, page) {
								self.company = name;
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
							
							self.gotoList2Page = function(entity2, page2) {
								$state.go("home.listBranch", {
									"entity" : entity2,
									"page" : page2
								});
							}


							self.submit = function(valid, entity, closeModalId) {
								if (valid == true) {
									console.log("Common Controller submit "
											+ entity);
									self.entity.branchId = $stateParams.branchId;
									self.entity.clientId = $stateParams.clientId;
									
									CommonService
											.save(self.entity, entity,
													$stateParams.clientId)
											.then(
													function(data) {
														console
																.log(entity
																		+ ' added successfully');
														
														self.gotoList2Page(
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

						} ]);