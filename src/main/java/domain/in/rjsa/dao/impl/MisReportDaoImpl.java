package domain.in.rjsa.dao.impl;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.MisReportDao;
import domain.in.rjsa.model.form.MisReport;
import domain.in.rjsa.model.form.Regular24Q4Challan;

@Repository("misreportDao")
public class MisReportDaoImpl extends AbstractNewDao<Long, MisReport> implements MisReportDao {
	@SuppressWarnings("unchecked")
	public List<MisReport> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}

		if (entity.get("reportType") != null) {
			criteria.add(Restrictions.eqOrIsNull("reportType", entity.get("reportType")));
		}
		criteria.addOrder(Order.desc("date"));
		return (List<MisReport>) criteria.list();
	}
}
