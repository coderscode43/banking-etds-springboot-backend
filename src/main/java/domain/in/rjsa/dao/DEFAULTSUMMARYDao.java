package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.tds.DEFAULTSUMMARY;

public interface DEFAULTSUMMARYDao extends DaoInterfaceTaxo<Long,  DEFAULTSUMMARY>{
	List<DEFAULTSUMMARY> search(HashMap entity, int pageNo, int noOfResult);
}
