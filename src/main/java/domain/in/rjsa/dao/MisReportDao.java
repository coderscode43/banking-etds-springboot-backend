package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.MisReport;
import domain.in.rjsa.model.tds.CHALLAN;

public interface MisReportDao extends DaoInterfaceFY<Long, MisReport> {
	//public MisReport getByKey(Long key) ;
	List<MisReport> search(HashMap entity, int pageNo, int noOfResult);
}
