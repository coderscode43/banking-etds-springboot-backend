package domain.in.rjsa.dao;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Zone;

public interface ZoneDao extends DaoInterface<Long, Zone>{
	public Zone getByKey(Long key) ;
}
