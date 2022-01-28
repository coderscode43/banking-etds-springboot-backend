package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.LogsJson;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.model.wrapper.LogsDetailWrapper;
import domain.in.rjsa.service.LogsJsonService;
import domain.in.rjsa.service.LogsService;

@Controller
@RequestMapping("/apilogs")
public class LogsController extends AbstractController<Long, Logs, LogsService>{
@Autowired
LogsService service;
@Autowired
LogsJsonService ljservice;

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
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		LogsDetailWrapper ld=new LogsDetailWrapper();
		Logs logs = service.getByKey(id);
		ld.setLogs(logs);
		LogsJson logsJson= ljservice.getByKey(id);
		ld.setLogsjson(logsJson);
		return ld;
	}
	
	
	

}
