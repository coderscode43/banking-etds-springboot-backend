package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.GH15RETURNSTATUSDao;
import domain.in.rjsa.model.tds.GH15RETURNSTATUS;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;

@Repository("GH15RETURNSTATUSDao")
public class GH15RETURNSTATUSDaoImpl extends AbstractTDSDao<Long, GH15RETURNSTATUS> implements GH15RETURNSTATUSDao {
	@SuppressWarnings("unchecked")
	public List<GH15RETURNSTATUS> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
          if(entity.get("TAN")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
          }
          if(entity.get("FORM")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FORM", entity.get("FORM")));
          }
          if(entity.get("RT")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("RT", entity.get("RT")));
          }
          if(entity.get("QUARTER")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("QUARTER", entity.get("QUARTER")));
          }
          
	
		return (List<GH15RETURNSTATUS>) criteria.list();
	}
}
