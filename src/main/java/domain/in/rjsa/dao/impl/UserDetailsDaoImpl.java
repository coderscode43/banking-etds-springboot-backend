package domain.in.rjsa.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.model.form.UserDetails;
@Repository("UserDetailsDao")
public class UserDetailsDaoImpl extends AbstractDaoForm<Long, UserDetails> implements UserDetailsDao{

	@Override
	public UserDetails getByuserName(String userName) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.eq("userName", userName));
		return (UserDetails) criteria.uniqueResult();
	}


	
	
}

