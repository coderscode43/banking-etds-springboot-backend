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

import domain.in.rjsa.model.fy.FileDetail;
import domain.in.rjsa.service.FileDetailService;

@Controller
@RequestMapping("/apifileDetail")
public class FileDetailController extends AbstractTDSController<Long, FileDetail, FileDetailService>{
@Autowired
FileDetailService service;
	@Override
	public FileDetailService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<FileDetail> getEntity() {
		// TODO Auto-generated method stub
		return FileDetail.class;
	}
	

	@RequestMapping(value = "/getFile/{clientId}/{fileId}", method = RequestMethod.GET)
	public void downloadInvoice(HttpServletResponse response,@PathVariable Long fileId,
			@PathVariable Long clientId) {
		// verify the clientId authorization
		
		try {
			HashMap<String, Object>constrains= new HashMap<>();
			constrains.put("id",fileId);
			constrains.put("clientId",clientId);
			FileDetail fd = service.uniqueSearch(constrains);
			if("pdf".equalsIgnoreCase(fd.getType())) {
				response.setContentType("application/pdf");
			}else {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");	
			}
			response.getOutputStream().write(fd.getFile());
		    response.getOutputStream().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

	
	
	
	@RequestMapping(value = "/getFileLocation/{clientId}/{fileId}", method = RequestMethod.POST)
	public void downloadFile(HttpServletResponse response,@PathVariable Long fileId,
			@PathVariable Long clientId) {
		// verify the clientId authorization
		
		try {
			HashMap<String, Object>constrains= new HashMap<>();
			constrains.put("id",fileId);
			constrains.put("clientId",clientId);
			FileDetail fd = service.uniqueSearch(constrains);
			File file = new File(fileId+"");
			FileUtils.writeByteArrayToFile(file, fd.getFile());
			response.setContentType("application/"+fd.getType());
			response.setHeader("Content-disposition", "attachment; filename="
					+ file.getName());
			Path path = file.toPath();
			OutputStream out;
			out = response.getOutputStream();
			Files.copy(path, out);
			out.close();
			file.delete();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

}
