package domain.in.rjsa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.TicketRemarkDao;
import domain.in.rjsa.model.fy.TicketRemarks;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.TicketRemarkService;

@Transactional("transactionManager")
@Service("ticketRemark")
public class TicketRemarkServiceImpl extends AbstractServiceForm<Long, TicketRemarks, TicketRemarkDao> implements TicketRemarkService{
	@Autowired
	TicketRemarkDao dao;
		@Override
		public TicketRemarkDao getPrimaryDao() {
			// TODO Auto-generated method stub
			return dao;
		}
		@Override
		public TicketRemarks getByKey(Long id) {
			// TODO Auto-generated method stub
			return dao.getByKey(id);
		}
		@Override
		public String createUserExcel(LinkedHashMap<String, Object> map) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
			// TODO Auto-generated method stub
			return null;
		}

	}

