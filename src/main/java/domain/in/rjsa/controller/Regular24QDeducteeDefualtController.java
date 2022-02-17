package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular24QDeducteeDefualt;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular24QDeducteeDefualtService;

@Controller
@RequestMapping("/apiform24QDeducteeDefault")
public class Regular24QDeducteeDefualtController
		extends AbstractBranchControllerFY<Long, Regular24QDeducteeDefualt, Regular24QDeducteeDefualtService> {

	@Autowired
	Regular24QDeducteeDefualtService service;
	


	@Override
	public Regular24QDeducteeDefualtService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular24QDeducteeDefualt> getEntity() {
		// TODO Auto-generated method stub
		return Regular24QDeducteeDefualt.class;
	}
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
		// Login l = applicationCache.getLoginDetail(getPrincipal());

		// LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		// constrains.put("clientId", l.getClientId());
		Regular24QDeducteeDefualt deducteeDefault24 = service.getByKey(id);
		ew.setDeducteeDefault24(deducteeDefault24);

		return ew;
	}

	
	
	
}
