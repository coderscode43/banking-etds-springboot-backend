package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.RODetailsDao;
import domain.in.rjsa.model.form.RODetails;

@Repository("RODetailsDao")
public class RODetailsDaoImpl extends AbstractDaoForm<Long, RODetails> implements RODetailsDao{
	@Override
	public List<RODetails> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode")));
		}
		if (entity.get("roName") != null) {
			criteria.add(Restrictions.eqOrIsNull("roName", entity.get("roName")));
		}
		if (entity.get("roAddress") != null) {
			criteria.add(Restrictions.eqOrIsNull("roAddress", entity.get("roAddress")));
		}
		if (entity.get("roEmail") != null) {
			criteria.add(Restrictions.eqOrIsNull("roEmail", entity.get("roEmail")));
		}
		if (entity.get("roState") != null) {
			criteria.add(Restrictions.eqOrIsNull("roState", entity.get("roState")));
		}
		if (entity.get("roPincode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roPincode", entity.get("roPincode")));
		}
		return (List<RODetails>) criteria.list();
}
}