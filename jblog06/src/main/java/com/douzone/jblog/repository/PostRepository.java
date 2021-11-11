package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.PostRepositoryException;
import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> findAll(String blogId) throws PostRepositoryException {
		return sqlSession.selectList("post.findAll", blogId);
	}
	
	public Long countByCategoryNo(Long categoryNo) throws PostRepositoryException {
		return sqlSession.selectOne("post.countByCategoryNo", categoryNo);
	}
	
	public boolean delete(PostVo vo) throws PostRepositoryException {
		int count = sqlSession.delete("post.delete", vo);
		return count == 1;		

	}
	
	public boolean insert(PostVo vo) throws PostRepositoryException {
		int count = sqlSession.insert("post.insert", vo);
		return count == 1;
	}

	public PostVo findByNo(Long postNo) {
		return sqlSession.selectOne("post.findByNo", postNo);
	}

	public List<PostVo> findByCategoryNo(PostVo postVo) {
		return sqlSession.selectList("post.findByCategoryNo", postVo);
	}
}