package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Regular24Q4Challan;
import domain.in.rjsa.model.form.VendorPayment;
import domain.in.rjsa.model.wrapper.SalaryDetailWrapper;
import domain.in.rjsa.model.wrapper.VendorDetailWrapper;
import domain.in.rjsa.service.VendorPaymentService;

@Controller
@RequestMapping("/apivendorPayment")
public class VendorPaymentController extends AbstractController<Long, VendorPayment, VendorPaymentService>{
	@Autowired
	VendorPaymentService service;
		@Override
		public VendorPaymentService getService() {
			// TODO Auto-generated method stub
			return service;
		}

		@Override
		public Class<VendorPayment> getEntity() {
			// TODO Auto-generated method stub
			return VendorPayment.class;
		}
		
		@Override
		public Object getDetail(Long id, Long clientId) {
			// TODO Auto-generated method stub
			VendorDetailWrapper ew = new VendorDetailWrapper();

			VendorPayment vp = service.getByKey(id);
			ew.setVendorPayment(vp);
			
			
			
			return ew;
		}

}
