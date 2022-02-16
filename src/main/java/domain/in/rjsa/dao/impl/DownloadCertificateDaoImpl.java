package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.DownloadCertificateDao;
import domain.in.rjsa.model.fy.DownloadCertificate;

@Repository("downloadCertificateDao")
public class DownloadCertificateDaoImpl extends AbstractDaoFY<Long, DownloadCertificate> implements DownloadCertificateDao{
	@SuppressWarnings("unchecked")
	public List<DownloadCertificate> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		
		 if (entity.get("certificatetype") != null) {
				criteria.add(Restrictions.eqOrIsNull("certificatetype", entity.get("certificatetype")));
			}
		 if (entity.get("customerid") != null) {
	  			criteria.add(Restrictions.eqOrIsNull("customerid", Long.valueOf((String) entity.get("customerid"))));
	  		}
		 if (entity.get("financialyear") != null) {
				criteria.add(Restrictions.eqOrIsNull("financialyear", entity.get("financialyear")));
			}
		 if (entity.get("quarter") != null) {
				criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
			}
		 if (entity.get("pan") != null) {
				criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
			}
		return (List<DownloadCertificate>) criteria.list();
	}

}
