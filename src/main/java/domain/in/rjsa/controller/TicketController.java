package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.TicketService;
@Controller
@RequestMapping("/apiticket")
public class TicketController extends AbstractController<Long, Ticket, TicketService>{
	@Autowired
	TicketService service;

	@Override
	public TicketService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Ticket> getEntity() {
		// TODO Auto-generated method stub
		return Ticket.class;
	}
	


}
