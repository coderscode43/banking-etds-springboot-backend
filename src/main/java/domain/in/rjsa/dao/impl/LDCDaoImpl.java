//package domain.in.rjsa.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoTaxo;
//import domain.in.rjsa.dao.LDCDao;
//import domain.in.rjsa.model.tds.LDC;
//
//@Repository("LDCDao")
//public class LDCDaoImpl extends AbstractDaoTaxo<String, LDC> implements LDCDao {
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LDC> search(HashMap entity, int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//
//		criteria.add(Restrictions.allEq(propertyNameValues));
//
//		if (entity.get("PAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("PAN", entity.get("PAN")));
//		}
//		if (entity.get("SECTION_CODE") != null) {
//			criteria.add(Restrictions.eqOrIsNull("SECTION_CODE", entity.get("SECTION_CODE")));
//		}
//
//		if (entity.get("LDC_NUMBER") != null) {
//			criteria.add(Restrictions.eqOrIsNull("LDC_NUMBER", entity.get("LDC_NUMBER")));
//		}
//		if (entity.get("TAN") != null) {
//			String[] Tan = entity.get("TAN").toString().split(Pattern.quote("-"), -1);
//			criteria.add(Restrictions.eqOrIsNull("TAN", Tan[0]));
//		}
//		if (entity.get("FY") != null) {
//			criteria.add(Restrictions.eqOrIsNull("FY", entity.get("FY")));
//		}
//		if (entity.get("LDC_RATE") != null) {
//			criteria.add(Restrictions.eqOrIsNull("LDC_RATE", entity.get("LDC_RATE")));
//		}
//		if (entity.get("CERTIFICATE_LIMIT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("CERTIFICATE_LIMIT", entity.get("CERTIFICATE_LIMIT")));
//		}
//		if (entity.get("AMOUNT_CONSUMED") != null) {
//			criteria.add(Restrictions.eqOrIsNull("AMOUNT_CONSUMED", entity.get("AMOUNT_CONSUMED")));
//		}
//		if (entity.get("NATURE_OF_PAYMENT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("NATURE_OF_PAYMENT", entity.get("NATURE_OF_PAYMENT")));
//		}
//		if (entity.get("NAME") != null) {
//			criteria.add(Restrictions.eqOrIsNull("NAME", entity.get("NAME")));
//		}
//
//		criteria.addOrder(Order.desc("PAN"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
//		return (List<LDC>) criteria.list();
//	}
//
//	@Override
//	public LDC getByKey(String tan) {
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("TAN", tan);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.allEq(propertyNameValues));
//
//		return (LDC) crit.uniqueResult();
//	}
//
//	@Override
//	public List<LDC> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
////	propertyNameValues.put("TAN", TAN);
////	criteria.add(Restrictions.allEq(propertyNameValues));
//
//		if (entity.get("PAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("PAN", entity.get("PAN")));
//		}
//		if (entity.get("SECTION_CODE") != null) {
//			criteria.add(Restrictions.eqOrIsNull("SECTION_CODE", entity.get("SECTION_CODE")));
//		}
//
//		if (entity.get("LDC_NUMBER") != null) {
//			criteria.add(Restrictions.eqOrIsNull("LDC_NUMBER", entity.get("LDC_NUMBER")));
//		}
//		if (entity.get("TAN") != null) {
//			criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
//		}
//		if (entity.get("FY") != null) {
//			criteria.add(Restrictions.eqOrIsNull("FY", entity.get("FY")));
//		}
//		if (entity.get("LDC_RATE") != null) {
//			criteria.add(Restrictions.eqOrIsNull("LDC_RATE", entity.get("LDC_RATE")));
//		}
//		if (entity.get("CERTIFICATE_LIMIT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("CERTIFICATE_LIMIT", entity.get("CERTIFICATE_LIMIT")));
//		}
//		if (entity.get("AMOUNT_CONSUMED") != null) {
//			criteria.add(Restrictions.eqOrIsNull("AMOUNT_CONSUMED", entity.get("AMOUNT_CONSUMED")));
//		}
//		if (entity.get("NATURE_OF_PAYMENT") != null) {
//			criteria.add(Restrictions.eqOrIsNull("NATURE_OF_PAYMENT", entity.get("NATURE_OF_PAYMENT")));
//		}
//		if (entity.get("NAME") != null) {
//			criteria.add(Restrictions.eqOrIsNull("NAME", entity.get("NAME")));
//		}
//		criteria.addOrder(Order.desc("PAN"));
//		return (List<LDC>) criteria.list();
//	}
//
//}

