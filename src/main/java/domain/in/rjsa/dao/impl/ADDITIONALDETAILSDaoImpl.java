package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.ADDITIONALDETAILSDao;
import domain.in.rjsa.dao.AbstractDaoTaxo;
import domain.in.rjsa.model.tds.ADDITIONALDETAILS;

@Repository("ADDITIONALDETAILSDao")
public class ADDITIONALDETAILSDaoImpl extends AbstractDaoTaxo<String, ADDITIONALDETAILS>
implements ADDITIONALDETAILSDao{

}
