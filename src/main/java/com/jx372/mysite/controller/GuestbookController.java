package com.jx372.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jx372.mysite.service.GuestbookService;
import com.jx372.mysite.vo.GuestbookVO;
import com.jx372.security.Auth;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	@Auth
	@RequestMapping(value={"/list","/"}, method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", guestbookService.getList());
		
		return "/guestbook/index";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String list(@ModelAttribute GuestbookVO vo){
		guestbookService.insert(vo);
		
		return "redirect:/guestbook/";
	}
	
	@RequestMapping("/deleteform/{no}")
	public String deleteform(
			@PathVariable ("no")
			int no,Model model){
			model.addAttribute("no",no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping("delete")
	public String delete(
			@ModelAttribute GuestbookVO vo){
		guestbookService.delete(vo);
		return "redirect:/guestbook/";
		
	}
	
}
