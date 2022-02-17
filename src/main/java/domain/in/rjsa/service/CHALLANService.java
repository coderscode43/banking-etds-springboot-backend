package domain.in.rjsa.service;

import domain.in.rjsa.model.tds.CHALLAN;

public interface CHALLANService extends ServiceInterfaceTaxo<String, CHALLAN>{
	 public CHALLAN getByKey(String tan);
	 
}