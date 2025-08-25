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
//import javax.persistence.Table;
//
//import org.hibernate.Criteria;
//import org.hibernate.Query;
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
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.transaction.annotation.Transactional;
//
//import domain.in.rjsa.exception.CustomException;
//
//@Transactional("transactionManager")
//public abstract class AbstractDaoFY<K extends Serializable, E> implements DaoInterfaceFY<K, E> {
//
//	private final Class<E> persistentClass;
//
//	@SuppressWarnings("unchecked")
//	public AbstractDaoFY() {
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
//
//		return (E) crit.uniqueResult();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		if (constrains.containsKey("branchCode")) {
//			if (constrains.get("branchCode") instanceof ArrayList)
//				criteria.add(Restrictions.in("branchCode", (List<Long>) constrains.remove("branchCode")));
//		}
//		criteria.add(Restrictions.allEq(constrains));
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<E>) criteria.list();
//
//	}
//	public List<E> findForm(HashMap<String, Object> constrains, int pageNo, int noOfResult,String type) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		if (constrains.containsKey("branchCode")) {
//			if (constrains.get("branchCode") instanceof ArrayList)
//				criteria.add(Restrictions.in("branchCode", (List<Long>) constrains.remove("branchCode")));
//		}
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		
//		criteria.add(Restrictions.allEq(constrains));
//		propertyNameValues.put("deducteeForm",type);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<E>) criteria.list();
//
//	}
//	
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
//	public void delete(E entity) {
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
//
//	@SuppressWarnings("unchecked")
//	public List<E> searchIn(HashSet set, String property) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.in(property, set));
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
//	@SuppressWarnings("unchecked")
//	public E uniqueSearch(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(entity));
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
//		hql.append(" WHERE branchCode = :branchCode");
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
//	public void saveAllEntries(JSONObject jsonObject) {
//
//	}
//	
//	
//	/*
//	 * @SuppressWarnings("unchecked") public List<E> search(HashMap entity) {
//	 * Criteria criteria = createEntityCriteria();
//	 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
//	 * duplicates. Map<String, Object> propertyNameValues = new HashMap<String,
//	 * Object>(entity); criteria.add(Restrictions.allEq(propertyNameValues)); return
//	 * (List<E>) criteria.list(); }
//	 */
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
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.exception.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Transactional("transactionManager")
public abstract class AbstractDaoFY<K extends Serializable, E> implements DaoInterfaceFY<K, E> {

    private final Class<E> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDaoFY() {
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

    // ----------------------- GET BY KEY ------------------------
    public E getByKey(K key) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", key);
        List<E> list = search(map);
        return list.isEmpty() ? null : list.get(0);
    }

