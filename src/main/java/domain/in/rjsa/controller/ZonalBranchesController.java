package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.ZonalBranches;
import domain.in.rjsa.service.ZonalBranchesService;

@Controller
@RequestMapping("/apizonalBranches")
public class ZonalBranchesController extends AbstractController<Long, ZonalBranches, ZonalBranchesService>{
@Autowired
ZonalBranchesService service;

private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public ZonalBranchesService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<ZonalBranches> getEntity() {
		// TODO Auto-generated method stub
		return ZonalBranches.class;
	}
	
	
	@Override
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
		
		logger.info("Creating new Return instance");
		create(entity);
		//Here we will change the zone 
		//create(entity);
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}
	
//	public void create(LinkedHashMap<String, Object> entity) {
//		Gson gson = new Gson();
//		Login l = applicationCache.getLoginDetail(getPrincipal());
//		if (entity.containsKey("clientId")) {
//		entity.put("clientId", l.getClientId());
//		}
//		
//		
//		
//		
//		
//	    JsonElement jsonElement = gson.toJsonTree(entity);
//
//		getService().save(gson.fromJson(jsonElement, getEntity()));
//
//	}
	
}
