package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.DEFAULTSUMMARYDao;
import domain.in.rjsa.model.tds.DEFAULTSUMMARY;

@Repository("DEFAULTSUMMARYDao")
public class DEFAULTSUMMARYDaoImpl extends AbstractDaoTaxo<Long, DEFAULTSUMMARY> implements DEFAULTSUMMARYDao{

	@Override
	public List<DEFAULTSUMMARY> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
