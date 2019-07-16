package domain.in.rjsa.model.wrapper;

import java.util.List;

import domain.in.rjsa.model.form.Ticket;
import lombok.Data;

@Data
public class TicketWrapper {
	Ticket ticket;
	public List<Ticket> ticketList;
	

}
