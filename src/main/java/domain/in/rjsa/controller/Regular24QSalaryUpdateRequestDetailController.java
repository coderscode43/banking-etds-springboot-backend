package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

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

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.fy.Regular24QSalary;
import domain.in.rjsa.model.fy.Regular24QSalaryUpdateRequestDetail;
//import domain.in.rjsa.model.fy.Remarks;
import domain.in.rjsa.model.wrapper.Regular24QSalaryUpdateRequestDetailWrapper;
import domain.in.rjsa.service.Regular24QSalaryService;
import domain.in.rjsa.service.Regular24QSalaryUpdateRequestDetailService;
//import domain.in.rjsa.service.RemarksService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform24QSalaryUpdateRequestDetail")
public class Regular24QSalaryUpdateRequestDetailController extends
AbstractBranchControllerFY<Long, Regular24QSalaryUpdateRequestDetail, Regular24QSalaryUpdateRequestDetailService> {

	@Autowired
	Regular24QSalaryUpdateRequestDetailService service;
	@Autowired
	Regular24QSalaryService rUService;
	/*@Autowired
	RemarksService rService;*/

	@Override
	public Regular24QSalaryUpdateRequestDetailService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular24QSalaryUpdateRequestDetail> getEntity() {
		// TODO Auto-generated method stub
		return Regular24QSalaryUpdateRequestDetail.class;
	}

	@Override
	public void update(LinkedHashMap<String, Object> entity) {
	}

	@RequestMapping(value = "/approve/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<?> approveUpdate(@RequestBody LinkedHashMap<String, Object> entity,
			HttpServletResponse response, UriComponentsBuilder ucBuilder) {

		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		if (entity.get("docId") == null || entity.get("docId").equals("")) {
			entity.put("status", StaticData.documentStatus.get(1));
			JsonElement jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, Regular24QSalaryUpdateRequestDetail.class));
			addLogsU(entity);
			
			entity.remove("id");
			entity.remove("docId");
			jsonElement = gson.toJsonTree(entity);
			rUService.save(gson.fromJson(jsonElement, Regular24QSalary.class));
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			entity.put("status", StaticData.documentStatus.get(1));
			JsonElement jsonElement = gson.toJsonTree(entity);
			service.update(gson.fromJson(jsonElement, Regular24QSalaryUpdateRequestDetail.class));
			entity.remove("status");

			entity.put("id", entity.remove("docId"));
			jsonElement = gson.toJsonTree(entity);
			rUService.update(gson.fromJson(jsonElement, Regular24QSalary.class));

			/*Remarks remark = new Remarks();
			remark.setEntity(StaticData.entity.get(6));
			remark.setRemark(entity.get("remarks").toString());
			remark.setEntityId(Long.valueOf(entity.get("id").toString()));
			rService.save(remark);*/

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

			Regular24QSalaryUpdateRequestDetail doc = service.getByKey(Long.valueOf(entity.get("id").toString()));
			doc.setStatus(StaticData.documentStatus.get(2));
			service.update(doc);

			/*Remarks remark = new Remarks();
			remark.setEntity(StaticData.entity.get(6));
			remark.setRemark(entity.get("remarks").toString());
			remark.setEntityId(doc.getDocId());
			rService.save(remark);

			addLogsU(entity);

			ermsg.setMessage("Updated Successfully");*/
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public Regular24QSalaryUpdateRequestDetailWrapper getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		Regular24QSalaryUpdateRequestDetailWrapper data = new Regular24QSalaryUpdateRequestDetailWrapper();
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("clientId", clientId);
		data.setNewData(getService().uniqueSearch(constrains));
		if(data.getNewData().getDocId() != null) {
			data.setOldData(rUService.getByKey(data.getNewData().getDocId()));
		}
		constrains.remove("id");
		constrains.put("entityId", data.getNewData().getDocId());
		constrains.put("entity", StaticData.entity.get(6));
		//data.setListRemarks(rService.search(constrains));
		return data;
	}

}