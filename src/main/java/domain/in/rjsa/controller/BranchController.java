package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.Branch;
import domain.in.rjsa.service.BranchService;

@Controller
@RequestMapping("/apibranch")
public class BranchController extends AbstractController<Long, Branch, BranchService>{
@Autowired
BranchService service;
	@Override
	public BranchService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Branch> getEntity() {
		// TODO Auto-generated method stub
		return Branch.class;
	}

}
