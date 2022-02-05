package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.PensionerSalaryDao;
import domain.in.rjsa.model.fy.PensionerSalary;
@Repository("pensionsalaryDao")
public class PensionerSalaryDaoImpl extends AbstractDaoFY<Long, PensionerSalary> implements PensionerSalaryDao  {

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
		 
		if(entity.get("pensionersNo")!=null)
	        {
			criteria.add(Restrictions.eqOrIsNull("pensionersNo", entity.get("pensionersNo")));
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
		return (List< PensionerSalary>) criteria.list();
	}
}

	


	

