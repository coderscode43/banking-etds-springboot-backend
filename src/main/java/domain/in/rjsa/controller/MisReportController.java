package domain.in.rjsa.controller;


import java.util.LinkedHashMap;

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
import org.springframework.web.bind.annotation.ResponseBody;

import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.fy.MisReport;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.MisReportService;
@Controller
@RequestMapping("/apigenerateReport")
public class MisReportController extends AbstractControllerFY<Long, MisReport, MisReportService> {
	@Autowired
	MisReportService service;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public MisReportService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<MisReport> getEntity() {
		// TODO Auto-generated method stub
		return MisReport.class;
	}
	
	  @RequestMapping(value = "/add/{fy}/{branchCode}", method =RequestMethod.POST)
	  @ResponseBody public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity,
	  @PathVariable Long branchCode, @PathVariable String fy) 
	  { 
	  FieldErrorDTO ermsg = new FieldErrorDTO(); logger.info("Creating new Return instance");
	  adminValidation(entity);
	  entity.put("branchCode", branchCode); entity.put("fy", fy); 
	  String userName = getPrincipal(); 
	  entity.put("userName", userName); 
	  create(entity); 
	  //addLogs(entity); // ermsg.setMessage(" Saved Successfully"); 
	  return new ResponseEntity<Object>(HttpStatus.CREATED);
	  
	  }
}
