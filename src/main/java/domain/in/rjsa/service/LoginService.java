package domain.in.rjsa.service;

import java.util.HashMap;

import domain.in.rjsa.model.Login;

public interface LoginService {


	void updateLogin(Login login);
	Login getLogin(String userName);
	void persist(Login login);
	HashMap<String, Login> getUserNameLogin();
	public void updatePassword(Login login, String password);
	
	
}
