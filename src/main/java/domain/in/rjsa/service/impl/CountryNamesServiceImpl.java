package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.CountryNamesDao;
import domain.in.rjsa.model.form.CountryNames;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.CountryNamesService;

@Transactional("transactionManager")
@Service("countryNamesService")
public class CountryNamesServiceImpl extends AbstractService<Long, CountryNames, CountryNamesDao>
		implements CountryNamesService {

	@Autowired
	CountryNamesDao dao;

	@Override
	public CountryNamesDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public CountryNames getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
}
