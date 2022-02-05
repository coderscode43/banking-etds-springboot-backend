package domain.in.rjsa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular24QDeducteeDefualt;
import domain.in.rjsa.model.wrapper.CommentsWrapper;
import domain.in.rjsa.service.CommentsService;
import domain.in.rjsa.service.Regular24QDeducteeDefualtService;

@Controller
@RequestMapping("/apiform24QDeducteeDefault")
public class Regular24QDeducteeDefualtController
		extends AbstractBranchControllerFY<Long, Regular24QDeducteeDefualt, Regular24QDeducteeDefualtService> {

	@Autowired
	Regular24QDeducteeDefualtService service;
	
	@Autowired
	CommentsService rService;


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
		    CommentsWrapper ew = new CommentsWrapper();
		    Regular24QDeducteeDefualt deducteedefualt24 = service.getByKey(id);
		    ew.setDeducteedefualt24(deducteedefualt24);
	    	HashMap<String, Object> map = new HashMap<>();
			map.put("clientId", clientId);
			ew.setListComments(rService.search(map, clientId));
			return ew;
		}

	
	
	
}
