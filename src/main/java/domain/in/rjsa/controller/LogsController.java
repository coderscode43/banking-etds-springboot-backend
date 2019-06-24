package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Logs;
import domain.in.rjsa.service.LogsService;

@Controller
@RequestMapping("/apilogs")
public class LogsController extends AbstractController<Long, Logs, LogsService>{
@Autowired
LogsService service;
	@Override
	public LogsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Logs> getEntity() {
		// TODO Auto-generated method stub
		return Logs.class;
	}

}
