//package domain.in.rjsa.dao.impl;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Conjunction;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Disjunction;
//import org.hibernate.criterion.MatchMode;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoFY;
//import domain.in.rjsa.dao.Regular24QDeducteeDao;
//import domain.in.rjsa.model.fy.Regular24QDeductee;
//
//@Repository("regular24QDeducteeDao")
//public class Regular24QDeducteeDaoImpl extends AbstractDaoFY<Long, Regular24QDeductee>
//		implements Regular24QDeducteeDao {
//	@SuppressWarnings("unchecked")
//	public List<Regular24QDeductee> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(criteria, entity);
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		criteria.addOrder(Order.desc("id"));
//		return (List<Regular24QDeductee>) criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public Long findallCount(HashMap<String, Object> entity) {
//
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(criteria, entity);
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Regular24QDeductee> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(criteria, entity);
//		criteria.addOrder(Order.desc("id"));
//		return (List<Regular24QDeductee>) criteria.list();
//	}
//
//	@Override
//	public List<String> getPanList(String q, String fy, Long branchCode) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("quarter", q);
//		propertyNameValues.put("fy", fy);
//		propertyNameValues.put("branchCode", branchCode);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		criteria.setProjection(Projections.property("pan"));
//		return (List<String>) criteria.list();
//	}
//
//	private void check(Criteria criteria, HashMap entity) {
////		if (entity.get("name") != null && entity.get("name") != "") {
////			criteria.add(
////					Restrictions.or(Restrictions.like("name", "%" + entity.get("name").toString().toLowerCase() + "%"),
////							Restrictions.like("name", "%" + entity.get("name").toString().toUpperCase() + "%")));
////		}
//		
//		if (entity.get("name") != null) {
//		    String searchName = entity.get("name").toString().toLowerCase(); // Convert the input to lowercase
//		    criteria.add(Restrictions.or(
//		        Restrictions.sqlRestriction("LOWER({alias}.name) LIKE ?", "%" + searchName + "%", org.hibernate.type.StringType.INSTANCE)
//		    ));
//		}
//
//		
//		if (entity.get("sectionCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("sectionCode",
//					entity.get("sectionCode").toString().split(Pattern.quote("-"), -1)[0]));
//		}
//
//		if (entity.get("pan") != null && entity.get("pan") != "") {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("quarter") != null) {
//			List a = new ArrayList<>();
//			String[] q = entity.get("quarter").toString().split(Pattern.quote(", "), -1);
//			for (String quarter : q) {
//				if (!quarter.isEmpty()) {
//					a.add(quarter);
//				}
//			}
//			criteria.add(Restrictions.in("quarter", a));
//		}
//
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//		if (entity.get("accNo") != null) {
//			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
//		}
//		if (entity.get("challanHeading") != null) {
//			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
//		}
//
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//
//		if (entity.get("warningDescription") != null) {
//			criteria.add(Restrictions.eqOrIsNull("warningDescription", entity.get("warningDescription")));
//		}
//		if (entity.get("errorDescription") != null) {
//			criteria.add(Restrictions.eqOrIsNull("errorDescription", entity.get("errorDescription")));
//		}
//		if (entity.get("resolved") != null) {
//			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
//		}
//		if (entity.get("TAN") != null && entity.get("TAN") != "") {
//			String[] Tan = (entity.get("TAN").toString()).split("-");
//			criteria.add(Restrictions.eqOrIsNull("TAN", Tan[0]));
//		}
//		if (entity.get("roCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode").toString()));
//		}
//
//	}
//
//	/*
//	 * @SuppressWarnings("unchecked")
//	 * 
//	 * @Override public List<Regular24QDeductee> search(HashMap dataMap) { Criteria
//	 * criteria = createEntityCriteria();
//	 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
//	 * duplicates.
//	 * 
//	 * checkConditionsMap(criteria, dataMap); if (dataMap.containsKey("desc")) {
//	 * String colName = dataMap.get("desc").toString().toLowerCase();
//	 * criteria.addOrder(Order.desc(colName)); } return (List<Regular24QDeductee>)
//	 * criteria.list(); }
//	 */
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
//	private void applyOrCondition(Disjunction orDisjunction, String operator, HashMap<String, Object> data) {
//		for (Map.Entry<String, Object> entry : data.entrySet()) {
//			String key = entry.getKey();
//			Object value = entry.getValue();
//
//			switch (operator.toLowerCase()) {
//			case "eq":
//				if (typeCheck(key)) {
//					orDisjunction.add(Restrictions.eq(key, Double.valueOf(value.toString())));
//				} else {
//					orDisjunction.add(Restrictions.eq(key, value));
//				}
//				break;
//			case "like":
//				orDisjunction.add(Restrictions.like(key, value.toString(), MatchMode.ANYWHERE));
//				break;
//			case "in":
//				List<String> list = formatString(value.toString());
//				orDisjunction.add(Restrictions.in(key, list));
//				break;
//			default:
//				throw new IllegalArgumentException("Unsupported operator: " + operator);
//			}
//		}
//
//	}
//
//	private void applyAndCondition(Conjunction andConjunction, String operator, HashMap<String, Object> data) {
//		for (Map.Entry<String, Object> entry : data.entrySet()) {
//			String key = entry.getKey();
//			Object value = entry.getValue();
//
//			switch (operator.toLowerCase()) {
//			case "eq":
//				if (typeCheck(key)) {
//					andConjunction.add(Restrictions.eq(key, Double.valueOf(value.toString())));
//				} else {
//					andConjunction.add(Restrictions.eq(key, value));
//				}
//				break;
//			case "like":
//				andConjunction.add(Restrictions.like(key, value.toString(), MatchMode.ANYWHERE));
//				break;
//			case "in":
//				List<String> list = formatString(value.toString());
//				andConjunction.add(Restrictions.in(key, list));
//				break;
//			default:
//				throw new IllegalArgumentException("Unsupported operator: " + operator);
//			}
//		}
//	}
//
//	private List<String> formatString(String value) {
//		value = value.replace("(", "").replace(")", "");
//		String[] arr = value.split(Pattern.quote(","), -1);
//		return Arrays.asList(arr);
//	}
//
//	private boolean typeCheck(String key) {
//		List<String> keyList = List.of("amountPaid", "tds", "surcharge", "eduCess", "totalTaxDeducted",
//				"totalTaxDeposited", "shortDeduction", "interestOnShortDeduction", "interestOnLatePayment",
//				"interestOnLateDeduction");
//
//		return keyList.contains(key);
//	}
//
//}

