package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.AddressDao;
import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.AddressService;

@Transactional("transactionManager")
@Service("addressService")
public class AddressServiceImpl extends AbstractServiceForm<Long, Address, AddressDao> implements AddressService{
	@Autowired
	AddressDao dao;
		@Override
		public AddressDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}
		@Override
		public Address getByKey(Long addressId) {
			// TODO Auto-generated method stub
			return dao.getByKey(addressId);
		}

}
