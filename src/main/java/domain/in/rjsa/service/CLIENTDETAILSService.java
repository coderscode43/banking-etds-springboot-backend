package domain.in.rjsa.service;

import domain.in.rjsa.model.tds.CLIENTDETAILS;

public interface CLIENTDETAILSService extends ServiceTDSInterface<String, CLIENTDETAILS>{
 public  CLIENTDETAILS getByKey(String tan);
 
 
 
}
