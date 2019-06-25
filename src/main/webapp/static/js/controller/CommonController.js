'use strict';

App.controller(
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
								CommonService, $q,$window) {

							var self = this;
							self.tan;
							self.clientId=0;
							self.loader='';
							self.entity = {};
							self.search = {};
							self.entityList = [];
							// self.ItemsPerPage=100;
							self.dropdown = [];
							self.ajax = [];
							self.temp = {};
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
							self.changeAction = function() {
								if( $state.current.name=='main'){
									$window.location.reload();	
								}
								else{
									$state.go("main");
								}
								
								
							}
							self.gotoRestPqassword = function() {
								$state.go("resetPass");
							}

							self.gotoHomePageSC = function(clientId, action) {
								self.company = name;
								self.clientId=clientId;
								$state.go("home.homepage", {
									"clientId" : clientId,
									"action" : action
								});

							}
							
							self.gotoHomePageWOT = function(id, action) {
								self.company = name;
								self.clientId=$stateParams.clientId;
								$state.go("home.homepage", {
								    "branchId" :id,
									"action" : action
								});

							}
							
							self.gotoDetailPage = function(entity, detailId,
									page) {
								self.company = name;
								$state.go("home.detail", {
									"entity" : entity,
									"detailId" : detailId,
									"page" : page
								});

							}

							
							
						
							
							
							self.gotoDetail = function(entity, detailId) {
								self.company = name;
								var deferred = $q.defer();
								return CommonService
										.detail($stateParams.clientId, entity,
												detailId)
										.then(
												function(data) {
													console
															.log('Common Controller get Detail..');
													return data;
												});

							}
							self.deleteFunction = function(valid, entityName,
									entity, entityList, index, closeModalId) {
								if (valid = true) {

									CommonService
											.deleteEntity(
													$stateParams.clientId,
													entityName, entity.id)
											.then(
													function(data) {
														console
																.log(entity
																		+ ' deleted successfully');
													
														entityList.splice(
																index, 1);
														
														
                                                         angular.element('#'+closeModalId).trigger('click');
														
														$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
											          	$('#successMsg').find('.modal-body').find('.msg').append(" Deleted Successfully");
											         	$("#successMsg").modal();

													},
													function(error) {
														console
																.error('Error while deleting , '
																		+ status);
														if(error.exceptionMsg!=null && error.exceptionMsg!=undefined){
											      	  		$('#errorMsg1').find('.modal-body').find('.msg').append("Can not Delete "+error.entityName+" : "+error.exceptionMsg);
											      	  		$("#errorMsg1").modal();
											      	  	}else{
											      	  		for(var i=0;i<error.fieldErrors.length;i++){
											      	  			var obj = error.fieldErrors[i];
											      	  			document.getElementById(obj.fieldName).innerHTML=obj.message;
											      	  		}
											      	  	}
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

							self.gotoSearchDetailPage = function(entity2,
									detailId, page2) {

								$state.go("home.search.detail2", {
									"entity2" : entity2,
									"detailId" : detailId,
									"page2" : page2
								});
							}

							self.gotoList2Page = function(entity2, page2, map) {
								if (true) {
									$.each(map, function(key, value) {
										if (value === "" || value === null) {
											delete map[key];
										}

									});
								}
								$state.go("home.list.list2", {
									"entity2" : entity2,
									"page2" : page2,
									"searchParams" : JSON.stringify(map)
								});
							}

							self.gotoDirectList2Page = function(entity, page,
									entity2, page2, map) {
								if (true) {
									$.each(map, function(key, value) {
										if (value === "" || value === null) {
											delete map[key];
										}
									});
								}
								$state.go("home.list.list2", {
									"entity" : entity,
									"page" : page,
									"entity2" : entity2,
									"page2" : page2,
									"searchParams" : JSON.stringify(map)
								});
							}

							self.activetab2 = function(en) {
								if (en == $stateParams.entity2) {
									return true;
								}
								return false;
							}

							self.getEntityListData = function() {
								console
										.log("Common Controller get  getEntityList");
								return CommonService.getEntityList();
							}

							self.submitFile = function(valid, entity, form,closeModalId) {
								if (valid == true) {
									console.log("Common Controller submit "
											+ entity);
									self.entity.clientId = $stateParams.clientId;
									
									progressBar();
									CommonService
											.saveDoc(form, entity,
													$stateParams.clientId)
											.then(
													function(data) {
														progressBar();
														console
																.log(entity
																		+ ' added successfully');
														
														angular.element('#'+closeModalId).trigger('click');
														
														$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
											          	$('#successMsg').find('.modal-body').find('.msg').append(" Saved Successfully");
											         	$("#successMsg").modal();
														

													},
													function(error) { 
														progressBar();
														console
																.error('Error while creating saving Details, '
																		+ status);
														if(error.exceptionMsg!=null && error.exceptionMsg!=undefined){
											      	  		$('#errorMsg').find('.modal-body').find('.msg').append("Can not Save "+error.entityName+" : "+error.exceptionMsg);
											      	  		$("#errorMsg").modal();
											      	  	}else{
											      	  		for(var i=0;i<error.fieldErrors.length;i++){
											      	  			var obj = error.fieldErrors[i];
											      	  			document.getElementById(obj.fieldName).innerHTML=obj.message;
											      	  		}
											      	  	}
													});
								}
							}

							var progressBar = function() {
								 angular.element('#viewProgressBar').trigger('click');
								// $('#myModalShower').hide();

							}

							self.updateFile = function(valid, entity, form,closeModalId) {
								if (valid == true) {
									console.log("Common Controller submit "
											+ entity);
									self.entity.clientId = $stateParams.clientId;
									if( form.dec.status == undefined ||form.dec.status == "Pending Verification")
									{
									progressBar();
									CommonService
											.updateDoc(form, entity,
													$stateParams.clientId)
											.then(
													function(data) {
														console
																.log(entity
																		+ ' updated successfully');
														progressBar();
														angular.element('#'+closeModalId).trigger('click');
														$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
											          	$('#successMsg').find('.modal-body').find('.msg').append(" Updated Successfully");
											         	$("#successMsg").modal();
														
													},
													function(error) {
														progressBar();
														console
																.error('Error while updating Details, '
																		+ status);
														if(error.exceptionMsg!=null && error.exceptionMsg!=undefined){
											      	  		$('#errorMsg').find('.modal-body').find('.msg').append("Can not Update "+error.entityName+" : "+error.exceptionMsg);
											      	  		$("#errorMsg").modal();
											      	  	}else{
											      	  		for(var i=0;i<error.fieldErrors.length;i++){
											      	  			var obj = error.fieldErrors[i];
											      	  			document.getElementById(obj.fieldName+'U').innerHTML=obj.message;

											      	  		}
											      	  	}
														
														
													});
									}
									
									else
										{
										angular.element('#'+closeModalId).trigger('click');
										$('#successMsg1').find('.modal-header').find('.headingMsg').append("Information");
							          	$('#successMsg1').find('.modal-body').find('.msg').append(" Update allowed only for Pending Verification");
							         	$("#successMsg1").modal();
										
										}
									
								}
							}

							self.submit = function(valid, entity) {
								if (valid == true) {
									console.log("Common Controller submit "
											+ entity);

									self.entity.clientId = $stateParams.clientId;
									CommonService
											.save(self.entity, entity,
													$stateParams.clientId)
											.then(
													function(data) {
														console
																.log(entity
																		+ ' added successfully');
														self.gotoListPage(
																entity, entity);
														$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull!");
											          	$('#successMsg').find('.modal-body').find('.msg').append(" Saved Successfully!");
											         	$("#successMsg").modal();

													},
													function(error) {
														console
																.error('Error while creating saving Details, '
																		+ status);
														if(error.exceptionMsg!=null && error.exceptionMsg!=undefined){
											      	  		$('#errorMsg').find('.modal-body').find('.msg').append("Can not Save "+error.entityName+" : "+error.exceptionMsg);
											      	  		$("#errorMsg").modal();
											      	  	}else{
											      	  		for(var i=0;i<error.fieldErrors.length;i++){
											      	  			var obj = error.fieldErrors[i];
											      	  			document.getElementById(obj.fieldName).innerHTML=obj.message;
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

							self.getEmployeeId = function(employeeCode, entity) {
								console.log("Common Controller is working");
								var map = {};
								map.employeeCode = employeeCode;
								if (employeeCode != null) {
									CommonService
											.search($stateParams.clientId,
													entity, map)
											.then(
													function(data) {
														console
																.log(name
																		+ ' dynamic drop down');

														var items = data;
														self.search.employeeId = data.id;

													});
								}
							}

							self.getEmployeeDetail = function(employeeCode,
									entity) {
								console.log("Common Controller is working");
								var map = {};
								map.employeeCode = employeeCode;
								if (employeeCode != null) {
									CommonService
											.search($stateParams.clientId,
													entity, map)
											.then(
													function(data) {
														console
																.log(name
																		+ ' dynamic drop down');

														var items = data;
														self.entity.name = data.name;
														self.entity.pan = data.pan;
														self.entity.mobile = data.mobile;
														self.entity.email = data.email;
														self.entity.employeeId = data.id
													
													});
								}
							}
							
							self.getGroupId = function(groupCode,
									entity) {
								console.log("Common Controller is working");
								var map = {};
								map.groupCode = groupCode;
								if (groupCode != null) {
									CommonService
											.search($stateParams.clientId,
													entity, map)
											.then(
													function(data) {
														console
																.log(name
																		+ ' dynamic drop down');

														var items = data;
														self.entity.groupId = data.id;
													
													});
								}
							}

							self.updateData = function(valid, entity, data,closeModalId) {
								if (valid == true) {
									console.log("Common Controller updateData "
											+ entity);

									self.entity.clientId = $stateParams.clientId;
									
									CommonService
											.update(data, entity,
													$stateParams.clientId)
											.then(
													function(data) {
														console
																.log(entity
																		+ ' updated successfully');
														angular.element('#'+closeModalId).trigger('click');
													
														$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
											          	$('#successMsg').find('.modal-body').find('.msg').append(" Updated Successfully");
											         	$("#successMsg").modal();
														

													},
													function(error) {
														console
																.error('Error while updating Details, '
																		+ status);
														
														if(error.exceptionMsg!=null && error.exceptionMsg!=undefined){
											      	  		$('#errorMsg').find('.modal-body').find('.msg').append("Can not Update "+error.entityName+" : "+error.exceptionMsg);
											      	  		$("#errorMsg").modal();
											      	  	}else{
											      	  		for(var i=0;i<error.fieldErrors.length;i++){
											      	  			var obj = error.fieldErrors[i];
											      	  			document.getElementById(obj.fieldName).innerHTML=obj.message;
											      	  		}
											      	  	}
														
													});
								
								
								}
							}
							
							/////////////////////////\\\\\\\\\\\\\
							self.updateStatus = function(valid, entity, data,closeModalId) {
								if (valid == true) {
									console.log("Common Controller updateData "
											+ entity);

									self.entity.clientId = $stateParams.clientId;
									
									CommonService
											.updateStatus(data, entity,
													$stateParams.clientId)
											.then(
													function(data) {
														console
																.log(entity
																		+ ' updated successfully');
														angular.element('#'+closeModalId).trigger('click');
													
														$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
											          	$('#successMsg').find('.modal-body').find('.msg').append(" Updated Successfully");
											         	$("#successMsg").modal();
														

													},
													function(error) {
														console
																.error('Error while updating Details, '
																		+ status);
														
														if(error.exceptionMsg!=null && error.exceptionMsg!=undefined){
											      	  		$('#errorMsg').find('.modal-body').find('.msg').append("Can not Update "+error.entityName+" : "+error.exceptionMsg);
											      	  		$("#errorMsg").modal();
											      	  	}else{
											      	  		for(var i=0;i<error.fieldErrors.length;i++){
											      	  			var obj = error.fieldErrors[i];
											      	  			document.getElementById(obj.fieldName).innerHTML=obj.message;
											      	  		}
											      	  	}
														
													});
								
								
								}
							}
							
							
							
							
							
							
							
							

							self.setIndex = function(ind) {
								self.dlIndex = ind;
							}
							
							//view profile pic
							
							self.viewProfile=function(entity,id){
								//commonserive method to call the get File
								self.loader='static/img/Spinner-1s-200px.gif';
								self.loader='api'+entity+'/getFile/' + $stateParams.clientId + '/'+id+ '/'+Date.now();
							}
							
							self.viewFile=function(entity,a,modal){
								//commonserive method to call the get File
						     //	self.loader='';
							//	self.loader='static/img/Spinner-1s-200px.gif';
								
								
								self.temp1 = a;
								modal = modal+'Click';
								if(a.fileId!=null && a.fileId!=undefined ){
									modalClick(modal);
									wait(1000);
									self.loader='api'+entity+'/getFile/' + $stateParams.clientId + '/'+a.fileId;
								}else{
									modalClick('no'+modal);
							}
								
								
//								modal = modal+'Click';
//								if(a.fileId!=null && a.fileId!=undefined ){
//
//									
//									
//									  $http.get(restUrl+'static/img/Spinner-1s-200px.gif').then(res => {
//									      self.loader = 'api'+entity+'/getFile/' + $stateParams.clientId + '/'+a.fileId;
//									    })
//								modalClick(modal);	
//								}else{
//									modalClick('no'+modal);
//								}

								
								
								
								
								
								
							}
							
							
							
							
						
							
							
							
							
							
							self.isFileNull=function(valid, entity, form,closeModalId){
								if(form.file==null || form.file==undefined){
									 document.getElementById("file-upload").innerHTML="file is required ";
									}else{
										self.submitFile(valid, entity, form,closeModalId);
									}
							}
							
							var modalClick = function(a) {
								 angular.element('#'+a).trigger('click');
								// $('#myModalShower').hide();

							}
							var wait=function (ms){
								   var start = new Date().getTime();
								   var end = start;
								   while(end < start + ms) {
								     end = new Date().getTime();
								  }
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
