package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.RequestCorrectionDao;
import domain.in.rjsa.model.form.RequestCorrection;

@Repository("requestCorrectionDao")
public class RequestCorrectionDaoImpl extends AbstractNewDao<Long, RequestCorrection> implements RequestCorrectionDao{

}
