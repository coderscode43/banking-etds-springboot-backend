package domain.in.rjsa.dao.impl;

import java.util.HashMap;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDao;
import domain.in.rjsa.dao.LoginDao;
import domain.in.rjsa.model.form.Login;
@Repository("loginDao")
public class LoginDaoImpl extends AbstractDao<Long, Login> implements LoginDao {

	@Override
	public Login getByuserName(String userName) {
		HashMap<String,Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("userName", userName);
		CriteriaQuery<Login> criteria = createEntityCriteria(propertyNameValues);
    	criteria.distinct(true);
		Query query = getSession().createQuery(criteria);
		return  (Login) query.getSingleResult();
	}

}
