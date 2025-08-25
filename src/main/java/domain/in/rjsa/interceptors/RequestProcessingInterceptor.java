//package domain.in.rjsa.interceptors;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//public class RequestProcessingInterceptor extends HandlerInterceptorAdapter{
//
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		
//		
//		
//		String[] uris= 	request.getRequestURI().split("/");
//		return true;
//	}
//	
//	
//	
//}

package domain.in.rjsa.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.stereotype.Component;

@Component
public class RequestProcessingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String[] uris = request.getRequestURI().split("/");
		// you can log, validate, or modify request here

		return true; // continue processing
	}
}
