package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.fy.Tickets;
import lombok.Data;

@Data
public class TicketWrapper {
	Tickets tickets;
	public List<Tickets> ticketList;
	

}
