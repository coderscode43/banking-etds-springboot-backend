package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.MessageDao;
import domain.in.rjsa.model.form.Message;

@Repository("messageDao")
public class MessageDaoImpl extends AbstractNewDao<Long, Message> implements MessageDao{

}
