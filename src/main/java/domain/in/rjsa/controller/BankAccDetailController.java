package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.BankAccDetail;
import domain.in.rjsa.service.BankAccDetailService;

@Controller
@RequestMapping("/apibank")
public class BankAccDetailController extends AbstractController<Long, BankAccDetail, BankAccDetailService>{
	@Autowired
	BankAccDetailService service;
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
