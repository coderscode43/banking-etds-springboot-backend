package domain.in.rjsa.dao;

import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.fy.UploadCertificate;

public interface UploadCertificateDao extends DaoInterfaceForm<Long,  UploadCertificate>{

	List<?> search(LinkedHashMap<?, ?> map, int pageNo, int resultPerPage);

}
