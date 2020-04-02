package domain.in.rjsa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.MessageDao;
import domain.in.rjsa.model.form.Message;
import domain.in.rjsa.service.AbstractService;
import domain.in.rjsa.service.MessageService;

@Transactional("transactionManager")
@Service("messageService")
public class MessageServiceImpl extends AbstractService<Long, Message, MessageDao> implements MessageService{

	@Autowired
	MessageDao dao;
	@Override
	public MessageDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	@Override
	public Message getByKey(Long id) {
		// TODO Auto-generated method stub
		return dao.getByKey(id);
	}
	
}
