package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.tds.GH15RETURNSTATUS;

public interface GH15RETURNSTATUSDao extends DaoInterfaceTaxo<Long,  GH15RETURNSTATUS> {

	List<GH15RETURNSTATUS> search(HashMap entity, int pageNo, int noOfResult);
	
}
