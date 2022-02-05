package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.GOVERNMENTDETAILSDao;
import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;
import domain.in.rjsa.service.AbstractServiceTaxo;
import domain.in.rjsa.service.GOVERNMENTDETAILSService;

@Transactional("tdsTxManager")
@Service("GOVERNMENTDETAILSService")
public class GOVERNMENTDETAILSServiceImpl extends AbstractServiceTaxo<String, GOVERNMENTDETAILS, GOVERNMENTDETAILSDao> implements GOVERNMENTDETAILSService{
	@Autowired
	GOVERNMENTDETAILSDao dao;
		@Override
		public GOVERNMENTDETAILSDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}
		@Override
		public GOVERNMENTDETAILS getByKey(String tan) {
			// TODO Auto-generated method stub
			return dao.getByKey(tan);
		}

}
