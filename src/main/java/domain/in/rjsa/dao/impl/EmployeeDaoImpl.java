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
import domain.in.rjsa.dao.EmployeeDao;
import domain.in.rjsa.model.form.Employee;
import domain.in.rjsa.model.form.Regular26QDeductee;
@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractNewDao<Long, Employee> implements EmployeeDao  
{
	@SuppressWarnings("unchecked")
	public List< Employee> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
	/*	if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("paymentDate",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("paymentDate", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}*/
          if(entity.get("employeeNo")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("employeeNo", entity.get("employeeNo")));
          }
          if(entity.get("name")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
          }
          if(entity.get("designation")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("designation", entity.get("designation")));
          }
          if(entity.get("pan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
          }
          if(entity.get("email1")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("email1", entity.get("email1")));
          }
          if(entity.get("email2")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("email2", entity.get("email2")));
          }
          if(entity.get("mobile1")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("mobile1", entity.get("mobile1")));
          }
          if(entity.get("mobile2")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("mobile2", entity.get("mobile2")));
          }
          
          
		//criteria.addOrder(Order.desc("paymentDate"));
		return (List< Employee>) criteria.list();
	}
}

	
	
	
	
