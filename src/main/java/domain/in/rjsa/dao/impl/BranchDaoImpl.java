package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.BranchDao;
import domain.in.rjsa.model.form.Branch;

@Repository("branchDao")
public class BranchDaoImpl extends AbstractNewDao<Long, Branch> implements BranchDao{

}
