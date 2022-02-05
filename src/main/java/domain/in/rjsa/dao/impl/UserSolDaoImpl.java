package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.UserSolDao;
import domain.in.rjsa.model.form.UserSol;

@Repository("userSolDao")
public class UserSolDaoImpl extends AbstractDaoForm<Long, UserSol> implements UserSolDao{

	
}
