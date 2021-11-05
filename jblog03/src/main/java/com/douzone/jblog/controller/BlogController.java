package com.douzone.jblog.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/{id:(?!main|assets|user|admin).*}")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping({""})
	public String index(@AuthUser UserVo authUser) {		
		return "blog/blog-main";
	}
	
	
	@Auth
	@RequestMapping({"/admin/basic"})
	public String basic(
			@AuthUser UserVo authUser, 
			Model model) {
		BlogVo blogVo = blogService.getBlog(authUser.getId());
		System.out.println("---------------------");
		System.out.println(authUser.getId());
		System.out.println(blogVo);
		model.addAttribute("vo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	
	@Auth
	@RequestMapping({"/admin/category"})
	public String category() {
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping({"/admin/write"})
	public String write() {
		return "blog/blog-admin-write";
	}
	
}
