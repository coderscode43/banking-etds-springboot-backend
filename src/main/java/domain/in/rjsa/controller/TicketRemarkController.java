package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.TicketRemark;
import domain.in.rjsa.service.TicketRemarkService;

@Controller
@RequestMapping("/apiticketRemark")
public class TicketRemarkController extends AbstractControllerForm<Long, TicketRemark, TicketRemarkService>{
	@Autowired
	TicketRemarkService service;

	@Override
	public TicketRemarkService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<TicketRemark> getEntity() {
		// TODO Auto-generated method stub
		return TicketRemark.class;
	}
}