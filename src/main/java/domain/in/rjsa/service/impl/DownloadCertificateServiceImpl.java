package domain.in.rjsa.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.DownloadCertificateDao;
import domain.in.rjsa.model.fy.DownloadCertificate;
import domain.in.rjsa.service.DownloadCertificateService;

@Transactional("transactionManager")
@Service("downloadCertificateService")
public class DownloadCertificateServiceImpl implements DownloadCertificateService{

	@Autowired
	DownloadCertificateDao dao;
	
	@Override
	public List<DownloadCertificate> findAll(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		return dao.findall(constrains, pageNo, noOfResult);
	}

	@Override
	public Long findallCount(HashMap<String, Object> constrains) {
		// TODO Auto-generated method stub
		return dao.findallCount(constrains);
	}

	@Override
	public List<DownloadCertificate> search(HashMap map, Long clientId) {
		// TODO Auto-generated method stub
		return dao.search(map, clientId);
	}

	@Override
	public DownloadCertificate uniqueSearch(HashMap map) {
		// TODO Auto-generated method stub
		return dao.uniqueSearch(map);
	}
	
}
