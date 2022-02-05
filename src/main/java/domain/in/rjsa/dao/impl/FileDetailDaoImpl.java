package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.FileDetailDao;
import domain.in.rjsa.model.form.FileDetail;
@Repository("fileDetailDao")
public class FileDetailDaoImpl extends AbstractDaoForm<Long, FileDetail> implements FileDetailDao{

}
