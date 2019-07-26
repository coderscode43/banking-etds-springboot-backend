package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.LogsJsonDao;
import domain.in.rjsa.model.form.LogsJson;
@Repository("logsJsonDao")
public class LogsJsonDaoImpl extends AbstractNewDao<Long, LogsJson> implements LogsJsonDao{

}
