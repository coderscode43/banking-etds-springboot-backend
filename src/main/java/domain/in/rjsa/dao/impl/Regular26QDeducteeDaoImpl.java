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
import domain.in.rjsa.dao.Regular26QDeducteeDao;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.Regular27QDeductee;

@Repository("regular26QDeducteeDao")
public class Regular26QDeducteeDaoImpl  extends AbstractNewDao<Long, Regular26QDeductee> implements Regular26QDeducteeDao {
	@SuppressWarnings("unchecked")
	public List< Regular26QDeductee> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("paymentDate",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("paymentDate", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
          if(entity.get("deducteePan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteePan", entity.get("deducteePan")));
          }
          if(entity.get("deducteeName")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteeName", entity.get("deducteeName")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
          
		criteria.addOrder(Order.desc("paymentDate"));
		return (List< Regular26QDeductee>) criteria.list();
	}
}
