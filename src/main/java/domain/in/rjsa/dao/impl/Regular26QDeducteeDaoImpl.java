package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.model.fy.Regular26QDeductee;

@Repository("regular26QDeducteeDao")
public class Regular26QDeducteeDaoImpl extends AbstractDaoFY<Long, Regular26QDeductee> implements Regular26QDeducteeDao {
	@SuppressWarnings("unchecked")
	public List< Regular26QDeductee> search(HashMap entity, int pageNo, int noOfResult) {
			
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("branchCode", branchCode);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		 if(entity.get("branchCode")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("branchCode",entity.get("branchCode")));
         }
		 if(entity.get("accNo")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
         }
          if(entity.get("deducteePan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteePan", entity.get("deducteePan")));
          }
          if(entity.get("sectionCode")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
          }
          
          if(entity.get("deducteeName")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteeName", entity.get("deducteeName")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
         
          if (entity.get("challanHeading") != null) {
  			criteria.add(Restrictions.eqOrIsNull("challanHeading",  entity.get("challanHeading")));
  		}
          if (entity.get("resolved") != null) {
  			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
  		}
          if (entity.get("custVendId") != null) {
  			criteria.add(Restrictions.eqOrIsNull("custVendId",  entity.get("custVendId")));
  		}
          if (entity.get("errorDescription") != null) {
    			criteria.add(Restrictions.eqOrIsNull("errorDescription",  entity.get("errorDescription")));
    		}
          
          if (entity.get("warningDescription") != null) {
  			criteria.add(Restrictions.eqOrIsNull("warningDescription",  entity.get("warningDescription")));
  		}
          if (entity.get("TAN") != null) {
   			criteria.add(Restrictions.eqOrIsNull("TAN",  entity.get("TAN")));
   		}
           if (entity.get("roCode") != null) {
   			criteria.add(Restrictions.eqOrIsNull("roCode",  Long.parseLong(entity.get("roCode").toString())));
   			
   		}
           criteria.setFirstResult(pageNo * noOfResult);
    		criteria.setMaxResults(noOfResult);
		return (List< Regular26QDeductee>) criteria.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public Long findallCount(HashMap<String,Object> entity) {
		
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		
		 if(entity.get("branchCode")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("branchCode",entity.get("branchCode")));
         }
		 if(entity.get("accNo")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
         }
          if(entity.get("deducteePan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteePan", entity.get("deducteePan")));
          }
          if(entity.get("sectionCode")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
          }
          
          if(entity.get("deducteeName")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteeName", entity.get("deducteeName")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
         
          if (entity.get("challanHeading") != null) {
  			criteria.add(Restrictions.eqOrIsNull("challanHeading",  entity.get("challanHeading")));
  		}
          if (entity.get("resolved") != null) {
  			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
  		}
          if (entity.get("custVendId") != null) {
  			criteria.add(Restrictions.eqOrIsNull("custVendId",  entity.get("custVendId")));
  		}
          if (entity.get("errorDescription") != null) {
    			criteria.add(Restrictions.eqOrIsNull("errorDescription",  entity.get("errorDescription")));
    		}
          
          if (entity.get("warningDescription") != null) {
  			criteria.add(Restrictions.eqOrIsNull("warningDescription",  entity.get("warningDescription")));
  		}
          if (entity.get("TAN") != null) {
   			criteria.add(Restrictions.eqOrIsNull("TAN",  entity.get("TAN")));
   		}
           if (entity.get("roCode") != null) {
   			criteria.add(Restrictions.eqOrIsNull("roCode", Long.parseLong(entity.get("roCode").toString())));
   			
   		}
//           criteria.setFirstResult(pageNo * noOfResult);
//    		criteria.setMaxResults(noOfResult);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	
	
	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	
	}
	
	@SuppressWarnings("unchecked")
	public List< Regular26QDeductee> searchExcel(HashMap entity) {
//			int pageNo = 0 ;
//		int noOfResult = 100;
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("branchCode", branchCode);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		 if(entity.get("branchCode")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("branchCode",entity.get("branchCode")));
         }
		 if(entity.get("accNo")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
         }
          if(entity.get("deducteePan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteePan", entity.get("deducteePan")));
          }
          if(entity.get("sectionCode")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
          }
          
          if(entity.get("deducteeName")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteeName", entity.get("deducteeName")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
         
          if (entity.get("challanHeading") != null) {
  			criteria.add(Restrictions.eqOrIsNull("challanHeading",  entity.get("challanHeading")));
  		}
          if (entity.get("resolved") != null) {
  			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
  		}
          if (entity.get("custVendId") != null) {
  			criteria.add(Restrictions.eqOrIsNull("custVendId",  entity.get("custVendId")));
  		}
          if (entity.get("errorDescription") != null) {
    			criteria.add(Restrictions.eqOrIsNull("errorDescription",  entity.get("errorDescription")));
    		}
          
          if (entity.get("warningDescription") != null) {
  			criteria.add(Restrictions.eqOrIsNull("warningDescription",  entity.get("warningDescription")));
  		}
          if (entity.get("TAN") != null) {
   			criteria.add(Restrictions.eqOrIsNull("TAN",  entity.get("TAN")));
   		}
           if (entity.get("roCode") != null) {
   			criteria.add(Restrictions.eqOrIsNull("roCode",  Long.parseLong(entity.get("roCode").toString())));
   			
   		}
//           criteria.setFirstResult(pageNo * noOfResult);
//    		criteria.setMaxResults(noOfResult);
		return (List< Regular26QDeductee>) criteria.list();
	}
	
	
}
