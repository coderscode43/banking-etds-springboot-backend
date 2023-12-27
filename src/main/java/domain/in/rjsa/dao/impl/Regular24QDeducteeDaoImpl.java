package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.model.fy.Regular24QDeductee;

@Repository("regular24QDeducteeDao")
public class Regular24QDeducteeDaoImpl extends AbstractDaoFY<Long, Regular24QDeductee>
		implements Regular24QDeducteeDao {
	@SuppressWarnings("unchecked")
	public List<Regular24QDeductee> search(HashMap entity, int pageNo, int noOfResult) {
		
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		// propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));

		if (entity.get("name") != null) {
			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
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

		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("accNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		
		  if (entity.get("custVendId") != null) {
		  criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId"))); }
		 
		if (entity.get("warningDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("warningDescription", entity.get("warningDescription")));
		}
		if (entity.get("errorDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("errorDescription", entity.get("errorDescription")));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("TAN") != null) {
			 String[] Tan = (entity.get("TAN").toString()).split("-"); 
			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
		}
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode")));
		}
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<Regular24QDeductee>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Long findallCount(HashMap<String, Object> entity) {

		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.

		if (entity.get("name") != null) {
			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
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

		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("accNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		
		  if (entity.get("custVendId") != null) {
		  criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId"))); }
		 
		if (entity.get("warningDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("warningDescription", entity.get("warningDescription")));
		}
		if (entity.get("errorDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("errorDescription", entity.get("errorDescription")));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("TAN") != null) {
			 String[] Tan = (entity.get("TAN").toString()).split("-"); 
			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
		}
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode")));
		}
//           criteria.setFirstResult(pageNo * noOfResult);
//    		criteria.setMaxResults(noOfResult);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Regular24QDeductee> searchExcel(HashMap entity) {
//		int pageNo = 0;
//		int noOfResult = 100;
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		// propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));

		if (entity.get("name") != null) {
			criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
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

		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("accNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		
		  if (entity.get("custVendId") != null) {
		  criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId"))); }
		 
		if (entity.get("warningDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("warningDescription", entity.get("warningDescription")));
		}
		if (entity.get("errorDescription") != null) {
			criteria.add(Restrictions.eqOrIsNull("errorDescription", entity.get("errorDescription")));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("TAN") != null) {
			 String[] Tan = (entity.get("TAN").toString()).split("-"); 
			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
		}
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode")));
		}
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
		return (List<Regular24QDeductee>) criteria.list();
	}

}