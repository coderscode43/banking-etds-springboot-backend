package domain.in.rjsa.service;

import domain.in.rjsa.model.form.UserDetails;

public interface UserDetailsService extends ServiceInterface<Long, UserDetails>{
	 public UserDetails getByKey(Long id);

}
