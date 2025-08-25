//package domain.in.rjsa.dao.impl;
//
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoFY;
//import domain.in.rjsa.dao.MonthlyChallanDao;
//import domain.in.rjsa.model.fy.MonthlyChallan;
//	
//@Repository("monthlyChallanDao")
//public class MonthlyChallanDaoImpl extends AbstractDaoFY<Long, MonthlyChallan> implements MonthlyChallanDao{
//	@SuppressWarnings("unchecked")
//	public List<MonthlyChallan> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//          if(entity.get("branchCode")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//          }
//          if(entity.get("month")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")))	;
//          }  
//          if (entity.get("amtAsPerFinacle") != null) {
//        	  criteria.add(Restrictions.eqOrIsNull("amtAsPerFinacle",  Double.valueOf((String)entity.get("amtAsPerFinacle"))));
//  		  }
//           
//          if (entity.get("amtAsPerTaxCalculation") != null) 
//          {
//    			criteria.add(Restrictions.eqOrIsNull("amtAsPerTaxCalculation", Double.valueOf((String) entity.get("amtAsPerTaxCalculation"))));
//          }
//          if(entity.get("challanHeading")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
//          }  
//          if(entity.get("fy")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//          }  
//          
//          criteria.addOrder(Order.desc("branchCode"));
//			criteria.setFirstResult(pageNo * noOfResult);
//			criteria.setMaxResults(noOfResult);
//		return (List<MonthlyChallan>) criteria.list();
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	public Long findallCount(HashMap<String,Object> entity) {
//		
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		
//          if(entity.get("branchCode")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//          }
//          if(entity.get("month")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")))	;
//          }  
//          if (entity.get("amtAsPerFinacle") != null) {
//        	  criteria.add(Restrictions.eqOrIsNull("amtAsPerFinacle",  Double.valueOf((String)entity.get("amtAsPerFinacle"))));
//  		  }
//           
//          if (entity.get("amtAsPerTaxCalculation") != null) 
//          {
//    			criteria.add(Restrictions.eqOrIsNull("amtAsPerTaxCalculation", Double.valueOf((String) entity.get("amtAsPerTaxCalculation"))));
//          }
//          if(entity.get("challanHeading")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
//          }  
//          return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//	
//	
//	
//	@SuppressWarnings("unchecked")
//	public List<MonthlyChallan> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//          if(entity.get("branchCode")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//          }
//          if(entity.get("month")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")))	;
//          }  
//          if (entity.get("amtAsPerFinacle") != null) {
//        	  criteria.add(Restrictions.eqOrIsNull("amtAsPerFinacle",  Double.valueOf((String)entity.get("amtAsPerFinacle"))));
//  		  }
//           
//          if (entity.get("amtAsPerTaxCalculation") != null) 
//          {
//    			criteria.add(Restrictions.eqOrIsNull("amtAsPerTaxCalculation", Double.valueOf((String) entity.get("amtAsPerTaxCalculation"))));
//          }
//          if(entity.get("challanHeading")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
//          }  
//          if(entity.get("fy")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//          }  
//		return (List<MonthlyChallan>) criteria.list();
//	}
//}

package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.MonthlyChallanDao;
import domain.in.rjsa.model.fy.MonthlyChallan;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("monthlyChallanDao")
public class MonthlyChallanDaoImpl extends AbstractDaoFY<Long, MonthlyChallan> implements MonthlyChallanDao {

    @Override
    public List<MonthlyChallan> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MonthlyChallan> cq = cb.createQuery(MonthlyChallan.class);
        Root<MonthlyChallan> root = cq.from(MonthlyChallan.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("branchCode")));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<MonthlyChallan> root = cq.from(MonthlyChallan.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<MonthlyChallan> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MonthlyChallan> cq = cb.createQuery(MonthlyChallan.class);
        Root<MonthlyChallan> root = cq.from(MonthlyChallan.class);

        List<Predicate> predicates = buildPredicates(entity, cb, root);

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        return getEntityManager().createQuery(cq).getResultList();
    }

    private List<Predicate> buildPredicates(Map<String, Object> entity, CriteriaBuilder cb, Root<MonthlyChallan> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("branchCode") != null) {
            predicates.add(cb.or(cb.equal(root.get("branchCode"), entity.get("branchCode")),
                                 cb.isNull(root.get("branchCode"))));
        }
        if (entity.get("month") != null) {
            predicates.add(cb.or(cb.equal(root.get("month"), entity.get("month")),
                                 cb.isNull(root.get("month"))));
        }
        if (entity.get("amtAsPerFinacle") != null) {
            Double value = Double.valueOf(entity.get("amtAsPerFinacle").toString());
            predicates.add(cb.or(cb.equal(root.get("amtAsPerFinacle"), value),
                                 cb.isNull(root.get("amtAsPerFinacle"))));
        }
        if (entity.get("amtAsPerTaxCalculation") != null) {
            Double value = Double.valueOf(entity.get("amtAsPerTaxCalculation").toString());
            predicates.add(cb.or(cb.equal(root.get("amtAsPerTaxCalculation"), value),
                                 cb.isNull(root.get("amtAsPerTaxCalculation"))));
        }
        if (entity.get("challanHeading") != null) {
            predicates.add(cb.or(cb.equal(root.get("challanHeading"), entity.get("challanHeading")),
                                 cb.isNull(root.get("challanHeading"))));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.or(cb.equal(root.get("fy"), entity.get("fy")),
                                 cb.isNull(root.get("fy"))));
        }

        return predicates;
    }
}
