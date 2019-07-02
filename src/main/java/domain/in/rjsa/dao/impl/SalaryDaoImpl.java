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
import domain.in.rjsa.dao.SalaryDao;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.Salary;
@Repository("salaryDao")
public class SalaryDaoImpl extends AbstractNewDao<Long, Salary> implements SalaryDao{
	@SuppressWarnings("unchecked")
	public List< Salary> search(HashMap entity, Long clientId) {
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
		 if(entity.get("month")!=null)
	        {
			criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
	        }     
		 if(entity.get("fy")!=null)
	        {
			criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
	        }
	          
        
        
        
          
		//criteria.addOrder(Order.desc("paymentDate"));
		return (List< Salary>) criteria.list();
	}
}

	

