package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.REQUESTSTATUSDao;
import domain.in.rjsa.model.tds.REQUESTSTATUS;

@Repository("REQUESTSTATUSDao")
public class REQUESTSTATUSDaoImpl extends AbstractDaoTaxo<String, REQUESTSTATUS> implements REQUESTSTATUSDao{

	@Override
	public List<REQUESTSTATUS> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

}
