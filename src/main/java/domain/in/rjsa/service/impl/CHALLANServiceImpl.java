package domain.in.rjsa.service.impl;

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
		public CHALLAN getByKey(String tan) {
			// TODO Auto-generated method stub
			return dao.getByKey(tan);
		}

	}
