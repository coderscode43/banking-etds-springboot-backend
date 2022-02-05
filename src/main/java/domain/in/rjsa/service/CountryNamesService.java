package domain.in.rjsa.service;

import domain.in.rjsa.model.form.CountryNames;

public interface CountryNamesService extends ServiceInterfaceForm<Long, CountryNames> {
	public CountryNames getByKey(Long id);
}
