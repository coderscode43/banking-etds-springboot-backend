package domain.in.rjsa.service;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.form.RegularReturn;

public interface RegularReturnService extends ServiceInterfaceForm<Long, RegularReturn> {

	Object getDetail(HashMap<String, Object> constrains, Long id, String principal, String roCode);

	public List<?> getList(HashMap<String, Object> constrains, int pageNo, int resultPerPage);

	Long findallCountwithBranch(HashMap<String, Object> constrains);

	public void addRegularReturn(RegularReturn rr, String userName);

}
