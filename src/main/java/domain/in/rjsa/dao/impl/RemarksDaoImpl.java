package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.RemarksDao;
import domain.in.rjsa.model.fy.Remarks;

@Repository("remarksDao")
public class RemarksDaoImpl extends AbstractDaoFY<Long, Remarks> implements RemarksDao{

}
