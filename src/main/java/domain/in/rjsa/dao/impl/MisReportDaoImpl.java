package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.MisReportDao;
import domain.in.rjsa.model.form.MisReport;
@Repository("misreportDao")
public class MisReportDaoImpl extends AbstractNewDao<Long, MisReport> implements MisReportDao {

}
