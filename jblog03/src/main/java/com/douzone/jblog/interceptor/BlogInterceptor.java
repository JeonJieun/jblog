package com.douzone.jblog.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		BlogVo blogVo = (BlogVo)request.getServletContext().getAttribute("blogVo");
		List<CategoryVo> cList = (List<CategoryVo>)request.getServletContext().getAttribute("cList");
		
		String url = request.getServletPath();
		String[] arrUrl = url.split("/");
		String id = arrUrl[1];
	
		if(blogVo == null || cList == null || blogVo.getId() != id) {
			blogVo = blogService.getBlog(id);
			cList = categoryService.getCategory(id);
			for(CategoryVo categoryVo : cList) {
				categoryVo.setPostCount(postService.getCount(categoryVo.getNo()));
			}
		}
		
		request.getServletContext().setAttribute("blogVo", blogVo);
		request.getServletContext().setAttribute("cList", cList);

		return true;
	}
}