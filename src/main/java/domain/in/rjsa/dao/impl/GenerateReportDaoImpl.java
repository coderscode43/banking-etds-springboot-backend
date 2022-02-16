package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.GenerateReportDao;
import domain.in.rjsa.model.fy.GenerateReport;
@Repository("generateReportDao")
public class GenerateReportDaoImpl extends AbstractDaoFY<Long, GenerateReport> implements GenerateReportDao{

}
