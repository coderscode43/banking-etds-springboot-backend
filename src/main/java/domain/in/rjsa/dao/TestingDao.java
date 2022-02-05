package domain.in.rjsa.dao;

import domain.in.rjsa.model.fy.Testing;

public interface TestingDao extends DaoInterfaceFY<Long,  Testing>{
	public Testing getByKey(Long key) ;
}
