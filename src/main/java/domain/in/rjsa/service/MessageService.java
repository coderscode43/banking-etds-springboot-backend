package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Message;

public interface MessageService extends ServiceInterface<Long, Message>{

	public Message getByKey(Long id);
}
