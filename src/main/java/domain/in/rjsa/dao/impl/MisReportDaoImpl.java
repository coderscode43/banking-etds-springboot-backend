package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.MisReportDao;
import domain.in.rjsa.model.fy.MisReport;

@Repository("misReportDao")
public class MisReportDaoImpl extends AbstractDaoForm<Long, MisReport> implements MisReportDao {
	@SuppressWarnings("unchecked")
	public List<MisReport> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.eqOrIsNull("fromDate", entity.get("fromDate")));
		}
		if (entity.get("toDate") != null) {
			criteria.add(Restrictions.eqOrIsNull("toDate", entity.get("toDate")));
		}

		if (entity.get("reportType") != null) {
			criteria.add(Restrictions.eqOrIsNull("reportType", entity.get("reportType")));
		}
		criteria.addOrder(Order.desc("fromDate"));
		return (List<MisReport>) criteria.list();
	}
}
