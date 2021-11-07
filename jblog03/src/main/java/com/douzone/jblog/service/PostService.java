package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Long getPost(Long categoryNo) {
		return postRepository.countByCategoryNo(categoryNo);
	}

	public Boolean insert(PostVo postVo, CategoryVo categoryVo) {
		postVo.setCategoryNo(categoryRepository.findByName(categoryVo));
		
		if(postVo.getTitle()==null || postVo.getTitle().replaceAll(" ", "").equals("")) {
			return false;
		}
		
		return postRepository.insert(postVo);
		
	}

}