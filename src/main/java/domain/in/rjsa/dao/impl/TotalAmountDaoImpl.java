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
//import domain.in.rjsa.dao.TotalAmountDao;
//import domain.in.rjsa.model.fy.TotalAmount;
//
//@Repository("totalAmountDao")
//public class TotalAmountDaoImpl extends AbstractDaoFY<Long, TotalAmount> implements TotalAmountDao{
//	
//	@SuppressWarnings("unchecked")
//	public List<TotalAmount> search(LinkedHashMap entity, int pageNo, int noOfResult) {
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
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//
//		if (entity.get("sectionCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
//		}
//		if (entity.get("month") != null) {
//			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("totalAmountPaidRaw") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidRaw", Double.parseDouble(entity.get("totalAmountPaidRaw").toString())));
//		}
//		if (entity.get("totalAmountPaidUpload") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidUpload", Double.parseDouble(entity.get("totalAmountPaidUpload").toString())));
//		}
//		if (entity.get("totaltaxRaw") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totaltaxRaw", Double.parseDouble(entity.get("totaltaxRaw").toString())));
//		}
//		if (entity.get("totalTaxUploaded") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalTaxUploaded", Double.parseDouble(entity.get("totalTaxUploaded").toString())));
//		}
//		if (entity.get("remark") != null) {
//			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
//		}
//		if (entity.get("source") != null) {
//			criteria.add(Restrictions.eqOrIsNull("source", entity.get("source")));
//		}
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<TotalAmount>) criteria.list();
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
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//
//		if (entity.get("sectionCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
//		}
//		if (entity.get("month") != null) {
//			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("totalAmountPaidRaw") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidRaw", Double.parseDouble(entity.get("totalAmountPaidRaw").toString())));
//		}
//		if (entity.get("totalAmountPaidUpload") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidUpload", Double.parseDouble(entity.get("totalAmountPaidUpload").toString())));
//		}
//		if (entity.get("totaltaxRaw") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totaltaxRaw", Double.parseDouble(entity.get("totaltaxRaw").toString())));
//		}
//		if (entity.get("totalTaxUploaded") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalTaxUploaded", Double.parseDouble(entity.get("totalTaxUploaded").toString())));
//		}
//		if (entity.get("remark") != null) {
//			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
//		}
//		if (entity.get("source") != null) {
//			criteria.add(Restrictions.eqOrIsNull("source", entity.get("source")));
//		}
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//
//
//	@Override
//	public List<TotalAmount> searchExcel(HashMap entity) {
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
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//
//		if (entity.get("sectionCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
//		}
//		if (entity.get("month") != null) {
//			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("totalAmountPaidRaw") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidRaw", Double.parseDouble(entity.get("totalAmountPaidRaw").toString())));
//		}
//		if (entity.get("totalAmountPaidUpload") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidUpload", Double.parseDouble(entity.get("totalAmountPaidUpload").toString())));
//		}
//		if (entity.get("totalTaxRaw") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalTaxRaw", Double.parseDouble(entity.get("totalTaxRaw").toString())));
//		}
//		if (entity.get("totalTaxUploaded") != null) {
//			criteria.add(Restrictions.eqOrIsNull("totalTaxUploaded", Double.parseDouble(entity.get("totalTaxUploaded").toString())));
//		}
//		if (entity.get("remark") != null) {
//			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
//		}
//		if (entity.get("source") != null) {
//			criteria.add(Restrictions.eqOrIsNull("source", entity.get("source")));
//		}
//
//		return (List<TotalAmount>) criteria.list();
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.TotalAmountDao;
import domain.in.rjsa.model.fy.TotalAmount;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("totalAmountDao")
public class TotalAmountDaoImpl extends AbstractDaoFY<Long, TotalAmount> implements TotalAmountDao {

	@Override
    public List<TotalAmount> search(LinkedHashMap<?, ?> entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TotalAmount> cq = cb.createQuery(TotalAmount.class);
        Root<TotalAmount> root = cq.from(TotalAmount.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public List<TotalAmount> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TotalAmount> cq = cb.createQuery(TotalAmount.class);
        Root<TotalAmount> root = cq.from(TotalAmount.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TotalAmount> root = cq.from(TotalAmount.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    /**
     * Helper method to build predicates from HashMap dynamically.
     */
    
    // Helper method to convert date 
    
    private String convertFiscalYearToDBFormat(String fy) {
        if (fy == null || !fy.contains("-")) {
            return fy; // Already in correct format or invalid
        }

        try {
            String[] parts = fy.split("-");
            String startYear = parts[0]; 
            String endYear = parts[1];   
            return startYear + endYear; 
        } catch (Exception e) {
            return fy;
        }
    }

    
    private List<Predicate> buildPredicates(HashMap entity, CriteriaBuilder cb, Root<TotalAmount> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("custVendId") != null) {
            predicates.add(cb.equal(root.get("custVendId"), entity.get("custVendId")));
        }
        if (entity.get("challanHeading") != null) {
            predicates.add(cb.equal(root.get("challanHeading"), entity.get("challanHeading")));
        }
        if (entity.get("pan") != null) {
            predicates.add(cb.equal(root.get("pan"), entity.get("pan")));
        }
        if (entity.get("sectionCode") != null) {
            String section = entity.get("sectionCode").toString().split(Pattern.quote("-"))[0];
            predicates.add(cb.equal(root.get("sectionCode"), section));
        }
        if (entity.get("month") != null) {
            predicates.add(cb.equal(root.get("month"), entity.get("month")));
        }
        if (entity.get("fy") != null) {
            String fyRaw = entity.get("fy").toString();
            String fyConverted = convertFiscalYearToDBFormat(fyRaw);
            predicates.add(cb.equal(root.get("fy"), fyConverted));
        }
        if (entity.get("totalAmountPaidRaw") != null) {
            predicates.add(cb.equal(root.get("totalAmountPaidRaw"),
                    Double.parseDouble(entity.get("totalAmountPaidRaw").toString())));
        }
        if (entity.get("totalAmountPaidUpload") != null) {
            predicates.add(cb.equal(root.get("totalAmountPaidUpload"),
                    Double.parseDouble(entity.get("totalAmountPaidUpload").toString())));
        }
        if (entity.get("totaltaxRaw") != null) {
            predicates.add(cb.equal(root.get("totaltaxRaw"),
                    Double.parseDouble(entity.get("totaltaxRaw").toString())));
        }
        if (entity.get("totalTaxUploaded") != null) {
            predicates.add(cb.equal(root.get("totalTaxUploaded"),
                    Double.parseDouble(entity.get("totalTaxUploaded").toString())));
        }
        if (entity.get("remark") != null) {
            predicates.add(cb.equal(root.get("remark"), entity.get("remark")));
        }
        if (entity.get("source") != null) {
            predicates.add(cb.equal(root.get("source"), entity.get("source")));
        }

        return predicates;
    }

}
