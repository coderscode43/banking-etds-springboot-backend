package domain.in.rjsa.service;

import domain.in.rjsa.model.tds.TRACESSLOGIN;

public interface TRACESSLOGINService extends ServiceInterfaceTaxo<String,TRACESSLOGIN>{
	public TRACESSLOGIN getByKey(String tan);

}
