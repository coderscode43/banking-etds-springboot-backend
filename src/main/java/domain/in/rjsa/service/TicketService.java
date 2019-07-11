package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Ticket;

public interface TicketService extends ServiceInterface<Long, Ticket>{
	
	public Ticket getByKey(Long id);

}
