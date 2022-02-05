package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.LogsJson;
import domain.in.rjsa.service.LogsJsonService;

@Controller
@RequestMapping("/apilogsJson")
public class LogsJsonController extends AbstractControllerFY<Long, LogsJson, LogsJsonService>{
@Autowired
LogsJsonService service;

	@Override
	public LogsJsonService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<LogsJson> getEntity() {
		// TODO Auto-generated method stub
		return LogsJson.class;
	}

}
