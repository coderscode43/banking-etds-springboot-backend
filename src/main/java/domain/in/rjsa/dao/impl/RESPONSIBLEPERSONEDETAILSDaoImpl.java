//package domain.in.rjsa.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import domain.in.rjsa.dao.AbstractDaoTaxo;
//import domain.in.rjsa.dao.RESPONSIBLEPERSONEDETAILSDao;
//import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
//
//@Repository("RESPONSIBLEPERSONEDETAILSDao")
//public class RESPONSIBLEPERSONEDETAILSDaoImpl extends AbstractDaoTaxo<String, RESPONSIBLEPERSONEDETAILS> implements RESPONSIBLEPERSONEDETAILSDao{
//
//	@Override
//	public RESPONSIBLEPERSONEDETAILS getByKey(String tan) {
//		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
//		propertyNameValues.put("TAN", tan);
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.allEq(propertyNameValues));
//
//		return (RESPONSIBLEPERSONEDETAILS) crit.uniqueResult();
//	}
//
//	@Override
//	public List<RESPONSIBLEPERSONEDETAILS> searchExcel(HashMap map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<RESPONSIBLEPERSONEDETAILS> search(HashMap entity, int pageNo, int noOfResult) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}


package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.RESPONSIBLEPERSONEDETAILSDao;
import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("RESPONSIBLEPERSONEDETAILSDao")
public class RESPONSIBLEPERSONEDETAILSDaoImpl extends AbstractDaoTaxo<String, RESPONSIBLEPERSONEDETAILS>
        implements RESPONSIBLEPERSONEDETAILSDao {

    @Override
    public RESPONSIBLEPERSONEDETAILS getByKey(String tan) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RESPONSIBLEPERSONEDETAILS> cq = cb.createQuery(RESPONSIBLEPERSONEDETAILS.class);
        Root<RESPONSIBLEPERSONEDETAILS> root = cq.from(RESPONSIBLEPERSONEDETAILS.class);

        cq.select(root).where(cb.equal(root.get("TAN"), tan));

        TypedQuery<RESPONSIBLEPERSONEDETAILS> query = getEntityManager().createQuery(cq);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<RESPONSIBLEPERSONEDETAILS> search(HashMap<String, Object> entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RESPONSIBLEPERSONEDETAILS> cq = cb.createQuery(RESPONSIBLEPERSONEDETAILS.class);
        Root<RESPONSIBLEPERSONEDETAILS> root = cq.from(RESPONSIBLEPERSONEDETAILS.class);

        // Build predicates dynamically from the map
        Predicate[] predicates = entity.entrySet().stream()
                .map(e -> cb.equal(root.get(e.getKey()), e.getValue()))
                .toArray(Predicate[]::new);

        cq.select(root).where(predicates);

        TypedQuery<RESPONSIBLEPERSONEDETAILS> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    @Override
    public List<RESPONSIBLEPERSONEDETAILS> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RESPONSIBLEPERSONEDETAILS> cq = cb.createQuery(RESPONSIBLEPERSONEDETAILS.class);
        Root<RESPONSIBLEPERSONEDETAILS> root = cq.from(RESPONSIBLEPERSONEDETAILS.class);

        // Build predicates safely with raw HashMap
        List<Predicate> predicates = new ArrayList<>();
        for (Object keyObj : entity.keySet()) {
            Object value = entity.get(keyObj);
            if (value != null) {
                predicates.add(cb.equal(root.get(keyObj.toString()), value));
            }
        }

        cq.select(root).where(predicates.toArray(new Predicate[0]));

        return getEntityManager().createQuery(cq).getResultList();
    }

}
