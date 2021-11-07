package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.CategoryRepositoryException;
import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(CategoryVo vo) throws CategoryRepositoryException {
		return 1 == sqlSession.insert("category.insert", vo);
	}

	public Boolean delete(Long no) throws CategoryRepositoryException {
		return 1 == sqlSession.delete("category.delete", no);
	}
	
	public CategoryVo findByNo(Long no) throws CategoryRepositoryException {
		return sqlSession.selectOne("category.findByNo", no);
	}
	
	public List<CategoryVo> findByBlogId(String blogId) throws CategoryRepositoryException {
		return sqlSession.selectList("category.findByBlogId", blogId);
	}
	
	public Long countByName(CategoryVo vo) throws CategoryRepositoryException {
		return sqlSession.selectOne("category.countByName", vo);
	}
	
}