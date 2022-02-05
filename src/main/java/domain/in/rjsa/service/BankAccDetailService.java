package domain.in.rjsa.service;

import domain.in.rjsa.model.form.BankAccDetail;

public interface BankAccDetailService extends ServiceInterfaceForm<Long,BankAccDetail>{

	BankAccDetail getByKey(Long bankId);

}
