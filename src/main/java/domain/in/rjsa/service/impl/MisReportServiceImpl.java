package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.MisReportDao;
import domain.in.rjsa.model.fy.MisReport;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.MisReportService;
@Transactional("transactionManager")
@Service("misReportService")
public class MisReportServiceImpl extends AbstractServiceForm<Long, MisReport, MisReportDao> implements  MisReportService {
	@Autowired
	MisReportDao dao;
	@Override
	public MisReportDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
//	@Override
//	public MisReport getByKey(Long id) {
//		// TODO Auto-generated method stub
//		return dao.getByKey(id);
//	}

}
