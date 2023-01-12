package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.UploadCertificateDao;
import domain.in.rjsa.model.fy.UploadCertificate;

@Repository("uploadCertificateDao")
public class UploadCertificateDaoImpl extends AbstractDaoForm<Long, UploadCertificate> implements UploadCertificateDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UploadCertificate> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		if (entity.get("fileName") != null) {
			criteria.add(Restrictions.eqOrIsNull("fileName", entity.get("fileName")));
		}
		if (entity.get("TAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("form") != null) {
			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("uploadedTime",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("uploadedTime", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		} 
		criteria.addOrder(Order.desc("id"));
		return (List<UploadCertificate>) criteria.list();
	}

}
