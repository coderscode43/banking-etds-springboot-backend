package domain.in.rjsa.service;

import domain.in.rjsa.model.form.TicketRemark;

public interface TicketRemarkService extends ServiceInterfaceForm<Long, TicketRemark>{
	
	public TicketRemark getByKey(Long id);

}
