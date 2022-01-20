package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.EmployeeMasterDao;
import domain.in.rjsa.model.form.EmployeeMaster;
import domain.in.rjsa.model.form.PensionMaster;
@Repository("employeeMasterDao")
public class EmployeeMasterDaoImpl extends AbstractNewDao<Long, EmployeeMaster> implements EmployeeMasterDao{
	@Override
	public List<EmployeeMaster> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("employeeId") != null) {
			criteria.add(Restrictions.eqOrIsNull("employeeId", entity.get("employeeId")));
		}
		if (entity.get("employeeName") != null) {
			criteria.add(Restrictions.eqOrIsNull("employeeName", entity.get("employeeName")));
		}
		if (entity.get("employeeCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("employeeCode", entity.get("employeeCode")));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("designation") != null) {
			criteria.add(Restrictions.eqOrIsNull("designation", entity.get("designation")));
		}
		
		
		
		return (List<EmployeeMaster>) criteria.list();
	}

}
