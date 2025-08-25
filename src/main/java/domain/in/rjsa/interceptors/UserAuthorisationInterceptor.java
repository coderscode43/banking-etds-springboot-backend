package domain.in.rjsa.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import domain.in.rjsa.web.ApplicationCache;
@Component
public class UserAuthorisationInterceptor implements HandlerInterceptor {

	ApplicationCache applicationCache;

	@Autowired
	public void setApplicationCache(ApplicationCache applicationCache) {
		this.applicationCache = applicationCache;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		
		
		String[] uris= 	request.getRequestURI().split("/");
		//uris[2] is page, uris[3] is action,uris[4] is clientId   
		if("index".equals(uris[2])||"static".equals(uris[2])){
			return true;
		}else{
//			String key = applicationCache.getUserNameLogin().get(getPrincipal()).getUserId()+"-"+uris[4];
//			if(applicationCache.getUserAuthorised().containsKey(key)){
//				if(applicationCache.getUserAuthorised().get(key).containsKey(uris[2]) && applicationCache.getUserAuthorised().get(key).get(uris[2]).contains(uris[3])){
					return true;
//				}
//			}
		}
//		response.sendError(403, "Access Denied");
//		return false;
	}
	
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;

	}
	
}
