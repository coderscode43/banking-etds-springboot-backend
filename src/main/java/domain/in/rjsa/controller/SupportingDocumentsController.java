package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import domain.in.rjsa.model.form.SupportingDocuments;
import domain.in.rjsa.service.SupportingDocumentsService;

@Controller
@RequestMapping("/apisupportingDocuments")
public class SupportingDocumentsController extends AbstractControllerForm<Long, SupportingDocuments, SupportingDocumentsService>{

	
	@Autowired
	SupportingDocumentsService service;
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public SupportingDocumentsService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<SupportingDocuments> getEntity() {
		// TODO Auto-generated method stub
		return SupportingDocuments.class;
	}
	
	
	
	// ------------------- Add Entity ---------------------------------

		@PostMapping(value = "/add")
		@ResponseBody
		public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity) {
			// FieldErrorDTO ermsg=new FieldErrorDTO();
			logger.info("Creating new Return instance");
			adminValidation(entity);
			create(entity);
			
			// ermsg.setMessage(" Saved Successfully");
			return new ResponseEntity<Object>(HttpStatus.CREATED);

		}

		public void create(LinkedHashMap<String, Object> entity) {


			try {
				String jsonElement = mapper.writeValueAsString(entity);
				getService().save(mapper.readValue(jsonElement, getEntity()));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
}
