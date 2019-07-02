package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.tds.EFILLINGLOGIN;
import domain.in.rjsa.model.tds.TRACESSLOGIN;
import domain.in.rjsa.service.EFILLINGLOGINService;
import domain.in.rjsa.service.TRACESSLOGINService;

@Controller
@RequestMapping("/apiEFILLINGLOGIN")
public class EFILLINGLOGINController extends AbstractTDSController<String, EFILLINGLOGIN, EFILLINGLOGINService>{
@Autowired
EFILLINGLOGINService service;

@Override
public EFILLINGLOGINService getService() {
	// TODO Auto-generated method stub
	return service;
}

@Override
public Class<EFILLINGLOGIN> getEntity() {
	// TODO Auto-generated method stub
	return EFILLINGLOGIN.class;
}
	

}
