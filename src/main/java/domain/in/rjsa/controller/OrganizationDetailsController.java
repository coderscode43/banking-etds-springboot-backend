package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.OrganizationDetails;
import domain.in.rjsa.service.OrganizationDetailsService;

@Controller
@RequestMapping("/apiorganizationDetails")
public class OrganizationDetailsController extends AbstractControllerForm<Long, OrganizationDetails, OrganizationDetailsService> {

	@Autowired
	OrganizationDetailsService service;
	@Override
	public OrganizationDetailsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<OrganizationDetails> getEntity() {
		// TODO Auto-generated method stub
		return OrganizationDetails.class;
	}

}
