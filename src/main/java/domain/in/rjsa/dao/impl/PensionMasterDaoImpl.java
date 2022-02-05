package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.PensionMasterDao;
import domain.in.rjsa.model.form.PensionMaster;

@Repository("pensionMasterDao")
public class PensionMasterDaoImpl extends AbstractDaoForm<Long, PensionMaster> implements PensionMasterDao{
	@Override
	public List<PensionMaster> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("pensionerId") != null) {
			criteria.add(Restrictions.eqOrIsNull("pensionerId", entity.get("pensionerId")));
		}
		if (entity.get("pensionerName") != null) {
			criteria.add(Restrictions.eqOrIsNull("pensionerName", entity.get("pensionerName")));
		}
		if (entity.get("address") != null) {
			criteria.add(Restrictions.eqOrIsNull("address", entity.get("address")));
		}
		if (entity.get("maker") != null) {
			criteria.add(Restrictions.eqOrIsNull("maker", entity.get("maker")));
		}
		if (entity.get("checker") != null) {
			criteria.add(Restrictions.eqOrIsNull("checker", entity.get("checker")));
		}
		if (entity.get("status") != null) {
			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
		}
		if (entity.get("pensionerCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("pensionerCode", entity.get("pensionerCode")));
		}
		
		
		
		return (List<PensionMaster>) criteria.list();
	}

}
