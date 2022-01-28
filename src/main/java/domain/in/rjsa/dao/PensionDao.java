package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.Pension;

public interface PensionDao  extends DaoInterface<Long,  Pension>{
	public Pension getByKey(Long key) ;
}

