package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Address;

public interface AddressService extends ServiceInterfaceForm<Long,Address>{
	Address getByKey(Long addressId);
}
