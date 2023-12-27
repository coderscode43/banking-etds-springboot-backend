package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.in.rjsa.model.form.CorrectionRequest;


public interface CorrectionRequestService extends ServiceInterfaceForm<Long, CorrectionRequest> {

	public CorrectionRequest getByKey(Long id);

	List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);

	public Map<String, Long> getStatusDetails(Long branchCode, boolean isAdmin);
	
	public void saveAmount(LinkedHashMap<String,Object> entity);

	public void autoGenerateTicketNumber(LinkedHashMap<String, Object> entity);

	public List<?> getList(HashMap<String, Object> constrains, int pageNo, int resultPerPage);

	public Object getDetail(HashMap<String, Object> constrains, Long id, String principal, Long branchCode);
	
}
