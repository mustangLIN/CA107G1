package com.chat.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ChatJDBCDAO implements ChatDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO CHAT (C_ROOM_NO,MEM_NO,EMP_NO,C_ROOM_DATE,C_ROOM_FILE) VALUES (CHAT_SEQ.NEXTVAL,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT C_ROOM_NO,MEM_NO,EMP_NO,C_ROOM_DATE,C_ROOM_FILE FROM CHAT ORDER BY C_ROOM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT C_ROOM_NO,MEM_NO,EMP_NO,C_ROOM_DATE,C_ROOM_FILE FROM CHAT where C_ROOM_NO = ?";
	private static final String DELETE = 
		"DELETE FROM CHAT where C_ROOM_NO = ?";
	
	@Override
	public void insert(ChatVO chatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, chatVO.getMem_no());
			pstmt.setString(2, chatVO.getEmp_no());
			pstmt.setDate(3, chatVO.getC_room_date());
			pstmt.setString(4, chatVO.getC_room_file());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(Integer c_room_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, c_room_no);
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public ChatVO findByPrimaryKey(Integer c_room_no) {
		ChatVO chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, c_room_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				chatVO = new ChatVO();
				chatVO.setC_room_no(rs.getInt("C_ROOM_NO"));
				chatVO.setMem_no(rs.getString("MEM_NO"));
				chatVO.setEmp_no(rs.getString("EMP_NO"));
				chatVO.setC_room_date(rs.getDate("C_ROOM_DATE"));
				chatVO.setC_room_file(rs.getString("C_ROOM_FILE"));
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}			
		return chatVO;
	}
	
	@Override
	public List<ChatVO> getAll() {
		List<ChatVO> list = new ArrayList<ChatVO>();
		ChatVO chatVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				chatVO = new ChatVO();
				chatVO.setC_room_no(rs.getInt("C_ROOM_NO"));
				chatVO.setMem_no(rs.getString("MEM_NO"));
				chatVO.setEmp_no(rs.getString("EMP_NO"));
				chatVO.setC_room_date(rs.getDate("C_ROOM_DATE"));
				chatVO.setC_room_file(rs.getString("C_ROOM_FILE"));
				
				list.add(chatVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		ChatJDBCDAO dao = new ChatJDBCDAO();
		
//		// test insert() INSERT_STMT
//		ChatVO chatVO = new ChatVO();
//		chatVO.setMem_no("M000001");
//		chatVO.setEmp_no("EMP000001");
//		chatVO.setC_room_date(Date.valueOf("2012-05-01"));
//		chatVO.setC_room_file(null);
//		dao.insert(chatVO);
		
//		// test delete() DELETE
//		dao.delete(3);
		
//		// test findByPrimaryKey() GET_ONE_STMT
//		ChatVO chatVO = dao.findByPrimaryKey(1);
//		System.out.print(chatVO.getC_room_no() + ",");
//		System.out.print(chatVO.getMem_no() + ",");
//		System.out.print(chatVO.getEmp_no() + ",");
//		System.out.print(chatVO.getC_room_date() + ",");
//		System.out.print(chatVO.getC_room_file() + "\n");
//		System.out.println("---------------------");
		
//		// test getAll() GET_ALL_STMT
//		List<ChatVO> list = dao.getAll();
//		for (ChatVO row : list) {
//			System.out.print(row.getC_room_no() + ",");
//			System.out.print(row.getMem_no() + ",");
//			System.out.print(row.getEmp_no() + ",");
//			System.out.print(row.getC_room_date() + ",");
//			System.out.print(row.getC_room_file());
//			System.out.println();
//		}
		
	}
}
