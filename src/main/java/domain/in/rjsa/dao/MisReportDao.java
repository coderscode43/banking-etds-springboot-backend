package domain.in.rjsa.dao;

import domain.in.rjsa.model.fy.MisReport;

public interface MisReportDao extends DaoInterfaceFY<Long, MisReport> {
	public MisReport getByKey(Long key) ;

}
