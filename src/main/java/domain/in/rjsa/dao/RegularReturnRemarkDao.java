package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.form.RegularReturnRemark;

public interface RegularReturnRemarkDao extends DaoInterfaceForm<Long,  RegularReturnRemark>{
	
	public List<RegularReturnRemark> findForm(HashMap<String,Object> constrains);

}
