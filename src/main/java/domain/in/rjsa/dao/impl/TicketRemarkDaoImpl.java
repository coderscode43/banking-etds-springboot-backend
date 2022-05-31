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
import domain.in.rjsa.dao.TicketRemarkDao;
import domain.in.rjsa.model.fy.TicketRemark;
@Repository("ticketRemarkDao")
public class TicketRemarkDaoImpl extends AbstractDaoForm<Long, TicketRemark> implements TicketRemarkDao{
	
	@SuppressWarnings("unchecked")
	public List<TicketRemark> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		
		if (entity.get("ticketId") != null) {
			criteria.add(Restrictions.eqOrIsNull("ticketId", entity.get("ticketId")));
		}
		if (entity.get("date") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("date")).toInstant())));
		}
		if (entity.get("date") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("date")).toInstant())));
		}
          if(entity.get("description")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("description", entity.get("description")));
          }
          if(entity.get("userName")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("userName", entity.get("userName")));
          }
         
         criteria.addOrder(Order.desc("date"));
		return (List<TicketRemark>) criteria.list();
	}

	@Override
	public List<TicketRemark> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("clientId", clientId);
//		criteria.add(Restrictions.allEq(propertyNameValues));
		
		
		if (entity.get("ticketId") != null) {
			criteria.add(Restrictions.eqOrIsNull("ticketId", entity.get("ticketId")));
		}
		if (entity.get("date") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("date")).toInstant())));
		}
		if (entity.get("date") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("date")).toInstant())));
		}
          if(entity.get("description")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("description", entity.get("description")));
          }
          if(entity.get("userName")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("userName", entity.get("userName")));
          }
         
         criteria.addOrder(Order.desc("date"));
		return (List<TicketRemark>) criteria.list();
	}

}
