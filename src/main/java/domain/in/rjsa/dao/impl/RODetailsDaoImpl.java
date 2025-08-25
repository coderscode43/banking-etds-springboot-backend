//package domain.in.rjsa.dao.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoForm;
//import domain.in.rjsa.dao.RODetailsDao;
//import domain.in.rjsa.model.form.RODetails;
//
//@Repository("RODetailsDao")
//public class RODetailsDaoImpl extends AbstractDaoForm<Long, RODetails> implements RODetailsDao{
//	@Override
//	public List<RODetails> search(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		int pageNo = 0 ;
//		int noOfResult = 100;
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("roCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode").toString()));
//		}
//		if (entity.get("roName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roName", entity.get("roName")));
//		}
//		if (entity.get("roAddress") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roAddress", entity.get("roAddress")));
//		}
//		if (entity.get("roEmail") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roEmail", entity.get("roEmail")));
//		}
//		if (entity.get("roState") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roState", entity.get("roState")));
//		}
//		if (entity.get("roPincode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roPincode", entity.get("roPincode")));
//		}
//		criteria.setFirstResult(pageNo * noOfResult);
// 		criteria.setMaxResults(noOfResult);
//		return (List<RODetails>) criteria.list();
//	}
//	@Override
//	public List<RODetails> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		if(constrains.containsKey("roCode")) {
//			if(constrains.get("roCode") instanceof ArrayList)
//			criteria.add(Restrictions.in("roCode", constrains.get("roCode")));
//		}
//		criteria.add(Restrictions.allEq(constrains));
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<RODetails>) criteria.list();
//	}
//	public List<RODetails> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		int pageNo = 0 ;
//		int noOfResult = 100;
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("roCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode").toString()));
//		}
//		if (entity.get("roName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roName", entity.get("roName")));
//		}
//		if (entity.get("roAddress") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roAddress", entity.get("roAddress")));
//		}
//		if (entity.get("roEmail") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roEmail", entity.get("roEmail")));
//		}
//		if (entity.get("roState") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roState", entity.get("roState")));
//		}
//		if (entity.get("roPincode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roPincode", entity.get("roPincode")));
//		}
//		 criteria.setFirstResult(pageNo * noOfResult);
// 		criteria.setMaxResults(noOfResult);
//		return (List<RODetails>) criteria.list();
//	}
//
//	@Override
//	public RODetails getByKey(String key) {
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("employeeId", key);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.allEq(propertyNameValues));
//
//		return (RODetails) crit.uniqueResult();
//	}
//	
//	
//	
//	
//}

package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
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
import domain.in.rjsa.dao.RODetailsDao;
import domain.in.rjsa.model.form.RODetails;

@Repository("RODetailsDao")
public class RODetailsDaoImpl extends AbstractDaoForm<Long, RODetails> implements RODetailsDao {

    /**
     * Search with raw HashMap (external input)
     */
    @Override
    public List<RODetails> search(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RODetails> cq = cb.createQuery(RODetails.class);
        Root<RODetails> root = cq.from(RODetails.class);

        cq.select(root).where(buildPredicates(root, cb, entity));

        TypedQuery<RODetails> query = getEntityManager().createQuery(cq);
        query.setFirstResult(0);
        query.setMaxResults(100);

        return query.getResultList();
    }

    /**
     * Search without pagination for Excel export
     */
    @Override
    public List<RODetails> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RODetails> cq = cb.createQuery(RODetails.class);
        Root<RODetails> root = cq.from(RODetails.class);

        cq.select(root).where(buildPredicates(root, cb, entity));

        TypedQuery<RODetails> query = getEntityManager().createQuery(cq);
        query.setFirstResult(0);
        query.setMaxResults(100); // optional; can remove if you want all rows

        return query.getResultList();
    }

    /**
     * Find all with typed HashMap (internal constraints)
     */
    @Override
    public List<RODetails> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RODetails> cq = cb.createQuery(RODetails.class);
        Root<RODetails> root = cq.from(RODetails.class);

        List<Predicate> predicates = new ArrayList<>();

        // Handle roCode as list if provided
        if (constrains.containsKey("roCode") && constrains.get("roCode") instanceof List) {
            predicates.add(root.get("roCode").in((List<?>) constrains.get("roCode")));
        }

        // Add all other constraints
        constrains.forEach((k, v) -> {
            if (!k.equals("roCode")) {
                predicates.add(cb.equal(root.get(k), v));
            }
        });

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<RODetails> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    /**
     * Get by key (typed HashMap internally)
     */
    @Override
    public RODetails getByKey(String key) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RODetails> cq = cb.createQuery(RODetails.class);
        Root<RODetails> root = cq.from(RODetails.class);

        cq.select(root).where(cb.equal(root.get("employeeId"), key));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    /**
     * Helper method to build predicates from raw HashMap
     */
    private Predicate[] buildPredicates(Root<RODetails> root, CriteriaBuilder cb, HashMap entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("roCode") != null) {
            predicates.add(cb.equal(root.get("roCode"), entity.get("roCode").toString()));
        }
        if (entity.get("roName") != null) {
            predicates.add(cb.equal(root.get("roName"), entity.get("roName")));
        }
        if (entity.get("roAddress") != null) {
            predicates.add(cb.equal(root.get("roAddress"), entity.get("roAddress")));
        }
        if (entity.get("roEmail") != null) {
            predicates.add(cb.equal(root.get("roEmail"), entity.get("roEmail")));
        }
        if (entity.get("roState") != null) {
            predicates.add(cb.equal(root.get("roState"), entity.get("roState")));
        }
        if (entity.get("roPincode") != null) {
            predicates.add(cb.equal(root.get("roPincode"), entity.get("roPincode")));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
