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
@RequestMapping("/{id:(?!main|assets|user|blog).*}") // img_mapping = /blog/logo/라서
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
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}", "//{postNo}"})
	public String index(Model model,
			@PathVariable String id,
			@PathVariable(value = "categoryNo", required = false) Long categoryNo,
			@PathVariable(value = "postNo", required = false) Long postNo) {
		
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		List<CategoryVo> cList = categoryService.getCategory(id);
		model.addAttribute("cList", cList);
		List<PostVo> pList = postService.getpList(id, categoryNo);
		model.addAttribute("pList", pList);
		PostVo post = postService.getPost(pList, postNo);
		model.addAttribute("post", post);
		
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
		}else {
			String logo = fileUploadService.restoreImage(file);
			newBlogVo.setLogo(logo);
		}
		
		blogService.update(oriBlogVo, newBlogVo);
		servletContext.setAttribute("list", blogService.getBlog());
		
		return "redirect:/" + authUser.getId();
	}
	
	@Auth
	@RequestMapping(value={"/admin/category"}, method=RequestMethod.GET)
	public String category(Model model,
			@AuthUser UserVo authUser) {
		
		List<CategoryVo> cList = categoryService.getCategory(authUser.getId());
		for(CategoryVo categoryVo : cList) {
			categoryVo.setPostCount(postService.getCount(categoryVo.getNo()));
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

		categoryService.insert(categoryVo, authUser.getId());
		
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
		
		postService.insert(postVo, authUser.getId(), category);
		
		return "redirect:/" + authUser.getId();
	}
	
	@Auth
	@RequestMapping({"/admin/delete"})
	public String delete(Model model,
			@AuthUser UserVo authUser,
			CategoryVo categoryVo){
		categoryService.delete(categoryVo.getNo());
		
		return "redirect:/" + authUser.getId() + "/admin/category";
	}

}
