package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular26QDeducteeService;

@Controller
@RequestMapping("/apiform26QDeductee")
public class Regular26QDeducteeController
		extends AbstractBranchControllerFY<Long, Regular26QDeductee, Regular26QDeducteeService> {
	
	@Autowired
	Regular26QDeducteeService service;
	
	@Autowired
	@Override
	public Class<Regular26QDeductee> getEntity() {
		// TODO Auto-generated method stub
		return Regular26QDeductee.class;
	}

	@Override
	public Regular26QDeducteeService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	
	
	

	

}
