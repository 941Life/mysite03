package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.BoardVO;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;
//
//	public List<BoardVO> getList(String keyword, Integer page, Integer size) {
//
//		String sql1 = "   select a.no, a.title, a.hit, date_format(a.reg_date, '%Y-%m-%d %p %h:%i:%s'), a.depth, b.name, a.user_no"
//				+ "     from board a, user b" + "    where a.user_no = b.no" + " order by group_no desc, order_no asc"
//				+ "    limit ?, ?";
//
//		String sql2 = "   select a.no, a.title, a.hit, date_format(a.reg_date, '%Y-%m-%d %p %h:%i:%s'), a.depth, b.name, a.user_no"
//				+ "     from board a, user b" + "    where a.user_no = b.no"
//				+ "      and (title like ? or content like ?)" + " order by group_no desc, order_no asc"
//				+ "    limit ?, ?";
//
//	}

	public boolean insert(BoardVO vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			if (vo.getGroupNo() == 0) { // 새글
				String sql = " insert" + "   into board"
						+ " values (null, ?, ?, 0, now(), (select ifnull(max(group_no), 0) + 1 from board a), 1, 0, ?)";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setLong(3, vo.getUserNo());
			} else { // 답글
				String sql = " insert" + "   into board" + " values (null, ?, ?, 0, now(), ?, ?, ?, ?)";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setInt(3, vo.getGroupNo());
				pstmt.setInt(4, vo.getOrderNo());
				pstmt.setInt(5, vo.getDepth());
				pstmt.setLong(6, vo.getUserNo());
			}

			int count = pstmt.executeUpdate();
			return count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean delete(Long no, Long userNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("no", no);
		map.put("userNo", userNo);
		int count = sqlSession.delete("board.delete", map);
		return count == 1;
	}

	public int getTotalCount(String keyword) {
		int totalCount = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			if ("".equals(keyword)) {
				String sql = "select count(*) from board";
				pstmt = conn.prepareStatement(sql);
			} else {
				String sql = "select count(*)" + "  from board" + " where title like ? or content like ?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return totalCount;
	}

	public void increaseGroupOrder(Integer groupNo, Integer orderNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "update board set order_no = order_no + 1 where group_no = ? and order_no > ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, orderNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public void updateHit(Long boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "update board set hit = hit + 1 where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, boardNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public boolean update(BoardVO vo) {
		int count = sqlSession.update("board.update", vo);
		return count == 1;
	}

	public BoardVO get(Long no) {	
		return sqlSession.selectOne("board.view",no);
	}

	public List<BoardVO> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		return sqlSession.selectList("board.list", map);
	}
}
