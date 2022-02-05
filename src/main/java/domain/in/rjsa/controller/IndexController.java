package domain.in.rjsa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.model.form.OrganizationDetails;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.UserSol;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/index")
public class IndexController {

	static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	ApplicationCache applicationCache;

	@RequestMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login.jsp";// You can redirect wherever you want, but generally it's a good practice to
										// show login screen again.
	}

	// mainPage
	@RequestMapping(value = "/{page}")
	public String getSelectCompanyTemplate(@PathVariable String page, ModelMap model) {
		if (page.equals("main")) {
			String userName = getPrincipal();
			Login login = applicationCache.getLoginDetail(userName);
			OrganizationDetails cd = applicationCache.getOrganizationDetails(login.getClientId());
			model.addAttribute("cd", cd);
			if (login.getPasswordReset() == null || login.getPasswordReset()) {
				return "firstLogin";
			}
		}
		return page;
	}

	@RequestMapping(value = "/resetPass")
	public String resetPassword(ModelMap model) {
		String pageName = "resetPass";
		return pageName;
	}

	@RequestMapping(value = "/home/{clientId}/{action}/{branchId}")
	public String gethome(@PathVariable Long clientId, @PathVariable String action, @PathVariable String branchId,
			ModelMap model) {
		String userName = getPrincipal();
		Login login = applicationCache.getLoginDetail(userName);
		/*
		 * if(branchId!=null) {
		 * 
		 * try { Long bId = Long.valueOf(branchId); Branch br =
		 * applicationCache.getBranch(bId); model.addAttribute("br", br);
		 * }catch(Exception e) { model.addAttribute("br", new Branch()); } ClientDetail
		 * cd = applicationCache.getClientDetail(login.getClientId());
		 * model.addAttribute("cd", cd);
		 * 
		 * }
		 */

		OrganizationDetails cd = applicationCache.getOrganizationDetails(login.getClientId());
		UserSol us = applicationCache.getUserSol(login.getId(), cd.getId());
		List<Long> listSolId = new ArrayList<>();
		for (String sol : us.getSolId().split("-")) {
			listSolId.add(Long.valueOf(sol));
		}
		model.addAttribute("cd", cd);
		model.addAttribute("listBr", listSolId);

		return action;
//		String userName =getPrincipal();
//		Login login =applicationCache.getLoginDetail(userName);
//		Branch br = applicationCache.getClientDetail(login.getClientId());
//		model.addAttribute("cd", cd);	
//		model.addAttribute("employeeId", login.getEmployeeId());
//		model.addAttribute("userName", login.getUserName());
//		
//		String pageName="Home";
//		return action+"/"+action+pageName;
	}

	@RequestMapping(value = "/homePage/{clientId}/{action}")
	public String getHomePage(@PathVariable Long clientId, @PathVariable String action, ModelMap model) {
		String pageName = "HomePage";
//		Login l = applicationCache.getLoginDetail(getPrincipal());
//		
//		{
//		HashMap<String, Object>constrains= new HashMap<>();
//		constrains.put("clientId",l.getClientId());
//		DeclarationTotalCount dtc=dtcs.uniqueSearch(constrains);
//		if(dtc!=null)
//		{
//			model.addAttribute("totalEmpDeclaration",dtc.getTotalEmpDeclaration());
//			model.addAttribute("totalApprovedEmpDeclaration",dtc.getTotalApprovedEmpDeclaration());
//			model.addAttribute("totalPendingEmpDeclaration",dtc.getTotalPendingEmpDeclaration());
//			model.addAttribute("totalRejectedEmpDeclaration",dtc.getTotalRejectedEmpDeclaration());
//			model.addAttribute("totalEmpReimbursement",dtc.getTotalEmpReimbursement());
//			model.addAttribute("totalPendingEmpReimbursement",dtc.getTotalPendingEmpReimbursement());
//			model.addAttribute("totalRejectedEmpReimbursement",dtc.getTotalRejectedEmpReimbursement());
//		}
//		else {
//			model.addAttribute("totalEmpDeclaration",0L);
//			model.addAttribute("totalApprovedEmpDeclaration",0L);
//			model.addAttribute("totalPendingEmpDeclaration",0L);
//			model.addAttribute("totalRejectedEmpDeclaration",0L);
//			model.addAttribute("totalEmpReimbursement",0L);
//			model.addAttribute("totalPendingEmpReimbursement",0L);
//			model.addAttribute("totalRejectedEmpReimbursement",0L);
//			
//		}
//		}
//		
//		HashMap<String, Object>constrains= new HashMap<>();
//		constrains.put("clientId",l.getClientId());
//		constrains.put("employeeId",l.getEmployeeId());
//		DeclarationCount dc=dcs.uniqueSearch(constrains);
//		if(dc!=null)
//		{
//			model.addAttribute("totalDeclaration",dc.getTotalDeclaration());
//			model.addAttribute("totalApprovedDeclaration",dc.getTotalApprovedDeclaration());
//			model.addAttribute("totalPendingDeclaration",dc.getTotalPendingDeclaration());
//			model.addAttribute("totalRejectedDeclaration",dc.getTotalRejectedDeclaration());
//			model.addAttribute("totalReimbursement",dc.getTotalReimbursement());
//			model.addAttribute("totalPendingReimbursement",dc.getTotalPendingReimbursement());
//			model.addAttribute("totalRejectedReimbursement",dc.getTotalRejectedReimbursement());
//		}
//		else {
//			model.addAttribute("totalDeclaration",0L);
//			model.addAttribute("totalApprovedDeclaration",0L);
//			model.addAttribute("totalPendingDeclaration",0L);
//			model.addAttribute("totalRejectedDeclaration",0L);
//			model.addAttribute("totalReimbursement",0L);
//			model.addAttribute("totalPendingReimbursement",0L);
//			model.addAttribute("totalRejectedReimbursement",0L);
//			
//		}
//		model.addAttribute("impDates",applicationCache.getImpDates());
//		model.addAttribute("recentNotifications",applicationCache.getRecentNotifications(clientId));
//		model.addAttribute("recentRemark",applicationCache.getRecentRemark(clientId));

		return action + "/" + action + pageName;
		// return action+"/"+action+pageName;
	}

	@RequestMapping(value = "/detail/{clientId}/{action}/{page}")
	public String getPage(@PathVariable Long clientId, @PathVariable String action, @PathVariable String page,
			ModelMap model) {
		return action + "/" + page;
	}

	@RequestMapping(value = "/list/{clientId}/{action}/{page}")
	public String getListPage(@PathVariable Long clientId, @PathVariable String action, @PathVariable String page,
			ModelMap model) {
		Login login = applicationCache.getLoginDetail(getPrincipal());
		OrganizationDetails cd = applicationCache.getOrganizationDetails(login.getClientId());
		UserSol us = applicationCache.getUserSol(login.getId(), cd.getId());
		List<Long> listSolId = new ArrayList<>();
		for (String sol : us.getSolId().split("-")) {
			listSolId.add(Long.valueOf(sol));
		}
		model.addAttribute("cd", cd);
		model.addAttribute("listBr", listSolId);
		return action + "/" + page;
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;

	}

}
