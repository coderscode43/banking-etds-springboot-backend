package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.model.form.UserDetails;
@Repository("UserDetailsDao")
public class UserDetailsDaoImpl extends AbstractNewDao<Long, UserDetails> implements UserDetailsDao{

	@Override
	public UserDetails getByuserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}

