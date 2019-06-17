package domain.in.rjsa.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.in.rjsa.web.ApplicationCache;
@Component
public class UserAuthorisationInterceptor extends HandlerInterceptorAdapter{

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
			return super.preHandle(request, response, handler);
		}else{
//			String key = applicationCache.getUserNameLogin().get(getPrincipal()).getUserId()+"-"+uris[4];
//			if(applicationCache.getUserAuthorised().containsKey(key)){
//				if(applicationCache.getUserAuthorised().get(key).containsKey(uris[2]) && applicationCache.getUserAuthorised().get(key).get(uris[2]).contains(uris[3])){
					return super.preHandle(request, response, handler);
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
