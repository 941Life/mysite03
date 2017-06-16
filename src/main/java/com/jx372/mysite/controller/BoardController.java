package com.jx372.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jx372.mysite.service.BoardService;
import com.jx372.mysite.vo.BoardVO;
import com.jx372.mysite.vo.UserVO;
import com.jx372.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("/")
	public String list(Model model) {
		model.addAttribute("list", boardService.getList());
		return "/board/list";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write( UserVO userVo) {
		return "/board/write";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, @ModelAttribute BoardVO vo) {
		UserVO authUser = (UserVO) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/user/login";
		}
		boardService.insert(vo);

		return "/board/write";
	}

	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(HttpSession session, @PathVariable("no") Long no, Model model) {
		UserVO authUser = (UserVO) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/user/login";
		}
		// BoardVO boardVo = boardService.Modify(no , authUser.getNo());
		// model.addAttribute( "boardVo", boardVo );
		return "/board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpSession session, @ModelAttribute BoardVO vo) {
		UserVO authUser = (UserVO) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/user/login";
		}
		vo.setUserNo(authUser.getNo());
		boardService.Update(vo);
		System.out.println(vo);
		return "/board/view";
	}

	@RequestMapping("/view/{no}")
	public String view(Model model, @PathVariable Long no) {
		BoardVO boardvo = boardService.view(no);
		model.addAttribute("boardVo", boardvo);
		return "/board/view";
	}

	@RequestMapping("/delete/{no}")
	public String delete(HttpSession session, @PathVariable Long no, Model model) {

		UserVO authUser = (UserVO) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/user/login";
		}

		boardService.delete(no, authUser.getNo());
		System.out.println("삭제 " + no + "번");
		return "redirect:/board/";
	}

}
