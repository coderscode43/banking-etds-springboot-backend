//package domain.in.rjsa.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoFY;
//import domain.in.rjsa.dao.MisReportDao;
//import domain.in.rjsa.model.fy.MisReport;
//
//@Repository("misReportDao")
//public class MisReportDaoImpl extends AbstractDaoFY<Long, MisReport> implements MisReportDao {
//	@SuppressWarnings("unchecked")
//	
//	public List<MisReport> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fromDate", entity.get("fromDate")));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(Restrictions.eqOrIsNull("toDate", entity.get("toDate")));
//		}
//
//		if (entity.get("reportType") != null) {
//			criteria.add(Restrictions.eqOrIsNull("reportType", entity.get("reportType")));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		criteria.addOrder(Order.desc("fromDate"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<MisReport>) criteria.list();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<MisReport> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fromDate", entity.get("fromDate")));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(Restrictions.eqOrIsNull("toDate", entity.get("toDate")));
//		}
//
//		if (entity.get("reportType") != null) {
//			criteria.add(Restrictions.eqOrIsNull("reportType", entity.get("reportType")));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		criteria.addOrder(Order.desc("fromDate"));
//		return (List<MisReport>) criteria.list();
//	}
//
//	
//}

package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.MisReportDao;
import domain.in.rjsa.model.fy.MisReport;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("misReportDao")
public class MisReportDaoImpl extends AbstractDaoFY<Long, MisReport> implements MisReportDao {

    @Override
    public List<MisReport> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MisReport> cq = cb.createQuery(MisReport.class);
        Root<MisReport> root = cq.from(MisReport.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("fromDate")));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public List<MisReport> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MisReport> cq = cb.createQuery(MisReport.class);
        Root<MisReport> root = cq.from(MisReport.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root)
          .where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("fromDate")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    private List<Predicate> buildPredicates(Map<String, Object> entity, CriteriaBuilder cb, Root<MisReport> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("fromDate") != null) {
            predicates.add(cb.or(cb.equal(root.get("fromDate"), entity.get("fromDate")),
                                 cb.isNull(root.get("fromDate"))));
        }
        if (entity.get("toDate") != null) {
            predicates.add(cb.or(cb.equal(root.get("toDate"), entity.get("toDate")),
                                 cb.isNull(root.get("toDate"))));
        }
        if (entity.get("reportType") != null) {
            predicates.add(cb.or(cb.equal(root.get("reportType"), entity.get("reportType")),
                                 cb.isNull(root.get("reportType"))));
        }
        if (entity.get("branchCode") != null) {
            predicates.add(cb.or(cb.equal(root.get("branchCode"), entity.get("branchCode")),
                                 cb.isNull(root.get("branchCode"))));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.or(cb.equal(root.get("fy"), entity.get("fy")),
                                 cb.isNull(root.get("fy"))));
        }

        return predicates;
    }
}
