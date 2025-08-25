//package domain.in.rjsa.dao.impl;
//
//import java.time.ZonedDateTime;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoForm;
//import domain.in.rjsa.dao.TicketRemarkDao;
//import domain.in.rjsa.model.fy.TicketRemarks;
//@Repository("ticketRemarkDao")
//public class TicketRemarkDaoImpl extends AbstractDaoForm<Long, TicketRemarks> implements TicketRemarkDao{
//	
//	@SuppressWarnings("unchecked")
//	public List<TicketRemarks> search(HashMap entity, Long clientId) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>(	);
//		propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//		
//		if (entity.get("ticketId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("ticketId", entity.get("ticketId")));
//		}
//		if (entity.get("date") != null) {
//			criteria.add(Restrictions.ge("date",
//					Date.from(ZonedDateTime.parse((String) entity.get("date")).toInstant())));
//		}
//		if (entity.get("date") != null) {
//			criteria.add(
//					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("date")).toInstant())));
//		}
//          if(entity.get("description")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("description", entity.get("description")));
//          }
//          if(entity.get("userName")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("userName", entity.get("userName")));
//          }
//         
//         criteria.addOrder(Order.desc("date"));
//		return (List<TicketRemarks>) criteria.list();
//	}
//
//	@Override
//	public List<TicketRemarks> searchExcel(HashMap entity) {
//		Criteria criteria = createEntityCriteria();
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
////		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
////		propertyNameValues.put("clientId", clientId);
////		criteria.add(Restrictions.allEq(propertyNameValues));
//		
//		
//		if (entity.get("ticketId") != null) {
//			criteria.add(Restrictions.eqOrIsNull("ticketId", entity.get("ticketId")));
//		}
//		if (entity.get("date") != null) {
//			criteria.add(Restrictions.ge("date",
//					Date.from(ZonedDateTime.parse((String) entity.get("date")).toInstant())));
//		}
//		if (entity.get("date") != null) {
//			criteria.add(
//					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("date")).toInstant())));
//		}
//          if(entity.get("description")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("description", entity.get("description")));
//          }
//          if(entity.get("userName")!=null)
//          {
//		criteria.add(Restrictions.eqOrIsNull("userName", entity.get("userName")));
//          }
//         
//         criteria.addOrder(Order.desc("date"));
//		return (List<TicketRemarks>) criteria.list();
//	}
//
//}

package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.TicketRemarkDao;
import domain.in.rjsa.model.fy.TicketRemarks;

@Repository("ticketRemarkDao")
public class TicketRemarkDaoImpl extends AbstractDaoForm<Long, TicketRemarks> implements TicketRemarkDao {

    public List<TicketRemarks> search(HashMap entity, Long clientId) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TicketRemarks> cq = cb.createQuery(TicketRemarks.class);
        Root<TicketRemarks> root = cq.from(TicketRemarks.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("clientId"), clientId));
        addTicketRemarkPredicates(entity, cb, root, predicates);

        cq.select(root).where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("date")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<TicketRemarks> searchExcel(HashMap entity) {
        // Same as search but without clientId filter or pagination
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TicketRemarks> cq = cb.createQuery(TicketRemarks.class);
        Root<TicketRemarks> root = cq.from(TicketRemarks.class);

        List<Predicate> predicates = new ArrayList<>();
        addTicketRemarkPredicates(entity, cb, root, predicates);

        cq.select(root).where(predicates.toArray(new Predicate[0]))
          .orderBy(cb.desc(root.get("date")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Helper method to add predicates for TicketRemarks based on HashMap constraints
     */
    private void addTicketRemarkPredicates(HashMap entity, CriteriaBuilder cb, Root<TicketRemarks> root, List<Predicate> predicates) {
        if (entity.get("ticketId") != null) {
            predicates.add(cb.equal(root.get("ticketId"), entity.get("ticketId")));
        }

        // If fromDate and toDate are provided separately, use them properly
        if (entity.get("fromDate") != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"),
                    Date.from(ZonedDateTime.parse(entity.get("fromDate").toString()).toInstant())));
        }
        if (entity.get("toDate") != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("date"),
                    Date.from(ZonedDateTime.parse(entity.get("toDate").toString()).toInstant())));
        }

        if (entity.get("description") != null) {
            predicates.add(cb.equal(root.get("description"), entity.get("description")));
        }
        if (entity.get("userName") != null) {
            predicates.add(cb.equal(root.get("userName"), entity.get("userName")));
        }
    }
}

