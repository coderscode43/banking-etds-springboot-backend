package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Ticket;

public interface TicketService extends ServiceInterfaceForm<Long, Ticket>{
	
	public Ticket getByKey(Long id);

}
