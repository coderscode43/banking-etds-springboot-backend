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
import domain.in.rjsa.dao.VendorDao;
import domain.in.rjsa.model.form.Regular26QDeductee;
import domain.in.rjsa.model.form.Vendor;

@Repository("vendorDao")
public class VendorDaoImpl extends AbstractNewDao<Long, Vendor> implements VendorDao{

	@SuppressWarnings("unchecked")
	public List< Vendor> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		/*if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("paymentDate",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("paymentDate", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}*/
          if(entity.get("pan")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("pan", entity.get("pan")));
          }
          if(entity.get("vendorName")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("vendorName", entity.get("vendorName")));
          }
          if(entity.get("gstNo")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("gstNo", entity.get("gstNo")));
          }
          if(entity.get("email")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("email", entity.get("email")));
          }
          
	//	criteria.addOrder(Order.desc("paymentDate"));
		return (List< Vendor>) criteria.list();
	}
}


