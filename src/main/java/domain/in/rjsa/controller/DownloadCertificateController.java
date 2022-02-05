package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.DownloadCertificate;
import domain.in.rjsa.service.DownloadCertificateService;

@Controller
@RequestMapping("/apidownloadCertificate")
public class DownloadCertificateController extends AbstractBranchControllerFY<Long, DownloadCertificate, DownloadCertificateService > {

	@Autowired
	DownloadCertificateService service;
	@Autowired
	@Override
	public Class<DownloadCertificate> getEntity() {
		// TODO Auto-generated method stub
		return DownloadCertificate.class ;
	}

	@Override
	public DownloadCertificateService getService() {
		// TODO Auto-generated method stub
		return service;
	}
}
