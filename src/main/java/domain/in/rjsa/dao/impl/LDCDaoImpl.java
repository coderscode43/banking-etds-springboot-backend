package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.LDCDao;
import domain.in.rjsa.model.tds.LDC;

@Repository("lDCDao")
public class LDCDaoImpl extends AbstractDaoTaxo<String, LDC> implements LDCDao{
	@SuppressWarnings("unchecked")
	public List<LDC> search(HashMap entity, Long TAN) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("TAN", TAN);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		 if(entity.get("PAN")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("PAN", entity.get("PAN")));
         }
		
		
		return (List< LDC>) criteria.list();
	}
		

@Override
public LDC getByKey(String tan) {
	Map<String, Object> propertyNameValues = new HashMap<String, Object>();
	propertyNameValues.put("TAN", tan);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.allEq(propertyNameValues));

	return (LDC) crit.uniqueResult();
}


@Override
public List<LDC> searchExcel(HashMap entity) {
	Criteria criteria = createEntityCriteria();
	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
	Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//	propertyNameValues.put("TAN", TAN);
//	criteria.add(Restrictions.allEq(propertyNameValues));
	
	 if(entity.get("PAN")!=null)
     {
	criteria.add(Restrictions.eqOrIsNull("PAN", entity.get("PAN")));
     }
	
	
	return (List<LDC>) criteria.list();
}
	

}
