package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.in.rjsa.model.fy.Tickets;

public interface TicketDao extends DaoInterfaceForm<Long,  Tickets>{
	
	List<Tickets> search(HashMap entity, int pageNo, int noOfResult);
	public Map<String,Long> getStatusCounts(Long branchCode, boolean isAdmin);
}
