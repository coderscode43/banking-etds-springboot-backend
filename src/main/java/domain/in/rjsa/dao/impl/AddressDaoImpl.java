package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.AddressDao;
import domain.in.rjsa.model.form.Address;
@Repository("addressDao")
public class AddressDaoImpl extends AbstractDaoForm<Long, Address> implements AddressDao{

}
