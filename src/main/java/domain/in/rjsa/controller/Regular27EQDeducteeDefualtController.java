package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular27EQDeducteeDefualt;
import domain.in.rjsa.service.Regular27EQDeducteeDefualtService;
@Controller
@RequestMapping("/apiform27EQDeducteeDefault")
public class Regular27EQDeducteeDefualtController extends AbstractBranchControllerFY<Long, Regular27EQDeducteeDefualt, Regular27EQDeducteeDefualtService>{
	
	@Autowired
	Regular27EQDeducteeDefualtService service;
	
	@Override
	public Class<Regular27EQDeducteeDefualt> getEntity() {
		// TODO Auto-generated method stub
		return Regular27EQDeducteeDefualt.class;
	}

	@Override
	public Regular27EQDeducteeDefualtService getService() {
		// TODO Auto-generated method stub
		return service;
	}

}
