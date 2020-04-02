package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.in.rjsa.model.form.Ajax;
import domain.in.rjsa.model.form.CountryNames;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.service.CountryNamesService;

@Controller
@RequestMapping("/apicountryNames")
public class CountryNamesController extends AbstractController<Long, CountryNames, CountryNamesService>{
	
	@Autowired
	CountryNamesService service;
	
	@Override
	public CountryNamesService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<CountryNames> getEntity() {
		// TODO Auto-generated method stub
		return CountryNames.class;
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// ------------------- ajax Entities ---------------------------------

	@RequestMapping(value = "/ajax/{clientId}", method = RequestMethod.POST)
	public ResponseEntity<?> ajax(@RequestBody Ajax ajax, @PathVariable Long clientId) {
		// verify the clientId authorization
		try {
			List<?> list = getAjax(ajax.getName(), ajax.getTerm(), clientId);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getAjax(String name, String term, Long clientId) {
		// TODO Auto-generated method stub
		Login l = applicationCache.getLoginDetail(getPrincipal());
		HashMap<String, Object> constrains = new HashMap<>();
		return getService().ajax(name, term, constrains);
	}
}
