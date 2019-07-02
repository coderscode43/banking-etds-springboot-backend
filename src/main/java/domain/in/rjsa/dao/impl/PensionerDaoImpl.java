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
import domain.in.rjsa.dao.PensionerDao;
import domain.in.rjsa.model.form.Pensioner;
import domain.in.rjsa.model.form.Regular26QDeductee;

@Repository("pensionerDao")
public class PensionerDaoImpl extends AbstractNewDao<Long, Pensioner> implements PensionerDao{

	@SuppressWarnings("unchecked")
	public List< Pensioner> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		/*if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("paymentDate",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("paymentDate", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}*/
          if(entity.get("pensionNo")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("pensionNo", entity.get("pensionNo")));
          }
          if(entity.get("name")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("name", entity.get("name")));
          }
          if(entity.get("pan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
          }
          if(entity.get("empGrade")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("empGrade", entity.get("empGrade")));
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
          
	//	criteria.addOrder(Order.desc("paymentDate"));
		return (List< Pensioner>) criteria.list();
	}
}

	

