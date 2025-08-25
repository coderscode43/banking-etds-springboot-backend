//package domain.in.rjsa.dao.impl;
//
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoFY;
//import domain.in.rjsa.dao.PanUpdateListDao;
//import domain.in.rjsa.model.fy.PanUpdateList;
//
//@Repository("panUpdateListDao")
//public class PanUpdateListDaoImpl extends AbstractDaoFY<Long, PanUpdateList>
//implements PanUpdateListDao{
//
//	@SuppressWarnings("unchecked")
//	public List<PanUpdateList> search(LinkedHashMap entity, int pageNo, int noOfResult) {
//		
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		// propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		if (entity.get("challanHeading") != null) {
//			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
//		}
//		if (entity.get("previousPAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("previousPAN", entity.get("previousPAN")));
//		}
//		if (entity.get("newPAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("newPAN", entity.get("newPAN")));
//		}
//		if (entity.get("month") != null) {
//			criteria.add(Restrictions.ilike("month", entity.get("month")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<PanUpdateList>) criteria.list();
//	}
//	@SuppressWarnings("unchecked")
//	public Long findallCount(HashMap<String, Object> entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		if (entity.get("challanHeading") != null) {
//			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
//		}
//		if (entity.get("previousPAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("previousPAN", entity.get("previousPAN")));
//		}
//		if (entity.get("newPAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("newPAN", entity.get("newPAN")));
//		}
//		if (entity.get("month") != null) {
//			criteria.add(Restrictions.ilike("month", entity.get("month")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//
//
//	@Override
//	public List<PanUpdateList> searchExcel(HashMap entity) {
//		// TODO Auto-generated method stub
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		// propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		if (entity.get("challanHeading") != null) {
//			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
//		}
//		if (entity.get("previousPAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("previousPAN", entity.get("previousPAN")));
//		}
//		if (entity.get("newPAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("newPAN", entity.get("newPAN")));
//		}
//		if (entity.get("month") != null) {
//			criteria.add(Restrictions.ilike("month", entity.get("month")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		return (List<PanUpdateList>) criteria.list();
//	}
//	
//	@Override
//	public PanUpdateList getByPan(String custVendId) {
//		 Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("custVendId", custVendId);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.allEq(propertyNameValues));
//
//		return (PanUpdateList) crit.uniqueResult();
//	}
//
//
//}

package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.PanUpdateListDao;
import domain.in.rjsa.model.fy.PanUpdateList;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("panUpdateListDao")
public class PanUpdateListDaoImpl extends AbstractDaoFY<Long, PanUpdateList> implements PanUpdateListDao {

    @Override
    public List<PanUpdateList> search(LinkedHashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PanUpdateList> cq = cb.createQuery(PanUpdateList.class);
        Root<PanUpdateList> root = cq.from(PanUpdateList.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PanUpdateList> root = cq.from(PanUpdateList.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<PanUpdateList> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PanUpdateList> cq = cb.createQuery(PanUpdateList.class);
        Root<PanUpdateList> root = cq.from(PanUpdateList.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public PanUpdateList getByPan(String custVendId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PanUpdateList> cq = cb.createQuery(PanUpdateList.class);
        Root<PanUpdateList> root = cq.from(PanUpdateList.class);

        cq.select(root).where(cb.equal(root.get("custVendId"), custVendId));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    private List<Predicate> buildPredicates(Map<String, Object> entity, CriteriaBuilder cb, Root<PanUpdateList> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("custVendId") != null) {
            predicates.add(cb.or(cb.equal(root.get("custVendId"), entity.get("custVendId")),
                                 cb.isNull(root.get("custVendId"))));
        }
        if (entity.get("challanHeading") != null) {
            predicates.add(cb.or(cb.equal(root.get("challanHeading"), entity.get("challanHeading")),
                                 cb.isNull(root.get("challanHeading"))));
        }
        if (entity.get("previousPAN") != null) {
            predicates.add(cb.or(cb.equal(root.get("previousPAN"), entity.get("previousPAN")),
                                 cb.isNull(root.get("previousPAN"))));
        }
        if (entity.get("newPAN") != null) {
            predicates.add(cb.or(cb.equal(root.get("newPAN"), entity.get("newPAN")),
                                 cb.isNull(root.get("newPAN"))));
        }
        if (entity.get("month") != null) {
            predicates.add(cb.like(root.get("month"), "%" + entity.get("month").toString() + "%"));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.or(cb.equal(root.get("fy"), entity.get("fy")),
                                 cb.isNull(root.get("fy"))));
        }

        return predicates;
    }
}
