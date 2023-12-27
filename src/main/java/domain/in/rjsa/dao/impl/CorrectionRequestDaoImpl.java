package domain.in.rjsa.dao.impl;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl.CriterionEntry;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.CorrectionRequestDao;
import domain.in.rjsa.model.form.CorrectionRequest;

@Repository("correctionRequestDao")
public class CorrectionRequestDaoImpl extends AbstractDaoForm<Long, CorrectionRequest> implements CorrectionRequestDao {

	@Override
	public List<CorrectionRequest> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		check(entity, criteria);
		criteria.addOrder(Order.desc("date"));
		return (List<CorrectionRequest>) criteria.list();
	}

	public Long findallCount(HashMap<String, Object> entity) {

		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		check(entity, criteria);
		criteria.addOrder(Order.desc("date"));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public List<CorrectionRequest> search(HashMap entity, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		check(entity, criteria);
		criteria.addOrder(Order.desc("id"));
		return (List<CorrectionRequest>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<CorrectionRequest> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);
		check(entity, criteria);
		criteria.add(Restrictions.allEq(propertyNameValues));
		return (List<CorrectionRequest>) criteria.list();
	}

//	optional
	@SuppressWarnings("unchecked")
	public List<CorrectionRequest> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		if (constrains.get("fromDate") != null) {

			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) constrains.get("fromDate")).toInstant())));

		}
		if (constrains.get("toDate") != null) {
			criteria.add(Restrictions.le("date",
					Date.from(ZonedDateTime.parse((String) constrains.get("toDate")).toInstant())));
		}
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<CorrectionRequest>) criteria.list();

	}

	@Override
	public Map<String, Long> getStatusCounts(Long branchCode, boolean isAdmin) {
		Criteria criteria = createEntityCriteria();
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("status"));
		projectionList.add(Projections.rowCount());
		criteria.setProjection(projectionList);
		if (!isAdmin) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", branchCode));
			;
		}
		List results = criteria.list();
		Map<String, Long> hashMap = new HashMap();
		java.util.Iterator it = results.iterator();
		while (it.hasNext()) {
			Object[] obj = (Object[]) it.next();
			String status = obj[0].toString();
			Long count = Long.valueOf(obj[1].toString());
			hashMap.put(status, count);
		}

		return hashMap;
	}

	public void check(HashMap entity, Criteria criteria) {
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("fromDate") != null) {

			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));

		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
		if (entity.get("custId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custId", entity.get("custId")));
		}
//		if (entity.get("form") != null) {
//			String form = entity.get("form").toString();
//			String[] f = form.split(Pattern.quote("-"),-1);
//			criteria.add(Restrictions.eqOrIsNull("form", f[0]));
//		}
		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("nameOfCust") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfCust", entity.get("nameOfCust")));
		}
		if (entity.get("panOfCust") != null) {
			criteria.add(Restrictions.eqOrIsNull("panOfCust", entity.get("panOfCust")));
		}
		if (entity.get("nameOfRequest") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameOfRequest", entity.get("nameOfRequest")));
		}

		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("empNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("empNo", Long.valueOf((String) entity.get("empNo"))));
		}
		if (entity.get("ticketNumber") != null) {
			criteria.add(Restrictions.eqOrIsNull("ticketNumber", Long.valueOf((String) entity.get("ticketNumber"))));
		}
		if (entity.get("typeOfCorrection") != null) {
			criteria.add(Restrictions.eqOrIsNull("typeOfCorrection", entity.get("typeOfCorrection")));
		}
		if (entity.get("remark") != null) {
			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
		}
		if (entity.get("approvedBy") != null) {
			criteria.add(Restrictions.eqOrIsNull("approvedBy", entity.get("approvedBy")));
		}
		if (entity.get("approvedOn") != null) {
			criteria.add(Restrictions.eqOrIsNull("approvedOn", entity.get("approvedOn")));
		}
		if (entity.get("correctionBy") != null) {
			criteria.add(Restrictions.eqOrIsNull("correctionBy", entity.get("correctionBy")));
		}
		if (entity.get("correctionOn") != null) {
			criteria.add(Restrictions.eqOrIsNull("correctionOn", entity.get("correctionOn")));
		}
		if (entity.get("status") != null) {
			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
		}

	}

}
