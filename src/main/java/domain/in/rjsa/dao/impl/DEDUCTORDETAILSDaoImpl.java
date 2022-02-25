package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.DEDUCTORDETAILSDao;
import domain.in.rjsa.model.tds.DEDUCTORDETAILS;

@Repository("DEDUCTORDETAILSDao")
public class DEDUCTORDETAILSDaoImpl extends AbstractDaoTaxo<Long, DEDUCTORDETAILS> implements DEDUCTORDETAILSDao{
	@SuppressWarnings("unchecked")
	public List<DEDUCTORDETAILS> search(HashMap entity, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
			
          if(entity.get("TAN")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("TAN" ,String.valueOf((String) entity.get("TAN"))));
          }
          if(entity.get("STATE")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("STATE",String.valueOf((String) entity.get("STATE"))));
          }
          if(entity.get("CITY")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("CITY",String.valueOf((String) entity.get("CITY"))));
          }
          
          criteria.addOrder(Order.desc("id"));
  		criteria.setFirstResult(pageNo * noOfResult);
  		criteria.setMaxResults(noOfResult);
		return (List<DEDUCTORDETAILS>) criteria.list();
	}
	
	
	
	
	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
		 if(entity.get("TAN")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("TAN" ,String.valueOf((String) entity.get("TAN"))));
         }
         if(entity.get("STATE")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("STATE",String.valueOf((String) entity.get("STATE"))));
         }
         if(entity.get("CITY")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("CITY",String.valueOf((String) entity.get("CITY"))));
         }
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}

