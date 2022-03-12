package domain.in.rjsa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.in.rjsa.model.tds.CHALLAN;
import domain.in.rjsa.service.CHALLANService;

@Controller
@RequestMapping("/apichallan")
public class CHALLANController extends AbstractControllerTaxo<String, CHALLAN, CHALLANService>{

	@Autowired
	CHALLANService service;

	@Override
	public CHALLANService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<CHALLAN> getEntity() {
		// TODO Auto-generated method stub
		return CHALLAN.class;
	}
	public List<?> getSearch(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return getService().search(map,pageNo,resultPerPage);
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/files/{deductee}/{certificate}/{fy}/{q}/{pan}", method = RequestMethod.GET)

	public @ResponseBody void download(HttpServletRequest request, HttpServletResponse response,  @PathVariable String deductee,@PathVariable String certificate,@PathVariable String fy,@PathVariable String q,@PathVariable String pan ){
        String fileName="C:\\Users\\STAFF19112020-01\\Documents\\"+ deductee +"\\"+ certificate +"\\"+ fy +"\\"+ q +"\\"+ pan + ".pdf";
        PrintWriter out=null;

        try{
            System.out.println(fileName.substring(fileName.lastIndexOf('/')+1)+"fileName");
            response.setContentType("application/pdf");
            response.setHeader("Cache-Control", "must-revalidate");
            response.setHeader( "Pragma", "public" );
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName.substring(fileName.lastIndexOf('/')+1) );
            out = response.getWriter();
            int i;
            FileInputStream inputStream = new FileInputStream(fileName);
            while ((i = inputStream.read()) != -1) {
                out.write(i);
//                System.out.println(out);
            }
            inputStream.close();
            out.close();

        }
        catch(Exception e){
            System.out.println(e);
        }


    }
	
	
	
	
	
	
	
//	public ResponseEntity<InputStreamResource>  getFile(HttpServletResponse response, @PathVariable String deductee,@PathVariable String certificate,@PathVariable String fy,@PathVariable String q,@PathVariable String pan ) { 
//		try
//        {
//			System.out.println(response);
//			String path = "C:\\Users\\STAFF19112020-01\\Documents\\"+ deductee +"\\"+ certificate +"\\"+ fy +"\\"+ q +"\\"+ pan + ".pdf";
//            File file = new File(path) ;
////            HttpHeaders respHeaders = new HttpHeaders();
////            MediaType mediaType = MediaType.parseMediaType("application/pdf");
////            respHeaders.setContentType(mediaType);
////            respHeaders.setContentLength(file.length());
////            respHeaders.setContentDispositionFormData("attachment", file.getName());
//            InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
//            String mimeType = URLConnection.guessContentTypeFromName(path);
//            response.setContentType(mimeType);
//    		response.setHeader("Content-disposition", "attachment; filename=" + path);
//    		response.getOutputStream().close();
//            
//            return new ResponseEntity<InputStreamResource>(isr, HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
//        }
//	}
}



