package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.model.form.Branch;

@Repository("branchDao")
public class BranchDaoImpl extends AbstractDaoForm<Long, Branch> implements BranchDao{

	@SuppressWarnings("unchecked")
	public List< Branch> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
	/*	if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("paymentDate",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("paymentDate", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}*/
          if(entity.get("name")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
          }
          if(entity.get("tan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("tan", entity.get("tan")));
          }
          if(entity.get("email")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("email", entity.get("email")));
          }
          if(entity.get("branchCode")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
          }
          if(entity.get("pan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
          }
          
		//criteria.addOrder(Order.desc("paymentDate"));
		return (List< Branch>) criteria.list();
	}
}

	

