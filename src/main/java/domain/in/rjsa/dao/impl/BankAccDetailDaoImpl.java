package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.BankAccDetailDao;
import domain.in.rjsa.model.form.BankAccDetail;
@Repository("bankAccDetailDao")
public class BankAccDetailDaoImpl extends AbstractDaoForm<Long, BankAccDetail> implements BankAccDetailDao{

}