package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.LDCDao;
import domain.in.rjsa.model.tds.LDC;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("LDCDao")
public class LDCDaoImpl extends AbstractDaoTaxo<String, LDC> implements LDCDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<LDC> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LDC> cq = cb.createQuery(LDC.class);
        Root<LDC> root = cq.from(LDC.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("PAN") != null) {
            predicates.add(cb.or(cb.equal(root.get("PAN"), entity.get("PAN")), cb.isNull(root.get("PAN"))));
        }
        if (entity.get("SECTION_CODE") != null) {
            predicates.add(cb.or(cb.equal(root.get("SECTION_CODE"), entity.get("SECTION_CODE")), cb.isNull(root.get("SECTION_CODE"))));
        }
        if (entity.get("LDC_NUMBER") != null) {
            predicates.add(cb.or(cb.equal(root.get("LDC_NUMBER"), entity.get("LDC_NUMBER")), cb.isNull(root.get("LDC_NUMBER"))));
        }
        if (entity.get("TAN") != null) {
            String[] Tan = entity.get("TAN").toString().split(Pattern.quote("-"), -1);
            predicates.add(cb.or(cb.equal(root.get("TAN"), Tan[0]), cb.isNull(root.get("TAN"))));
        }
        if (entity.get("FY") != null) {
            predicates.add(cb.or(cb.equal(root.get("FY"), entity.get("FY")), cb.isNull(root.get("FY"))));
        }
        if (entity.get("LDC_RATE") != null) {
            predicates.add(cb.or(cb.equal(root.get("LDC_RATE"), entity.get("LDC_RATE")), cb.isNull(root.get("LDC_RATE"))));
        }
        if (entity.get("CERTIFICATE_LIMIT") != null) {
            predicates.add(cb.or(cb.equal(root.get("CERTIFICATE_LIMIT"), entity.get("CERTIFICATE_LIMIT")), cb.isNull(root.get("CERTIFICATE_LIMIT"))));
        }
        if (entity.get("AMOUNT_CONSUMED") != null) {
            predicates.add(cb.or(cb.equal(root.get("AMOUNT_CONSUMED"), entity.get("AMOUNT_CONSUMED")), cb.isNull(root.get("AMOUNT_CONSUMED"))));
        }
        if (entity.get("NATURE_OF_PAYMENT") != null) {
            predicates.add(cb.or(cb.equal(root.get("NATURE_OF_PAYMENT"), entity.get("NATURE_OF_PAYMENT")), cb.isNull(root.get("NATURE_OF_PAYMENT"))));
        }
        if (entity.get("NAME") != null) {
            predicates.add(cb.or(cb.equal(root.get("NAME"), entity.get("NAME")), cb.isNull(root.get("NAME"))));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("PAN")));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public LDC getByKey(String tan) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LDC> cq = cb.createQuery(LDC.class);
        Root<LDC> root = cq.from(LDC.class);

        cq.select(root).where(cb.equal(root.get("TAN"), tan));

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public List<LDC> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LDC> cq = cb.createQuery(LDC.class);
        Root<LDC> root = cq.from(LDC.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("PAN") != null) {
            predicates.add(cb.or(cb.equal(root.get("PAN"), entity.get("PAN")), cb.isNull(root.get("PAN"))));
        }
        if (entity.get("SECTION_CODE") != null) {
            predicates.add(cb.or(cb.equal(root.get("SECTION_CODE"), entity.get("SECTION_CODE")), cb.isNull(root.get("SECTION_CODE"))));
        }
        if (entity.get("LDC_NUMBER") != null) {
            predicates.add(cb.or(cb.equal(root.get("LDC_NUMBER"), entity.get("LDC_NUMBER")), cb.isNull(root.get("LDC_NUMBER"))));
        }
        if (entity.get("TAN") != null) {
            predicates.add(cb.or(cb.equal(root.get("TAN"), entity.get("TAN")), cb.isNull(root.get("TAN"))));
        }
        if (entity.get("FY") != null) {
            predicates.add(cb.or(cb.equal(root.get("FY"), entity.get("FY")), cb.isNull(root.get("FY"))));
        }
        if (entity.get("LDC_RATE") != null) {
            predicates.add(cb.or(cb.equal(root.get("LDC_RATE"), entity.get("LDC_RATE")), cb.isNull(root.get("LDC_RATE"))));
        }
        if (entity.get("CERTIFICATE_LIMIT") != null) {
            predicates.add(cb.or(cb.equal(root.get("CERTIFICATE_LIMIT"), entity.get("CERTIFICATE_LIMIT")), cb.isNull(root.get("CERTIFICATE_LIMIT"))));
        }
        if (entity.get("AMOUNT_CONSUMED") != null) {
            predicates.add(cb.or(cb.equal(root.get("AMOUNT_CONSUMED"), entity.get("AMOUNT_CONSUMED")), cb.isNull(root.get("AMOUNT_CONSUMED"))));
        }
        if (entity.get("NATURE_OF_PAYMENT") != null) {
            predicates.add(cb.or(cb.equal(root.get("NATURE_OF_PAYMENT"), entity.get("NATURE_OF_PAYMENT")), cb.isNull(root.get("NATURE_OF_PAYMENT"))));
        }
        if (entity.get("NAME") != null) {
            predicates.add(cb.or(cb.equal(root.get("NAME"), entity.get("NAME")), cb.isNull(root.get("NAME"))));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("PAN")));

        return getEntityManager().createQuery(cq).getResultList();
    }
}

