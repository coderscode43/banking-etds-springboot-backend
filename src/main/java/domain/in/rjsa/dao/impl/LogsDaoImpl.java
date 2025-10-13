//package domain.in.rjsa.dao.impl;
//
//import java.sql.Date;
//import java.time.ZonedDateTime;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
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
//import domain.in.rjsa.dao.LogsDao;
//import domain.in.rjsa.model.fy.Logs;
//@Repository("logsDao")
//public class LogsDaoImpl extends AbstractDaoForm<Long, Logs> implements LogsDao{
//	//@SuppressWarnings("unchecked")
//	@Override
//	public List<Logs> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		setSearch(entity,criteria);
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<Logs>) criteria.list();
//	}
//
//	@Override
//	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		setSearch(entity,criteria);
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//	@Override
//	public List<Logs> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		setSearch(entity,criteria);
//		criteria.addOrder(Order.desc("id"));
////		criteria.setFirstResult(pageNo * noOfResult);
////		criteria.setMaxResults(noOfResult);
//		return (List<Logs>) criteria.list();
//	}
//	
//	
//	private void setSearch(HashMap entity, Criteria criteria) {
//		 if(entity.get("username")!=null)
//         {
//		criteria.add(Restrictions.eqOrIsNull("username", entity.get("username")));
//         }
//         if(entity.get("entity")!=null)
//         {
//		criteria.add(Restrictions.eqOrIsNull("entity", entity.get("entity")));
//         }
//         if(entity.get("ipaddrs")!=null)
//         {
//		criteria.add(Restrictions.eqOrIsNull("ipaddrs", entity.get("ipaddrs")));
//         }      
//     	if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("date",
//					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(
//					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}  
//	}
//}


package domain.in.rjsa.dao.impl;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.model.fy.Logs;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("logsDao")
public class LogsDaoImpl extends AbstractDaoForm<Long, Logs> implements LogsDao {

    @Override
    public List<Logs> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Logs> cq = cb.createQuery(Logs.class);
        Root<Logs> root = cq.from(Logs.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public Long findSearchCount(LinkedHashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Logs> root = cq.from(Logs.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(cb.count(root))
          .where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Logs> searchExcel(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Logs> cq = cb.createQuery(Logs.class);
        Root<Logs> root = cq.from(Logs.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    private List<Predicate> buildPredicates(Map<String, Object> entity, CriteriaBuilder cb, Root<Logs> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("username") != null) {
            predicates.add(cb.or(cb.equal(root.get("username"), entity.get("username")),
                                 cb.isNull(root.get("username"))));
        }
        if (entity.get("entity") != null) {
            predicates.add(cb.or(cb.equal(root.get("entity"), entity.get("entity")),
                                 cb.isNull(root.get("entity"))));
        }
        if (entity.get("ipaddrs") != null) {
            predicates.add(cb.or(cb.equal(root.get("ipaddrs"), entity.get("ipaddrs")),
                                 cb.isNull(root.get("ipaddrs"))));
        }
        
        if (entity.get("fromDate") != null) {
			ZonedDateTime fromDate = ZonedDateTime.parse(entity.get("fromDate").toString())
					.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).with(LocalTime.MIN);
			predicates.add(cb.greaterThanOrEqualTo(root.get("logsDate"), Date.from(fromDate.toInstant())));
		}

		if (entity.get("toDate") != null) {
			ZonedDateTime fromDate = ZonedDateTime.parse(entity.get("toDate").toString())
					.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).with(LocalTime.MAX);
			predicates.add(cb.lessThanOrEqualTo(root.get("logsDate"), Date.from(fromDate.toInstant())));
		}

        return predicates;
    }
}
