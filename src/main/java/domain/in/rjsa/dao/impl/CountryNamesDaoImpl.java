package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.CountryNamesDao;
import domain.in.rjsa.model.form.CountryNames;

@Repository("countryNamesDao")
public class CountryNamesDaoImpl extends AbstractNewDao<Long, CountryNames> implements CountryNamesDao{

}