    // ----------------------- FIND ALL WITH PAGINATION ------------------------
    public List<E> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = buildPredicates(cb, root, constrains);
        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<E> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);
        return query.getResultList();
    }

    public List<E> findForm(HashMap<String, Object> constrains, int pageNo, int noOfResult, String type) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        Map<String, Object> copyConstrains = new HashMap<>(constrains);
        copyConstrains.put("deducteeForm", type);
        List<Predicate> predicates = buildPredicates(cb, root, copyConstrains);

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<E> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);
        return query.getResultList();
    }

    public Long findallCount(HashMap<String, Object> constrains) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = buildPredicates(cb, root, constrains);
        cq.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    public void persist(E entity) {
    	getEntityManager().persist(entity);
    }

    public void delete(E entity) {
    	getEntityManager().remove(entity);
    }

    public void deleteByKey(K key) {
        delete(getByKey(key));
    }

    public void update(E entity) {
    	getEntityManager().merge(entity);
    }

    // ----------------------- SEARCH IN ------------------------
    public List<E> searchIn(HashSet set, String property) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);
        cq.select(root).where(root.get(property).in(set));

        TypedQuery<E> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    // ----------------------- AJAX ------------------------
    public List<String> ajax(String name, String term, HashMap<String, Object> constrain) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<E> root = cq.from(persistentClass);
        cq.select(root.get(name));

        List<Predicate> predicates = buildPredicates(cb, root, constrain);
        predicates.add(cb.like(cb.upper(root.get(name)), term.toUpperCase() + "%"));
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<String> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    // ----------------------- UNIQUE SEARCH ------------------------
    public E uniqueSearch(HashMap<String, Object> entity) {
        List<E> list = search(entity);
        return list.isEmpty() ? null : list.get(0);
    }

    // ----------------------- SEARCH ------------------------
    public List<E> search(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(persistentClass);
        Root<E> root = cq.from(persistentClass);

        List<Predicate> predicates = new ArrayList<>();

        // handle AND/OR dynamic conditions
        if (entity.containsKey("and") || entity.containsKey("or")) {
            predicates.addAll(buildDynamicPredicates(cb, root, entity));
        } else {
            predicates.addAll(buildPredicates(cb, root, entity));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        // handle ordering
        if (entity.containsKey("orderBy")) {
            Map<String, Object> orderMap = (Map<String, Object>) entity.get("orderBy");
            List<Order> orders = new ArrayList<>();
            if (orderMap.containsKey("desc")) {
                for (String col : (List<String>) orderMap.get("desc")) {
                    orders.add(cb.desc(root.get(col)));
                }
            }
            cq.orderBy(orders);
        } else {
            cq.orderBy(cb.desc(root.get("id")));
        }

        TypedQuery<E> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

    // ----------------------- GET MAX VALUE ------------------------
    public Long getMaxValue(String name, Map<String, Object> propertyNameValues) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<E> root = cq.from(persistentClass);

        cq.select(cb.greatest(root.get(name).as(Long.class)));
        List<Predicate> predicates = buildPredicates(cb, root, propertyNameValues);
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Long> query = getEntityManager().createQuery(cq);
        return query.getSingleResult();
    }

    // ----------------------- CHANGE VALUE (HQL) ------------------------
    public void changeValue(Map<String, Object> propertyNameValuesToUpdate, String operator,
                            Map<String, Object> propertyNameValues) {
        StringBuilder hql = new StringBuilder("UPDATE " + persistentClass.getSimpleName() + " a SET ");
        List<String> keysToUpdate = new ArrayList<>(propertyNameValuesToUpdate.keySet());
        for (int i = 0; i < keysToUpdate.size(); i++) {
            String key = keysToUpdate.get(i);
            if (i > 0) hql.append(", ");
            hql.append("a." + key + " = a." + key + " " + operator + " " + propertyNameValuesToUpdate.get(key));
        }

        hql.append(" WHERE branchCode = :branchCode");
        for (String key : propertyNameValues.keySet()) {
            hql.append(" AND a." + key + " = :" + key);
        }

        Query query = getEntityManager().createQuery(hql.toString());
        for (Map.Entry<String, Object> entry : propertyNameValues.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.executeUpdate();
    }

    // ----------------------- SAVE ALL ENTRIES ------------------------
    public void saveAllEntries(JSONObject jsonObject) {
        // TODO: implement
    }

    // ----------------------- HELPER METHODS ------------------------

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<E> root, Map<String, Object> propertyNameValues) {
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : propertyNameValues.entrySet()) {
            predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
        }
        return predicates;
    }

    private List<Predicate> buildDynamicPredicates(CriteriaBuilder cb, Root<E> root, Map<String, Object> entity) {
        // TODO: implement AND/OR logic conversion from old Conjunction/Disjunction
        // This will replace Restrictions.conjunction()/disjunction() with Predicate arrays
        return new ArrayList<>();
    }

    // ----------------------- DATE UTILS ------------------------
    protected Date parseDate(String dateAsString) {
        if (dateAsString == null || dateAsString.isEmpty()) return null;
        try {
            String[] data = dateAsString.split(Pattern.quote(","), -1);
            String[] dayNmonth = data[0].split(Pattern.quote(" "), -1);

            String month = dayNmonth[0];
            String day = dayNmonth[1];
            String year = data[1];

            String formattedDateAsString = year + "-" + getMonthNum(month) + "-"
                    + (day.length() == 1 ? "0" + day : day);

            return new SimpleDateFormat("yyyy-MM-dd").parse(formattedDateAsString);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Invalid date format provided!");
        }
    }

    protected String getMonthNum(String month) {
    	 Map<String, String> map = Map.ofEntries(
    	    	    Map.entry("Jan", "01"),
    	    	    Map.entry("Feb", "02"),
    	    	    Map.entry("Mar", "03"),
    	    	    Map.entry("Apr", "04"),
    	    	    Map.entry("May", "05"),
    	    	    Map.entry("Jun", "06"),
    	    	    Map.entry("Jul", "07"),
    	    	    Map.entry("Aug", "08"),
    	    	    Map.entry("Sep", "09"),
    	    	    Map.entry("Oct", "10"),
    	    	    Map.entry("Nov", "11"),
    	    	    Map.entry("Dec", "12")
    	    	);
        return map.get(month);
    }
}

