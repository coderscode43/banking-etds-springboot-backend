
package domain.in.rjsa.dao.impl;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.Regular27QDeducteeDao;
import domain.in.rjsa.model.fy.Regular27QDeductee;

@Repository("regular27QDeducteeDao")
public class Regular27QDeducteeDaoImpl extends AbstractDaoFY<Long, Regular27QDeductee>
		implements Regular27QDeducteeDao {

	@SuppressWarnings("unchecked")
	public List<Regular27QDeductee> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		// propertyNameValues.put("clientId", clientId);
		int pageNo = 0;
		int noOfResult = 100;
		criteria.add(Restrictions.allEq(propertyNameValues));

		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("sectionCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("name") != null) {
			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
		}

		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("accNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("custVendId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
		}
		if (entity.get("errorDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("errorDescription", entity.get("errorDescription")));
		}
		if (entity.get("warningDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("warningDescription", entity.get("warningDescription")));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("TAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
		}
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode", Long.parseLong(entity.get("roCode").toString())));
		}

		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);

		criteria.addOrder(Order.desc("date"));
		return (List<Regular27QDeductee>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Long findallCount(HashMap<String, Object> entity) {

		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.

		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("sectionCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("name") != null) {
			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
		}

		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("accNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("custVendId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
		}
		if (entity.get("errorDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("errorDescription", entity.get("errorDescription")));
		}
		if (entity.get("warningDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("warningDescription", entity.get("warningDescription")));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("TAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
		}
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode", Long.parseLong(entity.get("roCode").toString())));
		}
//           criteria.setFirstResult(pageNo * noOfResult);
//    		criteria.setMaxResults(noOfResult);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Regular27QDeductee> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		// propertyNameValues.put("clientId", clientId);
//		int pageNo = 0;
//		int noOfResult = 100;
		criteria.add(Restrictions.allEq(propertyNameValues));

		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("sectionCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("name") != null) {
			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
		}

		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("accNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("custVendId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
		}
		if (entity.get("errorDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("errorDescription", entity.get("errorDescription")));
		}
		if (entity.get("warningDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("warningDescription", entity.get("warningDescription")));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("TAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
		}
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode", Long.parseLong(entity.get("roCode").toString())));
		}

//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);

		criteria.addOrder(Order.desc("date"));
		return (List<Regular27QDeductee>) criteria.list();
	}

}
