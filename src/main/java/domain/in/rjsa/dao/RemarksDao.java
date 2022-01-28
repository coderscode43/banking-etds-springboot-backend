package domain.in.rjsa.dao;

import domain.in.rjsa.model.fy.Remarks;

public interface RemarksDao extends DaoInterface<Long, Remarks>{
	public Remarks getByKey(Long key) ;
}
