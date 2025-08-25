//package domain.in.rjsa.dao.impl;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.ZonedDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.ProjectionList;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoForm;
//import domain.in.rjsa.dao.CorrectionRequestDao;
//import domain.in.rjsa.model.form.CorrectionRequest;
//
//@Repository("correctionRequestDao")
//public class CorrectionRequestDaoImpl extends AbstractDaoForm<Long, CorrectionRequest> implements CorrectionRequestDao {
//
//	@Override
//	public List<CorrectionRequest> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(entity, criteria);
//		criteria.addOrder(Order.desc("correctionRequestDate"));
//		return (List<CorrectionRequest>) criteria.list();
//	}
//
//	public Long findallCount(HashMap<String, Object> entity) {
//
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(entity, criteria);
//		criteria.addOrder(Order.desc("correctionRequestDate"));
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//	@Override
//	public List<CorrectionRequest> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(entity, criteria);
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<CorrectionRequest>) criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<CorrectionRequest> search(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);
//		check(entity, criteria);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		return (List<CorrectionRequest>) criteria.list();
//	}
//
////	optional
//	@SuppressWarnings("unchecked")
//	public List<CorrectionRequest> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		if (constrains.get("fromDate") != null) {
//
//			criteria.add(Restrictions.ge("date",
//					Date.from(ZonedDateTime.parse((String) constrains.get("fromDate")).toInstant())));
//
//		}
//		if (constrains.get("toDate") != null) {
//			criteria.add(Restrictions.le("date",
//					Date.from(ZonedDateTime.parse((String) constrains.get("toDate")).toInstant())));
//		}
//		criteria.add(Restrictions.allEq(constrains));
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<CorrectionRequest>) criteria.list();
//
//	}
//
//	@Override
//	public Map<String, Long> getStatusCounts(Long branchCode, boolean isAdmin) {
//		Criteria criteria = createEntityCriteria();
//		ProjectionList projectionList = Projections.projectionList();
//		projectionList.add(Projections.groupProperty("status"));
//		projectionList.add(Projections.rowCount());
//		criteria.setProjection(projectionList);
//		if (!isAdmin) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", branchCode));
//		}
//		List results = criteria.list();
//		Map<String, Long> hashMap = new HashMap();
//		java.util.Iterator it = results.iterator();
//		while (it.hasNext()) {
//			Object[] obj = (Object[]) it.next();
//			String status = obj[0].toString();
//			Long count = Long.valueOf(obj[1].toString());
//			hashMap.put(status, count);
//		}
//		return hashMap;
//	}
//
//	public void check(HashMap entity, Criteria criteria) {
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("fromDate") != null) {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			try {
//				Date date = sdf.parse(ZonedDateTime.parse((String) entity.get("fromDate")).truncatedTo(ChronoUnit.DAYS)
//						.toInstant().toString());
//				criteria.add(Restrictions.ge("lastUpdatedOn", date));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
////			criteria.add(Restrictions.ge("lastUpdatedOn", Date.from(ZonedDateTime.parse((String)entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			try {
//				Date date = sdf.parse(ZonedDateTime.parse((String) entity.get("toDate")).truncatedTo(ChronoUnit.DAYS)
//						.toInstant().toString());
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTime(date);
//				calendar.set(Calendar.HOUR_OF_DAY, 23);
//				calendar.set(Calendar.MINUTE, 59);
//				calendar.set(Calendar.SECOND, 59);
//				criteria.add(Restrictions.le("lastUpdatedOn", calendar.getTime()));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
////			criteria.add(Restrictions.le("lastUpdatedOn",
////					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}
//		if (entity.get("custId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custId", entity.get("custId")));
//		}
////		if (entity.get("form") != null) {
////			String form = entity.get("form").toString();
////			String[] f = form.split(Pattern.quote("-"),-1);
////			criteria.add(Restrictions.eqOrIsNull("form", f[0]));
////		}
//		if (entity.get("quarter") != null) {
//			criteria.add(Restrictions.ilike("quarter", "%" + entity.get("quarter").toString() + "%"));
//		}
//		if (entity.get("name") != null) {
//			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
//		}
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.ilike("pan", "%" + entity.get("pan") + "%"));
//		}
//		if (entity.get("nameOfRequest") != null) {
//			criteria.add(Restrictions.eqOrIsNull("nameOfRequest", entity.get("nameOfRequest")));
//		}
//
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//		if (entity.get("empNo") != null) {
//			criteria.add(Restrictions.eqOrIsNull("empNo", Long.valueOf((String) entity.get("empNo"))));
//		}
//		if (entity.get("ticketNumber") != null) {
//			criteria.add(Restrictions.eqOrIsNull("ticketNumber", Long.valueOf((String) entity.get("ticketNumber"))));
//		}
//		if (entity.get("typeOfCorrection") != null) {
//			criteria.add(Restrictions.ilike("typeOfCorrection",
//					"%" + entity.get("typeOfCorrection").toString().replace("^", "/") + "%"));
//		}
//		if (entity.get("remark") != null) {
//			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
//		}
//		if (entity.get("approvedBy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("approvedBy", entity.get("approvedBy")));
//		}
//		if (entity.get("approvedOn") != null) {
//			criteria.add(Restrictions.eqOrIsNull("approvedOn", entity.get("approvedOn")));
//		}
//		if (entity.get("correctionBy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("correctionBy", entity.get("correctionBy")));
//		}
//		if (entity.get("correctionOn") != null) {
//			criteria.add(Restrictions.eqOrIsNull("correctionOn", entity.get("correctionOn")));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
//
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.CorrectionRequestDao;
import domain.in.rjsa.model.form.CorrectionRequest;

@Repository("correctionRequestDao")
public class CorrectionRequestDaoImpl extends AbstractDaoForm<Long, CorrectionRequest> implements CorrectionRequestDao {

    @Override
    public List<CorrectionRequest> searchExcel(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CorrectionRequest> cq = cb.createQuery(CorrectionRequest.class);
        Root<CorrectionRequest> root = cq.from(CorrectionRequest.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("correctionRequestDate")));

        TypedQuery<CorrectionRequest> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CorrectionRequest> root = cq.from(CorrectionRequest.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(cb.count(root))
          .where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<CorrectionRequest> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CorrectionRequest> cq = cb.createQuery(CorrectionRequest.class);
        Root<CorrectionRequest> root = cq.from(CorrectionRequest.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        TypedQuery<CorrectionRequest> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<CorrectionRequest> search(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CorrectionRequest> cq = cb.createQuery(CorrectionRequest.class);
        Root<CorrectionRequest> root = cq.from(CorrectionRequest.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<CorrectionRequest> root, Map<String, Object> entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("fromDate") != null) {
            Date fromDate = parseDate(entity.get("fromDate").toString(), true);
            predicates.add(cb.greaterThanOrEqualTo(root.get("lastUpdatedOn"), fromDate));
        }

        if (entity.get("toDate") != null) {
            Date toDate = parseDate(entity.get("toDate").toString(), false);
            predicates.add(cb.lessThanOrEqualTo(root.get("lastUpdatedOn"), toDate));
        }

        // Simple equality filters
        List<String> equalityFields = Arrays.asList(
            "id","custId","fy", "name", "nameOfRequest", "branchCode", "remark",
            "approvedBy", "approvedOn", "correctionBy", "correctionOn", "status", "empNo", "ticketNumber"
        );
        for (String field : equalityFields) {
            if (entity.get(field) != null) {
                predicates.add(cb.equal(root.get(field), entity.get(field)));
            }
        }

        // LIKE filters
        if (entity.get("quarter") != null) {
            predicates.add(cb.like(root.get("quarter"), "%" + entity.get("quarter") + "%"));
        }
        if (entity.get("pan") != null) {
            predicates.add(cb.like(root.get("pan"), "%" + entity.get("pan") + "%"));
        }
        if (entity.get("typeOfCorrection") != null) {
            predicates.add(cb.like(root.get("typeOfCorrection"),
                    "%" + entity.get("typeOfCorrection").toString().replace("^", "/") + "%"));
        }

        return predicates;
    }

    private Date parseDate(String dateString, boolean startOfDay) {
        try {
            ZonedDateTime zdt = ZonedDateTime.parse(dateString).truncatedTo(ChronoUnit.DAYS);
            Calendar cal = Calendar.getInstance();
            cal.setTime(Date.from(zdt.toInstant()));
            if (startOfDay) {
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
            } else {
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
            }
            return cal.getTime();
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format: " + dateString, e);
        }
    }

    @Override
    public Map<String, Long> getStatusCounts(Long branchCode, boolean isAdmin) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<CorrectionRequest> root = cq.from(CorrectionRequest.class);

        cq.multiselect(root.get("status"), cb.count(root))
          .groupBy(root.get("status"));

        if (!isAdmin) {
            cq.where(cb.equal(root.get("branchCode"), branchCode));
        }

        List<Object[]> results = getEntityManager().createQuery(cq).getResultList();
        Map<String, Long> map = new HashMap<>();
        for (Object[] obj : results) {
            map.put(obj[0].toString(), (Long) obj[1]);
        }
        return map;
    }
}
