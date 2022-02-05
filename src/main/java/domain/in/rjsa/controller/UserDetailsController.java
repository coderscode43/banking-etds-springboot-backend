package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.UserDetails;
import domain.in.rjsa.service.UserDetailsService;

@Controller
@RequestMapping("/apiuserDetails")
public class UserDetailsController extends AbstractControllerForm<Long, UserDetails, UserDetailsService> {

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

	
	
	

}
