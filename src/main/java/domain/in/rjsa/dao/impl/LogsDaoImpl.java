package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.model.form.Logs;
import domain.in.rjsa.model.tds.TOKENNUMBER;
@Repository("logsDao")
public class LogsDaoImpl extends AbstractNewDao<Long, Logs> implements LogsDao{
	public List<Logs> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
          if(entity.get("user")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("user", entity.get("user")));
          }
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
          if(entity.get("tan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("tan", entity.get("tan")));
          }
          if(entity.get("form")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          
	
		return (List<Logs>) criteria.list();
	}
}
