//package domain.in.rjsa.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaQuery;
//
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoForm;
//import domain.in.rjsa.dao.LoginDao;
//import domain.in.rjsa.model.form.Login;
//@Repository("loginDao")
//public class LoginDaoImpl extends AbstractDaoForm<Long, Login> implements LoginDao {
//
//	@Override
//	public Login getByuserName(String userName) {
//		HashMap<String,Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("userName", userName);
//		CriteriaQuery<Login> criteria = createEntityCriteria(propertyNameValues);
//    	criteria.distinct(true);
//		Query query = getSession().createQuery(criteria);
//		return  (Login) query.getSingleResult();
//	}
//
//	@Override
//	public List<Login> findall() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Login> searchExcel(HashMap map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Login getByAuth(String auth) {
//		HashMap<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("authtoken", auth);
//		CriteriaQuery<Login> criteria = createEntityCriteria(propertyNameValues);
//		criteria.distinct(true);
//		Query query = getSession().createQuery(criteria);
//		return (Login) query.getSingleResult();
//	}
//}

package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.LoginDao;
import domain.in.rjsa.model.form.Login;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("loginDao")
public class LoginDaoImpl extends AbstractDaoForm<Long, Login> implements LoginDao {

    @Override
    public Login getByuserName(String userName) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Login> cq = cb.createQuery(Login.class);
        Root<Login> root = cq.from(Login.class);

        cq.select(root).where(cb.equal(root.get("userName"), userName));
        cq.distinct(true);

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Login> findall() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Login> cq = cb.createQuery(Login.class);
        Root<Login> root = cq.from(Login.class);
        cq.select(root);
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<Login> searchExcel(HashMap<String, Object> map) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Login> cq = cb.createQuery(Login.class);
        Root<Login> root = cq.from(Login.class);

        List<Predicate> predicates = map.entrySet().stream()
            .map(e -> cb.equal(root.get(e.getKey()), e.getValue()))
            .toList();

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public Login getByAuth(String auth) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Login> cq = cb.createQuery(Login.class);
        Root<Login> root = cq.from(Login.class);

        cq.select(root).where(cb.equal(root.get("authtoken"), auth));
        cq.distinct(true);

        return getEntityManager().createQuery(cq).getSingleResult();
    }
}

