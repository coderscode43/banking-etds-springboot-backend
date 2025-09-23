//package domain.in.rjsa.dao;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.Table;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.MatchMode;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.transaction.annotation.Transactional;
//
//import domain.in.rjsa.model.form.AddChallan;
//
//@Transactional("transactionManager")
//public abstract class AbstractDaoForm<K extends Serializable, E> implements DaoInterfaceForm<K, E> {
//
//	private final Class<E> persistentClass;
//
//	@SuppressWarnings("unchecked")
//	public AbstractDaoForm() {
//		this.persistentClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
//				.getActualTypeArguments()[1];
//	}
//
//	@Autowired
//	@Qualifier("sessionFactory")
//	private SessionFactory sessionFactory;
//
//	protected Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}
//
//	public E getByKey(K key) {
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("id", key);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.allEq(propertyNameValues));
//		return (E) crit.uniqueResult();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
////		if(constrains.containsKey("branchId")) {
////			if(constrains.get("branchId") instanceof ArrayList)
////			criteria.add(Restrictions.in("branchId", (List<Long>)constrains.remove("branchId")));
////		}
//		criteria.add(Restrictions.allEq(constrains));
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<E>) criteria.list();
//
//	}
//
//	public Long findallCount(HashMap<String, Object> constrains) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(constrains));
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//
//	}
//
//	public void persist(E entity) {
//
//		getSession().persist(entity);
//	}
//
//	public void delete(K entity) {
//		getSession().delete(entity);
//	}
//
//	protected Criteria createEntityCriteria() {
//		return getSession().createCriteria(persistentClass);
//	}
//
//	public void deleteByKey(K key) {
//		getSession().delete(getByKey(key));
//	}
//
//	public void update(E entity) {
//		getSession().update(entity);
//	}
//	
//	@Override
//	public List<E> searchExcel(HashMap map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<E> search(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);
//
//		criteria.add(Restrictions.allEq(propertyNameValues));
////		if (entity.get("fromDate") != null) {
////			criteria.add(Restrictions.ge("date",
////					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
////		}
////		if (entity.get("toDate") != null) {
////			criteria.add(
////					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
////		}
////          if(entity.get("vendorName")!=null)
////          {
////		criteria.add(Restrictions.eqOrIsNull("vendorName", entity.get("vendorName")));
////          }
////		criteria.addOrder(Order.desc("date"));
//		criteria.addOrder(Order.desc("id"));
//		return (List<E>) criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<E> searchIn(HashSet set, String property) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.in(property, set));
//		return (List<E>) criteria.list();
//	}
//
//	@Override
//	public List<String> ajax(String name, String term) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setProjection(Projections.property(name));
//		if (name.contains("branchCode")) {
//			Long code = Long.valueOf(term);
//			criteria.add(Restrictions.ilike(name, code));
//		} else {
//			String code = term;
//			criteria.add(Restrictions.ilike(name, code.toUpperCase(), MatchMode.START));
//		}
//		return criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public E uniqueSearch(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		Map<String, Object> propertyNameValue = entity;
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(propertyNameValue));
//		return (E) criteria.uniqueResult();
//	}
//
//	public Long getMaxValue(String name, Map<String, Object> propertyNameValues) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setProjection(Projections.max(name));
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		return (long) criteria.uniqueResult();
//	}
//
//	public void changeValue(Map<String, Object> propertyNameValuesToUpdate, String operator,
//			Map<String, Object> propertyNameValues) {
//		Table table = persistentClass.getAnnotation(Table.class);
//		String name = table.name();
//		StringBuilder hql = new StringBuilder("UPDATE " + persistentClass.getSimpleName() + " a SET ");
//		List<String> listKeyToUpdate = new ArrayList<String>(propertyNameValuesToUpdate.keySet());
//		int i = 0;
//		for (String key : listKeyToUpdate) {
//			if (i > 0) {
//				hql.append(',');
//			}
//			hql.append("a." + key + " = a." + key + " " + operator + " " + propertyNameValuesToUpdate.get(key));
//			i++;
//		}
//
//		hql.append(" WHERE clientId = :clientId");
//		List<String> listKey = new ArrayList<String>(propertyNameValues.keySet());
//		for (String key : listKey) {
//			hql.append(" and a." + key + " = :" + key);
//		}
//		Query query = getSession().createQuery(hql.toString());
//		for (String key : listKey) {
//			query.setParameter(key, propertyNameValues.get(key));
//		}
//		query.executeUpdate();
//	}
//
//	protected CriteriaQuery<E> createEntityCriteria(HashMap<String, Object> property) {
//		// return getSession().createCriteria(persistentClass);
//		CriteriaBuilder cb = getSession().getCriteriaBuilder();
//		CriteriaQuery<E> cr = cb.createQuery(persistentClass);
//		Root<E> root = cr.from(persistentClass);
//		List<Predicate> predicates = new ArrayList<Predicate>();
//
//		for (String prop : property.keySet()) {
//			predicates.add(cb.equal(root.get(prop), property.get(prop)));
//		}
//		cr.select(root).where(predicates.toArray(new Predicate[] {}));
//		return cr;
//	}
//
//}


