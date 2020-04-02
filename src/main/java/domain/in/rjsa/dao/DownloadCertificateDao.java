package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.form.DownloadCertificate;

public interface DownloadCertificateDao {

	public List<DownloadCertificate> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult);

	public Long findallCount(HashMap<String, Object> constrains);

	public List<DownloadCertificate> search(HashMap map, Long clientId);

	public DownloadCertificate uniqueSearch(HashMap map);
}
