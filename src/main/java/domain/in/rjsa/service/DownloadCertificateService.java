package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.form.DownloadCertificate;

public interface DownloadCertificateService {

	public List<DownloadCertificate> findAll(HashMap<String, Object> constrains, int pageNo, int noOfResult);
	
	public Long findallCount(HashMap<String, Object> constrains);
	
	public List<DownloadCertificate> search(HashMap map,Long clientId);

	public DownloadCertificate uniqueSearch(HashMap map);
	
}
