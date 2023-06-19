package domain.in.rjsa.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.in.rjsa.model.form.Ticket;

public interface TicketService extends ServiceInterfaceForm<Long, Ticket>{
	
	public Ticket getByKey(Long id);

	List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);

	public Map<String, Long> getStatusDetails(Long branchCode, boolean isAdmin);
}
