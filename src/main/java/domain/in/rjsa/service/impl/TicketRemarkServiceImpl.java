package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.TicketRemarkDao;
import domain.in.rjsa.model.fy.TicketRemark;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.TicketRemarkService;

@Transactional("transactionManager")
@Service("ticketRemark")
public class TicketRemarkServiceImpl extends AbstractServiceFY<Long, TicketRemark, TicketRemarkDao> implements TicketRemarkService{
	@Autowired
	TicketRemarkDao dao;
		@Override
		public TicketRemarkDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}
		@Override
		public TicketRemark getByKey(Long id) {
			// TODO Auto-generated method stub
			return dao.getByKey(id);
		}

	}

