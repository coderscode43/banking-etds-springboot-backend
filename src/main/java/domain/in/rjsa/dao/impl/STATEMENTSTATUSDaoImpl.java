//package domain.in.rjsa.dao.impl;
//
//import java.time.ZonedDateTime;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoTaxo;
//import domain.in.rjsa.dao.STATEMENTSTATUSDao;
//import domain.in.rjsa.model.tds.STATEMENTSTATUS;
//
//@Repository("STATEMENTSTATUSDao")
//public class STATEMENTSTATUSDaoImpl extends AbstractDaoTaxo<Long, STATEMENTSTATUS> implements STATEMENTSTATUSDao {
//	@SuppressWarnings("unchecked")
//	public List<STATEMENTSTATUS> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("AS_ON_DATE",
//					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(Restrictions.le("AS_ON_DATE",
//					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}
//		if (entity.get("TAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
//		}
//		if (entity.get("FORM") != null) {
//			criteria.add(Restrictions.eqOrIsNull("FORM", String.valueOf((String) entity.get("FORM"))));
//		}
//		if (entity.get("FY") != null) {
//			criteria.add(Restrictions.eqOrIsNull("FY", String.valueOf((String) entity.get("FY"))));
//		}
//		if (entity.get("STATUS") != null) {
//			criteria.add(Restrictions.eqOrIsNull("STATUS", String.valueOf((String) entity.get("STATUS"))));
//		}
//		if (entity.get("QUARTER") != null) {
//			criteria.add(Restrictions.eqOrIsNull("QUARTER", String.valueOf((String) entity.get("QUARTER"))));
//		}
//		if (entity.get("RT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("RT", entity.get("RT")));
//		}
//
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<STATEMENTSTATUS>) criteria.list();
//	}
//
//	@Override
//	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		if (entity.get("roCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode").toString()));
//		}
//		if (entity.get("branchName") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchName", entity.get("branchName").toString()));
//		}
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode").toString()));
//		}
//		if (entity.get("branchState") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchState", entity.get("branchState").toString()));
//		}
//		if (entity.get("FY") != null) {
//			String Date = entity.get("FY").toString();
//			String[] parts = Date.split("-");
//			String fy = parts[0] + parts[1];
//			criteria.add(Restrictions.eqOrIsNull("FY", String.valueOf(fy)));
//		}
//		if (entity.get("RT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("RT", entity.get("RT")));
//		}
//
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//	@Override
//	public List<STATEMENTSTATUS> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//
//		if (entity.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("AS_ON_DATE",
//					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
//		}
//		if (entity.get("toDate") != null) {
//			criteria.add(Restrictions.le("AS_ON_DATE",
//					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
//		}
//		if (entity.get("TAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
//		}
//		if (entity.get("FORM") != null) {
//			criteria.add(Restrictions.eqOrIsNull("FORM", String.valueOf((String) entity.get("FORM"))));
//		}
//
//		if (entity.get("FY") != null) {
//			String Date = entity.get("FY").toString();
//			String[] parts = Date.split("-");
//			String fy = parts[0] + parts[1];
//			criteria.add(Restrictions.eqOrIsNull("FY", String.valueOf(fy)));
//		}
//		if (entity.get("QUARTER") != null) {
//			criteria.add(Restrictions.eqOrIsNull("QUARTER", String.valueOf((String) entity.get("QUARTER"))));
//		}
//		if (entity.get("RT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("RT", entity.get("RT")));
//		}
//		if (entity.get("STATUS") != null) {
//			criteria.add(Restrictions.eqOrIsNull("STATUS", entity.get("STATUS")));
//		}
//
//		criteria.addOrder(Order.desc("id"));
////  		criteria.setFirstResult(pageNo * noOfResult);
////  		criteria.setMaxResults(noOfResult);
//		return (List<STATEMENTSTATUS>) criteria.list();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<STATEMENTSTATUS> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(constrains));
//		if (constrains.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("AS_ON_DATE",
//					Date.from(ZonedDateTime.parse((String) constrains.get("fromDate")).toInstant())));
//		}
//		if (constrains.get("toDate") != null) {
//			criteria.add(Restrictions.le("AS_ON_DATE",
//					Date.from(ZonedDateTime.parse((String) constrains.get("toDate")).toInstant())));
//		}
//		if (constrains.get("TAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("TAN", constrains.get("TAN")));
//		}
//		if (constrains.get("FORM") != null) {
//			criteria.add(Restrictions.eqOrIsNull("FORM", String.valueOf((String) constrains.get("FORM"))));
//		}
//
//		if (constrains.get("FY") != null) {
//			String Date = constrains.get("FY").toString();
//			String[] parts = Date.split("-");
//			String fy = parts[0] + parts[1];
//			criteria.add(Restrictions.eqOrIsNull("FY", String.valueOf(fy)));
//		}
//		if (constrains.get("QUARTER") != null) {
//			criteria.add(Restrictions.eqOrIsNull("QUARTER", String.valueOf((String) constrains.get("QUARTER"))));
//		}
//		if (constrains.get("RT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("RT", constrains.get("RT")));
//		}
//		if (constrains.get("STATUS") != null) {
//			criteria.add(Restrictions.eqOrIsNull("STATUS", constrains.get("STATUS")));
//		}
//		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<STATEMENTSTATUS>) criteria.list();
//
//	}
//	
//	public Long findallCount(HashMap<String, Object> constrains) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		criteria.add(Restrictions.allEq(constrains));
//		if (constrains.get("fromDate") != null) {
//			criteria.add(Restrictions.ge("AS_ON_DATE",
//					Date.from(ZonedDateTime.parse((String) constrains.get("fromDate")).toInstant())));
//		}
//		if (constrains.get("toDate") != null) {
//			criteria.add(Restrictions.le("AS_ON_DATE",
//					Date.from(ZonedDateTime.parse((String) constrains.get("toDate")).toInstant())));
//		}
//		if (constrains.get("TAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("TAN", constrains.get("TAN")));
//		}
//		if (constrains.get("FORM") != null) {
//			criteria.add(Restrictions.eqOrIsNull("FORM", String.valueOf((String) constrains.get("FORM"))));
//		}
//		if (constrains.get("FY") != null) {
//			criteria.add(Restrictions.eqOrIsNull("FY", String.valueOf((String) constrains.get("FY"))));
//		}
//		if (constrains.get("QUARTER") != null) {
//			criteria.add(Restrictions.eqOrIsNull("QUARTER", String.valueOf((String) constrains.get("QUARTER"))));
//		}
//		if (constrains.get("RT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("RT", constrains.get("RT")));
//		}
//		if (constrains.get("STATUS") != null) {
//			criteria.add(Restrictions.eqOrIsNull("STATUS", constrains.get("STATUS")));
//		}
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("STATEMENTSTATUSDao")
public class STATEMENTSTATUSDaoImpl extends AbstractDaoTaxo<Long, STATEMENTSTATUS> implements STATEMENTSTATUSDao {

