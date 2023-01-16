package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.model.form.UserDetails;

@Repository("UserDetailsDao")
public class UserDetailsDaoImpl extends AbstractDaoForm<String, UserDetails> implements UserDetailsDao {

	@Override
	public UserDetails getByKey(String key) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("employeeId", key);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.allEq(propertyNameValues));

		return (UserDetails) crit.uniqueResult();
	}

	@Override
	public List<UserDetails> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		
		if(entity.get("employeeId")!=null)
        {
		criteria.add(Restrictions.eqOrIsNull("employeeId", entity.get("employeeId")));
        }
        if(entity.get("typeOfUser")!=null)
        {
		criteria.add(Restrictions.eqOrIsNull("typeOfUser", entity.get("typeOfUser")));
        }
		// TODO Auto-generated method stub
        criteria.addOrder(Order.desc("employeeId"));
		return (List<UserDetails>) criteria.list();
	}

}
