package com.jx372.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jx372.mysite.service.AdminService;
import com.jx372.mysite.vo.AdminVO;
import com.jx372.security.Auth;

//@Auth( value={"ADMIN","USER"} )
//@Auth( {"ADMIN","USER"} )

@Auth( Auth.Role.ADMIN )
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
		@RequestMapping(value={"","/main" } , method=RequestMethod.GET  )
		public String main(AdminVO vo, Model model){
			AdminVO adminVo = adminService.getHome(vo);
			model.addAttribute("vo" , adminVo);
			return "admin/main";
		}
		
		@RequestMapping(value="/modify", method=RequestMethod.POST )
		public String modify(AdminVO vo , Model model){
			AdminVO adminVo = adminService.update(vo);
			model.addAttribute("main", adminVo);
			return "redirect:/";
		}
		
		@RequestMapping( "/board" )
		public String board(){
			return "admin/board";
		}
		
		@RequestMapping("/guestbook")
		public String guestbook(){
			return "admin/guestbook";
		}
		
		@RequestMapping("/user")
		public String user(){
			return "admin/user";
		}
}
