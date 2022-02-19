package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.model.fy.Ticket;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.TicketService;

@Transactional("transactionManager")
@Service("ticketService")
public class TicketServiceImpl extends AbstractServiceForm<Long, Ticket, TicketDao> implements TicketService{
@Autowired
TicketDao dao;
	@Override
	public TicketDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Ticket getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}

}
