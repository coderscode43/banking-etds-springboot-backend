package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Regular24QSalary;
import domain.in.rjsa.model.form.Regular24QSalaryUpdateRequestDetail;
import domain.in.rjsa.model.form.Remarks;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular24QSalaryService;
import domain.in.rjsa.service.Regular24QSalaryUpdateRequestDetailService;
import domain.in.rjsa.service.RemarksService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform24Qsalary")
public class Regular24QSalaryController
		extends AbstractBranchController<Long, Regular24QSalary, Regular24QSalaryService> {

	@Autowired
	Regular24QSalaryService service;

	@Autowired
	Regular24QSalaryUpdateRequestDetailService uService;

	@Autowired
	RemarksService rService;
	
	@Override
	public Regular24QSalaryService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular24QSalary> getEntity() {
		// TODO Auto-generated method stub
		return Regular24QSalary.class;
	}

	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
		// Login l = applicationCache.getLoginDetail(getPrincipal());

		// LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		// constrains.put("clientId", l.getClientId());
		Regular24QSalary sal = service.getByKey(id);
		ew.setSalary(sal);

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
		uService.save(gson.fromJson(jsonElement, Regular24QSalaryUpdateRequestDetail.class));

	}

	@Override
	public void update(LinkedHashMap<String, Object> entity) {

		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> map = new HashMap<>();
		map.put("docId", Long.valueOf(entity.get("id").toString()));
		map.put("clientId", l.getClientId());
		List<Regular24QSalaryUpdateRequestDetail> listReqDoc = uService.search(map, l.getClientId());
		map.put("entityId", map.remove("docId"));
		List<Remarks> listRemarks = rService.search(map, l.getClientId());
		if (listRemarks.isEmpty() || listRemarks == null) {
			Regular24QSalary oldEntity = service.getByKey(Long.valueOf(entity.get("id").toString()));
			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(6));
			remark.setEntityId(Long.valueOf(entity.get("id").toString()));
			remark.setRemark(oldEntity.getRemarks());
			rService.save(remark);
		}
		if (listReqDoc.isEmpty() || listReqDoc == null) {
			entity.put("docId", entity.remove("id"));
			entity.put("status", StaticData.documentStatus.get(0));
			JsonElement jsonElement = gson.toJsonTree(entity);
			uService.save(gson.fromJson(jsonElement, Regular24QSalaryUpdateRequestDetail.class));

			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(6));
			remark.setEntityId(Long.valueOf(entity.get("docId").toString()));
			remark.setRemark(entity.get("remarks").toString());
			rService.save(remark);
		} else {
			for (Regular24QSalaryUpdateRequestDetail doc : listReqDoc) {
				if (doc.getStatus().equals(StaticData.documentStatus.get(0))) {
					throw new CustomException("An update is pending to approve/reject of this entry");
				}
			}

			entity.put("docId", entity.remove("id"));
			entity.put("status", StaticData.documentStatus.get(0));
			JsonElement jsonElement = gson.toJsonTree(entity);
			uService.save(gson.fromJson(jsonElement, Regular24QSalaryUpdateRequestDetail.class));
			Remarks remark = new Remarks();
			remark.setClientId(l.getClientId());
			remark.setEntity(StaticData.entity.get(6));
			remark.setEntityId(Long.valueOf(entity.get("docId").toString()));
			remark.setRemark(entity.get("remarks").toString());
			rService.save(remark);
		}
		entity.put("id", entity.remove("docId"));
	}

}
