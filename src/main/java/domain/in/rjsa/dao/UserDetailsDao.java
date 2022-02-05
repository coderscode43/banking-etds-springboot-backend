package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.UserDetails;

public interface UserDetailsDao  extends DaoInterfaceForm<Long, UserDetails>{

	UserDetails getByuserName(String userName);
}
