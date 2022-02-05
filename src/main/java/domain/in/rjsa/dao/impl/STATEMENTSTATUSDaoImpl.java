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

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;

@Repository("STATEMENTSTATUSDao")
public class STATEMENTSTATUSDaoImpl extends AbstractDaoTaxo<Long, STATEMENTSTATUS> implements STATEMENTSTATUSDao{
	@SuppressWarnings("unchecked")
	public List<STATEMENTSTATUS> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("AS_ON_DATE",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("AS_ON_DATE", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
          if(entity.get("TAN")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
          }
          if(entity.get("FORM")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FORM", entity.get("FORM")));
          }
          if(entity.get("FY")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FY", entity.get("FY")));
          }
          if(entity.get("QUARTER")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("QUARTER", entity.get("QUARTER")));
          }
          
          criteria.addOrder(Order.desc("AS_ON_DATE"));
		return (List<STATEMENTSTATUS>) criteria.list();
	}
	

}
