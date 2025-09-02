//package domain.in.rjsa.dao.impl;
//
//import java.time.ZonedDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoForm;
//import domain.in.rjsa.dao.RegularReturnDao;
//import domain.in.rjsa.model.form.RegularReturn;
//
//@Repository("regularReturnDao")
//public class RegularReturnDaoImpl extends AbstractDaoForm<Long, RegularReturn> implements RegularReturnDao {
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<RegularReturn> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
////		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
////		criteria.add(Restrictions.allEq(propertyNameValues));
//
//		check(entity, criteria);
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<RegularReturn>) criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<RegularReturn> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		int pageNo = 0;
//		int noOfResult = 100;
//		check(entity, criteria);
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<RegularReturn>) criteria.list();
//	}
//
//	public Long findallCount(HashMap<String, Object> constrains) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//
//		check(constrains, criteria);
////		criteria.add(Restrictions.allEq(constrains));
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//
//	}
//
//	@Override
//	public List<RegularReturn> findallwithBranch(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
////		String roCode = constrains.get("branchCode").toString();
////		constrains.put("roCode", roCode);
////		constrains.remove("branchCode");
//		criteria.add(Restrictions.allEq(constrains));
//		return (List<RegularReturn>) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//	public void check(HashMap<String, Object> entity, Criteria criteria) {
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode").toString()));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy").toString()));
//		}
//		if (entity.get("tan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("tan", entity.get("tan").toString()));
//		}
//		if (entity.get("quarter") != null) {
//			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter").toString()));
//		}
//		if (entity.get("form") != null) {
//			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form").toString()));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status").toString()));
//		}
//		if (entity.get("addedOn") != null) {
////			criteria.add(Restrictions.ge("addedOn",
////					Date.from(ZonedDateTime.parse(entity.get("addedOn").toString()).toInstant())));
//
//			criteria.add(Restrictions.between("addedOn",
//					Date.from(ZonedDateTime.parse(entity.get("addedOn").toString()).toInstant()
//							.truncatedTo(ChronoUnit.DAYS)),
//					Date.from(ZonedDateTime.parse(entity.get("addedOn").toString()).toInstant().plus(1, ChronoUnit.DAYS)
//							.minusNanos(1))));
//		}
//	}
//}


package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.RegularReturnDao;
import domain.in.rjsa.model.form.RegularReturn;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("regularReturnDao")
public class RegularReturnDaoImpl extends AbstractDaoForm<Long, RegularReturn> implements RegularReturnDao {

    @Override
    public List<RegularReturn> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RegularReturn> cq = cb.createQuery(RegularReturn.class);
        Root<RegularReturn> root = cq.from(RegularReturn.class);

        cq.select(root).where(buildPredicates(cb, root, entity));
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<RegularReturn> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    @Override
    public List<RegularReturn> searchExcel(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RegularReturn> cq = cb.createQuery(RegularReturn.class);
        Root<RegularReturn> root = cq.from(RegularReturn.class);

        cq.select(root).where(buildPredicates(cb, root, entity));
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<RegularReturn> query = getEntityManager().createQuery(cq);
        query.setFirstResult(0);
        query.setMaxResults(100);

        return query.getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RegularReturn> root = cq.from(RegularReturn.class);

        cq.select(cb.count(root)).where(buildPredicates(cb, root, entity));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<RegularReturn> findallwithBranch(HashMap<String, Object> entity, int pageNo, int resultPerPage) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RegularReturn> cq = cb.createQuery(RegularReturn.class);
        Root<RegularReturn> root = cq.from(RegularReturn.class);

        cq.select(root).where(buildPredicates(cb, root, entity));

        TypedQuery<RegularReturn> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * resultPerPage);
        query.setMaxResults(resultPerPage);

        return query.getResultList();
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<RegularReturn> root, Map<String, Object> entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("branchCode") != null) {
            predicates.add(cb.equal(root.get("branchCode"), entity.get("branchCode").toString()));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.equal(root.get("fy"), entity.get("fy").toString()));
        }
        if (entity.get("tan") != null) {
            predicates.add(cb.equal(root.get("tan"), entity.get("tan").toString()));
        }
        if (entity.get("quarter") != null) {
            predicates.add(cb.equal(root.get("quarter"), entity.get("quarter").toString()));
        }
        if (entity.get("form") != null) {
            predicates.add(cb.equal(root.get("form"), entity.get("form").toString()));
        }
        if (entity.get("status") != null) {
            predicates.add(cb.equal(root.get("status"), entity.get("status").toString()));
        }
        if (entity.get("addedOn") != null) {
            ZonedDateTime zdt = ZonedDateTime.parse(entity.get("addedOn").toString());
            Date start = Date.from(zdt.truncatedTo(ChronoUnit.DAYS).toInstant());
            Date end = Date.from(zdt.plusDays(1).truncatedTo(ChronoUnit.DAYS).minusNanos(1).toInstant());
            predicates.add(cb.between(root.get("addedOn"), start, end));
        }

        return predicates.toArray(new Predicate[0]);
    }
}

