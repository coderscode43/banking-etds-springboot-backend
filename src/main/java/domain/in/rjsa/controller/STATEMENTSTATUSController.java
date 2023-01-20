package domain.in.rjsa.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.STATEMENTSTATUS;
import domain.in.rjsa.service.STATEMENTSTATUSService;

@Controller
@RequestMapping("/apistatementStatus")
public class STATEMENTSTATUSController extends AbstractControllerTaxo<Long, STATEMENTSTATUS, STATEMENTSTATUSService> {

	@Autowired
	STATEMENTSTATUSService service;

	@Override
	public STATEMENTSTATUSService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<STATEMENTSTATUS> getEntity() {
		// TODO Auto-generated method stub
		return STATEMENTSTATUS.class;
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public List<?> getSearch(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
			return getService().search(map, pageNo, resultPerPage);
	}

}
