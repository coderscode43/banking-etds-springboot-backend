package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.LoginDao;
import domain.in.rjsa.model.form.Login;
@Repository("loginDao")
public class LoginDaoImpl extends AbstractDaoForm<Long, Login> implements LoginDao {

	@Override
	public Login getByuserName(String userName) {
		HashMap<String,Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("userName", userName);
		CriteriaQuery<Login> criteria = createEntityCriteria(propertyNameValues);
    	criteria.distinct(true);
		Query query = getSession().createQuery(criteria);
		return  (Login) query.getSingleResult();
	}

	@Override
	public List<Login> findall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Login> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
