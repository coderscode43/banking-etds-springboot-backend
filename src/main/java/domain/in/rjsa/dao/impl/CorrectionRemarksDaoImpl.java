//package domain.in.rjsa.dao.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoForm;
//import domain.in.rjsa.dao.CorrectionRemarksDao;
//import domain.in.rjsa.model.form.CorrectionRemarks;
//
//@Repository("correctionRemarkDao")
//public class CorrectionRemarksDaoImpl extends AbstractDaoForm<Long, CorrectionRemarks> implements CorrectionRemarksDao{
//
//	@Override
//	public List<CorrectionRemarks> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(entity));
//		return (List<CorrectionRemarks>) criteria.list();
//	}
//
//	public List<CorrectionRemarks> findForm(HashMap<String, Object> constrains) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		if (constrains.containsKey("correctionRequestId")) {
//			if (constrains.get("correctionRequestId") instanceof ArrayList)
//				criteria.add(Restrictions.in("correctionRequestId", (List<Long>) constrains.remove("correctionRequestId")));
//		}
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		
//		criteria.add(Restrictions.allEq(constrains));
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		criteria.addOrder(Order.desc("id"));
//		return (List<CorrectionRemarks>) criteria.list();
//
//	}
//
//	@Override
//	public List<?> search(LinkedHashMap map, int pageNo, int resultPerPage) {
//		Criteria criteria = createEntityCriteria();
//		criteria.addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(map));
//		criteria.setFirstResult(pageNo * resultPerPage);
//		criteria.setMaxResults(resultPerPage);
//		return (List<CorrectionRemarks>) criteria.list();
//	}
//	
//}


package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.CorrectionRemarksDao;
import domain.in.rjsa.model.form.CorrectionRemarks;

@Repository("correctionRemarkDao")
public class CorrectionRemarksDaoImpl extends AbstractDaoForm<Long, CorrectionRemarks> implements CorrectionRemarksDao {

    @Override
    public List<CorrectionRemarks> searchExcel(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CorrectionRemarks> cq = cb.createQuery(CorrectionRemarks.class);
        Root<CorrectionRemarks> root = cq.from(CorrectionRemarks.class);

        List<Predicate> predicates = entity.entrySet().stream()
                .map(e -> cb.equal(root.get(e.getKey()), e.getValue()))
                .collect(Collectors.toList());

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        TypedQuery<CorrectionRemarks> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    public List<CorrectionRemarks> findForm(HashMap<String, Object> constrains) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CorrectionRemarks> cq = cb.createQuery(CorrectionRemarks.class);
        Root<CorrectionRemarks> root = cq.from(CorrectionRemarks.class);

        List<Predicate> predicates = constrains.entrySet().stream()
                .map(e -> cb.equal(root.get(e.getKey()), e.getValue()))
                .collect(Collectors.toList());

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        TypedQuery<CorrectionRemarks> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<CorrectionRemarks> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CorrectionRemarks> cq = cb.createQuery(CorrectionRemarks.class);
        Root<CorrectionRemarks> root = cq.from(CorrectionRemarks.class);

        List<Predicate> predicates = map.entrySet().stream()
                .map(e -> cb.equal(root.get(e.getKey()), e.getValue()))
                .collect(Collectors.toList());

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        TypedQuery<CorrectionRemarks> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * resultPerPage);
        query.setMaxResults(resultPerPage);

        return query.getResultList();
    }
}

