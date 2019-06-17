package domain.in.rjsa.web;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import domain.in.rjsa.model.Admin;
import domain.in.rjsa.model.ClientDetail;
import domain.in.rjsa.model.ImpDates;
import domain.in.rjsa.model.Login;
import domain.in.rjsa.model.Notifications;
import domain.in.rjsa.model.Remark;
import domain.in.rjsa.service.AdminService;
import domain.in.rjsa.service.ClientDetailService;
import domain.in.rjsa.service.EmployeeInchargeGroupService;
import domain.in.rjsa.service.EmployeeService;
import domain.in.rjsa.service.ImpDatesService;
import domain.in.rjsa.service.LoginService;
import domain.in.rjsa.service.NotificationsService;
import domain.in.rjsa.service.RemarkService;

@Service("ApplicationCache")
public class ApplicationCache {

	private ImpDatesService impDatesService;
	private NotificationsService notificationService;
	private RemarkService remarkService;
	private ClientDetailService cdService;
	private LoginService loginService;
	private EmployeeInchargeGroupService egService;
	private AdminService aService;
	private EmployeeService eService;

	@Cacheable(value = "clientDetail")
	public ClientDetail getClientDetail(Long id) {
		// TODO Auto-generated method stub
		return cdService.getByKey(id);
	}

	@Cacheable(value = "employeeInchargeGroupCode")
	public HashSet<String> getEmployeeInchargeGroupCode(Long employeeId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("employeeId", employeeId);
		HashSet<String> set = new HashSet<>();
		set.addAll(egService.ajax("groupCode", "", map));
		return set;
	}


	@Cacheable(value = "employeeAdmin")
	public Boolean isAdmin(Long employeeId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("employeeId", employeeId);
		Admin admin = aService.uniqueSearch(map);
		if(admin!=null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	

	@Cacheable(value = "login")
	public Login getLoginDetail(String userName) {
		// TODO Auto-generated method stub
		return loginService.getLogin(userName);
	}

	@Cacheable(value = "impDates")
	public List<ImpDates> getImpDates() {
		return impDatesService.getDates();
	}

	@Cacheable(value = "recentNotifications")
	public List<Notifications> getRecentNotifications(Long clientId) {
		return notificationService.getRecentNotifications(clientId);
	}

	@Cacheable(value = "recentRemark")
	public List<Remark> getRecentRemark(Long clientId) {
		return remarkService.getRecentRemark(clientId);
	}

	@CacheEvict(value = "login", key = "#userName")
	public void refreshLogin(String userName) {
		// TODO Auto-generated method stub
	}

	
	
	//**********************SET SERVICES HERE*********************************\\
	@Autowired
	public void setImpDatesService(ImpDatesService impDatesService) {
		this.impDatesService = impDatesService;
	}

	@Autowired
	public void setNotificationsService(NotificationsService notificationService) {
		this.notificationService = notificationService;
	}

	@Autowired
	public void setRemarkService(RemarkService remarkService) {
		this.remarkService = remarkService;
	}

	@Autowired
	public void setClientDetailService(ClientDetailService clientDetailService) {
		this.cdService = clientDetailService;
	}

	@Autowired
	public void setEGService(EmployeeInchargeGroupService eService) {
		this.egService = eService;
	}

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	@Autowired
	public void setAdminService(AdminService adminService) {
		this.aService = adminService;
	}
	@Autowired
	public void setEmployeeService(EmployeeService eService) {
		this.eService = eService;
	}

}
