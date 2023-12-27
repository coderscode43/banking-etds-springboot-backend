package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.PANITR2324Dao;
import domain.in.rjsa.model.tds.PANITR2324;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.PANITR2324Service;

@Transactional("transactionManager")
@Service("PANITR2324Service")
public class PANITR2324ServiceImpl extends AbstractServiceTaxo<Long, PANITR2324, PANITR2324Dao> implements PANITR2324Service {

	@Autowired
	PANITR2324Service service;
	
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PANITR2324Dao getPrimaryDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
