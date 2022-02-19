package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.TicketRemark;

public interface TicketRemarkService extends ServiceInterfaceFY<Long, TicketRemark>{
	
	public TicketRemark getByKey(Long id);

}
