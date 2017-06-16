package com.jx372.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.AdminVO;

@Repository
public class AdminDao {
	@Autowired
	private SqlSession sqlSession;
	
	public AdminVO home(AdminVO vo){
		AdminVO adminVO= sqlSession.selectOne("admin.main" , vo);
		
		return adminVO;
	}
	
	public boolean modify(AdminVO vo){
		int count = sqlSession.update("admin.update", vo);
		
		return count == 1;
	}

}
