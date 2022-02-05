package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.PensionDao;
import domain.in.rjsa.model.form.Pension;
@Repository("pensionDao")
public class PensionDaoImpl extends AbstractDaoForm<Long, Pension> implements PensionDao{
	@Override
	public List<Pension> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("pensionId") != null) {
			criteria.add(Restrictions.eqOrIsNull("pensionId", entity.get("pensionId")));
		}
		if (entity.get("pensionName") != null) {
			criteria.add(Restrictions.eqOrIsNull("pensionName", entity.get("pensionName")));
		}
		if (entity.get("address") != null) {
			criteria.add(Restrictions.eqOrIsNull("address", entity.get("address")));
		}
		
		if (entity.get("status") != null) {
			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
		}
		if (entity.get("pensionCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("pensionCode", entity.get("pensionCode")));
		}
		
		
		
		return (List<Pension>) criteria.list();
	}

}