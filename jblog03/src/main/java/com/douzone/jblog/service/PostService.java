package com.douzone.jblog.service;

import java.util.List;

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
	
	public List<PostVo> getPost(String blogId, Long categoryNo, Long postNo){
		
		if(categoryNo==null) { // categoryNo 없을 시, 전체 포스트 출력
			return postRepository.findAll(blogId);
		}
		
		else{ // categoryNo 있을 시, 해당 카테고리의 포스트 전체 출력
			PostVo postVo = new PostVo();
			postVo.setBlogId(blogId);
			postVo.setCategoryNo(categoryNo);
			return postRepository.findByCategoryNo(postVo);
		}
	}
	
	public List<PostVo> getPost(String blogId){
		return postRepository.findAll(blogId);
	}
	
	public List<PostVo> getPost(PostVo postVo){
		return postRepository.findByCategoryNo(postVo);
	}
	
	public Long getCount(Long categoryNo) {
		return postRepository.countByCategoryNo(categoryNo);
	}

	public Boolean insert(PostVo postVo, String id, String category) {
		
		if(postVo.getTitle()==null || postVo.getTitle().replaceAll(" ", "").equals("")) {
			return false;
		}
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(id);
		categoryVo.setName(category);
		postVo.setCategoryNo(categoryRepository.findByName(categoryVo));
		
		return postRepository.insert(postVo);
		
	}

	public PostVo getPost(Long postNo) {
		return postRepository.findByNo(postNo);
	}
	
	public PostVo getPost(List<PostVo> pList, Long postNo) {
		System.out.println(postNo);
		if(pList.size()==0){
			return null;
		}else if(postNo == null || postNo == 0){
			return pList.get(0);
		}else{
			return getPost(postNo);
		}
	}

	public List<PostVo> getpList(String id, Long categoryNo) {
		if(categoryNo == null) {
			return getPost(id);
		}
		
		PostVo postVo = new PostVo();
		postVo.setBlogId(id);
		postVo.setCategoryNo(categoryNo);

		return getPost(postVo);
	}

	

}