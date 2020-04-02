package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import domain.in.rjsa.model.form.Form15GH;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.service.Form15GHService;

@Controller
@RequestMapping("/apiform15GH")
public class Form15GHController extends AbstractController<Long, Form15GH, Form15GHService> {

	@Autowired
	Form15GHService service;

	@Override
	public Form15GHService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Form15GH> getEntity() {
		// TODO Auto-generated method stub
		return Form15GH.class;
	}

	// ------------------- Update Entity ---------------------------------

	@RequestMapping(value = "/update/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody LinkedHashMap<String, Object> entity, HttpServletResponse response,
			UriComponentsBuilder ucBuilder) {

		update(entity);
		addLogsU(entity);

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	public void update(LinkedHashMap<String, Object> entity) {
		Gson gson = new Gson();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		JsonElement jsonElement = gson.toJsonTree(entity);
		getService().update(gson.fromJson(jsonElement, getEntity()));
	}

}
