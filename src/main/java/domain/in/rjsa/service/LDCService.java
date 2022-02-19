package domain.in.rjsa.service;

import domain.in.rjsa.model.tds.LDC;

public interface LDCService extends ServiceInterfaceTaxo<String, LDC>{
	public LDC getByKey(String tan);
}
