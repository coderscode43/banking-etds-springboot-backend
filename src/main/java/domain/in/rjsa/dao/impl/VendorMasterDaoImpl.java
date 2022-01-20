package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.VendorMasterDao;
import domain.in.rjsa.model.form.VendorMaster;
@Repository("vendorMasterDao")
public class VendorMasterDaoImpl extends AbstractNewDao<Long, VendorMaster> implements VendorMasterDao{
	@Override
	public List<VendorMaster> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("vendorName") != null) {
			criteria.add(Restrictions.eqOrIsNull("vendorName", entity.get("vendorName")));
		}
		if (entity.get("address") != null) {
			criteria.add(Restrictions.eqOrIsNull("address", entity.get("address")));
		}
		if (entity.get("vendorCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("vendorCode", entity.get("vendorCode")));
		}
		if (entity.get("vendorId") != null) {
			criteria.add(Restrictions.eqOrIsNull("vendorId", entity.get("vendorId")));
		}
		
		return (List<VendorMaster>) criteria.list();
	}

}
