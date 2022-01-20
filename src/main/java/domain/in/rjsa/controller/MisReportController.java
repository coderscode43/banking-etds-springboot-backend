package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.MisReport;
import domain.in.rjsa.service.MisReportService;
@Controller
@RequestMapping("/apigenerateReport")
public class MisReportController extends AbstractController<Long, MisReport, MisReportService> {
	@Autowired
	MisReportService service;

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


