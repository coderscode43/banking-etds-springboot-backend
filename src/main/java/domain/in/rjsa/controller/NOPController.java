package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.NOP;
import domain.in.rjsa.service.NOPService;

@Controller
@RequestMapping("/apinop")
public class NOPController extends AbstractController<Long, NOP, NOPService>{
@Autowired
NOPService service;
	@Override
	public NOPService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<NOP> getEntity() {
		// TODO Auto-generated method stub
		return NOP.class;
	}

}
