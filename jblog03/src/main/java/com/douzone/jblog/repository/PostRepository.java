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
		return sqlSession.selectList("guestbook.findAll");
	}
	
	public boolean delete(PostVo vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return count == 1;		

	}
	
	public boolean insert(PostVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		System.out.println(vo);
		return count == 1;
	}
}