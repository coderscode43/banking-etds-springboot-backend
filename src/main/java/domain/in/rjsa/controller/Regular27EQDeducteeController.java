package domain.in.rjsa.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.fy.Regular27EQDeductee;
import domain.in.rjsa.service.Regular27EQDeducteeService;
import domain.in.rjsa.service.RemarkService;
@Controller
@RequestMapping("/apiform27EQDeductee")
public class Regular27EQDeducteeController extends AbstractControllerFY<Long, Regular27EQDeductee, Regular27EQDeducteeService>{
	
	@Autowired
	Regular27EQDeducteeService service;
	@Autowired
	RemarkService rService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Class<Regular27EQDeductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular27EQDeductee.class;
	}

	@Override
	public Regular27EQDeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@RequestMapping(value = "/detail/{fy}/{branchCode}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id, @PathVariable String fy,
			@PathVariable String branchCode) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, fy, branchCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	public HashMap<String, Object> getDetail(Long id, String fy, String branchCode) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		HashMap<String, Object> map = new HashMap<>();
		map.put("deductee",getService().uniqueSearch(constrains));
		constrains.remove("id", id);
		constrains.put("deducteeId",id);
		map.put("remark",rService.findAll(constrains, 0, 100));
		return map; 
	}
}
