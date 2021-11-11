package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public Boolean insert(CategoryVo categoryVo, String id) {
		categoryVo.setBlogId(id);
		if( categoryVo.getName()!=null && 
			!(categoryVo.getName().replaceAll(" ", "").equals("")) && 
			categoryRepository.countByName(categoryVo)==0 ) {
			return categoryRepository.insert(categoryVo);
		}
		return false;
	}
	
	public CategoryVo getCategory(Long no) {
		return categoryRepository.findByNo(no);
	}
	
	public List<CategoryVo> getCategory(String id) {
		return categoryRepository.findByBlogId(id);
	}
	
	public boolean delete(Long no) {
		return categoryRepository.delete(no);
	}

}