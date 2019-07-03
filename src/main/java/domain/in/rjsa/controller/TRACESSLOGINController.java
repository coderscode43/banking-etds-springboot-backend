package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.TRACESSLOGIN;
import domain.in.rjsa.service.TRACESSLOGINService;

@Controller
@RequestMapping("/apiTRACESSLOGIN")
public class TRACESSLOGINController extends AbstractTDSController<String, TRACESSLOGIN, TRACESSLOGINService> {
@Autowired
TRACESSLOGINService service;
	@Override
	public TRACESSLOGINService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<TRACESSLOGIN> getEntity() {
		// TODO Auto-generated method stub
		return TRACESSLOGIN.class;
	}

}
