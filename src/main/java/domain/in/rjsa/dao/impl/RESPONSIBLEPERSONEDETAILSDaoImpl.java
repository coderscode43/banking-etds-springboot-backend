package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.RESPONSIBLEPERSONEDETAILSDao;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;

@Repository("RESPONSIBLEPERSONEDETAILSDao")
public class RESPONSIBLEPERSONEDETAILSDaoImpl extends AbstractDaoTaxo<String, RESPONSIBLEPERSONEDETAILS> implements RESPONSIBLEPERSONEDETAILSDao{

	@Override
	public RESPONSIBLEPERSONEDETAILS getByKey(String tan) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("TAN", tan);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.allEq(propertyNameValues));

		return (RESPONSIBLEPERSONEDETAILS) crit.uniqueResult();
	}

	@Override
	public List<RESPONSIBLEPERSONEDETAILS> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
}
