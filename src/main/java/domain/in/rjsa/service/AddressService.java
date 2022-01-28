package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Address;

public interface AddressService extends ServiceTDSInterface<Long,Address>{
	Address getByKey(Long addressId);
}
