package domain.in.rjsa.service;

import java.util.HashMap;

import domain.in.rjsa.model.form.Login;

public interface LoginService extends ServiceInterfaceForm<Long, Login>{


	void updateLogin(Login login);
	Login getLogin(String userName);
	void persist(Login login);
	HashMap<String, Login> getUserNameLogin();
	public void updatePassword(Login login, String password);
	
	
	
}
