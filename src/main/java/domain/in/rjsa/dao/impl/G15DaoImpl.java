package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.G15Dao;
import domain.in.rjsa.model.fy.G15;

@Repository("G15Dao")
public class G15DaoImpl extends AbstractDaoFY<Long, G15>
implements G15Dao{

	public List<G15> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		//propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		if (entity.get("nameofAssesseeDeclarant") != null) {
			criteria.add(Restrictions.eqOrIsNull("nameofAssesseeDeclarant", entity.get("nameofAssesseeDeclarant")));
		}
		if (entity.get("panoftheAssessee") != null) {
			criteria.add(Restrictions.eqOrIsNull("panoftheAssessee", entity.get("panoftheAssessee")));
		}
		if (entity.get("aadhaarNumber") != null) {
			criteria.add(Restrictions.eqOrIsNull("aadhaarNumber", entity.get("aadhaarNumber")));
		}
		if (entity.get("warning") != null) {
			criteria.add(Restrictions.eqOrIsNull("warning", entity.get("warning")));
		}
		if (entity.get("error") != null) {
			criteria.add(Restrictions.eqOrIsNull("error", entity.get("error")));
		}
		if (entity.get("custVendId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
		}
		if (entity.get("accNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("month") != null) {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		return (List<G15>) criteria.list();
		
		
}
}