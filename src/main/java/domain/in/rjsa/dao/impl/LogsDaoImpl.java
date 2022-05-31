package domain.in.rjsa.dao.impl;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.model.fy.Logs;
@Repository("logsDao")
public class LogsDaoImpl extends AbstractDaoForm<Long, Logs> implements LogsDao{
	//@SuppressWarnings("unchecked")
	@Override
	public List<Logs> search(HashMap entity, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
          if(entity.get("username")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("username", entity.get("username")));
          }
          if(entity.get("entity")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("entity", entity.get("entity")));
          }
          if(entity.get("ipaddrs")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("ipaddrs", entity.get("ipaddrs")));
          }      
      	if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}  
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<Logs>) criteria.list();
	}

	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
		if(entity.get("username")!=null)
        {
		criteria.add(Restrictions.eqOrIsNull("username", entity.get("username")));
        }
        if(entity.get("entity")!=null)
        {
		criteria.add(Restrictions.eqOrIsNull("entity", entity.get("entity")));
        }
        if(entity.get("ipaddrs")!=null)
        {
		criteria.add(Restrictions.eqOrIsNull("ipaddrs", entity.get("ipaddrs")));
        }      
    	if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}  
//		if (entity.get("active") != null) {
//			criteria.add(Restrictions.eqOrIsNull("active", Boolean.valueOf(entity.get("active").toString())));
//		}
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public List<Logs> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
          if(entity.get("username")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("username", entity.get("username")));
          }
          if(entity.get("entity")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("entity", entity.get("entity")));
          }
          if(entity.get("ipaddrs")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("ipaddrs", entity.get("ipaddrs")));
          }      
      	if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}  
		criteria.addOrder(Order.desc("id"));
//		criteria.setFirstResult(pageNo * noOfResult);
//		criteria.setMaxResults(noOfResult);
		return (List<Logs>) criteria.list();
	}
}
