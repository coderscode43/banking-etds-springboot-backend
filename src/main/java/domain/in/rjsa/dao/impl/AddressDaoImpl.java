package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.AddressDao;
import domain.in.rjsa.model.form.Address;
@Repository("addressDao")
public class AddressDaoImpl extends AbstractTDSDao<Long, Address> implements AddressDao{

}
