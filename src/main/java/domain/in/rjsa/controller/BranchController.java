package domain.in.rjsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.service.BranchService;

@Controller
@RequestMapping("/apibranch")
public class BranchController extends AbstractController<Long, Branch, BranchService>{
	
	@Autowired
	BranchService service;
	@Override
	public BranchService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<Branch> getEntity() {
		// TODO Auto-generated method stub
		return Branch.class;
	}
	// ------------------- Other Methods ---------------------------------

//		public Object getDetail(Long id, Long clientId) {
//			BranchDetailWrapper bw =new BranchDetailWrapper();
//			Login l = applicationCache.getLoginDetail(getPrincipal());
//			LinkedHashMap<String, Object> constrains = new LinkedHashMap<>();
//			constrains.put("id", id);
//			constrains.put("clientId", l.getClientId());
//			bw.branch = getService().uniqueSearch(constrains);
//				constrains.put("employeeId", bw.branch.getId());
//				constrains.remove("id");
//				bw.branchList = service.search(constrains);
//				return bw;
//			
//		
//			
//		}

}
