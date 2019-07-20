package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.AddressDao;
import domain.in.rjsa.dao.BankAccDetailDao;
import domain.in.rjsa.dao.PensionerDao;
import domain.in.rjsa.model.form.Pensioner;
import domain.in.rjsa.model.wrapper.PensionerDetailWrapper;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.PensionerService;

@Transactional("transactionManager")
@Service("pensionerService")
public class PensionerServiceImpl extends AbstractService<Long, Pensioner, PensionerDao> implements PensionerService{
@Autowired
PensionerDao dao;

@Autowired
AddressDao cdao;

@Autowired
AddressDao pdao;

@Autowired
BankAccDetailDao bdao;


	@Override
	public PensionerDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Pensioner getByKey(Long pensionerId) {
		// TODO Auto-generated method stub
		return dao.getByKey(pensionerId);
	}
	
	@Override
	public void saveEntity(PensionerDetailWrapper pensioner) {
		
		bdao.persist(pensioner.getBank());
		cdao.persist(pensioner.getCaddress());
		pdao.persist(pensioner.getPaddress());
		pensioner.getPensioner().setBankId(pensioner.getBank().getId());
		pensioner.getPensioner().setCaddrId(pensioner.getCaddress().getId());
		pensioner.getPensioner().setPaddrId(pensioner.getPaddress().getId());
		save(pensioner.getPensioner());
		
		
		
	}

}
