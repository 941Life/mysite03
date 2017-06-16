package com.jx372.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.AdminDao;
import com.jx372.mysite.vo.AdminVO;

@Service
public class AdminService {

	@Autowired
	AdminDao adminDao;
	
	public AdminVO getHome(AdminVO vo){
		return adminDao.home(vo);	
	}
	
	public AdminVO update(AdminVO vo){
		adminDao.modify(vo);	
		return vo;
	}

}
