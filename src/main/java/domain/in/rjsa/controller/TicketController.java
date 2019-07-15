package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Ticket;
import domain.in.rjsa.model.wrapper.TicketWrapper;
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
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		TicketWrapper ew = new TicketWrapper();
	//	Login l = applicationCache.getLoginDetail(getPrincipal());
		
	//	LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		Ticket ticket = service.getByKey(id);
		ew.setTicket(ticket);
		
		
		
		return ew;
	}

}
