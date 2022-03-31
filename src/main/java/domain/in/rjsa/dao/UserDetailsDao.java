package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.UserDetails;

public interface UserDetailsDao  extends DaoInterfaceForm<String, UserDetails>{

	public UserDetails getByKey(String key);
}
