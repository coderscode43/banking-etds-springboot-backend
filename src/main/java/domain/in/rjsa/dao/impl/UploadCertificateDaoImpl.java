package domain.in.rjsa.dao.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.UploadCertificateDao;
import domain.in.rjsa.model.form.UploadCertificate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository("uploadCertificateDao")
public class UploadCertificateDaoImpl extends AbstractDaoForm<Long, UploadCertificate> implements UploadCertificateDao {

    @Override
    public List<UploadCertificate> searchExcel(HashMap entity) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UploadCertificate> cq = cb.createQuery(UploadCertificate.class);
        Root<UploadCertificate> root = cq.from(UploadCertificate.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("fileName") != null) {
            predicates.add(cb.equal(root.get("fileName"), entity.get("fileName")));
        }
        if (entity.get("TAN") != null) {
            predicates.add(cb.equal(root.get("TAN"), entity.get("TAN")));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.equal(root.get("fy"), entity.get("fy")));
        }
        if (entity.get("quarter") != null) {
            predicates.add(cb.equal(root.get("quarter"), entity.get("quarter")));
        }
        if (entity.get("form") != null) {
            predicates.add(cb.equal(root.get("form"), entity.get("form")));
        }
        if (entity.get("fromDate") != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("uploadedTime"),
                    Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
        }
        if (entity.get("toDate") != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("uploadedTime"),
                    Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<?> search(LinkedHashMap<?, ?> entity, int pageNo, int resultPerPage) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UploadCertificate> cq = cb.createQuery(UploadCertificate.class);
        Root<UploadCertificate> root = cq.from(UploadCertificate.class);

        List<Predicate> predicates = new ArrayList<>();

        if (entity.get("fileName") != null) {
            predicates.add(cb.equal(root.get("fileName"), entity.get("fileName")));
        }
        if (entity.get("TAN") != null) {
            predicates.add(cb.equal(root.get("TAN"), entity.get("TAN")));
        }
        if (entity.get("fy") != null) {
            predicates.add(cb.equal(root.get("fy"), entity.get("fy")));
        }
        if (entity.get("quarter") != null) {
            predicates.add(cb.equal(root.get("quarter"), entity.get("quarter")));
        }
        if (entity.get("form") != null) {
            predicates.add(cb.equal(root.get("form"), entity.get("form")));
        }
        if (entity.get("fromDate") != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("uploadedTime"),
                    Date.from(ZonedDateTime.parse((String) entity.get("fromDate")).toInstant())));
        }
        if (entity.get("toDate") != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("uploadedTime"),
                    Date.from(ZonedDateTime.parse((String) entity.get("toDate")).toInstant())));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        return getEntityManager().createQuery(cq)
                .setFirstResult(pageNo * resultPerPage)
                .setMaxResults(resultPerPage)
                .getResultList();
    }
}

