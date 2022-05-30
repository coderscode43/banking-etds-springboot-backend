package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.EmployeeMasterDao;
import domain.in.rjsa.model.form.EmployeeMaster;
@Repository("employeeMasterDao")
public class EmployeeMasterDaoImpl extends AbstractDaoForm<Long, EmployeeMaster> implements EmployeeMasterDao{
	@Override
	public List<EmployeeMaster> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
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
		if (entity.get("maker") != null) {
			criteria.add(Restrictions.eqOrIsNull("maker", entity.get("maker")));
		}
		if (entity.get("checker") != null) {
			criteria.add(Restrictions.eqOrIsNull("checker", entity.get("checker")));
		}
		if (entity.get("status") != null) {
			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
		}
		if (entity.get("designation") != null) {
			criteria.add(Restrictions.eqOrIsNull("designation", entity.get("designation")));
		}
		
		
		
		return (List<EmployeeMaster>) criteria.list();
	}

	@Override
	public List<EmployeeMaster> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
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
		if (entity.get("maker") != null) {
			criteria.add(Restrictions.eqOrIsNull("maker", entity.get("maker")));
		}
		if (entity.get("checker") != null) {
			criteria.add(Restrictions.eqOrIsNull("checker", entity.get("checker")));
		}
		if (entity.get("status") != null) {
			criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
		}
		if (entity.get("designation") != null) {
			criteria.add(Restrictions.eqOrIsNull("designation", entity.get("designation")));
		}
		
		
		
		return (List<EmployeeMaster>) criteria.list();
	}

}
