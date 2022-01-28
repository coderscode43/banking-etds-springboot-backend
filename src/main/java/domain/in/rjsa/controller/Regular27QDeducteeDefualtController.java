package domain.in.rjsa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular27QDeducteeDefualt;
import domain.in.rjsa.model.wrapper.CommentsWrapper;
import domain.in.rjsa.service.CommentsService;
import domain.in.rjsa.service.Regular27QDeducteeDefualtService;

@Controller
@RequestMapping("/apiform27QDeducteeDefault")
public class Regular27QDeducteeDefualtController
		extends AbstractBranchController<Long, Regular27QDeducteeDefualt, Regular27QDeducteeDefualtService> {

	@Autowired
	Regular27QDeducteeDefualtService service;
	@Autowired
	CommentsService rService;

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
		    CommentsWrapper ew = new CommentsWrapper();
		    Regular27QDeducteeDefualt deducteedefualt27 = service.getByKey(id);
		    ew.setDeducteedefualt27(deducteedefualt27);
	    	HashMap<String, Object> map = new HashMap<>();
			map.put("clientId", clientId);
			ew.setListComments(rService.search(map, clientId));
			return ew;
		}
	


}
