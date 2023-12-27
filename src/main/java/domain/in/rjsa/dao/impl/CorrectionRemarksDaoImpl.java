package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.CorrectionRemarksDao;
import domain.in.rjsa.model.form.CorrectionRemarks;

@Repository("correctionRemarkDao")
public class CorrectionRemarksDaoImpl extends AbstractDaoForm<Long, CorrectionRemarks> implements CorrectionRemarksDao{

	@Override
	public List<CorrectionRemarks> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(entity));
		return (List<CorrectionRemarks>) criteria.list();
	}

	public List<CorrectionRemarks> findForm(HashMap<String, Object> constrains) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		if (constrains.containsKey("correctionRequestId")) {
			if (constrains.get("correctionRequestId") instanceof ArrayList)
				criteria.add(Restrictions.in("correctionRequestId", (List<Long>) constrains.remove("correctionRequestId")));
		}
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		
		criteria.add(Restrictions.allEq(constrains));
		criteria.add(Restrictions.allEq(propertyNameValues));
		criteria.addOrder(Order.desc("id"));
		return (List<CorrectionRemarks>) criteria.list();

	}

	@Override
	public List<?> search(LinkedHashMap map, int pageNo, int resultPerPage) {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(map));
		criteria.setFirstResult(pageNo * resultPerPage);
		criteria.setMaxResults(resultPerPage);
		return (List<CorrectionRemarks>) criteria.list();
	}
	
}

	

