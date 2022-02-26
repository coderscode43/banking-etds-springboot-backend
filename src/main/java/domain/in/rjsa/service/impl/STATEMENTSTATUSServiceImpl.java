package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.STATEMENTSTATUSService;

@Transactional("transactionManager")
@Service("STATEMENTSTATUSService")
public class STATEMENTSTATUSServiceImpl extends AbstractServiceTaxo<Long, STATEMENTSTATUS, STATEMENTSTATUSDao> implements STATEMENTSTATUSService{
@Autowired 
STATEMENTSTATUSDao dao;
	@Override
	public STATEMENTSTATUSDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findSearchCount(map);
	}
	@Override
	public List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return dao.search(map, pageNo, resultPerPage);
	}
	@Override
	public STATEMENTSTATUS getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

	@Override
	public List<String> ajax(String name, String term) {
		// TODO Auto-generated method stub
		return dao.ajax(name, term);
	}
}
