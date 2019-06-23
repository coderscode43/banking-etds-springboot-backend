package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.ZonalBranches;
import domain.in.rjsa.service.ZonalBranchesService;

@Controller
@RequestMapping("/apizonalBranches")
public class ZonalBranchesController extends AbstractController<Long, ZonalBranches, ZonalBranchesService>{
@Autowired
ZonalBranchesService service;
	@Override
	public ZonalBranchesService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<ZonalBranches> getEntity() {
		// TODO Auto-generated method stub
		return ZonalBranches.class;
	}

}
