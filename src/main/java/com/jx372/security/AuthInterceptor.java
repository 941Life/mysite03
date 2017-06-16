package com.jx372.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jx372.mysite.vo.UserVO;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		
		//1. handler  종류
		if( handler instanceof HandlerMethod == false ){			
			return false;
		}
		
		//2 메소드에 @Auth가 붙어있는 지 확인
		Auth auth = ((HandlerMethod)handler).getMethodAnnotation( Auth.class );
		
		
		//3 메소드에 @Auth가 붙어 있지 않으면  
		if( auth == null ){
			//4. Class 에 붙어 있는지 확인
			auth = ((HandlerMethod)handler).
					getMethod().
					getDeclaringClass().
					getAnnotation( Auth.class );
			if( auth == null ){
				return true;
			}
		}
		
		//5. 접근 제어
		HttpSession session = request.getSession();
		if ( session == null ){
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		if (session.getAttribute( "authUser" )== null){
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 6.Role 체크를 해줌 session에 관리자인지 아닌지
		Auth.Role role = auth.value();
		UserVO authUser = (UserVO) session.getAttribute("authUser");
		System.out.println("check");
		System.out.println(authUser.getRole());
		
		if( role == Auth.Role.ADMIN &&
				authUser.getRole().equals( "ADMIN" ) == false ){
			return false;
	}
		return true;
	}

}
