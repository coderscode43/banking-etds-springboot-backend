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

import domain.in.rjsa.model.form.Login;
import domain.in.rjsa.model.form.OrganizationDetails;
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

	@RequestMapping(value = "/resetPass")
	public String resetPassword(ModelMap model) {
		String pageName = "resetPass";
		return pageName;
	}

	@RequestMapping(value = "/home")
	public String gethome(ModelMap model) {
		return "homeSC";
	}

	@RequestMapping(value = "/homePage")
	public String getHomePage( ModelMap model) {
		return  "homeSC/homeSCHomepage";
	}

	@RequestMapping(value = "/detail/{clientId}/{action}/{page}")
	public String getPage(@PathVariable Long clientId, @PathVariable String action, @PathVariable String page,
			ModelMap model) {
		return action + "/" + page;
	}

	@RequestMapping(value = "/list/{entity}/{page}")
	public String getListPage(@PathVariable Long clientId, @PathVariable String entity, @PathVariable String page,
			ModelMap model) {
		Login login = applicationCache.getLoginDetail(getPrincipal());
		OrganizationDetails cd = applicationCache.getOrganizationDetails(login.getClientId());
		List<Long> listSolId = new ArrayList<>();
		model.addAttribute("cd", cd);
		model.addAttribute("listBr", listSolId);
		return entity + "/" + page;
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
