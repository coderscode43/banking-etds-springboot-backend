package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.form.Branch;

public interface BranchDao extends DaoInterfaceForm<Long,  Branch>{
	public Branch getByKey(Long key) ;

	public Long findSearchCount(LinkedHashMap<String, Object> map);

	List<Branch> search(LinkedHashMap<String, Object> entity, int pageNo, int noOfResult);
}
