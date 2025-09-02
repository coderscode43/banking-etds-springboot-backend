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
//import domain.in.rjsa.dao.AbstractDaoFY;
//import domain.in.rjsa.dao.G15Dao;
//import domain.in.rjsa.model.fy.G15;
//
//@Repository("G15Dao")
//public class G15DaoImpl extends AbstractDaoFY<Long, G15>
//implements G15Dao{
//
//	public List<G15> search(HashMap entity,int pageNo,int resultPerPage) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//		if (entity.get("nameofAssesseeDeclarant") != null) {
//			criteria.add(Restrictions.eqOrIsNull("nameofAssesseeDeclarant", entity.get("nameofAssesseeDeclarant")));
//		}
//		if (entity.get("panoftheAssessee") != null) {
//			criteria.add(Restrictions.eqOrIsNull("panoftheAssessee", entity.get("panoftheAssessee")));
//		}
//		if (entity.get("aadhaarNumber") != null) {
//			criteria.add(Restrictions.eqOrIsNull("aadhaarNumber", entity.get("aadhaarNumber")));
//		}
//		if (entity.get("warning") != null) {
//			criteria.add(Restrictions.eqOrIsNull("warning", entity.get("warning")));
//		}
//		if (entity.get("error") != null) {
//			criteria.add(Restrictions.eqOrIsNull("error", entity.get("error")));
//		}
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		if (entity.get("accNo") != null) {
//			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("quarter") != null) {
//			criteria.add(Restrictions.ilike("quarter", "%" + entity.get("quarter").toString() + "%"));
//		}
//		if (entity.get("month") != null) {
//			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//		return (List<G15>) criteria.list();
//		
//		
//}
//	
//	public List<G15> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//		if (entity.get("nameofAssesseeDeclarant") != null) {
//			criteria.add(Restrictions.eqOrIsNull("nameofAssesseeDeclarant", entity.get("nameofAssesseeDeclarant")));
//		}
//		if (entity.get("panoftheAssessee") != null) {
//			criteria.add(Restrictions.eqOrIsNull("panoftheAssessee", entity.get("panoftheAssessee")));
//		}
//		if (entity.get("aadhaarNumber") != null) {
//			criteria.add(Restrictions.eqOrIsNull("aadhaarNumber", entity.get("aadhaarNumber")));
//		}
//		if (entity.get("warning") != null) {
//			criteria.add(Restrictions.eqOrIsNull("warning", entity.get("warning")));
//		}
//		if (entity.get("error") != null) {
//			criteria.add(Restrictions.eqOrIsNull("error", entity.get("error")));
//		}
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//		if (entity.get("accNo") != null) {
//			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("quarter") != null) {
//			criteria.add(Restrictions.ilike("quarter", "%" + entity.get("quarter").toString() + "%"));
//		}
//		if (entity.get("month") != null) {
//			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//		return (List<G15>) criteria.list();
//		
//		
//}
//}


package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.G15Dao;
import domain.in.rjsa.model.fy.G15;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("G15Dao")
public class G15DaoImpl extends AbstractDaoFY<Long, G15> implements G15Dao {

    @Override
    public List<G15> search(HashMap entity, int pageNo, int resultPerPage) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<G15> cq = cb.createQuery(G15.class);
        Root<G15> root = cq.from(G15.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);
        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * resultPerPage)
                .setMaxResults(resultPerPage)
                .getResultList();
    }

    @Override
    public List<G15> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<G15> cq = cb.createQuery(G15.class);
        Root<G15> root = cq.from(G15.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);
        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Build predicates dynamically from the entity map
     */
    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<G15> root, Map<String, Object> entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("nameofAssesseeDeclarant") != null) {
            predicates.add(cb.equal(root.get("nameofAssesseeDeclarant"), entity.get("nameofAssesseeDeclarant")));
        }
        if (entity.get("panoftheAssessee") != null) {
            predicates.add(cb.equal(root.get("panoftheAssessee"), entity.get("panoftheAssessee")));
        }
        if (entity.get("aadhaarNumber") != null) {
            predicates.add(cb.equal(root.get("aadhaarNumber"), entity.get("aadhaarNumber")));
        }
        if (entity.get("warning") != null) {
            predicates.add(cb.equal(root.get("warning"), entity.get("warning")));
        }
        if (entity.get("error") != null) {
            predicates.add(cb.equal(root.get("error"), entity.get("error")));
        }
        if (entity.get("custVendId") != null) {
            predicates.add(cb.equal(root.get("custVendId"), entity.get("custVendId")));
        }
        if (entity.get("accNo") != null) {
            predicates.add(cb.equal(root.get("accNo"), entity.get("accNo")));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.equal(root.get("fy"), entity.get("fy")));
        }
        if (entity.get("quarter") != null) {
            predicates.add(cb.like(root.get("quarter"), "%" + entity.get("quarter") + "%"));
        }
        if (entity.get("month") != null) {
            predicates.add(cb.equal(root.get("month"), entity.get("month")));
        }
        if (entity.get("branchCode") != null) {
            predicates.add(cb.equal(root.get("branchCode"), entity.get("branchCode")));
        }

        return predicates;
    }
}
