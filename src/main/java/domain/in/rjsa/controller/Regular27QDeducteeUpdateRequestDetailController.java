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
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Regular27QDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.fy.Remarks;
import domain.in.rjsa.model.wrapper.Regular27QDeducteeUpdateRequestDetailWrapper;
import domain.in.rjsa.service.Regular27QDeducteeService;
import domain.in.rjsa.service.Regular27QDeducteeUpdateRequestDetailService;
import domain.in.rjsa.service.RemarksService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform27QDeducteeUpdateRequestDetail")
public class Regular27QDeducteeUpdateRequestDetailController extends
		AbstractBranchController<Long, Regular27QDeducteeUpdateRequestDetail, Regular27QDeducteeUpdateRequestDetailService> {

	@Autowired
	Regular27QDeducteeUpdateRequestDetailService service;
	@Autowired
	Regular27QDeducteeService dService;
	@Autowired
	RemarksService rService;

	@Override
	public Class<Regular27QDeducteeUpdateRequestDetail> getEntity() {
		// TODO Auto-generated method stub
		return Regular27QDeducteeUpdateRequestDetail.class;
	}

	@Override
	public Regular27QDeducteeUpdateRequestDetailService getService() {
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
//		Regular27QUpdateRequestDetail doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
//		Regular27QDeductee updateDoc = dService.getByKey(doc.getDocId());
//		updateDoc.setAddressOfDeductee(entity.get("addressOfDeductee").toString());
//		updateDoc.setAmountPaid(Double.valueOf(entity.get("amountPaid").toString()));
//		updateDoc.setBranchId(Long.valueOf(entity.get("branchId").toString()));
//		updateDoc.setClientId(l.getClientId());
//		updateDoc.setContactNoOfDeductee(entity.get("contactNoOfDeductee").toString());
//		updateDoc.setCountryOfResidence(entity.get("countryOfResidence").toString());
//		updateDoc.setDate(Date.valueOf(entity.get("date").toString()));
//		updateDoc.setDateOfDeduction(Date.valueOf(entity.get("dateOfDeduction").toString()));
//		updateDoc.setDeducteeCode(entity.get("deducteeCode").toString());
//		updateDoc.setDeducteeRefNo(Long.valueOf(entity.get("deducteeRefNo").toString()));
//		updateDoc.setDeducteeSrNo(Long.valueOf(entity.get("deducteeSrNo").toString()));
//		updateDoc.setEducationCess(Double.valueOf(entity.get("educationCess").toString()));
//		updateDoc.setEmailId(entity.get("emailId").toString());
//		updateDoc.setFy(entity.get("fy").toString());
//		updateDoc.setGrossingUpIndicator(entity.get("grossingUpIndicator").toString());
//		updateDoc.setName(entity.get("name").toString());
//		updateDoc.setNatureOfRemittance(entity.get("natureOfRemittance").toString());
//		updateDoc.setNoOfCertificateUnderSection(Long.valueOf(entity.get("noOfCertificateUnderSection").toString()));
//		updateDoc.setPan(entity.get("pan").toString());
//		updateDoc.setQuarter(entity.get("quarter").toString());
//		updateDoc.setRateAtWhichTaxDeducted(Double.valueOf(entity.get("rateAtWhichTaxDeducted").toString()));
//		updateDoc.setReasonForNonDeduction(entity.get("reasonForNonDeduction").toString());
//		updateDoc.setSectionCode(entity.get("sectionCode").toString());
//		updateDoc.setSrNoInChallan(Long.valueOf(entity.get("srNoInChallan").toString()));
//		updateDoc.setSurcharge(Double.valueOf(entity.get("surcharge").toString()));
//		updateDoc.setTaxIdentificationNo(Long.valueOf(entity.get("taxIdentificationNo").toString()));
//		updateDoc.setTdaRateAsPerItActs(Double.valueOf(entity.get("tdaRateAsPerItActs").toString()));
//		updateDoc.setTds(Double.valueOf(entity.get("tds").toString()));
//		updateDoc.setTotalTaxDeducted(Double.valueOf(entity.get("totalTaxDeducted").toString()));
//		updateDoc.setTotalTaxDeposited(Double.valueOf(entity.get("totalTaxDeposited").toString()));
//		updateDoc.setUniqueAcknowledgeNo(Long.valueOf(entity.get("uniqueAcknowledgeNo").toString()));
//		updateDoc.setVerify(Boolean.valueOf(entity.get("verify").toString()));
//		updateDoc.setRemarks(entity.get("remarks").toString());
//		dService.update(updateDoc);
//		
//		Remarks remark = new Remarks();
//		remark.setClientId(l.getClientId());
//		remark.setEntity("form27QUpdateRequestDetail");
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
			service.update(gson.fromJson(jsonElement, Regular27QDeducteeUpdateRequestDetail.class));
			addLogsU(entity);

			entity.remove("id");
			entity.remove("docId");
			jsonElement = gson.toJsonTree(entity);
			dService.save(gson.fromJson(jsonElement, Regular27QDeductee.class));
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			entity.put("status", StaticData.documentStatus.get(1));
			JsonElement jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, Regular27QDeducteeUpdateRequestDetail.class));
			entity.remove("status");

			entity.put("id", entity.remove("docId"));
			jsonElement = gson.toJsonTree(entity);
			dService.update(gson.fromJson(jsonElement, Regular27QDeductee.class));

			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(1));
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

			Regular27QDeducteeUpdateRequestDetail doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
			doc.setStatus(StaticData.documentStatus.get(2));
			service.update(doc);

			Remarks remark = new Remarks();
			remark.setClientId(clientId);
			remark.setEntity(StaticData.entity.get(1));
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

	public Regular27QDeducteeUpdateRequestDetailWrapper getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		Regular27QDeducteeUpdateRequestDetailWrapper data = new Regular27QDeducteeUpdateRequestDetailWrapper();
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("clientId", clientId);
		data.setNewData(getService().uniqueSearch(constrains));
		data.setOldData(dService.getByKey(data.getNewData().getDocId()));
		constrains.remove("id");
		constrains.put("entityId", data.getNewData().getDocId());
		constrains.put("entity", StaticData.entity.get(1));
		data.setListRemarks(rService.search(constrains, clientId));
		return data;
	}

}
