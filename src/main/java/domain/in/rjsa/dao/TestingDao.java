package domain.in.rjsa.dao;

import domain.in.rjsa.model.fy.Testing;

public interface TestingDao extends DaoFYInterface<Long,  Testing>{
	public Testing getByKey(Long key) ;
}
