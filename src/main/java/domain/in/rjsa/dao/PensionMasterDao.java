package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.PensionMaster;

public interface PensionMasterDao extends DaoInterface<Long,  PensionMaster>{
	public PensionMaster getByKey(Long key) ;
}
