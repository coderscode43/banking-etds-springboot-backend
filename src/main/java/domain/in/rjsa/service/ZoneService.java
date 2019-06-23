package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Zone;

public interface ZoneService extends ServiceInterface<Long,Zone>{

	Zone getByKey(Long id);

}
