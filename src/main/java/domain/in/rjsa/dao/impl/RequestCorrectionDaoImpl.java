package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.RequestCorrectionDao;
import domain.in.rjsa.model.form.RequestCorrection;

@Repository("requestCorrectionDao")
public class RequestCorrectionDaoImpl extends AbstractNewDao<Long, RequestCorrection> implements RequestCorrectionDao{

	@SuppressWarnings("unchecked")
	public List<RequestCorrection> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
          if(entity.get("form")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
          }
          if(entity.get("status")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
          
	
		return (List<RequestCorrection>) criteria.list();
	}
}
