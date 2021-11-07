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
	
	public List<PostVo> findAll() throws PostRepositoryException {
		return sqlSession.selectList("post.findAll");
	}
	
	public Long countByCategoryNo(Long categoryNo) {
		return sqlSession.selectOne("post.countByCategoryNo", categoryNo);
	}
	
	public boolean delete(PostVo vo) {
		int count = sqlSession.delete("post.delete", vo);
		return count == 1;		

	}
	
	public boolean insert(PostVo vo) {
		int count = sqlSession.insert("post.insert", vo);
		System.out.println(vo);
		return count == 1;
	}
}