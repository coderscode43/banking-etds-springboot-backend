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
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.fy.Remarks;
import domain.in.rjsa.model.wrapper.Regular26QDeducteeUpdateRequestDetailWrapper;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.Regular26QDeducteeUpdateRequestDetailService;
import domain.in.rjsa.service.RemarksService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform26QDeducteeUpdateRequestDetail")
public class Regular26QDeducteeUpdateRequestDetailController extends
AbstractBranchControllerFY<Long, Regular26QDeducteeUpdateRequestDetail, Regular26QDeducteeUpdateRequestDetailService> {

	@Autowired
	Regular26QDeducteeUpdateRequestDetailService service;
	@Autowired
	Regular26QDeducteeService dService;
	@Autowired
	RemarksService rService;

	@Override
	public Class<Regular26QDeducteeUpdateRequestDetail> getEntity() {
		// TODO Auto-generated method stub
		return Regular26QDeducteeUpdateRequestDetail.class;
	}

	@Override
	public Regular26QDeducteeUpdateRequestDetailService getService() {
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
//		Regular26QUpdateRequestDetail doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
//		Regular26QDeductee updateDoc = dService.getByKey(doc.getDocId());
//		updateDoc.setBranchId(Long.valueOf(entity.get("branchId").toString()));
//		updateDoc.setCertificateNo(entity.get("certificateNo").toString());
//		updateDoc.setChallanSrNo(Long.valueOf(entity.get("challanSrNo").toString()));
//		updateDoc.setClientId(l.getClientId());
//		updateDoc.setDeductDate(Date.valueOf(entity.get("deductDate").toString()));
//		updateDoc.setDeducteeCode(entity.get("deducteeCode").toString());
//		updateDoc.setDeducteeName(entity.get("deducteeName").toString());
//		updateDoc.setDeducteePan(entity.get("deducteePan").toString());
//		updateDoc.setDeducteeRefNo(entity.get("deducteeRefNo").toString());
//		updateDoc.setEduCess(Double.valueOf(entity.get("eduCess").toString()));
//		updateDoc.setFy(entity.get("fy").toString());
//		updateDoc.setPaidAmt(Double.valueOf(entity.get("paidAmt").toString()));
//		updateDoc.setPaymentDate(Date.valueOf(entity.get("paymentDate").toString()));
//		updateDoc.setQuarter(entity.get("quarter").toString());
//		updateDoc.setRateTaxDeduct(Double.valueOf(entity.get("rateTaxDeduct").toString()));
//		updateDoc.setRemarks(entity.get("remarks").toString());
//		updateDoc.setSectionCode(entity.get("sectionCode").toString());
//		updateDoc.setSrNo(Long.valueOf(entity.get("srNo").toString()));
//		updateDoc.setSurcharge(Double.valueOf(entity.get("surcharge").toString()));
//		updateDoc.setTds(Double.valueOf(entity.get("tds").toString()));
//		updateDoc.setTotalTaxDeduct(Double.valueOf(entity.get("totalTaxDeduct").toString()));
//		updateDoc.setTotalTaxDeposit(Double.valueOf(entity.get("totalTaxDeposit").toString()));
//		updateDoc.setVerify(Boolean.valueOf(entity.get("verify").toString()));
//		dService.update(updateDoc);
//		
//		Remarks remark = new Remarks();
//		remark.setClientId(l.getClientId());
//		remark.setEntity("form26QUpdateRequestDetail");
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
			service.update(gson.fromJson(jsonElement, Regular26QDeducteeUpdateRequestDetail.class));
			addLogsU(entity);

			entity.remove("id");
			entity.remove("docId");
			jsonElement = gson.toJsonTree(entity);
			dService.save(gson.fromJson(jsonElement, Regular26QDeductee.class));
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			entity.put("status", StaticData.documentStatus.get(1));
			JsonElement jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, Regular26QDeducteeUpdateRequestDetail.class));
			entity.remove("status");

			entity.put("id", entity.remove("docId"));
			jsonElement = gson.toJsonTree(entity);
			dService.update(gson.fromJson(jsonElement, Regular26QDeductee.class));

			Remarks remark = new Remarks();
			remark.setEntity(StaticData.entity.get(0));
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

			Regular26QDeducteeUpdateRequestDetail doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
			doc.setStatus(StaticData.documentStatus.get(2));
			service.update(doc);

			Remarks remark = new Remarks();
			remark.setEntity(StaticData.entity.get(0));
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

	public Regular26QDeducteeUpdateRequestDetailWrapper getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		Regular26QDeducteeUpdateRequestDetailWrapper data = new Regular26QDeducteeUpdateRequestDetailWrapper();
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("clientId", clientId);
		data.setNewData(getService().uniqueSearch(constrains));
		data.setOldData(dService.getByKey(data.getNewData().getDocId()));
		constrains.remove("id");
		constrains.put("entityId", data.getNewData().getDocId());
		constrains.put("entity", StaticData.entity.get(0));
		data.setListRemarks(rService.search(constrains));
		return data;
	}

}
