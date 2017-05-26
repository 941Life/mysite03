package com.jx372.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler( Exception.class )
	public ModelAndView HandleException(HttpServletRequest request, Exception e ){
		
		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter (errors) );
		
		// 2. 안내 페이지 가기 
		ModelAndView mav = new ModelAndView();
		mav.addObject("uri", request.getRequestURI());
		mav.addObject("exception", errors.toString());
		mav.setViewName("error/exception");
		
		return mav;
	}
}
