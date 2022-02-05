package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.DownloadCertificateDao;
import domain.in.rjsa.model.fy.DownloadCertificate;

@Repository("downloadCertificateDao")
public class DownloadCertificateDaoImpl extends AbstractDaoFY<Long, DownloadCertificate> implements DownloadCertificateDao{

}
