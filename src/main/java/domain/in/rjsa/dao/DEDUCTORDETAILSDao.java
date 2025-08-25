package domain.in.rjsa.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import domain.in.rjsa.model.tds.DEDUCTORDETAILS;

public interface DEDUCTORDETAILSDao extends DaoInterfaceTaxo<Long,  DEDUCTORDETAILS>{

	public DEDUCTORDETAILS getByKey(Long key) ;

	public Long findSearchCount(LinkedHashMap<String, Object> map);

	List<DEDUCTORDETAILS> search(HashMap<String, Object> entity, int pageNo, int noOfResult);

}
