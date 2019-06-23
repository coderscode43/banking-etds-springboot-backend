package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDao;
import domain.in.rjsa.dao.ClientDetailDao;
import domain.in.rjsa.model.form.ClientDetail;
@Repository("clientDetailDao")
public class ClientDetailDaoImpl  extends AbstractDao<Long, ClientDetail> implements ClientDetailDao {
	
}
