package com.jx372.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVO;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join" , method=RequestMethod.GET)
	public String join(){	
		return "user/join";
	}
	
	@RequestMapping(value="/join" , method=RequestMethod.POST)
	public String join(@ModelAttribute UserVO userVo){
		userService.join( userVo );
		return "redirect:user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess" , method=RequestMethod.GET)
	public String joinsuccess(){	
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login" , method=RequestMethod.GET)
	public String login(){
		return "user/login";
	}
	
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String login(
			HttpSession session,
			Model model,
			@RequestParam(value = "email" , required=true, defaultValue="") String email,
			@RequestParam( value="password", required=true, defaultValue="") String password
			){
		UserVO userVo = userService.getUser( email, password);
		if( userVo == null){
			model.addAttribute("result","fail");
			return "user/login";
		}
		
		//인증
		session.setAttribute("authUser", userVo);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping( value="/modify" , method=RequestMethod.GET)
	public String modify(HttpSession session){
		//인증 여부 체크(접근제한)	 
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if( authUser == null){
			return "redirect:/user/login";
		}
		return "user/modify";
	}
	
//	@Auth( " " )
//	@RequestMapping( value="/modify" , method=RequestMethod.GET)
//	public String modify(){		
//		return "user/modify";
//	}
	
//	@ExceptionHandler (UserDaoException.class)
//	public String handlerUserDaoException(){
//		// 1. 로깅
//		// 2. 사과 페이지 안내
//		return "error/exception";
//	}
}
