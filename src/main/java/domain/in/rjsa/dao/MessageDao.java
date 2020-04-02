package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.Message;

public interface MessageDao extends DaoInterface<Long, Message>{
	public Message getByKey(Long key) ;
}
