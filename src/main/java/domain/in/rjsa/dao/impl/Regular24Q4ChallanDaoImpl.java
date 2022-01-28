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
import domain.in.rjsa.dao.Regular24Q4ChallanDao;
import domain.in.rjsa.model.fy.Regular24Q4Challan;
@Repository("Regular24Q4ChallanDao")
public class Regular24Q4ChallanDaoImpl extends AbstractNewDao<Long, Regular24Q4Challan>
implements Regular24Q4ChallanDao {
	@SuppressWarnings("unchecked")
	public List< Regular24Q4Challan> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("dateOfDeposition",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("dateOfDeposition", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
		 if (entity.get("fy") != null) {
				criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
			}
	        if (entity.get("tds") != null) {
				criteria.add(Restrictions.eqOrIsNull("tds",Long.valueOf((String) entity.get("tds"))));
			}
	        if (entity.get("surcharge") != null) {
				criteria.add(Restrictions.eqOrIsNull("surcharge", Long.valueOf((String) entity.get("surcharge"))));
			}
	        if (entity.get("educationCess") != null) {
				criteria.add(Restrictions.eqOrIsNull("educationCess", Long.valueOf((String) entity.get("educationCess"))));
			}
	        if (entity.get("interest") != null) {
				criteria.add(Restrictions.eqOrIsNull("interest", Long.valueOf((String) entity.get("interest"))));
			}
	        if (entity.get("fee") != null) {
				criteria.add(Restrictions.eqOrIsNull("fee", Long.valueOf((String) entity.get("fee"))));
			}
	        if (entity.get("others") != null) {
				criteria.add(Restrictions.eqOrIsNull("others", Long.valueOf((String) entity.get("others"))));
			}
	        if (entity.get("totalTaxDeposited") != null) {
				criteria.add(Restrictions.eqOrIsNull("totalTaxDeposited", Long.valueOf((String) entity.get("totalTaxDeposited"))));
			}
	        if (entity.get("challanSrNo") != null) {
				criteria.add(Restrictions.eqOrIsNull("challanSrNo", Long.valueOf((String) entity.get("challanSrNo"))));
			}
	        
	        if (entity.get("bankBranchCode") != null) {
				criteria.add(Restrictions.eqOrIsNull("bankBranchCode", entity.get("bankBranchCode")));
			}
	        
	        if (entity.get("remarks") != null) {
				criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
			}
	        
	        if (entity.get("tdsDepositBookEntry") != null) {
				criteria.add(Restrictions.eqOrIsNull("tdsDepositBookEntry", entity.get("tdsDepositBookEntry")));
			}
	        if (entity.get("nilChallanIndicator") != null) {
				criteria.add(Restrictions.eqOrIsNull("nilChallanIndicator", entity.get("nilChallanIndicator")));
			}
	        
	       
	        if (entity.get("minorHeadChallan") != null) {
				criteria.add(Restrictions.eqOrIsNull("minorHeadChallan", entity.get("minorHeadChallan")));
			}
	        if (entity.get("interestAllocated") != null) {
				criteria.add(Restrictions.eqOrIsNull("interestAllocated", Long.valueOf((String) entity.get("interestAllocated"))));
			}
	        if (entity.get("otherAmtAllocated") != null) {
				criteria.add(Restrictions.eqOrIsNull("otherAmtAllocated", Long.valueOf((String) entity.get("otherAmtAllocated"))));
			}
	        if (entity.get("amountConsumed") != null) {
				criteria.add(Restrictions.eqOrIsNull("amountConsumed",Long.valueOf((String) entity.get("amountConsumed"))));
			}
	        if (entity.get("amountAvailable") != null) {
				criteria.add(Restrictions.eqOrIsNull("amountAvailable", Long.valueOf((String) entity.get("amountAvailable"))));
			}
          
		criteria.addOrder(Order.desc("dateOfDeposition"));
		
		return (List< Regular24Q4Challan>) criteria.list();
	}
}
