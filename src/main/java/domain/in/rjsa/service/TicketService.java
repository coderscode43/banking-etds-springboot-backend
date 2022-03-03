package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Ticket;

public interface TicketService extends ServiceInterfaceForm<Long, Ticket>{
	
	public Ticket getByKey(Long id);

	List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);

}
