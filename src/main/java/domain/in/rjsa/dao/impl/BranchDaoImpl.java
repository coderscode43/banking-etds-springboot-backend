package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.model.form.Branch;

@Repository("branchDao")
public class BranchDaoImpl extends AbstractDaoForm<Long, Branch> implements BranchDao{

	/*Pranay*/
	@Override
	public List<Branch> search(HashMap entity, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode",Long.valueOf((String) entity.get("roCode").toString())));
		}
		if (entity.get("branchName") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchName", entity.get("branchName").toString()));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode",Long.valueOf((String) entity.get("branchCode").toString())));
		}
		if (entity.get("branchState") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchState", entity.get("branchState").toString()));
		}
//		if (entity.get("active") != null) {
//			criteria.add(Restrictions.eqOrIsNull("active", Boolean.valueOf(entity.get("active").toString())));
//		}
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<Branch>) criteria.list();
	}

	/* pranay */
	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode",Long.valueOf((String) entity.get("roCode").toString())));
		}
		if (entity.get("branchName") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchName", entity.get("branchName").toString()));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", Long.valueOf((String) entity.get("branchCode").toString())));
		}
		if (entity.get("branchState") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchState", entity.get("branchState").toString()));
		}
//		if (entity.get("active") != null) {
//			criteria.add(Restrictions.eqOrIsNull("active", Boolean.valueOf(entity.get("active").toString())));
//		}
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
}

	

