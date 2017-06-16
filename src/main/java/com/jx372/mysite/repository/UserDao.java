package com.jx372.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.exception.UserDaoException;
import com.jx372.mysite.vo.UserVO;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public UserVO get (String email){
		return sqlSession.selectOne("user.getByEmail", email);
	}
	
	//	Map을 ResultType
	public UserVO get(Long no) {
		Map map = sqlSession.selectOne("user.getByNo", no);
		//List<Map> list = sqlSession.selectList("user.getByNo", no);
		
		UserVO userVo = sqlSession.selectOne("user.getByNo" , no );
		return userVo;
	}

	public boolean update(UserVO vo) {
		int count = sqlSession.insert("user.update",vo);
		return count == 1;
	}

	// 로그인 처리
	public UserVO get(String email, String password) throws UserDaoException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		UserVO userVo = sqlSession.selectOne("user.login", map);
		
		return userVo;
	}

	public boolean insert(UserVO vo) {
		int count = sqlSession.insert("user.insert",vo);
		
		return count == 1;
	}
}
