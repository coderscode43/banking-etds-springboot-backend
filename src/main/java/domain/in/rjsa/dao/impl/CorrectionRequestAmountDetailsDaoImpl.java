package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.CorrectionRequestAmountDetailsDao;
import domain.in.rjsa.model.form.CorrectionRemarks;
import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;

@Repository("correctionRequestAmountDetailsDao")
public class CorrectionRequestAmountDetailsDaoImpl extends AbstractDaoForm<Long, CorrectionRequestAmountDetails> implements CorrectionRequestAmountDetailsDao{

	@Override
	public List<CorrectionRequestAmountDetails> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CorrectionRequestAmountDetails> findForm(HashMap<String, Object> constrains) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		if (constrains.containsKey("correctionRequestId")) {
			if (constrains.get("correctionRequestId") instanceof ArrayList)
				criteria.add(Restrictions.in("correctionRequestId", (List<Long>) constrains.remove("correctionRequestId")));
		}
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		
		criteria.add(Restrictions.allEq(constrains));
		criteria.add(Restrictions.allEq(propertyNameValues));
		criteria.addOrder(Order.desc("id"));
		return (List<CorrectionRequestAmountDetails>) criteria.list();
	}

	
}

	

