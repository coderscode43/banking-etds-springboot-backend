package domain.in.rjsa.service;

import domain.in.rjsa.model.tds.DEDUCTORDETAILS;

public interface DEDUCTORDETAILSService extends ServiceInterfaceTaxo<String, DEDUCTORDETAILS>{
	 public DEDUCTORDETAILS getByKey(String tan);
}
