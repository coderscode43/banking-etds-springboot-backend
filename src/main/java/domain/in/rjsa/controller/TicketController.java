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
	
//	@RequestMapping(value = "/getFile/{clientId}/{id}", method = RequestMethod.GET)
//	public void downloadInvoice(HttpServletResponse response,@PathVariable Long id,
//			@PathVariable Long clientId) {
//		// verify the clientId authorization
//		
//		try {
//			HashMap<String, Object>constrains= new HashMap<>();
//			constrains.put("id",id);
//			constrains.put("clientId",clientId);
//			Ticket fd = service.uniqueSearch(constrains);
//			if("pdf".equalsIgnoreCase(fd.getType())) {
//				response.setContentType("application/pdf");
//			}else {
//				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");	
//			}
//			response.getOutputStream().write(fd.getFile());
//		    response.getOutputStream().close();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//
//	}
//
//	
//	
//	
//	@RequestMapping(value = "/getFileLocation/{clientId}/{fileId}", method = RequestMethod.POST)
//	public void downloadFile(HttpServletResponse response,@PathVariable Long id,
//			@PathVariable Long clientId) {
//		// verify the clientId authorization
//		
//		try {
//			HashMap<String, Object>constrains= new HashMap<>();
//			constrains.put("id",id);
//			constrains.put("clientId",clientId);
//			Ticket fd = service.uniqueSearch(constrains);
//			File file = new File(id+"");
//			FileUtils.writeByteArrayToFile(file, fd.getFile());
//			response.setContentType("application/"+fd.getType());
//			response.setHeader("Content-disposition", "attachment; filename="
//					+ file.getName());
//			Path path = file.toPath();
//			OutputStream out;
//			out = response.getOutputStream();
//			Files.copy(path, out);
//			out.close();
//			file.delete();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//
//	}

}
