package domain.in.rjsa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractNewDao<K extends Serializable, E> implements DaoInterface<K, E> {

	private final Class<E> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractNewDao() {
		this.persistentClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public E getByKey(K key) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("id", key);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.allEq(propertyNameValues));

		return (E) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<E> findall(HashMap<String,Object> constrains, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<E>) criteria.list();

	}

	public Long findallCount(HashMap<String,Object> constrains) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(constrains));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

	}

	public void persist(E entity) {

		getSession().persist(entity);
	}

	public void delete(E entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	public void deleteByKey(K key) {
		getSession().delete(getByKey(key));
	}

	public void update(E entity) {
		getSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> search(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		for (Object key : entity.keySet()) {
//			if (key.equals("fromDate")) {
//				criteria.add(Restrictions.ge("date",
//						Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//			} else if (key.equals("toDate")) {
//				criteria.add(Restrictions.le("date",
//						Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//			} else {
//				criteria.add(Restrictions.eqOrIsNull((String) key, entity.get(key)));
//			}
//		}
//		
//		criteria.addOrder(Order.desc("date"));
		Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);		
		criteria.addOrder(Order.desc("id"));
		criteria.add(Restrictions.allEq(propertyNameValues));
		return (List<E>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> searchIn(HashSet set, String property) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.in(property, set));
		return (List<E>) criteria.list();
	}

	public List<String> ajax(String name, String term, HashMap<String ,Object>constrain) {

		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.allEq(constrain));
		criteria.setProjection(Projections.property(name));
		criteria.add(Restrictions.ilike(name, term.toUpperCase(), MatchMode.START));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public E uniqueSearch(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(entity));
		return (E) criteria.uniqueResult();
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
