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
import domain.in.rjsa.model.fy.VendorPayment;

@Repository("vendorPaymentDao")
public class VendorPaymentDaoImpl extends AbstractNewDao<Long, VendorPayment> implements VendorPaymentDao {

	@Override
	public List<VendorPayment> search(HashMap entity, Long clientId) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		propertyNameValues.put("clientId", clientId);
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("branchId") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchId", entity.get("branchId")));
		}
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("date",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("date", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
	

		if (entity.get("vendorNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("vendorNo", entity.get("vendorNo")));
		}
		if (entity.get("vendorName") != null) {
			criteria.add(Restrictions.eqOrIsNull("vendorName", entity.get("vendorName")));
		}
		if (entity.get("vendorPAN") != null) {
			criteria.add(Restrictions.eqOrIsNull("vendorPAN", entity.get("vendorPAN")));
		}
		if (entity.get("gstNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("gstNo", entity.get("gstNo")));
		}
		if (entity.get("remark") != null) {
			criteria.add(Restrictions.eqOrIsNull("remark", entity.get("remark")));
		}
		
		
		if (entity.get("invoiceNumber") != null) {
			criteria.add(Restrictions.eqOrIsNull("invoiceNumber", entity.get("invoiceNumber")));
		}
		if (entity.get("paymentMode") != null) {
			criteria.add(Restrictions.eqOrIsNull("paymentMode", entity.get("paymentMode")));
		}
		if (entity.get("totalInvoiceValue") != null) {
			criteria.add(Restrictions.eqOrIsNull("totalInvoiceValue", entity.get("totalInvoiceValue")));
		}
		if (entity.get("taxableValue") != null) {
			criteria.add(Restrictions.eqOrIsNull("taxableValue", entity.get("taxableValue")));
		}
		if (entity.get("surcharge") != null) {
			criteria.add(Restrictions.eqOrIsNull("surcharge", entity.get("surcharge")));
		}
		if (entity.get("natureOfPayment") != null) {
			criteria.add(Restrictions.eqOrIsNull("natureOfPayment", entity.get("natureOfPayment")));
		}
		if (entity.get("blgCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("blgCode", entity.get("blgCode")));
		}
		if (entity.get("netAmountPaid") != null) {
			criteria.add(Restrictions.eqOrIsNull("netAmountPaid", entity.get("netAmountPaid")));
		}
		
		if (entity.get("gstNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("gstNo", entity.get("gstNo")));
		}
		
		if (entity.get("vendorNo") != null) {
			criteria.add(Restrictions.eqOrIsNull("vendorNo", entity.get("vendorNo")));
		}
		
		if (entity.get("igst") != null) {
			criteria.add(Restrictions.eqOrIsNull("igst", Double.valueOf((String) entity.get("igst"))));
		}
		if (entity.get("sgst") != null) {
			criteria.add(Restrictions.eqOrIsNull("sgst", Double.valueOf((String) entity.get("sgst"))));
		}
		if (entity.get("cgst") != null) {
			criteria.add(Restrictions.eqOrIsNull("cgst", Double.valueOf((String) entity.get("cgst"))));
		}
		
		
		criteria.addOrder(Order.desc("date"));
		return (List<VendorPayment>) criteria.list();
	}

}
