package domain.in.rjsa.dao.impl;

import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.AbstractNewDao;
import domain.in.rjsa.dao.ZoneDao;
import domain.in.rjsa.model.form.Zone;

@Repository("zoneDao")
public class ZoneDaoImpl extends AbstractNewDao<Long, Zone> implements ZoneDao{

}
