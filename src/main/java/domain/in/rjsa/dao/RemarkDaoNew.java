package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.form.Remark;

public interface RemarkDaoNew extends DaoInterfaceForm<Long,  Remark>{ 
	
	Remark searchForm(Long trackId);

	Object findRemark(HashMap<String, Object> constrains, int pageNo, int resultPerPage);

	List<Remark> findTransaction(HashMap<String, Object> constrains, int pageNo, int noOfResult);


}
