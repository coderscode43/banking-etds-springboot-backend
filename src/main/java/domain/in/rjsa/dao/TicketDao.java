package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.fy.Ticket;

public interface TicketDao extends DaoInterfaceForm<Long,  Ticket>{
	
	List<Ticket> search(HashMap entity, int pageNo, int noOfResult);

}
