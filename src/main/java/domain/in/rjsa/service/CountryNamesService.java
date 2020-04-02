package domain.in.rjsa.service;

import domain.in.rjsa.model.form.CountryNames;

public interface CountryNamesService extends ServiceInterface<Long, CountryNames> {
	public CountryNames getByKey(Long id);
}
