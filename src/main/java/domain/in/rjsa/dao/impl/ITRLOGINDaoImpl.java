package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.ITRLOGINDao;
import domain.in.rjsa.model.tds.ITRLOGIN;

@Repository("itrLoginDao")
public class ITRLOGINDaoImpl extends AbstractDaoTaxo<Long, ITRLOGIN> implements ITRLOGINDao {

	@Override
	public List<ITRLOGIN> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
