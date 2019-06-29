package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.TOKENNUMBERDao;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;
import domain.in.rjsa.model.tds.TOKENNUMBER;	
@Repository("TOKENNUMBERDao")
public class TOKENNUMBERDaoImpl extends AbstractTDSDao<Long, TOKENNUMBER> implements TOKENNUMBERDao {
	@SuppressWarnings("unchecked")
	public List<TOKENNUMBER> search(HashMap entity, Long clientId) {
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
          if(entity.get("FY")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FY", entity.get("FY")));
          }
          if(entity.get("QUARTER")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("QUARTER", entity.get("QUARTER")));
          }
          
	
		return (List<TOKENNUMBER>) criteria.list();
	}
	

}
