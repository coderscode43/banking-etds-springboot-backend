package domain.in.rjsa.service;


import domain.in.rjsa.model.form.UserDetails;

public interface UserDetailsService extends ServiceInterfaceForm<String, UserDetails>{

	UserDetails getUserByUserName(String userName);

	public void saveNewUser(String userName, String Password);





}
