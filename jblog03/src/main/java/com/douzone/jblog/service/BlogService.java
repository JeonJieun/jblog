package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	public List<BlogVo> getBlog() {
		return blogRepository.findAll();
	}
	
	public BlogVo getBlog(String id) {
		return blogRepository.findById(id);
	}
	
	public List<BlogVo> getBlog(String which, String kwd) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean update(BlogVo vo) {
		return blogRepository.update(vo);
	}
	
}
