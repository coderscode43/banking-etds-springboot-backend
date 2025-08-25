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
//import domain.in.rjsa.dao.EmployeeMasterDao;
//import domain.in.rjsa.model.form.EmployeeMaster;
//@Repository("employeeMasterDao")
//public class EmployeeMasterDaoImpl extends AbstractDaoForm<Long, EmployeeMaster> implements EmployeeMasterDao{
//	@Override
//	public List<EmployeeMaster> search(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("employeeId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("employeeId", entity.get("employeeId")));
//		}
//		if (entity.get("employeeName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("employeeName", entity.get("employeeName")));
//		}
//		if (entity.get("employeeCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("employeeCode", entity.get("employeeCode")));
//		}
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
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
//		if (entity.get("designation") != null) {
//			criteria.add(Restrictions.eqOrIsNull("designation", entity.get("designation")));
//		}
//		
//		
//		
//		return (List<EmployeeMaster>) criteria.list();
//	}
//
//	@Override
//	public List<EmployeeMaster> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("employeeId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("employeeId", entity.get("employeeId")));
//		}
//		if (entity.get("employeeName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("employeeName", entity.get("employeeName")));
//		}
//		if (entity.get("employeeCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("employeeCode", entity.get("employeeCode")));
//		}
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
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
//		if (entity.get("designation") != null) {
//			criteria.add(Restrictions.eqOrIsNull("designation", entity.get("designation")));
//		}
//		
//		
//		
//		return (List<EmployeeMaster>) criteria.list();
//	}
//
//}

package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.EmployeeMasterDao;
import domain.in.rjsa.model.form.EmployeeMaster;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("employeeMasterDao")
public class EmployeeMasterDaoImpl extends AbstractDaoForm<Long, EmployeeMaster> implements EmployeeMasterDao {

    @Override
    public List<EmployeeMaster> search(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EmployeeMaster> cq = cb.createQuery(EmployeeMaster.class);
        Root<EmployeeMaster> root = cq.from(EmployeeMaster.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<EmployeeMaster> searchExcel(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EmployeeMaster> cq = cb.createQuery(EmployeeMaster.class);
        Root<EmployeeMaster> root = cq.from(EmployeeMaster.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Build predicates dynamically based on the provided entity map
     */
    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<EmployeeMaster> root, HashMap<String, Object> entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("branchId") != null) {
            predicates.add(cb.equal(root.get("branchId"), entity.get("branchId")));
        }
        if (entity.get("employeeId") != null) {
            predicates.add(cb.equal(root.get("employeeId"), entity.get("employeeId")));
        }
        if (entity.get("employeeName") != null) {
            predicates.add(cb.equal(root.get("employeeName"), entity.get("employeeName")));
        }
        if (entity.get("employeeCode") != null) {
            predicates.add(cb.equal(root.get("employeeCode"), entity.get("employeeCode")));
        }
        if (entity.get("pan") != null) {
            predicates.add(cb.equal(root.get("pan"), entity.get("pan")));
        }
        if (entity.get("maker") != null) {
            predicates.add(cb.equal(root.get("maker"), entity.get("maker")));
        }
        if (entity.get("checker") != null) {
            predicates.add(cb.equal(root.get("checker"), entity.get("checker")));
        }
        if (entity.get("status") != null) {
            predicates.add(cb.equal(root.get("status"), entity.get("status")));
        }
        if (entity.get("designation") != null) {
            predicates.add(cb.equal(root.get("designation"), entity.get("designation")));
        }

        return predicates;
    }
}