package domain.in.rjsa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Transactional("transactionManager")
public abstract class AbstractDaoForm<K extends Serializable, E> implements DaoInterfaceForm<K, E> {

    private final Class<E> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDaoForm() {
        this.persistentClass = (Class<E>) ((ParameterizedType) this.getClass()
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

    public E getByKey(K key) {
        HashMap<String, Object> propertyNameValues = new HashMap<>();
        propertyNameValues.put("id", key);

        List<E> list = search(propertyNameValues);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        // Build predicates
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : constrains.entrySet()) {
            predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id"))); // Ordering by id descending

        TypedQuery<E> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);
        return query.getResultList();
    }


    public Long findallCount(HashMap<String, Object> constrains) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<E> root = cq.from(persistentClass);
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : constrains.entrySet()) {
            predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
        }
        cq.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    public void persist(E entity) {
    	getEntityManager().persist(entity);
    }

    public void delete(K entity) {
    	getEntityManager().remove(getByKey(entity));
    }

    public void deleteByKey(K key) {
    	getEntityManager().remove(getByKey(key));
    }

    public void update(E entity) {
    	getEntityManager().merge(entity);
    }

    @SuppressWarnings("unchecked")
    public List<E> search(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        // Build predicates
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : entity.entrySet()) {
            predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id"))); // Keep old ordering

        TypedQuery<E> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }


    @SuppressWarnings("unchecked")
    public List<E> searchIn(HashSet set, String property) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        cq.select(root).where(root.get(property).in(set));

        TypedQuery<E> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    public List<E> searchExcel(HashMap<String, Object> map) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        // Build predicates
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id"))); // Keep old ordering

        TypedQuery<E> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    	
    }

    public List<String> ajax(String name, String term) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<E> root = cq.from(persistentClass);
        cq.select(root.get(name));

        if (name.contains("branchCode")) {
            cq.where(cb.equal(root.get(name), Long.valueOf(term)));
        } else {
            cq.where(cb.like(cb.upper(root.get(name)), term.toUpperCase() + "%"));
        }

        TypedQuery<String> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    public E uniqueSearch(HashMap<String, Object> entity) {
        List<E> list = search(entity);
        return list.isEmpty() ? null : list.get(0);
    }

    public Long getMaxValue(String name, Map<String, Object> propertyNameValues) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<E> root = cq.from(persistentClass);

        cq.select(cb.greatest(root.get(name).as(Long.class)));

        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : propertyNameValues.entrySet()) {
            predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Long> query = getEntityManager().createQuery(cq);
        return query.getSingleResult();
    }

    public void changeValue(Map<String, Object> propertyNameValuesToUpdate, String operator,
            Map<String, Object> propertyNameValues) {

        StringBuilder hql = new StringBuilder("UPDATE " + persistentClass.getSimpleName() + " a SET ");
        List<String> keysToUpdate = new ArrayList<>(propertyNameValuesToUpdate.keySet());
        for (int i = 0; i < keysToUpdate.size(); i++) {
            String key = keysToUpdate.get(i);
            if (i > 0) hql.append(", ");
            hql.append("a." + key + " = a." + key + " " + operator + " " + propertyNameValuesToUpdate.get(key));
        }

        hql.append(" WHERE clientId = :clientId");
        for (String key : propertyNameValues.keySet()) {
            hql.append(" AND a." + key + " = :" + key);
        }

        Query query = getEntityManager().createQuery(hql.toString());
        for (Map.Entry<String, Object> entry : propertyNameValues.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.executeUpdate();
    }

    protected CriteriaQuery<E> createEntityCriteria(Map<String, Object> property) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : property.entrySet()) {
            predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
        }
        cq.select(root).where(predicates.toArray(new Predicate[0]));
        return cq;
    }
}

