//package domain.in.rjsa.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoForm;
//import domain.in.rjsa.dao.UserDetailsDao;
//import domain.in.rjsa.model.form.UserDetails;
//
//@Repository("UserDetailsDao")
//public class UserDetailsDaoImpl extends AbstractDaoForm<String, UserDetails> implements UserDetailsDao {
//
//	@Override
//	public UserDetails getByKey(String key) {
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("employeeId", key);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.allEq(propertyNameValues));
//
//		return (UserDetails) crit.uniqueResult();
//	}
//
//	@Override
//	public List<UserDetails> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		
//		if(entity.get("employeeId")!=null)
//        {
//		criteria.add(Restrictions.eqOrIsNull("employeeId", entity.get("employeeId")));
//        }
//        if(entity.get("typeOfUser")!=null)
//        {
//		criteria.add(Restrictions.eqOrIsNull("typeOfUser", entity.get("typeOfUser")));
//        }
//		// TODO Auto-generated method stub
//        criteria.addOrder(Order.desc("employeeId"));
//		return (List<UserDetails>) criteria.list();
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.UserDetailsDao;
import domain.in.rjsa.model.form.UserDetails;

@Repository("UserDetailsDao")
public class UserDetailsDaoImpl extends AbstractDaoForm<String, UserDetails> implements UserDetailsDao {

    @Override
    public UserDetails getByKey(String key) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserDetails> cq = cb.createQuery(UserDetails.class);
        Root<UserDetails> root = cq.from(UserDetails.class);

        cq.where(cb.equal(root.get("employeeId"), key));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<UserDetails> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserDetails> cq = cb.createQuery(UserDetails.class);
        Root<UserDetails> root = cq.from(UserDetails.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("employeeId") != null) {
            predicates.add(cb.equal(root.get("employeeId"), entity.get("employeeId")));
        }
        if (entity.get("typeOfUser") != null) {
            predicates.add(cb.equal(root.get("typeOfUser"), entity.get("typeOfUser")));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("employeeId")));

        return getEntityManager().createQuery(cq).getResultList();
    }
}

