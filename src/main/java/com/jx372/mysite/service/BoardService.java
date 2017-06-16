package com.jx372.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.BoardDao;
import com.jx372.mysite.vo.BoardVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	private static final int perPage = 10;
	private static final int page_size = 5;
	
	public List<BoardVO> getList(){
		List<BoardVO> list = boardDao.getList();
		
		return list;
	}
	
	public void insert(BoardVO vo){
		boardDao.insert(vo);
	}
	
	public void delete(Long boardNo, Long userNo){
		boardDao.delete(boardNo, userNo);
	}
	
	public BoardVO view(Long no){
		BoardVO vo = boardDao.get(no);
		System.out.println(vo);
		return vo;
	}
	
	public BoardVO Update(BoardVO vo){
		boardDao.update(vo);
		System.out.println(vo);
		return vo;
	}
	
	//public BoardVo Modify(BoardVo vo){
	//	boardDao.
	//}
}
