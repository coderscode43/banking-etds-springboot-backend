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

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.model.fy.Regular26QDeductee;

@Repository("regular26QDeducteeDao")
public class Regular26QDeducteeDaoImpl extends AbstractNewDao<Long, Regular26QDeductee> implements Regular26QDeducteeDao {
	@SuppressWarnings("unchecked")
	public List< Regular26QDeductee> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("paymentDate",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("paymentDate", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
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
          if(entity.get("certificateNo")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("certificateNo", entity.get("certificateNo")));
          }
          if(entity.get("remarks")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
          
          if(entity.get("challanSrNo")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("challanSrNo", entity.get("challanSrNo")));
          }

          if(entity.get("deducteeRefNo")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteeRefNo", entity.get("deducteeRefNo")));
          }
          if (entity.get("paidAmt") != null) {
  			criteria.add(Restrictions.eqOrIsNull("paidAmt", Double.valueOf((String) entity.get("paidAmt"))));
  		}
         
          if (entity.get("surcharge") != null) {
    			criteria.add(Restrictions.eqOrIsNull("surcharge", Double.valueOf((String) entity.get("surcharge"))));
    		}
         
          
          if (entity.get("eduCess") != null) {
  			criteria.add(Restrictions.eqOrIsNull("eduCess", Double.valueOf((String) entity.get("eduCess"))));
  		}
          
          if (entity.get("totalTaxDeduct") != null) {
    			criteria.add(Restrictions.eqOrIsNull("totalTaxDeduct", Double.valueOf((String) entity.get("totalTaxDeduct"))));
    		}
          
          if (entity.get("totalTaxDeposit") != null) {
  			criteria.add(Restrictions.eqOrIsNull("totalTaxDeposit", Double.valueOf((String) entity.get("totalTaxDeposit"))));
  		}
          
          if (entity.get("rateTaxDeduct") != null) {
    			criteria.add(Restrictions.eqOrIsNull("rateTaxDeduct", Double.valueOf((String) entity.get("rateTaxDeduct"))));
    		}
          
          if (entity.get("tds") != null) {
  			criteria.add(Restrictions.eqOrIsNull("tds", Double.valueOf((String) entity.get("tds"))));
  		}
          
          
          
          
          
		criteria.addOrder(Order.desc("paymentDate"));
		
		return (List< Regular26QDeductee>) criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Regular26QDeductee> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.in("branchId", (List<Long>)constrains.remove("branchId")));
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<Regular26QDeductee>) criteria.list();
	}
}
