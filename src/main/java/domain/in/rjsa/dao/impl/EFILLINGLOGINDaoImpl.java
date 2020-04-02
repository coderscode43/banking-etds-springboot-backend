package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.EFILLINGLOGINDao;
import domain.in.rjsa.model.tds.EFILLINGLOGIN;

@Repository("EFILLINGLOGINDao")
public class EFILLINGLOGINDaoImpl extends AbstractTDSDao<String, EFILLINGLOGIN> implements EFILLINGLOGINDao{

	@Override
	public EFILLINGLOGIN getByKey(String tan) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("TAN", tan);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.allEq(propertyNameValues));

		return (EFILLINGLOGIN) crit.uniqueResult();
	}
}
