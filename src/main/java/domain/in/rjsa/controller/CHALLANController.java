package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.model.form.Ajax;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.model.fy.Logs;
import domain.in.rjsa.model.tds.CHALLAN;
import domain.in.rjsa.service.CHALLANService;
import domain.in.rjsa.service.LogsService;
import domain.in.rjsa.web.ApplicationCache;

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
	
}



