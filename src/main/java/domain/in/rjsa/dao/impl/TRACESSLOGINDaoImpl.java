package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.TRACESSLOGINDao;
import domain.in.rjsa.model.tds.TRACESSLOGIN;

@Repository("TRACESSLOGINDao")
public class TRACESSLOGINDaoImpl extends AbstractDaoTaxo<String, TRACESSLOGIN> implements TRACESSLOGINDao {

	@Override
	public TRACESSLOGIN getByKey(String tan) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("TAN", tan);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.allEq(propertyNameValues));

		return (TRACESSLOGIN) crit.uniqueResult();
	}
}
