package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular26QChallan;
import domain.in.rjsa.service.Regular26QChallanService;
@Controller
@RequestMapping("/apiformchallan26Q")
public class Regular26QChallanController extends AbstractBranchControllerFY<Long, Regular26QChallan, Regular26QChallanService>{


	@Autowired
	Regular26QChallanService service;

	@Override
	public Class<Regular26QChallan> getEntity() {
		// TODO Auto-generated method stub
		return Regular26QChallan.class;
	}

	@Override
	public Regular26QChallanService getService() {
		// TODO Auto-generated method stub
		return service;
	}
	
}
