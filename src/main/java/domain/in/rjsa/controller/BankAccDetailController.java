package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.service.BankAccDetailService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apibank")
public class BankAccDetailController extends AbstractControllerForm<Long, BankAccDetail, BankAccDetailService>{
	@Autowired
	BankAccDetailService service;
	@Autowired
	ApplicationCache applicationCache;
		@Override
		public BankAccDetailService getService() {
			// TODO Auto-generated method stub
			return service;
		}

		@Override
		public Class<BankAccDetail> getEntity() {
			// TODO Auto-generated method stub
			return BankAccDetail.class;
		}
		
			
}
