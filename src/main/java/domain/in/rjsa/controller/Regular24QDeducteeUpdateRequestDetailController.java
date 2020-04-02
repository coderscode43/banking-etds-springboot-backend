package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Regular24QDeductee;
import domain.in.rjsa.model.form.Regular24QDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.form.Remarks;
import domain.in.rjsa.model.wrapper.Regular24QDeducteeUpdateRequestDetailWrapper;
import domain.in.rjsa.service.Regular24QDeducteeService;
import domain.in.rjsa.service.Regular24QDeducteeUpdateRequestDetailService;
import domain.in.rjsa.service.RemarksService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform24QDeducteeUpdateRequestDetail")
public class Regular24QDeducteeUpdateRequestDetailController extends
		AbstractBranchController<Long, Regular24QDeducteeUpdateRequestDetail, Regular24QDeducteeUpdateRequestDetailService> {

	@Autowired
	Regular24QDeducteeUpdateRequestDetailService service;
	@Autowired
	Regular24QDeducteeService dService;
	@Autowired
	RemarksService rService;

	@Override
	public Class<Regular24QDeducteeUpdateRequestDetail> getEntity() {
		// TODO Auto-generated method stub
		return Regular24QDeducteeUpdateRequestDetail.class;
	}

	@Override
	public Regular24QDeducteeUpdateRequestDetailService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// ------------------- Update Entity ---------------------------------

//	@RequestMapping(value = "/update/{clientId}", method = RequestMethod.PUT)
//	public ResponseEntity<?> update(@RequestBody LinkedHashMap<String, Object> entity, HttpServletResponse response,
//			UriComponentsBuilder ucBuilder) {
//		FieldErrorDTO ermsg = new FieldErrorDTO();
//		Login l = applicationCache.getLoginDetail(getPrincipal());
//		Long clientId = Long.valueOf(entity.get("clientId").toString());
//		if (clientId == l.getClientId()) {
//			update(entity);
//			addLogsU(entity);
//
//			ermsg.setMessage(" Updated Successfully");
//			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
//		} else {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
//
//	public void update(LinkedHashMap<String, Object> entity) {
//
//		Gson gson = new Gson();
//		Login l = applicationCache.getLoginDetail(getPrincipal());
//		JsonElement jsonElement = gson.toJsonTree(entity);
//
//		Regular24QUpdateRequestDetail doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
//		Regular24Q4Deductee updateDoc = dService.getByKey(doc.getDocId());
//		updateDoc.setAmountPaid(Double.valueOf(entity.get("amountPaid").toString()));
//		updateDoc.setBranchId(Long.valueOf(entity.get("branchId").toString()));
//		updateDoc.setCertificateNumber(entity.get("certificateNumber").toString());
//		updateDoc.setChallanSrNo(Long.valueOf(entity.get("challanSrNo").toString()));
//		updateDoc.setClientId(l.getClientId());
//		updateDoc.setDateOfDeduction(Date.valueOf(entity.get("dateOfDeduction").toString()));
//		updateDoc.setDateOfPayment(Date.valueOf(entity.get("dateOfPayment").toString()));
//		updateDoc.setEducationCess(Double.valueOf(entity.get("educationCess").toString()));
//		updateDoc.setEmployeeRefNo(entity.get("employeeRefNo").toString());
//		updateDoc.setFy(entity.get("fy").toString());
//		updateDoc.setName(entity.get("name").toString());
//		updateDoc.setPan(entity.get("pan").toString());
//		updateDoc.setPanRefNo(entity.get("panRefNo").toString());
//		updateDoc.setQuarter(entity.get("quarter").toString());
//		updateDoc.setRemarks(entity.get("remarks").toString());
//		updateDoc.setSectionCode(entity.get("sectionCode").toString());
//		updateDoc.setSrNo(Long.valueOf(entity.get("srNo").toString()));
//		updateDoc.setSurcharge(Double.valueOf(entity.get("surcharge").toString()));
//		updateDoc.setTds(Double.valueOf(entity.get("tds").toString()));
//		updateDoc.setTotalTaxDeposited(Double.valueOf(entity.get("totalTaxDeposited").toString()));
//		updateDoc.setTotalTds(Double.valueOf(entity.get("totalTds").toString()));
//		updateDoc.setVerify(Boolean.valueOf(entity.get("verify").toString()));
//		dService.update(updateDoc);
//		
//		Remarks remark = new Remarks();
//		remark.setClientId(l.getClientId());
//		remark.setEntity("form24QUpdateRequestDetail");
//		remark.setEntityId(doc.getId());
//		remark.setRemark(entity.get("remarks").toString());
//		rService.save(remark);
//		getService().update(gson.fromJson(jsonElement, getEntity()));
//
//	}

	@RequestMapping(value = "/approve/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<?> approveUpdate(@RequestBody LinkedHashMap<String, Object> entity,
			HttpServletResponse response, UriComponentsBuilder ucBuilder) {

		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		if (entity.get("docId") == null || entity.get("docId").equals("")) {
			entity.put("status", StaticData.documentStatus.get(1));
			JsonElement jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, Regular24QDeducteeUpdateRequestDetail.class));
			addLogsU(entity);

			entity.remove("id");
			entity.remove("docId");
			jsonElement = gson.toJsonTree(entity);
			dService.save(gson.fromJson(jsonElement, Regular24QDeductee.class));
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			entity.put("status", StaticData.documentStatus.get(1));
			JsonElement jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, Regular24QDeducteeUpdateRequestDetail.class));
			entity.remove("status");

			entity.put("id", entity.remove("docId"));
			jsonElement = gson.toJsonTree(entity);
			dService.update(gson.fromJson(jsonElement, Regular24QDeductee.class));

			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(2));
			remark.setRemark(entity.get("remarks").toString());
			remark.setEntityId(Long.valueOf(entity.get("id").toString()));
			rService.save(remark);

			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = "/reject/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<?> rejectUpdate(@RequestBody LinkedHashMap<String, Object> entity,
			HttpServletResponse response, UriComponentsBuilder ucBuilder) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		Long clientId = Long.valueOf(entity.get("clientId").toString());
		if (clientId == l.getClientId()) {

			Regular24QDeducteeUpdateRequestDetail doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
			doc.setStatus(StaticData.documentStatus.get(2));
			service.update(doc);

			Remarks remark = new Remarks();
			remark.setClientId(clientId);
			remark.setEntity(StaticData.entity.get(2));
			remark.setRemark(entity.get("remarks").toString());
			remark.setEntityId(doc.getDocId());
			rService.save(remark);

			addLogsU(entity);

			ermsg.setMessage("Updated Successfully");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// ------------------- Get Detail ---------------------------------

	@RequestMapping(value = "/detail/{clientId}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id, @PathVariable Long clientId) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, clientId), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public Regular24QDeducteeUpdateRequestDetailWrapper getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		Regular24QDeducteeUpdateRequestDetailWrapper data = new Regular24QDeducteeUpdateRequestDetailWrapper();
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("clientId", clientId);
		data.setNewData(getService().uniqueSearch(constrains));
		data.setOldData(dService.getByKey(data.getNewData().getDocId()));
		constrains.remove("id");
		constrains.put("entityId", data.getNewData().getDocId());
		constrains.put("entity", StaticData.entity.get(2));
		data.setListRemarks(rService.search(constrains, clientId));
		return data;
	}

}
