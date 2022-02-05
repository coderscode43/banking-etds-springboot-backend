package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.Pension;

public interface PensionDao  extends DaoInterfaceForm<Long,  Pension>{
	public Pension getByKey(Long key) ;
}

