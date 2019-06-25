package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.BankAccDetailDao;
import domain.in.rjsa.model.form.BankAccDetail;
@Repository("bankAccDetailDao")
public class BankAccDetailDaoImpl extends AbstractNewDao<Long, BankAccDetail> implements BankAccDetailDao{

}
