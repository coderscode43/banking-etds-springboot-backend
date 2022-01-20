package domain.in.rjsa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular26QDeducteeDefualt;
import domain.in.rjsa.model.wrapper.CommentsWrapper;
import domain.in.rjsa.service.CommentsService;
import domain.in.rjsa.service.Regular26QDeducteeDefualtService;

@Controller
@RequestMapping("/apiform26QDeducteeDefault")
public class Regular26QDeducteeDefualtController
		extends AbstractBranchController<Long, Regular26QDeducteeDefualt, Regular26QDeducteeDefualtService> {

	@Autowired
	Regular26QDeducteeDefualtService service;
	
	@Autowired
	CommentsService rService;

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
		    CommentsWrapper ew = new CommentsWrapper();
		    Regular26QDeducteeDefualt deducteedefualt26 = service.getByKey(id);
		    ew.setDeducteedefualt26(deducteedefualt26);
	    	HashMap<String, Object> map = new HashMap<>();
			map.put("clientId", clientId);
			ew.setListComments(rService.search(map, clientId));
			return ew;
		}


}
