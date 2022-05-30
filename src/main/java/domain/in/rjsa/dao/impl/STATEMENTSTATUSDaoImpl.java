package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.STATEMENTSTATUSDao;
import domain.in.rjsa.model.tds.STATEMENTSTATUS;

@Repository("STATEMENTSTATUSDao")
public class STATEMENTSTATUSDaoImpl extends AbstractDaoTaxo<Long, STATEMENTSTATUS> implements STATEMENTSTATUSDao{
	@SuppressWarnings("unchecked")
	public List<STATEMENTSTATUS> search(HashMap entity, int pageNo, int noOfResult) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("AS_ON_DATE",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("AS_ON_DATE", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
          if(entity.get("TAN")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("TAN" ,entity.get("TAN")));
          }
          if(entity.get("FORM")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FORM",String.valueOf((String) entity.get("FORM"))));
          }
          if(entity.get("FY")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FY",String.valueOf((String) entity.get("FY"))));
          }
          if(entity.get("QUARTER")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("QUARTER", String.valueOf((String) entity.get("QUARTER"))));
          }
          
          criteria.addOrder(Order.desc("id"));
  		criteria.setFirstResult(pageNo * noOfResult);
  		criteria.setMaxResults(noOfResult);
		return (List<STATEMENTSTATUS>) criteria.list();
	}
	
	
	
	
	@Override
	public Long findSearchCount(LinkedHashMap<String, Object> entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("roCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("roCode",  entity.get("roCode").toString()));
		}
		if (entity.get("branchName") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchName", entity.get("branchName").toString()));
		}
		if (entity.get("branchCode") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchCode", entity.get("branchCode").toString()));
		}
		if (entity.get("branchState") != null) {
			criteria.add(Restrictions.eqOrIsNull("branchState", entity.get("branchState").toString()));
		}
	
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}




	@Override
	public List<STATEMENTSTATUS> searchExcel(HashMap entity) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		criteria.add(Restrictions.allEq(propertyNameValues));
		if (entity.get("fromDate") != null) {
			criteria.add(Restrictions.ge("AS_ON_DATE",
					Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
		}
		if (entity.get("toDate") != null) {
			criteria.add(
					Restrictions.le("AS_ON_DATE", Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
		}
          if(entity.get("TAN")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("TAN" ,entity.get("TAN")));
          }
          if(entity.get("FORM")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FORM",String.valueOf((String) entity.get("FORM"))));
          }
          if(entity.get("FY")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("FY",String.valueOf((String) entity.get("FY"))));
          }
          if(entity.get("QUARTER")!=null)
          {
		criteria.add(Restrictions.eqOrIsNull("QUARTER", String.valueOf((String) entity.get("QUARTER"))));
          }
          
          criteria.addOrder(Order.desc("id"));
//  		criteria.setFirstResult(pageNo * noOfResult);
//  		criteria.setMaxResults(noOfResult);
		return (List<STATEMENTSTATUS>) criteria.list();
	}

}