    /** Search with raw HashMap (external input) */
    @Override
    public List<STATEMENTSTATUS> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<STATEMENTSTATUS> cq = cb.createQuery(STATEMENTSTATUS.class);
        Root<STATEMENTSTATUS> root = cq.from(STATEMENTSTATUS.class);

        Predicate[] predicates = buildPredicatesFromRaw(entity, cb, root);
        cq.select(root).where(predicates);
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<STATEMENTSTATUS> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    /** Search Excel (no pagination) */
    @Override
    public List<STATEMENTSTATUS> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<STATEMENTSTATUS> cq = cb.createQuery(STATEMENTSTATUS.class);
        Root<STATEMENTSTATUS> root = cq.from(STATEMENTSTATUS.class);

        Predicate[] predicates = buildPredicatesFromRaw(entity, cb, root);
        cq.select(root).where(predicates);
        cq.orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    /** Find all with typed HashMap<String,Object> */
    @Override
    public List<STATEMENTSTATUS> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<STATEMENTSTATUS> cq = cb.createQuery(STATEMENTSTATUS.class);
        Root<STATEMENTSTATUS> root = cq.from(STATEMENTSTATUS.class);

        Predicate[] predicates = buildPredicatesFromTyped(constrains, cb, root);
        cq.select(root).where(predicates);
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<STATEMENTSTATUS> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    /** Count query */
    @Override
    public Long findallCount(HashMap<String, Object> constrains) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<STATEMENTSTATUS> root = cq.from(STATEMENTSTATUS.class);

        Predicate[] predicates = buildPredicatesFromTyped(constrains, cb, root);
        cq.select(cb.count(root)).where(predicates);

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    /** Search count for LinkedHashMap */
    @Override
    public Long findSearchCount(LinkedHashMap<String, Object> entity) {
        return findallCount(new HashMap<>(entity));
    }

    /** Build predicates for raw HashMap (external input) */
    private Predicate[] buildPredicatesFromRaw(HashMap entity, CriteriaBuilder cb, Root<STATEMENTSTATUS> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("fromDate") != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("AS_ON_DATE"),
                    Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
        }
        if (entity.get("toDate") != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("AS_ON_DATE"),
                    Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
        }
        if (entity.get("TAN") != null) {
            predicates.add(cb.equal(root.get("TAN"), entity.get("TAN")));
        }
        if (entity.get("FORM") != null) {
            predicates.add(cb.equal(root.get("FORM"), String.valueOf(entity.get("FORM"))));
        }
        if (entity.get("FY") != null) {
            String[] parts = entity.get("FY").toString().split("-");
            predicates.add(cb.equal(root.get("FY"), entity.get("FY")));
        }
        if (entity.get("QUARTER") != null) {
            predicates.add(cb.equal(root.get("QUARTER"), String.valueOf(entity.get("QUARTER"))));
        }
        if (entity.get("RT") != null) {
            predicates.add(cb.equal(root.get("RT"), entity.get("RT")));
        }
        if (entity.get("STATUS") != null) {
            predicates.add(cb.equal(root.get("STATUS"), entity.get("STATUS")));
        }

        return predicates.toArray(new Predicate[0]);
    }

    /** Build predicates for typed HashMap<String,Object> */
    private Predicate[] buildPredicatesFromTyped(HashMap<String, Object> entity, CriteriaBuilder cb,
            Root<STATEMENTSTATUS> root) {
        List<Predicate> predicates = new ArrayList<>();

        entity.forEach((k, v) -> {
            if (v != null) {
                switch (k) {
                    case "fromDate":
                        predicates.add(cb.greaterThanOrEqualTo(root.get("AS_ON_DATE"),
                                Date.from(ZonedDateTime.parse((String) v).toInstant())));
                        break;
                    case "toDate":
                        predicates.add(cb.lessThanOrEqualTo(root.get("AS_ON_DATE"),
                                Date.from(ZonedDateTime.parse((String) v).toInstant())));
                        break;
                    case "FY":
//                        String[] parts = v.toString().split("-");
                        predicates.add(cb.equal(root.get("FY"), entity.get(v)));
                        break;
                    default:
                        predicates.add(cb.equal(root.get(k), v));
                }
            }
        });

        return predicates.toArray(new Predicate[0]);
    }
}

