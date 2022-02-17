package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular27QDeducteeDefualt;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
//import domain.in.rjsa.service.CommentsService;
import domain.in.rjsa.service.Regular27QDeducteeDefualtService;

@Controller
@RequestMapping("/apiform27QDeducteeDefault")
public class Regular27QDeducteeDefualtController
		extends AbstractBranchControllerFY<Long, Regular27QDeducteeDefualt, Regular27QDeducteeDefualtService> {

	@Autowired
	Regular27QDeducteeDefualtService service;
	/*@Autowired
	CommentsService rService;*/

	@Override
	public Regular27QDeducteeDefualtService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Regular27QDeducteeDefualt> getEntity() {
		// TODO Auto-generated method stub
		return Regular27QDeducteeDefualt.class;
	}

	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		SalaryDetailWrapper ew = new SalaryDetailWrapper();
		// Login l = applicationCache.getLoginDetail(getPrincipal());

		// LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		// constrains.put("clientId", l.getClientId());
		Regular27QDeducteeDefualt deducteeDefault27 = service.getByKey(id);
		ew.setDeducteeDefault27(deducteeDefault27);

		return ew;
	}
	


}
