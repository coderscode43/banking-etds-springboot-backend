package domain.in.rjsa.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.dao.CHALLANDao;
import domain.in.rjsa.model.tds.CHALLAN;

@Repository("CHALLANDao")
public class CHALLANDaoImpl extends AbstractDaoTaxo<String, CHALLAN> implements CHALLANDao {

    @Override
    public List<CHALLAN> search(HashMap<String, Object> entity, int pageNo, int noOfResult) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CHALLAN> cq = cb.createQuery(CHALLAN.class);
        Root<CHALLAN> root = cq.from(CHALLAN.class);

        Predicate[] predicates = buildPredicates(cb, root, entity);
        cq.select(root).where(predicates);
        cq.orderBy(cb.desc(root.get("CIN")));

        TypedQuery<CHALLAN> query = getEntityManager().createQuery(cq);
        query.setFirstResult(pageNo * noOfResult);
        query.setMaxResults(noOfResult);

        return query.getResultList();
    }

    @Override
    public Long findallCount(HashMap<String, Object> entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CHALLAN> root = cq.from(CHALLAN.class);

        Predicate[] predicates = buildPredicates(cb, root, entity);
        cq.select(cb.count(root)).where(predicates);

        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public CHALLAN getByKey(String key) {
        return getEntityManager().find(CHALLAN.class, key);
    }

    @Override
    public List<CHALLAN> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CHALLAN> cq = cb.createQuery(CHALLAN.class);
        Root<CHALLAN> root = cq.from(CHALLAN.class);

        Predicate[] predicates = buildPredicates(cb, root, entity);
        cq.select(root).where(predicates);
        cq.orderBy(cb.desc(root.get("CIN")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    private Predicate[] buildPredicates(CriteriaBuilder cb, Root<CHALLAN> root, Map<String, Object> entity) {
        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("CIN") != null) {
            predicates.add(cb.equal(root.get("CIN"), entity.get("CIN")));
        }
        if (entity.get("TAN") != null) {
            predicates.add(cb.equal(root.get("TAN"), entity.get("TAN")));
        }
        if (entity.get("AMOUNT_OF_CLALLAN") != null) {
            predicates.add(cb.equal(root.get("AMOUNT_OF_CLALLAN"), entity.get("AMOUNT_OF_CLALLAN")));
        }
        if (entity.get("CHALLAN_MISMATCH") != null) {
            predicates.add(cb.equal(root.get("CHALLAN_MISMATCH"), entity.get("CHALLAN_MISMATCH")));
        }
        if (entity.get("dateOfDeposition") != null) {
            Date date = parseDateFromString((String) entity.get("dateOfDeposition"));
            if (date != null) {
                predicates.add(cb.equal(root.get("DATE_OF_DEPOSITION"), date));
            }
        }
        if (entity.get("asOnDate") != null) {
            Date date = parseDateFromString((String) entity.get("asOnDate"));
            if (date != null) {
                predicates.add(cb.equal(root.get("AS_ON_DATE"), date));
            }
        }

        return predicates.toArray(new Predicate[0]);
    }

    private Date parseDateFromString(String isoDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(ZonedDateTime.parse(isoDate).truncatedTo(ChronoUnit.DAYS).toLocalDate().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
