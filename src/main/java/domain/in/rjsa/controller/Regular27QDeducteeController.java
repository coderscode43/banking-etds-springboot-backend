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
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.fy.Regular27QDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.fy.Remarks;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular27QDeducteeService;
import domain.in.rjsa.service.Regular27QDeducteeUpdateRequestDetailService;
import domain.in.rjsa.service.RemarksService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform27QDeductee")
public class Regular27QDeducteeController
		extends AbstractBranchController<Long, Regular27QDeductee, Regular27QDeducteeService> {

	@Autowired
	Regular27QDeducteeService service;
	@Autowired
	Regular27QDeducteeUpdateRequestDetailService uService;
	@Autowired
	RemarksService rService;

	@Override
	public Regular27QDeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular27QDeductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular27QDeductee.class;
	}

	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
		// Login l = applicationCache.getLoginDetail(getPrincipal());

		// LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		// constrains.put("clientId", l.getClientId());
		Regular27QDeductee ded27 = service.getByKey(id);
		ew.setDeductee27(ded27);
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
		uService.save(gson.fromJson(jsonElement, Regular27QDeducteeUpdateRequestDetail.class));

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
		List<Regular27QDeducteeUpdateRequestDetail> listReqDoc = uService.search(map, l.getClientId());
		map.put("entityId", map.remove("docId"));
		List<Remarks> listRemarks = rService.search(map, l.getClientId());
		if (listRemarks.isEmpty() || listRemarks == null) {
			Regular27QDeductee oldEntity = service.getByKey(Long.valueOf(entity.get("id").toString()));
			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(1));
			remark.setEntityId(Long.valueOf(entity.get("id").toString()));
			remark.setRemark(oldEntity.getRemarks());
			rService.save(remark);
		}
		if (listReqDoc.isEmpty() || listReqDoc == null) {
			entity.put("docId", entity.remove("id"));
			entity.put("status", StaticData.documentStatus.get(0));
			JsonElement jsonElement = gson.toJsonTree(entity);
			uService.save(gson.fromJson(jsonElement, Regular27QDeducteeUpdateRequestDetail.class));

			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(1));
			remark.setEntityId(Long.valueOf(entity.get("docId").toString()));
			remark.setRemark(entity.get("remarks").toString());
			rService.save(remark);
		} else {
			for (Regular27QDeducteeUpdateRequestDetail doc : listReqDoc) {
				if (doc.getStatus().equals(StaticData.documentStatus.get(0))) {
					throw new CustomException("An update is pending to approve/reject of this entry");
				}
			}

			entity.put("docId", entity.remove("id"));
			entity.put("status", StaticData.documentStatus.get(0));
			JsonElement jsonElement = gson.toJsonTree(entity);
			uService.save(gson.fromJson(jsonElement, Regular27QDeducteeUpdateRequestDetail.class));
			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(1));
			remark.setEntityId(Long.valueOf(entity.get("docId").toString()));
			remark.setRemark(entity.get("remarks").toString());
			rService.save(remark);
		}
		entity.put("id", entity.remove("docId"));
	}
}
