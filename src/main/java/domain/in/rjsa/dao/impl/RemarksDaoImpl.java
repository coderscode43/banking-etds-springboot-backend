package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.RemarksDao;
import domain.in.rjsa.model.form.Remarks;

@Repository("remarksDao")
public class RemarksDaoImpl extends AbstractNewDao<Long, Remarks> implements RemarksDao{

}
