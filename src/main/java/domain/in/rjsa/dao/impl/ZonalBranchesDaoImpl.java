package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.ZonalBranchesDao;
import domain.in.rjsa.model.form.ZonalBranches;

@Repository("zonalBranchesDao")
public class ZonalBranchesDaoImpl extends AbstractNewDao<Long, ZonalBranches> implements ZonalBranchesDao{

}
