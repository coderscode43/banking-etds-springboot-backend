package domain.in.rjsa.dao.impl;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.Regular27EQDeducteeDao;
import domain.in.rjsa.model.fy.Regular27EQDeductee;
@Repository("regular27EQDeducteeDao")
public class Regular27EQDeducteeDaoImpl extends AbstractDaoFY<Long, Regular27EQDeductee> implements Regular27EQDeducteeDao {

	@SuppressWarnings("unchecked")
	public List<Regular27EQDeductee> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		 if(entity.get("branchCode")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
         } 
          
		 if(entity.get("fy")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
         } 
		 if(entity.get("collectionCode")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("collectionCode", entity.get("collectionCode")));
         } 
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
          if(entity.get("custVendId")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
          }
          
          if(entity.get("panofthedeductee")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("panofthedeductee", entity.get("panofthedeductee")));
          }
          
          if(entity.get("nameoftheDeductee")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("nameoftheDeductee", entity.get("nameoftheDeductee")));
          }
          if (entity.get("accNo") != null) {
    			criteria.add(Restrictions.eqOrIsNull("accNo",  entity.get("accNo")));
    		}
        
          if (entity.get("challanHeading") != null) {
  			criteria.add(Restrictions.eqOrIsNull("challanHeading",  entity.get("challanHeading")));
  		}
          if (entity.get("errorDescription") != null) {
    			criteria.add(Restrictions.eqOrIsNull("errorDescription",  entity.get("errorDescription")));
    		}
          if (entity.get("warningDescription") != null) {
    			criteria.add(Restrictions.eqOrIsNull("warningDescription",  entity.get("warningDescription")));
    		}
          
          if (entity.get("resolved") != null) {
  			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
  		}
          
          
          
          
		criteria.addOrder(Order.desc("dateofReceivedDebited"));
		return (List<Regular27EQDeductee>) criteria.list();
	
	
	}
}
