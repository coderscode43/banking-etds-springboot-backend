package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.GenerateReport;
import domain.in.rjsa.service.GenerateReportService;
@Controller
@RequestMapping("/apigenerateReport")
public class GenerateReportController extends AbstractBranchControllerFY<Long, GenerateReport, GenerateReportService>{

	@Autowired
	GenerateReportService service;
	@Override
	public Class<GenerateReport> getEntity() {
		// TODO Auto-generated method stub
		return GenerateReport.class;
	}

	@Override
	public GenerateReportService getService() {
		// TODO Auto-generated method stub
		return service;
	}

}
