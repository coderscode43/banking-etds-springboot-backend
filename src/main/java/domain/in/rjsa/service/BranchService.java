package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.form.Branch;

public interface BranchService extends ServiceInterfaceForm<Long, Branch> {

	public Branch getByKey(Long id);

	public Long findSearchCount(LinkedHashMap<String, Object> map);

	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage);
	
	public void saveNewUser(String userName, String Password,Long branchCode);
	

}
