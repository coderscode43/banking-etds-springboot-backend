package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.model.fy.Ticket;
@Repository("ticketDao")
public class TicketDaoImpl extends AbstractDaoForm<Long, Ticket> implements TicketDao{
	
	@SuppressWarnings("unchecked")
	public List<Ticket> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("dateOfOpening",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("dateOfChange", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}  
          if(entity.get("status")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
          }
          if(entity.get("form")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("remarks")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
          }
          
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
         criteria.addOrder(Order.desc("dateOfOpening"));
		return (List<Ticket>) criteria.list();
	}

	@Override
	public List<Ticket> search(HashMap entity, int pageNo, int noOfResult) {
		// TODO Auto-generated method stub
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("dateOfOpening",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("dateOfChange", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}  
          if(entity.get("status")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
          }
          if(entity.get("form")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("remarks")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
          }
          
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
         criteria.addOrder(Order.desc("dateOfOpening"));
		return (List<Ticket>) criteria.list();
	}

}
