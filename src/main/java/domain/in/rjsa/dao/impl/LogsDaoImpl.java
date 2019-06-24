package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.LogsDao;
import domain.in.rjsa.model.form.Logs;
@Repository("logsDao")
public class LogsDaoImpl extends AbstractNewDao<Long, Logs> implements LogsDao{

}
