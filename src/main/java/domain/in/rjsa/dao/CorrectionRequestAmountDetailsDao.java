package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;

public interface CorrectionRequestAmountDetailsDao extends DaoInterfaceForm<Long,  CorrectionRequestAmountDetails>{
	public List<CorrectionRequestAmountDetails> findForm(HashMap<String,Object> constrains);
}
