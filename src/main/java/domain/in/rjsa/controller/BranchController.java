package domain.in.rjsa.controller;



import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apibranch")
public class BranchController {
	
	@Autowired
	BranchService service;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ApplicationCache applicationCache;
	
	@RequestMapping(value = "/list/get/{pageNo}/{resultPerPage}", method = RequestMethod.GET)
	public ResponseEntity<?> listAll( HttpServletRequest request, @PathVariable int pageNo,
			@PathVariable int resultPerPage) {
		try {
			List<?> list = getList( pageNo, resultPerPage);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getList( int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		HashMap<String, Object> constrains = new HashMap<>();

		return service.findAll(constrains, pageNo, resultPerPage);
	}
	
	
	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;

	}
	@RequestMapping(value = "/list/count/", method = RequestMethod.GET)
	public ResponseEntity<?> count( HttpServletRequest request) {
		// verify the clientId authorization
//			applicationCache.getUserAuthorised();
		HashMap<String, Object> constrains = new HashMap<>();

		String mapping = request.getPathInfo();

		try {
			Long count = service.findallCount(constrains);
			List<?> list = getList( 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

}
