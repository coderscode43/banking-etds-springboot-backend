package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.BLG;
import domain.in.rjsa.model.form.VendorPayment;
import domain.in.rjsa.service.BLGService;
import domain.in.rjsa.service.VendorPaymentService;

@Controller
@RequestMapping("/apiblg")
public class BLGController extends AbstractController<Long, BLG, BLGService>{
@Autowired
BLGService service;
	@Override
	public BLGService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<BLG> getEntity() {
		// TODO Auto-generated method stub
		return BLG.class;
	}

}
