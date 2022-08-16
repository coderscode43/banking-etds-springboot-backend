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
import domain.in.rjsa.dao.PanUpdateListDao;
import domain.in.rjsa.model.fy.PanUpdateList;

@Repository("panUpdateListDao")
public class PanUpdateListDaoImpl extends AbstractDaoFY<Long, PanUpdateList>
implements PanUpdateListDao{

	@SuppressWarnings("unchecked")
	public List<PanUpdateList> search(LinkedHashMap entity, int pageNo, int noOfResult) {
		
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		// propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));

		if (entity.get("customerVendorID") != null) {
			criteria.add(Restrictions.eqOrIsNull("customerVendorID", entity.get("customerVendorID")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("previousPAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("previousPAN", entity.get("previousPAN")));
		}
		if (entity.get("newPAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("newPAN", entity.get("newPAN")));
		}
		if (entity.get("month") != null) {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<PanUpdateList>) criteria.list();
	}
	@SuppressWarnings("unchecked")
	public Long findallCount(HashMap<String, Object> entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		
		if (entity.get("customerVendorID") != null) {
			criteria.add(Restrictions.eqOrIsNull("customerVendorID", entity.get("customerVendorID")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("previousPAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("previousPAN", entity.get("previousPAN")));
		}
		if (entity.get("newPAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("newPAN", entity.get("newPAN")));
		}
		if (entity.get("month") != null) {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}



	@Override
	public List<PanUpdateList> searchExcel(HashMap entity) {
		// TODO Auto-generated method stub
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		// propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		if (entity.get("customerVendorID") != null) {
			criteria.add(Restrictions.eqOrIsNull("customerVendorID", entity.get("customerVendorID")));
		}
		if (entity.get("challanHeading") != null) {
			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
		}
		if (entity.get("previousPAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("previousPAN", entity.get("previousPAN")));
		}
		if (entity.get("newPAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("newPAN", entity.get("newPAN")));
		}
		if (entity.get("month") != null) {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		return (List<PanUpdateList>) criteria.list();
	}


}
