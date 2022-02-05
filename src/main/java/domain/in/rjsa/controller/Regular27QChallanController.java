package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular27QChallan;
import domain.in.rjsa.service.Regular27QChallanService;
@Controller
@RequestMapping("/apiregular27QChallan")
public class Regular27QChallanController extends AbstractBranchControllerFY<Long, Regular27QChallan, Regular27QChallanService>{

	
	@Autowired
	Regular27QChallanService service;

	@Override
	public Class<Regular27QChallan> getEntity() {
		// TODO Auto-generated method stub
		return Regular27QChallan.class;
	}

	@Override
	public Regular27QChallanService getService() {
		// TODO Auto-generated method stub
		return service;
	}
}
