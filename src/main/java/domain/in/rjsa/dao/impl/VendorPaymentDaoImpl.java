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

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.VendorPaymentDao;
import domain.in.rjsa.model.fy.VendorPayment;

@Repository("vendorPaymentDao")
public class VendorPaymentDaoImpl extends AbstractDaoFY<Long, VendorPayment> implements VendorPaymentDao {

	
	@SuppressWarnings("unchecked")
	public List<VendorPayment> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		
		
		if(entity.get("totalInvoiceValue")!=null)
        {
		criteria.add(Restrictions.eqOrIsNull("totalInvoiceValue", entity.get("totalInvoiceValue")));
        }
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("dateOfOpening",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("dateOfOpening", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
          if(entity.get("status")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("status", entity.get("status")));
          }
          if(entity.get("form")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("form", entity.get("form")));
          }
          if(entity.get("fy")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("fy", entity.get("fy")));
          }
          if(entity.get("remarks")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("remarks", entity.get("remarks")));
          }
          
          if(entity.get("quarter")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("quarter", entity.get("quarter")));
          }
          if(entity.get("deducteeId")!=null)
          {
 		criteria.add(Restrictions.eqOrIsNull("deducteeId",Long.valueOf((String) entity.get("deducteeId"))));
          }
          if (entity.get("challanHeading") != null) {
  			criteria.add(Restrictions.eqOrIsNull("challanHeading",  entity.get("challanHeading")));
  		}
         criteria.addOrder(Order.desc("dateOfOpening"));
		return (List<VendorPayment>) criteria.list();
	}

}
