//package domain.in.rjsa.dao;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//public abstract class AbstractDao<PK extends Serializable, T> {
//	
//	private final Class<T> persistentClass;
//    
//    @SuppressWarnings("unchecked")
//    public AbstractDao(){
//        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//    }
//     
//    @Autowired
//    @Qualifier("sessionFactory")
//    private SessionFactory sessionFactory;
// 
//    protected Session getSession(){
//        return sessionFactory.getCurrentSession();
//    }
// 
//    public T getByKey(PK key) {
//        return (T) getSession().get(persistentClass, key);
//    }
//    
//    @SuppressWarnings("unchecked")
//	public List<T> findall(){
//    	CriteriaQuery<T> criteria = createEntityCriteria(new HashMap<String, Object>());
//    	criteria.distinct(true);
//		Query query = getSession().createQuery(criteria);
//		return  query.getResultList();
//    }
// 
//    public void persist(T entity) {
//        getSession().saveOrUpdate(entity);
//    }
// 
//    public void delete(T entity) {
//        getSession().delete(entity);
//    }
//     
//    protected CriteriaQuery<T> createEntityCriteria(HashMap<String,Object>property){
//       // return getSession().createCriteria(persistentClass);
//        CriteriaBuilder cb =  getSession().getCriteriaBuilder();
//        CriteriaQuery<T> cr = cb.createQuery(persistentClass);
//        Root<T> root = cr.from(persistentClass);
//        List<Predicate> predicates = new ArrayList<Predicate>();
//        
//        for(String prop : property.keySet()) {
//        	predicates.add(cb.equal(root.get(prop),property.get(prop)));
//        }
//        cr.select(root).where(predicates.toArray(new Predicate[]{}));
//        return cr;
//    }
//
//    
//    public void deleteByKey(PK key)
//    {
//        getSession().delete(getByKey(key));
//    }
//    public void update(T entity) {
//        getSession().update(entity);
//    }
//    
//    @SuppressWarnings("unchecked")
//	public List<T> search(HashMap entity) {
//		CriteriaQuery criteria = createEntityCriteria(entity);
//		criteria.distinct(true);
//		List<Order> orders = new ArrayList<Order>();
//		orders.add( Order.desc("id"));
//		criteria.orderBy((orders));
//		Query query = getSession().createQuery(criteria);
//		return  query.getResultList();
//	}
//    
//    @SuppressWarnings("unchecked")
//	public T uniqueSearch(HashMap entity) {
//		CriteriaQuery criteria = createEntityCriteria(entity);
//		criteria.distinct(true);
//		Query query = getSession().createQuery(criteria);
//		 List<T> list = query.getResultList();
//		 if (list == null || list.isEmpty()) {
//		        return null;
//		    }
//		 return list.get(0);
//	}
//
//	
//
//}


package domain.in.rjsa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected CriteriaBuilder createEntityCriteria() {
		return entityManager.getCriteriaBuilder();
	}
	
    public T getByKey(PK key) {
        return entityManager.find(persistentClass, key);
    }

    @SuppressWarnings("unchecked")
    public List<T> findall() {
        CriteriaQuery<T> criteria = createEntityCriteria(new HashMap<>());
        criteria.distinct(true);
        Query query = getEntityManager().createQuery(criteria);
        return query.getResultList();
    }

    public void persist(T entity) {
    	getEntityManager().persist(entity);
    }

    public void delete(T entity) {
    	getEntityManager().remove(entity);
    }

    protected CriteriaQuery<T> createEntityCriteria(Map<String, Object> properties) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cr = cb.createQuery(persistentClass);
        Root<T> root = cr.from(persistentClass);

        List<Predicate> predicates = new ArrayList<>();
        for (String prop : properties.keySet()) {
            predicates.add(cb.equal(root.get(prop), properties.get(prop)));
        }

        cr.select(root).where(predicates.toArray(new Predicate[0]));
        return cr;
    }

    public void deleteByKey(PK key) {
    	getEntityManager().remove(getByKey(key));
    }

    public void update(T entity) {
    	getEntityManager().merge(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> search(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = createEntityCriteria(entity);
        Root<T> root = (Root<T>) criteria.getRoots().iterator().next();

        criteria.distinct(true);
        List<Order> orders = new ArrayList<>();
        orders.add(cb.desc(root.get("id")));  // ✅ use cb.desc instead of old Hibernate Order
        criteria.orderBy(orders);

        Query query = getEntityManager().createQuery(criteria);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public T uniqueSearch(HashMap<String, Object> entity) {
        CriteriaQuery<T> criteria = createEntityCriteria(entity);
        criteria.distinct(true);

        Query query = getEntityManager().createQuery(criteria);
        List<T> list = query.getResultList();

        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
