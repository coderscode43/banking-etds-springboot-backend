package domain.in.rjsa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.service.AddressService;

@Controller
@RequestMapping("/apiaddress")
public class AddressController extends AbstractControllerForm<Long, Address, AddressService>{
	@Autowired
	AddressService service;
		@Override
		public AddressService getService() {
			// TODO Auto-generated method stub
			return service;
		}

		@Override
		public Class<Address> getEntity() {
			// TODO Auto-generated method stub
			return Address.class;
		}

}
