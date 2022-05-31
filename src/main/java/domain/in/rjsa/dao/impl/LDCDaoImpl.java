package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.LDCDao;
import domain.in.rjsa.model.tds.LDC;

@Repository("LDCDao")
public class LDCDaoImpl extends AbstractDaoTaxo<String, LDC> implements LDCDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<LDC> search(HashMap entity, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		 if(entity.get("PAN")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("PAN", entity.get("PAN")));
         }
		 if(entity.get("SECTION_CODE")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("SECTION_CODE", entity.get("SECTION_CODE")));
         }
		 
		 if(entity.get("LDC_NUMBER")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("LDC_NUMBER", entity.get("LDC_NUMBER")));
         }
		 if(entity.get("TAN")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
         }
		 if(entity.get("FY")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("FY", entity.get("FY")));
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
		 if(entity.get("NATURE_OF_PAYMENT")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("NATURE_OF_PAYMENT", entity.get("NATURE_OF_PAYMENT")));
         }
		 
		
		 criteria.addOrder(Order.desc("PAN"));
			criteria.setFirstResult(pageNo * noOfResult);
			criteria.setMaxResults(noOfResult);
			return (List<LDC>) criteria.list();
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
