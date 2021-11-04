package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"", "/main"})
	public String index() {		
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping({"/basic"})
	public String basic(@AuthUser UserVo authUser, Model model) {
		BlogVo blogVo = blogService.getBlog(authUser.getId());
		model.addAttribute("vo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping({"/category"})
	public String category() {
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping({"/write"})
	public String write() {
		return "blog/blog-admin-write";
	}

}
