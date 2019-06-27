package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Address;
import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Pensioner;
import domain.in.rjsa.model.wrapper.PensionerDetailWrapper;
import domain.in.rjsa.service.AddressService;
import domain.in.rjsa.service.BankAccDetailService;
import domain.in.rjsa.service.PensionerService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apipensioner")
public class PensionerController extends AbstractController<Long, Pensioner, PensionerService>{
@Autowired
PensionerService service;
@Autowired
BankAccDetailService bService;
@Autowired
AddressService aService;
@Autowired
ApplicationCache applicationCache;
	@Override
	public PensionerService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Pensioner> getEntity() {
		// TODO Auto-generated method stub
		return Pensioner.class;
	}
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		PensionerDetailWrapper ew = new PensionerDetailWrapper();
		Login l = applicationCache.getLoginDetail(getPrincipal());
       
		LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		constrains.put("clientId", l.getClientId());
		
		Pensioner pensioner = service.getByKey(id);
		ew.setPensioner(pensioner);
		if(pensioner.getBankId()!=null || pensioner.getCaddrId()!=null || pensioner.getPaddrId()!=null) {
			ew.setBank(bService.getByKey(pensioner.getBankId()));
			ew.setPaddress(aService.getByKey(pensioner.getPaddrId()));
			ew.setCaddress(aService.getByKey(pensioner.getCaddrId()));
		}else {
			ew.setBank(new BankAccDetail());
			ew.setCaddress(new Address());
			ew.setPaddress(new Address());
		}
		
		return ew;
	}

}
