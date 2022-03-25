package domain.in.rjsa.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.MonthlyChallanDao;
import domain.in.rjsa.model.fy.MonthlyChallan;
	
@Repository("monthlyChallanDao")
public class MonthlyChallanDaoImpl extends AbstractDaoFY<Long, MonthlyChallan> implements MonthlyChallanDao{
	@SuppressWarnings("unchecked")
	
	public List<MonthlyChallan> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
          if(entity.get("branchCode")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
          }
          if(entity.get("monthFY")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("monthFY", entity.get("monthFY")))	;
          }  
          if (entity.get("amtAsPerFinacle") != null) {
        	  criteria.add(Restrictions.eqOrIsNull("amtAsPerFinacle",  Double.valueOf((String)entity.get("amtAsPerFinacle"))));
  		  }
           
          if (entity.get("amtAsPerTaxCalculation") != null) 
          {
    			criteria.add(Restrictions.eqOrIsNull("amtAsPerTaxCalculation", Double.valueOf((String) entity.get("amtAsPerTaxCalculation"))));
          }
          if(entity.get("challanHeading")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
          }  
		return (List<MonthlyChallan>) criteria.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public Long findallCount(HashMap<String,Object> entity) {
		
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		
          if(entity.get("branchCode")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
          }
          if(entity.get("monthFY")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("monthFY", entity.get("monthFY")))	;
          }  
          if (entity.get("amtAsPerFinacle") != null) {
        	  criteria.add(Restrictions.eqOrIsNull("amtAsPerFinacle",  Double.valueOf((String)entity.get("amtAsPerFinacle"))));
  		  }
           
          if (entity.get("amtAsPerTaxCalculation") != null) 
          {
    			criteria.add(Restrictions.eqOrIsNull("amtAsPerTaxCalculation", Double.valueOf((String) entity.get("amtAsPerTaxCalculation"))));
          }
          if(entity.get("challanHeading")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
          }  
          return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
}