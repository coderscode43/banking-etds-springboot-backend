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
//import domain.in.rjsa.dao.CorrectionRequestAmountDetailsDao;
//import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;
//
//@Repository("correctionRequestAmountDetailsDao")
//public class CorrectionRequestAmountDetailsDaoImpl extends AbstractDaoForm<Long, CorrectionRequestAmountDetails>
//		implements CorrectionRequestAmountDetailsDao {
//
//	@Override
//	public List<CorrectionRequestAmountDetails> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(entity, criteria);
//		criteria.addOrder(Order.desc("id"));
//		return (List<CorrectionRequestAmountDetails>) criteria.list();
//	}
//
//	public void check(HashMap entity, Criteria criteria) {
//		if (entity.get("correctionRequestId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("correctionRequestId", entity.get("correctionRequestId")));
//		}
//	}
//
//	@Override
//	public List<CorrectionRequestAmountDetails> findForm(HashMap<String, Object> constrains) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		if (constrains.containsKey("correctionRequestId")) {
//			if (constrains.get("correctionRequestId") instanceof ArrayList)
//				criteria.add(
//						Restrictions.in("correctionRequestId", (List<Long>) constrains.remove("correctionRequestId")));
//		}
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//
//		criteria.add(Restrictions.allEq(constrains));
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		criteria.addOrder(Order.desc("id"));
//		return (List<CorrectionRequestAmountDetails>) criteria.list();
//	}
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
import domain.in.rjsa.dao.CorrectionRequestAmountDetailsDao;
import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;

@Repository("correctionRequestAmountDetailsDao")
public class CorrectionRequestAmountDetailsDaoImpl
        extends AbstractDaoForm<Long, CorrectionRequestAmountDetails>
        implements CorrectionRequestAmountDetailsDao {

    @Override
    public List<CorrectionRequestAmountDetails> searchExcel(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CorrectionRequestAmountDetails> cq = cb.createQuery(CorrectionRequestAmountDetails.class);
        Root<CorrectionRequestAmountDetails> root = cq.from(CorrectionRequestAmountDetails.class);

        // Apply filters
        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        TypedQuery<CorrectionRequestAmountDetails> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<CorrectionRequestAmountDetails> root,
                                            Map<String, Object> entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("correctionRequestId") != null) {
            predicates.add(cb.equal(root.get("correctionRequestId"), entity.get("correctionRequestId")));
        }

        // Add other properties if needed
        for (Map.Entry<String, Object> e : entity.entrySet()) {
            if (!e.getKey().equals("correctionRequestId")) {
                predicates.add(cb.equal(root.get(e.getKey()), e.getValue()));
            }
        }

        return predicates;
    }

    @Override
    public List<CorrectionRequestAmountDetails> findForm(HashMap<String, Object> constrains) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CorrectionRequestAmountDetails> cq = cb.createQuery(CorrectionRequestAmountDetails.class);
        Root<CorrectionRequestAmountDetails> root = cq.from(CorrectionRequestAmountDetails.class);

        List<Predicate> predicates = new ArrayList<>();

        if (constrains.containsKey("correctionRequestId")) {
            Object val = constrains.remove("correctionRequestId");
            if (val instanceof List<?>) {
                predicates.add(root.get("correctionRequestId").in((List<?>) val));
            } else {
                predicates.add(cb.equal(root.get("correctionRequestId"), val));
            }
        }

        // Apply other constraints
        for (Map.Entry<String, Object> e : constrains.entrySet()) {
            predicates.add(cb.equal(root.get(e.getKey()), e.getValue()));
        }

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        TypedQuery<CorrectionRequestAmountDetails> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
}

