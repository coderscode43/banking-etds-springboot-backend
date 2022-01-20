package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular27EQDeductee;
import domain.in.rjsa.service.Regular27EQDeducteeService;
@Controller
@RequestMapping("/apiform27EQDeductee")
public class Regular27EQDeducteeController extends AbstractBranchController<Long, Regular27EQDeductee, Regular27EQDeducteeService>{
	
	@Autowired
	Regular27EQDeducteeService service;
	
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

}
