package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractTDSDao;
import domain.in.rjsa.dao.CLIENTDETAILSDao;
import domain.in.rjsa.model.tds.CLIENTDETAILS;
@Repository("CLIENTDETAILSDao")
public class CLIENTDETAILSDaoImpl extends AbstractTDSDao<String, CLIENTDETAILS> implements CLIENTDETAILSDao{

}
