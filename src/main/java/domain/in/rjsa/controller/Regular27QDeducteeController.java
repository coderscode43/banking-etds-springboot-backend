package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular27QDeducteeService;

@Controller
@RequestMapping("/apiform27QDeductee")
public class Regular27QDeducteeController
		extends AbstractControllerFY<Long, Regular27QDeductee, Regular27QDeducteeService> {

	@Autowired
	Regular27QDeducteeService service;
	

	@Override
	public Regular27QDeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular27QDeductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular27QDeductee.class;
	}


	
}
