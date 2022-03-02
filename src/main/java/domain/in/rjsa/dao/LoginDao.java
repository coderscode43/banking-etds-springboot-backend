package domain.in.rjsa.dao;

import java.util.List;

import domain.in.rjsa.model.form.Login;

public interface LoginDao extends DaoInterfaceForm<Long, Login>{
	Login getByKey(Long id);
	Login getByuserName (String userName);
	void update(Login login);
	void persist(Login login);
	List<Login> findall();
}
