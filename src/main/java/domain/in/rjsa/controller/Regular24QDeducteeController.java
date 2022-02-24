package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.service.Regular24QDeducteeService;

@Controller
@RequestMapping("/apiform24QDeductee")
public class Regular24QDeducteeController
		extends AbstractControllerFY<Long, Regular24QDeductee, Regular24QDeducteeService> {

	@Autowired
	Regular24QDeducteeService service;
	

	@Override
	public Regular24QDeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular24QDeductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular24QDeductee.class;
	}

	

	

}
