package domain.in.rjsa.service;

import domain.in.rjsa.model.tds.GOVERNMENTDETAILS;

public interface GOVERNMENTDETAILSService extends ServiceTDSInterface<String, GOVERNMENTDETAILS>{
	 public GOVERNMENTDETAILS getByKey(String tan);

}
