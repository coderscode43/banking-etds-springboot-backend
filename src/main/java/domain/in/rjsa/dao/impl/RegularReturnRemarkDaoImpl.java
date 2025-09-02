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
//import domain.in.rjsa.dao.RegularReturnRemarkDao;
//import domain.in.rjsa.model.form.RegularReturnRemark;
//
//@Repository("regularReturnRemarkDao")
//public class RegularReturnRemarkDaoImpl extends AbstractDaoForm<Long, RegularReturnRemark> implements RegularReturnRemarkDao{
//
//	@Override
//	public List<RegularReturnRemark> searchExcel(HashMap map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public List<RegularReturnRemark> findForm(HashMap<String, Object> constrains) {
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
//		return (List<RegularReturnRemark>) criteria.list();
//
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.RegularReturnRemarkDao;
import domain.in.rjsa.model.form.RegularReturnRemark;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("regularReturnRemarkDao")
public class RegularReturnRemarkDaoImpl extends AbstractDaoForm<Long, RegularReturnRemark>
        implements RegularReturnRemarkDao {

    @Override
    public List<RegularReturnRemark> searchExcel(HashMap<String, Object> map) {
        return findByConstraints(map);
    }

    @SuppressWarnings("unchecked")
    public List<RegularReturnRemark> findForm(HashMap<String, Object> constraints) {
        return findByConstraints(constraints);
    }

    private List<RegularReturnRemark> findByConstraints(HashMap<String, Object> constraints) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RegularReturnRemark> cq = cb.createQuery(RegularReturnRemark.class);
        Root<RegularReturnRemark> root = cq.from(RegularReturnRemark.class);

        List<Predicate> predicates = new ArrayList<>();

        // Handle correctionRequestId separately if it's a list
        if (constraints.containsKey("correctionRequestId")) {
            Object val = constraints.get("correctionRequestId");
            if (val instanceof List) {
                predicates.add(root.get("correctionRequestId").in((List<Long>) val));
            } else {
                predicates.add(cb.equal(root.get("correctionRequestId"), val));
            }
            constraints.remove("correctionRequestId");
        }

        // Add all other equality constraints
        for (Map.Entry<String, Object> entry : constraints.entrySet()) {
            if (entry.getValue() != null) {
                predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
            }
        }

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("id")));

        TypedQuery<RegularReturnRemark> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
}

