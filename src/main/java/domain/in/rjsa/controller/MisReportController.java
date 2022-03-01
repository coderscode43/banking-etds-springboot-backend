package domain.in.rjsa.controller;


import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.in.rjsa.model.fy.MisReport;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.MisReportService;
@Controller
@RequestMapping("/apigenerateReport")
public class MisReportController extends AbstractControllerFY<Long, MisReport, MisReportService> {
	@Autowired
	MisReportService service;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public MisReportService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<MisReport> getEntity() {
		// TODO Auto-generated method stub
		return MisReport.class;
	}
	

	

	
}


