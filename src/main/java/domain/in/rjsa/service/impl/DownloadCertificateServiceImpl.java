package domain.in.rjsa.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.DownloadCertificateDao;
import domain.in.rjsa.model.fy.DownloadCertificate;
import domain.in.rjsa.service.AbstractServiceFY;
import domain.in.rjsa.service.DownloadCertificateService;

@Transactional("transactionManager")
@Service("downloadCertificateService")
public class DownloadCertificateServiceImpl extends AbstractServiceFY<Long, DownloadCertificate, DownloadCertificateDao>implements DownloadCertificateService{

	@Autowired
	DownloadCertificateDao dao;

	@Override
	public DownloadCertificateDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	


	
}