package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.Regular24QDeducteeDao;
import domain.in.rjsa.model.fy.Regular24QDeductee;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("regular24QDeducteeDao")
public class Regular24QDeducteeDaoImpl extends AbstractDaoFY<Long, Regular24QDeductee>
        implements Regular24QDeducteeDao {

    @Override
    public List<Regular24QDeductee> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Regular24QDeductee> cq = cb.createQuery(Regular24QDeductee.class);
        Root<Regular24QDeductee> root = cq.from(Regular24QDeductee.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Regular24QDeductee> root = cq.from(Regular24QDeductee.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(cb.count(root)).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<Regular24QDeductee> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Regular24QDeductee> cq = cb.createQuery(Regular24QDeductee.class);
        Root<Regular24QDeductee> root = cq.from(Regular24QDeductee.class);

        List<Predicate> predicates = buildPredicates(cb, root, entity);

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<String> getPanList(String q, String fy, Long branchCode) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Regular24QDeductee> root = cq.from(Regular24QDeductee.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("quarter"), q));
        predicates.add(cb.equal(root.get("fy"), fy));
        predicates.add(cb.equal(root.get("branchCode"), branchCode));

        cq.select(root.get("pan")).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Regular24QDeductee> root, HashMap<String, Object> entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("name") != null) {
            String searchName = entity.get("name").toString().toLowerCase();
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + searchName + "%"));
        }

        if (entity.get("sectionCode") != null) {
            String section = entity.get("sectionCode").toString().split(Pattern.quote("-"), -1)[0];
            predicates.add(cb.equal(root.get("sectionCode"), section));
        }

        if (entity.get("pan") != null && !entity.get("pan").toString().isEmpty()) {
            predicates.add(cb.equal(root.get("pan"), entity.get("pan")));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.equal(root.get("fy"), entity.get("fy")));
        }
        if (entity.get("quarter") != null) {
            String[] qArr = entity.get("quarter").toString().split(Pattern.quote(", "), -1);
            predicates.add(root.get("quarter").in(Arrays.asList(qArr)));
        }
        if (entity.get("branchCode") != null) {
            predicates.add(cb.equal(root.get("branchCode"), entity.get("branchCode")));
        }
        if (entity.get("accNo") != null) {
            predicates.add(cb.equal(root.get("accNo"), entity.get("accNo")));
        }
        if (entity.get("challanHeading") != null) {
            predicates.add(cb.equal(root.get("challanHeading"), entity.get("challanHeading")));
        }
        if (entity.get("custVendId") != null) {
            predicates.add(cb.equal(root.get("custVendId"), entity.get("custVendId")));
        }
        if (entity.get("warningDescription") != null) {
            predicates.add(cb.equal(root.get("warningDescription"), entity.get("warningDescription")));
        }
        if (entity.get("errorDescription") != null) {
            predicates.add(cb.equal(root.get("errorDescription"), entity.get("errorDescription")));
        }
        if (entity.get("resolved") != null) {
            predicates.add(cb.equal(root.get("resolved"), Boolean.valueOf(entity.get("resolved").toString())));
        }
        if (entity.get("TAN") != null && !entity.get("TAN").toString().isEmpty()) {
            String tan = entity.get("TAN").toString().split("-")[0];
            predicates.add(cb.equal(root.get("TAN"), tan));
        }
        if (entity.get("roCode") != null) {
            predicates.add(cb.equal(root.get("roCode"), entity.get("roCode").toString()));
        }

        return predicates;
    }
}
