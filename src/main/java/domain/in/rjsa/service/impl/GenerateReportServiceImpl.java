package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.GenerateReportDao;
import domain.in.rjsa.model.fy.GenerateReport;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.GenerateReportService;
@Transactional("transactionManager")
@Service("generateReportService")
public class GenerateReportServiceImpl extends AbstractServiceFY<Long, GenerateReport, GenerateReportDao> implements GenerateReportService{

	@Autowired 
	GenerateReportDao dao;
	
	
	@Override
	public GenerateReport getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public GenerateReportDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
