package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.GuestbookVO;

@Repository
public class GuestbookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		// 1. 드라이버 로딩
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2. Connection 하기
			String url = "jdbc:mysql://localhost:3306/webdb?useUnicode=true&useSSL=false&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver를 찾을 수 없습니다.");
		}
		return conn;
	}

	public List<GuestbookVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<GuestbookVO> list = new ArrayList<GuestbookVO>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "select no , name, message , date from guestbook order by no desc";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String message = rs.getString(3);
				String date = rs.getString(4);
				
				GuestbookVO vo = new GuestbookVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage(message);
				vo.setDate(date);
				
				list.add(vo);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	}
	
	public boolean insert(GuestbookVO vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = getConnection();
			
			String sql = "insert into guestbook values(null, ?,?,? ,now())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getMessage());
			
			int count = pstmt.executeUpdate();

			return count == 1;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public boolean delete(GuestbookVO vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPwd());
			
			int count = pstmt.executeUpdate();

			return count == 1;
		}catch (SQLException e){
			e.printStackTrace();
			
			return false;
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
