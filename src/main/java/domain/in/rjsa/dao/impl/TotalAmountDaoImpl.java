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
import domain.in.rjsa.dao.TotalAmountDao;
import domain.in.rjsa.model.fy.TotalAmount;

@Repository("totalAmountDao")
public class TotalAmountDaoImpl extends AbstractDaoFY<Long, TotalAmount> implements TotalAmountDao{
	
	@SuppressWarnings("unchecked")
	public List<TotalAmount> search(LinkedHashMap entity, int pageNo, int noOfResult) {
		
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		// propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));

		if (entity.get("customerID") != null) {
			criteria.add(Restrictions.eqOrIsNull("customerID", entity.get("customerID")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}

		if (entity.get("section") != null) {
			criteria.add(Restrictions.eqOrIsNull("section", entity.get("section")));
		}
		if (entity.get("month") != null) {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("totalAmountPaidRaw") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidRaw", Double.parseDouble(entity.get("totalAmountPaidRaw").toString())));
		}
		if (entity.get("totalAmountPaidUpload") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidUpload", Double.parseDouble(entity.get("totalAmountPaidUpload").toString())));
		}
		if (entity.get("totalTaxRaw") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxRaw", Double.parseDouble(entity.get("totalTaxRaw").toString())));
		}
		if (entity.get("totalTaxUploaded") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxUploaded", Double.parseDouble(entity.get("totalTaxUploaded").toString())));
		}
		if (entity.get("remark") != null) {
			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
		}
		if (entity.get("source") != null) {
			criteria.add(Restrictions.eqOrIsNull("source", entity.get("source")));
		}
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<TotalAmount>) criteria.list();
	}
	@SuppressWarnings("unchecked")
	public Long findallCount(HashMap<String, Object> entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		
		if (entity.get("customerID") != null) {
			criteria.add(Restrictions.eqOrIsNull("customerID", entity.get("customerID")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}

		if (entity.get("section") != null) {
			criteria.add(Restrictions.eqOrIsNull("section", entity.get("section")));
		}
		if (entity.get("month") != null) {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("totalAmountPaidRaw") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidRaw", Double.parseDouble(entity.get("totalAmountPaidRaw").toString())));
		}
		if (entity.get("totalAmountPaidUpload") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidUpload", Double.parseDouble(entity.get("totalAmountPaidUpload").toString())));
		}
		if (entity.get("totalTaxRaw") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxRaw", Double.parseDouble(entity.get("totalTaxRaw").toString())));
		}
		if (entity.get("totalTaxUploaded") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxUploaded", Double.parseDouble(entity.get("totalTaxUploaded").toString())));
		}
		if (entity.get("remark") != null) {
			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
		}
		if (entity.get("source") != null) {
			criteria.add(Restrictions.eqOrIsNull("source", entity.get("source")));
		}
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}



	@Override
	public List<TotalAmount> searchExcel(HashMap entity) {
		// TODO Auto-generated method stub
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		// propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		if (entity.get("customerID") != null) {
			criteria.add(Restrictions.eqOrIsNull("customerID", entity.get("customerID")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}

		if (entity.get("section") != null) {
			criteria.add(Restrictions.eqOrIsNull("section", entity.get("section")));
		}
		if (entity.get("month") != null) {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("totalAmountPaidRaw") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidRaw", Double.parseDouble(entity.get("totalAmountPaidRaw").toString())));
		}
		if (entity.get("totalAmountPaidUpload") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalAmountPaidUpload", Double.parseDouble(entity.get("totalAmountPaidUpload").toString())));
		}
		if (entity.get("totalTaxRaw") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxRaw", Double.parseDouble(entity.get("totalTaxRaw").toString())));
		}
		if (entity.get("totalTaxUploaded") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalTaxUploaded", Double.parseDouble(entity.get("totalTaxUploaded").toString())));
		}
		if (entity.get("remark") != null) {
			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
		}
		if (entity.get("source") != null) {
			criteria.add(Restrictions.eqOrIsNull("source", entity.get("source")));
		}

		return (List<TotalAmount>) criteria.list();
	}




}