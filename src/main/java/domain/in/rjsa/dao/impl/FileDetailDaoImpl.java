package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.FileDetailDao;
import domain.in.rjsa.model.fy.FileDetail;
@Repository("fileDetailDao")
public class FileDetailDaoImpl extends AbstractTDSDao<Long, FileDetail> implements FileDetailDao{

}
