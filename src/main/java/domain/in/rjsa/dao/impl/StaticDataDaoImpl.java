package domain.in.rjsa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.model.form.StaticDataModel;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Transactional
@Repository("staticDataDao")
public class StaticDataDaoImpl extends AbstractDaoForm<Long, StaticDataModel> implements StaticDataDao{
	
	
	@Override
	public List<StaticDataModel> findall(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<StaticDataModel> cq = cb.createQuery(StaticDataModel.class);
	    Root<StaticDataModel> root = cq.from(StaticDataModel.class);

	    // Build predicates from the HashMap
	    List<Predicate> predicates = new ArrayList<>();
	    constrains.forEach((key, value) -> {
	        if (value != null) {
	            predicates.add(cb.equal(root.get(key), value));
	        }
	    });

	    cq.select(root)
	      .where(predicates.toArray(new Predicate[0]))
	      .orderBy(cb.desc(root.get("id")));

	    TypedQuery<StaticDataModel> query = getEntityManager().createQuery(cq);
	    query.setFirstResult(pageNo * resultPerPage);
	    query.setMaxResults(resultPerPage);

	    return query.getResultList();
	}


	@Override
	public List<StaticDataModel> searchExcel(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
