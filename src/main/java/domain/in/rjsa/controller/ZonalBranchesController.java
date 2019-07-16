package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.in.rjsa.model.form.ZonalBranches;
import domain.in.rjsa.service.ZonalBranchesService;

@Controller
@RequestMapping("/apizonalBranches")
public class ZonalBranchesController extends AbstractController<Long, ZonalBranches, ZonalBranchesService>{
@Autowired
ZonalBranchesService service;
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
	
	
	
	@RequestMapping(value = "/changeBranchZone/{clientId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> changeBranchZone(@RequestBody LinkedHashMap<String, Object> entity) {
		
		
		//Here we will change the zone 
		//create(entity);
		return new ResponseEntity<Object>(HttpStatus.CREATED);

	}
	
}
