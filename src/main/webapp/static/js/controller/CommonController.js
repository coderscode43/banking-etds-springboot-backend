'use strict';

App.controller('CommonController', [
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
		$scope.checkerStatus = { 'background-color': 'red', 'color': 'white' }
		var self = this;
		self.tan;
		self.branchId;
		self.loader = '';
		self.entity = {};
		self.color = {};
		self.newEntity = {};
		self.resolve = {};
		self.reject = {};
		self.search = {};// vaibhav
		self.documents = {
			docs: []
		};
		self.quarter = {};// vaibhav
		self.typeOfCorrection = {};// vaibhav

		self.container = [];
		self.containerBackup = [];

		self.entityList = [];
		// self.ItemsPerPage=100;
		self.dropdown = [];
		self.ajax = [];
		self.temp = {};
		self.status = {};
		self.temp3 = {};
		self.temp1 = [];
		self.temp2 = {};
		self.company = {};
		self.delete = {};
		self.lastSearch = {};
		self.currentPage = 1;
		self.PreviousNo = 1;
		self.srNo = 1;
		self.batchColor = {};
		self.goodsColor = [];
		self.b = [];
		self.fileName = new FormData();
		self.showCheckRemarkError = true;
		self.access = false;// Regular Return bulkRemarkReminderBtn access 
		self.tableShow = true;
		self.reload = function() {
			$window.location.reload();
		}
		self.reloadState = function() {
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
			$window.location.reload();

		}
		/*sachin*/
		self.logoutButton = function() {
			$("#myModal2").modal();

		}

		$(document).ready(function() {
			$('body').removeClass('modal-open');
			$('.modal-backdrop').remove();
		});

		self.DisplayNo = function(no) {
			self.PreviousNo = no;
		}


		self.quarter = function() {
			self.showQuarterError = false;
		}

		self.typeOfCorrection = function() {
			self.showTOCError = false;
		}

		self.checkRemark = function() {
			self.showCheckRemarkError = false;
		}

		self.addNewDocument = function() {
			self.documents.docs.push({});
		}
		self.removeDocument = function(index) {
			self.documents.docs.splice(index, 1);
		};

		self.addSendEntity = function(whetherSelected, idPk) {
			//	{	
			if (!self.access) {
				self.container = [];
			} // } the if condition inside the {} bracess is use to empty the container 
			//when the selectAllCheckBox method is once used the container is filled wih data which cannot be removed in selectAllCheckBox method.  	
			self.access = true;
			if (typeof self.container === 'undefined') {
				self.container = [];
			}
			if (whetherSelected == true) {
				if (self.typeOfCorrection[0] == 'PAN Updation') {
					idPk.correctPan = null;
				} else if (self.typeOfCorrection[1] == 'Mismatch In Gross Amount') {
					idPk.correctAmountPaid = null;
				} else if (self.typeOfCorrection[2] == 'Mismatch In TDS Amount') {
					idPk.correctTds = null;
				} else if (self.typeOfCorrection[3] == 'Section Correction') {
					idPk.correctSection = null;
				}
				self.container.push(idPk);
			}
			else if (self.container.includes(idPk, 0)) {
				const index = self.container.indexOf(idPk);
				if (index > -1) {
					self.container.splice(index, 1)
				}
				if (self.container.length == 0) {
					self.access = false;
				}
			}

		}
		self.getAllSelected = function() {
			const selectAllCheckbox = document.getElementById('selectAllCheckbox');
			const rowCheckboxes = document.querySelectorAll('.checkhour');

			selectAllCheckbox.addEventListener('change', function() {
				const isChecked = this.checked;

				rowCheckboxes.forEach(checkbox => {
					checkbox.checked = isChecked;
				});
				var clicked = false;
				if (!clicked) {
					self.getEntityListData().forEach(function(a) {
						self.container.push(a);
					});
				} else {
					self.container = [];
				}
				clicked = !clicked;
				this.innerHTML = clicked;
			});

			rowCheckboxes.forEach(checkbox => {
				checkbox.addEventListener('change', function() {
					if (!this.checked) {
						selectAllCheckbox.checked = false;
					} else {
						// Check if all row checkboxes are checked
						const allChecked = [...rowCheckboxes].every(checkbox => checkbox.checked);
						selectAllCheckbox.checked = allChecked;
					}
				});
			});
			if (selectAllCheckbox.checked) {
				self.access = true;
			}
			else {
				self.access = false;
			}

		}


		self.addBulkRemark = function(valid, rows, object, entity) {
			if (rows.length == 0) {
				valid = false;
				$('#invalidRow')
					.find(
						'.msg')
					.empty();
				$('#invalidRow')
					.find(
						'.modal-body')
					.find(
						'.msg')
					.append("Please select row to add Bulk Remark/Reminder.");
				$("#invalidRow")
					.modal();
			} else {
				valid = true;
			}
			if (self.showCheckRemarkError) {
				valid = false;
				self.showCheckRemarkError = true;
			}
			if (valid == true) {
				console.log("Common Controller submit "
					+ entity);
				object.regularReturns = rows;
				progressBar();

				CommonService
					.saveBulkRemark(object, entity)
					.then(
						function(data) {
							progressBar();
							console
								.log(entity
									+ ' added successfully');

							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
							$('#successMsg').find('.modal-body').find('.msg').append("Saved successfully");
							$("#successMsg").modal();
							window.location.reload();
							self.gotoListPage(page, page)

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});

			}

		}


		self.sendReminder = function(valid, rows, entity) {
			if (rows.length == 0) {
				valid = false;
				$('#invalidRow')
					.find(
						'.msg')
					.empty();
				$('#invalidRow')
					.find(
						'.modal-body')
					.find(
						'.msg')
					.append("Please select row to add Bulk Remark/Reminder.");
				$("#invalidRow")
					.modal();
			} else {
				valid = true;
			}

			if (valid == true) {
				console.log("Common Controller submit "
					+ entity);
				self.temp.regularReturn = rows;
				progressBar();
				CommonService
					.sendReminder(self.temp, entity)
					.then(
						function(data) {
							progressBar();
							console
								.log(entity
									+ ' added successfully');

							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
							$('#successMsg').find('.modal-body').find('.msg').append("Saved Successfully");
							$("#successMsg").modal();
							/*window.location.reload();*/
							self.gotoListPage(page, page)
						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}

		}

		self.enable = function(value, selected) {
			if (typeof value !== 'undefined' && selected) {
				return false;
			} else if (value == '' && selected) {
				return false;
			} else if (value == null && selected) {
				return false;
			} else {
				return true;
			}
		}

		self.disableButton = function(num) {
			console.log(num);
			var pan = "pan1" + num;
			var correctAmount = "correctAmount2" + num;
			var correctTds = "correctTds3" + num;
			var section = "section4" + num;

			var pan1 = document.getElementById(pan);
			var correctAmount2 = document.getElementById(correctAmount);
			var correctTds3 = document.getElementById(correctTds);
			var section4 = document.getElementById(section);
			if (pan1.disabled === true) {
				pan1.disabled = false;
				pan1.required = true;
				pan1.ariaInvalid = true
			} else {
				pan1.disabled = true;
				pan1.required = false;
				pan1.ariaInvalid = false;
			}
			if (correctAmount2.disabled === true) {
				correctAmount2.disabled = false;
				correctAmount2.required = true;
			} else {
				correctAmount2.disabled = true;
				correctAmount2.required = false;
			}
			if (correctTds3.disabled === true) {
				correctTds3.disabled = false;
				correctTds3.required = true;
			} else {
				correctTds3.disabled = true;
				correctTds3.required = false;
			}
			if (section4.disabled === true) {
				section4.disabled = false;
				section4.required = true;
			} else {
				section4.disabled = true;
				section4.required = false;
			}
		}

		self.ok = function() {
			$('#invalidRow')
				.find(
					'.msg')
				.empty();

			$('#successMsg')
				.find(
					'.msg')
				.empty();
			$('#successMsg')
				.find(
					'.headingMsg')
				.empty();

			$('#errorMsg')
				.find(
					'.msg')
				.empty();


		}
		self.submitCorrection = function(valid, entity, documents, rows, page, closeModalId) {
			var valid;
			var v = entity.cd.typeOfCorrection;
			if (typeof rows !== 'undefined') {
				if (rows.length == 0) {
					$('#invalidRow')
						.find(
							'.msg')
						.empty();
					if (v.includes('PAN Updation') || v.includes('Mismatch In TDS Amount') || v.includes('Mismatch In Gross Amount') || v.includes('Section Correction')) {
						valid = false;
						$('#invalidRow')
							.find(
								'.modal-body')
							.find(
								'.msg')
							.append("Please select row to add correction.");
						$("#invalidRow")
							.modal();
					} else if (rows == 'addWithoutData' && remark != undefined && remark != "") {
						valid = true;
					} else {
						if (v.includes('Add Entry/Challan') && entity.cad.challanSupportingDoc != undefined && valid) {
							valid = true;
						} else {
							if (valid && v.includes('Add Entry/Challan')) {
								$('#errorMsg')
									.find('.modal-body')
									.find('.msg')
									.text("Please upload challan supporting document.");
								$("#errorMsg").modal();
								valid = false;
							}
						}
					}

				} else if (rows == 'addWithoutData' && remark != undefined && remark != "") {
					valid = true;
				} else {
					if (v.includes('Add Entry/Challan') && entity.cad.challanSupportingDoc != undefined && valid) {
						valid = true;
					} else {
						if (valid && v.includes('Add Entry/Challan')) {
							$('#errorMsg')
								.find('.modal-body')
								.find('.msg')
								.text("Please upload challan supporting document.");
							$("#errorMsg").modal();
							valid = false;
						}
					}
				}
			}
			else {
				$('#invalidRow')
					.find(
						'.msg')
					.empty();
				if (v.includes('PAN Updation') || v.includes('Mismatch In TDS Amount') || v.includes('Mismatch In Gross Amount') || v.includes('Section Correction')) {
					valid = false;
					$('#invalidRow')
						.find(
							'.modal-body')
						.find(
							'.msg')
						.append("Please select row to add correction.");
					$("#invalidRow")
						.modal();
				} else {
					if (v.includes('Add Entry/Challan') && entity.cad.challanSupportingDoc != undefined && valid) {
						valid = true;
					} else {
						if (valid && v.includes('Add Entry/Challan')) {
							$('#errorMsg')
								.find('.modal-body')
								.find('.msg')
								.text("Please upload challan supporting document.");
							$("#errorMsg").modal();
							valid = false;
						}
					}
				}
			}

			/* else {
				var a = [];
				var map = {};
				for (let i = 0; i < rows.length; i++) {
					if (v.includes('PAN Updation')) {
						var correctPan = rows[i].correctPan
						if (typeof correctPan === 'undefined') {
							map = { PU: '' };
						} else if (correctPan === '') {
							map = { PU: '' };
						} else {
							map = { PU: correctPan };
						}
						a.push(map);
					} if (v.includes('Mismatch In Gross Amount')) {
						var correctAmountPaid = rows[i].correctAmountPaid
						if (typeof correctAmountPaid === 'undefined') {
							map = { MITA: '' };
						} else if (correctAmountPaid === '') {
							map = { MITA: '' };
						} else {
							map = { MITA: correctAmountPaid };
						}
						a.push(map);
					} if (v.includes('Mismatch In TDS Amount')) {
						var correctTds = rows[i].correctTds
						if (typeof correctTds === 'undefined') {
							map = { MIGA: '' };
						} else if (correctTds === '') {
							map = { MIGA: '' };
						} else {
							map = { MIGA: correctTds };
						}
						a.push(map);
					} if (v.includes('Section Correction')) {
						var correctSection = rows[i].correctSection
						if (typeof correctSection === 'undefined') {
							map = { SC: '' };
						} else if (correctSection === '') {
							map = { SC: '' };
						} else {
							map = { SC: correctSection };
						}
						a.push(map);
					} if (v.includes('Add Entry/Challan')) {
						if (ad) {
							map = { AEC: true };
						} else {
							map = { AEC: '' };
						}
						a.push(map);
					}
	
					for (var ar in a) {
						if (typeof valid === 'undefined' || valid !== false) {
							if (a.hasOwnProperty(ar)) {
								for (var prop in a[ar]) {
									if (a[ar].hasOwnProperty(prop)) {
										if (a[ar][prop] === '') {
											valid = false;
											break;
										} else {
											valid = true;
										}
									}
								}
							}
						} else {
							break;
						}
					}
	
				}
	
			}*/

			if (valid == true) {
				console.log("Common Controller submit "
					+ entity);
				self.entity.clientId = $stateParams.clientId;
				if (rows != 'addWithoutData') {
					entity.correctAmount = rows;
				}
				entity.docs = documents.docs;
				progressBar();
				CommonService
					.saveCorrection(entity, page)
					.then(
						function(data) {
							progressBar();
							console
								.log(entity
									+ ' added successfully');

							angular.element('#' + closeModalId).trigger('click');
							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
							$('#successMsg').find('.modal-body').find('.msg').append("Saved successfully");
							$("#successMsg").modal();
							self.gotoListPage(page, page)

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}
			/* else {
				$('#invalidRow').find('.modal-body').find('.msg').append("Invalid Row Selection");
				$("#invalidRow").modal();*/
		}

		self.addOthersCorrection = function(valid, entity, page, closeModalId) {
			var tan = entity.cd.tanOfCust;

			if (typeof tan === 'undefined') {
				entity.cd.tanOfCust = self.tan;
			}
			var fy = self.search.cd.fy;
			var tan = self.search.cd.tanOfCust;
			var pan = self.search.cd.pan;
			var name = self.search.cd.name;
			var ro = self.search.cd.branchCode;
			var tof = self.search.cd.typeOfForm;
			var mob = self.search.cd.mobileNumber;
			var remark = self.search.cd.remark;

			if (typeof tan === 'undefined') {
				self.search.cd.tanOfCust = self.tan;
				tan = self.tan;
			}
			if (fy == '2023-24' && pan != null && ro != null && tof != null && mob != null && remark != null) {
				valid = true
			} else {
				if (tan != null) {
					if (ro != null && tof != null && mob != null && remark != null && name != null) {
						valid = true;
					} else {
						if (fy != '2023-24' && typeof name === 'undefined') {
							valid = true;
						} else {
							valid = false;
						}
					}
				} else if (name != null) {
					if (ro != null && tof != null && mob != null && remark != null && tan != null) {
						valid = true;
					} else {
						valid = false
					}
				} else {
					valid = false;
				}
			}
			if (valid == true) {

				var quarter = "";
				var typeOfCorrection = "Others ,";
				$.each(self.quarter, function(key, value) {
					if (value != false && value != null) {
						quarter += value + ", ";
					}
				});

				self.search.cd.quarter = quarter;
				self.search.cd.typeOfCorrection = typeOfCorrection;

				console.log("Common Controller submit "
					+ entity);
				self.entity.clientId = $stateParams.clientId;
				progressBar();
				CommonService
					.saveCorrection(entity, page)
					.then(
						function(data) {
							progressBar();
							$window.location.reload;
							console
								.log(entity
									+ ' added successfully');

							angular.element('#' + closeModalId).trigger('click');
							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
							$('#successMsg').find('.modal-body').find('.msg').append(" Saved successfully");
							$("#successMsg").modal();
							self.gotoListPage(page, page)

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}
		}


		self.addOthersCorrectionFY = function(valid, entity, page, closeModalId) {
			var tan = entity.cd.tanOfCust;

			if (typeof tan === 'undefined') {
				entity.cd.tanOfCust = self.tan;
			}
			var fy = self.search.cd.fy;
			var tan = self.search.cd.tanOfCust;
			var pan = self.search.cd.pan;
			var name = self.search.cd.name;
			var ro = self.search.cd.branchCode;
			var tof = self.search.cd.typeOfForm;
			var mob = self.search.cd.mobileNumber;
			var remark = self.search.cd.remark;

			if (typeof tan === 'undefined') {
				self.search.cd.tanOfCust = self.tan;
				tan = self.tan;
			}
			if (fy == '2023-24' && pan != null && ro != null && tof != null && mob != null && remark != null) {
				valid = true
			} else {
				if (tan != null) {
					if (fy != null && ro != null && tof != null && mob != null && remark != null && name != null) {
						valid = true;
					} else {
						if (fy != '2023-24' && typeof name === 'undefined') {
							valid = true;
						} else {
							valid = false;
						}
					}
				} else if (name != null) {
					if (fy != null && ro != null && tof != null && mob != null && remark != null && tan != null) {
						valid = true;
					} else {
						valid = false
					}
				} else {
					valid = false;
				}
			}
			if (valid == true) {

				var quarter = "";
				var typeOfCorrection = "Others ,";
				$.each(self.quarter, function(key, value) {
					if (value != false && value != null) {
						quarter += value + ", ";
					}
				});

				self.search.cd.quarter = quarter;
				self.search.cd.typeOfCorrection = typeOfCorrection;

				console.log("Common Controller submit "
					+ entity);
				self.entity.clientId = $stateParams.clientId;
				progressBar();
				CommonService
					.saveCorrection(entity, page)
					.then(
						function(data) {
							progressBar();
							$window.location.reload;
							console
								.log(entity
									+ ' added successfully');

							angular.element('#' + closeModalId).trigger('click');
							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
							$('#successMsg').find('.modal-body').find('.msg').append(" Saved successfully");
							$("#successMsg").modal();
							self.gotoWFYListPage(page, page)

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving Details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}
		}

		self.selectAllQuarter = function() {
			const selectAllCheckbox = document.getElementById('selectAllQuarter');
			const rowCheckboxes = document.querySelectorAll('.checkQuarter');

			selectAllCheckbox.addEventListener('change', function() {
				const isChecked = this.checked;

				rowCheckboxes.forEach(checkbox => {
					checkbox.checked = isChecked;
				});
				var clicked = false;
				if (!clicked) {
					self.quarter.Q1 = 'Q1';
					self.quarter.Q2 = 'Q2';
					self.quarter.Q3 = 'Q3';
					self.quarter.Q4 = 'Q4';
				} else {
					self.quarter = [];
				}
				clicked = !clicked;
				this.innerHTML = clicked;
			});

			rowCheckboxes.forEach(checkbox => {
				checkbox.addEventListener('change', function() {
					if (!this.checked) {
						selectAllCheckbox.checked = false;
					} else {
						// Check if all row checkboxes are checked
						const allChecked = [...rowCheckboxes].every(checkbox => checkbox.checked);
						selectAllCheckbox.checked = allChecked;
					}
				});
			});
		}

		self.typeOfCorrectionValue = function(number) {
			if (number === 6) {
				self.unselectOthers(number);
				/*$(".added").removeClass("hid");
				$(".added").addClass("dis");
				$(".nexts").removeClass("dis");
				$(".nexts").addClass("hid");*/
				var exempted = document.getElementById("exempted");
				if (exempted.hidden === false) {
					exempted.hidden = true;
				}

			} else if (number === 7) {
				self.unselectOthers(number);
				var exempted = document.getElementById("exempted");
				if (exempted.hidden === true) {
					exempted.hidden = false;
				} else {
					exempted.hidden = true;
				}
			}
			else {
				self.unselectOthersValue(number);
				self.unselectExempted(number);
				var exempted = document.getElementById("exempted");
				if (exempted.hidden === false) {
					exempted.hidden = true;
				}
				/*$(".nexts").removeClass("hid");
				$(".nexts").addClass("dis");
				$(".added").removeClass("dis");
				$(".added").addClass("hid");*/
			}
		}

		self.unselectOthersValue = function(selectedIndex) {
			var checkboxes = document.getElementsByName("typeOfCorrection");
			console.log(self.search.typeOfCorrection);
			for (var i = 0; i < checkboxes.length; i++) {
				if (i !== selectedIndex) {
					checkboxes[6].checked = false;
					delete self.typeOfCorrection[6];
				}
			}
		}
		self.unselectExempted = function(selectedIndex) {
			var checkboxes = document.getElementsByName("typeOfCorrection");
			console.log(self.search.typeOfCorrection);
			for (var i = 0; i < checkboxes.length; i++) {
				if (i !== selectedIndex) {
					checkboxes[7].checked = false;
					delete self.typeOfCorrection[7];
				}
			}
		}

		self.bulkRemarkReminderBtn = function() {
			if (self.access == false) {
				$('#invalidRow')
					.find(
						'.msg')
					.empty();
				$('#invalidRow')
					.find(
						'.modal-body')
					.find(
						'.msg')
					.append("Please select row to add Bulk Remark/Reminder.");
				$("#invalidRow")
					.modal();
			}
			else {
				$("#bd-example-modal-xl")
					.modal();
			}
		}

		self.unselectOthers_RegularReturn = function(selectedIndex) {
			var checkbox1 = document.getElementById("chkPassport");
			var status = document.getElementById("status");
			var checkbox2 = document.getElementById("chkPassport1");
			var returnFiling = document.getElementById("date");
			var checkbox3 = document.getElementById("chkPassport2");
			var remark = document.getElementById("remark");
			var checkbox4 = document.getElementById("chkPassport3");
			var sendReminderButton = document.getElementById("reminder");

			if (4 === selectedIndex) {
				status.disabled = true;
				returnFiling.disabled = true;
				remark.disabled = true;
				checkbox1.checked = false;
				checkbox2.checked = false;
				checkbox3.checked = false;
			}
			else {
				checkbox4.checked = false;
				sendReminderButton.disabled = true;
			}

		}

		self.unselectOthers = function(selectedIndex) {
			var checkboxes = document.getElementsByName("typeOfCorrection");
			console.log(self.search.typeOfCorrection);
			for (var i = 0; i < checkboxes.length; i++) {
				if (i !== selectedIndex) {
					checkboxes[i].checked = false;
					delete self.typeOfCorrection[i];
				}
			}
		}


		self.getSearchPage = function(valid, entity, pageNo) {
			if (valid = true) {
				self.currentPage = pageNo;
				self.srNo = ((pageNo - 1) * 100) + 1;
				if (self.isNotEmpty($stateParams.searchParams)) {
					CommonService.fetchSearch(entity, $stateParams.searchParams, pageNo - 1);
				} else {
					CommonService.fetch(entity, pageNo - 1);
				}
			}
		}

		self.searchData = function(valid, entity, page) {

			var fy = self.search.cd.fy;
			var tan = self.search.cd.tanOfCust;
			var pan = self.search.cd.pan;
			var name = self.search.cd.name;
			var ro = self.search.cd.branchCode;
			var tof = self.search.cd.typeOfForm;
			var mob = self.search.cd.mobileNumber;
			var exemp = self.search.cd.reasonForExemption;
			/*var remark = self.search.cd.remark;*/

			if (typeof tan === 'undefined') {
				self.search.cd.tanOfCust = self.tan;
				tan = self.tan;
			}
			if (fy == '2023-24' && fy != '' && typeof fy !== 'undefined' && pan != null && pan !== '' && ro != null && ro !== '' && tof != null && mob != null && mob != '') {
				valid = true
			} else {
				if (tan != null) {
					if (typeof fy !== 'undefined' && fy != '' && ro != null && ro !== '' && tof != null && mob != null && mob != '' && name != null && name != '') {
						valid = true;
					} else {
						if (fy != '2023-24' && fy != '' && typeof fy !== 'undefined' && typeof name === 'undefined') {
							valid = true;
						} else {
							valid = false;
						}
					}
				} else if (name != null) {
					if (typeof fy !== 'undefined' && fy != '' && ro != null && ro != '' && tof != null && mob != null && mob != '' && tan != null) {
						valid = true;
					} else {
						valid = false
					}
				} else {
					valid = false;
				}
			}


			var quarter = "";
			var typeOfCorrection = "";
			$.each(self.quarter, function(key, value) {
				if (value != false && value != null) {
					quarter += value + ", ";
				}
			});
			$.each(self.typeOfCorrection, function(key, value) {
				if (value != "" && value != null) {
					typeOfCorrection += value + ", ";
				}
				/*delete self.typeOfCorrection[key];*/
			});
			if (quarter === "" || quarter === null) {
				valid = false;
				self.showQuarterError = true;
				var errMsg = "Quarter is Required";
			}
			if (typeOfCorrection === "" || typeOfCorrection === null) {
				valid = false;
				self.showTOCError = true;
				var errMsg1 = "typeOfCorrection is Required";
			}
			progressBar();
			if (valid == true) {
				$.each(self.search, function(key, value) {
					if (value === "" || value === null) {
						delete self.search[key];
					}
				});

				self.search.cd.typeOfCorrection = typeOfCorrection;
				self.search.cd.quarter = quarter;
				self.lastSearch = self.search.cd;
				var param = JSON.stringify(self.search.cd);
				var toc = typeOfCorrection.substring(0, typeOfCorrection.length - 2);
				if (toc == "Others" || toc == "Exempted" || toc == "Add Entry/Challan" || toc == "Default Correction") {
					if (toc == "Exempted") {
						if (typeof exemp === 'undefined' || exemp == null) {
							valid = false;
						} else {
							valid = true;
						}
					} else {
						valid = true;
					}
					if (valid) {
						$(".pk").removeClass("active");
						$(".sk").addClass("active");
						$(".jk").removeClass("active");
						$(".lk").addClass("active");
					}
				} else {
					self.currentPage = page;
					self.srNo = ((page - 1) * 100) + 1;
					progressBar();
					CommonService.searchTable(entity, param, page - 1).then(
						function(data) {
							progressBar();
							if (typeof self.container !== 'undefined') {
								for (let i = 0; i < self.container.length; i++) {
									for (let j = 0; j < data.entities.length; j++) {
										if (data.entities[j].id == self.container[i].id) {
											data.entities[j] = self.container[i];
										}
									}
								}
							}
							if (data.count == 0) {
								self.tableShow = false;
							} else {
								self.tableShow = true;
							}
							/*if (data.count == 0) {
								$('#successMsg').find('.modal-header').find('.headingMsg').append("Data Not Available");
								$('#successMsg').find('.modal-body').find('.msg').append("Data not found for " + name + ". Kindly select others to proceed");
								$("#successMsg").modal();
								delete self.typeOfCorrection;
								var exempted = document.getElementById("exempted");
								if (exempted.hidden === false) {
									exempted.hidden = true;
								}
							} else {*/
							$(".pk").removeClass("active");
							$(".sk").addClass("active");
							$(".jk").removeClass("active");
							$(".lk").addClass("active");
							/*}*/
						});
				}
			}
		}


		self.nextBtn = function() {
			$(".pk").removeClass("active");
			$(".sk").addClass("active");
			$(".jk").removeClass("active");
			$(".lk").addClass("active");
		}

		self.previousBtn = function() {
			delete self.container;
			$(".pk").addClass("active");
			$(".sk").removeClass("active");
			$(".jk").addClass("active");
			$(".lk").removeClass("active");
		}
		self.btnNext = function() {
			$(".t1").removeClass("active");
			$(".t2").addClass("active");
			$(".tab1").removeClass("active");
			$(".tab2").addClass("active");

		}
		self.btnPrevious = function() {
			$(".t2").removeClass("active");
			$(".t1").addClass("active");
			$(".tab2").removeClass("active");
			$(".tab1").addClass("active");

		}


		self.closeDialog = function() {
			$(".POPmain").addClass("displayNone");
			/*let d = document.getElementById('popup')
			d.style.display = "none"
			d.close()*/
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
			self.PreviousNo = 1;
			self.srNo = 1;
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

		self.gotoHelpSC = function(helpFolder, helpPage) {
			var newTabUrl = $state.href("homeHelpSC.homePage", {
				"helpFolder": helpFolder,
				"helpPage": helpPage,
			});
			window.open(newTabUrl, '_blank');
		}

		self.gotoHelpWOT = function(helpFolder, helpPage) {
			var newTabUrl = $state.href("homeHelpWOT.homePage", {
				"helpFolder": helpFolder,
				"helpPage": helpPage,
			});
			window.open(newTabUrl, '_blank');
		}


		self.changePassword = function(valid, password) {
			if (valid == true) {
				CommonService
					.changePassword(password)
					.then(
						function(data) {
							console
								.log('Course added successfully');
							self.ok();
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
								.error('Error while creating saving details, '
									+ status);
							self.ok();
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

		self.download = function(valid, url) {
			if (valid == true) {
				wait(1000);
				self.fileLoader = url;
				CommonServiceFY.check(url)
					.then(
						function(data) {

							console
								.log(' Download successfully');
							window.open(self.fileLoader, "_blank")
							self.ok();

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
									"File downloaded successfully");
							$("#successMsg")
								.modal();


						},
						function(error) {
							console
								.error('Error while downloading details, '
									+ status);
							self.ok();
							$('#errorMsg')
								.find(
									'.modal-body')
								.find(
									'.msg')
								.append("File not found.");
							$("#errorMsg")
								.modal();


						});
			}

		}

		self.downloadTAR = function(valid, entity) {
			if (valid == true) {

				if (entity.typeOfReport == 'Annual Report') {
					entity.typeOfForm = '""';
					entity.quarter = '""';
				}

				var url = 'apigenerateReport/files/' + entity.TAN + '/' + entity.typeOfForm + '/' + entity.fy + '/' + entity.quarter + '/' + entity.typeOfReport;

				wait(1000);
				self.fileLoader = url;
				CommonServiceFY.check(url)
					.then(
						function(data) {
							window.open(self.fileLoader, "_blank")
							console
								.log(' Download successfully');
							self.ok();
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
									"File downloaded successfully");
							$("#successMsg")
								.modal();


						},
						function(error) {
							console
								.error('Error while downloading details, '
									+ status);
							self.ok();
							$('#errorMsg')
								.find(
									'.modal-body')
								.find(
									'.msg')
								.append("File not found for this TAN Number.");
							$("#errorMsg")
								.modal();


						});
			}

		}

		self.downloadSingle = function(valid, url) {
			if (valid == true) {
				wait(1000);
				self.fileLoader = url;
				CommonServiceFY.check(url)
					.then(
						function(data) {
							window.open(self.fileLoader, "_blank")
							console
								.log(' Download successfully');
							self.ok();
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
									"File downloaded successfully");
							$("#successMsg")
								.modal();


						},
						function(error) {
							console
								.error('Error while downloading details, '
									+ status);
							self.ok();
							$('#errorMsg')
								.find(
									'.modal-body')
								.find(
									'.msg')
								.append("File not found for this PAN Number.");
							$("#errorMsg")
								.modal();


						});
			}

		}


		self.downloadMultiple = function(valid, url) {
			if (valid == true) {
				wait(1000);
				self.fileLoader = url;
				CommonServiceFY.check(url)
					.then(
						function(data) {
							window.open(self.fileLoader, "_blank")
							console
								.log(' Download successfully');

							self.ok();
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
									"File downloaded successfully");
							$("#successMsg")
								.modal();


						},
						function(error) {
							console
								.error('Error while downloading details, '
									+ status);
							self.ok();
							$('#errorMsg')
								.find(
									'.modal-body')
								.find(
									'.msg')
								.append("File not found for this PAN Numbers.");
							$("#errorMsg")
								.modal();


						});
			}

		}
		self.checkBranchZip = function(valid, url) {
			if (valid == true) {
				wait(1000);
				var branchCode = $stateParams.branchCode;
				url = url + '/' + branchCode;
				self.fileLoader = url;
				CommonServiceFY.check(url)
					.then(
						function(data) {
							/*window.open(self.fileLoader, "_blank")*/
							console
								.log(' Check zip File');

							$('#download')
								.find(
									'.modal-header')
								.find(
									'.headingMsg')
								.append(
									"Download Zip");
							$('#download')
								.find(
									'.modal-body')
								.find('.msg')
								.append(
									"Please select one of the following:");
							$("#download")
								.modal();


						},
						function(error) {
							console
								.error('Zip file not found');

							$('#generateZip')
								.find(
									'.modal-body')
								.find(
									'.msg')
								.append("Zip file not found.");
							$('#generateZip')
								.find(
									'.modal-body')
								.find(
									'.msg1')
								.append("Please click on senerate ZIP file.");
							$("#generateZip")
								.modal();


						});
			}

		}


		self.createBranchZip = function(valid, url) {
			if (valid == true) {
				wait(1000);
				url = url;
				self.fileLoader = url;
				CommonServiceFY.check(url)
					.then(
						function(data) {
							/*window.open(self.fileLoader, "_blank")*/
							console
								.log('Request geneated successfully');

							$('#successMsg')
								.find(
									'.modal-header')
								.find(
									'.headingMsg')
								.append(
									"Request geneated successfully!!!");
							$('#successMsg')
								.find(
									'.modal-body')
								.find('.msg')
								.append(
									"Zip will be available in 2 hours for download.");
							$("#successMsg").modal();
						},
						function(error) {
							console
								.error('Error while downloading details, '
									+ status);
							self.ok();
							$('#errorMsg')
								.find(
									'.modal-body')
								.find(
									'.msg')
								.append("File not found.");
							$("#errorMsg")
								.modal();


						});
			}

		}

		self.downloadBranchZip = function(valid, url) {
			if (valid == true) {
				wait(1000);
				var branchCode = $stateParams.branchCode;
				url = url + '/' + branchCode;
				self.fileLoader = url;
				CommonServiceFY.check(url)
					.then(
						function(data) {
							window.open(self.fileLoader, "_blank")
							console
								.log('Download successfully');
							self.ok();
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
									" Downloaded successfully");
							$("#successMsg")
								.modal();

						},
						function(error) {
							console
								.error('Error while updating details, '
									+ status);
							$('#errorMsg')
								.find(
									'.modal-body')
								.find(
									'.msg')
								.append(
									"Generation of zip file in process, please check after sometime.");
							$("#errorMsg")
								.modal();

						});
			}

		}

		//For downloading with branch16A login
		self.downloadBranchZip = function(valid, url, branchCode) {
			if (valid == true) {
				wait(1000);
				url = url + '/' + branchCode;
				self.fileLoader = url;
				CommonServiceFY.check(url)
					.then(
						function(data) {
							window.open(self.fileLoader, "_blank")
							console
								.log('Download successfully');
							self.ok();
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
									" Downloaded successfully");
							$("#successMsg")
								.modal();

						},
						function(error) {
							console
								.error('Error while updating details, '
									+ status);
							$('#errorMsg')
								.find(
									'.modal-body')
								.find(
									'.msg')
								.append(
									"Generation of zip file in process, please check after sometime.");
							$("#errorMsg")
								.modal();

						});
			}

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
							$('.modal').modal(
								"hide");
							self.ok();
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
									" Downloaded successfully");
							$("#successMsg")
								.modal();

						},
						function(error) {
							console
								.error('Error while updating details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null
								&& error.exceptionMsg != undefined) {
								$('#errorMsg')
									.find(
										'.modal-body')
									.find(
										'.msg')
									.append(
										"Can not download "
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
			self.PreviousNo = 1;
			self.srNo = 1;
			self.batchColor = {};
			self.goodsColor = [];
			self.b = [];
			self.fileName = new FormData();
			$state.go("homeWot.homepage", {
				"branchCode": branchCode,
				"fy": '2023-24'
				/*"fy": '2024-25'*/
                /*"fy": '2025-26'*/
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
		//pranay-detail state for Notice
		self.gotoDetailPage1 = function(entity, id, din, page) {
			$state.go("home.detail1", {
				"entity": entity,
				"din": din,
				"id": id,
				"page": page,

			});
		}

		self.submitRemark = function(valid, entity, object, closeModalId) {
			if (valid == true) {
				var downloadFile = object.blob;

				if (downloadFile != null && downloadFile != undefined) {
					console.log('remark with file submitData:CC');
					delete object.blob;
					CommonService.saveRemarkWithFile(downloadFile, object, entity).then(function(data) {
						console.log(entity + ' added successfully');
						$('.modal').modal("hide");
						self.ok();
						$('#remarkSuccessMsg').find('.modal-header').find('.headingMsg').text("Successful.");
						$('#remarkSuccessMsg').find('.modal-body').find('.msg').text("Remark & File add  Successfully.");
						$("#remarkSuccessMsg").modal();
						// window.alert(entity+data.msg);
					}, function(error) {
						console.error('Error while Adding Remark, ' + status);
						if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
							if (error.exceptionMsg == 'Please add reason for updation.') {
								$('#updateMsg').find('.modal-header').find('.headingMsg').text("Information.");
								$('#updateMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
								$("#updateMsg").modal({
									keyboard: false,
									backdrop: 'static'
								});
							}
						}
					});
				}
				else {
					console.log('remark without file submitData:CC');
					CommonService.saveRemark(object, entity).then(function(data) {
						console.log(entity + ' added successfully');
						$('.modal').modal("hide");
						self.ok();
						$('#remarkSuccessMsg').find('.modal-header').find('.headingMsg').text("Successful.");
						$('#remarkSuccessMsg').find('.modal-body').find('.msg').text("Remark added successfully.");
						$("#remarkSuccessMsg").modal();

					}, function(error) {
						console.error('Error while updating Details, ' + status);
						self.ok();
						if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
							$('#errorMsg').find('.modal-header').find('.headingMsg').text("Information.");
							$('#errorMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
							$("#errorMsg").modal({
								keyboard: false,
								backdrop: 'static'
							});
						}
					});
				}
				//file check
			}//main if
		}//submitData

		self.regenerateCorrectionRequest = function(valid, entity, object, page, closeModalId) {
			if (valid == true) {
				console.log('remark without file submitData:CC');
				CommonService.regenerateCorrectionRequest(object, entity).then(function(data) {
					console.log(entity + ' added successfully');
					$('.modal').modal("hide");
					self.ok();
					$('#SuccessMsg').find('.modal-header').find('.headingMsg').text("Successful.");
					$('#SuccessMsg').find('.modal-body').find('.msg').text("Correction Regenerate successfully.");
					$("#SuccessMsg").modal();
					self.gotoListPage(entity, entity)
				}, function(error) {
					console.error('Error while updating Details, ' + status);
					self.ok();
					if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
						$('#errorMsg').find('.modal-header').find('.headingMsg').text("Information.");
						$('#errorMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
						$("#errorMsg").modal({
							keyboard: false,
							backdrop: 'static'
						});
					}
				});
				//file check
			}//main if
		}//submitData

		self.regenerateCorrectionRequestFY = function(valid, entity, object, page, closeModalId) {
			if (valid == true) {
				console.log('remark without file submitData:CC');
				CommonService.regenerateCorrectionRequest(object, entity).then(function(data) {
					console.log(entity + ' added successfully');
					$('.modal').modal("hide");
					self.ok();
					$('#SuccessMsg').find('.modal-header').find('.headingMsg').text("Successful.");
					$('#SuccessMsg').find('.modal-body').find('.msg').text("Correction Regenerate successfully.");
					$("#SuccessMsg").modal();
					self.gotoWFYListPage(entity, entity)
				}, function(error) {
					console.error('Error while updating Details, ' + status);
					self.ok();
					if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
						$('#errorMsg').find('.modal-header').find('.headingMsg').text("Information.");
						$('#errorMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
						$("#errorMsg").modal({
							keyboard: false,
							backdrop: 'static'
						});
					}
				});
				//file check
			}//main if
		}//submitData


		self.uploadCertificate = function(valid, entity, object) {
			if (valid == true) {
				var downloadFile = object.blob;
				if (downloadFile != null && downloadFile != undefined) {
					if (downloadFile.size > 10485760) {
						$('#errorMsg').find('.modal-header').find('.headingMsg').text("Information.");
						$('#errorMsg').find('.modal-body').find('.msg').text("File size should be less than 10mb.");
						$("#errorMsg").modal({
							keyboard: false,
							backdrop: 'static'
						});
					} else {
						console.log('CommonController add Certificate');
						CommonService.uploadCertificate(downloadFile, object, entity).then(function(data) {
							console.log(entity + ' added successfully');
							$('.modal').modal("hide");
							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').text("Successful.");
							$('#successMsg').find('.modal-body').find('.msg').text("Certificate Uploaded Successfully.");
							$("#successMsg").modal();
						}, function(error) {
							console.error('CommonController error while adding Certificate');
							if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-header').find('.headingMsg').text("Information.");
								$('#errorMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal({
									keyboard: false,
									backdrop: 'static'
								});
							}
						});
					}
				}
			}
		}

		self.submitCertificate = function(valid, entity, object, closeModalId) {
			if (valid == true) {
				var downloadFile = object.blob;

				if (downloadFile != null && downloadFile != undefined) {
					console.log('remark with file submitData:CC');
					delete object.blob;
					CommonService.saveRemarkWithFile(downloadFile, object, entity).then(function(data) {
						console.log(entity + ' added successfully');
						$('.modal').modal("hide");
						self.ok();
						$('#remarkSuccessMsg').find('.modal-header').find('.headingMsg').text("Successful.");
						$('#remarkSuccessMsg').find('.modal-body').find('.msg').text("Remark & File add  successfully.");
						$("#remarkSuccessMsg").modal();
						// window.alert(entity+data.msg);
					}, function(error) {
						console.error('Error while Adding Remark, ' + status);
						if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
							if (error.exceptionMsg == 'Please add reason for updation.') {
								$('#updateMsg').find('.modal-header').find('.headingMsg').text("Information.");
								$('#updateMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
								$("#updateMsg").modal({
									keyboard: false,
									backdrop: 'static'
								});
							}
						}
					});
				}
				else {
					console.error('Error while updating details, ' + status);
					self.ok();
					$('#errorMsg').find('.modal-header').find('.headingMsg').text("Certificate not found.");
					$('#errorMsg').find('.modal-body').find('.msg').text("Can not resolve without certificate");
					//file check
				}
			}//main if
		}//submitData

		// Ticket Remark

		self.submitTicketRemark = function(valid, entity, object, closeModalId) {
			if (valid == true) {
				var downloadFile = object.blob;
				var fy = object.fy;
				var branchCode = object.branchCode;
				if (downloadFile != null && downloadFile != undefined) {
					console.log('remark with file submitData:CC');
					delete object.blob;
					CommonService.saveTicketRemarkWithFile(downloadFile, object, entity).then(function(data) {
						console.log(entity + ' added successfully');
						$('.modal').modal("hide");
						self.ok();
						$('#successMsg').find('.modal-header').find('.headingMsg').text("Successful.");
						$('#successMsg').find('.modal-body').find('.msg').text("Remark & File add Successfully.");
						$("#successMsg").modal();
						// window.alert(entity+data.msg);
					}, function(error) {
						console.error('Error while Adding Remark, ' + status);
						if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
							if (error.exceptionMsg == 'Please add reason for updation.') {
								$('#updateMsg').find('.modal-header').find('.headingMsg').text("Information.");
								$('#updateMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
								$("#updateMsg").modal({
									keyboard: false,
									backdrop: 'static'
								});
							}
						}
					});
				}
				else {
					console.log('remark without file submitData:CC');
					CommonServiceFY.add(object, entity, 'Remark', fy, branchCode).then(function(data) {
						console.log(entity + ' added successfully');
						$('.modal').modal("hide");
						self.ok();
						$('#successMsg').find('.modal-header').find('.headingMsg').text("Successful.");
						$('#successMsg').find('.modal-body').find('.msg').text("Remark add successfully.");
						$("#successMsg").modal();
					}, function(error) {
						console.error('Error while updating Details, ' + status);
						self.ok();
						if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
							$('#errorMsg').find('.modal-header').find('.headingMsg').text("Information.");
							$('#errorMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
							$("#errorMsg").modal({
								keyboard: false,
								backdrop: 'static'
							});
						}
					});
				}
				//file check
			}//main if
		}//submitData




		//Pranay

		self.gotoDetailFormPage = function(entity, detailId, fy, branchCode, page) {
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
			self.PreviousNo = 1;
			self.srNo = 1;
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
			self.lastSearch = {};
			self.currentPage = 1;
			self.PreviousNo = 1;
			self.srNo = 1;
			self.batchColor = {};
			self.goodsColor = [];
			self.b = [];
			self.fileName = new FormData();

			$state.go("home.list", {
				"entity": entity,
				"page": page
			});
			self.lastSearch = {};

		}

		self.submitTicket = function(valid, entity, saveData) {
			if (valid == true) {
				var file = saveData.file;
				console.log("Common Controller submit "
					+ entity);

				progressBar();
				CommonService
					.submitTicket(file, saveData, entity)
					.then(
						function(data) {
							console.log(entity + ' added successfully');
							$('.modal').modal("hide");
							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').text("Successful.");
							$('#successMsg').find('.modal-body').find('.msg').text("Ticket added  successfully.");
							$("#successMsg").modal();
							self.gotoListPage(entity, entity);

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving Details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}
		}

		self.submitTicketFY = function(valid, entity, saveData) {
			if (valid == true) {
				var file = saveData.file;
				console.log("Common Controller submit "
					+ entity);

				progressBar();
				CommonService
					.submitTicket(file, saveData, entity)
					.then(
						function(data) {
							console.log(entity + ' added successfully');
							$('.modal').modal("hide");
							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').text("Successful.");
							$('#successMsg').find('.modal-body').find('.msg').text("Ticket added successfully.");
							$("#successMsg").modal();
							self.gotoWFYListPage(entity, entity);

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving Details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}
		}

		self.gotoDownloadCertificatePage = function(branchCode, entity, page) {
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
			self.PreviousNo = 1;
			self.srNo = 1;
			self.batchColor = {};
			self.goodsColor = [];
			self.b = [];
			self.fileName = new FormData();

			$state.go("homeWot.downloadCertificate", {
				"branchCode": branchCode,
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
			self.PreviousNo = 1;
			self.srNo = 1;
			self.batchColor = {};
			self.goodsColor = [];
			self.b = [];
			self.fileName = new FormData();

			$state.go("homeWot.list", {
				"entity": entity,
				"page": page
			});

		}

		self.gotoWFYTicketListPage = function(entity, page, branchCode, fy) {
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
			self.PreviousNo = 1;
			self.srNo = 1;
			self.batchColor = {};
			self.goodsColor = [];
			self.b = [];
			self.fileName = new FormData();

			$state.go("homeWot.ticketlist", {
				"entity": entity,
				"page": page,
				"branchCode": branchCode,
				"fy": fy
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
			self.PreviousNo = 1;
			self.srNo = 1;
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

				/*		progressBar();*/
				CommonServiceFY
					.add(object, entity, type,
						$stateParams.fy,
						$stateParams.branchCode)
					.then(
						function(data) {
							/*progressBar();*/
							console
								.log(entity
									+ ' added successfully');
							$('.modal').modal(
								"hide");
							self.ok();
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
									" Saved successfully");
							$("#successMsg")
								.modal();

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
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

							$('.modal').modal(
								"hide");
							self.ok();
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
									" Saved successfully");
							$("#successMsg")
								.modal();

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
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

							$('.modal').modal(
								"hide");
							self.ok();
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
									" Saved successfully");
							$("#successMsg")
								.modal();

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
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

								$('.modal').modal(
									"hide");
								self.ok();
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
										" Updated successfully");
								$("#successMsg")
									.modal();

							},
							function(error) {
								progressBar();
								console
									.error('Error while updating details, '
										+ status);
								self.ok();
								if (error.exceptionMsg != null
									&& error.exceptionMsg != undefined) {
									$('#errorMsg')
										.find(
											'.modal-body')
										.find(
											'.msg')
										.append(
											"Can not update "
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
							" Update allowed only for pending verification");
					$("#successMsg1").modal();

				}

			}
		}
		self.submit = function(valid, object, entity,
			closeModalId) {
			if (valid == true) {
				console.log("Common Controller submit "
					+ entity);
				self.object = object;
				progressBar();
				CommonService
					.save(self.object, entity)
					.then(
						function(data) {
							console
								.log(entity
									+ ' Add successfully');

							self.ok();
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
									" Saved successfully!");
							$("#successMsg")
								.modal();
							self.gotoListPage(
								entity, entity);

						},
						function(error) {
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
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


		self.submitCorrectionFY = function(valid, entity, documents, rows, page, closeModalId) {
			var valid;
			var v = entity.cd.typeOfCorrection;
			if (typeof rows !== 'undefined') {
				if (rows.length == 0) {
					$('#invalidRow')
						.find(
							'.msg')
						.empty();
					if (v.includes('PAN Updation') || v.includes('Mismatch In TDS Amount') || v.includes('Mismatch In Gross Amount') || v.includes('Section Correction')) {
						valid = false;
						$('#invalidRow')
							.find(
								'.modal-body')
							.find(
								'.msg')
							.append("Please select row to add correction.");
						$("#invalidRow")
							.modal();
					} else if (rows == 'addWithoutData' && remark != undefined && remark != "") {
						valid = true;
					} else {
						if (v.includes('Add Entry/Challan') && entity.cad.challanSupportingDoc != undefined && valid) {
							valid = true;
						} else {
							if (valid && v.includes('Add Entry/Challan')) {
								$('#errorMsg')
									.find('.modal-body')
									.find('.msg')
									.text("Please upload challan supporting document.");
								$("#errorMsg").modal();
								valid = false;
							}
						}
					}

				} else if (rows == 'addWithoutData' && remark != undefined && remark != "") {
					valid = true;
				}
				else {
					if (v.includes('Add Entry/Challan') && entity.cad.challanSupportingDoc != undefined && valid) {
						valid = true;
					} else {
						if (valid && v.includes('Add Entry/Challan')) {
							$('#errorMsg')
								.find('.modal-body')
								.find('.msg')
								.text("Please upload challan supporting document.");
							$("#errorMsg").modal();
							valid = false;
						}
					}
				}
			}
			else {
				$('#invalidRow')
					.find(
						'.msg')
					.empty();
				if (v.includes('PAN Updation') || v.includes('Mismatch In TDS Amount') || v.includes('Mismatch In Gross Amount') || v.includes('Section Correction')) {
					valid = false;
					$('#invalidRow')
						.find(
							'.modal-body')
						.find(
							'.msg')
						.append("Please select row to add correction.");
					$("#invalidRow")
						.modal();
				} else {
					if (v.includes('Add Entry/Challan') && entity.cad.challanSupportingDoc != undefined && valid) {
						valid = true;
					} else {
						if (valid && v.includes('Add Entry/Challan')) {
							$('#errorMsg')
								.find('.modal-body')
								.find('.msg')
								.text("Please upload challan supporting document.");
							$("#errorMsg").modal();
							valid = false;
						}
					}
				}
			}
			/*else {
				var a = [];
				var map = {};
				for (let i = 0; i < rows.length; i++) {
					if (v.includes('PAN Updation')) {
						var correctPan = rows[i].correctPan
						if (typeof correctPan === 'undefined') {
							map = { PU: '' };
						} else if (correctPan === '') {
							map = { PU: '' };
						} else {
							map = { PU: correctPan };
						}
						a.push(map);
					} if (v.includes('Mismatch In Gross Amount')) {
						var correctAmountPaid = rows[i].correctAmountPaid
						if (typeof correctAmountPaid === 'undefined') {
							map = { MITA: '' };
						} else if (correctAmountPaid === '') {
							map = { MITA: '' };
						} else {
							map = { MITA: correctAmountPaid };
						}
						a.push(map);
					} if (v.includes('Mismatch In TDS Amount')) {
						var correctTds = rows[i].correctTds
						if (typeof correctTds === 'undefined') {
							map = { MIGA: '' };
						} else if (correctTds === '') {
							map = { MIGA: '' };
						} else {
							map = { MIGA: correctTds };
						}
						a.push(map);
					} if (v.includes('Section Correction')) {
						var correctSection = rows[i].correctSection
						if (typeof correctSection === 'undefined') {
							map = { SC: '' };
						} else if (correctSection === '') {
							map = { SC: '' };
						} else {
							map = { SC: correctSection };
						}
						a.push(map);
					} if (v.includes('Add Entry/Challan')) {
						if (ad) {
							map = { AEC: true };
						} else {
							map = { AEC: '' };
						}
						a.push(map);
					}
			
					for (var ar in a) {
						if (typeof valid === 'undefined' || valid !== false) {
							if (a.hasOwnProperty(ar)) {
								for (var prop in a[ar]) {
									if (a[ar].hasOwnProperty(prop)) {
										if (a[ar][prop] === '') {
											valid = false;
											break;
										} else {
											valid = true;
										}
									}
								}
							}
						} else {
							break;
						}
					}
				}
			
			}
		*/
			if (valid == true) {
				console.log("Common Controller submit "
					+ entity);
				self.entity.clientId = $stateParams.clientId;
				if (rows != 'addWithoutData') {
					entity.correctAmount = rows;
				}
				entity.docs = documents.docs;
				progressBar();
				CommonService
					.saveCorrection(entity, page)
					.then(
						function(data) {
							progressBar();
							console
								.log(entity
									+ ' added successfully');

							angular.element('#' + closeModalId).trigger('click');
							self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
							$('#successMsg').find('.modal-body').find('.msg').append("Saved successfully");
							$("#successMsg").modal();
							self.gotoWFYListPage(page, page)

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
							if (error == 'Supporting Document is Required') {
								$('#errorMsg').find('.modal-body').find('.msg').append("Supporting document is empty");
								$("#errorMsg").modal();
							} else if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							}
							else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}
		}



		self.saveDocument = function(valid, object, entity, closeModalId) {// remove
			if (valid == true) {
				console.log("Common Controller submit " + entity);
				//self.object = object;
				progressBar();
				CommonService
					.documentSave(object, entity)
					.then(
						function(data) {
							progressBar();
							console
								.log(entity
									+ ' Add successfully');
							self.gotoListPage(
								entity, entity);

							angular
								.element(
									'#'
									+ closeModalId)
								.trigger(
									'click');

							$('#myModal').hide();
							self.ok();
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
									" Saved successfully!");
							$("#successMsg")
								.modal();

						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null
								&& error.exceptionMsg != undefined) {
								$('#errorMsg')
									.find(
										'.modal-body')
									.find(
										'.msg')
									.append(
										"Can not save "
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
		}// close saveDocument
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
							self.ok();
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
									" Saved successfully!");
							$("#successMsg")
								.modal();

						},
						function(error) {
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null
								&& error.exceptionMsg != undefined) {
								$('#errorMsg')
									.find(
										'.modal-body')
									.find(
										'.msg')
									.append(
										"Can not save "
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
				.log("Common Controller get entity data");
			return CommonService.getEntity();
		}
		self.getEntityDataFY = function() {
			console
				.log("Common Controller get entity data");
			return CommonServiceFY.getEntity();
		}

		/*self.getPage = function(valid, entity, pageNo) {
			if (valid == true) {
				self.currentPage = pageNo;
				self.srNo = ((pageNo - 1) * 100) + 1;
				self.onGo();
				if (self.isNotEmpty($stateParams.searchParams)) {
					CommonService.fetchSearch(entity, $stateParams.searchParams, pageNo - 1);
				} else {
					CommonService.fetch(entity, pageNo - 1);
				}
			}
		}*/



		self.getPage = function(valid, entity, pageNo) {
			if (valid == true) {
				self.srNo = ((pageNo - 1) * 100 + 1);
				self.onGo();
				self.currentPage = pageNo;
				for (var key in self.search) {
					let value = self.search[key];
					if (value == "" || value == null) {
						delete self.search[key];
					}
				}
				if (self.isNotEmpty(self.search)) {
					CommonService.fetchSearch(entity, JSON.stringify(self.search), pageNo - 1);
				} else {
					CommonService.fetch(entity, pageNo - 1);
				}
			}
		}






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
				self.currentPage = 1;
				self.PreviousNo = 1;
				self.srNo = 1;
				$.each(self.search, function(key, value) {
					if (value === "" || value === null) {
						delete self.search[key];
					}
				});
				var param = JSON.stringify(self.search);
				self.lastSearch = self.search;
				$state.go("home.search", {
					"entity": entity,
					"page": page,
					"searchParams": param
				});
			}
		}
		self.searchFYEntities = function(valid, entity, page) {
			if (valid = true) {
				self.currentPage = 1;
				self.PreviousNo = 1;
				self.srNo = 1;
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
							self.ok();
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
									" Updated successfully");
							$("#successMsg")
								.modal();

						},
						function(error) {
							console
								.error('Error while updating details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null
								&& error.exceptionMsg != undefined) {
								$('#errorMsg')
									.find(
										'.modal-body')
									.find(
										'.msg')
									.append(
										"Can not update "
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

		self.updateForm = function(valid, object, entity, remark) {// --remove
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
							$('#successMsgDetail')
								.find(
									'.modal-header')
								.find(
									'.headingMsg')
								.append(
									"Successfull");
							$('#successMsgDetail')
								.find(
									'.modal-body')
								.find('.msg')
								.append(
									" Updated successfully");
							$("#successMsgDetail")
								.modal();

						},
						function(error) {
							console
								.error('Error while updating details, '
									+ status);
							self.ok();
							if (error.exceptionMsg != null
								&& error.exceptionMsg != undefined) {
								$('#errorMsg')
									.find(
										'.modal-body')
									.find(
										'.msg')
									.append(
										"Can not update "
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

		self.save = function(valid, object, entity) {
			if (valid == true) {
				CommonService.save(object, entity).then(function(data) {

					$('.modal').modal(
						"hide");
					self.ok();
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
							" Saved successfully");
					$("#successMsg")
						.modal();
				},
					function(error) {
						progressBar();
						console
							.error('Error while creating saving Details, '
								+ status);
						if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
							$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
							$("#errorMsg").modal();
						} else {
							for (var i = 0; i < error.fieldErrors.length; i++) {
								var obj = error.fieldErrors[i];
								document.getElementById(obj.fieldName).innerHTML = obj.message;
							}
						}
					});
			}
		}

		self.updateDeductee = function(valid, temp, object, remarkId, deducteeId, entity) {
			if (valid == true) {
				console.log("Common Controller addDeducteeRemark");
				if (temp.typeOfAction == 'Approved') {
					CommonService.updatedeductee(object, remarkId, deducteeId, entity)
						.then(
							function(data) {
								console
									.log(entity
										+ ' Updated Successfully');
								$('.modal').modal(
									"hide");
								self.ok();
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
									.error('Error while updating details, '
										+ status);
								self.ok();
								if (error.exceptionMsg != null
									&& error.exceptionMsg != undefined) {
									$('#errorMsg')
										.find(
											'.modal-body')
										.find(
											'.msg')
										.append(
											"Can not update "
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
				else {
					self.entity.deductee.resolved = true;
					CommonService.rejectductee(temp.rejectRemark, self.entity.deductee, remarkId, deducteeId, entity)
						.then(
							function(data) {
								console
									.log(entity
										+ ' Updated Successfully');
								$('.modal').modal(
									"hide");
								self.ok();
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
									.error('Error while updating details, '
										+ status);
								self.ok();
								if (error.exceptionMsg != null
									&& error.exceptionMsg != undefined) {
									$('#errorMsg')
										.find(
											'.modal-body')
										.find(
											'.msg')
										.append(
											"Can not update "
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
		}



		var wait = function(ms) {
			var start = new Date().getTime();
			var end = start;
			while (end < start + ms) {
				end = new Date().getTime();
			}
		}

		self.isNotEmpty = function(obj) {
			for (var key in obj) {
				if (obj.hasOwnProperty(key))
					return true;
			}
			return false;
		}


		self.getPages = function(valid, entity, pageNo) {
			if (valid == true) {
				self.srNo = ((pageNo - 1) * 100 + 1);
				self.onGo();
				self.currentPage = pageNo;
				for (var key in self.search) {
					let value = self.search[key];
					if (value == "" || value == null) {
						delete self.search[key];
					}
				}
				if (self.isNotEmpty(self.search)) {
					CommonServiceFY.fetchSearch(entity, JSON.stringify(self.search), pageNo - 1, $stateParams.fy, $stateParams.branchCode);
				} else {
					CommonServiceFY.fetch(entity, pageNo - 1);
				}
			}
		}



		/*self.getPages = function(valid, entity, pageNo) {
			if (valid = true) {
				self.currentPage = pageNo;
				self.srNo = ((pageNo - 1) * 100) + 1;
				self.onGo();
				if (self.isNotEmpty($stateParams.searchParams)) {
					CommonServiceFY.fetchSearch(entity, $stateParams.searchParams, pageNo - 1, $stateParams.fy, $stateParams.branchCode);
				} else {
					CommonServiceFY.fetch(entity, pageNo - 1);
				}
			}
		}*/

		self.onGo = function() {
			$('[data-toggle="check-all"]').html('Check All');
			$('.dropdown-list input[type="checkbox"]').prop('checked', false);
			$('.sliderButton input[type="checkbox"]').prop('checked', false);
			$('.hideClass').hide();
		}

		self.date = function(date) {
			date = new Date(
				date.getTime()
				+ Math
					.abs(date
						.getTimezoneOffset() * 60000));
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
					self.ok();
					$('#successMsg')
						.find('.modal-header')
						.find('.headingMsg')
						.text("Successfull!");
					$('#successMsg')
						.find('.modal-body')
						.find('.msg')
						.text(" Deleted successfully!");
					$("#successMsg").modal();

				},
					function(error) {
						console.error('Error while creating saving details, ' + status);
						self.ok();
						if (error.exceptionMsg != null
							&& error.exceptionMsg != undefined) {
							$('#errorMsg')
								.find('.modal-body')
								.find('.msg')
								.text("Can not save " + entity + " : "
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

		self.generateExcel = function(url) {
			$.each(self.search, function(key, value) {
				if (value === "" || value === null) {
					delete self.search[key];
				}
			});

			if (self.isEmpty(self.search)) {
				if ($stateParams.searchParams == undefined) {
					self.search = "{}";
					url = url + self.search;
				} else {
					self.search = JSON.parse($stateParams.searchParams);
					wait(1000);
					url = url.concat(JSON.stringify(self.search));
				}
			} else {
				wait(1000);
				url = url.concat(JSON.stringify(self.search));
			}
			self.loader = url;
			window.open(self.loader, "_blank");
			/*$window.location.reload();*/
		}
		self.isEmpty = function(obj) {
			for (var key in obj) {
				if (obj.hasOwnProperty(key))
					return false;
			}
			return true;
		}

		self.getBaseFormType = function() {
			var formType = self.entity.typeOfForm;
			return formType.split('-')[0];
		}

		self.generateProcessExcel = function(url, modal) {
			if ($stateParams.searchParams == undefined) {
				if (self.isEmpty(self.search)) {
					self.search.branchCode = $stateParams.branchCode;
					self.search.fy = $stateParams.fy;
				} else {
					self.search.branchCode = $stateParams.branchCode;
					self.search.fy = $stateParams.fy;
				}
			} else {
				self.search = JSON.parse($stateParams.searchParams);
				self.search.branchCode = $stateParams.branchCode;
				self.search.fy = $stateParams.fy;
			}

			wait(1000);
			url = url.concat(JSON.stringify(self.search));
			self.loader = url;
			window.open(self.loader, "_blank");
			/*$window.location.reload();*/
			self.reloadState();
		}

		self.generateCorrectionRequestExcel = function(url, modal) {
			if ($stateParams.searchParams == undefined) {
				if (self.isEmpty(self.search)) {
					self.search.branchCode = $stateParams.branchCode;
				} else {
					self.search.branchCode = $stateParams.branchCode;
				}
			} else {
				self.search = JSON.parse($stateParams.searchParams);
				self.search.branchCode = $stateParams.branchCode;
			}

			wait(1000);
			url = url.concat(JSON.stringify(self.search));
			self.loader = url;
			window.open(self.loader, "_blank");
			$window.location.reload();
		}
		self.isEmpty = function(obj) {
			for (var key in obj) {
				if (obj.hasOwnProperty(key))
					return false;
			}
			return true;
		}


		self.uploadRegularReturn = function(valid, entity, documents, page) {
			if (valid == true) {
				entity.docs = documents.docs;
				CommonService.saveRegularReturn(entity, page)
					.then(
						function(data) {
							progressBar();
							console
								.log(entity
									+ ' added successfully');

							//	angular.element('#' + closeModalId).trigger('click');
							//	self.ok();
							$('#successMsg').find('.modal-header').find('.headingMsg').append("Successfull");
							$('#successMsg').find('.modal-body').find('.msg').append("Saved successfully");
							$('#myModal').modal('hide');
							$("#successMsg").modal();
						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							self.ok();
							if (error == 'Supporting Document is Required') {
								$('#errorMsg').find('.modal-body').find('.msg').append("Supporting document is empty");
								$("#errorMsg").modal();
							} else if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							}
							else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}
		}


		self.submitWithFile = function(valid, entity, page, closeModalId) {
			if (valid == true) {
				console.log("Common Controller submit "
					+ entity);
				self.entity.clientId = $stateParams.clientId;

				progressBar();
				CommonService
					.saveWithFile(entity, page)
					.then(
						function(data) {
							progressBar();
							console
								.log(entity
									+ ' added successfully');
							$('.modal').modal(
								"hide");
							self.ok();
							$('#successMsgReturn')
								.find(
									'.modal-header')
								.find(
									'.headingMsg')
								.append(
									"Successfull");
							$('#successMsgReturn')
								.find(
									'.modal-body')
								.find('.msg')
								.append(
									" Saved successfully");
							$("#successMsgReturn")
								.modal();
							//self.gotoListPage(page, page)
						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving details, '
									+ status);
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								if (error.fieldErrors && error.fieldErrors.length > 0) {
									for (var i = 0; i < error.fieldErrors.length; i++) {
										var obj = error.fieldErrors[i];
										document.getElementById(obj.fieldName).innerHTML = obj.message;
									}
								} else {
									$('#errorMsg').find('.modal-body').find('.msg').append("Can not save details");
									$("#errorMsg").modal();
								}
							}
						});
			}
		}

		self.submitWithFileFY = function(valid, entity, page, closeModalId) {
			if (valid == true) {
				console.log("Common Controller submit "
					+ entity);
				self.entity.clientId = $stateParams.clientId;

				progressBar();
				CommonServiceFY
					.saveWithFile(entity, page)
					.then(
						function(data) {
							progressBar();
							console
								.log(entity
									+ ' added successfully');
							$('.modal').modal(
								"hide");
							self.ok();
							$('#successMsgReturn')
								.find(
									'.modal-header')
								.find(
									'.headingMsg')
								.append(
									"Successfull");
							$('#successMsgReturn')
								.find(
									'.modal-body')
								.find('.msg')
								.append(
									" Saved successfully");
							$("#successMsgReturn")
								.modal();
						},
						function(error) {
							progressBar();
							console
								.error('Error while creating saving Details, '
									+ status);
							if (error.exceptionMsg != null && error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-body').find('.msg').append("Can not save " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal();
							} else {
								for (var i = 0; i < error.fieldErrors.length; i++) {
									var obj = error.fieldErrors[i];
									document.getElementById(obj.fieldName).innerHTML = obj.message;
								}
							}
						});
			}
		}

		var progressBar = function() {
			CommonServiceFY.check('index/example-loader.tpl');
			// angular.element('#viewProgressBar').trigger('click');
			// $('#myModalShower').hide();

		}

		self.MysqlData = function(valid, data, entity) {
			if (valid) {
				var json = JSON.stringify(data);
				CommonService.getMysql(json, entity).then(function(data) {
					if (data.encodedFile) {
						// Decode Base64 back to byte array
						var byteCharacters = atob(data.encodedFile);
						var byteNumbers = new Uint8Array(byteCharacters.length);
						for (var i = 0; i < byteCharacters.length; i++) {
							byteNumbers[i] = byteCharacters.charCodeAt(i);
						}

						var mimeTye, fileName;
						if (data.fileType == "EXCEL") {
							mimeTye = 'application/vnd.ms-excel';
							fileName = 'AIGeneratedFile.xlsx';

						} else if (data.fileType == "CSV") {
							mimeTye = 'text/csv';
							fileName = 'AIGeneratedFile.csv';
						}

						var blob = new Blob([byteNumbers], { type: mimeTye });
						var url = window.URL.createObjectURL(blob);
						var a = document.createElement('a');
						a.href = url;
						a.download = fileName;
						document.body.appendChild(a);
						a.click();
						window.URL.revokeObjectURL(url);
						document.body.removeChild(a);
					} else {
						console.error("File bytes not found in the response.");
					}
				});
			}
		}

		self.newChat = function() {
					self.chatMessages = [];  // Clear chat history
					self.showDownloadButton = false;
					self.sqlAI.query = ""; // Clear input field
				};
				
		self.chatMessages = []; // Store chat records
		self.showDownloadButton = false; // Flag to control the visibility of the download button

		self.sendMessageAndGenerate = function(valid) {
			if (valid) {
				var userQuery = self.sqlAI.query;
				self.chatMessages.push({ sender: "You ", text: userQuery });
				self.sqlAI.query = "";

				var jsonData = JSON.stringify({ query: userQuery });

				CommonService.getDataWithAI(jsonData, 'promptQuery').then(function(response) {
					// Start polling for the result
					pollForResult(response.requestId);
				}, function(error) {
					console.log(error);
					self.chatMessages.push({ sender: "AI", text: "Error: Request failed." });
				});
			}
		};

		function pollForResult(requestId) {
			var pollInterval = setInterval(function() {
				CommonService.getStatus(requestId).then(function(response) {
					if (response.status === "Completed") {
						clearInterval(pollInterval);

						// Determine file type and name
						var mimeType, fileName;
						if (response.fileType === "EXCEL") {
							mimeType = 'application/vnd.ms-excel';
							fileName = 'AIGeneratedFile.xlsx';
						} else if (response.fileType === "ZIP") {
							mimeType = 'application/zip';
							fileName = 'AIGeneratedFile.zip';
						} else if (response.fileType === "PDF") {
							mimeType = 'application/pdf';
							fileName = 'AIGeneratedFile.pdf';
						} else {
							mimeType = 'application/octet-stream';
							fileName = 'AIGeneratedFile';
						}

						// Process file download
						var byteCharacters = atob(response.encodedFile);
						var byteNumbers = new Uint8Array(byteCharacters.length);
						for (var i = 0; i < byteCharacters.length; i++) {
							byteNumbers[i] = byteCharacters.charCodeAt(i);
						}
						var blob = new Blob([byteNumbers], { type: mimeType });

						var url = window.URL.createObjectURL(blob);

						self.chatMessages.push({
							sender: 'AI',
							text: "Your file is ready for download!",
							showDownloadButton: true,
							fileUrl: url,
							fileName: fileName
						});
					} else {
						clearInterval(pollInterval);
						self.chatMessages.push({ sender: 'AI', text: "Error: " + response.status });
					}
				});
			}, 5000); // Poll every 5 seconds
		}

		self.uploadInvoice = function(valid, entity, object) {
					if (valid == true) {
						var downloadFile = object.blob;
						CommonService.uploadFile(downloadFile, object, entity).then(function(data) {
							console.log(entity + ' added successfully');
							$('#successMsg').find('.modal-body').find('.msg').text(data.message || "Uploaded Successfully!");
							$("#successMsg").modal('show');
						}, function(error) {
							console.error('CommonController error while adding Certificate');
							if (error.exceptionMsg != null || error.exceptionMsg != undefined) {
								$('#errorMsg').find('.modal-header').find('.headingMsg').text("Information.");
								$('#errorMsg').find('.modal-body').find('.msg').text("Can not add " + error.entityName + " : " + error.exceptionMsg);
								$("#errorMsg").modal({
									keyboard: false,
									backdrop: 'static'
								});
							}
						});
					}
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
