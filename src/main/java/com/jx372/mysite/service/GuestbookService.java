package com.jx372.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.GuestbookDao;
import com.jx372.mysite.vo.GuestbookVO;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	
	public List<GuestbookVO> getList(){
		List<GuestbookVO> list = guestbookDao.getList();
		return list;
	}
	
	public void insert(GuestbookVO vo){
		guestbookDao.insert(vo);
	}
	
	public void delete(GuestbookVO vo){
		guestbookDao.delete(vo);
	}
}
