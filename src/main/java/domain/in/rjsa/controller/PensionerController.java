package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Pensioner;
import domain.in.rjsa.service.PensionerService;

@Controller
@RequestMapping("/apipensioner")
public class PensionerController extends AbstractController<Long, Pensioner, PensionerService>{
@Autowired
PensionerService service;
	@Override
	public PensionerService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Pensioner> getEntity() {
		// TODO Auto-generated method stub
		return Pensioner.class;
	}

}
