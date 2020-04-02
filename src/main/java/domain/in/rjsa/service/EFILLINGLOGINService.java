package domain.in.rjsa.service;

import domain.in.rjsa.model.tds.EFILLINGLOGIN;

public interface EFILLINGLOGINService extends ServiceTDSInterface<String,EFILLINGLOGIN>{
	public EFILLINGLOGIN getByKey(String tan);

}
