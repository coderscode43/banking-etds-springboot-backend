//package domain.in.rjsa.dao.impl;
//
//import java.time.ZonedDateTime;
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
//import domain.in.rjsa.dao.TicketDao;
//import domain.in.rjsa.model.fy.Tickets;
//
//@Repository("ticketDao")
//public class TicketDaoImpl extends AbstractDaoForm<Long, Tickets> implements TicketDao {
//
//	@SuppressWarnings("unchecked")
//	public List<Tickets> search(HashMap entity, Long clientId) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		// entity.remove("fy");
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("dateOfOpening",
//					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(Restrictions.le("dateOfChange",
//					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
////		if (entity.get("form") != null) {
////			String form = entity.get("form").toString();
////			String[] f = form.split(Pattern.quote("-"),-1);
////			criteria.add(Restrictions.eqOrIsNull("form", f[0]));
////		}
//
//		if (entity.get("form") != null) {
//			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("remarks") != null) {
//			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
//		}
//
//		if (entity.get("quarter") != null) {
//			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", Long.valueOf((String) entity.get("branchCode"))));
//		}
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		criteria.addOrder(Order.desc("dateOfOpening"));
//		return (List<Tickets>) criteria.list();
//	}
//
//	@Override
//	public List<Tickets> search(HashMap entity, int pageNo, int noOfResult) {
//		// TODO Auto-generated method stub
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
////		entity.remove("fy");
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("dateOfOpening",
//					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(Restrictions.le("dateOfChange",
//					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
////		if (entity.get("form") != null) {
////		String form = entity.get("form").toString();
////		String[] f = form.split(Pattern.quote("-"),-1);
////		criteria.add(Restrictions.eqOrIsNull("form", f[0]));
////	}
//
//		if (entity.get("form") != null) {
//			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("remarks") != null) {
//			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
//		}
//
//		if (entity.get("quarter") != null) {
//			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		criteria.addOrder(Order.desc("dateOfOpening"));
//		return (List<Tickets>) criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public Long findallCount(HashMap<String, Object> entity) {
//
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		// entity.remove("fy");
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("dateOfOpening",
//					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(Restrictions.le("dateOfChange",
//					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
////		if (entity.get("form") != null) {
////		String form = entity.get("form").toString();
////		String[] f = form.split(Pattern.quote("-"),-1);
////		criteria.add(Restrictions.eqOrIsNull("form", f[0]));
////	}
//
//		if (entity.get("form") != null) {
//			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("remarks") != null) {
//			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
//		}
//
//		if (entity.get("quarter") != null) {
//			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		criteria.addOrder(Order.desc("dateOfOpening"));
////           criteria.setFirstResult(pageNo * noOfResult);
////    		criteria.setMaxResults(noOfResult);
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Tickets> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("dateOfOpening",
//					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(Restrictions.le("dateOfChange",
//					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
////		if (entity.get("form") != null) {
////		String form = entity.get("form").toString();
////		String[] f = form.split(Pattern.quote("-"),-1);
////		criteria.add(Restrictions.eqOrIsNull("form", f[0]));
////	}
//
//		if (entity.get("form") != null) {
//			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("remarks") != null) {
//			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
//		}
//
//		if (entity.get("quarter") != null) {
//			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", Long.valueOf(entity.get("branchCode").toString())));
//		}
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		criteria.addOrder(Order.desc("dateOfOpening"));
//		return (List<Tickets>) criteria.list();
//	}
//
//	public Map<String, Long> getStatusCounts(Long branchCode, boolean isAdmin) {
//
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
//
//		return hashMap;
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.model.fy.Tickets;

@Repository("ticketDao")
public class TicketDaoImpl extends AbstractDaoForm<Long, Tickets> implements TicketDao {

    public List<Tickets> search(HashMap entity, Long clientId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tickets> cq = cb.createQuery(Tickets.class);
        Root<Tickets> root = cq.from(Tickets.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("clientId"), clientId));
        addTicketPredicates(entity, cb, root, predicates);

        cq.select(root).where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("dateOfOpening")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<Tickets> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tickets> cq = cb.createQuery(Tickets.class);
        Root<Tickets> root = cq.from(Tickets.class);

        List<Predicate> predicates = new ArrayList<>();
        addTicketPredicates(entity, cb, root, predicates);

        cq.select(root).where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("dateOfOpening")));

        TypedQuery<Tickets> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tickets> root = cq.from(Tickets.class);

        List<Predicate> predicates = new ArrayList<>();
        addTicketPredicates(entity, cb, root, predicates);

        cq.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Tickets> searchExcel(HashMap entity) {
        // Same as search but without pagination
        return search(entity, 0, Integer.MAX_VALUE);
    }

    @Override
    public Map<String, Long> getStatusCounts(Long branchCode, boolean isAdmin) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Tickets> root = cq.from(Tickets.class);

        cq.multiselect(root.get("status"), cb.count(root));
        if (!isAdmin) {
            cq.where(cb.equal(root.get("branchCode"), branchCode));
        }
        cq.groupBy(root.get("status"));

        List<Object[]> results = getEntityManager().createQuery(cq).getResultList();
        Map<String, Long> statusCounts = new HashMap<>();
        for (Object[] row : results) {
            statusCounts.put((String) row[0], (Long) row[1]);
        }
        return statusCounts;
    }

    /**
     * Helper to add predicates for Tickets based on the HashMap constraints
     */
    private void addTicketPredicates(HashMap entity, CriteriaBuilder cb, Root<Tickets> root, List<Predicate> predicates) {
        if (entity.get("branchId") != null)
            predicates.add(cb.equal(root.get("branchId"), entity.get("branchId")));
        if (entity.get("fromDate") != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("dateOfOpening"),
                    Date.from(ZonedDateTime.parse(entity.get("fromDate").toString()).toInstant())));
        if (entity.get("toDate") != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("dateOfChange"),
                    Date.from(ZonedDateTime.parse(entity.get("toDate").toString()).toInstant())));
        if (entity.get("status") != null)
            predicates.add(cb.equal(root.get("status"), entity.get("status")));
        if (entity.get("form") != null)
            predicates.add(cb.equal(root.get("form"), entity.get("form")));
        if (entity.get("fy") != null)
            predicates.add(cb.equal(root.get("fy"), entity.get("fy")));
        if (entity.get("remarks") != null)
            predicates.add(cb.equal(root.get("remarks"), entity.get("remarks")));
        if (entity.get("quarter") != null)
            predicates.add(cb.equal(root.get("quarter"), entity.get("quarter")));
        if (entity.get("branchCode") != null)
            predicates.add(cb.equal(root.get("branchCode"), Long.valueOf(entity.get("branchCode").toString())));
        if (entity.get("pan") != null)
            predicates.add(cb.equal(root.get("pan"), entity.get("pan")));
        if (entity.get("custVendId") != null)
            predicates.add(cb.equal(root.get("custVendId"), entity.get("custVendId")));
    }
}

