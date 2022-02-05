package domain.in.rjsa.dao;

import domain.in.rjsa.model.fy.Remarks;

public interface RemarksDao extends DaoInterfaceFY<Long, Remarks>{
	public Remarks getByKey(Long key) ;
}
