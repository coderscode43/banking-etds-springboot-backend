	package domain.in.rjsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.UserDetailsService;

@Controller
@RequestMapping("/apiuserDetails")
public class UserDetailsController extends AbstractControllerForm<String, UserDetails, UserDetailsService> {

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserDetailsService service;

	@Override
	public UserDetailsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<UserDetails> getEntity() {
		// TODO Auto-generated method stub
		return UserDetails.class;
	}
	
	// ------------------- Delete Entity ---------------------------------

		@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
		public ResponseEntity<?> delete(@PathVariable String id) {
			// verify the clientId authorization
			try {
				if("admin".equals(getBranchCode())) {
					getService().deleteT(id);
					return new ResponseEntity<>(HttpStatus.OK);
				}else {
					return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
				}
				
			} catch (Exception e) {
				logger.error("Error in getting detail ", e);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}

		}
}
