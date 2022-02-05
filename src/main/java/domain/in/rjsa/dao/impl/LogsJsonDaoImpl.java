package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractDaoFY;
import domain.in.rjsa.dao.LogsJsonDao;
import domain.in.rjsa.model.fy.LogsJson;
@Repository("logsJsonDao")
public class LogsJsonDaoImpl extends AbstractDaoFY<Long, LogsJson> implements LogsJsonDao{

}
