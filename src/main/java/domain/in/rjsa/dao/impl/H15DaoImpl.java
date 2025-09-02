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
//import domain.in.rjsa.dao.H15Dao;
//import domain.in.rjsa.model.fy.H15;
//
//@Repository("H15Dao")
//public class H15DaoImpl extends AbstractDaoFY<Long, H15>
//implements H15Dao{
//
//	public List<H15> search(HashMap entity, int pageNo, int noOfResult) {
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
//		if (entity.get("aadhaarNumberoftheAssessee") != null) {
//			criteria.add(Restrictions.eqOrIsNull("aadhaarNumberoftheAssessee", entity.get("aadhaarNumberoftheAssessee")));
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
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<H15>) criteria.list();
//}
//	
//	public List<H15> searchExcel(HashMap entity) {
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
//		if (entity.get("aadhaarNumberoftheAssessee") != null) {
//			criteria.add(Restrictions.eqOrIsNull("aadhaarNumberoftheAssessee", entity.get("aadhaarNumberoftheAssessee")));
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
//		return (List<H15>) criteria.list();
//}
//}


package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.H15Dao;
import domain.in.rjsa.model.fy.H15;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("H15Dao")
public class H15DaoImpl extends AbstractDaoFY<Long, H15> implements H15Dao {

    @SuppressWarnings("unchecked")
    @Override
    public List<H15> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<H15> cq = cb.createQuery(H15.class);
        Root<H15> root = cq.from(H15.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("nameofAssesseeDeclarant") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("nameofAssesseeDeclarant"), entity.get("nameofAssesseeDeclarant")),
                    cb.isNull(root.get("nameofAssesseeDeclarant"))
            ));
        }
        if (entity.get("panoftheAssessee") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("panoftheAssessee"), entity.get("panoftheAssessee")),
                    cb.isNull(root.get("panoftheAssessee"))
            ));
        }
        if (entity.get("aadhaarNumberoftheAssessee") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("aadhaarNumberoftheAssessee"), entity.get("aadhaarNumberoftheAssessee")),
                    cb.isNull(root.get("aadhaarNumberoftheAssessee"))
            ));
        }
        if (entity.get("warning") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("warning"), entity.get("warning")),
                    cb.isNull(root.get("warning"))
            ));
        }
        if (entity.get("error") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("error"), entity.get("error")),
                    cb.isNull(root.get("error"))
            ));
        }
        if (entity.get("custVendId") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("custVendId"), entity.get("custVendId")),
                    cb.isNull(root.get("custVendId"))
            ));
        }
        if (entity.get("accNo") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("accNo"), entity.get("accNo")),
                    cb.isNull(root.get("accNo"))
            ));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("fy"), entity.get("fy")),
                    cb.isNull(root.get("fy"))
            ));
        }
        if (entity.get("quarter") != null) {
            predicates.add(cb.like(cb.upper(root.get("quarter")), "%" + entity.get("quarter").toString().toUpperCase() + "%"));
        }
        if (entity.get("month") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("month"), entity.get("month")),
                    cb.isNull(root.get("month"))
            ));
        }
        if (entity.get("branchCode") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("branchCode"), entity.get("branchCode")),
                    cb.isNull(root.get("branchCode"))
            ));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public List<H15> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<H15> cq = cb.createQuery(H15.class);
        Root<H15> root = cq.from(H15.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("nameofAssesseeDeclarant") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("nameofAssesseeDeclarant"), entity.get("nameofAssesseeDeclarant")),
                    cb.isNull(root.get("nameofAssesseeDeclarant"))
            ));
        }
        if (entity.get("panoftheAssessee") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("panoftheAssessee"), entity.get("panoftheAssessee")),
                    cb.isNull(root.get("panoftheAssessee"))
            ));
        }
        if (entity.get("aadhaarNumberoftheAssessee") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("aadhaarNumberoftheAssessee"), entity.get("aadhaarNumberoftheAssessee")),
                    cb.isNull(root.get("aadhaarNumberoftheAssessee"))
            ));
        }
        if (entity.get("warning") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("warning"), entity.get("warning")),
                    cb.isNull(root.get("warning"))
            ));
        }
        if (entity.get("error") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("error"), entity.get("error")),
                    cb.isNull(root.get("error"))
            ));
        }
        if (entity.get("custVendId") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("custVendId"), entity.get("custVendId")),
                    cb.isNull(root.get("custVendId"))
            ));
        }
        if (entity.get("accNo") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("accNo"), entity.get("accNo")),
                    cb.isNull(root.get("accNo"))
            ));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("fy"), entity.get("fy")),
                    cb.isNull(root.get("fy"))
            ));
        }
        if (entity.get("quarter") != null) {
            predicates.add(cb.like(cb.upper(root.get("quarter")), "%" + entity.get("quarter").toString().toUpperCase() + "%"));
        }
        if (entity.get("month") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("month"), entity.get("month")),
                    cb.isNull(root.get("month"))
            ));
        }
        if (entity.get("branchCode") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("branchCode"), entity.get("branchCode")),
                    cb.isNull(root.get("branchCode"))
            ));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }
}
