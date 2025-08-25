package domain.in.rjsa.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.model.form.Branch;

@Repository("branchDao")
public class BranchDaoImpl extends AbstractDaoForm<Long, Branch> implements BranchDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Branch> search(LinkedHashMap<String, Object> filters, int pageNo, int noOfResult) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Branch> cq = cb.createQuery(Branch.class);
        Root<Branch> root = cq.from(Branch.class);

        Predicate predicate = buildPredicate(cb, root, filters);
        cq.where(predicate);
        cq.orderBy(cb.desc(root.get("id")));

        return entityManager.createQuery(cq)
                .setFirstResult(pageNo * noOfResult)
                .setMaxResults(noOfResult)
                .getResultList();
    }

    @Override
    public Long findSearchCount(LinkedHashMap<String, Object> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Branch> root = cq.from(Branch.class);

        Predicate predicate = buildPredicate(cb, root, filters);
        cq.select(cb.count(root)).where(predicate);

        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public Branch getByKey(Long key) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Branch> cq = cb.createQuery(Branch.class);
        Root<Branch> root = cq.from(Branch.class);

        cq.where(cb.equal(root.get("branchCode"), key));
        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate buildPredicate(CriteriaBuilder cb, Root<Branch> root, Map<String, Object> filters) {
        Predicate predicate = cb.conjunction();

        if (filters.get("roCode") != null)
            predicate = cb.and(predicate, cb.equal(root.get("roCode"), filters.get("roCode").toString()));
        if (filters.get("branchName") != null)
            predicate = cb.and(predicate, cb.equal(root.get("branchName"), filters.get("branchName").toString()));
        if (filters.get("branchCode") != null)
            predicate = cb.and(predicate, cb.equal(root.get("branchCode"),
                    Long.valueOf(filters.get("branchCode").toString())));
        if (filters.get("branchState") != null)
            predicate = cb.and(predicate, cb.equal(root.get("branchState"), filters.get("branchState").toString()));

        return predicate;
    }

}