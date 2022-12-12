package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ibm.icu.util.BytesTrie.Iterator;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.TicketDao;
import domain.in.rjsa.model.fy.Ticket;

@Repository("ticketDao")
public class TicketDaoImpl extends AbstractDaoForm<Long, Ticket> implements TicketDao {

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
			criteria.add(Restrictions.le("dateOfChange",
					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("form") != null) {
			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("remarks") != null) {
			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
		}

		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", Long.valueOf((String) entity.get("branchCode"))));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("custVendId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custVendId", Long.valueOf((String)entity.get("custVendId"))));
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
			criteria.add(Restrictions.le("dateOfChange",
					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("form") != null) {
			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("remarks") != null) {
			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
		}

		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("custVendId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custVendId",Long.valueOf((String) entity.get("custVendId"))));
		}
		criteria.addOrder(Order.desc("dateOfOpening"));
		return (List<Ticket>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Long findallCount(HashMap<String, Object> entity) {

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
			criteria.add(Restrictions.le("dateOfChange",
					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("form") != null) {
			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("remarks") != null) {
			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
		}

		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode")));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("custVendId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custVendId", Long.valueOf((String)entity.get("custVendId"))));
		}
		criteria.addOrder(Order.desc("dateOfOpening"));
//           criteria.setFirstResult(pageNo * noOfResult);
//    		criteria.setMaxResults(noOfResult);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> searchExcel(HashMap entity) {
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
			criteria.add(Restrictions.le("dateOfChange",
					Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
		if (entity.get("resolved") != null) {
			criteria.add(Restrictions.eqOrIsNull("resolved", Boolean.valueOf(entity.get("resolved").toString())));
		}
		if (entity.get("form") != null) {
			criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
		}
		if (entity.get("fy") != null) {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		}
		if (entity.get("remarks") != null) {
			criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
		}

		if (entity.get("quarter") != null) {
			criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", Long.valueOf((String)entity.get("branchCode"))));
		}
		if (entity.get("pan") != null) {
			criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
		}
		if (entity.get("custVendId") != null) {
			criteria.add(Restrictions.eqOrIsNull("custVendId", Long.valueOf((String)entity.get("custVendId"))));
		}
		criteria.addOrder(Order.desc("dateOfOpening"));
		return (List<Ticket>) criteria.list();
	}
	
	
	public Map<String,Long> getStatusCounts(Long branchCode, boolean isAdmin) {
		
		
		Criteria criteria = createEntityCriteria();
	    ProjectionList projectionList = Projections.projectionList();
	    projectionList.add(Projections.groupProperty("status"));
	    projectionList.add(Projections.rowCount());
	    criteria.setProjection(projectionList);
	    if(!isAdmin) {
	    	criteria.add(Restrictions.eqOrIsNull("branchCode", branchCode));;
	    }
	    List results = criteria.list();
	    Map<String,Long> hashMap = new HashMap();
	    java.util.Iterator it =  results.iterator();
	    while(it.hasNext()) {
	    	Object[] obj = (Object[]) it.next();
	    	String status = obj[0].toString();
	    	Long count = Long.valueOf(obj[1].toString());
	    	hashMap.put(status,count);
	    }
	    
	    return hashMap;
	}

}
