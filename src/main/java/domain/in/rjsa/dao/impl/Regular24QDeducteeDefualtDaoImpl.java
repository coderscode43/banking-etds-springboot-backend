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
import domain.in.rjsa.dao.Regular24QDeducteeDefualtDao;
import domain.in.rjsa.model.fy.Regular24QDeducteeDefualt;

@Repository("regular24QDeducteeDefualtDao")
public class Regular24QDeducteeDefualtDaoImpl extends AbstractDaoFY<Long, Regular24QDeducteeDefualt>
		implements Regular24QDeducteeDefualtDao {
	@SuppressWarnings("unchecked")
	public List<Regular24QDeducteeDefualt> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("dateOfPayment",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(Restrictions.le("dateOfPayment",
					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}

		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		
		if (entity.get("sectionCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
		}
		
		if (entity.get("name") != null) {
			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		
		if (entity.get("employeeRefNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("employeeRefNo", entity.get("employeeRefNo")));
		}
		
		if (entity.get("certificateNumber") != null) {
			criteria.add(Restrictions.eqOrIsNull("certificateNumber", entity.get("certificateNumber")));
		}
		
		if (entity.get("panRefNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("panRefNo", entity.get("panRefNo")));
		}
		
		if (entity.get("month") != null) {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		}
		
		if (entity.get("remarks") != null) {
			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
		}
		
		if (entity.get("challanSrNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanSrNo", Double.valueOf((String) entity.get("challanSrNo"))));
		}
		
		if (entity.get("totalTds") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTds", Double.valueOf((String) entity.get("totalTds"))));
		}
		
		if (entity.get("totalTaxDeposited") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxDeposited", Double.valueOf((String) entity.get("totalTaxDeposited"))));
		}
		
		
		
		if (entity.get("challanSrNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanSrNo", Double.valueOf((String) entity.get("challanSrNo"))));
		}

		if (entity.get("surcharge") != null) {
			criteria.add(Restrictions.eqOrIsNull("surcharge", Double.valueOf((String) entity.get("surcharge"))));
		}
		
		if (entity.get("educationCess") != null) {
			criteria.add(Restrictions.eqOrIsNull("educationCess", Double.valueOf((String) entity.get("educationCess"))));
		}
		
		if (entity.get("amountPaid") != null) {
			criteria.add(Restrictions.eqOrIsNull("amountPaid", Double.valueOf((String) entity.get("amountPaid"))));
		}
		
		if (entity.get("tds") != null) {
			criteria.add(Restrictions.eqOrIsNull("tds", Double.valueOf((String) entity.get("tds"))));
		}
		
		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		 if(entity.get("type")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("type", entity.get("type")));
         }
         if(entity.get("month")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
         }
         if(entity.get("cif")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("cif", entity.get("cif")));
         }
         if (entity.get("branchCode") != null) {
 			criteria.add(Restrictions.eqOrIsNull("branchCode", Long.valueOf((String) entity.get("branchCode"))));
 		}
         if (entity.get("accNo") != null) {
   			criteria.add(Restrictions.eqOrIsNull("accNo", Long.valueOf((String) entity.get("accNo"))));
   		}
         if (entity.get("idNo") != null) {
 			criteria.add(Restrictions.eqOrIsNull("idNo", Long.valueOf((String) entity.get("idNO"))));
 		}
         if(entity.get("deducteeId")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("deducteeId",Long.valueOf((String) entity.get("deducteeId"))));
         }
         if (entity.get("challanHeading") != null) {
 			criteria.add(Restrictions.eqOrIsNull("challanHeading",  entity.get("challanHeading")));
 		}
         if (entity.get("staffId") != null) {
   			criteria.add(Restrictions.eqOrIsNull("staffId", Long.valueOf((String) entity.get("staffId"))));
   		}
          if (entity.get("uniqueRefNo") != null) {
   			criteria.add(Restrictions.eqOrIsNull("uniqueRefNo", Long.valueOf((String) entity.get("uniqueRefNo"))));
   		}
          if (entity.get("errorDescription") != null) {
   			criteria.add(Restrictions.eqOrIsNull("errorDescription",  entity.get("errorDescription")));
   		}
          if (entity.get("warningDescription") != null) {
   			criteria.add(Restrictions.eqOrIsNull("warningDescription",  entity.get("warningDescription")));
   		} 
          if (entity.get("shortDeduction") != null) {
     			criteria.add(Restrictions.eqOrIsNull("shortDeduction", Long.valueOf((String) entity.get("shortDeduction"))));
     		}
            if (entity.get("interestOnShortDeduction") != null) {
     			criteria.add(Restrictions.eqOrIsNull("interestOnShortDeduction", Long.valueOf((String) entity.get("interestOnShortDeduction"))));
     		}
            if (entity.get("interestOnLatePayment") != null) {
       			criteria.add(Restrictions.eqOrIsNull("interestOnLatePayment", Long.valueOf((String) entity.get("interestOnLatePayment"))));
       		}
              if (entity.get("interestOnLateDeduction") != null) {
       			criteria.add(Restrictions.eqOrIsNull("interestOnLateDeduction", Long.valueOf((String) entity.get("interestOnLateDeduction"))));
       		}
         

		criteria.addOrder(Order.desc("dateOfPayment"));
		return (List<Regular24QDeducteeDefualt>) criteria.list();
	}

	/*
	 * @Override
	 * 
	 * @SuppressWarnings("unchecked") public List<Regular24QDeducteeDefualt>
	 * findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
	 * Criteria criteria = createEntityCriteria();
	 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
	 * duplicates. criteria.add(Restrictions.in("branchId", (List<Long>)
	 * constrains.remove("branchId")));
	 * criteria.add(Restrictions.allEq(constrains));
	 * criteria.addOrder(Order.desc("id")); criteria.setFirstResult(pageNo *
	 * noOfResult); criteria.setMaxResults(noOfResult); return
	 * (List<Regular24QDeducteeDefualt>) criteria.list();
	 * 
	 * }
	 */
}
