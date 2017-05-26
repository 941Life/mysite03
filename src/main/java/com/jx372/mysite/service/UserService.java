package com.jx372.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.UserDao;
import com.jx372.mysite.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void join ( UserVO userVo){
		userDao.insert(userVo);
	}
	
	public UserVO getUser(String email, String password){
		return userDao.get(email, password);
	}
	
	public UserVO getUser ( Long no ) {	
		return userDao.get(no);
	}
}
