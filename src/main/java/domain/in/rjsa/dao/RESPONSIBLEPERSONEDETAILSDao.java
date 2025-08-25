package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.List;

import domain.in.rjsa.model.tds.RESPONSIBLEPERSONEDETAILS;

public interface RESPONSIBLEPERSONEDETAILSDao extends DaoInterfaceTaxo<String,  RESPONSIBLEPERSONEDETAILS>{
	List<RESPONSIBLEPERSONEDETAILS> search(HashMap<String, Object> entity, int pageNo, int noOfResult);
}
