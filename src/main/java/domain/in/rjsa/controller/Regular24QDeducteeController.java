package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Regular24QDeductee;
import domain.in.rjsa.model.form.Regular24QDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.form.Remarks;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular24QDeducteeService;
import domain.in.rjsa.service.Regular24QDeducteeUpdateRequestDetailService;
import domain.in.rjsa.service.RemarksService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform24QDeductee")
public class Regular24QDeducteeController
		extends AbstractBranchController<Long, Regular24QDeductee, Regular24QDeducteeService> {

	@Autowired
	Regular24QDeducteeService service;
	@Autowired
	Regular24QDeducteeUpdateRequestDetailService uService;
	@Autowired
	RemarksService rService;

	@Override
	public Regular24QDeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular24QDeductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular24QDeductee.class;
	}

	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
		// Login l = applicationCache.getLoginDetail(getPrincipal());

		// LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		// constrains.put("clientId", l.getClientId());
		Regular24QDeductee ded = service.getByKey(id);
		ew.setDeductee(ded);

		return ew;
	}

	public void create(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());

		if (entity.containsKey("clientId")) {
			entity.put("clientId", l.getClientId());
		}

		entity.put("clientId", l.getClientId());
		entity.put("status", StaticData.documentStatus.get(0));
		entity.put("remarks", "");
		// entity.put("branchId", );
		JsonElement jsonElement = gson.toJsonTree(entity);
		uService.save(gson.fromJson(jsonElement, Regular24QDeducteeUpdateRequestDetail.class));

	}

	// ------------------- Update Entity ---------------------------------

	@RequestMapping(value = "/update/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody LinkedHashMap<String, Object> entity, HttpServletResponse response,
			UriComponentsBuilder ucBuilder) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		Long clientId = Long.valueOf(entity.get("clientId").toString());
		if (clientId == l.getClientId()) {
			update(entity);
			addLogsU(entity);

			ermsg.setMessage(" Updated Successfully");
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public void update(LinkedHashMap<String, Object> entity) {

		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> map = new HashMap<>();
		map.put("docId", Long.valueOf(entity.get("id").toString()));
		map.put("clientId", l.getClientId());
		List<Regular24QDeducteeUpdateRequestDetail> listReqDoc = uService.search(map, l.getClientId());
		map.put("entityId", map.remove("docId"));
		List<Remarks> listRemarks = rService.search(map, l.getClientId());
		if (listRemarks.isEmpty() || listRemarks == null) {
			Regular24QDeductee oldEntity = service.getByKey(Long.valueOf(entity.get("id").toString()));
			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(2));
			remark.setEntityId(Long.valueOf(entity.get("id").toString()));
			remark.setRemark(oldEntity.getRemarks());
			rService.save(remark);
		}
		if (listReqDoc.isEmpty() || listReqDoc == null) {
			entity.put("docId", entity.remove("id"));
			entity.put("status", StaticData.documentStatus.get(0));
			JsonElement jsonElement = gson.toJsonTree(entity);
			uService.save(gson.fromJson(jsonElement, Regular24QDeducteeUpdateRequestDetail.class));

			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(2));
			remark.setEntityId(Long.valueOf(entity.get("docId").toString()));
			remark.setRemark(entity.get("remarks").toString());
			rService.save(remark);
		} else {
			for (Regular24QDeducteeUpdateRequestDetail doc : listReqDoc) {
				if (doc.getStatus().equals(StaticData.documentStatus.get(0))) {
					throw new CustomException("An update is pending to approve/reject of this entry");
				}
			}

			entity.put("docId", entity.remove("id"));
			entity.put("status", StaticData.documentStatus.get(0));
			JsonElement jsonElement = gson.toJsonTree(entity);
			uService.save(gson.fromJson(jsonElement, Regular24QDeducteeUpdateRequestDetail.class));
			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(2));
			remark.setEntityId(Long.valueOf(entity.get("docId").toString()));
			remark.setRemark(entity.get("remarks").toString());
			rService.save(remark);
		}
		entity.put("id", entity.remove("docId"));
	}

}
