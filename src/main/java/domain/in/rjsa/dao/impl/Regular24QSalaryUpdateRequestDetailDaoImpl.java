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
import domain.in.rjsa.dao.Regular24QSalaryUpdateRequestDetailDao;
import domain.in.rjsa.model.form.Regular24QSalaryUpdateRequestDetail;

@Repository("regular24QSalaryUpdateRequestDetailDao")
public class Regular24QSalaryUpdateRequestDetailDaoImpl extends AbstractNewDao<Long, Regular24QSalaryUpdateRequestDetail>
		implements Regular24QSalaryUpdateRequestDetailDao {
	@SuppressWarnings("unchecked")
	public List<Regular24QSalaryUpdateRequestDetail> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("fromDateOfEmployment",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(Restrictions.le("fromDateOfEmployment",
					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}

		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if(entity.get("docId") != null) {
			criteria.add(Restrictions.eqOrIsNull("docId", entity.get("docId")));
		}

		criteria.addOrder(Order.desc("id"));
		return (List<Regular24QSalaryUpdateRequestDetail>) criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Regular24QSalaryUpdateRequestDetail> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.in("branchId", (List<Long>)constrains.remove("branchId")));
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<Regular24QSalaryUpdateRequestDetail>) criteria.list();
	}
}
