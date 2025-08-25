//package domain.in.rjsa.dao;
//
//import java.io.Serializable;
//import java.lang.reflect.Field;
//import java.lang.reflect.ParameterizedType;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
//import javax.persistence.Query;
//import javax.persistence.Table;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Conjunction;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Disjunction;
//import org.hibernate.criterion.MatchMode;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.ProjectionList;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.transaction.annotation.Transactional;
//
//import domain.in.rjsa.exception.CustomException;
//
//@Transactional("transactionManager")
//public abstract class AbstractDaoTaxo<K extends Serializable, E> implements DaoInterfaceTaxo<K, E> {
//
//	private final Class<E> persistentClass;
//
//	@SuppressWarnings("unchecked")
//	public AbstractDaoTaxo() {
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
//		return (E) getSession().get(persistentClass, key);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
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
//		criteria.add(Restrictions.ilike(name, term.toUpperCase(), MatchMode.START));
//		return criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public E uniqueSearch(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(entity));
//		return (E) criteria.uniqueResult();
//	}
//
//	public void update(E entity) {
//		getSession().update(entity);
//	}
//
//	public void persist(E entity) {
////		getSession().saveOrUpdate(entity);
//		try {
//			getSession().persist(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//		getSession().flush();
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
//	protected Criteria createEntityCriteria() {
//		return getSession().createCriteria(persistentClass);
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<E> findallWithConstrain(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(constrains));
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<E>) criteria.list();
//	}
//
//	public List<String> ajax(String name, String term, HashMap<String, Object> constrain) {
//
//		Criteria criteria = createEntityCriteria();
//		criteria.add(Restrictions.allEq(constrain));
//		criteria.setProjection(Projections.property(name));
//		criteria.add(Restrictions.ilike(name, term.toUpperCase(), MatchMode.START));
//		return criteria.list();
//
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
//	@Override
//	public List<E> searchExcel(HashMap map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/*-------------------- GET data through API -------------------*/
//	@SuppressWarnings("unchecked")
//	public List<E> search(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//
//		boolean flag = false;
//		if (entity.keySet().contains("and") || entity.keySet().contains("or")) {
//			checkConditionsMap(criteria, entity);
//			flag = true;
//		}
//		if (entity.keySet().contains("distinct")) {
//			ProjectionList projectionList = Projections.projectionList();
//
//			for (String colName : (List<String>) entity.get("distinct")) {
//				projectionList.add(Projections.property(colName));
//			}
//			criteria.setProjection(Projections.distinct(projectionList));
//			flag = true;
//		}
//		if (entity.containsKey("orderBy")) {
//			HashMap<String, Object> orderByMap = (HashMap<String, Object>) entity.get("orderBy");
//
//			if (orderByMap.containsKey("desc")) {
//				for (String colName : (List<String>) orderByMap.get("desc")) {
//					criteria.addOrder(Order.desc(colName));
//				}
//			}
//			flag = true;
//		}
//
//		if (!flag) {
//			Map<String, Object> propertyNameValues = new HashMap<String, Object>(entity);
//			criteria.add(Restrictions.allEq(propertyNameValues));
//		}
//		return (List<E>) criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	private void checkConditionsMap(Criteria criteria, HashMap<String, Object> dataMap) {
//		Conjunction andConjunction = Restrictions.conjunction();
//		if (dataMap.containsKey("and")) {
//			HashMap<String, Object> andConditions = (HashMap<String, Object>) dataMap.get("and");
//
//			for (String operator : andConditions.keySet()) {
//				List<HashMap<String, Object>> dataList = (List<HashMap<String, Object>>) andConditions.get(operator);
//
//				for (HashMap<String, Object> data : dataList) {
//					applyAndCondition(andConjunction, operator, data);
//				}
//			}
//		}
//
//		Disjunction orDisjunction = Restrictions.disjunction();
//		if (dataMap.containsKey("or")) {
//			HashMap<String, Object> orConditions = (HashMap<String, Object>) dataMap.get("or");
//
//			for (String operator : orConditions.keySet()) {
//				List<HashMap<String, Object>> dataList = (List<HashMap<String, Object>>) orConditions.get(operator);
//
//				for (HashMap<String, Object> data : dataList) {
//					applyOrCondition(orDisjunction, operator, data);
//				}
//			}
//		}
//
//		Criterion finalCriterion = Restrictions.and(andConjunction, orDisjunction);
//		criteria.add(finalCriterion);
//	}
//
//	private void applyAndCondition(Conjunction andConjunction, String operator, HashMap<String, Object> data) {
//		List<Field> fields = Arrays.asList(persistentClass.getDeclaredFields());
//
//		for (Map.Entry<String, Object> entry : data.entrySet()) {
//			String key = entry.getKey();
//			String stringValue = entry.getValue().toString();
//
//			Field field = fieldByName(fields, key);
//			if (field != null) {
//				Class<?> fieldType = field.getType();
//				Object value = convertValue(stringValue, fieldType);
//
//				switch (operator.toLowerCase()) {
//				case "eq":
//					andConjunction.add(Restrictions.eq(key, value));
//					break;
//				case "like":
//					andConjunction.add(Restrictions.like(key, value.toString(), MatchMode.ANYWHERE));
//					break;
//				case "in":
//					List<String> list = formatString(value.toString());
//					andConjunction.add(Restrictions.in(key, list));
//					break;
//				default:
//					throw new IllegalArgumentException("Unsupported operator: " + operator);
//				}
//			}
//		}
//	}
//
//	private void applyOrCondition(Disjunction orDisjunction, String operator, HashMap<String, Object> data) {
//		List<Field> fields = Arrays.asList(persistentClass.getDeclaredFields());
//
//		for (Map.Entry<String, Object> entry : data.entrySet()) {
//			String key = entry.getKey();
//			String stringValue = entry.getValue().toString();
//
//			Field field = fieldByName(fields, key);
//			if (field != null) {
//				Class<?> fieldType = field.getType();
//				Object value = convertValue(stringValue, fieldType);
//
//				switch (operator.toLowerCase()) {
//				case "eq":
//					orDisjunction.add(Restrictions.eq(key, value));
//					break;
//				case "like":
//					orDisjunction.add(Restrictions.like(key, value.toString(), MatchMode.ANYWHERE));
//					break;
//				case "in":
//					List<String> list = formatString(stringValue);
//					orDisjunction.add(Restrictions.in(key, list));
//					break;
//				default:
//					throw new IllegalArgumentException("Unsupported operator: " + operator);
//				}
//			}
//		}
//
//	}
//
//	private Field fieldByName(List<Field> fields, String key) {
//		Field field = fields.stream().filter(f -> f.getName().equals(key)).findFirst().get();
//		return field;
//	}
//
//	private Object convertValue(String stringValue, Class<?> fieldType) {
//		if (fieldType == String.class) {
//			return stringValue;
//		}
//		if (fieldType == Date.class) {
//			return parseDate(stringValue);
//		}
//		return null;
//	}
//
//	private Date parseDate(String dateAsString) {
//		Date date = null;
//
//		try {
//			if (!(dateAsString.equals(""))) {
////			"Sep 2, 2024, 12:00:00 AM"
//				String[] data = dateAsString.split(Pattern.quote(","), -1);
//				String[] dayNmonth = data[0].split(Pattern.quote(" "), -1);
//
//				String month = dayNmonth[0];
//				String day = dayNmonth[1];
//				String year = data[1];
//
//				String formatttedDateAsString = year + "-" + getMonthNum(month) + "-"
//						+ (day.length() == 1 ? "0" + day : day);
//
//				return new SimpleDateFormat("yyyy-MM-dd").parse(formatttedDateAsString);
//			} else {
//				return date;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new CustomException("Invalid date format provided!");
//		}
//	}
//
//	private String getMonthNum(String month) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("Jan", "01");
//		map.put("Feb", "02");
//		map.put("Mar", "03");
//		map.put("Apr", "04");
//		map.put("May", "05");
//		map.put("Jun", "06");
//		map.put("Jul", "07");
//		map.put("Aug", "08");
//		map.put("Sep", "09");
//		map.put("Oct", "10");
//		map.put("Nov", "11");
//		map.put("Dec", "12");
//
//		return map.get(month);
//	}
//
////	public Date parseDate(String dateAsString) {
////		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy, hh:mm:ss a", Locale.ENGLISH);
////		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
////		String formattedDate = "";
////
////		try {
////			LocalDateTime dateTime = LocalDateTime.parse(dateAsString.trim(), inputFormatter);
////			formattedDate = dateTime.format(outputFormatter);
////			return new SimpleDateFormat("yyyy-MM-dd").parse(formattedDate);
////		} catch (Exception e) {
////			e.printStackTrace();
////			throw new CustomException("Invalid date format provided!");
////		}
////
////	}
//
//	private List<String> formatString(String value) {
//		value = value.replace("(", "").replace(")", "");
//		String[] arr = value.split(Pattern.quote(","), -1);
//		return Arrays.asList(arr);
//	}
//
//}

