package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.EFILLINGLOGINDao;
import domain.in.rjsa.model.tds.EFILLINGLOGIN;

@Repository("EFILLINGLOGINDao")
public class EFILLINGLOGINDaoImpl extends AbstractTDSDao<String, EFILLINGLOGIN> implements EFILLINGLOGINDao{

}
