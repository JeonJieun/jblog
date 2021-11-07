package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/{id:(?!main|assets|user|admin).*}")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({""})
	public String index(Model model,
			@AuthUser UserVo authUser,
			@PathVariable String id) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> cList = categoryService.getCategory(authUser.getId());
		model.addAttribute("cList", cList);
		
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping(value={"/admin/basic"}, method=RequestMethod.GET)
	public String basic(Model model,
			@AuthUser UserVo authUser) {
		BlogVo blogVo = blogService.getBlog(authUser.getId());

		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value={"/admin/basic"}, method=RequestMethod.POST)
	public String update(Model model,
			@AuthUser UserVo authUser,
			BlogVo newBlogVo,
			@RequestParam("logo-file") MultipartFile file) {
		
		BlogVo oriBlogVo = blogService.getBlog(authUser.getId());
		newBlogVo.setId(authUser.getId());
		
		if(file.isEmpty()) {
			newBlogVo.setLogo(oriBlogVo.getLogo());
		}
		
		else {
			String logo = fileUploadService.restoreImage(file);
			newBlogVo.setLogo(logo);
		}
		
		blogService.update(oriBlogVo, newBlogVo);
		
		List<BlogVo> list = blogService.getBlog();
		servletContext.setAttribute("list", list);
		
		return "redirect:/" + authUser.getId();
	}
	
	@Auth
	@RequestMapping(value={"/admin/category"}, method=RequestMethod.GET)
	public String category(Model model,
			@AuthUser UserVo authUser) {
		
		List<CategoryVo> cList = categoryService.getCategory(authUser.getId());
		for(CategoryVo categoryVo : cList) {
			categoryVo.setPostCount(postService.getPost(categoryVo.getNo()));
		}
		model.addAttribute("cList", cList);
		
		BlogVo blogVo = blogService.getBlog(authUser.getId());
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value={"/admin/category"}, method=RequestMethod.POST)
	public String categoryAdd(Model model,
			@AuthUser UserVo authUser,
			CategoryVo categoryVo) {
		categoryVo.setBlogId(authUser.getId());
		
		categoryService.insert(categoryVo);
		
		return "redirect:/" + authUser.getId() + "/admin/category";
	}
	
	@Auth
	@RequestMapping(value={"/admin/write"}, method=RequestMethod.GET)
	public String write(Model model,
			@AuthUser UserVo authUser) {
		
		List<CategoryVo> cList = categoryService.getCategory(authUser.getId());
		model.addAttribute("cList", cList);
		
		BlogVo blogVo = blogService.getBlog(authUser.getId());
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value={"/admin/write"}, method=RequestMethod.POST)
	public String postAdd(Model model,
			@AuthUser UserVo authUser,
			PostVo postVo,
			String category) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(authUser.getId());
		categoryVo.setName(category);
		
		postService.insert(postVo, categoryVo);
		
		return "redirect:/" + authUser.getId();
	}
	
}
