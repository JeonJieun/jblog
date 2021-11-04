package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(CategoryVo vo) {
		return 1 == sqlSession.insert("gallery.insert", vo);
	}

	public Boolean delete(Long no) {
		return 1 == sqlSession.delete("gallery.delete", no);
	}

	public List<CategoryVo> findAll() {
		return sqlSession.selectList("gallery.findAll");
	}
}