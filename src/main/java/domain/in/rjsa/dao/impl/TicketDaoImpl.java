package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.model.form.Ticket;
@Repository("ticketDao")
public class TicketDaoImpl extends AbstractNewDao<Long, Ticket> implements TicketDao{

}
