package domain.in.rjsa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.in.rjsa.dao.StaticDataDao;
import domain.in.rjsa.model.form.Branch;
import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.StaticDataModel;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.CorrectionRequestService;
import domain.in.rjsa.util.StaticData;
import domain.in.rjsa.web.ApplicationCache;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/index")
public class IndexController extends AbstractController {

	static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	ApplicationCache applicationCache;
	@Autowired
	private StaticDataDao dao;
	@Autowired
	private CorrectionRequestService crService;
	@Autowired
	private BranchService brService;
	
	@Value("${panel.access:}")
	private String panelAccess;

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
		setStaticData();
		setModel(model);

		String branchCodeS = getBranchCode();

		if ("branch16A".equals(branchCodeS)) {
			addAttributes(model);
			return "homeWOT";
		} else {
			boolean isAdmin = false;
			Long branchCode = 0L;
			if ("admin".equals(branchCodeS)) {
				logger.info(branchCodeS);
				isAdmin = true;
			} else {
				branchCode = Long.valueOf(branchCodeS);
				logger.info(branchCode.toString());
			}
			if (isAdmin) {
				return "homeSC";
			} else {
				return "homeSCBranch";
			}
		}
	}
	
	public void addAttributes(ModelMap model) {
		Login login = applicationCache.getLoginDetail(getPrincipal());
		Long branchCode = login.getBranchCode();
		Branch branch = applicationCache.getBranch(branchCode);
		model.addAttribute("branchName", branch.getBranchName());
		model.addAttribute("branchCode", String.valueOf(Integer.parseInt(branchCode.toString())));
		model.addAttribute("fy", "2024-25");
	}

	@RequestMapping(value = "/homePage")
	public String getHomePage(ModelMap model) {
		String branchCodeS = getBranchCode();
//		String branchCodeS = "101";
		model.addAttribute("typeOfUser", branchCodeS);
		model.addAttribute("latestFy", StaticData.latestFy);
// 		for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		boolean isAdmin = false;
		Long branchCode = 0L;

		if ("branch16A".equals(branchCodeS)) {
			setStaticData();
			setModel(model);
			addAttributes(model);
			return "homeWOT/downloadCertificate16A";
		} else if ("admin".equals(branchCodeS)) {
			logger.info(branchCodeS);
			isAdmin = true;
		} else {
			branchCode = Long.valueOf(branchCodeS);
			logger.info(branchCode.toString());
		}
//		Map<String, Long> ticketDetails = tcService.getStatusDetails(branchCode, isAdmin);
//		if (ticketDetails.get("Open") != null) {
//			model.addAttribute("openTicket", ticketDetails.get("Open"));
//		} else {
//			model.addAttribute("openTicket", "0");
//		}
//		if (ticketDetails.get("Resolve") != null) {
//			model.addAttribute("resolveTicket", ticketDetails.get("Resolve"));
//		} else {
//			model.addAttribute("resolveTicket", "0");
//		}
//		if (ticketDetails.get("Reject") != null) {
//			model.addAttribute("rejectTicket", ticketDetails.get("Reject"));
//		} else {
//			model.addAttribute("rejectTicket", "0");
//		}

		Map<String, Long> correctionDetails = crService.getStatusDetails(branchCode, isAdmin);
		Long totalCount = 0L;
		if (correctionDetails.get("Pending Checker Approval") != null) {
			model.addAttribute("pendingCheckerApproval", correctionDetails.get("Pending Checker Approval"));
			totalCount += correctionDetails.get("Pending Checker Approval");
		} else {
			model.addAttribute("pendingCheckerApproval", "0");
		}
		if (correctionDetails.get("Sent for Clarification") != null) {
			model.addAttribute("sendForClarification", correctionDetails.get("Sent for Clarification"));
			totalCount += correctionDetails.get("Sent for Clarification");
		} else {
			model.addAttribute("sendForClarification", "0");
		}
		if (correctionDetails.get("Correction Checked") != null) {
			model.addAttribute("correctionChecked", correctionDetails.get("Correction Checked"));
			totalCount += correctionDetails.get("Correction Checked");
		} else {
			model.addAttribute("correctionChecked", "0");
		}
		if (correctionDetails.get("Sent for Correction") != null) {
			model.addAttribute("sendForCorrection", correctionDetails.get("Sent for Correction"));
			totalCount += correctionDetails.get("Sent for Correction");
		} else {
			model.addAttribute("sendForCorrection", "0");
		}
		if (correctionDetails.get("Resolved") != null) {
			model.addAttribute("resolveRequest", correctionDetails.get("Resolved"));
			totalCount += correctionDetails.get("Resolved");
		} else {
			model.addAttribute("resolveRequest", "0");
		}
		if (correctionDetails.get("Rejected") != null) {
			model.addAttribute("rejectRequest", correctionDetails.get("Rejected"));
			totalCount += correctionDetails.get("Rejected");
		} else {
			model.addAttribute("rejectRequest", "0");
		}
		if (!correctionDetails.isEmpty()) {
			model.addAttribute("totalCount", totalCount);
		} else {
			model.addAttribute("totalCount", "0");
		}

		if (isAdmin) {
			return "homeSC/homeSCHomepage";
		} else {
			// get branch Details based on branchCode.
			Branch branch = applicationCache.getBranch(branchCode);
			model.addAttribute("branchName", branch.getBranchName());
			model.addAttribute("branchCode", String.valueOf(Integer.parseInt(branchCode.toString())));
			return "homeSC/homeSCBranchHomepage";
		}
	}

	@RequestMapping(value = "/homeWOT/{branchCode}/{fy}")
	public String gethomeWOT(@PathVariable String fy, @PathVariable Long branchCode, ModelMap model) {
		setStaticData();
		setModel(model);
		Branch branch = applicationCache.getBranch(branchCode);
		model.addAttribute("branchName", branch.getBranchName());
		model.addAttribute("branchCode", String.valueOf(Integer.parseInt(branchCode.toString())));
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
		setModel(model);
		if (!getBranchCode().equalsIgnoreCase("admin")) {
			Branch b = brService.getByKey(Long.valueOf(getBranchCode()));
			model.addAttribute("tan", b.getTan());
		} else {
			model.addAttribute("tan", "");
		}
		return sendPage(action, page);
	}

	@RequestMapping(value = "/detail/{action}/{page}")
	public String getPage(@PathVariable String action, @PathVariable String page, ModelMap model) {
		setStaticData();
		setModel(model);
		return sendPage(action, page);
	}

	@RequestMapping(value = "/list/{action}/{page}")
	public String getListPage(@PathVariable String action, @PathVariable String page, ModelMap model) {
		setStaticData();
		setModel(model);
		return sendPage(action, page);
	}

	@RequestMapping(value = "/downloadCertificate/{branchCode}/{action}/{page}")
	public String getDownloadCertificate(@PathVariable Long branchCode, @PathVariable String action,
			@PathVariable String page, ModelMap model) {
		setStaticData();
		setModel(model);
		// get branch Details based on branchCode.
		Branch branch = applicationCache.getBranch(branchCode);
		model.addAttribute("branchName", branch.getBranchName());
		model.addAttribute("branchCode", String.valueOf(Integer.parseInt(branchCode.toString())));
		return sendPage(action, page);
	}

	private void setModel(ModelMap model) {
		model.addAttribute("userName", getPrincipal());
		model.addAttribute("typeOfUser", getBranchCode());
		model.addAttribute("latestFy", StaticData.latestFy);
		model.addAttribute("financialYear", StaticData.financialYear);
		model.addAttribute("Quarter", StaticData.Quarter);
		model.addAttribute("typeOfDeductee", StaticData.typeOfDeductee);
		model.addAttribute("typeOfCertificate", StaticData.typeOfCertificate);
		model.addAttribute("Month", StaticData.Month);
		model.addAttribute("Tan", StaticData.Tan);
		model.addAttribute("Section", StaticData.Section);
		model.addAttribute("Form", StaticData.Form);
		model.addAttribute("status", StaticData.status);
		model.addAttribute("typeOfCorrection", StaticData.typeOfCorrection);
		model.addAttribute("typeOfForm", StaticData.typeOfForm);
		model.addAttribute("regularReturnStatus", StaticData.regularReturnStatus);
		model.addAttribute("exemption", StaticData.exemption);
		model.addAttribute("typeOfReport", StaticData.typeOfReport);
		model.addAttribute("ClientName", StaticData.ClientName);
		model.addAttribute("ClientPAN", StaticData.ClientPAN);
		model.addAttribute("State", StaticData.State);
		// for Dashboard
		model.addAttribute("statementStatus", applicationCache.getStatementStatus());
		
		model.addAttribute("panelAccess", panelAccess);

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
					stringArray = xString.split(Pattern.quote("^"));
					StaticData.Section = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "CertificatePath":
					xString = list1.getValue();
					StaticData.CertificatePath = xString;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "latestFy":
					xString = list1.getValue();
					StaticData.latestFy = xString;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "taxAuditReportPath":
					xString = list1.getValue();
					StaticData.TaxAuditReportPath = xString;
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
				case "command":
					xString = list1.getValue();
					StaticData.command = xString;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "status":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.status = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "typeOfCorrection":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.typeOfCorrection = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "typeOfForm":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.typeOfForm = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "documentSave":
					xString = list1.getValue();
					StaticData.documentSave = xString;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "regularReturnStatus":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.regularReturnStatus = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "exemption":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.exemption = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "typeOfReport":
					xString = list1.getValue();
					stringArray = xString.split(",");
					StaticData.typeOfReport = stringArray;
					// model.addAttribute("ChallanMismatch", stringArray);
					break;
				case "annualReportPath":
					xString = list1.getValue();
					StaticData.annualReportPath = xString;
					break;
				default:
					System.out.println("Not Match");
					break;
				}
			}
		}
	}

    @GetMapping("/staticData")
    public ResponseEntity<Map<String, Object>> staticData() {
        // Set static data at the start of the method
        setStaticData();

        Map<String, Object> map = new HashMap<>();
        map.put("latestFy", StaticData.latestFy);
        map.put("financialYear", StaticData.financialYear);
        map.put("Quarter", StaticData.Quarter);
        map.put("typeOfDeductee", StaticData.typeOfDeductee);
        map.put("typeOfCertificate", StaticData.typeOfCertificate);
        map.put("Month", StaticData.Month);
        map.put("Tan", StaticData.Tan);
        map.put("Section", StaticData.Section);
        map.put("Form", StaticData.Form);
        map.put("status", StaticData.status);
        map.put("typeOfCorrection", StaticData.typeOfCorrection);
        map.put("typeOfForm", StaticData.typeOfForm);
        map.put("regularReturnStatus", StaticData.regularReturnStatus);
        map.put("exemption", StaticData.exemption);
        map.put("typeOfReport", StaticData.typeOfReport);
        map.put("State", StaticData.State);
        map.put("statementStatus", applicationCache.getStatementStatus());

        // User Details
        Map<String, Object> userDetailsMap = new HashMap<>();
        userDetailsMap.put("userName", getPrincipal());
        userDetailsMap.put("typeOfUser", getBranchCode());
        map.put("userDetails", userDetailsMap);

        // Client Details
        Map<String, Object> clientDetailsMap = new HashMap<>();
        clientDetailsMap.put("ClientName", StaticData.ClientName);
        clientDetailsMap.put("ClientPAN", StaticData.ClientPAN);
        clientDetailsMap.put("panelAccess", panelAccess);
        map.put("clientDetails", clientDetailsMap);

        return ResponseEntity.ok(map);
    }

	@RequestMapping(value = "/HomehelpSC")
	public String getHelpHomePageSC() {
		return "homeHelpSC";
	}

	@RequestMapping(value = "/HomehelpWOT")
	public String getHelpHomePageWOT() {
		return "homeHelpWOT";
	}

	@RequestMapping(value = "/help/{helpFolder}/{helpPage}")
	public String getHelpPage(@PathVariable String helpFolder, @PathVariable String helpPage, ModelMap model) {
		return "helpPage/" + helpFolder + "/" + helpPage;
	}

}
