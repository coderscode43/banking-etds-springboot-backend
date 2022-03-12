package domain.in.rjsa.service;


import domain.in.rjsa.model.form.UserDetails;

public interface UserDetailsService extends ServiceInterfaceForm<Long, UserDetails>{
	 public UserDetails getByKey(Long id);

	UserDetails getUserByUserName(String userName);

	public void saveNewUser(String userName, String Password);



}
