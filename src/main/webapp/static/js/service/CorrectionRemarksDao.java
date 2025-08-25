package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.form.CorrectionRemarks;

public interface CorrectionRemarksDao extends DaoInterfaceForm<Long,  CorrectionRemarks>{
	
	public List<CorrectionRemarks> findForm(HashMap<String,Object> constrains);

}
