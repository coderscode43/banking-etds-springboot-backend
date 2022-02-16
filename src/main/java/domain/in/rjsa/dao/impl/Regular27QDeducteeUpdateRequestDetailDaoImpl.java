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
import domain.in.rjsa.dao.Regular27QDeducteeUpdateRequestDetailDao;
import domain.in.rjsa.model.fy.Regular27QDeducteeUpdateRequestDetail;

@Repository("regular27QDeducteeUpdateRequestDetailDao")
public class Regular27QDeducteeUpdateRequestDetailDaoImpl
		extends AbstractDaoFY<Long, Regular27QDeducteeUpdateRequestDetail>
		implements Regular27QDeducteeUpdateRequestDetailDao {

	@SuppressWarnings("unchecked")
	public List<Regular27QDeducteeUpdateRequestDetail> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("sectionCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		 if(entity.get("name")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
         }
         
         if(entity.get("quarter")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
         }
         
         if(entity.get("natureOfRemittance")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("natureOfRemittance", entity.get("natureOfRemittance")));
         }
         
         if(entity.get("contactNoOfDeductee")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("contactNoOfDeductee", entity.get("contactNoOfDeductee")));
         }
         
         if(entity.get("emailId")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("emailId", entity.get("emailId")));
         }
         
         if(entity.get("grossingUpIndicator")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("grossingUpIndicator", entity.get("grossingUpIndicator")));
         }
         
         if(entity.get("countryOfResidence")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("countryOfResidence", entity.get("countryOfResidence")));
         }
         
         if(entity.get("deducteeCode")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("deducteeCode", entity.get("deducteeCode")));
         }
         
         if (entity.get("tdaRateAsPerItActs") != null) {
 			criteria.add(Restrictions.eqOrIsNull("tdaRateAsPerItActs", Double.valueOf((String) entity.get("tdaRateAsPerItActs"))));
 		}
         
         if (entity.get("deducteeSrNo") != null) {
   			criteria.add(Restrictions.eqOrIsNull("deducteeSrNo", Long.valueOf((String) entity.get("deducteeSrNo"))));
   		}
         
         if (entity.get("srNoInChallan") != null) {
 			criteria.add(Restrictions.eqOrIsNull("srNoInChallan", Long.valueOf((String) entity.get("srNoInChallan"))));
 		}
         
         if (entity.get("tds") != null) {
   			criteria.add(Restrictions.eqOrIsNull("tds", Double.valueOf((String) entity.get("tds"))));
   		}
         
         if (entity.get("taxIdentificationNo") != null) {
 			criteria.add(Restrictions.eqOrIsNull("taxIdentificationNo", Long.valueOf((String) entity.get("taxIdentificationNo"))));
 		}
         
         if (entity.get("surcharge") != null) {
 			criteria.add(Restrictions.eqOrIsNull("surcharge", Double.valueOf((String) entity.get("surcharge"))));
 		}
         
         if (entity.get("educationCess") != null) {
   			criteria.add(Restrictions.eqOrIsNull("educationCess", Double.valueOf((String) entity.get("educationCess"))));
   		}
          if (entity.get("totalTaxDeducted") != null) {
 			criteria.add(Restrictions.eqOrIsNull("totalTaxDeducted", Double.valueOf((String) entity.get("totalTaxDeducted"))));
 		}
         
         if (entity.get("totalTaxDeposited") != null) {
   			criteria.add(Restrictions.eqOrIsNull("totalTaxDeposited", Double.valueOf((String) entity.get("totalTaxDeposited"))));
   		}
         
         if (entity.get("rateAtWhichTaxDeducted") != null) {
 			criteria.add(Restrictions.eqOrIsNull("rateAtWhichTaxDeducted", Double.valueOf((String) entity.get("rateAtWhichTaxDeducted"))));
 		}
         
         if (entity.get("deducteeRefNo") != null) {
   			criteria.add(Restrictions.eqOrIsNull("deducteeRefNo", Long.valueOf((String) entity.get("deducteeRefNo"))));
   		}
         
         if (entity.get("deducteeRefNo") != null) {
 			criteria.add(Restrictions.eqOrIsNull("deducteeRefNo", Long.valueOf((String) entity.get("deducteeRefNo"))));
 		}
         
         if (entity.get("noOfCertificateUnderSection") != null) {
   			criteria.add(Restrictions.eqOrIsNull("noOfCertificateUnderSection", Long.valueOf((String) entity.get("noOfCertificateUnderSection"))));
   		}
         
         
         if (entity.get("uniqueAcknowledgeNo") != null) {
   			criteria.add(Restrictions.eqOrIsNull("uniqueAcknowledgeNo", Long.valueOf((String) entity.get("uniqueAcknowledgeNo"))));
   		}
         
         if(entity.get("reasonForNonDeduction")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("reasonForNonDeduction", entity.get("reasonForNonDeduction")));
         }
         if(entity.get("deducteeId")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("deducteeId",Long.valueOf((String) entity.get("deducteeId"))));
         }
         if (entity.get("challanHeading") != null) {
 			criteria.add(Restrictions.eqOrIsNull("challanHeading",  entity.get("challanHeading")));
 		}
         
		criteria.addOrder(Order.desc("date"));
		return (List<Regular27QDeducteeUpdateRequestDetail>) criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Regular27QDeducteeUpdateRequestDetail> findall(HashMap<String, Object> constrains, int pageNo,
			int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.in("branchId", (List<Long>) constrains.remove("branchId")));
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<Regular27QDeducteeUpdateRequestDetail>) criteria.list();
	}
}
