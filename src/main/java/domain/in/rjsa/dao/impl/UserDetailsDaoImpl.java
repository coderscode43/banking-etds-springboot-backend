package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.model.form.UserDetails;
@Repository("UserDetailsDao")
public class UserDetailsDaoImpl extends AbstractDaoForm<String, UserDetails> implements UserDetailsDao{

	
	public UserDetails getByKey(String key) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("employeeId", key);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.allEq(propertyNameValues));

		return (UserDetails) crit.uniqueResult();
	}

	
	
	
}

