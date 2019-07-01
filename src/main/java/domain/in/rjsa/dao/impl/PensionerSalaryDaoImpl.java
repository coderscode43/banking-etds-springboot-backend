package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.PensionerSalaryDao;
import domain.in.rjsa.model.form.PensionerSalary;
import domain.in.rjsa.model.form.Salary;
@Repository("pensionsalaryDao")
public class PensionerSalaryDaoImpl extends AbstractNewDao<Long, PensionerSalary> implements PensionerSalaryDao  {

	@SuppressWarnings("unchecked")
	public List< PensionerSalary> search(HashMap entity, Long clientId) {
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
       
        if(entity.get("month")!=null)
        {
		criteria.add(Restrictions.eqOrIsNull("month", entity.get("month")));
        }
          
		//criteria.addOrder(Order.desc("paymentDate"));
		return (List< PensionerSalary>) criteria.list();
	}
}

	


	

