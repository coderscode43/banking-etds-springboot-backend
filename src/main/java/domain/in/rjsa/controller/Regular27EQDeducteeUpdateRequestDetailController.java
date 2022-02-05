package domain.in.rjsa.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.fy.Regular27EQDeducteeUpdateRequestDetail;
import domain.in.rjsa.model.wrapper.Regular27EQDeducteeUpdateRequestDetailWrapper;
import domain.in.rjsa.service.Regular27EQDeducteeService;
import domain.in.rjsa.service.Regular27EQDeducteeUpdateRequestDetailService;
import domain.in.rjsa.service.RemarksService;
import domain.in.rjsa.util.StaticData;

@Controller
@RequestMapping("/apiform27EQDeducteeUpdateRequestDetail")
public class Regular27EQDeducteeUpdateRequestDetailController extends
AbstractBranchControllerFY<Long, Regular27EQDeducteeUpdateRequestDetail, Regular27EQDeducteeUpdateRequestDetailService>{

	@Autowired
	Regular27EQDeducteeUpdateRequestDetailService service;
	@Autowired
	Regular27EQDeducteeService dService;
	@Autowired
	RemarksService rService;
	
	@Override
	public Class<Regular27EQDeducteeUpdateRequestDetail> getEntity() {
		// TODO Auto-generated method stub
		return Regular27EQDeducteeUpdateRequestDetail.class;
	}

	@Override
	public Regular27EQDeducteeUpdateRequestDetailService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	

	public Regular27EQDeducteeUpdateRequestDetailWrapper getDetail(Long id, Long clientId) {
		// TODO Auto-generated method stub
		Regular27EQDeducteeUpdateRequestDetailWrapper data = new Regular27EQDeducteeUpdateRequestDetailWrapper();
		HashMap<String, Object> constrains = new HashMap<>();
		constrains.put("id", id);
		constrains.put("clientId", clientId);
		data.setNeData(getService().uniqueSearch(constrains));
		data.setOldData(dService.getByKey(data.getNeData().getId()));
		constrains.remove("id");
		constrains.put("entityId", data.getNeData().getId());
		constrains.put("entity", StaticData.entity.get(1));
		data.setListRemarks(rService.search(constrains));
		return data;
	}
}
