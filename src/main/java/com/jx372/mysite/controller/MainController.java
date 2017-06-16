package com.jx372.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.service.AdminService;
import com.jx372.mysite.vo.AdminVO;

@Controller
public class MainController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping({"/index","/"})
	public String index(Model model , AdminVO vo){
		AdminVO adminVo = adminService.getHome(vo);
		model.addAttribute("main" , adminVo);
		
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping( "/hello")
	public String hello() { 
		return "<H1>안</H1>";
	}
}
