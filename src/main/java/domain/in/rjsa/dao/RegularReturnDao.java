package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.form.RegularReturn;

public interface RegularReturnDao extends DaoInterfaceForm<Long,  RegularReturn>{
	
	List<RegularReturn> search(HashMap entity, int pageNo, int noOfResult);

	List<RegularReturn> findallwithBranch(HashMap<String, Object> constrains, int pageNo, int resultPerPage);

}
