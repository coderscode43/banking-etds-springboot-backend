package domain.in.rjsa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
public abstract class AbstractDaoForm<K extends Serializable, E> implements DaoInterfaceForm<K, E> {

	private final Class<E> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDaoForm() {
		this.persistentClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	@Qualifier("sessionFactory")
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
	public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		if(constrains.containsKey("branchId")) {
//			if(constrains.get("branchId") instanceof ArrayList)
//			criteria.add(Restrictions.in("branchId", (List<Long>)constrains.remove("branchId")));
//		}
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<E>) criteria.list();

	}

	public Long findallCount(HashMap<String, Object> constrains) {
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
		Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);
		
		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("date",
//					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(
//					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}
//          if(entity.get("vendorName")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("vendorName", entity.get("vendorName")));
//          }
//		criteria.addOrder(Order.desc("date"));
		return (List<E>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> searchIn(HashSet set, String property) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.in(property, set));
		return (List<E>) criteria.list();
	}

	@Override
	public List<String> ajax(String name, String term) {
		Criteria criteria = createEntityCriteria();
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
	 protected CriteriaQuery<E> createEntityCriteria(HashMap<String,Object>property){
	       // return getSession().createCriteria(persistentClass);
	        CriteriaBuilder cb =  getSession().getCriteriaBuilder();
	        CriteriaQuery<E> cr = cb.createQuery(persistentClass);
	        Root<E> root = cr.from(persistentClass);
	        List<Predicate> predicates = new ArrayList<Predicate>();
	        
	        for(String prop : property.keySet()) {
	        	predicates.add(cb.equal(root.get(prop),property.get(prop)));
	        }
	        cr.select(root).where(predicates.toArray(new Predicate[]{}));
	        return cr;
	    }

}
