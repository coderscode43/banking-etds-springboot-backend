package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.in.rjsa.model.form.CorrectionRequest;

public interface CorrectionRequestDao extends DaoInterfaceForm<Long,  CorrectionRequest>{
	public CorrectionRequest getByKey(Long key) ;
	List<CorrectionRequest> search(HashMap entity, int pageNo, int noOfResult);
	public Map<String,Long> getStatusCounts(Long branchCode, boolean isAdmin);
}
