package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.model.form.UserDetails;
@Repository("UserDetailsDao")
public class UserDetailsDaoImpl extends AbstractDaoForm<Long, UserDetails> implements UserDetailsDao{

	@Override
	public UserDetails getByuserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}

