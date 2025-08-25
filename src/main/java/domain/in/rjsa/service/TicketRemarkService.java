package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.TicketRemarks;

public interface TicketRemarkService extends ServiceInterfaceForm<Long, TicketRemarks>{
	
	public TicketRemarks getByKey(Long id);

}
