package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.BLGDao;
import domain.in.rjsa.model.form.BLG;
@Repository("blgDao")
public class BLGDaoImpl extends AbstractNewDao<Long, BLG> implements BLGDao{

}