package domain.in.rjsa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.exception.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Transactional("transactionManager")
public abstract class AbstractDaoTaxo<K extends Serializable, E> implements DaoInterfaceTaxo<K, E> {

    protected final Class<E> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDaoTaxo() {
        this.persistentClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    @PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected CriteriaBuilder createEntityCriteria() {
		return entityManager.getCriteriaBuilder();
	}
    
    @Override
    public E getByKey(K key) {
        return entityManager.find(persistentClass, key);
    }

    @Override
    public void persist(E entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public void deleteByKey(K key) {
        E entity = getByKey(key);
        if (entity != null) {
            delete(entity);
        }
    }

    @Override
    public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = new ArrayList<>();
        constrains.forEach((k, v) -> predicates.add(cb.equal(root.get(k), v)));

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        return entityManager.createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> constrains) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = new ArrayList<>();
        constrains.forEach((k, v) -> predicates.add(cb.equal(root.get(k), v)));

        cq.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<E> searchIn(HashSet<?> set, String property) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        cq.select(root).where(root.get(property).in(set));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<String> ajax(String name, String term) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<E> root = cq.from(persistentClass);

        cq.select(root.get(name)).where(cb.like(cb.upper(root.get(name)), term.toUpperCase() + "%"));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<String> ajax(String name, String term, HashMap<String, Object> constrain) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = new ArrayList<>();
        constrain.forEach((k, v) -> predicates.add(cb.equal(root.get(k), v)));
        predicates.add(cb.like(cb.upper(root.get(name)), term.toUpperCase() + "%"));

        cq.select(root.get(name)).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public E uniqueSearch(HashMap<String, Object> entity) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = new ArrayList<>();
        entity.forEach((k, v) -> predicates.add(cb.equal(root.get(k), v)));

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public Long getMaxValue(String name, Map<String, Object> propertyNameValues) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = new ArrayList<>();
        propertyNameValues.forEach((k, v) -> predicates.add(cb.equal(root.get(k), v)));

        cq.select(cb.max(root.get(name))).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public void changeValue(Map<String, Object> propertyNameValuesToUpdate, String operator,
                            Map<String, Object> propertyNameValues) {

        Table table = persistentClass.getAnnotation(Table.class);
        StringBuilder jpql = new StringBuilder("UPDATE " + persistentClass.getSimpleName() + " a SET ");
        int i = 0;
        for (String key : propertyNameValuesToUpdate.keySet()) {
            if (i > 0) jpql.append(", ");
            jpql.append("a." + key + " = a." + key + " " + operator + " :" + key + "Val");
            i++;
        }
        jpql.append(" WHERE 1=1 ");
        for (String key : propertyNameValues.keySet()) {
            jpql.append(" AND a." + key + " = :" + key);
        }

        Query query = entityManager.createQuery(jpql.toString());

        propertyNameValuesToUpdate.forEach((k, v) -> query.setParameter(k + "Val", v));
        propertyNameValues.forEach(query::setParameter);

        query.executeUpdate();
    }

    @Override
    public List<E> search(HashMap<String, Object> entity) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = new ArrayList<>();

        // simple eq filtering
        entity.forEach((k, v) -> {
            if (!(k.equals("and") || k.equals("or") || k.equals("distinct") || k.equals("orderBy"))) {
                predicates.add(cb.equal(root.get(k), v));
            }
        });

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        // orderBy support
        if (entity.containsKey("orderBy")) {
            HashMap<String, Object> orderByMap = (HashMap<String, Object>) entity.get("orderBy");
            if (orderByMap.containsKey("desc")) {
                for (String colName : (List<String>) orderByMap.get("desc")) {
                    cq.orderBy(cb.desc(root.get(colName)));
                }
            }
        }

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<E> findallWithConstrain(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
        return findall(constrains, pageNo, noOfResult);
    }

    @Override
    public List<E> searchExcel(HashMap map) {
        return search(map);
    }

    /* ---------- Utility (date parsing) ---------- */
    private Date parseDate(String dateAsString) {
        try {
            if (!(dateAsString.equals(""))) {
                String[] data = dateAsString.split(Pattern.quote(","), -1);
                String[] dayNmonth = data[0].split(Pattern.quote(" "), -1);

                String month = dayNmonth[0];
                String day = dayNmonth[1];
                String year = data[1];

                String formatttedDateAsString = year + "-" + getMonthNum(month) + "-"
                        + (day.length() == 1 ? "0" + day : day);

                return new SimpleDateFormat("yyyy-MM-dd").parse(formatttedDateAsString);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new CustomException("Invalid date format provided!");
        }
    }

    private String getMonthNum(String month) {
        Map<String, String> map = new HashMap<>();
        map.put("Jan", "01");
        map.put("Feb", "02");
        map.put("Mar", "03");
        map.put("Apr", "04");
        map.put("May", "05");
        map.put("Jun", "06");
        map.put("Jul", "07");
        map.put("Aug", "08");
        map.put("Sep", "09");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");
        return map.get(month);
    }
}

