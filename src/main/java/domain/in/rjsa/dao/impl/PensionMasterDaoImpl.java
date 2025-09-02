//package domain.in.rjsa.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoForm;
//import domain.in.rjsa.dao.PensionMasterDao;
//import domain.in.rjsa.model.form.PensionMaster;
//
//@Repository("pensionMasterDao")
//public class PensionMasterDaoImpl extends AbstractDaoForm<Long, PensionMaster> implements PensionMasterDao{
//	@Override
//	public List<PensionMaster> search(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("pensionerId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pensionerId", entity.get("pensionerId")));
//		}
//		if (entity.get("pensionerName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pensionerName", entity.get("pensionerName")));
//		}
//		if (entity.get("address") != null) {
//			criteria.add(Restrictions.eqOrIsNull("address", entity.get("address")));
//		}
//		if (entity.get("maker") != null) {
//			criteria.add(Restrictions.eqOrIsNull("maker", entity.get("maker")));
//		}
//		if (entity.get("checker") != null) {
//			criteria.add(Restrictions.eqOrIsNull("checker", entity.get("checker")));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
//		if (entity.get("pensionerCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pensionerCode", entity.get("pensionerCode")));
//		}
//		
//		
//		
//		return (List<PensionMaster>) criteria.list();
//	}
//
//	@Override
//	public List<PensionMaster> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("pensionerId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pensionerId", entity.get("pensionerId")));
//		}
//		if (entity.get("pensionerName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pensionerName", entity.get("pensionerName")));
//		}
//		if (entity.get("address") != null) {
//			criteria.add(Restrictions.eqOrIsNull("address", entity.get("address")));
//		}
//		if (entity.get("maker") != null) {
//			criteria.add(Restrictions.eqOrIsNull("maker", entity.get("maker")));
//		}
//		if (entity.get("checker") != null) {
//			criteria.add(Restrictions.eqOrIsNull("checker", entity.get("checker")));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
//		if (entity.get("pensionerCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pensionerCode", entity.get("pensionerCode")));
//		}
//		
//		
//		
//		return (List<PensionMaster>) criteria.list();
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
import domain.in.rjsa.dao.PensionMasterDao;
import domain.in.rjsa.model.form.PensionMaster;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("pensionMasterDao")
public class PensionMasterDaoImpl extends AbstractDaoForm<Long, PensionMaster> implements PensionMasterDao {

    @Override
    public List<PensionMaster> search(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PensionMaster> cq = cb.createQuery(PensionMaster.class);
        Root<PensionMaster> root = cq.from(PensionMaster.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<PensionMaster> searchExcel(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PensionMaster> cq = cb.createQuery(PensionMaster.class);
        Root<PensionMaster> root = cq.from(PensionMaster.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }

    private List<Predicate> buildPredicates(Map<String, Object> entity, CriteriaBuilder cb, Root<PensionMaster> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("branchId") != null) {
            predicates.add(cb.or(cb.equal(root.get("branchId"), entity.get("branchId")),
                                 cb.isNull(root.get("branchId"))));
        }
        if (entity.get("pensionerId") != null) {
            predicates.add(cb.or(cb.equal(root.get("pensionerId"), entity.get("pensionerId")),
                                 cb.isNull(root.get("pensionerId"))));
        }
        if (entity.get("pensionerName") != null) {
            predicates.add(cb.or(cb.equal(root.get("pensionerName"), entity.get("pensionerName")),
                                 cb.isNull(root.get("pensionerName"))));
        }
        if (entity.get("address") != null) {
            predicates.add(cb.or(cb.equal(root.get("address"), entity.get("address")),
                                 cb.isNull(root.get("address"))));
        }
        if (entity.get("maker") != null) {
            predicates.add(cb.or(cb.equal(root.get("maker"), entity.get("maker")),
                                 cb.isNull(root.get("maker"))));
        }
        if (entity.get("checker") != null) {
            predicates.add(cb.or(cb.equal(root.get("checker"), entity.get("checker")),
                                 cb.isNull(root.get("checker"))));
        }
        if (entity.get("status") != null) {
            predicates.add(cb.or(cb.equal(root.get("status"), entity.get("status")),
                                 cb.isNull(root.get("status"))));
        }
        if (entity.get("pensionerCode") != null) {
            predicates.add(cb.or(cb.equal(root.get("pensionerCode"), entity.get("pensionerCode")),
                                 cb.isNull(root.get("pensionerCode"))));
        }

        return predicates;
    }
}
