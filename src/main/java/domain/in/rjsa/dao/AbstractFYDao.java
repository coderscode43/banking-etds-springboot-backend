package domain.in.rjsa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
@Transactional("transactionManager")
public abstract class AbstractFYDao <PK extends Serializable, T>{
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractFYDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	@SuppressWarnings("unchecked")
	public List<T> findall() {
		CriteriaQuery<T> criteria = createEntityCriteria(new HashMap<String, Object>());
		criteria.distinct(true);
		Query query = getSession().createQuery(criteria);
		return query.getResultList();
	}

	public void persist(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	

	protected CriteriaQuery<T> createEntityCriteria(HashMap<String, Object> property) {
		// return getSession().createCriteria(persistentClass);
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery(persistentClass);
		Root<T> root = cr.from(persistentClass);
		List<Predicate> predicates = new ArrayList<Predicate>();

		for (String prop : property.keySet()) {
			predicates.add(cb.equal(root.get(prop), property.get(prop)));
		}
		cr.select(root).where(predicates.toArray(new Predicate[] {}));
		return cr;
	}

	public void deleteByKey(PK key) {
		getSession().delete(getByKey(key));
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
	    Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);		
		criteria.addOrder(Order.desc("id"));
		criteria.add(Restrictions.allEq(propertyNameValues));
		return (List<T>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public T uniqueSearch(HashMap entity) {
		CriteriaQuery criteria = createEntityCriteria(entity);
		criteria.distinct(true);
		Query query = getSession().createQuery(criteria);
		List<T> list = query.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<T>) criteria.list();
	}

	public Long findallCount(HashMap<String, Object> constrains) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(constrains));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

	}

	public List<String> ajax(String name, String term, HashMap<String, Object> constrain) {

		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.allEq(constrain));
		criteria.setProjection(Projections.property(name));
		criteria.add(Restrictions.ilike(name, term.toUpperCase(), MatchMode.START));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<T> searchIn(HashSet set, String property) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.in(property, set));
		return (List<T>) criteria.list();
	}

	public Long getMaxValue(String name, Map<String, Object> propertyNameValues) {
		Criteria criteria = createEntityCriteria();
		criteria.setProjection(Projections.max(name));
		criteria.add(Restrictions.allEq(propertyNameValues));
		return (long) criteria.uniqueResult();
	}

	public void changeValue(Map<String, Object> propertyNameValuesToUpdate, String operator,
			Map<String, Object> propertyNameValues) {
		Table table = persistentClass.getAnnotation(Table.class);
		String name = table.name();
		StringBuilder hql = new StringBuilder("UPDATE " + persistentClass.getSimpleName() + " a SET ");
		List<String> listKeyToUpdate = new ArrayList<String>(propertyNameValuesToUpdate.keySet());
		int i = 0;
		for (String key : listKeyToUpdate) {
			if (i > 0) {
				hql.append(',');
			}
			hql.append("a." + key + " = a." + key + " " + operator + " " + propertyNameValuesToUpdate.get(key));
			i++;
		}

		hql.append(" WHERE clientId = :clientId");
		List<String> listKey = new ArrayList<String>(propertyNameValues.keySet());
		for (String key : listKey) {
			hql.append(" and a." + key + " = :" + key);
		}
		Query query = getSession().createQuery(hql.toString());
		for (String key : listKey) {
			query.setParameter(key, propertyNameValues.get(key));
		}
		query.executeUpdate();
	}

}
