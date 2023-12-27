package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.ITRLOGINDao;
import domain.in.rjsa.model.tds.ITRLOGIN;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.ITRLOGINService;

@Transactional("transactionManager")
@Service("ITRLOGINService")
public class ITRLOGINServiceImpl extends AbstractServiceTaxo<Long, ITRLOGIN, ITRLOGINDao> implements ITRLOGINService {

	@Autowired
	ITRLOGINDao dao;
	
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map);
	}

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITRLOGINDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}