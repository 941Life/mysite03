package com.jx372.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.GuestbookVO;

@Repository
public class GuestbookDao {
	@Autowired
	private SqlSession sqlSession;

	public List<GuestbookVO> getList() {
		List<GuestbookVO> list = sqlSession.selectList( "guestbook.getList" );
		return list;
	}
	
	public int insert(GuestbookVO vo){
		int count = sqlSession.insert("guestbook.insert",vo);
		return count;
	}
	
	public int delete(GuestbookVO vo){
		int count = sqlSession.delete("guestbook.delete" , vo);
		return count;
	}
	
}
