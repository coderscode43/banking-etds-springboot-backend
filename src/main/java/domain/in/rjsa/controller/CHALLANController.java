package domain.in.rjsa.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.CHALLAN;
import domain.in.rjsa.service.CHALLANService;

@Controller
@RequestMapping("/apichallan")
public class CHALLANController extends AbstractControllerTaxo<String, CHALLAN, CHALLANService> {

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
		return getService().search(map, pageNo, resultPerPage);
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());


	


}
