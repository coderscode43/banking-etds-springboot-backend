package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.StaticDataModel;
import domain.in.rjsa.service.TicketService;
import domain.in.rjsa.util.StaticData;
import domain.in.rjsa.web.ApplicationCache;

@Controller
@RequestMapping("/index")
public class IndexController extends AbstractController {

	static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	ApplicationCache applicationCache;
	@Autowired
	private StaticDataDao dao;
	@Autowired
	private TicketService tcService;

	@RequestMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login.jsp";// You can redirect wherever you want, but generally it's a good practice to
										// show login screen again.
	}

	@RequestMapping(value = "/resetPass")
	public String resetPassword(ModelMap model) {
		String pageName = "resetPass";
		return pageName;
	}
	
	@RequestMapping(value = "/example-loader.tpl")
	public String getLoader(ModelMap model) {
		return "example-loader.tpl";
	}

	@RequestMapping(value = "/home")
	public String gethome(ModelMap model) {
		
		String branchCodeS = getBranchCode();
		boolean isAdmin = false;
		Long branchCode = 0L;
		if ("admin".equals(branchCodeS)) {
			logger.info(branchCodeS);
			isAdmin = true;
		} else {
			branchCode = Long.valueOf(branchCodeS);
			logger.info(branchCode.toString());
		}
		setStaticData();
		model.addAttribute("typeOfUser", getBranchCode());
		model.addAttribute("financialYear", StaticData.financialYear);
		model.addAttribute("Quarter", StaticData.Quarter);
		model.addAttribute("ClientName", StaticData.ClientName);
		model.addAttribute("ClientPAN", StaticData.ClientPAN);
		model.addAttribute("Tan", StaticData.Tan);
		model.addAttribute("Section", StaticData.Section);
		model.addAttribute("Form", StaticData.Form);
		// for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		if(isAdmin) {
			return "homeSC";
		}else {
			return "homeSCBranch";
		}
	}

	@RequestMapping(value = "/homePage")
	public String getHomePage(ModelMap model) {
		String branchCodeS = getBranchCode();
//		String branchCodeS = "101";
		model.addAttribute("typeOfUser", branchCodeS);
// 		for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		boolean isAdmin = false;
		Long branchCode = 0L;
		if ("admin".equals(branchCodeS)) {
			logger.info(branchCodeS);
			isAdmin = true;
		} else {
			branchCode = Long.valueOf(branchCodeS);
			logger.info(branchCode.toString());
		}
		Map<String, Long> ticketDetails = tcService.getStatusDetails(branchCode, isAdmin);
		if (ticketDetails.get("Open") != null) {
			model.addAttribute("openTicket", ticketDetails.get("Open"));
		}else {
			model.addAttribute("openTicket", "0");
		}
		if (ticketDetails.get("Resolve") != null) {
			model.addAttribute("resolveTicket", ticketDetails.get("Resolve"));
		}else {
			model.addAttribute("resolveTicket", "0");
		}
		if (ticketDetails.get("Reject") != null) {
			model.addAttribute("rejectTicket", ticketDetails.get("Reject"));
		}else {
			model.addAttribute("rejectTicket", "0");
		}
		
		if(isAdmin) {
			return "homeSC/homeSCHomepage";
		}else {
			//get branch Details based on branchCode.
			Branch branch = applicationCache.getBranch(branchCode);
			model.addAttribute("branchName", branch.getBranchName());
			model.addAttribute("branchCode", branchCode);
			return "homeSC/homeSCBranchHomepage";
		}
	}

	@RequestMapping(value = "/homeWOT/{branchCode}/{fy}")
	public String gethomeWOT(@PathVariable String fy, @PathVariable Long branchCode, ModelMap model) {
		setStaticData();
		model.addAttribute("typeOfUser", getBranchCode());
		model.addAttribute("financialYear", StaticData.financialYear);
		model.addAttribute("branchCode", branchCode.toString());
		model.addAttribute("Tan", StaticData.Tan);
		model.addAttribute("Section", StaticData.Section);
		model.addAttribute("Form", StaticData.Form);
		// for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		return "homeWOT";
	}

	@RequestMapping(value = "/homePageWOT")
	public String getHomePageWOT(ModelMap model) {
		model.addAttribute("typeOfUser", getBranchCode());
		// for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		return "homeWOT/homeWOTHomepage";
	}

	@RequestMapping(value = "/add/{action}/{page}")
	public String getAddPage(ModelMap model, @PathVariable String page, @PathVariable String action) {
		logger.info("Get add page for " + page);
		// add Branch State-pranay
		setStaticData();
		model.addAttribute("typeOfUser", getBranchCode());
		model.addAttribute("financialYear", StaticData.financialYear);
		model.addAttribute("Quarter", StaticData.Quarter);
		model.addAttribute("State", StaticData.State);
		model.addAttribute("Form", StaticData.Form);
		// for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		return sendPage(action, page);
	}

	@RequestMapping(value = "/detail/{action}/{page}")
	public String getPage(@PathVariable String action, @PathVariable String page, ModelMap model) {
		setStaticData();
		model.addAttribute("typeOfUser", getBranchCode());
		model.addAttribute("financialYear", StaticData.financialYear);
		model.addAttribute("Quarter", StaticData.Quarter);
		model.addAttribute("State", StaticData.State);
		model.addAttribute("Tan", StaticData.Tan);
		model.addAttribute("Form", StaticData.Form);
		// for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		return sendPage(action, page);
	}

	@RequestMapping(value = "/list/{action}/{page}")
	public String getListPage(@PathVariable String action, @PathVariable String page, ModelMap model) {
		setStaticData();
		model.addAttribute("typeOfUser", getBranchCode());
		model.addAttribute("financialYear", StaticData.financialYear);
		model.addAttribute("Quarter", StaticData.Quarter);
		model.addAttribute("typeOfDeductee", StaticData.typeOfDeductee);
		model.addAttribute("typeOfCertificate", StaticData.typeOfCertificate);
		model.addAttribute("Month", StaticData.Month);
		model.addAttribute("Tan", StaticData.Tan);
		model.addAttribute("Section", StaticData.Section);
		model.addAttribute("Form", StaticData.Form);
//		model.addAttribute("TanDB", applicationCache.getTanDB());
		// for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		return sendPage(action, page);
	}

	public String sendPage(String action, String page) {
		if (getBranchCode().contains("admin")) {
			return action + "/" + page;
		} else {
			if (page.contains("branch") || page.contains("DetailsBranch") || action.contains("homeWOT")) {
				return action + "/" + page;
			}
			return "homeSC/homeSCHomepage";
		}
	}

	private void setStaticData() {
		if (StaticData.ClientName == null) {
			HashMap<String, Object> sd = new HashMap<String, Object>();
			List<StaticDataModel> list = dao.findall(sd, 0, 100);
			String[] stringArray;
			String xString;
			for (StaticDataModel list1 : list) {
				String key = list1.getKey();
				switch (key) {
				case "financialYear":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.financialYear = stringArray;
					break;
				case "typeOfDeductee":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.typeOfDeductee = stringArray;
					break;
				case "typeOfCertificate":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.typeOfCertificate = stringArray;
					break;
				case "Quarter":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.Quarter = stringArray;
					break;
				case "path":
//					xString = list1.getValue();
//					stringArray = xString.split(",");
//					model.addAttribute("path", stringArray);
					xString = list1.getValue();
					StaticData.path = xString;
					break;
				case "State":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.State = stringArray;
					break;
				case "Month":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.Month = stringArray;
					break;
				case "Client":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.ClientName = stringArray[0];
					StaticData.ClientPAN = stringArray[1];
					break;
				case "ChallanMismatch":
					xString = list1.getValue();
					stringArray = xString.split(",");
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "Tan":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.Tan = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "Section":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.Section = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "CertificatePath":
					xString = list1.getValue();
					StaticData.CertificatePath = xString;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "Form":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.Form = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "directDownload":
					xString = list1.getValue();
					StaticData.directDownloadAuth = xString;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;

				default:
					System.out.println("Not Match");
					break;
				}
			}
		}
	}
}
