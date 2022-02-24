package domain.in.rjsa.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.CHALLANDao;
import domain.in.rjsa.model.tds.CHALLAN;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.CHALLANService;

@Transactional("tdsTxManager")
@Service("CHALLANService")
public class CHALLANServiceImpl extends AbstractServiceTaxo<String, CHALLAN, CHALLANDao> implements CHALLANService{
	@Autowired
	CHALLANDao dao;
		@Override
		public CHALLANDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}
		@Override
		public CHALLAN getByKey(Long id) {
			// TODO Auto-generated method stub
			return dao.getByKey(id);
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
	}
