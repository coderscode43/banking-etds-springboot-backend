package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.fy.Regular24QSalary;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular24QSalaryService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform24Qsalary")
public class Regular24QSalaryController
		extends AbstractBranchControllerFY<Long, Regular24QSalary, Regular24QSalaryService> {

	@Autowired
	Regular24QSalaryService service;

	

	
	
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
	

	}

	@Override
	public void update(LinkedHashMap<String, Object> entity) {

		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> map = new HashMap<>();
		map.put("docId", Long.valueOf(entity.get("id").toString()));
		map.put("clientId", l.getClientId());
		
		map.put("entityId", map.remove("docId"));
		
		
		entity.put("id", entity.remove("docId"));
	}

}
