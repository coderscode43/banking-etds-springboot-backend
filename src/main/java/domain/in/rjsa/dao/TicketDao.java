package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.in.rjsa.model.form.Ticket;

public interface TicketDao extends DaoInterfaceForm<Long,  Ticket>{
	
	List<Ticket> search(HashMap entity, int pageNo, int noOfResult);
	public Map<String,Long> getStatusCounts(Long branchCode, boolean isAdmin);
}
