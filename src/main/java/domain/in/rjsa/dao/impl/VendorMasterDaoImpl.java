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
//import domain.in.rjsa.dao.VendorMasterDao;
//import domain.in.rjsa.model.form.VendorMaster;
//@Repository("vendorMasterDao")
//public class VendorMasterDaoImpl extends AbstractDaoForm<Long, VendorMaster> implements VendorMasterDao{
//	@Override
//	public List<VendorMaster> search(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("vendorName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("vendorName", entity.get("vendorName")));
//		}
//		if (entity.get("address") != null) {
//			criteria.add(Restrictions.eqOrIsNull("address", entity.get("address")));
//		}
//		if (entity.get("vendorCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("vendorCode", entity.get("vendorCode")));
//		}
//		if (entity.get("maker") != null) {
//			criteria.add(Restrictions.eqOrIsNull("maker", entity.get("maker")));
//		}
//		if (entity.get("checker") != null) {
//			criteria.add(Restrictions.eqOrIsNull("checker", entity.get("checker")));
//		}
//		if (entity.get("vendorId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("vendorId", entity.get("vendorId")));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
//		return (List<VendorMaster>) criteria.list();
//	}
//
//	@Override
//	public List<VendorMaster> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("branchId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
//		}
//		if (entity.get("vendorName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("vendorName", entity.get("vendorName")));
//		}
//		if (entity.get("address") != null) {
//			criteria.add(Restrictions.eqOrIsNull("address", entity.get("address")));
//		}
//		if (entity.get("vendorCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("vendorCode", entity.get("vendorCode")));
//		}
//		if (entity.get("maker") != null) {
//			criteria.add(Restrictions.eqOrIsNull("maker", entity.get("maker")));
//		}
//		if (entity.get("checker") != null) {
//			criteria.add(Restrictions.eqOrIsNull("checker", entity.get("checker")));
//		}
//		if (entity.get("vendorId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("vendorId", entity.get("vendorId")));
//		}
//		if (entity.get("status") != null) {
//			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
//		}
//		return (List<VendorMaster>) criteria.list();
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.VendorMasterDao;
import domain.in.rjsa.model.form.VendorMaster;

@Repository("vendorMasterDao")
public class VendorMasterDaoImpl extends AbstractDaoForm<Long, VendorMaster> implements VendorMasterDao {

    @Override
    public List<VendorMaster> search(HashMap entity) {
        return runSearch(entity);
    }

    @Override
    public List<VendorMaster> searchExcel(HashMap entity) {
        return runSearch(entity);
    }

    /**
     * Common search logic reused by both search() and searchExcel().
     */
    @SuppressWarnings("unchecked")
    private List<VendorMaster> runSearch(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<VendorMaster> cq = cb.createQuery(VendorMaster.class);
        Root<VendorMaster> root = cq.from(VendorMaster.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("branchId") != null) {
            predicates.add(cb.equal(root.get("branchId"), entity.get("branchId")));
        }
        if (entity.get("vendorName") != null) {
            predicates.add(cb.equal(root.get("vendorName"), entity.get("vendorName")));
        }
        if (entity.get("address") != null) {
            predicates.add(cb.equal(root.get("address"), entity.get("address")));
        }
        if (entity.get("vendorCode") != null) {
            predicates.add(cb.equal(root.get("vendorCode"), entity.get("vendorCode")));
        }
        if (entity.get("maker") != null) {
            predicates.add(cb.equal(root.get("maker"), entity.get("maker")));
        }
        if (entity.get("checker") != null) {
            predicates.add(cb.equal(root.get("checker"), entity.get("checker")));
        }
        if (entity.get("vendorId") != null) {
            predicates.add(cb.equal(root.get("vendorId"), entity.get("vendorId")));
        }
        if (entity.get("status") != null) {
            predicates.add(cb.equal(root.get("status"), entity.get("status")));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }
}

