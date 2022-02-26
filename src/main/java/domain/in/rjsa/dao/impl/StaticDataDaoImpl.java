package domain.in.rjsa.dao.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.model.form.StaticData;

@Transactional
@Repository("staticDataDao")
public class StaticDataDaoImpl extends AbstractDaoForm<Long, StaticData> implements StaticDataDao{
	
	
	@SuppressWarnings("unchecked")
	public List<StaticData> findall(HashMap<String, Object> constrains, int pageNo, int resultPerPage) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		criteria.add(Restrictions.allEq(constrains));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(pageNo * resultPerPage);
		criteria.setMaxResults(resultPerPage);
		return (List<StaticData>) criteria.list();
	}
	

}
