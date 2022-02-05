package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoForm;
import domain.in.rjsa.dao.AdminDao;
import domain.in.rjsa.model.form.Admin;
@Repository("adminDao")
public class AdminDaoImpl extends AbstractDaoForm<Long, Admin> implements AdminDao{

}
