//package domain.in.rjsa.dao.impl;
//
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
//import domain.in.rjsa.dao.AbstractDaoTaxo;
//import domain.in.rjsa.dao.DEDUCTORDETAILSDao;
//import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
//
//@Repository("DEDUCTORDETAILSDao")
//public class DEDUCTORDETAILSDaoImpl extends AbstractDaoTaxo<Long, DEDUCTORDETAILS> implements DEDUCTORDETAILSDao {
//	@SuppressWarnings("unchecked")
//	public List<DEDUCTORDETAILS> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//
//		if (entity.get("TAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN").toString()));
//		}
//		if (entity.get("STATE") != null) {
//			criteria.add(Restrictions.eqOrIsNull("STATE", entity.get("STATE").toString()));
//		}
//		if (entity.get("CITY") != null) {
//			criteria.add(Restrictions.eqOrIsNull("CITY", entity.get("CITY").toString()));
//		}
//
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<DEDUCTORDETAILS>) criteria.list();
//	}
//
//	@Override
//	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("TAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN").toString()));
//		}
//		if (entity.get("STATE") != null) {
//			criteria.add(Restrictions.eqOrIsNull("STATE", entity.get("STATE").toString()));
//		}
//		if (entity.get("CITY") != null) {
//			criteria.add(Restrictions.eqOrIsNull("CITY", entity.get("CITY").toString()));
//		}
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//	@Override
//	public List<DEDUCTORDETAILS> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//
//		if (entity.get("TAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN").toString()));
//		}
//		if (entity.get("STATE") != null) {
//			criteria.add(Restrictions.eqOrIsNull("STATE", entity.get("STATE").toString()));
//		}
//		if (entity.get("CITY") != null) {
//			criteria.add(Restrictions.eqOrIsNull("CITY", entity.get("CITY").toString()));
//		}
//
//		criteria.addOrder(Order.desc("id"));
////  		criteria.setFirstResult(pageNo * noOfResult);
////  		criteria.setMaxResults(noOfResult);
//		return (List<DEDUCTORDETAILS>) criteria.list();
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.DEDUCTORDETAILSDao;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("DEDUCTORDETAILSDao")
public class DEDUCTORDETAILSDaoImpl extends AbstractDaoTaxo<Long, DEDUCTORDETAILS> implements DEDUCTORDETAILSDao {

    @Override
    public List<DEDUCTORDETAILS> search(HashMap<String, Object> entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DEDUCTORDETAILS> cq = cb.createQuery(DEDUCTORDETAILS.class);
        Root<DEDUCTORDETAILS> root = cq.from(DEDUCTORDETAILS.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("TAN") != null) {
            predicates.add(cb.equal(root.get("TAN"), entity.get("TAN").toString()));
        }
        if (entity.get("STATE") != null) {
            String formattedState = formatStateQuery(entity.get("STATE").toString());
            predicates.add(cb.equal(root.get("STATE"), formattedState));
        }

        if (entity.get("CITY") != null) {
            predicates.add(cb.equal(root.get("CITY"), entity.get("CITY").toString()));
        }

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        TypedQuery<DEDUCTORDETAILS> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }
    
    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DEDUCTORDETAILS> root = cq.from(DEDUCTORDETAILS.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("TAN") != null) {
            predicates.add(cb.equal(root.get("TAN"), entity.get("TAN").toString()));
        }
        if (entity.get("STATE") != null) {
            String formattedState = formatStateQuery(entity.get("STATE").toString());
            predicates.add(cb.equal(root.get("STATE"), formattedState));
        }

        if (entity.get("CITY") != null) {
            predicates.add(cb.equal(root.get("CITY"), entity.get("CITY").toString()));
        }

        cq.select(cb.count(root))
          .where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public Long findSearchCount(LinkedHashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DEDUCTORDETAILS> root = cq.from(DEDUCTORDETAILS.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("TAN") != null) {
            predicates.add(cb.equal(root.get("TAN"), entity.get("TAN").toString()));
        }
        if (entity.get("STATE") != null) {
            predicates.add(cb.equal(root.get("STATE"), entity.get("STATE").toString()));
        }
        if (entity.get("CITY") != null) {
            predicates.add(cb.equal(root.get("CITY"), entity.get("CITY").toString()));
        }

        cq.select(cb.count(root))
          .where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<DEDUCTORDETAILS> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DEDUCTORDETAILS> cq = cb.createQuery(DEDUCTORDETAILS.class);
        Root<DEDUCTORDETAILS> root = cq.from(DEDUCTORDETAILS.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("TAN") != null) {
            predicates.add(cb.equal(root.get("TAN"), entity.get("TAN").toString()));
        }
        if (entity.get("STATE") != null) {
            String formattedState = formatStateQuery(entity.get("STATE").toString());
            predicates.add(cb.equal(root.get("STATE"), formattedState));
        }
        if (entity.get("CITY") != null) {
            predicates.add(cb.equal(root.get("CITY"), entity.get("CITY").toString()));
        }

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq).getResultList();
    }
    
    //Helper method 
    private String formatStateQuery(String stateQuery) {
        String[] parts = stateQuery.trim().split("-");
        if (parts.length == 2) {
            String stateName = parts[0].trim().toUpperCase();
            String stateCode = parts[1].trim();
            return stateCode + "-" + stateName;
        }
        return stateQuery.trim();
    }


}
