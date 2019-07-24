package domain.in.rjsa.controller;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.form.Ticket;
import domain.in.rjsa.service.TicketService;
@Controller
@RequestMapping("/apiticket")
public class TicketController extends AbstractController<Long, Ticket, TicketService>{
	@Autowired
	TicketService service;

	@Override
	public TicketService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Ticket> getEntity() {
		// TODO Auto-generated method stub
		return Ticket.class;
	}
	


}
