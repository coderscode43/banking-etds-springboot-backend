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
import domain.in.rjsa.dao.ChallanFormDao;
import domain.in.rjsa.model.form.ChallanForm;

@Repository("challanFormDao")
public class ChallanFormDaoImpl extends AbstractNewDao<Long, ChallanForm> implements ChallanFormDao {
	
	@SuppressWarnings("unchecked")
	public List<ChallanForm> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("dateTaxDeposit",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(Restrictions.le("dateTaxDeposit",
					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
        if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
        if (entity.get("tds") != null) {
			criteria.add(Restrictions.eqOrIsNull("tds", Long.valueOf((String) entity.get("tds"))));
		}
        if (entity.get("surcharge") != null) {
			criteria.add(Restrictions.eqOrIsNull("surcharge", Double.valueOf((String) entity.get("surcharge"))));
		}
        if (entity.get("eduCess") != null) {
			criteria.add(Restrictions.eqOrIsNull("eduCess", Double.valueOf((String) entity.get("eduCess"))));
		}
        if (entity.get("interest") != null) {
			criteria.add(Restrictions.eqOrIsNull("interest", Double.valueOf((String) entity.get("interest"))));
		}
        if (entity.get("fee") != null) {
			criteria.add(Restrictions.eqOrIsNull("fee", Double.valueOf((String) entity.get("fee"))));
		}
        if (entity.get("others") != null) {
			criteria.add(Restrictions.eqOrIsNull("others", Double.valueOf((String) entity.get("others"))));
		}
        if (entity.get("totalTaxDeposit") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxDeposit", Double.valueOf((String) entity.get("totalTaxDeposit"))));
		}
        if (entity.get("challanSrNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanSrNo", Long.valueOf((String) entity.get("challanSrNo"))));
		}
        
        if (entity.get("tdsDepositBookEntry") != null) {
			criteria.add(Restrictions.eqOrIsNull("tdsDepositBookEntry", entity.get("tdsDepositBookEntry")));
		}
        if (entity.get("nilChallanIndicator") != null) {
			criteria.add(Restrictions.eqOrIsNull("nilChallanIndicator", entity.get("nilChallanIndicator")));
		}
        
        if (entity.get("bsrCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("bsrCode", entity.get("bsrCode")));
		}
        if (entity.get("minorHeadChallan") != null) {
			criteria.add(Restrictions.eqOrIsNull("minorHeadChallan", entity.get("minorHeadChallan")));
		}
        if (entity.get("interestAllocated") != null) {
			criteria.add(Restrictions.eqOrIsNull("interestAllocated", Double.valueOf((String) entity.get("interestAllocated"))));
		}
        if (entity.get("otherAmtAllocated") != null) {
			criteria.add(Restrictions.eqOrIsNull("otherAmtAllocated", Double.valueOf((String) entity.get("otherAmtAllocated"))));
		}
        if (entity.get("amountConsumed") != null) {
			criteria.add(Restrictions.eqOrIsNull("amountConsumed", Double.valueOf((String) entity.get("amountConsumed"))));
		}
        if (entity.get("amountAvailable") != null) {
			criteria.add(Restrictions.eqOrIsNull("amountAvailable", Double.valueOf((String) entity.get("amountAvailable"))));
		}
        
        
        
        
//      if(entity.get("quarter")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
//          }

		criteria.addOrder(Order.desc("dateTaxDeposit"));
		return (List<ChallanForm>) criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ChallanForm> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.in("branchId", (List<Long>)constrains.remove("branchId")));
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<ChallanForm>) criteria.list();
	}
}
