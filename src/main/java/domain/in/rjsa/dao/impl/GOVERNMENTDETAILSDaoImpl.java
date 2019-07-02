package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.GOVERNMENTDETAILSDao;
import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;

@Repository("GOVERNMENTDETAILSDao")
public class GOVERNMENTDETAILSDaoImpl extends AbstractTDSDao<String, GOVERNMENTDETAILS> implements GOVERNMENTDETAILSDao{

	@Override
	public GOVERNMENTDETAILS getByKey(String tan) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("TAN", tan);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.allEq(propertyNameValues));

		return (GOVERNMENTDETAILS) crit.uniqueResult();
	}
}
