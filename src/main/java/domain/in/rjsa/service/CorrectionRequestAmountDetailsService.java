package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;

public interface CorrectionRequestAmountDetailsService extends ServiceInterfaceForm<Long, CorrectionRequestAmountDetails> {

	public void saveRemark(LinkedHashMap<String, Object> entity);

	public void setResolve(CorrectionRequestAmountDetails remark, String type);
	
	public List<CorrectionRequestAmountDetails> findForm(HashMap<String, Object> constrains);
}
