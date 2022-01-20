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

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.LDCDao;
import domain.in.rjsa.model.tds.LDC;

@Repository("lDCDao")
public class LDCDaoImpl extends AbstractTDSDao<Long, LDC> implements LDCDao{
	@SuppressWarnings("unchecked")
	public List<LDC> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("VALID_FROM",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("VALID_FROM", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
          if(entity.get("TAN")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
          }
          if(entity.get("NAME")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("NAME", entity.get("NAME")));
          }
          if(entity.get("PAN")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("PAN", entity.get("PAN")));
          }
          if(entity.get("FY")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FY", entity.get("FY")));
          }
          
          if(entity.get("SECTION_CODE")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("SECTION_CODE", entity.get("SECTION_CODE")));
          }
          if(entity.get("NATURE_OF_PAYMENT")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("NATURE_OF_PAYMENT", entity.get("NATURE_OF_PAYMENT")));
          }
          if(entity.get("LDC_RATE")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("LDC_RATE", entity.get("LDC_RATE")));
          }
          if(entity.get("CERTIFICATE_LIMIT")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("CERTIFICATE_LIMIT", entity.get("CERTIFICATE_LIMIT")));
          }
          
          if(entity.get("AMOUNT_CONSUMED")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("AMOUNT_CONSUMED", entity.get("AMOUNT_CONSUMED")));
          }
          
          criteria.addOrder(Order.desc("VALID_FROM"));
		return (List<LDC>) criteria.list();
	}

}
