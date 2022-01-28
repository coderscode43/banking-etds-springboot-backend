package domain.in.rjsa.dao.impl;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.Regular27QDeducteeDefualtDao;
import domain.in.rjsa.model.fy.Regular27QDeducteeDefualt;

@Repository("regular27QDeducteeDefualtDao")
public class Regular27QDeducteeDefualtDaoImpl extends AbstractNewDao<Long, Regular27QDeducteeDefualt> implements Regular27QDeducteeDefualtDao {

	@SuppressWarnings("unchecked")
	public List<Regular27QDeducteeDefualt> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
          if(entity.get("pan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
          }
          if(entity.get("sectionCode")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("sectionCode", entity.get("sectionCode")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
		
          }
         if(entity.get("month")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
		
          }
         
         if(entity.get("remarks")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
		
         }
          
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
          
		criteria.addOrder(Order.desc("date"));
		return (List<Regular27QDeducteeDefualt>) criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Regular27QDeducteeDefualt> findall(HashMap<String, Object> constrains, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.in("branchId", (List<Long>)constrains.remove("branchId")));
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * noOfResult);
		criteria.setMaxResults(noOfResult);
		return (List<Regular27QDeducteeDefualt>) criteria.list();

	}

}
