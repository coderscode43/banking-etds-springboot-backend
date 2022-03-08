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

import domain.in.rjsa.model.form.RODetails;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.RODetailsService;

@Controller
@RequestMapping("/apiRODetails")
public class RODetailsController extends AbstractControllerForm<Long, RODetails, RODetailsService>{
	@Autowired
	RODetailsService service;
	@Autowired
	BranchService bService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public RODetailsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<RODetails> getEntity() {
		// TODO Auto-generated method stub
		return RODetails.class;
	}	
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDetailController(@PathVariable Long id) {
		// verify the clientId authorization
		try {
			
			return new ResponseEntity<>(getDetail(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail ", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	public HashMap<String, Object> getDetail(Long id) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> constrains = new HashMap<>();	
		constrains.put("id", id);
		map.put("roDetails",getService().uniqueSearch(constrains));
		constrains.remove("id");
		constrains.put("roCode",id);
		map.put("branch", bService.findAll(constrains, 0, 100));
		return map;
	}
	 
}
















