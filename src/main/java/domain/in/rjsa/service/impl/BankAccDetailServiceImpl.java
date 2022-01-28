package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.BankAccDetailDao;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.service.AbstractTDSService;
import domain.in.rjsa.service.BankAccDetailService;

@Transactional("transactionManager")
@Service("bankAccDetailService")
public class BankAccDetailServiceImpl extends AbstractTDSService<Long, BankAccDetail, BankAccDetailDao> implements BankAccDetailService{
	@Autowired
	BankAccDetailDao dao;
		@Override
		public BankAccDetailDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}
		@Override
		public BankAccDetail getByKey(Long bankId) {
			// TODO Auto-generated method stub
			return dao.getByKey(bankId);
		}
}
