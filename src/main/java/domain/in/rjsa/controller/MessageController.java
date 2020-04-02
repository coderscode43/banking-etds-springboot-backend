package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Message;
import domain.in.rjsa.service.MessageService;

@Controller
@RequestMapping("/apimessage")
public class MessageController extends AbstractController<Long, Message, MessageService> {
	
	@Autowired
	MessageService service;
	
	@Override
	public MessageService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Message> getEntity() {
		// TODO Auto-generated method stub
		return Message.class;
	}
}
