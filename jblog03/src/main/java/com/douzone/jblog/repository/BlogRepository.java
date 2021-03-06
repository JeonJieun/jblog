package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.BlogRepositoryException;
import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BlogVo vo) throws BlogRepositoryException {
		int count = sqlSession.insert("blog.insert", vo);
		return count == 1;
	}
	
	public List<BlogVo> findAll() throws BlogRepositoryException {
		return sqlSession.selectList("blog.findAll");
	}
	
	public BlogVo findById(String id) throws BlogRepositoryException {
		return sqlSession.selectOne("blog.findById", id);
	}

	public boolean update(BlogVo vo) throws BlogRepositoryException {
		int count = sqlSession.update("blog.update", vo);
		return count == 1;
	}

	

}
