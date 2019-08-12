package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.VendorSectionAmount;
import domain.in.rjsa.service.VendorSectionAmountService;

@Controller
@RequestMapping("/apivendorSectionAmount")
public class VendorSectionAmountController extends AbstractController<Long, VendorSectionAmount, VendorSectionAmountService>{
@Autowired
VendorSectionAmountService service;
	@Override
	public VendorSectionAmountService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<VendorSectionAmount> getEntity() {
		// TODO Auto-generated method stub
		return VendorSectionAmount.class;
	}

}
