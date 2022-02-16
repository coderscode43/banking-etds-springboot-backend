'use strict';

App
	.controller(
		'CommonWOTController',
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
			    //Varibles
			    var self = this;
			    self.search={};
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
				    self.entity.branchId = $stateParams.clientId;
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
			    

			    self.searchEntities = function(valid, entity, page) {
				if (valid = true) {
				    $.each(self.search, function(key, value) {
					if (value === "" || value === null) {
					    delete self.search[key];
					}
				    });
				    self.search.clientId = $stateParams.clientId;
				    $state.go("home.search", {
					"entity" : entity,
					"page" : page,

					"searchParams" : JSON
						.stringify(self.search)
				    });
				}
			    }

			} ]);