package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular26QDeducteeDefualt;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.service.Regular26QDeducteeDefualtService;

@Controller
@RequestMapping("/apiform26QDeducteeDefault")
public class Regular26QDeducteeDefualtController
		extends AbstractBranchControllerFY<Long, Regular26QDeducteeDefualt, Regular26QDeducteeDefualtService> {

	@Autowired
	Regular26QDeducteeDefualtService service;
	
	

	@Override
	public Regular26QDeducteeDefualtService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular26QDeducteeDefualt> getEntity() {
		// TODO Auto-generated method stub
		return Regular26QDeducteeDefualt.class;
	}

	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
		// Login l = applicationCache.getLoginDetail(getPrincipal());

		// LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		// constrains.put("clientId", l.getClientId());
		Regular26QDeducteeDefualt deductee2 = service.getByKey(id);
		ew.setDeductee2(deductee2);

		return ew;
	}


}
