package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.PANITR2324Dao;
import domain.in.rjsa.model.tds.PANITR2324;

@Repository("PANITR2324Dao")
public class PANITR2324DaoImpl extends AbstractDaoTaxo<Long, PANITR2324> implements PANITR2324Dao {

	@Override
	public List<PANITR2324> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
