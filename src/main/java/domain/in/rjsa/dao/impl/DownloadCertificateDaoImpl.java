package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.DownloadCertificateDao;
import domain.in.rjsa.model.form.DownloadCertificate;

@Repository("downloadCertificateDao")
public class DownloadCertificateDaoImpl extends AbstractNewDao<Long, DownloadCertificate> implements DownloadCertificateDao{

}
