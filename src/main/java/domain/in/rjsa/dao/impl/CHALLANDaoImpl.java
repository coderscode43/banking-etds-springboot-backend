package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.CHALLANDao;
import domain.in.rjsa.model.tds.CHALLAN;

@Repository("CHALLANDao")
public class CHALLANDaoImpl extends AbstractDaoTaxo<String, CHALLAN> implements CHALLANDao{
	

	@Override
	public CHALLAN getByKey(String tan) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("TAN", tan);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.allEq(propertyNameValues));

		return (CHALLAN) crit.uniqueResult();
	}

	

}

