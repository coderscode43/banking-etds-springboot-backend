//package domain.in.rjsa.dao.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
//import org.hibernate.Criteria;
//import org.hibernate.annotations.Check;
//import org.hibernate.criterion.MatchMode;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoFY;
//import domain.in.rjsa.dao.Regular27EQDeducteeDao;
//import domain.in.rjsa.model.fy.Regular27EQDeductee;
//
//@Repository("regular27EQDeducteeDao")
//public class Regular27EQDeducteeDaoImpl extends AbstractDaoFY<Long, Regular27EQDeductee>
//		implements Regular27EQDeducteeDao {
//
//	@SuppressWarnings("unchecked")
//	public List<Regular27EQDeductee> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(criteria, entity);
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		criteria.addOrder(Order.desc("id"));
//		return (List<Regular27EQDeductee>) criteria.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	public Long findallCount(HashMap<String, Object> entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(criteria, entity);
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Regular27EQDeductee> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		check(criteria, entity);
//		criteria.addOrder(Order.desc("id"));
//		return (List<Regular27EQDeductee>) criteria.list();
//	}
//
//	@Override
//	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//
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
//
//		if (entity.get("branchCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
//		}
//
//		if (entity.get("fy") != null) {
//			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
//		}
//		if (entity.get("sectionCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("sectionCode",
//					entity.get("sectionCode").toString().split(Pattern.quote("-"), -1)[0]));
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
//		if (entity.get("custVendId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("custVendId", entity.get("custVendId")));
//		}
//
//		if (entity.get("pan") != null) {
//			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
//		}
//
////		if (entity.get("name") != null) {
////			criteria.add(Restrictions.or(
////					Restrictions.like("name", "%" + entity.get("name").toString().toLowerCase() + "%"),
////					Restrictions.like("name", "%" + entity.get("name").toString().toUpperCase() + "%")));
////		}
//		
//		if (entity.get("name") != null) {
//		    String searchName = entity.get("name").toString().toLowerCase(); // Convert the input to lowercase
//		    criteria.add(Restrictions.or(
//		        Restrictions.sqlRestriction("LOWER({alias}.name) LIKE ?", "%" + searchName + "%", org.hibernate.type.StringType.INSTANCE)
//		    ));
//		}
//		
//		if (entity.get("accNo") != null) {
//			criteria.add(Restrictions.eqOrIsNull("accNo", entity.get("accNo")));
//		}
//
//		if (entity.get("challanHeading") != null) {
//			criteria.add(Restrictions.eqOrIsNull("challanHeading", entity.get("challanHeading")));
//		}
//		if (entity.get("errorDescription") != null) {
//			criteria.add(Restrictions.eqOrIsNull("errorDescription", entity.get("errorDescription")));
//		}
//		if (entity.get("warningDescription") != null) {
//			criteria.add(Restrictions.eqOrIsNull("warningDescription", entity.get("warningDescription")));
//		}
//
//		if (entity.get("resolved") != null) {
//			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
//		}
//
//		if (entity.get("TAN") != null) {
//			String[] Tan = (entity.get("TAN").toString()).split("-");
//			criteria.add(Restrictions.eqOrIsNull("TAN", Tan[0]));
//		}
//		if (entity.get("roCode") != null) {
//			criteria.add(Restrictions.eqOrIsNull("roCode", entity.get("roCode").toString()));
//		}
//
//	}
//
//}


package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.Regular27EQDeducteeDao;
import domain.in.rjsa.model.fy.Regular27EQDeductee;

@Repository("regular27EQDeducteeDao")
public class Regular27EQDeducteeDaoImpl extends AbstractDaoFY<Long, Regular27EQDeductee>
        implements Regular27EQDeducteeDao {

    @Override
    public List<Regular27EQDeductee> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Regular27EQDeductee> cq = cb.createQuery(Regular27EQDeductee.class);
        Root<Regular27EQDeductee> root = cq.from(Regular27EQDeductee.class);

        cq.select(root).where(buildPredicates(cb, root, entity));
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<Regular27EQDeductee> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Regular27EQDeductee> root = cq.from(Regular27EQDeductee.class);

        cq.select(cb.count(root)).where(buildPredicates(cb, root, entity));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public Long findSearchCount(LinkedHashMap<String, Object> entity) {
        return findallCount(new HashMap<>(entity));
    }

    @Override
    public List<Regular27EQDeductee> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Regular27EQDeductee> cq = cb.createQuery(Regular27EQDeductee.class);
        Root<Regular27EQDeductee> root = cq.from(Regular27EQDeductee.class);

        cq.select(root).where(buildPredicates(cb, root, entity));
        cq.orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<String> getPanList(String q, String fy, Long branchCode) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Regular27EQDeductee> root = cq.from(Regular27EQDeductee.class);

        cq.select(root.get("pan"))
          .where(cb.equal(root.get("quarter"), q),
                 cb.equal(root.get("fy"), fy),
                 cb.equal(root.get("branchCode"), branchCode));

        return getEntityManager().createQuery(cq).getResultList();
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<Regular27EQDeductee> root, Map entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("branchCode") != null) {
            predicates.add(cb.equal(root.get("branchCode"), entity.get("branchCode")));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.equal(root.get("fy"), entity.get("fy")));
        }
        if (entity.get("sectionCode") != null) {
            String section = entity.get("sectionCode").toString().split(Pattern.quote("-"))[0];
            predicates.add(cb.equal(root.get("sectionCode"), section));
        }
        if (entity.get("quarter") != null) {
            String[] quarters = entity.get("quarter").toString().split(",\\s*");
            predicates.add(root.get("quarter").in(quarters));
        }
        if (entity.get("custVendId") != null) {
            predicates.add(cb.equal(root.get("custVendId"), entity.get("custVendId")));
        }
        if (entity.get("pan") != null) {
            predicates.add(cb.equal(root.get("pan"), entity.get("pan")));
        }
        if (entity.get("name") != null) {
            String searchName = entity.get("name").toString().toLowerCase();
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + searchName + "%"));
        }
        if (entity.get("accNo") != null) {
            predicates.add(cb.equal(root.get("accNo"), entity.get("accNo")));
        }
        if (entity.get("challanHeading") != null) {
            predicates.add(cb.equal(root.get("challanHeading"), entity.get("challanHeading")));
        }
        if (entity.get("errorDescription") != null) {
            predicates.add(cb.equal(root.get("errorDescription"), entity.get("errorDescription")));
        }
        if (entity.get("warningDescription") != null) {
            predicates.add(cb.equal(root.get("warningDescription"), entity.get("warningDescription")));
        }
        if (entity.get("resolved") != null) {
            predicates.add(cb.equal(root.get("resolved"), Boolean.valueOf(entity.get("resolved").toString())));
        }
        if (entity.get("TAN") != null) {
            String tan = entity.get("TAN").toString().split("-")[0];
            predicates.add(cb.equal(root.get("TAN"), tan));
        }
        if (entity.get("roCode") != null) {
            predicates.add(cb.equal(root.get("roCode"), entity.get("roCode").toString()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
