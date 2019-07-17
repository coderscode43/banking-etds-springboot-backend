package domain.in.rjsa.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.ZonalBranches;
import domain.in.rjsa.model.form.Zone;
import domain.in.rjsa.model.wrapper.BranchDetailWrapper;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.ZonalBranchesService;
import domain.in.rjsa.service.ZoneService;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/apizone")
public class ZoneController extends AbstractController<Long, Zone, ZoneService> {
	private static final boolean String = false;
	@Autowired
	ZoneService service;
	@Autowired
	ApplicationCache applicationCache;

	@Autowired
	ZonalBranchesService zbService;

	@Autowired
	BranchService bservice; 

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
        Zone z=applicationCache.getZone(id);
		LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
	//	constrains.put("clientId", l.getClientId());
		constrains.put("zonalId", z.getId());
		List<Branch> branchs = new ArrayList<>();
		for (ZonalBranches zb : zbService.search(constrains,l.getClientId())) {
			branchs.add(applicationCache.getBranch(zb.getBranchId()));
		}
		ew.setBranchList(branchs);
		ew.setZone(z);
		Branch branch = bservice.getByKey(id);		
		ew.setBranch(branch);
		
		return ew;

	}

}
