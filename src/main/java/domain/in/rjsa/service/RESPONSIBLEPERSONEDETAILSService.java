package domain.in.rjsa.service;

import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;

public interface RESPONSIBLEPERSONEDETAILSService extends ServiceInterfaceTaxo<String, RESPONSIBLEPERSONEDETAILS>{
	 public RESPONSIBLEPERSONEDETAILS getByKey(String tan);

}
