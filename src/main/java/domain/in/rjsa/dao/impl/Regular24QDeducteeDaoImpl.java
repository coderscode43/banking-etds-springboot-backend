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
import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.model.fy.Regular24QDeductee;

@Repository("regular24QDeducteeDao")
public class Regular24QDeducteeDaoImpl extends AbstractDaoFY<Long, Regular24QDeductee>
		implements Regular24QDeducteeDao {
	@SuppressWarnings("unchecked")
	public List<Regular24QDeductee> search(HashMap entity) {
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
		
		 if (entity.get("challanSrNo") != null) {
	  			criteria.add(Restrictions.eqOrIsNull("challanSrNo", Long.valueOf((String) entity.get("challanSrNo"))));
	  		}
		 if (entity.get("surcharge") != null) {
	  			criteria.add(Restrictions.eqOrIsNull("surcharge", Double.valueOf((String) entity.get("surcharge"))));
	  		}
		 if (entity.get("amountPaid") != null) {
	  			criteria.add(Restrictions.eqOrIsNull("amountPaid", Double.valueOf((String) entity.get("amountPaid"))));
	  		}
		 if (entity.get("educationCess") != null) {
	  			criteria.add(Restrictions.eqOrIsNull("educationCess", Double.valueOf((String) entity.get("educationCess"))));
	  		}
		 if (entity.get("tds") != null) {
	  			criteria.add(Restrictions.eqOrIsNull("tds", Double.valueOf((String) entity.get("tds"))));
	  		}
		 if (entity.get("totalTds") != null) {
	  			criteria.add(Restrictions.eqOrIsNull("totalTds", Double.valueOf((String) entity.get("totalTds"))));
	  		}
		 if (entity.get("totalTaxDeposited") != null) {
	  			criteria.add(Restrictions.eqOrIsNull("totalTaxDeposited", Double.valueOf((String) entity.get("totalTaxDeposited"))));
	  		}
		 if (entity.get("remarks") != null) {
				criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
			}
		 if (entity.get("certificateNumber") != null) {
				criteria.add(Restrictions.eqOrIsNull("certificateNumber", entity.get("certificateNumber")));
			}
		
		 if (entity.get("name") != null) {
				criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
			}
		 if (entity.get("panRefNo") != null) {
				criteria.add(Restrictions.eqOrIsNull("panRefNo", entity.get("panRefNo")));
			}
		 if (entity.get("employeeRefNo") != null) {
				criteria.add(Restrictions.eqOrIsNull("employeeRefNo", entity.get("employeeRefNo")));
			}
		 if (entity.get("sectionCode") != null) {
				criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
			}

		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
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
		criteria.addOrder(Order.desc("dateOfPayment"));
		return (List<Regular24QDeductee>) criteria.list();
	}

	/*
	 * @Override
	 * 
	 * @SuppressWarnings("unchecked") public List<Regular24QDeductee>
	 * findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
	 * Criteria criteria = createEntityCriteria();
	 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
	 * duplicates. criteria.add(Restrictions.in("branchId", (List<Long>)
	 * constrains.remove("branchId")));
	 * criteria.add(Restrictions.allEq(constrains));
	 * criteria.addOrder(Order.desc("id")); criteria.setFirstResult(pageNo *
	 * noOfResult); criteria.setMaxResults(noOfResult); return
	 * (List<Regular24QDeductee>) criteria.list();
	 * 
	 * }
	 */
}
