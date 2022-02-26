package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.fy.Regular24QSalary;
import domain.in.rjsa.service.Regular24QSalaryService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform24Qsalary")
public class Regular24QSalaryController
		extends AbstractControllerFY<Long, Regular24QSalary, Regular24QSalaryService> {

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


}
