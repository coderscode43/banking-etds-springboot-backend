package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.VendorPaymentDao;
import domain.in.rjsa.model.form.VendorPayment;

@Repository("vendorPaymentDao")
public class VendorPaymentDaoImpl extends AbstractNewDao<Long, VendorPayment> implements VendorPaymentDao{

	
	@Override
	public List<VendorPayment> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
		if(entity.get("vendorNo")!=null)
        {
		criteria.add(Restrictions.eqOrIsNull("vendorNo", entity.get("vendorNo")));
        }
          if(entity.get("vendorName")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("vendorName", entity.get("vendorName")));
          }
          if(entity.get("vendorPAN")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("vendorPAN", entity.get("vendorPAN")));
          }
		criteria.addOrder(Order.desc("date"));
		return (List<VendorPayment>) criteria.list();
	}

}
