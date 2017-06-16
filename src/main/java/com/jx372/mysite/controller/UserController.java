package com.jx372.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVO;
import com.jx372.security.Auth;
import com.jx372.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join( @ModelAttribute UserVO userVo ) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(
			@ModelAttribute @Valid UserVO userVo, 
			BindingResult result,
			Model model) {
		if ( result.hasErrors() ) {
			// 에러 출력
			model.addAllAttributes( result.getModel() );
			return "user/join";
		}
		//userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser UserVO authUser, Model model) {
		UserVO userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVO", userVo);

		return "user/modify";
	}

	// @ExceptionHandler (UserDaoException.class)
	// public String handlerUserDaoException(){
	// // 1. 로깅
	// // 2. 사과 페이지 안내
	// return "error/exception";
	// }
}
