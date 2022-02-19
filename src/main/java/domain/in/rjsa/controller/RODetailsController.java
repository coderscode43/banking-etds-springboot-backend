package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.RODetails;
import domain.in.rjsa.service.RODetailsService;

@Controller
@RequestMapping("/apiroDetails")
public class RODetailsController extends AbstractControllerForm<Long, RODetails, RODetailsService>{
	@Autowired
	RODetailsService service;
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
	 
}
















