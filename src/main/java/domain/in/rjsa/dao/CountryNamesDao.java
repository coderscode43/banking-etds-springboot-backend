package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.CountryNames;

public interface CountryNamesDao extends DaoInterfaceForm<Long, CountryNames>{
	public CountryNames getByKey(Long key) ;
}
