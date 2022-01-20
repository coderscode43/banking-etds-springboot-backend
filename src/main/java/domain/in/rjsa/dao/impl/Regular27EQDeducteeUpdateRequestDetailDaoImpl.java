package domain.in.rjsa.dao.impl;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.Regular27EQDeducteeUpdateRequestDetailDao;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.Regular27EQDeducteeUpdateRequestDetail;

@Repository("regular27EQDeducteeUpdateRequestDetailDao")
public class Regular27EQDeducteeUpdateRequestDetailDaoImpl extends AbstractNewDao<Long, Regular27EQDeducteeUpdateRequestDetail> implements Regular27EQDeducteeUpdateRequestDetailDao{

	@SuppressWarnings("unchecked")
	public List< Regular27EQDeducteeUpdateRequestDetail> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("dateofReceivedDebited",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("dateofReceivedDebited", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
          if(entity.get("deducteePan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("deducteePan", entity.get("deducteePan")));
          }
          if(entity.get("nameoftheParty")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("nameoftheParty", entity.get("nameoftheParty")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
          
		criteria.addOrder(Order.desc("dateofReceivedDebited"));
		
		return (List<Regular27EQDeducteeUpdateRequestDetail>) criteria.list();
	}
}
