package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.Zone;
import domain.in.rjsa.model.wrapper.BranchDetailWrapper;
import domain.in.rjsa.service.ZonalBranchesService;
import domain.in.rjsa.service.ZoneService;

@Controller
@RequestMapping("/apizone")
public class ZoneController extends AbstractController<Long, Zone, ZoneService>{
 @Autowired
 ZoneService service;
 @Autowired
 ZonalBranchesService zbService;
	@Override
	public ZoneService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Zone> getEntity() {
		// TODO Auto-generated method stub
		return Zone.class;
	}
	
	@Override
	public Object getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		BranchDetailWrapper ew = new BranchDetailWrapper();
		Login l = applicationCache.getLoginDetail(getPrincipal());
		LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
		constrains.put("id", id);
		constrains.put("clientId", l.getClientId());
			ew.zone = getService().uniqueSearch(constrains);
			constrains.put("zoneCode", ew.zone.getZoneCode());
			constrains.remove("id");
			ew.zonalBranches = zbService.search(constrains);
			return ew;
		
	}
	

}
