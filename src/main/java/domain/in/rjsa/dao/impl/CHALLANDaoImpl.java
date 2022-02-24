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
import domain.in.rjsa.dao.CHALLANDao;
import domain.in.rjsa.model.tds.CHALLAN;

@Repository("CHALLANDao")
public class CHALLANDaoImpl extends AbstractDaoTaxo<String, CHALLAN> implements CHALLANDao{
	//@SuppressWarnings("unchecked")
	@Override
	public List< CHALLAN> search(HashMap entity, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();

		criteria.add(Restrictions.allEq(propertyNameValues));
		
		 if(entity.get("CIN")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("CIN", entity.get("CIN")));
         }
		 if(entity.get("TAN")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
         }
		 if(entity.get("AMOUNT_OF_CLALLAN")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("AMOUNT_OF_CLALLAN", entity.get("AMOUNT_OF_CLALLAN")));
         }
		 if(entity.get("CHALLAN_MISMATCH")!=null)
         {
		criteria.add(Restrictions.eqOrIsNull("CHALLAN_MISMATCH", entity.get("AMOUNT_OF_CLALLAN")));
         }
		
		 criteria.addOrder(Order.desc("id"));
			criteria.setFirstResult(pageNo * noOfResult);
			criteria.setMaxResults(noOfResult);
			return (List<CHALLAN>) criteria.list();
	}

	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
	criteria.add(Restrictions.allEq(propertyNameValues));
	
	 if(entity.get("CIN")!=null)
    {
	criteria.add(Restrictions.eqOrIsNull("CIN", entity.get("CIN")));
    }
	 if(entity.get("TAN")!=null)
    {
	criteria.add(Restrictions.eqOrIsNull("TAN", entity.get("TAN")));
    }
	 if(entity.get("AMOUNT_OF_CLALLAN")!=null)
    {
	criteria.add(Restrictions.eqOrIsNull("AMOUNT_OF_CLALLAN", entity.get("AMOUNT_OF_CLALLAN")));
    }
	 if(entity.get("CHALLAN_MISMATCH")!=null)
    {
	criteria.add(Restrictions.eqOrIsNull("CHALLAN_MISMATCH", entity.get("AMOUNT_OF_CLALLAN")));
    }
	
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

}

	@Override
	public CHALLAN getByKey(Long key) {
		// TODO Auto-generated method stub
		return null;
	}
}

