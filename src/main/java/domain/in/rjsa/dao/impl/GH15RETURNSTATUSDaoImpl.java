//package domain.in.rjsa.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoTaxo;
//import domain.in.rjsa.dao.GH15RETURNSTATUSDao;
//import domain.in.rjsa.model.tds.GH15RETURNSTATUS;
//
//@Repository("GH15RETURNSTATUSDao")
//public class GH15RETURNSTATUSDaoImpl extends AbstractDaoTaxo<Long, GH15RETURNSTATUS> implements GH15RETURNSTATUSDao {
//	@SuppressWarnings("unchecked")
//	public List<GH15RETURNSTATUS> search(HashMap entity,int pageNo, int noOfResult) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		//propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//          if(entity.get("TAN")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
//          }
//          if(entity.get("FORM")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("FORM", entity.get("FORM")));
//          }
//          if(entity.get("RT")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("RT", entity.get("RT")));
//          }
//          if(entity.get("QUARTER")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("QUARTER", entity.get("QUARTER")));
//          }
//          criteria.setFirstResult(pageNo * noOfResult);
//  		criteria.setMaxResults(noOfResult);
//	
//		return (List<GH15RETURNSTATUS>) criteria.list();
//	}
//
//	@Override
//	public List<GH15RETURNSTATUS> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
////		propertyNameValues.put("clientId", clientId);
////		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//          if(entity.get("TAN")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
//          }
//          if(entity.get("FORM")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("FORM", entity.get("FORM")));
//          }
//          if(entity.get("RT")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("RT", entity.get("RT")));
//          }
//          if(entity.get("QUARTER")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("QUARTER", entity.get("QUARTER")));
//          }
//          
//	
//		return (List<GH15RETURNSTATUS>) criteria.list();
//	}
//}

package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.GH15RETURNSTATUSDao;
import domain.in.rjsa.model.tds.GH15RETURNSTATUS;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("GH15RETURNSTATUSDao")
public class GH15RETURNSTATUSDaoImpl extends AbstractDaoTaxo<Long, GH15RETURNSTATUS> implements GH15RETURNSTATUSDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<GH15RETURNSTATUS> search(HashMap entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GH15RETURNSTATUS> cq = cb.createQuery(GH15RETURNSTATUS.class);
        Root<GH15RETURNSTATUS> root = cq.from(GH15RETURNSTATUS.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("TAN") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("TAN"), entity.get("TAN")),
                    cb.isNull(root.get("TAN"))
            ));
        }
        if (entity.get("FORM") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("FORM"), entity.get("FORM")),
                    cb.isNull(root.get("FORM"))
            ));
        }
        if (entity.get("RT") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("RT"), entity.get("RT")),
                    cb.isNull(root.get("RT"))
            ));
        }
        if (entity.get("QUARTER") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("QUARTER"), entity.get("QUARTER")),
                    cb.isNull(root.get("QUARTER"))
            ));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public List<GH15RETURNSTATUS> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GH15RETURNSTATUS> cq = cb.createQuery(GH15RETURNSTATUS.class);
        Root<GH15RETURNSTATUS> root = cq.from(GH15RETURNSTATUS.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("TAN") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("TAN"), entity.get("TAN")),
                    cb.isNull(root.get("TAN"))
            ));
        }
        if (entity.get("FORM") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("FORM"), entity.get("FORM")),
                    cb.isNull(root.get("FORM"))
            ));
        }
        if (entity.get("RT") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("RT"), entity.get("RT")),
                    cb.isNull(root.get("RT"))
            ));
        }
        if (entity.get("QUARTER") != null) {
            predicates.add(cb.or(
                    cb.equal(root.get("QUARTER"), entity.get("QUARTER")),
                    cb.isNull(root.get("QUARTER"))
            ));
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }
}

